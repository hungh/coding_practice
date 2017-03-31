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
*/

import java.util.*;
import ds.Common;
import graph.common.*;
import graph.common.weighted.*;


public class Problem6_8 extends KruskalsMST {
	public static void main(String[] args){

	}

	public Problem6_8 (boolean weighted, boolean directed) {
		super(weighted, directed);
	}

	public EdgePair getBestNonMST () {
		kruskal();
		for(EdgePair ep: e) {
			if(!ep.mst) {

			}
		}
		return null;
	}


}
