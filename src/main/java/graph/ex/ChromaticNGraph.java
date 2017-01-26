/*
Exercise 5-4 - Give a linear algorithm to compute the chromatic number of graphs where each vertex has degree at most 2.
Must such graph be bipartite?
*/


package graph.ex;

import ds.Common;
import graph.apps.ColorGraph;

public class ChromaticNGraph extends ColorGraph { //TODO: Please see Euler (V - E + F = 2 for planar graph)

	public ChromaticNGraph(boolean directed){
		super(directed);
	}
	public static void main(String[] args){
		Common.log("Computing...");
	}
}