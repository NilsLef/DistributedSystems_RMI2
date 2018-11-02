package session;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import carRentalCompany.*;

public interface IReservationSession extends Remote {
	
	
	
	
    public Collection<ICarRentalCompany> getAllRentalCompanies() throws RemoteException; 
    
    public void createQuote(ReservationConstraints constraint, String carRenter) throws ReservationException, RemoteException;
    
    //Niet in git
    public void removeQuote(Quote quote) throws RemoteException;
    
    public Set<Quote> getCurrentQuotes() throws RemoteException;
    public List<Reservation> confirmQuotes() throws ReservationException, RemoteException;
    //public Set<CarType> getAvailableCarTypes(Date start, Date end) throws RemoteException;


    
    
    String getCheapestCarType() throws RemoteException;
	
    void addQuoteToSession(String name, Date start, Date end, String carType, String region) throws ReservationException, RemoteException;
    List<Reservation> confirmQuotes(String name) throws ReservationException, RemoteException;
   
	
	void checkForAvailableCarTypes(Date start, Date end) throws RemoteException;
	
    public String getClientName() throws RemoteException;



}
