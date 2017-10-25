import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class AdjacencyMapMultiGraph<N, E extends LabeledEdge<N>> implements MultiGraph<N, E> {
	//This implementation of MultiGraph contains types N and E
	//N represents a Station using generics
	//E represents the type LabeledEdge (The interface for the station class)
	
	private Map<N, List<E>> adjacencyMap;

	public AdjacencyMapMultiGraph() {
		//creates the MultiGraph in the form of a HashMap
	   adjacencyMap = new HashMap<N, List<E>>();
   	}
   
	@Override
	public boolean addNode(N node) {
		//Takes in a node and adds it to the map
		if (adjacencyMap.containsKey(node) == false) {
			adjacencyMap.put(node, new ArrayList<E>());
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean addEdge(E edge) {
		//Take in an edge and adds it to the graph

		List<E> edgeListIn = adjacencyMap.get(edge.getNodeIn());
		List<E> edgeListOut = adjacencyMap.get(edge.getNodeOut());
		if (edgeListIn != null && edgeListOut != null && containsEdge(edge) == false) {
			//If the nodes are found in the map and the edge to be added does not already exist
			edgeListIn.add(edge);
			edgeListOut.add(edge);
            //asertions
            assert this.containsEdge(edge) : edge.getLabel();
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean removeNode(N node) {
		//Method to remove a node from the map
		List<E> edgeList = adjacencyMap.get(node);
		if (edgeList != null) {
			//Checks if there are edges associated with the node to be deleted
			for (E edge : edgeList) {
				N adjacentNode = getNode(edge, node); //Finds the next node
				adjacencyMap.get(adjacentNode).remove(edge); //Removes the edge from the adjacent node to the deleted node
			}
			adjacencyMap.remove(node);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean removeEdge(E edge) {
		//Removes an edge from the map and returns true is successful
		N nodeIn = edge.getNodeIn();
		N nodeOut = edge.getNodeOut();
		if (nodeIn != null && nodeOut != null) {
			//If the nodes on either side of the edge have been identified
			List<E> edgeListIn = adjacencyMap.get(nodeIn);
			List<E> edgeListOut = adjacencyMap.get(nodeOut);
			if (edgeListIn != null && edgeListOut != null) {
				//removes edge from each node adjacent to it
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
	public List<N> getNodes() {
		//Returns a list of all nodes in the MultiGraph
		List<N> nodeList = new ArrayList<N>();
		nodeList.addAll(adjacencyMap.keySet());
		return nodeList;
	}
	
	@Override
	public List<E> getEdges() {
		//Returns a list of all edges in the MultiGraph

        List<E> edgeList = new ArrayList<E>();
		for (List<E> list : adjacencyMap.values()) {
			edgeList.addAll(list);
		}
		return edgeList;
	}

	@Override
	public List<E> getPath(N source, N destination) {
		//Returns a path from one node to another in the form of a list of edges
        assert adjacencyMap.containsKey(source) && adjacencyMap.containsKey(destination);

		//checks multigraph has not been altered
        class DataCopy {
            private HashMap mapCopy;

            DataCopy(){mapCopy = new HashMap();
            mapCopy.putAll(adjacencyMap);
            }
            boolean isConsistent(){return mapCopy.equals(adjacencyMap);}
            HashMap getMapCopy(){return mapCopy;}
            }
		DataCopy copy = null;
		assert((copy = new DataCopy())!=null);
        //


		Map<N, E> pathSource = new HashMap<N, E>();
		List<N> visited = new ArrayList<N>();
		Queue<N> queue = new ArrayDeque<N>();
		visited.add(source);
		queue.add(source);
		N node;
		N neighbourNode;
		List<E> sortedEdges;
		while (queue.isEmpty() == false) {
			//Continue searching until all connected nodes have been visited
			node = queue.poll();
			sortedEdges = new ArrayList<E>();
			for (E edge : adjacencyMap.get(node)) {
				//Get an appropriately sorted list of all edges connected to current node
				if (pathSource.containsKey(node) && edge.getLabel().equals(pathSource.get(node).getLabel())) {
					//Those edges whose label matches the label of the source edge of the current node go at the beginning
					sortedEdges.add(0, edge);
				} else {
					//Those edges whose label does not match the label of the source edge of the current node go at the end
					sortedEdges.add(edge);
				}
			}
			for(E edge: sortedEdges) {
				//Walk the list of sorted edges looking for nodes that have not been visited yet
				neighbourNode = getNode(edge, node);
				if (visited.contains(neighbourNode) == false) {
					//For unvisited nodes set a source edge and add them on the queue
					visited.add(neighbourNode);
					pathSource.put(neighbourNode, edge);
					queue.add(neighbourNode);
				}
			}
		}
		List<E> edgePath= new ArrayList<E>();
		node = destination;
		while (node != source) {
			//Continue starting from the destination until the source node has been reached
			E edge = pathSource.get(node);
			//Add the source edge at the beginning of the list
			edgePath.add(0, edge);
			node = getNode(edge, node);
		}
		assert copy.isConsistent():"Original size: "+adjacencyMap.size() +" Current size: "+copy.getMapCopy().size();
		return edgePath;
	}
	
	private boolean containsEdge(E edge) {
		//Returns true if a specified edge already exists in the multigraph
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

	@Override
	public List<N> getAdjacentNodes(N node){
		//Takes in a node and returns a list of all adjacent nodes
		List<N> adjacentNodes = new ArrayList<N>();
		List<E> connectedEdges = adjacencyMap.get(node);
		for(E currentEdge : connectedEdges){
			if(currentEdge.getNodeOut() != node)
				adjacentNodes.add(currentEdge.getNodeOut());
			else
				adjacentNodes.add(currentEdge.getNodeIn());
		}
		return adjacentNodes;
	}
}
