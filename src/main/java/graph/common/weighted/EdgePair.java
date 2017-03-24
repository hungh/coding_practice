package graph.common.weighted;

import java.util.*;

public class EdgePair implements Comparable<EdgePair> {
	public int x;
	public int y;
	public int w;
	public EdgePair(int x, int y, int w){
		this.x = x;
		this.y = y;
		this.w = w;
	}

	@Override
	public boolean equals(Object another) {
		if(another instanceof EdgePair){
			EdgePair epair2 = (EdgePair)another;
			return (x == epair2.x) && (y == epair2.y) && (w == epair2.w);
		}
		return false;
	}

	@Override
	public int compareTo(EdgePair t2){
		return this.w - t2.w;
	}

	@Override
	public int hashCode () {
		return new StringBuilder(x).append(y).toString().hashCode();
	}
}