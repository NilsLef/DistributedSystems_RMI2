package session;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import carRentalCompany.*;

public interface IReservationSession extends Remote {
	
    Set<String> getAllRentalCompanies();
    
    Quote createQuote(ReservationConstraints constraint, String carRenter) throws ReservationException;
    void removeQuote(Quote quote);
    Set<Quote> getCurrentQuotes();
    List<Reservation> confirmQuotes() throws ReservationException;
    public Set<CarType> getAvailableCarTypes(Date start, Date end);
    //public String getClientName() throws RemoteException;
	

}
