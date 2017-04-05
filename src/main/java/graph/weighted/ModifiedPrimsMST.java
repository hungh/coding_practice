/*
6-11. [5] Modify Prim’s algorithm so that it runs in time O(n log k) on a graph that has
only k different edges costs
*/

package graph.weighted;

import java.util.*;
import ds.Common;
import ds.sortsearch.heap.*;
import graph.common.*;
import graph.common.weighted.*;


public class ModifiedPrimsMST extends PrimsMST {
	private static final boolean DIRECTED = false;
	private static final boolean WEIGHTED = true;

	public ModifiedPrimsMST() {
		super(WEIGHTED, DIRECTED);
	}
	public static void main(String[] args){
		ModifiedPrimsMST g = new ModifiedPrimsMST();
		g.read_graph("/figure6_3.txt");
		g.print_graph();
		Common.log(" (*) prim_mod:\n");
		g.prim_mod(4).print_graph();
	}

	/*
	Complexity:
	----------
	n* logk + m*logk	
	*/
	public GraphAL prim_mod(int start){	
		this.init_local();
		EdgeNode minEdge;		
		
		HeapGR<EdgeNode> heap = new HeapGR<EdgeNode>(new EdgeNode[]{}, EdgeNode.class); // empty heap
		GraphAL mst = GraphUtils.getNewEmptyGraph(nvertices, WEIGHTED, DIRECTED);
		int v = start; // current vertex to process
		int[] dist = new int[nvertices + 1];

		for(int i = 1; i <= nvertices; i++) dist[i] = Integer.MAX_VALUE;
		
		EdgeNode p = edges[v];

		while(p != null){
			heap.insert(p);
			parent[p.y] = v;
			p = p.next;
		}	
		while(! heap.isEmpty()) {						
			intree[v] = true;

			minEdge = heap.extract_min();
			if(intree[minEdge.y]) continue;

			mst.insert_edge (parent[minEdge.y], minEdge.y, minEdge.weight, directed); 			

			v = minEdge.y;
			p = edges [v];
			while(p != null){
				if(!intree[p.y]) {											
					if(dist[p.y] > p.weight) {
						dist[p.y] = p.weight;
						parent[p.y] = v;
						heap.insert(p);		
						// Common.log("(*) inseted " + parent[p.y] + " -> " + p.y);		
					}					
				}
				p = p.next;	
			}
		}
		return mst;
	}
}