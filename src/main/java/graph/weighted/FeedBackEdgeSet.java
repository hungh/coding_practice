/*
6-10. [4] Let G = (V,E) be an undirected graph. A set F ⊆ E of edges is called a
feedback-edge set if every cycle of G has at least one edge in F.

	(a) Suppose that G is unweighted. Design an efficient algorithm to find a
	 	minimum-size feedback-edge set.

	(b) Suppose that G is a weighted undirected graph with positive edge weights.
	    Design an efficient algorithm to find a minimum-weight feedback-edge set.

Analysis:
---------
The above problem is the same as:
(*) Input Description: A (directed) graph G=(V,E).
(*) Problem: What is the smallest set of edges E' or vertices V' whose deletion leaves an acyclic graph?
Solution: 
a. F is the compliment graph of G's MST
b. F is the complement graph of G's maximum weighted spanning tree (by converting all E of G weights to negatives)
*/

package graph.weighted;

import java.util.*;
import ds.Common;
import graph.common.*;
import graph.common.weighted.*;

public class FeedBackEdgeSet extends KruskalsMST {
	private static final boolean DIRECTED = false;
	private static final boolean WEIGHTED = true;	

	public static void main(String[] args){
		FeedBackEdgeSet g = new FeedBackEdgeSet();
		g.read_graph("/figure6_3.txt");
		g.print_graph();
		Common.log(" Minimum size feedback-edge set: [here we make it a graph to be easily visualized]");
		g.getMinSizeFES().print_graph();
	}

	public FeedBackEdgeSet(){
		super(WEIGHTED, DIRECTED);
	}


	public GraphAL getMinSizeFES() {
		kruskal();
		GraphAL minFES = GraphUtils.getNewEmptyGraph(nvertices, WEIGHTED, DIRECTED);
		// e: all edge pairs
		for(EdgePair ep: e) {
			if(!ep.mst) minFES.insert_edge (ep.x, ep.y, ep.w, directed);
		}
		return minFES;
	}

}
