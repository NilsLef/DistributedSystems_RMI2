package carRentalAgency;

import java.rmi.Remote;
import java.rmi.RemoteException;

import session.IManagerSession;
import session.IReservationSession;

public interface ICarRentalAgency extends Remote {
	
	
	public IReservationSession createReservationSession(String id, String clientName) throws RemoteException;
	
	public IManagerSession createManagerSession(String id, String clientName) throws RemoteException;
	
	public void terminateReservationSession(String id) throws RemoteException;
	
	public void terminateManagerSession(String id) throws RemoteException;
	


}
