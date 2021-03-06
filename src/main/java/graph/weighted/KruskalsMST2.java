/* Kruskal's Minimum Spanning Implementation in Java (C code from Skiena book) */

package graph.weighted;

import java.util.*;
import ds.Common;
import graph.common.*;
import graph.common.weighted.*;

/*
Kruskal using Path compression Union-Find Set with link by rank
*/
public class KruskalsMST2 extends GraphAL {
	private static final boolean DIRECTED = false;
	private static final boolean WEIGHTED = true;	
	protected List<EdgePair> e;
	public static void main(String[] args){
		KruskalsMST2 g = new KruskalsMST2(WEIGHTED, DIRECTED);
		g.read_graph("/figure6_3.txt");
		g.print_graph();
		Common.log("Minimum Spanning Tree Is: \n");
		g.kruskal().print_graph();
	}

	public KruskalsMST2(boolean weighted, boolean directed){
		super(directed);
		this.weighted = weighted;
	}

	public GraphAL kruskal(){
		SetUnionPC s = new SetUnionPC();
		init_set_union(s, nvertices);
		e = to_edge_array();		
		Collections.sort(e);
		
		GraphAL mst = GraphUtils.getNewEmptyGraph(nvertices, WEIGHTED, DIRECTED);
		int direction;
		for(EdgePair ep: e){
			if(! SetUnionPC.same_component(s, ep.x, ep.y)){
				direction = SetUnionPC.union_sets(s, ep.x, ep.y);
				ep.mst = true;
				if(direction > 0) 
					mst.insert_edge (ep.x, ep.y, ep.w, directed);
				else if (direction < 0)
					mst.insert_edge (ep.y, ep.x, ep.w, directed);
			}
		}
		Common.log("\n -- Set Union (using Path Compression): \n" + s);
		return mst;
	}

	// n: the number of elements in the set su
	public void init_set_union(SetUnionPC s, int n) {
		for(int i = 1; i <= n; i++) s.p[i] = i;		
		s.n = n;	
	}

	public List<EdgePair> to_edge_array(){
		EdgeNode p;
		List<EdgePair> pairs = new ArrayList<EdgePair>();
		Set<EdgePair> uniqPairs = new HashSet<EdgePair>();

		for(int i = 1; i <= nvertices; i++) {
			p = edges[i];
			while(p != null){
				uniqPairs.add(new EdgePair(i, p.y, p.weight));
				p = p.next;
			}
		}
		for(EdgePair up: uniqPairs) pairs.add(up);
		return pairs;
	}

	public KruskalsMST2(boolean directed){
		super(directed);
	}

	public  void printEdgePairs(EdgePair[] es){
		if(es != null){
			for(int i = 1; i <= nvertices; i++) 
				Common.log("x = " + es[i].x + "; y = " + es[i].y + "; w = " + es[i].w);
		}
	}
}