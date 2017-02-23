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
import graph.common.*;

public class FindCycleGraph extends GraphAL {

	public static void main(String[] args){
		FindCycleGraph g = new FindCycleGraph(false);
		g.read_graph("/find_cycle_graph.txt");
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
	// O(V * E)
	public void findTriangleVE(){
		GraphConvertor convertor = new GraphConvertor(directed);
		GraphAM gim = convertor.convertGalToAm(this);
		// gim.print_graph();
		int[][] m = gim.getMatrix();
		int i, j;
		// fold the square matrix at the diagonal
		// and remove cells that are not symmetric to the other ones across the diagonal
		for(i = 1; i <= nvertices; i++) {
			for(j = i + 1; j < nvertices; j++) {
				if(m[i][j] != m[j][i]) { // ignore the diagonal
					m[i][j] = 0;
					m[j][i] = 0;
				}
			}
		}
		
		// look for triangles 
		int tnodes;
		int one, two, three;
		for(i = 1; i <= nvertices; i++) {
			one = 0; two = 0; three = 0;
			tnodes = 0; 
			for(j = i + 1; j < nvertices; j++) {
				
				if(m[i][j] == 1) {
					if(one == 0) one = i; else three = i;
					tnodes++;
					// scan down
					for(int k = i; k <= j - 1; k++) {
						if(m [k][j] == 1) {
							tnodes++;
							two = k;	
							break;						
						}
					}
				}
				Common.log("_tnodes=" + tnodes);
				if(tnodes == 3){
					Common.log("found one = " + one  + ";two=" + two + ";three=" + three);
				}
			}
		}

	}
}