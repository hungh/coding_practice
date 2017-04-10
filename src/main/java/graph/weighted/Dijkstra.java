package graph.weighted;

import ds.Common;
import graph.common.*;
import graph.common.weighted.*;

// See Skiena Book. Page 208
public class Dijkstra extends GraphAL {
	protected boolean[] intree;
	protected int[] distance;
	protected int[] vweights; // weights of vertices (Optional)

	public Dijkstra(boolean weighted, boolean directed){
		super(directed);
		this.weighted = weighted;
	}

	public static void main(String[] args){
		Dijkstra g = new Dijkstra(true, false);
		g.read_graph("/prims_mst.txt"); // Reused the prims' graph. See Figure 6.3 on Page 196.
		g.print_graph();

		WeightCalFunc calFunc = new WeightCalFunc() {
			public void calculate(int v, EdgeNode p, int[] distance, int[] parent, int[] vws){
				int w = p.y;
				int weight = p.weight;
				// assign all the weights to all of v's neighbors (not intree) who have more weigh than themselves
				if(distance[w] > (distance[v] + weight)) { // **
					distance[w] = distance[v] + weight;    // **
					parent[w] = v; 					
				}
			}
		};

		g.dijkstra(1, calFunc);
		g.print_path(7);
	}

	public void init_local() {
		intree = new boolean[nvertices + 1];
		distance = new int[nvertices + 1];

		for(int i = 1; i <= nvertices; i++){
			distance[i] = Integer.MAX_VALUE;
			parent[i] = -1;
		}
	}
	// almost the same as prims'
	public void dijkstra(int start, WeightCalFunc calFunc){
		this.init_local();
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
				calFunc.calculate(v, p, distance, parent, vweights);
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

	public void print_path(int end){
		int v = end;	
		Common._log("PATH:" + v + " ");	
		while(parent[v] > 0) {			
			v = parent[v];
			Common._log(v +  " ");
		}
		Common.log("");
	}
}