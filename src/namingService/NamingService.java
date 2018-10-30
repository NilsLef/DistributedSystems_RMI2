package namingService;
import java.util.HashMap;
import java.util.Map;

public class NamingService implements INamingService {

	public Map<String, ICarRentalCompany> allRegisteredCompanies = new HashMap<String, ICarRentalCompany>();

	@Override
	public void register(String companyName, ICarRentalCompany company) {
		allRegisteredCompanies.put(companyName, company);
		
	}

	@Override
	public void unRegisterCompany(String companyName) {
		allRegisteredCompanies.remove(companyName);
	}

	@Override
	public Map<String, ICarRentalCompany> getAllRegisteredCompanies() {
		return this.allRegisteredCompanies;
	}

	@Override
	public ICarRentalCompany getRegisteredCompany(String companyName) {
		return getAllRegisteredCompanies().get(companyName);
	}

}
