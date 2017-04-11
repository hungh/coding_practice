package graph.weighted;

import ds.Common;
import graph.common.*;

public class Floyd extends GraphAM {
	protected int diameter;
	protected int[][] weight;

	public Floyd(boolean weighted, boolean directed) {
		super(directed);
		this.weighted = weighted;
	}

	public static void main(String[] args){
		Floyd g = new Floyd(true, false);
		g.setDiameter(42);
		g.read_graph("/prims_mst.txt"); // Reused prims' See Figure 6.3 on Page 196.
		g.print_graph_pretty(g.getMatrix());		
		g.floyd();
		Common.log("Calculating shortest paths matrix ...");
		g.print_graph_pretty(g.getShortedPathMatrix());
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
					if(through_k < weight[i][j]) weight[i][j] = through_k;
				}
	}

	protected void cloneWeight() {
		for(int i = 1; i <= nvertices; i++)
			for(int j= 1; j <= nvertices; j++)
				weight[i][j] = m[i][j];				
	}
}