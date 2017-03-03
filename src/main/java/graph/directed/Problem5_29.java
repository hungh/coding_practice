/*
5-29. Following up on the previous problem (5-28), give an O(n+m) algorithm that finds a deletion order for the n vertices 
such that no deletion disconnects the graph. (Hint: think DFS/BFS.)
*/

package graph.directed;

import java.util.*;
import ds.Common;
import graph.apps.*;
import graph.common.*;


public class Problem5_29 extends GraphArticulate {
	protected List<Integer> delOrder = new ArrayList<Integer>();

	public Problem5_29(boolean directed) {
		super(directed);
	}

	public static void main(String[] args){
		Problem5_29 g = new Problem5_29(false);
		g.read_graph("/problem_5_29.txt");
		g.print_graph();
		g.dfs(1);
		g.print_del_order();
	}


	public void rm_articulate(int v){
		if(edges[v] != null){
			delOrder.add(v);
			remove_vertex(v);	
		}
	}

	public void print_del_order() {
		for(Integer ord: delOrder) Common._log(" " + ord);
	}

}