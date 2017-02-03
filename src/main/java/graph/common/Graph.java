package graph.common;

import java.util.*;
import java.io.*;

import ds.Common;

public abstract class Graph {

	public static final int MAXV = 1000;

	public int[] degree = new int[MAXV + 1];
	public int nvertices;
	public int provided_nedges;
	public int nedges;
	public boolean directed;

	// edge types
	protected int TREE = 0;
	protected int BACK = 1;
	protected int FORWARD = 2;
	protected int CROSS = 3;
	protected int UNKNOWN_EDGE = -1;
	//

	protected boolean[] processed = new boolean[MAXV + 1];  // which vertices have been processed
	protected boolean[] discovered = new boolean[MAXV + 1]; // which vertices have been found	
	protected int[] parent = new int[MAXV + 1];             // discovery relation

	protected int time; // used for dfs
	protected boolean finished;
	protected int[] entry_time = new int[MAXV + 1];
	protected int[] exit_time = new int[MAXV + 1];


	public void read_graph(String filePath){
		scanInput(new Scanner(GraphAL.class.getResourceAsStream(filePath), "UTF-8"), false);
	}

	public void read_graph (){
		scanInput(new Scanner(System.in), true);
	}

	public int getNVertices() {
		return nvertices;
	}

	public void setNVertices(int nvertices){
		this.nvertices = nvertices;
	}

	public void setProvided_nedges(int provided_nedges){
		this.provided_nedges = provided_nedges;
	}

	public int getProvided_nedges(){
		return this.provided_nedges;
	}

	public int getNedges () { return nedges;}
	
	public abstract void insert_edge(int x, int y, boolean _directed);
	public abstract void print_graph();
	public abstract void bfs(int start);
	public abstract void dfs(int start);
	public abstract void process_vertex_late(int v);
	public abstract void process_vertex_early(int v);
	public abstract void process_edge(int x, int y);
	public abstract void hard_reset();

	protected void scanInput(Scanner sc, boolean showLogs){
		int m, // number of edges;
		x, y; // vertices in edge (x, y)

		if(showLogs) Common.log("Number of Vertices and Edges:");
		nvertices = sc.nextInt();		
		m = sc.nextInt();
		setProvided_nedges (m);

		for(int i = 1; i <= m; i++){
			if(showLogs)  Common.log("Enter an edge by 2 numbers:");
			x = sc.nextInt();
			y = sc.nextInt();
			insert_edge(x, y, directed);
		}
		sc.close();
	}

	public void reset(){
		for(int i = 1; i <= nvertices; i++){
			processed[i] = false;
			discovered[i] = false;
			parent[i] = -1;
		}
		finished = false;
		time = 0;
	}
}