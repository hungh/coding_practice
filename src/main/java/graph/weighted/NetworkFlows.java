/* Network flows algorithm */

package graph.weighted;

import ds.Common;
import graph.common.*;
import graph.common.weighted.*;

// NOTE: not completed ..
public class NetworkFlows extends GraphAL {
	public static void main(String[] args){
		int s = 1; 
		int t = 6;
		NetworkFlows g = new NetworkFlows(true, false); // directed = false but make sure the input file has direction of flow
		g.read_graph("/networkflows.txt"); // Graph taken from : https://web.stanford.edu/class/cs97si/08-network-flow-problems.pdf
		g.print_graph();
		int max_flow = g.netflow(s, t);		
		Common.log("max_flow=" + max_flow); //
	}

	@Override
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

		if(z < 0){
			 p.residual = -z; // capacity = 0, residual = |z|
			 Common.log("inserting residual from " + x + " -> " + y + ": " + (-z));
		} else {
			p.weight = z; // capacity = z, flow = 0
			p.residual = z;
		}

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
	public int netflow(int source, int sink){
		int volume;  // weight of the augmenting path
		int max_flow = 0;

		reset();
		bfs(source);
		volume = path_volume(source, sink, parent);
		Common.log("volume1=" + volume);
		max_flow += volume;
		while(volume > 0) {
			// push flow of a non-zero volume to the augment path from source to sink 
			augment_path(source, sink, parent, volume);
			reset();
			bfs(source);
			volume = path_volume(source, sink, parent); // the returned volume is the min the augment path (for all edges on that path)
			max_flow += volume;
			Common.log("volume2=" + volume);
		}
		
		return max_flow;
	}
	// Find the minimum residual of path from start to end
	// Returns the minimum of all residual values on the path from start to end if there is one.
	// that means we can push at most this minimum into the path 
	public int path_volume(int start, int end, int[] parents){
		Common.log("end: " + end + ";parents[end]: " + parents[end]);
		// there is no such path from start to end, return zero
		if(parents[end] == -1) {
			Common.log("Cannot find path from " + start + " -> " + end);
			return 0;
		}

		EdgeNodeR e = find_edge(parents[end], end);

		if(start == parents[end]) { // (back edge or residual edge): this edge only has residual value
			Common.log("saw a back edge at vertex from  " + parents[end] + " -> " + end + "; with residual=" + e.residual);
			return e.residual;
		} else {
			// keep finding the min residual - for the graph in between start end parent of end
			return Math.min (path_volume(start, parents[end], parents), e.residual);
		}
		
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
		e.weight -= e.flow; // capacity reduce by flow
		e.residual -= volume;
	

		// the volume is the residual of the reverse edge
		e = find_edge (end, parents[end]);
		e.residual += volume;

		Common.log("augmenting " + parents[end] + " -> " + end);
		augment_path(start, parents[end], parents, volume); // recursively augment path for the parent  of end (towards the source)
	}

}
