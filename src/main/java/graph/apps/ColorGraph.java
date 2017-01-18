
package graph.apps;

import graph.common.GraphAL;
import ds.Common;


public class ColorGraph extends GraphAL {
	public static final int WHITE = 0;
	public static final int BLACK = 1;
	public static final int UNCOLORED = -1;

	private int[] color = new int[GraphAL.MAXV + 1];
	private boolean bipartite = true;

	public static void main(String[] args){
		ColorGraph graph = new ColorGraph(false);
		if(args.length > 0){
			graph.read_graph(args[0]);
		}else{
			graph.read_graph();	
		}		
		Common.log(graph.twocolor());
	}

	public ColorGraph(boolean directed){
		super(directed);		
	}

	public boolean twocolor(){
		for(int i = 1; i <= nvertices; i++) color[i] = UNCOLORED;
		for(int i = 1; i < nvertices; i++){
			if(!discovered[i]) {
				color[i] = WHITE; 
				bfs(i);
			}
		}
		return bipartite;
	}

	@Override
	public void process_edge(int x, int y){
		if(color[x] == color[y]){  
			bipartite = false;
			Common.log("Warning: not bipartite due to (" + x + "," + y);
		}else{			
			color[y] = complement(color[x]);	
		}
	}

	public boolean isBipartite() {
		return bipartite;
	}

	private static int complement(int colorx){
		if(colorx == WHITE) return BLACK;
		if(colorx == BLACK) return WHITE;
		return UNCOLORED;
	}
}