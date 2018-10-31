package session;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import carRentalCompany.*;

public interface IReservationSession extends Remote {
	
    public Set<String> getAllRentalCompanies(); 
    public void createQuote(ReservationConstraints constraint, String carRenter) throws ReservationException, RemoteException;
    public void removeQuote(Quote quote);
    public Set<Quote> getCurrentQuotes();
    public List<Reservation> confirmQuotes() throws ReservationException, RemoteException;
    public Set<CarType> getAvailableCarTypes(Date start, Date end) throws RemoteException;
    public String getClientName() throws RemoteException;
	

}
