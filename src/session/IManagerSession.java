package session;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Set;

import carRentalCompany.CarType;
import carRentalCompany.ICarRentalCompany;

public interface IManagerSession extends Remote {
	
	/************
	 * CARTYPES *
	 ************/
    List<CarType> getAllCarTypes(String crcName) throws RemoteException;
    
    /*****************************
	 * NUMBER OF RESERVATIONS BY *
	 *****************************/
    int getNumberOfReservationsBy(String clientName) throws RemoteException;
    int getNumberOfReservationsForCarType(String carRentalName, String carType) throws RemoteException;
    
	/*********************
	 * BEST/MOST POPULAR *
	 *********************/
    String getBestCustomer() throws RemoteException;
	Set<String> getBestClients() throws RemoteException;
	CarType getMostPopularCarTypeIn(String carRentalCompanyName, int year) throws RemoteException;
	
	

	/********************************
	 * REGISTRATION RENTALCOMPANIES *
	 ********************************/
	public void registerCRC(String crcName, ICarRentalCompany crc) throws RemoteException;
	public void unregisterCRC(String crcName) throws RemoteException;

}
