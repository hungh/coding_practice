/* Network flows algorithm */

package graph.weighted;

import ds.Common;
import graph.common.*;
import graph.common.weighted.*;

public class NetworkFlows extends GraphAL {
	public static void main(String[] args){
		int s = 1; 
		int t = 4;
		NetworkFlows g = new NetworkFlows(true, false);
		g.read_graph("/networkflows.txt"); // see Figure 6.13 (Page 219)
		g.print_graph();
		g.netflow(s, t);
	}

	public void init() {
		this.edges = new EdgeNodeR[MAXV + 1]; 
	}

	public NetworkFlows(boolean weighted, boolean directed){
		super(directed);
		this.weighted = weighted;
	}

	// initialize the residual flow of (i, j) = the capacity of edge i-j
	// and the residual flow of (j, i) = 0	
	@Override
	public void insert_edge(int x, int y, Integer z, boolean _directed){ // z: weight of the edge
		EdgeNodeR p = new EdgeNodeR();
		p.y = y;
		p.weight = Math.abs(z); // capacity

		if(z > 0) p.residual = z;
		p.next = (EdgeNodeR)edges[x]; // insert at the head of adjacency list
		edges[x] = p;

		if(!_directed) { // need another direction for undirected graph
			insert_edge(y, x, -z, true);
		}else{
			// count the number edge
			nedges++;
		}
	}

	/* calculate the maximum netflows by using minimum cut */
	public void netflow(int source, int sink){
		int volume;  // weight of the augmenting path
		
		reset();
		bfs(source);

		volume = path_volume(source, sink, parent);

		while(volume > 0) {
			augment_path(source, sink, parent, volume);
			reset();
			bfs(source);
			volume = path_volume(source, sink, parent);
		}
	}

	public int path_volume(int start, int end, int[] parents){
		if(parents[end] == -1) return 0;

		EdgeNodeR e = find_edge(parents[end], end);

		if(start == parents[end])  // start is the parent of end, just return the residual of the edge from start to end
			return e.residual;
		else
			// keep finding the min residual - for the graph in between start end parent of end
			return Math.min (path_volume(start, parents[end], parents), e.residual);
		
	}

	public EdgeNodeR find_edge (int x, int y){
		EdgeNode p = edges [x];

		while(p != null){
			if(p.y == y) return (EdgeNodeR)p;
			p = p.next;
		}
		Common.log("[ERROR] cannot find edge " + x + " -> " + y);
		return null;
	}

	public void augment_path(int start, int end, int[] parents, int volume) {
		if(start == end) return; // there is nothing in between to augment

		EdgeNodeR e = find_edge(parents[end], end); // think of it as a reverse flow moving from end to start
		                                            // until we reach the source it stops
		e.flow += volume;
		e.residual -= volume;

		// the volume is the residual of the reverse edge
		e = find_edge (end, parents[end]);
		e.residual += volume;

		augment_path(start, parents[end], parents, volume); // recursively augment path for the parent  of end (towards the source)
	}

}
