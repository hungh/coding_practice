/*
Graph implementation using Adjacency List
*/

package graph.common;

import java.util.*;
import java.io.*;
import ds.Common;
/*
Converted into Java from C code in Skiena Algorithm Design Manual (Chapter 5)
*/
public class GraphAL extends Graph {
	public static final int MAXV = Graph.MAXV;

	protected Set<Integer> depths = new HashSet<Integer>();

	public EdgeNode[] edges = new EdgeNode[MAXV + 1]; 
	
	public void hard_reset(){
		for(int i = 1; i <= nvertices; i++) {
			edges[i] = null;
			degree[i] = 0;
		}
		depths.clear();
		reset();
	}

	public static void main(String[] args){
		GraphAL graph = new GraphAL(false);
		graph.read_graph("/graph5D12.txt");
		graph.print_graph();
		graph.bfs(1);
		Common.log("-----PATH (node 1 to node 5)-----");
		graph.find_path(1, 5);
		Common.log("\nDepth First Search...");
		graph.reset();
		graph.dfs(1);	

	}

	public GraphAL(boolean directed) {
		this.directed = directed;
	}

	public EdgeNode[] getEdges(){
		return this.edges;
	}

	@Override
	public void insert_edge(int x, int y, boolean _directed){
		EdgeNode p = new EdgeNode();
		p.y = y;
		p.next = edges[x]; // insert at the head of adjacency list
		edges[x] = p;

		if(!_directed) { // need another direction for undirected graph
			insert_edge(y, x, true);
		}else{
			// count the number edge
			nedges++;
		}
	}

	@Override
	public void print_graph(){
		EdgeNode p;
		for(int i = 1; i <= nvertices; i++){
			Common._log(i + " :");
			p = edges[i];
			while(p != null){
				Common._log(" " + p.y);
				p = p.next;
			}
			Common.log("");
		}
	}

	@Override
	public void bfs(int start){
		LinkedList<Integer> q = new LinkedList<Integer>();
		int i,
		v, // current vertex
		y; // successor vertex
		EdgeNode p;
		for(i = 1; i <= nvertices; i++) parent[i] = -1;

		q.push(start);
		discovered[start]= true;

		while(! q.isEmpty()){
			v = q.pop();
			process_vertex_early(v);
			processed[v] = true;
			p = edges[v];
			while(p != null){
				y = p.y;
				if(! processed[y] || directed) process_edge(v, y);
				if(! discovered[y]){
					q.push(y);
					discovered[y] = true;
					parent[y] = v;
				}
				p = p.next;

				after_checking_aneighbor(v, y);
			}
			process_vertex_late(v);
		}
	}

	@Override
	public void dfs(int v){
		dfs(v, -1);
	}

	public void dfs(int v, int depth){
		EdgeNode p;
		int y;

		if(finished) return;
		discovered[v] = true;
		time++;
		entry_time[v] = time;
		process_vertex_early(v);

		p = edges[v];
		if(depth >= 0  &&  (p == null)) depths.add(depth); // Optional: for special graph (tree)
		while(p != null){
			y = p.y;
			if(!discovered[y]){
				parent[y] = v;
				process_edge(v, y);
				if(depth >= 0 ) dfs(y, depth + 1); /* optional */ else dfs(y, depth);
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

	protected void after_checking_aneighbor(int v, int y){ //TODO:
	}

	@Override
	public void process_vertex_late(int v) { //TODO:
	}

	@Override
	public void process_vertex_early(int v){
		Common.log("processed vertex " + v);
	}

	@Override
	public void process_edge(int x, int y){
		Common.log("\tprocessed edge (" + x + "," + y + ")");	
	}

	public void find_path(int start, int end){
		if( (start == end) || (end == -1) ) {
			Common._log(start + " ");
		} else {
			Common._log(end + " ");
			find_path(start, parent[end]);
		}
	}

	/*
	Find the number of connected components in a set of vertices
	Returns the components number and its vertices to which component they belong 
	*/
	public int connected_components(){
		int c = 0; // component number
		for(int i = 1; i <= nvertices; i++){
			if(! discovered[i]) {
				c++;
				bfs(i);
				Common.log("");
			}
		}
		return c;
	}

	public int edge_classification(int x, int y){
		if(parent[y] == x) return TREE;
		if(discovered[y] && ! processed[y]) return BACK;
		if(processed [y] && (entry_time[y] > entry_time[x])) return FORWARD;
		if(processed [y] && (entry_time[y] < entry_time[x])) return CROSS;
		return UNKNOWN_EDGE;
	}

	protected void populateVertexDegrees (){
		EdgeNode p;
		int tnode;
		for(int i = 1; i <= nvertices; i++){
			tnode = 0;
			p = edges[i];
			while(p != null){
				degree[p.y]++;
				tnode++;
				p = p.next;
			}
			degree[i] += tnode;
		}	
	}
}