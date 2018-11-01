package session;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import carRentalCompany.CarRentalCompany;
import carRentalCompany.CarType;
import carRentalCompany.ICarRentalCompany;
import namingService.INamingService;

public class ManagerSession extends Session implements IManagerSession {
	
	private String clientName;

	public ManagerSession(INamingService ns, String cName, String sID) {
		super(ns, sID);
		this.clientName = cName;
	}


    @Override
    public List<CarType> getAllCarTypes(String crcName) throws RemoteException {
        return new ArrayList<CarType>(getNamingService().getRegisteredCompany(crcName).getAllCarTypes());
    }

    @Override
    public Integer nbOfReservations(String crcName, String carType) throws RemoteException {
        return this.getNamingService().getRegisteredCompany(crcName).getNumberOfReservationsForCarType(carType);
    }
    
    @Override 
    public Integer nbOfReservationsBy(String carRenter) throws RemoteException {
        Collection<ICarRentalCompany> rentalCompanies = this.getNamingService().getAllRegisteredCompanies().values();
        Integer amount = 0;
        for ( ICarRentalCompany crc : rentalCompanies) {
            amount += crc.getReservationsByRenter(carRenter).size();
        }
        return amount;
    }

    @Override
    public String getBestCustomer() {       
        String bestCustomer = "";
        Collection<ICarRentalCompany> rentalCompanies = this.getNamingService().getAllRegisteredCompanies().values();
        Map<String, Integer> reservations = new HashMap<String, Integer>();     
        for (ICarRentalCompany crc : rentalCompanies) {
            Map<String, Integer> reservationsByCompany = new HashMap<String, Integer>();
            for(Map.Entry<String, Integer> info : reservationsByCompany.entrySet()) {
                String customer = info.getKey();
                if (reservations.containsKey(customer)) {
                    Integer number = reservations.get(customer) + info.getValue();
                    reservations.put(customer, number);
                } else {
                    reservations.put(customer, info.getValue());
                }
            }
        }
        for (Map.Entry<String, Integer> res : reservations.entrySet()) {
            if (res.getValue() > reservations.get(bestCustomer) || "".equals(bestCustomer)) {
                bestCustomer = res.getKey();
            }    
        }
        return bestCustomer;
        
    }


	@Override
	public Set<String> getBestClients() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public CarType getMostPopularCarTypeIn(String carRentalCompanyName, int year) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int getNumberOfReservationsBy(String clientName) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int getNumberOfReservationsForCarType(String carRentalName, String carType) throws RemoteException {
		return this.getNamingService().getRegisteredCompany(carRentalName).getNumberOfReservationsForCarType(carType);
	}



}
