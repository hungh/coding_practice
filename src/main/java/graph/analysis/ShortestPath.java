/*
5-21 [6] Let v and w be two vertices in a directed graph G = (V, E). Design a linear time 
algorithm to find the number of different shortest paths (not neccessarily vertex disjoint)
between v and w. Note: the edges in G are unweighted.
*/

package graph.analysis;

import java.util.*;
import ds.Common;
import graph.common.*;

public class ShortestPath extends GraphAL {
	public ShortestPath(boolean directed) {
		super(directed);
	}

	public static void main(String[] args){
		ShortestPath g = new ShortestPath(true);
		g.read_graph("/shortest_path1.txt");
		g.print_graph();
		g.find_shortest_path();
	}

	public void find_shortest_path() {

	}
}