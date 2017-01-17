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
	}

	public GraphAL(boolean directed) {
		this.directed = directed;
	}


	public void read_graph (){
		int m, // number of edges;
		x, y; // vertices in edge (x, y)

		Scanner sc = new Scanner(System.in);
		Common.log("Number of Vertices:");
		nvertices = sc.nextInt();
		Common.log("Number of Edges:");
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
}