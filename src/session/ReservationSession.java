package session;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import carRentalCompany.*;

public class ReservationSession implements IReservationSession {
	
	
	private Map<Quote, CarRentalCompany> allQuotes = new HashMap<Quote, CarRentalCompany>();
    

    @Override
    public Set<String> getAllRentalCompanies() {
        return new HashSet<String>(RentalStore.getRentals().keySet());
    }
    
    @Override
    public Quote createQuote(ReservationConstraints constraint, String carRenter) throws ReservationException {
        for (CarRentalCompany crc : RentalStore.getRentals().values()) {
            try {
                Quote quote = crc.createQuote(constraint, carRenter);
                this.allQuotes.put(quote, crc);
                return quote;
            } catch (ReservationException exc) {
                System.out.println("Reservation exception was thrown");
            }
        }
        throw new ReservationException("An Exception ocurred");
        
    }
    
    @Override
    public Set<Quote> getCurrentQuotes()  {
        return new HashSet(this.allQuotes.keySet());
    }
    
    @Override
    public List<Reservation> confirmQuotes() throws ReservationException  {
        Map<Reservation, CarRentalCompany> confirmedRes = new HashMap<Reservation, CarRentalCompany>();
        try {
            for (Map.Entry<Quote, CarRentalCompany> quote : this.allQuotes.entrySet()) {
                Reservation reservation = quote.getValue().confirmQuote(quote.getKey());
                confirmedRes.put(reservation, quote.getValue());
            }
        } catch (ReservationException exc) {
            for (Reservation reservation : confirmedRes.keySet()) {
                confirmedRes.get(reservation).cancelReservation(reservation);
            }
            throw new ReservationException("error");
        }
        this.allQuotes = new HashMap<Quote, CarRentalCompany>();
        return new ArrayList<Reservation>(confirmedRes.keySet());
    }
    
    @Override
    public Set<CarType> getAvailableCarTypes(Date start, Date end) {
        Set<CarType>  availableCarTypes = new HashSet<CarType>();
        for (CarRentalCompany crc : RentalStore.getRentals().values()) {
            availableCarTypes.addAll(crc.getAvailableCarTypes(start, end));
        }
        return availableCarTypes;
    }
    
    @Override
    public void removeQuote(Quote quote) {
        allQuotes.remove(quote);
    }


}
