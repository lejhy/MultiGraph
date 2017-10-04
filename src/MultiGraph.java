import java.util.List;

public interface MultiGraph <N, E extends LabeledEdge<N>> {
   public boolean addNode(N node);
   
   public boolean addEdge(E edge);
   
   public boolean addEdge(N node1, N node2, E edge);
   
   public boolean removeNode(N node);
   
   public boolean removeEdge(E edge);
   
   public List<N> getNodes();
   
   public List<E> getEdges();
   
   public List<E> getPath(N node1, N node2);
   
   }
