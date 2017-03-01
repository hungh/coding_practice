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
	private int starting;
	public static void main(String[] args){
		GraphCyc g = new GraphCyc(true);
		g.read_graph("/graph2.txt"); 
		g.reset();
		g.setStartingVertex(4);
		g.dfs(g.getStartingVertex());
	}

	public GraphCyc(boolean directed){
		super(directed);
	}

	@Override
	public void process_edge(int x, int y){
		Common.log("\tprocessing edge (" + x + "," + y + ")");	
		if( (parent[y] != -1 || y == starting) && (parent[y] != x) ) { // y=starting, the root node, does not have any children
			Common.log("parent[ " + x + "]=" + parent[y]);
			Common.log("Found circle (" + x + "," + y + ")");
			find_path(y, x);
			finished = true;
		}
	}

	public int getStartingVertex() {
		return starting;
	}

	public void setStartingVertex(int starting){
		this.starting = starting;
	}

}