package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.List;
import java.util.Set;

import carRentalAgency.ICarRentalAgency;
import carRentalCompany.Reservation;
import session.IManagerSession;
import session.IReservationSession;
import carRentalCompany.CarRentalCompanyServer;
import carRentalCompany.CarType;
import carRentalCompany.ICarRentalCompany;


public class Client extends AbstractTestManagement<IReservationSession, IManagerSession> {
	
	/********
	 * MAIN *
	 ********/
	
	public static final String connection = "0.0.0.0";
	
	public static void main(String[] args) throws Exception {
    	System.setProperty("java.rmi.server.hostname",connection);
    	//CarRentalAgencyServer.main(new String[] {});
    	Registry r = LocateRegistry.getRegistry(connection);
    	ICarRentalAgency carRentalAgency = (ICarRentalAgency) r.lookup("carRentalAgency");
    	Client client = new Client("trips", carRentalAgency);
    	
    	ICarRentalCompany company1 = CarRentalCompanyServer.serverSetUp("hertz.csv");
    	ICarRentalCompany company2 = CarRentalCompanyServer.serverSetUp("dockx.csv");
    	//CarRentalCompanyServer.main(new String[] {});
    	
    	IManagerSession manSess = client.cra.createManagerSession("setup");
    	manSess.registerCRC("Hertz", company1);
    	manSess.registerCRC("Dockx", company2);


		client.run();
		
	}

	

	/***************
	 * CONSTRUCTOR *
	 ***************/
	
	/**
	 * A private variable registering the car rental company
	 */
	private ICarRentalAgency cra;
	
	

	
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
	
	/************
	 * SESSIONS *
	 ************/
	
	@Override
	protected IReservationSession getNewReservationSession(String name) throws Exception {
		return cra.createReservationSession("Res_"+name, name);
	}

	@Override
	protected IManagerSession getNewManagerSession(String name, String carRentalName) throws Exception {
		return cra.createManagerSession("Man_"+name, name);
	}
	
	
	

	/***************
	 * RESERVATION *
	 ***************/
	@Override
	protected void checkForAvailableCarTypes(IReservationSession session, Date start, Date end) throws Exception {
		session.getAvailableCarTypes(start, end);
	}

	@Override
	protected void addQuoteToSession(IReservationSession session, String name, Date start, Date end, String carType, String region) throws Exception {
		session.addQuoteToSession(name, start, end, carType, region);
	}

	@Override
	protected List<Reservation> confirmQuotes(IReservationSession session, String name) throws Exception {
		return ((IReservationSession) session).confirmQuotes(name);
	}

	
	/***********
	 * MANAGER *
	 ***********/

	@Override
	protected Set<String> getBestClients(IManagerSession ms) throws Exception {
		return  ms.getBestClients();
	}
	
	@Override
	protected CarType getMostPopularCarTypeIn(IManagerSession ms, String carRentalCompanyName, int year) throws Exception {
		return ms.getMostPopularCarTypeIn(carRentalCompanyName, year);
	}


	@Override
	protected String getCheapestCarType(IReservationSession session, Date start, Date end, String region) throws Exception {
		return session.getCheapestCarType();
	}
	
	@Override
	protected int getNumberOfReservationsBy(IManagerSession ms, String clientName) throws Exception {
		return ms.getNumberOfReservationsBy(clientName);
	}

	@Override
	protected int getNumberOfReservationsForCarType(IManagerSession ms, String carRentalName, String carType) throws Exception {
		return ms.getNumberOfReservationsForCarType(carRentalName, carType);	
	}
	
	

}