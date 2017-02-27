/*
5-21 [6] Let v and w be two vertices in a directed graph G = (V, E). Design a linear time 
algorithm to find the number of different shortest paths (not neccessarily vertex disjoint)
between v and w. Note: the edges in G are unweighted.
*/

package graph.analysis;

import java.util.*;
import ds.Common;
import graph.apps.TopoSorting;
import graph.common.*;

public class ShortestPath extends TopoSorting {
	private static final int INF = Integer.MAX_VALUE;

	protected List[] source;
	protected int[] distance;

	public ShortestPath(boolean directed) {
		super(directed);
	}

	public static void main(String[] args){
		ShortestPath g = new ShortestPath(true);
		g.read_graph("/shortest_path1.txt"); // shortest_path2.txt  from 2 -> 9
		g.print_graph();
		g.find_shortest_path();
		g.printSources(1, 10);
	}

	// this must be called after the graph is read
	public void init () {
		source = new List[nvertices + 1];
		distance = new int[nvertices + 1];
		distance[1] = 0;
		for(int i = 2; i <= nvertices; i++) {
			distance[i] = INF;
		}
		this.topsort(); // topo sorting the DAG
	}

	public void find_shortest_path() {
		this.init();

		EdgeNode p; int i;
		List<Integer> s;
		while(! sorted.isEmpty()) {

			i = sorted.pop();
			p = edges[i];

			while(p != null){
				s = source[p.y];

				if(distance[p.y] >= distance[i] + 1) {
					distance[p.y] = distance[i] + 1;
					if(s == null){
						s = new ArrayList<Integer>();
					}
					s.add (i);
					source[p.y] = s;
				}
				p = p.next;
			}
		}
	}

	public void printSources (int s, int e) {
		Common._log(" " + e);		
		List<Integer> next = source[e];
		if(next == null) return;
		if(next.size()> 1) Common._log(" split ");
		for(Integer i : next)
			if(i != s) printSources(s, i); else Common._log(" " + s);
	}
}