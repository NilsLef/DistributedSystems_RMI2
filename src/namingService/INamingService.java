package namingService;
import java.rmi.Remote;
import java.util.Map;

import carRentalCompany.ICarRentalCompany;

public interface INamingService extends Remote {
	
	public void register(String companyName, ICarRentalCompany company);
	
	public void unRegisterCompany(String companyName);
	
	public Map<String, ICarRentalCompany> getAllRegisteredCompanies();
	
	public ICarRentalCompany getRegisteredCompany(String companyName);

}
