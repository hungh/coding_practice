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
	protected Map<Integer, Object> unProcessedMap = new HashMap<Integer, Object>();

	public static void main(String[] args) {
		RemoveV2Graph g = new RemoveV2Graph(false);
		g.read_graph("/remove_v2_graph.txt"); //   remove_v2_graph shortest_path2
		g.init();
		g.print_graph();
		g.remove_vdegree2(1);

		Common.log("After removing...\n");
		g.print_graph();
	}

	public void init() {
		this.populateVertexDegrees();
	}

	public RemoveV2Graph (boolean directed) {
		super(directed);		
	} 

	public void remove_vdegree2(int start) {
		LinkedList<Integer> q = new LinkedList<Integer>();
		EdgeNode p; int[] ns = new int[2];
		int k, i;
		for(i = 1; i <= nvertices; i++){		
			q.push(i);	
		}

		while(! q.isEmpty()){			
			i = q.pop();
			k = 0;
			Common._log(".");
			if(degree[i] == 2){
				p = edges [i];
				while(p != null) {
					ns[k++] = p.y;
					p = p.next;				
				}
				remove_edge(ns[0], i, directed);
				remove_edge (i, ns[1], directed);

				if( !insert_edge_b(ns[0], ns[1]) ) {
					degree[ns[0]]--;
					degree[ns[1]]--;
					q.push(ns[0]); // this will create dups in the queue, but it is okay for now
					q.push(ns[1]);
				}
				edges [i] = null;
				Common.log("\t\tremoving " +i);
				degree[i] = 0;
			}
		}
	}	

	// returns false if the edge (x,y) already exists
	protected boolean insert_edge_b(int x, int y){
		EdgeNode p = edges[x];
		while(p != null) {
			if(p.y == y) return false;
			p = p.next;			
		}
		Common.log("\t\t -- adding a new edge " + x +  " " + y);
		insert_edge(x, y, directed);
		return true;
	}

}