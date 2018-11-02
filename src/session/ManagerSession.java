package session;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import carRentalCompany.CarType;
import carRentalCompany.ICarRentalCompany;
import carRentalCompany.Reservation;
import namingService.INamingService;

public class ManagerSession extends Session implements IManagerSession {
	
	/***************
	 * CONSTRUCTOR *
	 ***************/
	private String clientName;

	public ManagerSession(INamingService ns, String sID, String cName) {
		super(ns, sID);
		this.clientName = cName;
	}
	

	/************
	 * CARTYPES *
	 ************/
    @Override
    public List<CarType> getAllCarTypes(String crcName) throws RemoteException {
        return new ArrayList<CarType>(getNamingService().getRegisteredCompany(crcName).getAllCarTypes());
    }

    /*****************************
	 * NUMBER OF RESERVATIONS BY *
	 *****************************/
    
    @Override
	public int getNumberOfReservationsBy(String clientName) throws RemoteException {
		int numberOfReservations = 0;
        Collection<ICarRentalCompany> rentalCompanies = this.getNamingService().getAllRegisteredCompanies(); 
        for (ICarRentalCompany crc : rentalCompanies) {
        	List<Reservation> toAdd = crc.getReservationsByRenter(clientName);
        	numberOfReservations += toAdd.size();
        	
        }
        return numberOfReservations;
	}


	@Override
	public int getNumberOfReservationsForCarType(String carRentalName, String carType) throws RemoteException {
		return this.getNamingService().getRegisteredCompany(carRentalName).getNumberOfReservationsForCarType(carType);
	}
	
	/*********************
	 * BEST/MOST POPULAR *
	 *********************/
    @Override
    public String getBestCustomer() throws RemoteException {       
        String bestCustomer = "";
        Collection<ICarRentalCompany> rentalCompanies = this.getNamingService().getAllRegisteredCompanies();
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
	public Set<String> getBestClients() throws RemoteException {
		Map<String, Integer> clientsNbOfReservations = new HashMap<String, Integer>();
		for(ICarRentalCompany crc : this.getNamingService().getAllRegisteredCompanies()) {
			for(String client : crc.getClients()) {
				if(clientsNbOfReservations.containsKey(client)) {
					clientsNbOfReservations.put(client, clientsNbOfReservations.get(client)+crc.getReservationsByRenter(client).size());
				} else {
					clientsNbOfReservations.put(client, crc.getReservationsByRenter(client).size());
				}
			}
		}
		int maxNbReservations = Collections.max(clientsNbOfReservations.values());
		Set<String> bestClients = new HashSet<String>();
		for (String c : clientsNbOfReservations.keySet()) {
			if (clientsNbOfReservations.get(c) == maxNbReservations)
				bestClients.add(c);
		}
		return bestClients;
	}


	@Override
	public CarType getMostPopularCarTypeIn(String carRentalCompanyName, int year) throws RemoteException {
		ICarRentalCompany crc = this.getNamingService().getRegisteredCompany(carRentalCompanyName);
		int mostReservations = 0;
		CarType mostPopularCarType = null;
		for (CarType ct : crc.getAllCarTypes()) {
			if (crc.getNumberOfReservationsForCarType(ct.getName(), year)>mostReservations) {
				mostReservations = crc.getNumberOfReservationsForCarType(ct.getName(), year);
				mostPopularCarType = ct;
			}
		}
		return mostPopularCarType;
	}


	

	/********************************
	 * REGISTRATION RENTALCOMPANIES *
	 ********************************/
	public void registerCRC(String crcName, ICarRentalCompany crc) throws RemoteException {
		this.namingService.register(crcName, crc);
	}
	
	public void unregisterCRC(String crcName) throws RemoteException {
		this.namingService.unRegisterCompany(crcName);
	}
	
}
