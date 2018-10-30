package carRentalAgency;

import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

import namingService.INamingService;
import session.*;


public class CarRentalAgency implements ICarRentalAgency {

	private Map<String, ReservationSession> reservationSessions = new HashMap<String, ReservationSession>();
	private Map<String, ManagerSession> ManagerSessions = new HashMap<String, ManagerSession>();
	
	private final INamingService namingService;

	public CarRentalAgency(INamingService ns) {
		this.namingService = ns;
	}

	@Override
	public IReservationSession createReservationSession(String id, String clientName) {
		IReservationSession s = this.reservationSessions.get(id);
		if (s != null)
			return s;
		else {
			ReservationSession newSession = new ReservationSession(this.namingService, id, clientName);
			this.reservationSessions.put(id, newSession);
			return (IReservationSession) UnicastRemoteObject.exportObject(newSession, 0);
		}
		
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
