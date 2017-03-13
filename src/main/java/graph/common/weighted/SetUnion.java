package graph.common.weighted;

import ds.Common;

public class SetUnion {
	public static final int SET_SIZE = 1000;
	public int[] p = new int[SET_SIZE + 1];  // parent elements
	public int[] size = new int[SET_SIZE + 1]; // number of  elements in subtree i
	public int n; // number of elements in set


	// return the root of x
	public static int find(SetUnion s, int x){
		if(s.p[x] == x) return x;
		return find(s, s.p[x]);
	}

	public static void union_sets(SetUnion s, int s1, int s2){
		int r1, r2;

		r1 = find(s, s1);
		r2 = find(s, s2);

		if(r1 == r2) return; // s1 and s2 are coming from the same root. (same set)

		if(s.size[r1] >= s.size[r2]){
			s.size[r1] += s.size[r2];
			s.p[r2] = r1;
		}else {
			s.size[r2] += s.size[r1];
			s.p[r1] = r2;
		}

	}

	public void printSet(){
		Common.log("-- SET --");
		for(int i = 1; i <= n; i++){
			Common.log("i = " + i + ";p = " + p[i] + "; size = " + size[i]);
		}
	}

	public static boolean same_component(SetUnion s, int s1, int s2){
		return (find(s, s1) == find(s, s2));
	}

}