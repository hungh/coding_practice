/*
Graph  implementation using Adjacency Matrix
*/

package graph.common;

import java.util.*;

import ds.Common;

/*
O(V * V)
*/
public class GraphAM  extends Graph {

	protected int[][] m;

	protected int[][] allocateMemoryAndInit(){
		if(nvertices < 1) throw new IllegalArgumentException("Invalid number of vertices.");
		return new int[nvertices + 1][nvertices + 1];
	}

	public static void main(String[] args){
		GraphAM g = new GraphAM(false);
		g.read_graph("/graph5D12.txt");
		g.print_graph();

		Common.log("\nBreath First Search...");
		g.bfs(1);		

		g.reset();
		Common.log("\nDepth First Search...");
		
		g.dfs(1);	
	}

	public GraphAM(boolean directed){
		this.directed = directed;
	}

	public int[][] getMatrix() {
		return this.m;
	}

	@Override
	public void insert_edge(int x, int y, boolean _directed){
		this.insert_edge(x, y, 1, _directed);
	}

	@Override
	public void insert_edge(int x, int y, Integer z, boolean _directed){
		if(m == null){
			m = allocateMemoryAndInit();
		}
		m[x][y] = z;

		if(!_directed) { // need another direction for undirected graph
			insert_edge(y, x, z, true);
		}else{			
			// count the number edge
			nedges++;
		}
	}

	@Override
	public void print_graph(){
		for(int i = 1; i <= nvertices; i++){
			for(int j = 1; j <= nvertices; j++){
				Common._log(" " +  m[i][j]);
			}
			Common.log("");
		}
	}

	public void print_graph_pretty(int[][] _m){
		for(int i = 1; i <= nvertices; i++){
			for(int j = 1; j <= nvertices; j++){
				if(_m[i][j] >= Integer.MAX_VALUE) Common._log(" f"); else Common._log(" " +  _m[i][j]);
			}
			Common.log("");
		}	
		Common.log("-------------------------");
		Common.log("* f: stands for +Infinite");
		Common.log("-------------------------");
	}

	@Override
	public void bfs(int start){
		LinkedList<Integer> q = new LinkedList<Integer>();
		int i,
		v, // current vertex
		y; // successor vertex		
		for(i = 1; i <= nvertices; i++) parent[i] = -1;

		q.push(start);
		discovered[start]= true;
		Common.log("discovered node :" + start);

		while(! q.isEmpty()){
			v = q.pop();
			process_vertex_early(v);
			processed[v] = true;			

			y = 1;
			while(y <= nvertices){				
				if( m[v][y] == 0 ) {
					y++;
					continue;
				}

				if(! processed[y] || directed) process_edge(v, y);
				if(! discovered[y]){
					Common.log("discovered node :" + y);
					q.push(y);
					discovered[y] = true;
					parent[y] = v;
				}
				y++;
			}
			process_vertex_late(v);
		}
	}

	@Override
	public void dfs(int v){
		int y = 1;

		if(finished) return;
		discovered[v] = true;
		Common.log("discovered node :" + v);
		time++;
		entry_time[v] = time;
		process_vertex_early(v);

		while(y <= nvertices){
			if(m[v][y] == 0){
				y++;	
				continue;
			} 

			if(!discovered[y]){
				parent[y] = v;
				process_edge(v, y);
				dfs(y);
			} else if(!processed[y] || directed){
				process_edge(v, y);
			}
			if(finished) return;
			y++;
		}
		process_vertex_late(v);
		time++;
		exit_time[v] = time;
		processed[v] = true;

	}

	@Override
	public void process_vertex_late(int v){
	}

	@Override
	public void process_vertex_early(int v){
	}

	@Override
	public void process_edge(int x, int y){
		Common.log(" \t\t processing edge (" + x + "," + y + ")");
	}

	public  void hard_reset() {
		m = null;
		for(int i = 1; i <= nvertices; i++) degree[i] = 0;
		reset();
	}
}
