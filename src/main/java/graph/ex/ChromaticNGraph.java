/*
Exercise 5-4 - Give a linear algorithm to compute the chromatic number of graphs where each vertex has degree at most 2.
Must such graph be bipartite?
*/


package graph.ex;

import ds.Common;
import graph.apps.ColorGraph;
import graph.common.GraphAL;

public class ChromaticNGraph extends ColorGraph { //TODO: Please see Euler (V - E + F = 2 for planar graph)
	private static final int MAX_COLOR = 6;
	protected int[] colors = new int[GraphAL.MAXV + 1];
	protected int[] color_types = new int[MAX_COLOR]; // planar graph does not have more than 6

	public ChromaticNGraph(boolean directed){
		super(directed);
	}
	public static void main(String[] args){
		Common.log("Computing...");
		ChromaticNGraph graph = new ChromaticNGraph(true);
		graph.read_graph("/graph5D12.txt");
		graph.compute();
		graph.printColors();
	}

	public void compute(){
		for(int i = 1; i < nvertices; i++){
			if(!discovered[i]) {
				color[i] = color_types[0]; 
				bfs(i);
			}
		}
	}

	public void init() {
		int i;		
		for(i = 1; i < MAX_COLOR; i++) color_types[i] = -1;
		for(i = 1; i <= nvertices; i++) color[i] = UNCOLORED;
	}


	@Override
	public void process_edge(int x, int y){
		if(color[x] == color[y]){  
			// introduce a new color
			if(color[x] < color_types.length - 1){
				color[y] = color[x] + 1;
				color_types[color[y]] = color[y];	
			} else{
				Common.log("Unable to use an extra color");
			}			
		}else{
			// pick up the lowest color that has not been picked by x
			for(int i = 0; i < MAX_COLOR; i++) 
				if(i != color[x]) {
					color[y] = i;
					return;
				}
		}
	}

	public void printColors(){
		for(int i = 1; i <= nvertices; i++) {
			Common._log ("vertext [" + i + "] color=" + color[i] + ", ");
		}
	}
}