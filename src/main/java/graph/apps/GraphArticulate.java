/*
Articulation Vertices
*/

package graph.apps;

import graph.common.GraphAL;
import ds.Common;

public class GraphArticulate extends GraphAL {

	public static final int TREE = 0;
	public static final int BACK = 1;
	public static final int FORWARD = 2;
	public static final int CROSS = 3;
	public static final int UNKNOWN_EDGE = -1;

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
		reachable_ancestor[v] = v;
	}

	@Override
	public void process_edge(int x, int y){
		int edge_type = edge_classification (x, y);

		if(edge_type == TREE) tree_out_degree[x]++;

		if( (edge_type == BACK) && (parent[x] != y)) { // there is more than 1 node in between x -> y and (x->y) is a back edge
			// we need to update the reachable_ancestor of x to y when y is earlier than x's parent
			if(entry_time[y] < entry_time[reachable_ancestor[x]]) reachable_ancestor[x] = y;
		}
	}

	@Override
	public void process_vertex_late(int v){

	}


	public int edge_classification(int x, int y){
		if(parent[y] == x) return TREE;
		if(discovered[y] && ! processed[y]) return BACK;
		if(processed [y] && (entry_time[y] > entry_time[x])) return FORWARD;
		if(processed [y] && (entry_time[y] < entry_time[x])) return CROSS;
		return UNKNOWN_EDGE;
	}
}