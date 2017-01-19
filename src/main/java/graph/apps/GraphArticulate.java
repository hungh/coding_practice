/*
Articulation Vertices
*/

package graph.apps;

import graph.common.GraphAL;
import ds.Common;

public class GraphArticulate extends GraphAL {

	protected int[] reachable_ancestor = new int[GraphAL.MAXV + 1];
	protected int[] tree_out_degree = new int[GraphAL.MAXV + 1];

	public static void main(String[] args){
		GraphArticulate graph = new GraphArticulate(true);
		graph.read_graph("/grapha.txt");
		graph.reset();
		graph.dfs(1);
	}

	public GraphArticulate(boolean directed){
		super(directed);
	}

	@Override
	public void process_vertex_early(int v){

	}
}