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
		g.read_graph("/problem6_19.2.txt"); // see problem6_19.png
		g.print_graph_pretty(g.getMatrix());		
		g.floyd();
		Common.log("Calculating shortest paths matrix ...");
		g.print_graph_pretty(g.getShortedPathMatrix());
	}

	public Problem6_19(boolean weighted, boolean directed) {
		super(weighted, directed);
	}
	// O(n^3)
	public void floyd(){		
		this.cloneWeight();

		int i, j; // dimension counters
		int k;    // intermediate vertex counter
		int through_k; // distance through vertex k		
		int d = getDiameter()  - 1;
		int minCycWeight = d;

		// to find minimum cycle path
		int start = -1, middle = start;

		for(i = 1; i <= nvertices; i++) parent[i] = -1;

		for(k = 1; k <= nvertices; k++)
			for(i = 1; i <= nvertices; i++)
				for(j = 1; j <= nvertices; j++){

					// update parent if it is not assigned yet and has to be neighbor
					if(m[i][k] < d){
						// the new parent comes from a smaller edge
						if( (parent[k] < 0) || (m[i][k] < m[parent[k]][k]) )
							parent[k] = i;						
					}

					if(m[k][j] < d) {
						// the new parent comes from a smaller edge
						if((parent[j] < 0) || (m[k][j] < m[parent[j]][j]))
							parent[j] = k;						
					}

					through_k = weight[i][k] + weight[k][j];

					if(through_k < weight[i][j]) {
						weight[i][j] = through_k;																	
					}

					// k has to be neighboring to j	
					if( (i == j) && (weight[i][j] < d) && (m[k][j] < d)){
						// Common.log("cycle [ " + i + " (" + k + ") " + j + "]= " + weight[i][j]+ "; " + m[k][j]); 

						if(minCycWeight > weight[i][j]) {
							minCycWeight = weight[i][j];
							start = i;
							middle = k;
							// re-update parent when seeing a min cycle
							parent[j] = k;
						}						
					} 
				}

		if(start > 0){
			Common.log("Min Cycle Weight: " + minCycWeight);
			int v = middle;
			Common._log("(*) Min Cycle Path: " + middle);
			while(parent[v] != middle){
				Common._log(" - " + parent[v]);
				v = parent[v];
			}
			Common.log(" - " + middle + "\n");
		}

	}
}