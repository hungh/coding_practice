/*
Exercise 5-4 - Give a linear algorithm to compute the chromatic number of graphs where each vertex has degree at most 2.
Must such graph be bipartite?
*/


package graph.ex;

import java.util.*;

import ds.Common;
import graph.apps.ColorGraph;
import graph.common.Color;
import graph.common.GraphAL;
import graph.common.EdgeNode;

/*
Greedy Algorithm 
*/
public class ChromaticNGraph extends ColorGraph { //TODO: Please see Euler (V - E + F = 2 for planar graph) 
												  // http://web.math.princeton.edu/math_alive/5/Notes2.pdf
	private static final int MAX_COLOR = 6;
	protected int[] colors = new int[GraphAL.MAXV + 1];
	protected Map<Integer, Integer> vtxClrMap = new HashMap<Integer, Integer>() ; 

	public ChromaticNGraph(boolean directed){
		super(directed);
	}
	public static void main(String[] args){
		Common.log("Computing...");
		ChromaticNGraph graph = new ChromaticNGraph(false);
		graph.read_graph("/graph5D12.txt"); // this graph has degree of 5
		// graph.read_graph("/graph5d4.txt");  // degree at most 2
		graph.compute();
		graph.printColors();
		Common.log("Chromatic Number=" + graph.getChromaticNumber());
	}

	public void compute(){
		init();
		color[1] = Color.WHITE.toInt();
		bfs(1);
	}

	public void init() {
		super.init();
		for(int i = 1; i <= nvertices; i++) color[i] = Color.UNCOLORED.toInt();
	}
	
	@Override
	public void process_vertex_early(int v){
		vtxClrMap.clear();
		if(color[v] != Color.UNCOLORED.toInt()){
			vtxClrMap.put(v, color[v]);
		}		
	}

	@Override
	public void process_edge(int x, int y){						
		vtxClrMap.put(y, color[y]); // will color y later if it has not been colored yet.
	}

	@Override
	public void after_checking_aneighbor(int v, int y){
		if(color[y] != Color.UNCOLORED.toInt()) vtxClrMap.put(y, color[y]); // y was colored, update v's neighbors			
	}

	@Override
	public void process_vertex_late(int v) { 
		// color the uncolored vertices (after discovering all v's neighbors)
		int i;
		int vtx, clr;
		Map<Integer, Object> clrMap = new HashMap<Integer, Object>();		
		EdgeNode p;

		for(Map.Entry<Integer, Integer> kv: vtxClrMap.entrySet()){
			i = Color.WHITE.toInt();
			clrMap.clear();
			vtx = kv.getKey();
			clr = kv.getValue();

			if(clr == Color.UNCOLORED.toInt()) { // uncolored vertex				
				// look into v's neighbors' colors
				p = edges[vtx];
				while(p != null) {
					if(color[p.y] != Color.UNCOLORED.toInt()) clrMap.put(color[p.y] , null);
					p = p.next;
				}

				while(clrMap.containsKey(i)) i++;
				if(i >= MAX_COLOR) throw new IllegalArgumentException("Invalid map");
				color[vtx] = i; 
			}
		}
	}

	public void printColors(){
		for(int i = 1; i <= nvertices; i++) {
			Common.log ("vertext [" + i + "] = " + Color.stringValue(color[i]));
		}
	}

	public int getChromaticNumber(){
		int max = -1;
		for(int i = 0; i < MAX_COLOR; i++) {
			if(color[i] > max) max = color[i];
		}
		return max + 1;
	}
}