/*
Find strongly connected components in a directed graph
*/

package graph.apps;

import java.util.*;

import ds.Common;
import graph.common.GraphAL;

public class StronglyCCGraph extends GraphAL {
	protected int components_found;
	protected LinkedList<Integer> active;
	protected int[] low = new int[GraphAL.MAXV + 1]; // the oldest vertex surely in the component of v
	protected int[] scc = new int[GraphAL.MAXV + 1]; // strong component number of each vertex

	public static void main(String[] args){
		StronglyCCGraph graph = new StronglyCCGraph(true);
		graph.read_graph("/strong_cc_graph.txt"); // Graph take from Figure 5.16 (page 182)
		graph.strong_components();
		graph.print_components();
	}

	public StronglyCCGraph(boolean directed){
		super(directed);
	}

	public void strong_components(){
		int i;
		for(i = 1; i <= nvertices; i++){
			low[i] = i; 
			scc[i] = -1; // don't know the component number yet
		}

		active = new LinkedList<Integer>();
		reset();

		for(i = 1; i <= nvertices; i++){
			if(!discovered[i])  dfs(i);
		}
	}

	@Override
	public void process_vertex_early(int v){
		active.push(v);
	}

	@Override
	public void process_edge(int x, int y){
		int edge_type = edge_classification(x, y);
		if(edge_type == BACK)
			if(entry_time [y] < entry_time [low[x]]) 
				low[x] = y;

		if(edge_type == CROSS)
			if(scc [y] == -1) // component that has not yet assigned
				if(entry_time[y] < entry_time[low[x]])
						low[x]  =y;

	}

	@Override
	public void process_vertex_late(int v){
		if(low[v]  == v) { // vertex v is the oldest vertext in the component, that is it is the cuff-off of scc
			pop_component(v);
		}
		if((parent[v] > 0) && (entry_time[low[v]] < entry_time[low[parent[v]]]) ){
			// update the oldest vertex of parent's v to v if v is older than its parent
			low[parent[v]] = low[v];
		}
	}


	public void pop_component(int v){
		// every time we pop, we increase the component number
		components_found++;
		scc[v] = components_found;
		int t; // temporary vertex within the component
		while( (t = active.pop())!= v){ // v is the cut-off (border) for a component 
			// assign the component number to each vertex in the same compoent
			scc[t] = components_found;
		}
	}

	public void print_components(){
		Map<Integer, StringBuilder> componentMap = new HashMap<Integer, StringBuilder>();
		StringBuilder buf;
		for(int i = 1; i <= nvertices; i++){
			buf = componentMap.get(scc[i]);
			if(buf == null){
				buf = new StringBuilder();
			}
			componentMap.put(scc[i], buf.append(" ").append(i));
		}
		for(Map.Entry<Integer, StringBuilder> ev: componentMap.entrySet()){
			Common.log("Component " + ev.getKey() + " has :");
			Common.log("\t " + ev.getValue());
		}
	}
}