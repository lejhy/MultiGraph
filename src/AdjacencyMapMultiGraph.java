import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class AdjacencyMapMultiGraph<N, E extends LabeledEdge<N>> implements MultiGraph<N, E> {
	private Map<N, List<E>> adjacencyMap;

	public AdjacencyMapMultiGraph() {
	   adjacencyMap = new HashMap<N, List<E>>();
   	}
   
	@Override
	public boolean addNode(N node) {
		if (adjacencyMap.containsKey(node) == false) {
			adjacencyMap.put(node, new ArrayList<E>());
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean addEdge(N nodeIn, N nodeOut, E edge) {
		List<E> edgeListIn = adjacencyMap.get(nodeIn);
		List<E> edgeListOut = adjacencyMap.get(nodeOut);
		if (edgeListIn != null && edgeListOut != null && containsEdge(edge) == false) {
			edge.setNodeIn(nodeIn);
			edge.setNodeOut(nodeOut);
			edgeListIn.add(edge);
			edgeListOut.add(edge);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean removeNode(N node) {
		List<E> edgeList = adjacencyMap.get(node);
		if (edgeList != null) {
			for (E edge : edgeList) {
				N adjacentNode = getNode(edge, node);
				adjacencyMap.get(adjacentNode).remove(edge);
			}
			adjacencyMap.remove(node);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean removeEdge(E edge) {
		N nodeIn = edge.getNodeIn();
		N nodeOut = edge.getNodeOut();
		if (nodeIn != null && nodeOut != null) {
			List<E> edgeListIn = adjacencyMap.get(nodeIn);
			List<E> edgeListOut = adjacencyMap.get(nodeOut);
			if (edgeListIn != null && edgeListOut != null) {
				boolean inRemoval = edgeListIn.remove(edge);
				boolean outRemoval = edgeListIn.remove(edge);
				if (inRemoval == true && outRemoval == true) {
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public List getNodes() {
		List<N> nodeList = new ArrayList<N>();
		nodeList.addAll(adjacencyMap.keySet());
		return nodeList;
	}
	
	@Override
	public List getEdges() {
		List<E> edgeList = new ArrayList<E>();
		for (List<E> list : adjacencyMap.values()) {
			edgeList.addAll(list);
		}
		return edgeList;
	}

	@Override
	public List<E> getPath(N source, N destination) {
		Map<N, E> pathSource = new HashMap<N, E>();
		List<N> visited = new ArrayList<N>();
		Queue<N> queue = new ArrayDeque<N>();
		visited.add(source);
		queue.add(source);
		N node;
		while (queue.isEmpty() == false) {
			node = queue.poll();
			for(E edge: adjacencyMap.get(node)) {
				node = getNode(edge, node);
				if (visited.contains(node) == false) {
					visited.add(node);
					pathSource.put(node, edge);
					queue.add(node);
				}
			}
		}
		List<E> edgeList= new ArrayList<E>();
		node = destination;
		while (node != source) {
			E edge = pathSource.get(node);
			edgeList.add(edge);
			node = getNode(edge, node);
		}
		return edgeList;
	}
	
	private boolean containsEdge(E edge) {
		for (List<E> edgeList : adjacencyMap.values()) {
			if (edgeList.contains(edge)) {
				return true;
			}
		}
		return false;
	}
	
	// Returns a node joined to the a node by the a edge.
	private N getNode(E edge, N node) {
		N nodeIn = edge.getNodeIn();
		if (nodeIn != node) {
			return nodeIn;
		} else {
			return edge.getNodeOut();
		}
	}
	
}