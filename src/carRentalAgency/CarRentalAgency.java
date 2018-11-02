package carRentalAgency;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import namingService.INamingService;
import session.*;


public class CarRentalAgency implements ICarRentalAgency {

	/***************
	 * CONSTRUCTOR *
	 ***************/
	
	private Map<String, ReservationSession> reservationSessions = new HashMap<String, ReservationSession>();
	private Map<String, ManagerSession> managerSessions = new HashMap<String, ManagerSession>();
	
	private final INamingService namingService;
	

	public CarRentalAgency(INamingService ns) {
		if (ns == null) {
			throw new IllegalArgumentException();
		}
		this.namingService = ns;
	}
	

	/************
	 * SESSIONS *
	 ************/

	@Override
	public IReservationSession createReservationSession(String id, String clientName) throws RemoteException {
		if (id == null || clientName == null)
			throw new IllegalArgumentException();
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
		if (id == null || clientName == null)
			throw new IllegalArgumentException();
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
	
	/************
	 * TIME-OUTS *
	 ************/
	
	public void terminateInactiveReservationSession(Date atLast) {
		Object[] resSessions = this.reservationSessions.values().toArray();
		for (int x=0; x<resSessions.length; x++) {
			ReservationSession temp = (ReservationSession) resSessions[x];
			if(temp.getDate().before(atLast))
				terminateReservationSession(temp.getSessionID());
		}
	}
	
	public void terminateInactiveManagerSession(Date atLast) {
		Object[] manSessions = this.managerSessions.values().toArray();
		for (int x=0; x<manSessions.length; x++) {
			ManagerSession temp = (ManagerSession) manSessions[x];
			if(temp.getDate().before(atLast))
				terminateReservationSession(temp.getSessionID());
		}
	}




}
