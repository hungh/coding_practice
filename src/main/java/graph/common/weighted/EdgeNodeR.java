package graph.common.weighted;

import graph.common.EdgeNode;

public class EdgeNodeR extends EdgeNode {
	public EdgeNodeR(){}
	public EdgeNodeR(int v) { 
		this.y = v;  // v:  neighboring vertex		
		this.weight = 0;
	 } 
	
	public int flow; // flow through edge
	public int residual; // residual capacity of edge	
}
