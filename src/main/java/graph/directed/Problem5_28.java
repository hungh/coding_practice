/*
5-28. [5] An articulation vertex of a graph G is a vertex whose deletion disconnects G. 
Let G be a graph with n vertices and m edges. Give a simple O(n + m) algorithm for 
finding a vertex of G that is not an articulation vertex—i.e. , whose deletion does not disconnect G.
*/

package graph.directed;

import java.util.*;
import ds.Common;
import graph.apps.*;
import graph.common.*;

public class Problem5_28 extends GraphArticulate {
	private int notArticulateV; // index of the vertex that is checked whether it is not a verticulate vertex
	private boolean isNotArticulate;

	public static void main(String[] args){
		Problem5_28 g = new Problem5_28(true);
		g.read_graph("/problem_5_28.txt");
		g.print_graph();
		g.setVertexID(3);
		g.dfs(1); // assume that 1 is the root node
		Common.log(g.isNotArticulate());
	}

	public Problem5_28(boolean directed) {
		super(directed);
	}

	public boolean isNotArticulate() { return this.isNotArticulate;}
	public void setVertexID(int v) {
		this.notArticulateV = v;
	}

	@Override
	public void record_articulate(int v, boolean isArticulate){
		if(!isArticulate && (this.notArticulateV == v)) {
			isNotArticulate = true;
		}
	}

}
