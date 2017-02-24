/*
5-19. [5] The diameter of a tree T = (V,E) is given by

  max δ(u, v)
u,v∈V

(where δ(u, v) is the number of edges on the path from u to v). Describe an efficient
algorithm to compute the diameter of a tree, and show the correctness and analyze
the running time of your algorithm.
*/

package graph.analysis;

import ds.Common;
import graph.common.*;

public class Problem5_19 extends GraphAL {
	public static void main(String[] args){
		Problem5_19 g = new Problem5_19(true);
		g.read_graph("/problem5_19.txt");

		// O(V + E)
		g.dfs(1, 0);
		Common.log("diameter of the tree:" + g.getDiameter());
	}

	public Problem5_19 (boolean directed) {
		super(directed);
	}

	public int getDiameter() {
		int max = -1;
		for(int d: depths) 
			if(d > max) max = d;
		return max;
	}
}
