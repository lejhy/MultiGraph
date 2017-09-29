import java.util.List;

public class Metro {
	
	private MultiGraph<Station, Route<Station>> graph;
	
	
	public void initialise() {
		graph = new AdjacencyMapMultiGraph<>();
		
	}
	
	public void findPath(String start, String end, Display view) {
		Station first = graph.getNode(start);
		Station last = graph.getNode(end);
		List<Route<Station>> path = graph.getPath(first, last);
		
		for(Route<Station> r : path) {
			view.output("Go from " + r.getNodeIn().getName() + " on the " + r.getLabel() + " to " + r.getNodeOut().getName()); 
		}
	}

	public boolean stationCheck(String name) {
		boolean isStation = graph.containsNode(name);
		return isStation;		
	}
   
   }
