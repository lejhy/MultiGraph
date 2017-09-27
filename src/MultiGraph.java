import java.util.List;

public interface MultiGraph <N, E> {
   public boolean addNode(N node);
   
   public boolean addEdge(N node1, N node2, E edge);
   
   public boolean removeNode(N node);
   
   public boolean removeEdge(E edge);
   
   public List<N> getNodes();
   
   public List<E> getEdges();
   
   public boolean containsNode(N node);
   
   public boolean containsEdge(E edge);
   
   }
