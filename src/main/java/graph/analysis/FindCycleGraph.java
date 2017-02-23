/*
5-17. [5] Consider the problem of determining whether a given undirected graph G =
(V,E) contains a triangle or cycle of length 3.
(a) Give an O(|V|^3) to find a triangle if one exists.
(b) Improve your algorithm to run in time O(|V |·|E|). You may assume |V | ≤ |E|.
Observe that these bounds gives you time to convert between the adjacency matrix
and adjacency list representations of G.
*/

package graph.analysis;

import java.util.*;
import ds.Common;
import graph.apps.GraphCyc;
import graph.common.*;

public class FindCycleGraph extends GraphCyc {

	public static void main(String[] args){
		FindCycleGraph g = new FindCycleGraph(false);
		g.read_graph("/find_cycle_graph.txt");  // or find_cycle_graph2.txt
		g.print_graph();
		g.findTriangleV3();

		Common.log("--------------------");
		g.findTriangleVE();
	}

	public FindCycleGraph(boolean directed) {
		super(directed);
	}

	// O(|V| ^ 3)
	public void findTriangleV3(){
		int[] proc_t = new int[nvertices + 1];
		EdgeNode p, q;
		Map<Integer, Object> cache = new HashMap<Integer, Object>();
		for(int i = 1; i <= nvertices; i++) {
			p = edges[i];			
			while(p != null){
				cache.put(p.y, null);
				p = p.next;
			} 
			cache.put(i, null);
			p = edges[i];
			while(p != null) {
				q = edges[p.y];				

				while(q != null){
					if( !(proc_t[i] == 1 &&  proc_t[q.y]== 1 && proc_t[p.y] == 1) && (i != q.y) && cache.containsKey(q.y)) {
						Common.log("Found a triangle at (" + i + ", " + q.y + ", " + p.y + ")");
						proc_t[i] = 1;
						proc_t[q.y] = 1;
						proc_t[p.y] = 1;
					}
					q = q.next;
				}
				p = p.next;				
			}
			cache.clear();
		}
	}
	// being asked O(V * E)
	// solution: O(V^2)
	public void findTriangleVE(){
		GraphConvertor convertor = new GraphConvertor(directed);
		GraphAM gim = convertor.convertGalToAm(this);
		int[][] m = gim.getMatrix();
		int i, j;
		Map<Integer, List<Integer>> rmap = new HashMap<Integer, List<Integer>> ();
		Map<Integer, List<Integer>> cmap = new HashMap<Integer, List<Integer>> ();
		// fold the square matrix at the diagonal
		// and remove cells that are not symmetric to the other ones across the diagonal
		for(i = 1; i <= nvertices; i++) {
			for(j = i + 1; j <= nvertices; j++) {
				if(m[i][j] != m[j][i]) { // ignore the diagonal
					m[i][j] = 0;
					m[j][i] = 0;
				}else if(m[i][j] == 1) {
					put(rmap, i, j);
					put(cmap, j, i);
				}
			}
		}

		debug(m);

		// look for triangles - a '1' cell where its row and col of which has only one '1'
		int ridx;
		List<Integer> rows;
		List<Integer> cols;
		for(Map.Entry<Integer, List<Integer>> e : rmap.entrySet()) {
			ridx = e.getKey();
			rows = e.getValue();
			if(rows != null && rows.size() == 2) {
				for(Integer cidx: rows){					
					cols = cmap.get(cidx);
					cols.remove(Integer.valueOf(ridx));
					if(cols.size() == 1) Common.log("* Triangle:" + rows + ", " + ridx);
				}
			}
		}

	}

	private void put(Map<Integer, List<Integer>> rmap, int i, int j){
		List<Integer> l = rmap.get(i);
		if(l == null) {
			l = new LinkedList<Integer>();
			rmap.put(i, l);
		}
		rmap.get(i).add(j);

	}

	private void debug(int[][] m) {
		for(int i = 1; i <= nvertices; i++){
			for(int j = 1; j <= nvertices; j++){
				Common._log(" " + m[i][j]);
			}
			Common.log("");
		}
	}
}