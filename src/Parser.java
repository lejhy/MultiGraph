import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {
	
	List<Station> stations;
	List<Route<Station>> routes;
	
	public Parser(String fileName) {
		stations = new ArrayList<Station>();
		routes = new ArrayList<Route<Station>>();
		parseFile(fileName);
	}
	
	public List<Station> getStations(){
		return stations;
	}
	
	public List<Route<Station>> getRoutes(){
		return routes;
	}
	
	private void parseFile(String fileName) {
		File file = new File(fileName);
		try {
			Scanner scanner = new Scanner(file);
			stations.add(null);
			while(scanner.hasNextLine()) {
				stations.add(scanner.nextInt(), new Station(scanner.next()));
				scanner.nextLine();
			}
			scanner.close();
			scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				//TODO Retrieve all edges from file
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found in parseFile");
		}
	}   
}
