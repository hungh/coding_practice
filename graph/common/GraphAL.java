/*
Graph implementation using Adjacency List
*/

package graph.common;

import java.util.*;
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

	public static void main(String[] args){
		GraphAL graph = new GraphAL(false);
		graph.read_graph();
		graph.print_graph();
		graph.bfs(1);
	}

	public GraphAL(boolean directed) {
		this.directed = directed;
	}


	public void read_graph (){
		int m, // number of edges;
		x, y; // vertices in edge (x, y)

		Scanner sc = new Scanner(System.in);
		Common.log("Number of Vertices and Edged:");
		nvertices = sc.nextInt();		
		m = sc.nextInt();

		for(int i = 1; i <= m; i++){
			Common.log("Enter an edge by 2 numbers:");
			x = sc.nextInt();
			y = sc.nextInt();
			insert_edge(x, y, directed);
		}
		sc.close();
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
		boolean[] processed = new boolean[MAXV + 1];  // which vertices have been processed
		boolean[] discovered = new boolean[MAXV + 1]; // which vertices have been found
		int[] parent = new int[MAXV + 1];             // discovery relation

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

	public void process_vertex_late(int v) { //TODO:
	}

	public void process_vertex_early(int v){
		Common.log("processed vertex " + v);
	}
	public void process_edge(int x, int y){
		Common.log("\tprocessed edge (" + x + "," + y + ")");	
	}
}