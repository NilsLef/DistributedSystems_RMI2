package carRentalCompany;


import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Car implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private int id;
    private CarType type;
    private List<Reservation> reservations;

    /***************
     * CONSTRUCTOR *
     ***************/
    
    public Car(int uid, CarType type) {
    	this.id = uid;
        this.type = type;
        this.reservations = new ArrayList<Reservation>();
    }

    /******
     * ID *
     ******/
    
    public int getId() {
    	return id;
    }
    
    /************
     * CAR TYPE *
     ************/
    
    public CarType getType() {
        return type;
    }

    /****************
     * RESERVATIONS *
     ****************/

    public boolean isAvailable(Date start, Date end) {
        if(!start.before(end))
            throw new IllegalArgumentException("Illegal given period");

        for(Reservation reservation : reservations) {
            if(reservation.getEndDate().before(start) || reservation.getStartDate().after(end))
                continue;
            return false;
        }
        return true;
    }
    
    public void addReservation(Reservation res) {
        reservations.add(res);
    }
    
    public void removeReservation(Reservation reservation) {
        // equals-method for Reservation is required!
        reservations.remove(reservation);
    }
    
    public List<Reservation> getReservations() {
    	List<Reservation> result = new java.util.ArrayList<Reservation>(this.reservations);
    	return result;
    }
    
    public List<Reservation> getAllReservationsBy(String clientName){
    	List<Reservation> reservationsByClient = new ArrayList<Reservation>();
    	for (Reservation reservation : this.reservations) {
    		if(reservation.getCarRenter().equals(clientName))
    			reservationsByClient.add(reservation);
    	}
    	return reservationsByClient;
    }
    
    public int getNumberOfReservations() {
    	return this.reservations.size();
    }
    
    public int getNumberOfReservationsInYear(int year) throws ParseException {
    	int amount = 0;
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	String y = Integer.toString(year);
        Date startOfYear = sdf.parse(year +"-01-01");
        Date endOfYear = sdf.parse(year +"-12-31");

    	for (Reservation r : this.reservations) {
    		if ((r.getStartDate().compareTo(endOfYear) < 0) || (r.getEndDate().compareTo(startOfYear) < 0))
    				amount++;
    	}
    	
    	return amount;
    }
    
    public boolean isCarType(String carType) {
    	return getType().isSameCarType(carType);
    }
}