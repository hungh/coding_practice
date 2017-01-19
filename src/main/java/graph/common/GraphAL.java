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
public class GraphAL {
	public static final int MAXV = 1000;

	public EdgeNode[] edges = new EdgeNode[MAXV + 1]; 
	public int[] degree = new int[MAXV + 1];
	public int nvertices;
	public int nedges;
	public boolean directed;

	protected boolean[] processed = new boolean[MAXV + 1];  // which vertices have been processed
	protected boolean[] discovered = new boolean[MAXV + 1]; // which vertices have been found	
	protected int[] parent = new int[MAXV + 1];             // discovery relation

	protected int time; // used for dfs
	protected boolean finished;
	protected int[] entry_time = new int[MAXV + 1];
	protected int[] exit_time = new int[MAXV + 1];

	public void reset(){
		for(int i = 1; i <= nvertices; i++){
			processed[i] = false;
			discovered[i] = false;
			parent[i] = -1;
		}
		finished = false;
		time = 0;
	}

	public static void main(String[] args){
		GraphAL graph = new GraphAL(false);
		if(args.length > 0){
			graph.read_graph(args[0]);
		}else{
			graph.read_graph();	
		}
		// int noComponents = graph.connected_components();
		// Common.log("The number of connected components=" + noComponents);
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

	public void read_graph(String filePath){
		scanInput(new Scanner(GraphAL.class.getResourceAsStream(filePath), "UTF-8"), false);
	}

	public void read_graph (){
		scanInput(new Scanner(System.in), true);
	}

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
			}
			process_vertex_late(v);
		}
	}

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

	protected void process_vertex_late(int v) { //TODO:
	}

	protected void process_vertex_early(int v){
		Common.log("processed vertex " + v);
	}
	protected void process_edge(int x, int y){
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

	private void scanInput(Scanner sc, boolean showLogs){
		int m, // number of edges;
		x, y; // vertices in edge (x, y)

		if(showLogs) Common.log("Number of Vertices and Edges:");
		nvertices = sc.nextInt();		
		m = sc.nextInt();

		for(int i = 1; i <= m; i++){
			if(showLogs)  Common.log("Enter an edge by 2 numbers:");
			x = sc.nextInt();
			y = sc.nextInt();
			insert_edge(x, y, directed);
		}
		sc.close();
	}
	
}