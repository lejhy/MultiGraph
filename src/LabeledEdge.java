
public interface LabeledEdge <N> {
	
	public String getLabel();
	
	public N getNodeIn();
	
	public void setNodeIn(N node);
	
	public N getNodeOut();
	
	public void setNodeOut(N node);
	
}
