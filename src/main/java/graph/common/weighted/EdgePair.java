package graph.common.weighted;

import java.util.*;

//undirected
public class EdgePair implements Comparable<EdgePair> {
	public int x;
	public int y;
	public int w;

	// indicator to tell if this edge is mst-edge
	public boolean mst;
	public EdgePair(int x, int y, int w){
		this.x = x;
		this.y = y;
		this.w = w;
	}

	@Override
	public boolean equals(Object another) {

		if(another instanceof EdgePair){
			EdgePair epair2 = (EdgePair)another;
			return (w == epair2.w) && (mst == epair2.mst) && (
				(x == epair2.x) && (y == epair2.y) ||
				(x == epair2.y) && (y == epair2.x)
				) ;
		}
		return false;
	}

	@Override
	public int compareTo(EdgePair t2){
		return this.w - t2.w;
	}

	@Override
	public int hashCode () {
		int u, v;
		if(x < y) {
			u = x; v = y;
		} else {
			u = y; v = x;
		}
		return new StringBuilder(u).append(v).append(w).append(mst).toString().hashCode();
	}

	@Override
	public String toString() {
		return new StringBuilder().append(x).append(" -> ").append(y).append(" w = ").append(w).append("; mst= ").append(mst).toString();
	}
}