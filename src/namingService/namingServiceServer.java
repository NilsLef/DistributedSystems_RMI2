package namingService;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class namingServiceServer {
	
	public static void main(String[] args) throws RemoteException {
		System.setSecurityManager(null);
		INamingService ns = (INamingService) new NamingService();
        INamingService stub = (INamingService) UnicastRemoteObject.exportObject(ns, 0);

		Registry r = LocateRegistry.getRegistry();
		r.rebind("namingservice", stub);
	}

}
