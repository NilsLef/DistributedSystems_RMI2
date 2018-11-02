package namingService;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import carRentalCompany.*;

public class NamingService implements INamingService {

	public Map<String, ICarRentalCompany> allRegisteredCompanies = new HashMap<String, ICarRentalCompany>();

	
	/*****************
	 * REGISTRATIONS *
	 *****************/
	@Override
	public synchronized void register(String companyName, ICarRentalCompany company) throws RemoteException {
		allRegisteredCompanies.put(companyName, company);
	}

	@Override
	public synchronized void unRegisterCompany(String companyName) throws RemoteException {
		allRegisteredCompanies.remove(companyName);
	}

	/***********
	 * GETTERS *
	 ***********/
	@Override
	public Collection<ICarRentalCompany> getAllRegisteredCompanies() throws RemoteException {
		return new ArrayList<ICarRentalCompany>(this.allRegisteredCompanies.values());
	}

	@Override
	public ICarRentalCompany getRegisteredCompany(String companyName) throws RemoteException {
		if (allRegisteredCompanies.containsKey(companyName))
			return allRegisteredCompanies.get(companyName);
		else
			throw new IllegalArgumentException(companyName + " doesn't exist in NamingService.");

	}
	
	/**********
	 * TESTER *
	 **********/
	public boolean doesCompanyExist(String companyName) throws RemoteException {
		if (allRegisteredCompanies.containsKey(companyName)) {
			return true;
		} else {
			return false; 
		}
	}

}
