package namingService;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import carRentalCompany.*;

public class NamingService implements INamingService {

	public Map<String, ICarRentalCompany> allRegisteredCompanies = new HashMap<String, ICarRentalCompany>();

	@Override
	public void register(String companyName, ICarRentalCompany company) throws RemoteException {
		allRegisteredCompanies.put(companyName, company);
		
	}

	@Override
	public void unRegisterCompany(String companyName) throws RemoteException {
		allRegisteredCompanies.remove(companyName);
	}

	@Override
	public Collection<ICarRentalCompany> getAllRegisteredCompanies() throws RemoteException {
		return new ArrayList<ICarRentalCompany>(this.allRegisteredCompanies.values());
	}

	@Override
	public ICarRentalCompany getRegisteredCompany(String companyName) throws RemoteException {
		return allRegisteredCompanies.get(companyName);
	}
	
	public boolean doesCompanyExist(String companyName) throws RemoteException {
		if (allRegisteredCompanies.containsKey(companyName)) {
			return true;
		} else {
			return false; 
		}
	}

}
