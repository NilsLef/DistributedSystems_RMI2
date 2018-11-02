package session;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import carRentalCompany.*;
import namingService.INamingService;

public class ReservationSession extends Session implements IReservationSession {
	
	/***************
	 * CONSTRUCTOR *
	 ***************/
	private Map<Quote, ICarRentalCompany> allQuotes = new HashMap<Quote, ICarRentalCompany>();
	private String clientName;
	
	public ReservationSession(INamingService ns, String sID, String cName) {
		super(ns, sID);
		this.clientName = cName;
	}
    
	/***********
	 * GETTERS *
	 ***********/
	public String getClientName() throws RemoteException {
		return this.clientName;
	}

    @Override
    public Set<ICarRentalCompany> getAllRentalCompanies() throws RemoteException {
        return new HashSet<ICarRentalCompany>(namingService.getAllRegisteredCompanies());
    }
    
	/**********
	 * QUOTES *
	 **********/
    @Override
    public void createQuote(ReservationConstraints constraint, String carRenter) throws ReservationException, RemoteException {
        for (ICarRentalCompany crc : this.getNamingService().getAllRegisteredCompanies()) {
            try {
                Quote quote = crc.createQuote(constraint, carRenter);
                this.allQuotes.put(quote, crc);
            } catch (ReservationException exc) {
                System.out.println("Reservation exception was thrown");
            }
        }
        if (this.allQuotes.isEmpty())
        	throw new ReservationException("An Exception ocurred: no Quote could be created");
        
    }
    
    @Override
    public Set<Quote> getCurrentQuotes()  {
        return new HashSet<Quote>(this.allQuotes.keySet());
    }
    
    @Override
    public void removeQuote(Quote quote) {
        allQuotes.remove(quote);
    }


	@Override
	public void addQuoteToSession(String name, Date start, Date end, String carType, String region) throws ReservationException, RemoteException {
		ReservationConstraints constraint = new ReservationConstraints(start, end, carType, region);
        for (ICarRentalCompany crc : this.getNamingService().getAllRegisteredCompanies()) {
            try {
                Quote quote = crc.createQuote(constraint, this.getClientName());
                this.allQuotes.put(quote, crc);
            } catch (Exception exc) {
                throw new ReservationException("An Exception ocurred in adding a Quote to the sessions");
            }
        }
	}

	@Override
	public List<Reservation> confirmQuotes(String name) throws ReservationException, RemoteException {
        Map<Reservation, ICarRentalCompany> confirmedQuotes = new HashMap<Reservation, ICarRentalCompany>();
		List<Reservation> confirmedRes = new ArrayList<Reservation>();
        try {
            for (Map.Entry<Quote, ICarRentalCompany> quote : this.allQuotes.entrySet()) {
            	if (quote.getKey().getCarRenter().equals(name)){
            		Reservation reservation = quote.getValue().confirmQuote(quote.getKey());
            		confirmedRes.add(reservation);
            		confirmedQuotes.put(reservation, quote.getValue());
            	}
            }
        } catch (ReservationException exc) {
            for (Reservation reservation : confirmedRes) {
                confirmedRes.remove(reservation);
                confirmedQuotes.get(reservation).cancelReservation(reservation);
            }
            throw new ReservationException("error");
        }
        return confirmedRes;
	}
	
	/*************************
	 * GET SPECIFIC CARTYPES *
	 *************************/
    public Set<CarType> getAvailableCarTypes(Date start, Date end) throws RemoteException {
        Set<CarType>  availableCarTypes = new HashSet<CarType>();
        for (ICarRentalCompany crc : namingService.getAllRegisteredCompanies()) {
            availableCarTypes.addAll(crc.getAvailableCarTypes(start, end));
        }
        return availableCarTypes;
    }
    

	@Override
	public String getCheapestCarType() throws RemoteException {
		String cheapestCarType = "";
		double lowestRentalPricePerDay = Double.MAX_VALUE;
		for(ICarRentalCompany crc : this.getNamingService().getAllRegisteredCompanies()) {
			for(CarType carType : crc.getAllCarTypes()) {				
				if (lowestRentalPricePerDay > carType.getRentalPricePerDay()) {
					lowestRentalPricePerDay = carType.getRentalPricePerDay();
					cheapestCarType = carType.getName();
				}
			}
		}
		return cheapestCarType;
	}


	

}
