/*
Graph using incident matrix
*/

package graph.common;

import java.util.*;

import ds.Common;
import graph.common.Graph;

public class GraphIM extends Graph {
	protected int[][] m;

	public GraphIM(){
		this(false); // for now, only support undirected graph
	}

	public GraphIM (boolean directed){
		this.directed = directed;
		this.nedges = 1;
	}

	public static void main(String[] args){
		GraphIM g = new GraphIM(false);
		g.read_graph("/graph5D12.txt");
		g.print_graph();

	}

	@Override
	public void insert_edge(int x, int y, boolean _directed){
		if(m == null){
			m = new int[nvertices + 1][provided_nedges + 1];
		}
		m[x][nedges] = 1;
		m[y][nedges] = 1;

		nedges++;
		
	}

	@Override
	public void print_graph(){
		for(int i = 1; i <= nvertices; i++){
			for(int j = 1; j <= provided_nedges; j++){
				Common._log(" " +  m[i][j]);
			}
			Common.log("");
		}
	}

	@Override
	public void bfs(int start){}

	@Override
	public void dfs(int start){}

	@Override
	public void process_vertex_late(int v){}

	@Override
	public  void process_vertex_early(int v){}

	@Override
	public void process_edge(int x, int y){}

	@Override
	public void hard_reset(){}
}

