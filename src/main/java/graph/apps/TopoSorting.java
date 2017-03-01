/*
Topological Sorting
*/
package graph.apps;

import graph.common.GraphAL;
import ds.Common;
import java.util.*;

public class TopoSorting extends GraphAL {

	protected boolean isCirular;
	protected LinkedList<Integer> sorted;

	public static void main(String[] args){
		new TopoSorting(true).getLeftMostVertex("/grapha.txt");
	}

	public int getLeftMostVertex(String fileName){
		read_graph(fileName);
		reset();
		topsort();
		return sorted.peek();
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
			isCirular = true;
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

	public boolean isCirular() { return this.isCirular; }

	protected void print_stack() {
		if(sorted == null) return;
		for(int e : sorted) Common._log(" " + e);
		Common.log("");
	}
}