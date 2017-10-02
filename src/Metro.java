import java.util.List;

public class Metro {
	
	private MultiGraph<Station, Route<Station>> graph;
	
	
	public Metro() {
		graph = new AdjacencyMapMultiGraph<>();
	}
	
	public void initialise() {
	}
	
	public List<Route<Station>> findPath(String startName, String endName) {
		Station start = getStation(startName);
		Station end = getStation(endName);
		if (start != null && end != null) {
			return graph.getPath(start, end);
		} else {
			return null;
		}
	}

	public Station getStation (String name) {
		for (Station station : graph.getNodes()) {
			if (station.getName().equals(name)) {
				return station;
			}
		}		
		return null;
	}
   
  }

