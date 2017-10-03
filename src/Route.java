public class Route<N> implements LabeledEdge<N>{
	
	private String label;
	private N nodeIn;
	private N nodeOut;
	
	public Route(String label) {
		this.label = label;
	}
	
	public String getColor() {
		return getLabel();
	}

	@Override
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String string) {
		label = string;
	}

	@Override
	public N getNodeIn() {
		return nodeIn;
	}

	@Override
	public void setNodeIn(N node) {
		nodeIn = node;		
	}

	@Override
	public N getNodeOut() {
		return nodeOut;
	}

	@Override
	public void setNodeOut(N node) {
		nodeOut = node;		
	}
   
   }
