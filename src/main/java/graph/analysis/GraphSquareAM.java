/*
5-12. [5] The square of a directed graph G = (V,E) is the graph G2 = (V,E^2) such that
(u,w) ∈ E^2 iff there exists v ∈ V such that (u, v) ∈ E and (v,w) ∈ E; i.e., there is
a path of exactly two edges from u to w.
Give efficient algorithms for both adjacency lists and matrices.
*/

package graph.analysis;

import java.util.*;

import ds.Common;
import graph.common.GraphConvertor;
import graph.common.GraphAL;
import graph.common.GraphAM;

public class GraphSquareAM extends GraphAM implements GraphSquare {

	protected int[][] m_new;

	public GraphSquareAM(boolean directed){
		super(directed);
	}

	public static void main(String[] args){
		GraphSquareAM g = new GraphSquareAM(true);
		g.read_graph("/graphsquare2.txt");

		Common.log(" ******* G");
		g.print_graph();

		g.square();

		Common.log("\n ******* G^2");
		g.print_graph();

		GraphAL gal = new GraphConvertor(true).convertTo(g);

		Common.log("\n *** Converted into graph (adjacency list)");
		gal.print_graph();
	}

	@Override
	public void backupOrigin(){
		m_new = new int[nvertices + 1][nvertices + 1];

		for(int i = 1; i <= nvertices; i++) {
			for(int j = 1; j <= nvertices; j++){
				m_new[i][j] = m[i][j];
				m[i][j] = 0;
			}
		}
	}

	// O(E^3). Please improve this ??
	@Override
	public void square(){
		backupOrigin();
		for(int u = 1; u <= nvertices; u++) {
			for(int v = 1; v <= nvertices; v++) {
				if((u != v) && (m_new[u][v] > 0)){
					for(int w = 1; w <= nvertices; w++){
						if((v != w) && (u != w) && (m_new[v][w] > 0)){
							m[u][w] = 1;
						}
					}
				}
			}
		}
		mergeGraph();
	}

	@Override
	public void mergeGraph() {
		for(int i = 1; i <= nvertices; i++){
			for(int j =1; j <= nvertices; j++){
				if( m_new[i][j] > 0) m[i][j] =  m_new[i][j];
			}
		}
	}
}

