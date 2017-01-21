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
		TopoSorting graph = new TopoSorting(true);
		graph.read_graph("/grapha.txt");
		graph.reset();
		graph.topsort();
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
			Common.log("Warning: directed cycle found, not a DAG");
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
		while(!sorted.isEmpty()){
			Common._log(" " + sorted.pop());
		}
		Common.log("");
	}
}