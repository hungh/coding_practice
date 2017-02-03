/*
Graph using incident matrix
*/

package graph.common;

import java.util.*;

import ds.Common;
import graph.common.Graph;

/*
Complexity O(V * E)
*/
public class GraphIM extends Graph {
	protected int[][] m;

	public GraphIM(){
		this(false); // for now, only support undirected graph
	}

	public GraphIM (boolean directed){
		this.directed = directed;
		this.nedges = 1;
	}

	public static void main(String[] args){
		GraphIM g = new GraphIM();
		g.read_graph("/graph5D12.txt");
		g.print_graph();

		Common.log("**** Breath first search");
		g.bfs(1);

		g.reset();
		Common.log("**** Depth first search");
		g.dfs(1);

	}

	@Override
	public void insert_edge(int x, int y, boolean _directed){
		if(m == null){
			Common.log("init matrix");
			m = new int[nvertices + 1][Graph.MAXV + 1]; 
		}
		m[x][nedges] = 1;
		m[y][nedges] = 1;
		nedges++;
	}

	@Override
	public void print_graph(){
		for(int i = 1; i <= nvertices; i++){
			for(int j = 1; j <= this.getProvided_nedges(); j++){
				Common._log(" " +  m[i][j]);
			}
			Common.log("");
		}
	}

	@Override
	public void bfs(int start){
		LinkedList<Integer> q = new LinkedList<Integer>();
		int i,
		v, // current vertex
		y, // successor vertex		
		edge; // currently examined edge
		for(i = 1; i <= nvertices; i++) parent[i] = -1;

		q.push(start);
		discovered[start]= true;

		while(! q.isEmpty()){
			v = q.pop();
			process_vertex_early(v);
			processed[v] = true;			
		
			edge = 1;
			while(edge <= this.getProvided_nedges()){				
				if( m[v][edge] == 0 ) { edge++; continue;}

				y = 1;
				// get the connected vertex				
				while(y <= nvertices &&  ( (m[y][edge] ==  0) || (y == v))) y++;

				if(! processed[y] || directed) process_edge(v, y);
				if(! discovered[y]){
					q.push(y);
					discovered[y] = true;
					parent[y] = v;
				}	
				edge++;			
			}
			process_vertex_late(v);
		}
	}

	@Override
	public void dfs(int v){
		int y;
		int edge = 1;

		if(finished) return;
		discovered[v] = true;
		time++;
		entry_time[v] = time;
		process_vertex_early(v);

		while(edge <= this.getProvided_nedges()){
			if( m[v][edge] == 0 ) { edge++; continue;}
			y = 1;
			while(y <= nvertices &&  ( (m[y][edge] ==  0) || (y == v))) y++;

			if(!discovered[y]){
				parent[y] = v;
				process_edge(v, y);
				dfs(y);
			} else if(!processed[y] || directed){
				process_edge(v, y);
			}
			if(finished) return;
			edge++;
		}
		process_vertex_late(v);
		time++;
		exit_time[v] = time;
		processed[v] = true;

	}


	@Override
	public void process_vertex_late(int v){}

	@Override
	public  void process_vertex_early(int v){}

	@Override
	public void process_edge(int x, int y){
		Common.log(" \t\t processing edge (" + x + "," + y + ")");
	}

	@Override
	public void hard_reset(){}
}

