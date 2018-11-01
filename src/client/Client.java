package client;

import java.rmi.NotBoundException;


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import carRentalAgency.ICarRentalAgency;
import carRentalCompany.ICarRentalCompany;
import carRentalCompany.Quote;
import namingService.INamingService;
import session.IManagerSession;
import session.IReservationSession;
import session.ManagerSession;
import session.ReservationSession;
import carRentalCompany.CarType;


public class Client extends AbstractTestManagement {
	
	/********
	 * MAIN *
	 ********/
	
	public static void main(String[] args) throws Exception {

		System.setSecurityManager(null);
		// An example reservation scenario on car rental company 'Hertz' would be...		
		//String carRentalCompanyName = "Hertz";
		Client client = new Client("simpleTrips", "agency");
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
	 * Return the car rental company of this client
	 */
	private ICarRentalAgency getCarRentalAgency() {
		return this.cra;
	}
	
	/**
	 * Constructs this client
	 * 
	 * @param scriptFile
	 * 		  The file that needs to be executed as a string
	 * @param carRentalCompanyName
	 * 		  The name of the requested car rental company as a string
	 */
	public Client(String scriptFile, String carRentalAgencyName) {
		super(scriptFile);


		try {
			Registry registry = LocateRegistry.getRegistry(null);
			ICarRentalAgency stub = (ICarRentalAgency) registry.lookup(carRentalAgencyName);
			this.cra = stub;

		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
	

	@Override
	protected Set<String> getBestClients(Object ms) throws Exception {
		return ((IManagerSession) ms).getBestClients();
	}

	@Override
	protected String getCheapestCarType(Object session, Date start, Date end, String region) throws Exception {
		return ((IReservationSession) session).getCheapestCarType();
	}

	@Override
	protected CarType getMostPopularCarTypeIn(Object ms, String carRentalCompanyName, int year)
			throws Exception {
		return ((IManagerSession) ms).getMostPopularCarTypeIn(carRentalCompanyName, year);
	}

	@Override
	protected Object getNewReservationSession(String name) throws Exception {
		return null; //TODO
		//return new ReservationSession(null, name, name); //TODO!!!
	}

	@Override
	protected Object getNewManagerSession(String name, String carRentalName) throws Exception {
		return null; //TODO
		//return new ManagerSession(null, carRentalName, carRentalName); //TODO!!!
	}

	@Override
	protected void checkForAvailableCarTypes(Object session, Date start, Date end) throws Exception {
		((IReservationSession) session).checkForAvailableCarTypes(start, end);
	}

	@Override
	protected void addQuoteToSession(Object session, String name, Date start, Date end, String carType, String region)
			throws Exception {
		((IReservationSession) session).addQuoteToSession(name, start, end, carType, region);
	}

	@Override
	protected List<Quote> confirmQuotes(Object session, String name) throws Exception {
		return ((IReservationSession) session).confirmQuotes(name);
	}

	@Override
	protected int getNumberOfReservationsBy(Object ms, String clientName) throws Exception {
		return ((IManagerSession) ms).getNumberOfReservationsBy(clientName);
	}

	@Override
	protected int getNumberOfReservationsForCarType(Object ms, String carRentalName, String carType) throws Exception {
		return ((IManagerSession) ms).getNumberOfReservationsForCarType(carRentalName, carType);	
	}


}