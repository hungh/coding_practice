/* Kruskal's Minimum Spanning Implementation in Java (C code from Skiena book) */

package graph.weighted;

import java.util.*;
import ds.Common;
import graph.common.*;
import graph.common.weighted.*;


public class KruskalsMST extends GraphAL {
	private static final boolean DIRECTED = false;
	private static final boolean WEIGHTED = true;	
	public static void main(String[] args){
		KruskalsMST g = new KruskalsMST(WEIGHTED, DIRECTED);
		g.read_graph("/figure6_3.txt");///kruskal_mst.txt");
		g.print_graph();
		Common.log("Minimum Spanning Tree Is: \n");
		g.kruskal().print_graph();
	}

	public KruskalsMST(boolean weighted, boolean directed){
		super(directed);
		this.weighted = weighted;
	}

	public GraphAL kruskal(){
		SetUnion s = new SetUnion();
		init_set_union(s, nvertices);
		List<EdgePair> e = to_edge_array();		
		Collections.sort(e);
		
		GraphAL mst = GraphUtils.getNewEmptyGraph(nvertices, WEIGHTED, DIRECTED);
		int direction;
		for(EdgePair ep: e){
			if(! SetUnion.same_component(s, ep.x, ep.y)){
				direction = SetUnion.union_sets(s, ep.x, ep.y);
				ep.mst = true;
				if(direction > 0) 
					mst.insert_edge (ep.x, ep.y, ep.w, directed);
				else if (direction < 0)
					mst.insert_edge (ep.y, ep.x, ep.w, directed);
			}
		}
		return mst;
	}

	// n: the number of elements in the set su
	public void init_set_union(SetUnion s, int n) {
		for(int i = 1; i <= n; i++) {
			s.p[i] = i;
			s.size[i] = 1;
		}
		s.n = n;
	}

	public List<EdgePair> to_edge_array(){
		EdgeNode p;
		List<EdgePair> pairs = new ArrayList<EdgePair>();
		for(int i = 1; i <= nvertices; i++) {
			p = edges[i];
			while(p != null){
				pairs.add(new EdgePair(i, p.y, p.weight));
				p = p.next;
			}
		}
		return pairs;
	}

	public KruskalsMST(boolean directed){
		super(directed);
	}

	public  void printEdgePairs(EdgePair[] es){
		if(es != null){
			for(int i = 1; i <= nvertices; i++) 
				Common.log("x = " + es[i].x + "; y = " + es[i].y + "; w = " + es[i].w);
		}
	}
}