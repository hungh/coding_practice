/* Kruskal's Minimum Spanning Implementation in Java (C code from Skiena book) */

package graph.weighted;

import java.util.*;
import ds.Common;
import graph.common.*;
import graph.common.weighted.SetUnion;

public class KruskalsMST extends GraphAL {
	public static void main(String[] args){

	}

	// n: the number of elements in the set su
	public void init_set_union(SetUnion s, int n) {
		for(int i = 1; i <= n; i++) {
			s.p[i] = i;
			s.size[i] = 1;
		}
		s.n = n;
	}

	public KruskalsMST(boolean directed){
		super(directed);
	}


}