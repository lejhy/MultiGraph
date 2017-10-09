import java.util.ArrayList;
import java.util.List;

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
		}
		for (Route<Station> route : parser.getRoutes()) {
			graph.addEdge(route);
		}
	}

	public String findPath(int choiceStart, int choiceEnd) {
		// Takes in the IDs of two stations and returns the fastest route between them in the form of a descriptive sentence
		Station start = getStation(choiceStart);
		Station end = getStation(choiceEnd);
		String path = "";
		if (start != null && end != null) {
			path += "Board Metro at ";
			path += start.getName();
			path += " on the ";
			Route<Station> lastRoute = null;
			for (Route<Station> route : graph.getPath(start, end)) {
				
				// Check for first case scenario
				if (lastRoute == null) {
					path += route.getLabel();
					path += " line.\nFollow this line to ";
				} else if (route.getLabel().equals(lastRoute.getLabel()) == false) {
					Station switchStation = getStation(route, lastRoute);
					path += switchStation.getName();
					path += ", where you switch to the ";
					path += route.getLabel();
					path += " line.\nFollow this line to ";
				}
				lastRoute = route;
			}
			path += end.getName();
			return path;
		} else {
			return null;
		}
	}

	public List<Integer> getStationsOfSameName(String name) {
		//Takes the name of a station and returns a list of IDs of stations with same name
		List<Integer> stations = new ArrayList<>();
		for (Station station : graph.getNodes()) {
			if (station.getName().equals(name)) {
				stations.add(station.getID());
			}
		}
		return stations;
	}

	public List<String> getNearbyStations(int id) {
		List<String> nearbyStations = new ArrayList<>();
		for (Station station : graph.getAdjacentNodes(getStation(id))) {
			nearbyStations.add(station.getName());
		}
		return nearbyStations;
	}
	
	private Station getStation(int id) {
		for (Station station : graph.getNodes()) {
			if (station.getID() == id) {
				return station;
			}
		}
		return null;
	}

	private Station getStation(Route<Station> route1, Route<Station> route2) {
		Station route1In = route1.getNodeIn();
		Station route1Out = route1.getNodeOut();
		Station route2In = route2.getNodeIn();
		Station route2Out = route2.getNodeOut();
		
		if (route1In.equals(route2In) || route1In.equals(route2Out)) {
			return route1In;
		} else {
			return route1Out;
		}
	}
}



