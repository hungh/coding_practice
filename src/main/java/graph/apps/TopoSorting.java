/*
Topological Sorting
*/
package graph.apps;

import graph.common.GraphAL;
import ds.Common;
import java.util.*;

public class TopoSorting extends GraphAL {

	protected LinkedList<Integer> sorted;

	public static void main(String[] args){
		new TopoSorting(true).test("/grapha.txt");
	}

	public void test(String fileName){
		read_graph(fileName);
		reset();
		topsort();
	}

	public TopoSorting(boolean directed){
		super(directed);
	}

	@Override
	public void process_vertex_late(int v){
		sorted.push(v);
	}

	@Override
	public void process_edge(int x, int y){
		int edge_type;  // edge class
		edge_type = edge_classification(x, y);

		if(edge_type == BACK){
			Common.log("Warning: directed cycle found, not a DAG (" + x + "," + y + ")");
		}

	}

	public void topsort(){
		sorted = new LinkedList<Integer>();
		for(int i = 1; i <= nvertices; i++){
			if(!discovered[i])  dfs(i);
		}

		print_stack();
	}

	protected void print_stack() {
		if(sorted == null) return;
		for(int e : sorted) Common._log(" " + e);
		Common.log("");
	}
}