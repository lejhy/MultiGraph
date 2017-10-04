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
				stations.add(new Station(scanner.nextInt(), scanner.next()));
				scanner.nextLine();
			}
			scanner.close();
			scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				String currentLine = scanner.nextLine();
				Scanner tokenScanner = new Scanner(currentLine);

				Station currentStationId = stations.get(tokenScanner.nextInt());
				System.out.println(currentStationId.getID());
				tokenScanner.next();
				while(tokenScanner.hasNext()) {
					String routeName = tokenScanner.next();
					Route<Station> route;
					int stationId = tokenScanner.nextInt();
					if(stationId!=0) {
						route = new Route<Station>(routeName);
						route.setNodeIn(currentStationId);
						route.setNodeOut(stations.get(stationId));
						routes.add(route);
					}

					stationId = tokenScanner.nextInt();
					if(stationId!=0) {
						route = new Route<Station>(routeName);
						route.setNodeIn(currentStationId);
						route.setNodeOut(stations.get(stationId));
						routes.add(route);
					}
				}
				tokenScanner.close();
			}
			scanner.close();
			stations.remove(0);
		} catch (FileNotFoundException e) {
			System.out.println("File not found in parseFile");
		}
	}   
}