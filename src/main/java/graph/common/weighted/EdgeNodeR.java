package graph.common.weighted;

import graph.common.EdgeNode;

public class EdgeNodeR extends EdgeNode {
	public EdgeNodeR(){}
	public EdgeNodeR(int v) { 
		this.y = v;  // v:  neighboring vertex
		this.weight = 0 ; // the is the capacity of the edge
	 } 
	
	public int flow; // flow through edge
	public int residual; // residual capacity of edge
	public EdgeNodeR next; // next edge in list
}
