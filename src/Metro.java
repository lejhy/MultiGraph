import java.util.List;

public class Metro {
	
	private MultiGraph<Station, Route<Station>> graph;
	
	
	public Metro() {
		//create multiGraph
		graph = new AdjacencyMapMultiGraph<>();
	}
	
	public void initialise() {
	}
	
	public List<Route<Station>> findPath(String startName, String endName) {
		//Takes in the name of two stations and returns the fastest route between them in 
		//the form of a list of individual routes
		Station start = getStation(startName);
		Station end = getStation(endName);
		if (start != null && end != null) {
			return graph.getPath(start, end);
		} else {
			return null;
		}
	}

	public Station getStation (String name) {
		//Takes the name of a station and returns it as a Sation type
		for (Station station : graph.getNodes()) {
			if (station.getName().equals(name)) {
				return station;
			}
		}		
		return null;
	}
   
  }

