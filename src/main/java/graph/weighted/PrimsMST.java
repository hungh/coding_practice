/*
Prim's algorithm to find minimum spanning trees
*/

package graph.weighted;

import java.util.*;
import ds.Common;
import graph.common.*;


/* See at P.194 */
public class PrimsMST extends GraphAL {
	protected boolean[] intree;
	protected int[] distance;

	public static void main(String[] args){
		PrimsMST g = new PrimsMST(true, false);
		g.read_graph("/prims_mst.txt"); // See Figure 6.3 on Page 196.
		g.print_graph();		
		g.prim(1);
	}

	public PrimsMST(boolean weighted, boolean directed) {
		super(directed);
		this.weighted = weighted;
	}

	public void init() {
		super.init();
		intree = new boolean[nvertices + 1];
		distance = new int[nvertices + 1];

		for(int i = 1; i <= nvertices; i++){
			distance[i] = Integer.MAX_VALUE;
			parent[i] = -1;
		}
	}

	public void prim(int start){
		this.init();
		EdgeNode p;
		int i;
		int v; // current vertex to process
		int w; // candidate next vertex;
		int weight; // edge weight
		int dist;   // best distance from start

		distance[start] = 0;
		v= start;
		while(!intree[v]){			
			intree[v] = true;  // we already knew v is the best candidate b/c it was picked from the loopp (see the end of this while)
			p = edges[v];
			while(p != null){
				w = p.y;
				weight = p.weight;
				// assign all the weights to all of v's neighbors (not intree) who have more weigh than themselves
				if( (distance[w] > weight) && !intree[w]) {
					distance[w] = weight;
					parent[w] = v; 					
				}
				p = p.next;
			}
			v = 1;
			dist = Integer.MAX_VALUE;
			// pick the best distance to be added as the best candidate for the next loop
			for(i = 1; i <=nvertices; i++)
				if(!intree[i] && (dist > distance[i])) {
					dist = distance[i];
					v = i;
				}			
		}

	}
}