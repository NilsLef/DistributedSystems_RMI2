package carRentalCompany;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;




public class CarRentalCompanyServer {

	public static void main(String[] args) throws ReservationException, IOException {
		CrcData data = loadData("hertz.csv");
		serverSetUp(data.name, data.cars, data.regions);
		data = loadData("dockx.csv");
		serverSetUp(data.name, data.cars, data.regions);
	}
	
	public static ICarRentalCompany serverSetUp(String companyName, List<Car> cars, List<String> regions) {
        System.setSecurityManager(null);
		try {
            ICarRentalCompany carRentalCompany = new CarRentalCompany(companyName, regions, cars);
            ICarRentalCompany stub =
                (ICarRentalCompany) UnicastRemoteObject.exportObject(carRentalCompany, 0);
            return stub;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
	
	public static ICarRentalCompany serverSetUp(String csvFile) {
        System.setSecurityManager(null);
		try {
			CrcData data = loadData(csvFile);
            ICarRentalCompany carRentalCompany = new CarRentalCompany(data.name, data.regions, data.cars);
            ICarRentalCompany stub =
                (ICarRentalCompany) UnicastRemoteObject.exportObject(carRentalCompany, 0);
            return stub;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
	
	public static CrcData loadData(String datafile)
			throws ReservationException, NumberFormatException, IOException {

		CrcData out = new CrcData();
		int nextuid = 0;

		// open file
		BufferedReader in = new BufferedReader(new FileReader(datafile));
		StringTokenizer csvReader;
		
		try {
			// while next line exists
			
			while (in.ready()) {
				String line = in.readLine();
				
				if (line.startsWith("#")) {
					// comment -> skip					
				} else if (line.startsWith("-")) {
					csvReader = new StringTokenizer(line.substring(1), ",");
					out.name = csvReader.nextToken();
					out.regions = Arrays.asList(csvReader.nextToken().split(":"));
				} else {
					// tokenize on ,
					csvReader = new StringTokenizer(line, ",");
					// create new car type from first 5 fields
					CarType type = new CarType(csvReader.nextToken(),
							Integer.parseInt(csvReader.nextToken()),
							Float.parseFloat(csvReader.nextToken()),
							Double.parseDouble(csvReader.nextToken()),
							Boolean.parseBoolean(csvReader.nextToken()));
					System.out.println(type);
					// create N new cars with given type, where N is the 5th field
					for (int i = Integer.parseInt(csvReader.nextToken()); i > 0; i--) {
						out.cars.add(new Car(nextuid++, type));
					}
				}
			}
		} finally {
			in.close();
		}

		return out;
	}
	 
		static class CrcData {
			public List<Car> cars = new LinkedList<Car>();
			public String name;
			public List<String> regions =  new LinkedList<String>();
		}
	


}
