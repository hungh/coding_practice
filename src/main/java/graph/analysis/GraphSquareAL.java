/*
5-12. [5] The square of a directed graph G = (V,E) is the graph G2 = (V,E^2) such that
(u,w) ∈ E^2 iff there exists v ∈ V such that (u, v) ∈ E and (v,w) ∈ E; i.e., there is
a path of exactly two edges from u to w.
Give efficient algorithms for both adjacency lists and matrices.

*/

package graph.analysis;

import java.util.*;

import ds.Common;
import graph.common.EdgeNode;
import graph.common.GraphAL;

public class GraphSquareAL extends GraphAL implements GraphSquare {

	protected EdgeNode[] edges_origin = new EdgeNode[GraphAL.MAXV + 1];

	public static void main(String[] args){
		GraphSquareAL g = new GraphSquareAL(true);
		g.read_graph("/graphsquare2.txt");
		Common.log(" ******* G");
		g.print_graph();

		g.dfs(1);

		g.square();

		Common.log("\n ******* G^2");
		g.print_graph();
	}

	public GraphSquareAL(boolean directed) {
		super(directed);
	}

	/* Complexity O(V * E^2). Can we improve this ? */
	@Override
	public void square(){
		backupOrigin();

		EdgeNode t1, t2;
		int u, w; // square nodes
		int v; // adjacency middle node
		for(u = 1; u <= nvertices; u++) {
			t1 = edges_origin [u];
			while(t1 != null){
				v = t1.y;
				t2 = edges_origin[v];
				while(t2 != null){
					insert_edge(u, t2.y, directed);  
					t2 = t2.next;
				}
				t1 = t1.next;
			}
		}
		mergeGraph();
	}

	@Override
	public void backupOrigin(){
		for(int i = 1; i <= nvertices; i++) {
			edges_origin[i] = edges[i];
			edges[i] = null;
		}
	}

	@Override
	public void mergeGraph(){
		Map<Integer, Object> m = new HashMap<Integer, Object>();
		EdgeNode t, newT, prev;
		for(int i = 1; i <= nvertices; i++){
			t = prev = edges[i];       // new graph
			while(t != null) {
				// remove dups
				if(m.containsKey(t.y)){
					prev.next = t.next;
				}else{
					m.put(t.y, null);
					prev = t;						
				}		
				t = t.next;
			}
			t = edges_origin[i]; // the old graph
			while(t != null){
				if(!m.containsKey(t.y)) {
					// insert at head
					newT = new EdgeNode(t.y);
					newT.next = edges[i];
					edges[i] = newT;
				}
				t = t.next;
			}
			m.clear();
		}
	}
}