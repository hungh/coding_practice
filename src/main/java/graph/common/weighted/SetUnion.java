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

	// return positive if s1 is parent if s2, false otherwise, zero if same
	public static int union_sets(SetUnion s, int s1, int s2){
		int r1, r2;

		r1 = find(s, s1);
		r2 = find(s, s2);

		if(r1 == r2) return 0; // s1 and s2 are coming from the same root. (same set)

		if(s.size[r1] >= s.size[r2]){
			s.size[r1] += s.size[r2];
			s.p[r2] = r1;
			return 1;
		}else {
			s.size[r2] += s.size[r1];
			s.p[r1] = r2;
			return -1;
		}

	}

	public static boolean same_component(SetUnion s, int s1, int s2){
		return (find(s, s1) == find(s, s2));
	}

	@Override
	public String toString(){
		StringBuilder b = new StringBuilder();
		for(int i = 1; i <= n; i++)
			b.append("parent_of [").append(i).append("] =").append(p[i]).append("; size=").append(size[i]).append('\n');
		return b.toString();
	}

}