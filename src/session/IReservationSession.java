package session;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import carRentalCompany.*;

public interface IReservationSession extends Remote {
	
	/***********
	 * GETTERS *
	 ***********/
	public String getClientName() throws RemoteException;
	public Set<ICarRentalCompany> getAllRentalCompanies() throws RemoteException;
	
	/**********
	 * QUOTES *
	 **********/
    public void createQuote(ReservationConstraints constraint, String carRenter) throws ReservationException, RemoteException;
    public Set<Quote> getCurrentQuotes() throws RemoteException;
    public void removeQuote(Quote quote) throws RemoteException;
    public void addQuoteToSession(String name, Date start, Date end, String carType, String region) throws ReservationException, RemoteException;
    public List<Reservation> confirmQuotes(String name) throws ReservationException, RemoteException;
    
	/*************************
	 * GET SPECIFIC CARTYPES *
	 *************************/
    public Set<CarType> getAvailableCarTypes(Date start, Date end) throws RemoteException;
    public String getCheapestCarType() throws RemoteException;

}
