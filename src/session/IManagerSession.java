package session;

import java.util.Set;

public interface IManagerSession {
	
	public IManagerSession(String name, String carRentalName);
	
	public Set<String> getBestClients();

}
