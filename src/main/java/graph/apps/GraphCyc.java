/*
Finding  cycles in a graph
*/

package graph.apps;

import graph.common.GraphAL;
import ds.Common;

/*
Find cycles in a directed graph
*/
public class GraphCyc extends GraphAL {
	public static void main(String[] args){
		GraphCyc graph = new GraphCyc(true);
		graph.read_graph("/graph2.txt");
		graph.reset();
		graph.dfs(1);
	}

	public GraphCyc(boolean directed){
		super(directed);
	}

	@Override
	public void process_edge(int x, int y){
		Common.log("\tprocessing edge (" + x + "," + y + ")");	
		if( (parent[y] != -1 || y == 1) && (parent[y] != x) ) { // y=1, the root node, does not have any children
			Common.log("parent[ " + x + "]=" + parent[y]);
			Common.log("Found circle (" + x + "," + y + ")");
			find_path(y, x);
			finished = true;
		}
	}

}