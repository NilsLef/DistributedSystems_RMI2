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
	
	
	private Map<Quote, ICarRentalCompany> allQuotes = new HashMap<Quote, ICarRentalCompany>();
	private String clientName;
	
	public ReservationSession(INamingService ns, String cName, String sID) {
		super(ns, sID);
		this.clientName = cName;
	}
    
	public String getClientName() {
		return this.clientName;
	}

    @Override
    public Set<String> getAllRentalCompanies() {
        return namingService.getAllRegisteredCompanies().keySet();
    }
    
    @Override
    public void createQuote(ReservationConstraints constraint, String carRenter) throws ReservationException, RemoteException {
        for (ICarRentalCompany crc : namingService.getAllRegisteredCompanies().values()) {
            try {
                Quote quote = crc.createQuote(constraint, carRenter);
                this.allQuotes.put(quote, crc);
            } catch (ReservationException exc) {
                System.out.println("Reservation exception was thrown");
            }
        }
        throw new ReservationException("An Exception ocurred");
        
    }
    
    @Override
    public Set<Quote> getCurrentQuotes()  {
        return new HashSet<Quote>(this.allQuotes.keySet());
    }
    
    @Override
    public List<Reservation> confirmQuotes() throws ReservationException, RemoteException  {
        Map<Reservation, ICarRentalCompany> confirmedRes = new HashMap<Reservation, ICarRentalCompany>();
        try {
            for (Map.Entry<Quote, ICarRentalCompany> quote : this.allQuotes.entrySet()) {
                Reservation reservation = quote.getValue().confirmQuote(quote.getKey());
                confirmedRes.put(reservation, quote.getValue());
            }
        } catch (ReservationException exc) {
            for (Reservation reservation : confirmedRes.keySet()) {
                confirmedRes.get(reservation).cancelReservation(reservation);
            }
            throw new ReservationException("error");
        }
        this.allQuotes = new HashMap<Quote, ICarRentalCompany>();
        return new ArrayList<Reservation>(confirmedRes.keySet());
    }
    
    @Override
    public Set<CarType> getAvailableCarTypes(Date start, Date end) throws RemoteException {
        Set<CarType>  availableCarTypes = new HashSet<CarType>();
        for (ICarRentalCompany crc : namingService.getAllRegisteredCompanies().values()) {
            availableCarTypes.addAll(crc.getAvailableCarTypes(start, end));
        }
        return availableCarTypes;
    }
    
    @Override
    public void removeQuote(Quote quote) {
        allQuotes.remove(quote);
    }


}
