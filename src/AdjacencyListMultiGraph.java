import java.util.List;

public class AdjacencyListMultiGraph<N, E> implements MultiGraph<N, E> {
   private List <List<E>> edges;
   private List <N> nodes;

	@Override
	public boolean addNode(N node) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean addEdge(N node1, N node2, E edge) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean removeNode(N node) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean removeEdge(E edge) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public List getNodes() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List getEdges() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean containsNode(N node) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean containsEdge(E edge) {
		// TODO Auto-generated method stub
		return false;
	}
}
