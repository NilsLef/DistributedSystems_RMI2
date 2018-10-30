package carRentalAgency;

import java.rmi.Remote;

import session.IManagerSession;
import session.IReservationSession;

public interface ICarRentalAgency extends Remote {
	
	public IReservationSession createReservationSession();
	
	public IManagerSession createManagerSession();
	
	public void terminateReservationSession();
	
	public void terminateManagerSession();
	


}
