package namingService;


import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import carRentalCompany.ReservationException;

public class NamingServiceServer {
			
		public static void main(String[] args) throws ReservationException, NumberFormatException, IOException {
			System.setSecurityManager(null);
			INamingService namingservice = new NamingService();
			INamingService stub = (INamingService) UnicastRemoteObject.exportObject(namingservice,0);
			Registry r = LocateRegistry.getRegistry();
			r.rebind("naming service", stub);
			System.out.println("The naming service is ready for action!");
		}
	
		


}
