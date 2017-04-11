package graph.weighted;

/*
6-18. [5] In certain graph problems, vertices have can have weights instead of or in addition
to the weights of edges. Let Cv be the cost of vertex v, and C(x,y) the cost of
the edge (x, y). This problem is concerned with finding the cheapest path between
vertices a and b in a graph G. The cost of a path is the sum of the costs of the
edges and vertices encountered on the path.

	(a) Suppose that each edge in the graph has a weight of zero (while non-edges
		have a cost of ∞). Assume that Cv = 1 for all vertices 1 ≤ v ≤ n (i.e. , all
		vertices have the same cost). Give an efficient algorithm to find the cheapest
		path from a to b and its time complexity.

	(b) Now suppose that the vertex costs are not constant (but are all positive)
		and the edge costs remain as above. Give an efficient algorithm to find the
		cheapest path from a to b and its time complexity.

	(c) Now suppose that both the edge and vertex costs are not constant (but are
		all positive). Give an efficient algorithm to find the cheapest path from a to
		b and its time complexity.
*/
import java.util.*;
import ds.Common;
import graph.common.*;
import graph.common.weighted.*;

public class Problem6_18 extends Dijkstra {
	private static final boolean WEIGHTED = true;
	private static final boolean DIRECTED = false;	

	public Problem6_18(boolean directed) {
		super(WEIGHTED, DIRECTED);
	}

	public void reset_local() {
		for(int i = 1; i <= nvertices; i++) distance[i] = Integer.MAX_VALUE;
	}
	
	public void getShortestPathACase(int start, int end) {
		reset_local();
		bfs(start);
		int v = end;
		Common._log(" (*) Shortest path when each edge has 0 weight and each vertex has 1 weight: " + v + ' ');
		while(parent[v] != start){
			Common._log(" - " + parent[v]);
			v = parent[v];
		}
		Common.log(" - " + parent[v]);
		
	}

	// Cv = 1 for all 1 <= v <= nvertices
	// Cxy = 0 for all (xy) belonging to E
	// to solve (a)
	// the shortest path from a -> b is the smallest number of hops between a and b
	// we can solve this by using bfs
	public void process_edge(int x, int y){
		super.process_edge(x, y);
		if(distance[y] > distance[x] + 1) {
			distance[y] = distance[x] + 1;
			parent[y] = x;
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
		distance[start] = 0;

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

	public static void main(String[] args){
		Problem6_18 g = new Problem6_18(false);
		g.read_graph("/problem6_18.txt");
		g.print_graph();

		WeightCalFunc calFunc = new WeightCalFunc() {
			public void calculate(int v, EdgeNode p, int[] distance, int[] parent, int[] vweights){
				int w = p.y;
				int weight = p.weight;				
				if(distance[w] > (distance[v] + weight + vweights[w])) { 
					distance[w] = distance[v] + weight + vweights[w];    
					parent[w] = v; 					
				}
			}
		};

		g.dijkstra(1, calFunc);
		g.print_path(5);
		g.getShortestPathACase(1, 5);
	}

	@Override
	public void readExtraData(Scanner sc){
		vweights = new int[nvertices + 1];
		for(int i = 1; i <= nvertices; i++)
			vweights[i] = sc.nextInt();
	}
}