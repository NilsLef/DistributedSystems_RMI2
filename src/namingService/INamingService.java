package namingService;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

import carRentalCompany.ICarRentalCompany;

public interface INamingService extends Remote {
	
	/*****************
	 * REGISTRATIONS *
	 *****************/
	public void register(String companyName, ICarRentalCompany company) throws RemoteException;
	public void unRegisterCompany(String companyName) throws RemoteException;
	
	/***********
	 * GETTERS *
	 ***********/
	public Collection<ICarRentalCompany> getAllRegisteredCompanies() throws RemoteException;
	public ICarRentalCompany getRegisteredCompany(String companyName) throws RemoteException;

}
