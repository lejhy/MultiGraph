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
		Scanner fileScanner;
		Scanner tokenScanner;
		String currentLine;
		int currentStationId;
		Station currentStation;
		int destinationStationId;
		Route<Station> route;
		
		try {
			fileScanner = new Scanner(file);
			// Station with id 0 does not exist, but we add it anyway so that indexing of remaining stations starts from 1
			stations.add(null);
			
			// Scan through the file first time to create all stations and add them to the list
			while(fileScanner.hasNextLine()) {
				stations.add(new Station(fileScanner.nextInt(), fileScanner.next()));
				fileScanner.nextLine();
			}
			
			// Recreate the scanner to point at the beginning of the file
			fileScanner.close();
			fileScanner = new Scanner(file);
			
			// Scan through the file second time to create all edges and add them to the list
			while(fileScanner.hasNextLine()) {
				
				// Get the line and its station object
				currentLine = fileScanner.nextLine();
				tokenScanner = new Scanner(currentLine);
				currentStationId = tokenScanner.nextInt();
				tokenScanner.next(); // station name
				currentStation = stations.get(currentStationId);
				
				// Get all pairs of outgoing edges
				while(tokenScanner.hasNext()) {
					String routeName = tokenScanner.next();
					for (int i = 0; i < 2; i++) {
						destinationStationId = tokenScanner.nextInt();
						
						// Create an edge only if it exists and points to a station with greater id to prevent duplicates 
						if(destinationStationId != 0 && destinationStationId >= currentStationId) {
							route = new Route<Station>(routeName);
							route.setNodeIn(currentStation);
							route.setNodeOut(stations.get(destinationStationId));
							routes.add(route);
						}
					}
				}
				tokenScanner.close();
			}
			fileScanner.close();
			
			// Do not forget to remove the non-existing station from the list now that the indeces do not matter anymore
			stations.remove(0);
		} catch (FileNotFoundException e) {
			System.out.println("File not found in parseFile");
		}
	}   
}