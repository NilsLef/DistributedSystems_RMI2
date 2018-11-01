package session;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import carRentalCompany.CarType;

public interface IManagerSession extends Remote {
	
	//public IManagerSession(String name, String carRentalName);
	
    List<CarType> getAllCarTypes(String crcName) throws RemoteException;
    
    Integer nbOfReservations(String crcName, String carType) throws RemoteException;
    
    Integer nbOfReservationsBy(String carRenter) throws RemoteException;
    
    
    
    String getBestCustomer();
	
	Set<String> getBestClients();
	
	CarType getMostPopularCarTypeIn(String carRentalCompanyName, int year);
	
    int getNumberOfReservationsBy(String clientName);
    int getNumberOfReservationsForCarType(String carRentalName, String carType) throws RemoteException;
	
	

}
