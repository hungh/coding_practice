/* Kruskal's Minimum Spanning Implementation in Java (C code from Skiena book) */

package graph.weighted;

import java.util.*;
import ds.Common;
import graph.common.*;
import graph.common.weighted.*;


public class KruskalsMST extends GraphAL {
	public static void main(String[] args){
		KruskalsMST g = new KruskalsMST(true, false);
		g.read_graph("/kruskal_mst.txt");
		g.print_graph();
		g.kruskal();
	}

	public KruskalsMST(boolean weighted, boolean directed){
		super(directed);
		this.weighted = weighted;
	}

	public void kruskal(){
		SetUnion s = new SetUnion();
		init_set_union(s, nvertices);
		EdgePair[] e = to_edge_array();
		printEdgePairs(e);
		Arrays.sort(e, 1, nvertices + 1);
		Common.log("-- After sorted --");
		printEdgePairs(e);

		for(int i = 1; i <= nvertices; i++){
			if(! SetUnion.same_component(s, e[i].x, e[i].y)){
				SetUnion.union_sets(s, e[i].x, e[i].y);
			}
		}
		s.printSet();

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


}