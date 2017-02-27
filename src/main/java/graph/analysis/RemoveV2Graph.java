/*
5-22. Design a linear-time algorithm to eliminate each vertex v of degree 2 from a graph by replacing edges (u,v) and (v,w)
 by an edge (u,w). We also seek to eliminate multiple copies of edges by replacing them with a single edge. 
 Note that removing multiple copies of an edge may create a new vertex of degree 2, which has to be removed, 
 and that removing a vertex of degree 2 may create multiple edges, which also must be removed.
*/

package graph.analysis;

import java.util.*;
import ds.Common;
import graph.common.*;

public class RemoveV2Graph  extends GraphAL {

	public static void main(String[] args) {
		RemoveV2Graph g = new RemoveV2Graph(false);
		g.read_graph("/shortest_path2.txt"); //remove_v2_graph
		g.init();

		Common.log("Before removing...");		
		g.print_graph();

		g.bfs(1);

		Common.log("After removing...");
		g.print_graph();
	}

	public void init() {
		this.populateVertexDegrees();
	}

	public RemoveV2Graph (boolean directed) {
		super(directed);		
	}

	@Override
	public void process_edge(int x, int y) {
		EdgeNode p; int s = -1;
		if(degree[x] == 2) {
			p = edges [x];
			while(p != null) {
				Common._log("..");
				if(p.y != y)
					s = p.y;
				p = p.next;				
			}
			if(s > 0){
				edges [x] = null;
				remove_edge(s, x);
				remove_edge (y, x);

				insert_edge(s, y, directed);

				processed[s] = false;	
			} else {
				throw new IllegalArgumentException("Invalid Graph for x=" + x);
			}
		}
	}

	protected void remove_edge(int x, int y){
		EdgeNode prev, curr;
		curr = edges[x];
		prev = curr;
		while(curr != null) {
			if(curr.y == y)
				prev.next = curr.next;
			prev = curr;
			curr = curr.next;
			Common._log(".");
		}
		Common.log("removing edge " + x +  ";" + y);
	}


}