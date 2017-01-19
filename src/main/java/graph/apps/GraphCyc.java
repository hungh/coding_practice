/*
Finding  cycles in a graph
*/

package graph.apps;

import graph.common.GraphAL;
import graph.common.EdgeNode;
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

	@Override
	public void dfs(int v){
		EdgeNode p;
		int y;

		if(finished) return;
		discovered[v] = true;
		time++;
		entry_time[v] = time;
		process_vertex_early(v);

		p = edges[v];
		while(p != null){
			y = p.y;
			if(!discovered[y]){
				parent[y] = v;
				process_edge(v, y);
				dfs(y);
			} else if(!processed[y] || directed){
				process_edge(v, y);
			}
			if(finished) return;
			p = p.next;
		}
		process_vertex_late(v);
		time++;
		exit_time[v] = time;
		processed[v] = true;

	}


}