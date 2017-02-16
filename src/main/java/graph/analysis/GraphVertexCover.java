/*
5-13. [5] A vertex cover of a graph G = (V,E) is a subset of vertices V
such that each edge in E is incident on at least one vertex of V

	(a) Give an efficient algorithm to find a minimum-size vertex cover if G is a tree.

	(b) Let G = (V,E) be a tree such that the weight of each vertex is equal to the
		degree of that vertex. Give an efficient algorithm to find a minimum-weight
		vertex cover of G.

	(c) Let G = (V,E) be a tree with arbitrary weights associated with the vertices.

	Give an efficient algorithm to find a minimum-weight vertex cover of G.
*/

package graph.analysis;

import java.util.*;

import ds.Common;
import graph.common.GraphAL;
import graph.common.EdgeNode;

public class GraphVertexCover extends GraphAL {
	protected Map<Integer, Object> coverMap = new HashMap<Integer, Object>();

	public GraphVertexCover(boolean directed){
		super(directed);
		coverMap.put(1, null);
	}

	public static void main(String[] args){
		GraphVertexCover g = new GraphVertexCover(true); // assume that we are using direct graph for parent ->child direction only
		g.read_graph("/graphtree.txt"); //graphtree_sim.txt  (for simple case)
		g.print_graph();
		List<Integer> cover_s = g.getMinCover(1);
		Common.log("");
		for(Integer e: cover_s) Common._log(" " + e);
	}

	// using dfs : not a correct solution
	@Override
	public void process_edge(int x, int y){
		if(! coverMap.containsKey(x) && !coverMap.containsKey(y) ) {
			coverMap.put(y, null);	
		}
	}

	public List<Integer> getMinCover(int start){
		List<Integer> rs = new ArrayList<Integer>();
		// make a copy of the original edges
		int i;
		Map<Integer, EdgeNode> edges_copy = new HashMap<Integer, EdgeNode>();
		EdgeNode tmp, h;
		for(i = 1; i <= nvertices; i++){
			tmp = edges[i];
			
			while(tmp != null){
				h = new EdgeNode(tmp.y);				
				h.next = edges_copy.get(i);
				edges_copy.put(i, h);
				tmp = tmp.next;
			}
		}	
		int u = start;
		EdgeNode vo;
		boolean shouldAddParent;
		while(!edges_copy.isEmpty()) {

			vo = edges_copy.get(u);
			shouldAddParent = (vo != null);

			while(vo != null) {				
				// dont add if it is a leaf, assume that a leaf does not connect back to its parent
				if(edges_copy.get(vo.y) != null) {
					rs.add(vo.y);
					shouldAddParent = false; // child covers parent
				}

				// remove all v's neighboring edges
				edges_copy.remove(vo.y);
				vo = vo.next;
			}				
			// removing all u's neighboring edges
			edges_copy.remove(u);
			if(shouldAddParent) rs.add(u);
			u++;
		}

		return rs;

	}

	public void printCoverVertices () {
		Common.log("** Cover vertices:");
		for(Integer e : coverMap.keySet()) Common._log(" " + e);
		Common.log("");
	}
}