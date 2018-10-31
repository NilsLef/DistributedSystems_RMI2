package carRentalAgency;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

import namingService.INamingService;
import session.*;


public class CarRentalAgency implements ICarRentalAgency {

	private Map<String, ReservationSession> reservationSessions = new HashMap<String, ReservationSession>();
	private Map<String, ManagerSession> managerSessions = new HashMap<String, ManagerSession>();
	
	private final INamingService namingService;

	public CarRentalAgency(INamingService ns) {
		this.namingService = ns;
	}

	@Override
	public IReservationSession createReservationSession(String id, String clientName) throws RemoteException {
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
	public IManagerSession createManagerSession(String id, String clientName) throws RemoteException {
		IManagerSession s = this.managerSessions.get(id);
		if (s != null)
			return s;
		else {
			ManagerSession newSession = new ManagerSession(this.namingService, id, clientName);
			this.managerSessions.put(id, newSession);
			return (IManagerSession) UnicastRemoteObject.exportObject(newSession, 0);
		}
	}

	@Override
	public void terminateReservationSession(String id) {
		synchronized (this.reservationSessions) {
			this.reservationSessions.remove(id);
		}
	}

	@Override
	public void terminateManagerSession(String id) {
		synchronized (this.managerSessions) {
			this.managerSessions.remove(id);
		}
	}

}
