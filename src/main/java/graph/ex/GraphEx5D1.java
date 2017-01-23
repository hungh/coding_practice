/*
Exercise 5.11
*/
package graph.ex;

import ds.Common;
import graph.common.GraphAL;

public class GraphEx5D1 extends GraphAL {
	public static void main(String[] args){
		GraphEx5D1 graph = new GraphEx5D1(false);
		graph.read_graph("/graph5d1.txt");

		Common.log("DFS");

		graph.reset();
		graph.bfs(1);

		Common.log("DFS");

		graph.reset();
		graph.dfs(1);


		graph.hard_reset();
		graph.read_graph("/graph5d1b.txt");

		Common.log("DFS");

		graph.reset();
		graph.bfs(1);

		Common.log("DFS");

		graph.reset();
		graph.dfs(1);

	}

	public GraphEx5D1(boolean directed){
		super(directed);
	}
}
