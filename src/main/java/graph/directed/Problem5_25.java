/*
5-25. [5] An arborescence of a directed graph G is a rooted tree such that there is a
directed path from the root to every other vertex in the graph. Give an efficient
and correct algorithm to test whether G contains an arborescence, and its time
complexity.
*/

package graph.directed;

import java.util.*;
import ds.Common;
import graph.apps.TopoSorting;
import graph.common.*;

public class Problem5_25 extends GraphAL {
	protected int processedCount;
	public static void main(String[] args){
		Problem5_25 g = new Problem5_25(true);
		String fileName = "/problem_5_25.txt";
		g.read_graph(fileName);
		g.print_graph();
		int r = g.getRoot(fileName);
		Common.log("rooted vertex:" + r);

	}

	public Problem5_25 (boolean directed) { super(directed); }

	// return negative if there is no such thing
	public int getRoot(String inputFile) {
		int candidateR = new TopoSorting(true).getLeftMostVertex(inputFile);
		this.dfs(candidateR);
		Common.log(processedCount);
		if(processedCount == nvertices) return candidateR; else return -1;
	}

	@Override
	public void process_edge(int x, int y){
		int edge_type =  edge_classification(x, y);
		if(edge_type == BACK){
			Common.log("Not a tree");
		}
	}

	@Override
	public void process_vertex_late(int v) { 		
		processedCount++;
		super.process_vertex_late(v);
	}
}