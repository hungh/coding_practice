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

	public int getMinCoverByWeight(int node, List<Integer> rs){
		LinkedList<Integer> queue_leaf = new LinkedList<Integer>();
		EdgeNode adj = edges [node];
		int nonleaf;
		int lweight = 0;
		while (adj != null) {
			if( edges[adj.y] == null ) { //leaf
				Common.log("adding leaf:" + adj.y);
				queue_leaf.add (adj.y);
				Common.log("\t Queue_after_adding_leaf:" + queue_leaf);
			}else {
				Common.log("exploring cnode:" + adj.y);
				nonleaf = getMinCoverByWeight(adj.y, rs);	
				if(nonleaf > 0) {
					vweights [adj.y] = nonleaf;
					queue_leaf.add (adj.y);
				}
			}
			lweight += vweights[adj.y];
			adj = adj.next;			
		}
		Common.log("Queue:" + queue_leaf);
		if(queue_leaf.size() < vweights[node]) {
			// select all leaves
			for(int leaf : queue_leaf) {
				rs.add(leaf);			
				// TODO: remove leaf's children from 'rs'
			}
			return queue_leaf.size();
		}else {
			rs.add(node);
			return -1;
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