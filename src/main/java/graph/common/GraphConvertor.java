/*
Graph convertor
5-8. [3] Present correct and efficient algorithms to convert an undirected graph G between
the following graph data structures. You must give the time complexity of
each algorithm, assuming n vertices and m edges.
(a) Convert from an adjacency matrix to adjacency lists.
(b) Convert from an adjacency list to an incidence matrix. An incidence matrix
M has a row for each vertex and a column for each edge, such that M[i, j]=1
if vertex i is part of edge j, otherwise M[i, j] = 0.
(c) Convert from an incidence matrix to adjacency lists.
*/

package graph.common;

import ds.Common;

public class GraphConvertor {

	protected boolean directed;

	public static void main(String[] args){
		GraphConvertor convertor = new GraphConvertor(false);

		GraphAM gm = new GraphAM(false);
		gm.read_graph("/graph5D12.txt");

		gm.print_graph();

		GraphAL gl = convertor.convertTo(gm);
		gl.print_graph();

		Common.log("****** Breath first search");
		gl.bfs(1);

		gl.reset();
		Common.log("***** Depth first search");
		gl.dfs(1);
	}

	public GraphConvertor(boolean directed){
		this.directed = directed;
	}

	/*
	Convert an adjacency matrix to adjacency lists 
	O(n^2) + O(m + n) = O(n^2)
	*/
	public GraphAL convertTo (GraphAM gm){
		int[][] m = gm.getMatrix();		
		if(m == null) throw new IllegalArgumentException ("graph has not been initialized yet.");

		GraphAL gl = new GraphAL(directed);
		gl.setNVertices(gm.getNVertices());
		int i, j;

		for(i = 1; i <= gm.getNVertices(); i++){
			for(j = 1; j <= gm.getNVertices(); j++){
				if( m[i][j] == 1) {
					gl.insert_edge(i, j, directed);
					if(!directed) m[j][i] = 2;					
				} 
				if(m[i][j] == 2) m[i][j] = 1; // restore the original value
			}
		}
		return gl;
	}
}