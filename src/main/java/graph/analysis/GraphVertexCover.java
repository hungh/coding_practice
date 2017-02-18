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

	protected int[] vweights;

	public GraphVertexCover(boolean directed){
		super(directed);
		vweights = new int [GraphAL.MAXV + 1];
	}

	public static void main(String[] args){
		GraphVertexCover g = new GraphVertexCover(true); // assume that we are using direct graph for parent ->child direction only
		g.read_graph("/graphtree.txt"); //graphtree_sim.txt  (for simple case)
		g.print_graph();
		List<Integer> cover_s = new ArrayList<Integer>();
		g.getMinCoverByWeight(1, cover_s); //g.getMinCover(1);
		Common.log("");
		if(cover_s != null)
			for(Integer e: cover_s) Common._log(" " + e);
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

	public boolean getMinCoverByWeight(int node, List<Integer> rs){
		// queue to weigh to compare against parent
		LinkedList<Integer> weighing_queue = new LinkedList<Integer>();
		EdgeNode adj = edges [node];
		boolean isSelected;
		int children_weights = 0;
		while (adj != null) {
			if( edges[adj.y] == null )  //leaf
				weighing_queue.add (adj.y);
			else 
				if(!getMinCoverByWeight(adj.y, rs)) weighing_queue.add (adj.y); // if the node is not selected, add it into weighing
			adj = adj.next;			
		}
		for(int w: weighing_queue) children_weights += vweights[w];
		if(children_weights < vweights[node]) {
			// select all leaves
			for(int leaf : weighing_queue) rs.add(leaf);			
			return false;  // the node was not selected
		}else {
			rs.add(node);
			return true; // was selected
		}
	}

	@Override
	public void readExtraData(Scanner sc) {
		int i = 0;
		while(sc.hasNext()) {
			vweights[++i] = sc.nextInt();
		}
	}
}