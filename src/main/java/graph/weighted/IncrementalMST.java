package graph.weighted;

import ds.Common;
import graph.common.*;

/*
Supposed that you were given an MST from a graph G = (V, E) and n: number of vertices
and m : the number of edges
Provide an efficient algorithm to G+e where e = (u,v) provided u,v belonging to E
Expected running time O(n)

Analysis:
--------
1. Find a path from u to v (there is such only 1 path from u to v ) in the mst graph.
2. Get the max edge mb that is neighboring to either u or v on this path.
3. If the max mb > w(u,v), then add edge u->v and remove mb edge. Else do nothing to the mst.

Complexity:
-----------
Step 1. takes O(n)
Steps 2 and 3 take C constant time
*/

public class IncrementalMST extends PrimsMST {
	private static final boolean DIRECTED = false;

	public static void main(String[] args){

		IncrementalMST g = new IncrementalMST(true, DIRECTED);
		g.read_graph("/remove1.txt");
		g.print_graph();
		GraphAL mst = g.prim(1);

		Common.log("original mst =>");
		mst.print_graph();

		int u = 1, v = 6, w = 4;
		g.updateMST(u, v, w, mst);

		Common.log("modified mst =>");
		mst.print_graph();

	}

	public IncrementalMST(boolean weighted, boolean directed) {
		super(weighted, directed);
	}

	// w: weight of u -> v
	public void updateMST(int u, int v, int w, GraphAL mst){
		mst.bfs(u);
		int[] mst_parent = mst.getParents();		
		int e = v;
		int r = mst_parent[v];
		
		EdgeNode ev = find_edge (mst_parent[v], v);
		EdgeNode maxEdge = ev;

		while(mst_parent[e] != u) e = mst_parent[e];

		EdgeNode ue = find_edge(u, e);
		if(ev.weight < ue.weight) {
			r = u;
			maxEdge = ue;
		}
		Common.log("maxEdge=" + maxEdge.weight);
		if(maxEdge.weight > w) {
			mst.remove_edge(r, maxEdge.y, directed);
		}		
	}
}