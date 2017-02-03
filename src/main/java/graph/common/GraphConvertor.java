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

import java.util.*;

import ds.Common;
import graph.common.EdgeNode;

public class GraphConvertor {
	private static final String GRAPH_INIT_ERROR = "graph has not been initialized yet.";

	protected boolean directed;

	public static void main(String[] args){
		GraphConvertor convertor = new GraphConvertor(false);

		GraphAM gm = new GraphAM(false);
		gm.read_graph("/graph5D12.txt");

		gm.print_graph();

		GraphAL gal = convertor.convertTo(gm);
		gal.print_graph();

		Common.log("****** 1.Breath first search");
		gal.bfs(1);

		gal.reset();
		Common.log("****** 1.Depth first search");
		gal.dfs(1);

		Common.log("***** Converting GAL into IM");
		GraphIM gim = convertor.convertTo(gal);

		gim.print_graph();

		Common.log("****** 2.Breath first search");
		gim.bfs(1);

		gim.reset();
		Common.log("***** 2.Depth first search");
		gim.dfs(1);
	}

	public GraphConvertor(boolean directed){
		this.directed = directed;
	}

	/*
	Convert an Adjacenct list into incident matrix
	O(V + E)
	TODO: not correct
	*/
	public GraphIM convertTo(GraphAL gal){
		EdgeNode[] gal_edges = gal.getEdges();
		if(gal_edges[1] == null) throw new IllegalArgumentException (GRAPH_INIT_ERROR);

		// WARNING: GraphIM does not support directed graphs for now
		GraphIM gim = new GraphIM(directed);	
		gim.setNVertices(gal.getNVertices());
		gim.setProvided_nedges(gal.getProvided_nedges());

		EdgeNode p; int c = 1;
		for(int i = 1; i <= gal.getNVertices(); i++){
			p = gal_edges[i];
			while(p != null){				
				gim.insert_edge(i, p.y, directed);					
				p = p.next;
			}
		}
		return gim;
	}

	/*
	Convert an adjacency matrix to adjacency lists 
	O(n^2) + O(m + n) = O(n^2)
	*/
	public GraphAL convertTo (GraphAM gm){
		int[][] m = gm.getMatrix();		
		if(m == null) throw new IllegalArgumentException (GRAPH_INIT_ERROR);

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
		gl.setProvided_nedges(gl.getNedges());
		gl.print_graph();
		return gl;
	}
}