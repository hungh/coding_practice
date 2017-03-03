/*
Articulation Vertices
*/

package graph.apps;

import graph.common.GraphAL;
import ds.Common;

/*
Skiena Algoritm Design and Manual (C -> Java)
*/
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
		boolean root; // is vertex root of the dfs tree
		int time_v; //earliest reachable time for v
		int time_parent; // earliest reachable time for parent's v
		boolean isArticulate = false;
		boolean isParentArt = false;

		// root does not have parent
		if(parent[v] < 1) {
			if(tree_out_degree[v] > 1) {// if it has more than 1 branch, then it is an articulation vertex
				Common.log("Root is an articulation vertex " + v);
				isArticulate = true;
			}
			return; // nothing to do
		}
		// is v's parent root?
		root = parent [parent[v]] < 1;
		// v's parent is also its reachable ancester but this parent is not a root
		if(reachable_ancestor[v] == parent[v] && !root){
			Common.log("v's parent is an articulate vertex v=" + v + ";parent=" + parent[v]);
			isParentArt = true;
			
		}
		// just discovered v (after the second call of process_vertex_early, v's parent and now v) [v is not processed yet]
		if(reachable_ancestor[v] == v){
			// v's parent must be an articulation vertex
			Common.log("Bridge articulation vertex " +  parent[v]);
			isParentArt = true;
			// if v is not a leaf then it is an articulate vertex
			if(tree_out_degree[v] > 0) {// a leaf must not have any branch-out
				Common.log("v is an bridge articulate vertex " + v);
				isArticulate = true;
			}
		}

		time_v = entry_time [reachable_ancestor[v]];
		time_parent  = entry_time [reachable_ancestor[parent[v]]];

		// entry of time v's reachable ancester is earlier (higher) than its parents'
		// we have to update parent of v reachable ancester to to higher one, which is v
		// ex, ->(1) -> (2) -> (3) -> (v) and (1) -> (v), update reachable_ancester[3] -> 1
		if(time_v < time_parent){
			reachable_ancestor[parent[v]] = reachable_ancestor[v];
		}
		if(isArticulate) record_articulate(v, isArticulate);
		if(isParentArt) record_articulate(parent[v], isArticulate);
	}

	public void record_articulate(int v, boolean isArticulate){}
}