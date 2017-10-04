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

	public String findPath(String startName, String endName) {
		// Takes in the name of two stations and returns the fastest route
		// between them in
		// the form of a list of individual routes
		Station start = getStation(startName);
		Station end = getStation(endName);
		if (start != null && end != null) {
			String path = "";
			path.concat(startName);
			Route<Station> lastRoute = null;
			String lastRouteLabel = "";
			for (Route<Station> route : graph.getPath(start, end)) {
				if (route.getLabel().equals(lastRouteLabel) != true) {
					path.concat(lastRouteLabel);
					lastRouteLabel = route.getLabel();
					
					if (lastRoute.getNodeIn().equals(route.getNodeOut())) {
						path.concat(route.getNodeOut().getName());
					}
					
					if (lastRoute.getNodeOut().equals(route.getNodeIn())) {
						path.concat(route.getNodeIn().getName());
					}
				}
				
				lastRoute = route;
			}
			path.concat(endName);
			return path;
		} else {
			return null;
		}
	}

	public Station getStation(String name) {
		// Takes the name of a station and returns it as a Station type
		for (Station station : graph.getNodes()) {
			if (station.getName().equals(name)) {
				return station;
			}
		}
		return null;
	}
}
