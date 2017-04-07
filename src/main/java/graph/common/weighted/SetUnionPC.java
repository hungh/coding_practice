package graph.common.weighted;

import ds.Common;
/*
Set Union using path compression and link-by-rank
Ref: https://www.cs.princeton.edu/courses/archive/spring13/cos423/lectures/UnionFind.pdf
*/
public class SetUnionPC {
	public static final int SET_SIZE = 1000;
	public int[] p = new int[SET_SIZE + 1];  // parent elements
	public int[] rank = new int[SET_SIZE + 1]; // rank of  elements in subtree i
	public int n; // number of elements in set


	// return the root of x
	public static int find(SetUnionPC s, int x){
		if(s.p[x] == x)return x;
		return find(s, s.p[x]);
	}

	public static void update_root(SetUnionPC s, int x, int root){
		int old_parent = s.p[x];
		if(old_parent != root){
			s.p[x] = root;
			update_root(s, old_parent, root);
		}
	}

	// return positive if s1 is parent if s2, false otherwise, zero if same
	public static int union_sets(SetUnionPC s, int s1, int s2){
		int r1, r2;

		r1 = find(s, s1);
		update_root(s, s1, r1);
		r2 = find(s, s2);
		update_root(s, s2, r2);

		if(r1 == r2) return 0; // s1 and s2 are coming from the same root. (same set)

		if(s.rank[r1] > s.rank[r2]){			
			s.p[r2] = r1;
			return 1;
		}else if (s.rank[r1] < s.rank[r2]){			
			s.p[r1] = r2;
			return -1;
		} else{
			s.rank[r1] += 1;
			s.p[r2] = r1;
			return 1;
		}

	}

	public static boolean same_component(SetUnionPC s, int s1, int s2){
		return (find(s, s1) == find(s, s2));
	}

	@Override
	public String toString(){
		StringBuilder b = new StringBuilder();
		for(int i = 1; i <= n; i++)
			b.append("parent_of [").append(i).append("] =").append(p[i]).append("; rank=").append(rank[i]).append('\n');
		return b.toString();
	}

}