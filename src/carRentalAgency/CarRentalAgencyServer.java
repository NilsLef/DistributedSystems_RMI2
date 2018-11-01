package carRentalAgency;


import java.io.IOException;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import carRentalCompany.ReservationException;
import namingService.INamingService;

public class CarRentalAgencyServer {
			
		public static void main(String[] args) throws ReservationException , NumberFormatException, IOException {
			System.setSecurityManager(null);
			ICarRentalAgency carRentalAgency = new CarRentalAgency(createNamingService());
			ICarRentalAgency stub = (ICarRentalAgency) UnicastRemoteObject.exportObject(carRentalAgency,0);
			Registry r = LocateRegistry.getRegistry();
			r.rebind("carRentalAgency", stub);
			System.out.println("The Car Rental Agency is ready for action!");
		}
	
		
		private static INamingService createNamingService() {
			System.setSecurityManager(null);
			try {
				Registry r = LocateRegistry.getRegistry();
				INamingService ns = (INamingService) r.lookup("namingservice");
				return ns; 
			} catch (NotBoundException | RemoteException ex) {
				ex.printStackTrace();
			}
			return null;
			
		}


}
