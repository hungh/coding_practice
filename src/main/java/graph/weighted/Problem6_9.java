/*
6-9. [4] Consider the problem of finding a minimum weight connected subset T of edges
from a weighted connected graph G. The weight of T is the sum of all the edge
weights in T.
(a) Why is this problem not just the minimum spanning tree problem? Hint: think
negative weight edges.
(b) Give an efficient algorithm to compute the minimum weight connected subset
T.
*/

package graph.weighted;

import java.util.*;
import ds.Common;

import graph.common.*;
import graph.common.weighted.*;


/*
Answer to (a):
---------------
This is a problem of finding a subset T of edges from G and T is connected.
Therefore, if an edge is negative, it will be included into T.
Clearly, T does not have to be an MST. 
Note that, if all edges have positive weights, then T is an MST.

Answer to (b):
---------------
This is similar to Prims or Kruskals but it has a subtle modification is that:
Let say, we are going to use Kruskals as a base line, then
When considering an edge:
  Even the edge is in the same component S, but if its weight can help reduce the total weight of T
  then it is added.

*/
public class Problem6_9 extends KruskalsMST {
	public static void main(String[] args){
		Problem6_9 g = new Problem6_9();
		g.read_graph("/figure6_3_neg.txt");
		g.print_graph();
		Graph gp = g.getMinWeightSetT();
		Common.log(" (*) Subset T:\n");
		gp.print_graph();

	}
	public Problem6_9() {
		super(true, false);
	}

	public GraphAL getMinWeightSetT() {
		int min_sum = 0;

		SetUnion s = new SetUnion();
		init_set_union(s, nvertices);
		e = to_edge_array();		
		Collections.sort(e);
		
		GraphAL minT = GraphUtils.getNewEmptyGraph(nvertices, true, false);
		int direction;
		for(EdgePair ep: e){
			if(min_sum + ep.w < min_sum){
				minT.insert_edge (ep.y, ep.x, ep.w, directed);
				min_sum += ep.w;
			} else if(! SetUnion.same_component(s, ep.x, ep.y)){
				direction = SetUnion.union_sets(s, ep.x, ep.y);
				min_sum += ep.w;
				if(direction > 0) 
					minT.insert_edge (ep.x, ep.y, ep.w, directed);
				else if (direction < 0)
					minT.insert_edge (ep.y, ep.x, ep.w, directed);
			}
		}

		return minT;
	}

}








