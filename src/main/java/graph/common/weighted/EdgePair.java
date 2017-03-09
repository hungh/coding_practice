package graph.common.weighted;

import java.util.*;

public class EdgePair implements Comparator<EdgePair> {
	public int x;
	public int y;
	public int w;
	public EdgePair(int x, int y, int w){
		this.x = x;
		this.y = y;
		this.w = w;
	}

	public int compare(EdgePair t1, EdgePair t2){
		return t1.w - t2.w;
	}
}