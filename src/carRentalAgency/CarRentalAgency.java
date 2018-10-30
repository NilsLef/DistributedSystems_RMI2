package carRentalAgency;

import java.util.HashMap;
import java.util.Map;

import namingService.INamingService;
import session.*;


public class CarRentalAgency implements ICarRentalAgency {

	private Map<String, ReservationSession> reservationSessions = new HashMap<String, ReservationSession>();
	private Map<String, ManagerSession> ManagerSessions = new HashMap<String, ManagerSession>();
	
	private final INamingService namingService;

	public CarRentalAgency() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public IReservationSession createReservationSession() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IManagerSession createManagerSession() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void terminateReservationSession() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void terminateManagerSession() {
		// TODO Auto-generated method stub
		
	}

}
