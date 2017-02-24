/*
5-20. [5] Given an undirected graph G with n vertices and m edges, and an integer k,
give an O(m + n) algorithm that finds the maximum induced subgraph H of G
such that each vertex in H has degree ≥ k, or prove that no such graph exists. An
induced subgraph F = (U,R) of a graph G = (V,E) is a subset of U of the vertices
V of G, and all edges R of G such that both vertices of each edge are in U.
*/

package graph.analysys;

import java.util.*;
import ds.Common;
import graph.common.*;

public class InducedGraph extends GraphAL {
	protected int k = 2; // default
	protected Map<Integer, Object> delMap = new HashMap<Integer, Object>();

	public static void main(String[] args){
		InducedGraph g = new InducedGraph(false, 3); 
		g.read_graph("/induced_graph.txt");// k = 2, returns 1 2 4 5 7; k =3 : there is no such graph
		g.bfs(1);
		g.print_graph();
		g.printInducedVertices();
	}

	public InducedGraph(boolean directed, int k) {
		this(directed);
		this.k = k;
	}

	public InducedGraph (boolean directed) {
		super(directed);
	}

	public void scanInput(Scanner sc, boolean showLogs){
		super.scanInput(sc, showLogs);
		// O(V + E)
		this.populateVertexDegrees();
	}

	@Override
	public void process_edge(int x, int y){
		if(degree[x] < k) {
			delMap.put(x, null);
			degree[y]--;
		} 

		if (degree[y] < k) {
			delMap.put(y, null);
			degree[x]--;
		}

		if( delMap.containsKey(y)) { 
			degree[x]--;
		}
	}

	public void printInducedVertices() {
		if(delMap.size() == nvertices) {
			Common.log("Error: There is no such graph for k=" + k);
		} else{
			for(int i = 1; i <= nvertices; i++) 
				if(! delMap.containsKey(i)) Common._log(" " + i);
		}
		Common.log("");
	}
	
}