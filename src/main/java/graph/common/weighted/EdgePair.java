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

	public int compareTo(EdgePair t2){
		return this.w - t2.w;
	}
}