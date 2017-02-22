/*
5-16. [5] An independent set of an undirected graph G = (V,E) is a set of vertices U
such that no edge in E is incident on two vertices of U.
(a) Give an efficient algorithm to find a maximum-size independent set if G is a
tree.
(b) Let G = (V,E) be a tree with weights associated with the vertices such that
the weight of each vertex is equal to the degree of that vertex. Give an efficient
algorithm to find a maximum independent set of G.
(c) Let G = (V,E) be a tree with arbitrary weights associated with the vertices.
Give an efficient algorithm to find a maximum independent set of G.
*/

package graph.analysis;

import java.util.*;
import ds.Common;
import graph.apps.ColorGraph;
import graph.common.Color;

public class IndepCoverGraph extends ColorGraph {
	private int oneColor;
	private int sumBlk;
	private int oneSideWeight;
	private int totalWeight;

	public static void main(String[] args) {
		
		IndepCoverGraph g = new IndepCoverGraph(true);

		g.read_graph("/indep_cover_graph.txt");
		g.print_graph();
		boolean isBip = g.twocolor();
		if(isBip){
			List<Integer> iset = g.getMaxIndepSet();
			Common.log("Found: " + iset);				

			List<Integer> isetw = g.getMaxIndepSetbyWeight();
			Common.log("Found (w): " + isetw);				

		}else {
			Common.log("ERROR: Cannot find the independent set.");
		}

	}

	public IndepCoverGraph(boolean _directed){
		super(_directed);
	}

	@Override
	public void process_edge(int x, int y){
		super.process_edge(x, y);
		if(color[y] == Color.BLACK.toInt()) sumBlk++;
	}

	public List<Integer> getMaxIndepSet(){
		oneColor = (sumBlk > (nvertices - sumBlk)) ? Color.BLACK.toInt() : Color.WHITE.toInt();
		List<Integer> ret = new ArrayList<Integer>();
		for(int i = 1; i <= nvertices; i++) {
			if(color[i] == oneColor) {
				ret.add (i);
				oneSideWeight += degree[i];
			} 
			totalWeight += degree[i];
		}

		return ret;
	}

	public List<Integer> getMaxIndepSetbyWeight () {
		this.populateVertexDegrees();
		List<Integer> indepSetNoWeight = this.getMaxIndepSet();
		if(oneSideWeight > totalWeight - oneSideWeight){
			return indepSetNoWeight;
		}else{
			int otherColor = complement(oneColor);
			List<Integer> ret = new ArrayList<Integer>();
			for(int i = 1; i <= nvertices; i++)
				if(color[i] == otherColor) ret.add(i);
			return ret;
		}
	}
}