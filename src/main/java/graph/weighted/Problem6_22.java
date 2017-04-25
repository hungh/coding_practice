/*
6-22. [5] Let G = (V,E) be a directed weighted graph such that all the weights are
	positive. Let v and w be two vertices in G and k ≤ |V | be an integer. Design an
	algorithm to find the shortest path from v to w that contains exactly k edges. Note
	that the path need not be simple.
*/
package graph.weighted;

import java.util.*;
import ds.Common;

import graph.common.*;
import graph.common.weighted.*;

// Reference from : https://www.cs.cmu.edu/~ckingsf/bioinfo-lectures/shortnegpath.pdf
public class Problem6_22  extends GraphAL {
	protected int[][] opt; // opt[i, w] optimal weight to w that has at most i edges
	protected int[][] sw; // sw[t, i] shortes weights from source to t with at most i edges
	public static void main(String[] args){

	}

	public Problem6_22(boolean weighted, boolean directed) {
		super(directed);
		this.weighted = weighted;
	}

	public void init_local() {
		opt = new int[nvertices + 1][nvertices + 1];
		sw = new int[nvertices + 1][nvertices + 1];
	}


	public void shortest_path_k (int u, int v, int k) {
		int best_w; // smallest weight from u -> v that has at most i edges
		EdgeNode p;
		for(int i = 1; i <= nvertices; i++) {
			for(int j = 1; j <= nvertices; j++){
				best_w = Integer.MAX_VALUE;
				p = edges[j];
				while(p != null) best_w = Math.min (best_w, opt[i-1][j] + p.weight);
				sw[j][i] = Math.min(best_w, opt[j][i - 1]);
			}
		}
	}

	public List<Integer> print_spath(int s, int t){
		List<Integer> sp = new ArrayList<Integer>();
		return sp;
	}

}