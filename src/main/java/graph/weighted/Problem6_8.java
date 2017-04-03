package graph.weighted;

/*
6-8. [5] Devise and analyze an algorithm that takes a weighted graph G and finds the
smallest change in the cost to a non-MST edge that would cause a change in the
minimum spanning tree of G. Your algorithm must be correct and run in polynomial
time.

*Analysis:
----------
  Observe that non-MST edge and this edge will always create a circle in MST tree.
  Otherwise it would have been part of the MST tree. (TODO: need proof ??).
  What we need to do is compare non-MST edge with 2 neighboring edges
  1. scan all edges that has mst indicator = false;
     compare its weight with 2 neighboring edgs and set the min_diff and save min non-mst 
   How do we pick the edge in mst-Tree that is closest to me?
  2. run 1 DFS on the MST.
  3. For each non-MST edge (u,v) get the min diff by find a path from u->v on MST 
    3.a To know the direction, we need to look at the exit_time

*/

import java.util.*;
import ds.Common;
import graph.common.*;
import graph.common.weighted.*;


public class Problem6_8 extends KruskalsMST {	
	public static void main(String[] args){
		Problem6_8 g = new Problem6_8(true, false);
		g.read_graph("/figure6_3.txt");
		g.print_graph();

		EdgePair minDiffEdge =  g.getBestNonMST();
		Common.log("minDiffEdge = " + minDiffEdge);

	}

	public Problem6_8 (boolean weighted, boolean directed) {
		super(weighted, directed);
	}

	public EdgePair getBestNonMST () {
		GraphAL mstTree = kruskal();
		Common.log("-> MST: \n");
		mstTree.print_graph();

		// Adjust edges: (u,v): true is always appears before false
		Map<Integer, Integer> cache = new HashMap<Integer, Integer> () ;
		Integer r;
		for(EdgePair ep: e) {
			r = cache.get(ep.x);
			if(r != null && ep.y == r) ep.mst = true; 				
			if(ep.mst) cache.put(ep.y, ep.x);
		}
		
		//
		EdgePair retEdge = null;

		// by doing this, we get the direction of an edge (or path) by looking at the exit_time[v]
		mstTree.dfs(1);
		// the exit_time[v] of the dfs
		int[] etimes = mstTree.getExitTimes();

		int u, v;
		int nu, nv; // neighbors
		int min_u = -1, min_v = -1, min_diff = Integer.MAX_VALUE;
		int curr_diff; // the current diff while traversing

		for(EdgePair ep: e) { // e: all the edge pairs
			if(!ep.mst) {
				u = ep.x;
				v = ep.y;

				if(etimes [u] > etimes[v]) // u -> v
					curr_diff = getMinDiff (u, v, ep.w, mstTree);
				else 
					curr_diff = getMinDiff (v, u, ep.w, mstTree);

				if(curr_diff < min_diff) {
					min_diff = curr_diff;
					min_u = u; // does not matter which direction
					min_v = v; 
				}
			}
		}
		if( (min_u > 0) && (min_v > 0)) 
			retEdge = new EdgePair (min_u, min_v, min_diff);

		return retEdge;
	}

	// direction from u->v
	// nonMstW: the weight of the non-MST edge
	private int getMinDiff (int u, int v, int nonMstW, GraphAL mst) {
		int[] _parents = mst.getParents();
		// we just need to look for edge (u, u1) and (v1, v), neighboring edges of u and v that are on the path from u -> v 
		// belonging to MST
		int _v = v; // temporary v
		int v1 = _parents[v];
		while (_parents [_v] != u) _v = _parents[_v];
		int u1 = _v;

		EdgeNode uu1 = find_edge(u, u1);
		EdgeNode v1v = find_edge(v1, v);
		return Math.min (nonMstW - uu1.weight, nonMstW - v1v.weight);		
	}
}
