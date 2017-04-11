package graph.weighted;
/*
6-19. 
[5] Let G be a weighted directed graph with n vertices and m edges, where all edges
have positive weight. A directed cycle is a directed path that starts and ends at
the same vertex and contains at least one edge. Give an O( n^3 ) algorithm to find
a directed cycle in G of minimum total weight. Partial credit will be given for an
O( n^2 * m ) algorithm.

*/

import java.util.*;
import ds.Common;
import graph.common.*;
import graph.common.weighted.*;

public class Problem6_19 extends Floyd {
	public static void main(String[] args) {
		Problem6_19 g = new Problem6_19(true, true);
		g.setDiameter(20);
		g.read_graph("/problem6_19.txt"); // Reused prims' See Figure 6.3 on Page 196.
		g.print_graph_pretty(g.getMatrix());		
		g.floyd();
		Common.log("Calculating shortest paths matrix ...");
		g.print_graph_pretty(g.getShortedPathMatrix());
	}

	public Problem6_19(boolean weighted, boolean directed) {
		super(weighted, directed);
	}

	public void floyd(){
		this.cloneWeight();

		int i, j; // dimension counters
		int k;    // intermediate vertex counter
		int through_k; // distance through vertex k

		for(k = 1; k <= nvertices; k++)
			for(i = 1; i <= nvertices; i++)
				for(j = 1; j <= nvertices; j++){
					through_k = weight[i][k] + weight[k][j];
					if(through_k < weight[i][j]) weight[i][j] = through_k;
					if(i == j) Common.log("cycle [ " + i + " (" + k + ") " + j + "]= " + weight[i][j]); // k has to be neighboring to j
				}
	}
}