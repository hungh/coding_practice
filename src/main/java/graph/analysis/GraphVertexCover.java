/*
5-13. [5] A vertex cover of a graph G = (V,E) is a subset of vertices V
such that each edge in E is incident on at least one vertex of V

	(a) Give an efficient algorithm to find a minimum-size vertex cover if G is a tree.

	(b) Let G = (V,E) be a tree such that the weight of each vertex is equal to the
		degree of that vertex. Give an efficient algorithm to find a minimum-weight
		vertex cover of G.

	(c) Let G = (V,E) be a tree with arbitrary weights associated with the vertices.

	Give an efficient algorithm to find a minimum-weight vertex cover of G.
*/

package graph.analysis;

import java.util.*;

import ds.Common;
import graph.common.GraphAL;

public class GraphVertexCover extends GraphAL {
	protected Map<Integer, Object> coverMap = new HashMap<Integer, Object>();

	public GraphVertexCover(boolean directed){
		super(directed);
		coverMap.put(1, null);
	}

	public static void main(String[] args){
		GraphVertexCover g = new GraphVertexCover(true);
		g.read_graph("/graphsquare2.txt");
		g.print_graph();
		g.dfs(1);
		g.printCoverVertices();
	}


	@Override
	public void process_edge(int x, int y){
		if(! coverMap.containsKey(x) && !coverMap.containsKey(y) ) {
			coverMap.put(y, null);
		}
	}

	public void printCoverVertices () {
		Common.log("** Cover vertices:");
		for(Integer e : coverMap.keySet()) Common._log(" " + e);
		Common.log("");
	}
}