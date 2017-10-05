import java.net.PasswordAuthentication;
import java.util.ArrayList;

public class Metro {

	private MultiGraph<Station, Route<Station>> graph;

	public Metro() {
		// create multiGraph
		graph = new AdjacencyMapMultiGraph<>();
	}

	public void populate(String fileName) {
		Parser parser = new Parser(fileName);
		for (Station station : parser.getStations()) {
			graph.addNode(station);
//			System.out.println(station.getID() + ". " + station.getName()+ " added");
		}
		for (Route<Station> route : parser.getRoutes()) {
			graph.addEdge(route);
		}
	}

	public String findPath(String startName, String endName, int choiceStart, int choiceEnd) {
		// Takes in the name of two stations and returns the fastest route
		// between them in
		// the form of a list of individual routes
		Station start = getStation(choiceStart);
		Station end = getStation(choiceEnd);
		if (start != null && end != null) {
			String path = startName;
			Route<Station> lastRoute = null;
			for (Route<Station> route : graph.getPath(start, end)) {
				if (lastRoute == null) {
					path = path.concat(" where you board ");
					path = path.concat(route.getLabel());
					path = path.concat(" and set off towards ");
				} else if (route.getLabel().equals(lastRoute.getLabel()) == false) {
					
					if (lastRoute.getNodeIn().equals(route.getNodeOut())) {
						path = path.concat(route.getNodeOut().getName());
					}

					if (lastRoute.getNodeOut().equals(route.getNodeIn())) {
						path = path.concat(route.getNodeIn().getName());
					}
					
					if (lastRoute.getNodeIn().equals(route.getNodeIn())) {
						path = path.concat(route.getNodeIn().getName());
					}
					
					if (lastRoute.getNodeOut().equals(route.getNodeOut())) {
						path = path.concat(route.getNodeOut().getName());
					}
					
					path = path.concat(" where you switch to ");
					path = path.concat(route.getLabel());
					path = path.concat(" and continue towards ");
				}
				lastRoute = route;
			}
			path = path.concat(endName);
			return path;
		} else {
			return null;
		}
	}


	public ArrayList<Integer> getStationsOfSameName(String name) {
		//Takes the name of a station and RETURNS it as a Station type
		ArrayList<Integer> stations = new ArrayList<>();
		for (Station station : graph.getNodes()) {
			if (station.getName().equals(name)) {
				stations.add(station.getID());
			}
		}
		return stations;
	}


	private Station getStation(int id) {
		for (Station station : graph.getNodes()) {
			if (station.getID() == id) {
				return station;
			}
		}

		return null;
	}

	public ArrayList<String> getNearbyStations(int id) {
		ArrayList<String> nearbyStations = new ArrayList<>();
		for (Station station : graph.getAdjacentNodes(getStation(id))) {
			nearbyStations.add(station.getName());
		}
		return nearbyStations;
	}

}



