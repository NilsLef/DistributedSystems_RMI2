package client;



import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.List;
import java.util.Set;

import carRentalAgency.CarRentalAgencyServer;
import carRentalAgency.ICarRentalAgency;
import carRentalCompany.Reservation;
import namingService.INamingService;
import session.IManagerSession;
import session.IReservationSession;
import carRentalCompany.Car;
import carRentalCompany.CarRentalCompanyServer;
import carRentalCompany.CarType;
import carRentalCompany.ICarRentalCompany;


public class Client extends AbstractTestManagement<IReservationSession, IManagerSession> {
	
	/********
	 * MAIN *
	 ********/
	
	public static void main(String[] args) throws Exception {

		System.setSecurityManager(null);
    	System.setProperty("java.rmi.server.hostname","0.0.0.0");
    	//CarRentalAgencyServer.main(new String[] {});

    	Registry r = LocateRegistry.getRegistry("0.0.0.0");
    	ICarRentalAgency carRentalAgency = (ICarRentalAgency) r.lookup("carRentalAgency");
		
    	
    	Client client = new Client("trips", carRentalAgency);
    	
    	List<Car> cars = CarRentalCompanyServer.loadData("hertz.csv");
    	ICarRentalCompany company1 = CarRentalCompanyServer.serverSetUp("Hertz", cars);
    	cars = CarRentalCompanyServer.loadData("dockx.csv");
    	ICarRentalCompany company2 = CarRentalCompanyServer.serverSetUp("Dockx", cars);
    	CarRentalCompanyServer.main(new String[] {});
    	IManagerSession ms = client.getNewManagerSession("manager");
		ms.registerCarRentalCompany("Hertz", company1);
		ms.registerCarRentalCompany("Dockx", company2);
    	
    	
    	
    	//System.out.println("Client is ready for action!");

		client.run();
	}
	
	protected IManagerSession getNewManagerSession(String name)
			throws Exception {
		return cra.getManagerSession("manager_" + name);
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
	

	
	protected Set<String> getBestClients(IManagerSession ms) throws Exception {
		return  ms.getBestClients();
	}


	protected String getCheapestCarType(IReservationSession session, Date start, Date end, String region) throws Exception {
		return session.getCheapestCarType();
	}


	protected CarType getMostPopularCarTypeIn(IManagerSession ms, String carRentalCompanyName, int year)
			throws Exception {
		return ms.getMostPopularCarTypeIn(carRentalCompanyName, year);
	}


	protected IReservationSession getNewReservationSession(String name) throws Exception {
		return cra.createReservationSession("Res_"+name, name);
	}


	protected IManagerSession getNewManagerSession(String name, String carRentalName) throws Exception {
		return cra.createManagerSession("Man_"+name, name);
	}

	
	protected void checkForAvailableCarTypes(IReservationSession session, Date start, Date end) throws Exception {
		session.checkForAvailableCarTypes(start, end);
	}

	
	protected void addQuoteToSession(IReservationSession session, String name, Date start, Date end, String carType, String region)
			throws Exception {
		session.addQuoteToSession(name, start, end, carType, region);
	}


	protected List<Reservation> confirmQuotes(IReservationSession session, String name) throws Exception {
		return ((IReservationSession) session).confirmQuotes(name);
	}

	
	protected int getNumberOfReservationsBy(IManagerSession ms, String clientName) throws Exception {
		return ms.getNumberOfReservationsBy(clientName);
	}

	
	protected int getNumberOfReservationsForCarType(IManagerSession ms, String carRentalName, String carType) throws Exception {
		return ms.getNumberOfReservationsForCarType(carRentalName, carType);	
	}


}