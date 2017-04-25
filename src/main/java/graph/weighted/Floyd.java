package graph.weighted;

import java.util.*;
import ds.Common;
import graph.common.*;

public class Floyd extends GraphAM {
	protected int diameter;
	protected int[][] weight;
	protected int[][] next;  // matrix to reconstruct the shortest path

	public Floyd(boolean weighted, boolean directed) {
		super(directed);
		this.weighted = weighted;
	}

	public static void main(String[] args){
		Floyd g = new Floyd(true, true);
		g.setDiameter(42);
		g.read_graph("/floyd.txt"); // works for non-cyclic negative weighted graphs (DAG)
		g.init_local();
		g.print_graph_pretty(g.getMatrix());		
		g.floyd();
		Common.log("s path:" + g.print_spath(1, 5));
		Common.log("Calculating shortest paths matrix ...");
		g.print_graph_pretty(g.getShortedPathMatrix());
	}

	public void init_local() {
		next = new int[nvertices + 1][nvertices + 1];	
		for(int i = 1; i <= nvertices; i++)
			for(int j= 1; j <= nvertices; j++){
				if(m[i][j] < diameter -1)
					next[i][j] = j;
				else
					next[i][j] = -1;
			}
	}


	public int getDiameter(){
		return this.diameter;
	}

	public void setDiameter(int diameter){
		this.diameter = diameter;
	}

	public int[][] getShortedPathMatrix() {
		return weight;
	}

	public int[][] allocateMemoryAndInit(){
		int[][] new_matrix = super.allocateMemoryAndInit();
		weight = new int[nvertices + 1][nvertices + 1];

		for(int i = 1; i <= nvertices; i++)
			for(int j= 1; j <= nvertices; j++)
				new_matrix[i][j] = this.getDiameter() - 1;				
		
		return new_matrix;
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
					if(through_k < weight[i][j]) { 
						weight[i][j] = through_k;
						next[i][j] = next[i][k]; // we know that k is next to i. Therefore, we don't need j anymore
					}
				}
	}

	public List<Integer> print_spath(int u, int v){		
		List<Integer> path = new ArrayList<Integer>();
		if( next[u][v] < 0) return path;
		
		path.add(u); // start from u
		while(u != v) { 
			u = next[u][v];  // keep moving u toward v until it hits v
			path.add(u);
		}
		return path;

	}

	protected void cloneWeight() {
		for(int i = 1; i <= nvertices; i++)
			for(int j= 1; j <= nvertices; j++)
				weight[i][j] = m[i][j];				
	}
}