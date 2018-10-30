package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;


public class Client extends AbstractTestManagement {
	
	/********
	 * MAIN *
	 ********/
	
	public static void main(String[] args) throws Exception {

		System.setSecurityManager(null);
		
		// An example reservation scenario on car rental company 'Hertz' would be...		
		String carRentalCompanyName = "Hertz";

		//Client client = new Client("simpleTrips", carRentalCompanyName);
		//client.run();
	}
	

	/***************
	 * CONSTRUCTOR *
	 ***************/
	
	/**
	 * A private variable registering the car rental company
	 */
	//private ICarRentalCompany crc;
	
	/**
	 * Return the car rental company of this client
	 */
	/*private ICarRentalCompany getCarRentalCompany() {
		return this.crc;
	}*/
	
	/**
	 * Constructs this client
	 * 
	 * @param scriptFile
	 * 		  The file that needs to be executed as a string
	 * @param carRentalCompanyName
	 * 		  The name of the requested car rental company as a string
	 */
	/*public Client(String scriptFile, String carRentalCompanyName) {
		super(scriptFile);


		try {
			Registry registry = LocateRegistry.getRegistry(null);
			ICarRentalCompany stub = (ICarRentalCompany) registry.lookup(carRentalCompanyName);
			this.crc = stub;

		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}*/
	
	/**
	 * Check which car types are available in the given period
	 * and print this list of car types.
	 *
	 * @param 	start
	 * 			start time of the period
	 * @param 	end
	 * 			end time of the period
	 * @throws 	Exception
	 * 			if things go wrong, throw exception
	 */
/*	@Override
	protected void checkForAvailableCarTypes(Date start, Date end) throws Exception {
		Set<CarType> list = getCarRentalCompany().getAvailableCarTypes(start, end);
		for ( CarType type : list) {
			System.out.println(type);
		}
	}

	/**
	 * Retrieve a quote for a given car type (tentative reservation).
	 * 
	 * @param	clientName 
	 * 			name of the client 
	 * @param 	start 
	 * 			start time for the quote
	 * @param 	end 
	 * 			end time for the quote
	 * @param 	carType 
	 * 			type of car to be reserved
	 * @param 	region
	 * 			region in which car must be available
	 * @return	the newly created quote
	 *  
	 * @throws 	Exception
	 * 			if things go wrong, throw exception
	 */
/*	@Override
	protected Quote createQuote(String clientName, Date start, Date end,
			String carType, String region) throws Exception {
		ReservationConstraints constraints = new ReservationConstraints(start, end, carType, region);
		return getCarRentalCompany().createQuote(constraints, clientName);
	}

	/**
	 * Confirm the given quote to receive a final reservation of a car.
	 * 
	 * @param 	quote 
	 * 			the quote to be confirmed
	 * @return	the final reservation of a car
	 * 
	 * @throws 	Exception
	 * 			if things go wrong, throw exception
	 */
/*	@Override
	protected Reservation confirmQuote(Quote quote) throws Exception {
		return getCarRentalCompany().confirmQuote(quote);
	}
	
	/**
	 * Get all reservations made by the given client.
	 *
	 * @param 	clientName
	 * 			name of the client
	 * @return	the list of reservations of the given client
	 * 
	 * @throws 	Exception
	 * 			if things go wrong, throw exception
	 */
//	@Override
/*	protected List<Reservation> getReservationsByRenter(String clientName) throws Exception {
		return getCarRentalCompany().getReservationsByRenter(clientName);
	}

	/**
	 * Get the number of reservations for a particular car type.
	 * 
	 * @param 	carType 
	 * 			name of the car type
	 * @return 	number of reservations for the given car type
	 * 
	 * @throws 	Exception
	 * 			if things go wrong, throw exception
	 */
/*	@Override
	protected int getNumberOfReservationsForCarType(String carType) throws Exception {
		return getCarRentalCompany().getNumberOfReservationsForCarType(carType);
	}  */

	@Override
	protected Set getBestClients(Object ms) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getCheapestCarType(Object session, Date start, Date end, String region) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected client.CarType getMostPopularCarTypeIn(Object ms, String carRentalCompanyName, int year)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Object getNewReservationSession(String name) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Object getNewManagerSession(String name, String carRentalName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void checkForAvailableCarTypes(Object session, Date start, Date end) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void addQuoteToSession(Object session, String name, Date start, Date end, String carType, String region)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected List confirmQuotes(Object session, String name) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected int getNumberOfReservationsBy(Object ms, String clientName) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int getNumberOfReservationsForCarType(Object ms, String carRentalName, String carType) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
/*
	@Override
	protected void processLine(String name, String cmd, List<Character> flags, StringTokenizer scriptLineTokens)
			throws ApplicationException {
		// TODO Auto-generated method stub
		
	}*/

}