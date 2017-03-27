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
		g.read_graph("/kruskal_mst.txt");
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
		EdgePair[] e = to_edge_array();		
		Arrays.sort(e, 1, nvertices + 1);
		
		GraphAL mst = getCandidateMST();
		int direction;
		for(int i = 1; i <= nvertices; i++){
			if(! SetUnion.same_component(s, e[i].x, e[i].y)){
				direction = SetUnion.union_sets(s, e[i].x, e[i].y);
				if(direction > 0) 
					mst.insert_edge (e[i].x, e[i].y, e[i].w, directed);
				else if (direction < 0)
					mst.insert_edge (e[i].y, e[i].x, e[i].w, directed);
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

	public EdgePair[] to_edge_array(){
		EdgeNode p;
		EdgePair[] pairs = new EdgePair[nvertices + 1];
		for(int i = 1; i <= nvertices; i++) {
			p = edges[i];
			while(p != null){
				pairs[i] = new EdgePair(i, p.y, p.weight);
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

	// draw a MST by creating a new graph
	public GraphAL getCandidateMST() {
		GraphAL mst = new GraphAL (DIRECTED);
		mst.setWeighted(WEIGHTED);
		mst.setNVertices(nvertices);
		return mst;
	}
}