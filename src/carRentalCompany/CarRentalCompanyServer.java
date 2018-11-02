package carRentalCompany;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;


public class CarRentalCompanyServer {

	public static void main(String[] args) throws ReservationException, IOException {
		//System.setProperty("java.rmi.server.hostname","0.0.0.0");
		
		List<Car> cars = loadData("hertz.csv");
		serverSetUp("Hertz", cars);
		cars = loadData("dockx.csv");
		serverSetUp("Dockx", cars);
		
		System.out.println("CarRentalCompanyServers are ready for action!");
	}
	
	public static ICarRentalCompany serverSetUp(String companyName, List<Car> cars) {
        System.setSecurityManager(null);
        
		try {
            ICarRentalCompany carRentalCompany = new CarRentalCompany(companyName, cars);
            ICarRentalCompany stub =
                (ICarRentalCompany) UnicastRemoteObject.exportObject(carRentalCompany, 0);
            return stub;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
	
	 public static List<Car> loadData(String datafile) throws NumberFormatException, IOException {

		 	List<Car> cars = new LinkedList<Car>();
	        StringTokenizer csvReader;
	        int nextuid = 0;
	       
	        //open file from jar
	        BufferedReader in = new BufferedReader(new FileReader(datafile));
	        
	        try {
	            while (in.ready()) {
	                String line = in.readLine();
	                
	                if (line.startsWith("#")) {
	                    // comment -> skip					
	                } else if (line.startsWith("-")) {
	                    csvReader = new StringTokenizer(line.substring(1), ",");
	                    //out.name = csvReader.nextToken();
	                    //out.regions = Arrays.asList(csvReader.nextToken().split(":"));
	                } else {
	                    csvReader = new StringTokenizer(line, ",");
	                    //create new car type from first 5 fields
	                    CarType type = new CarType(csvReader.nextToken(),
	                            Integer.parseInt(csvReader.nextToken()),
	                            Float.parseFloat(csvReader.nextToken()),
	                            Double.parseDouble(csvReader.nextToken()),
	                            Boolean.parseBoolean(csvReader.nextToken()));
	        				System.out.println(type);
	                    //create N new cars with given type, where N is the 5th field
	                    for (int i = Integer.parseInt(csvReader.nextToken()); i > 0; i--) {
	                        cars.add(new Car(nextuid++, type));
	                    }        
	                }
	            } 
	        } finally {
	            in.close();
	        }

	        return cars;
	    }
	


}
