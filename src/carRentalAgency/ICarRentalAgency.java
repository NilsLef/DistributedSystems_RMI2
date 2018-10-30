package carRentalAgency;

public interface ICarRentalAgency {
	
	public IReservationSession createReservationSession();
	
	public IManagerSession createManagerSession();
	
	
	
	
	public void terminateReservationSession();
	
	public void terminateManagerSession();
	


}
