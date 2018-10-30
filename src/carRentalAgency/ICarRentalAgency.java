package carRentalAgency;

import java.rmi.Remote;

import namingService.INamingService;
import session.IManagerSession;
import session.IReservationSession;

public interface ICarRentalAgency extends Remote {
	
	
	public IReservationSession createReservationSession(String id, String clientName);
	
	public IManagerSession createManagerSession();
	
	public void terminateReservationSession();
	
	public void terminateManagerSession();
	


}
