/*
Graph  implementation using Adjacency Matrix
*/

package graph.common;

import ds.Common;

public class GraphAM  extends Graph {

	protected int[][] m;

	public static void main(String[] args){
		GraphAM g = new GraphAM(false);
		g.read_graph("/graph5D12.txt");
		g.print_graph();
	}

	public GraphAM(boolean directed){
		this.directed = directed;
	}

	@Override
	public void insert_edge(int x, int y, boolean _directed){
		if(m == null){
			m = new int[nvertices + 1][nvertices + 1];
		}
		m[x][y] = 1;

		if(!_directed) { // need another direction for undirected graph
			insert_edge(y, x, true);
		}else{			
			// count the number edge
			nedges++;
		}
		
	}

	@Override
	public void print_graph(){
		for(int i = 0; i < nvertices; i++){
			for(int j = 0; j < nvertices; j++){
				Common._log(" " +  m[i][j]);
			}
			Common.log("");
		}
	}

	@Override
	public void bfs(int start){

	}

	@Override
	public void dfs(int start){

	}

	@Override
	public void process_vertex_late(int v){

	}

	@Override
	public void process_vertex_early(int v){

	}

	@Override
	public void process_edge(int x, int y){

	}


}
