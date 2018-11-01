package session;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import carRentalCompany.*;

public interface IReservationSession extends Remote {
	
    Set<String> getAllRentalCompanies();
    
    Quote createQuote(ReservationConstraints constraint, String carRenter) throws ReservationException, RemoteException;
    void removeQuote(Quote quote);
    Set<Quote> getCurrentQuotes();
    List<Reservation> confirmQuotes() throws ReservationException, RemoteException;
    public Set<CarType> getAvailableCarTypes(Date start, Date end) throws RemoteException;
    //public String getClientName() throws RemoteException;
    
    
    String getCheapestCarType();
	
    void addQuoteToSession(String name, Date start, Date end, String carType, String region);
    List<Quote> confirmQuotes(String name);
   
	
	void checkForAvailableCarTypes(Date start, Date end);
	

}
