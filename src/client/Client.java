package client;

import java.rmi.NotBoundException;


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import carRentalAgency.CarRentalAgencyServer;
import carRentalAgency.ICarRentalAgency;
import carRentalCompany.ICarRentalCompany;
import carRentalCompany.Quote;
import carRentalCompany.Reservation;
import namingService.INamingService;
import session.IManagerSession;
import session.IReservationSession;
import session.ManagerSession;
import session.ReservationSession;
import carRentalCompany.CarType;


public class Client extends AbstractTestManagement<ReservationSession, ManagerSession> {
	
	/********
	 * MAIN *
	 ********/
	
	public static void main(String[] args) throws Exception {

		System.setSecurityManager(null);
    	System.setProperty("java.rmi.server.hostname","0.0.0.0");
    	CarRentalAgencyServer.main(new String[] {});

    	Registry r = LocateRegistry.getRegistry("0.0.0.0");
    	ICarRentalAgency carRentalAgency = (ICarRentalAgency) r.lookup("carRentalAgency");
		
    	
    	Client client = new Client("trips", carRentalAgency);
    	

		client.run();
	}
	

	/***************
	 * CONSTRUCTOR *
	 ***************/
	
	/**
	 * A private variable registering the car rental company
	 */
	private ICarRentalAgency cra;
	
	INamingService ns;
	

	
	/**
	 * Constructs this client
	 * 
	 * @param scriptFile
	 * 		  The file that needs to be executed as a string
	 * @param carRentalCompanyName
	 * 		  The name of the requested car rental company as a string
	 */
	public Client(String scriptFile, ICarRentalAgency carRentalAgency) {
		super(scriptFile);
		this.cra = carRentalAgency;
	}
	

	
	protected Set<String> getBestClients(ManagerSession ms) throws Exception {
		return ((IManagerSession) ms).getBestClients();
	}


	protected String getCheapestCarType(ReservationSession session, Date start, Date end, String region) throws Exception {
		return ((IReservationSession) session).getCheapestCarType();
	}


	protected CarType getMostPopularCarTypeIn(ManagerSession ms, String carRentalCompanyName, int year)
			throws Exception {
		return ((IManagerSession) ms).getMostPopularCarTypeIn(carRentalCompanyName, year);
	}


	protected ReservationSession getNewReservationSession(String name) throws Exception {
		return (ReservationSession) cra.createReservationSession("Res_"+name, name);
	}


	protected ManagerSession getNewManagerSession(String name, String carRentalName) throws Exception {
		return (ManagerSession) cra.createManagerSession("Man_"+name, name);
	}

	@Override
	protected void checkForAvailableCarTypes(ReservationSession session, Date start, Date end) throws Exception {
		((IReservationSession) session).checkForAvailableCarTypes(start, end);
	}

	@Override
	protected void addQuoteToSession(ReservationSession session, String name, Date start, Date end, String carType, String region)
			throws Exception {
		((IReservationSession) session).addQuoteToSession(name, start, end, carType, region);
	}


	protected List<Reservation> confirmQuotes(ReservationSession session, String name) throws Exception {
		return ((IReservationSession) session).confirmQuotes(name);
	}

	
	protected int getNumberOfReservationsBy(ManagerSession ms, String clientName) throws Exception {
		return ((IManagerSession) ms).getNumberOfReservationsBy(clientName);
	}

	
	protected int getNumberOfReservationsForCarType(ManagerSession ms, String carRentalName, String carType) throws Exception {
		return ((IManagerSession) ms).getNumberOfReservationsForCarType(carRentalName, carType);	
	}


}