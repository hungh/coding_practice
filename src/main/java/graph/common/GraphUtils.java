package graph.common;

public class GraphUtils {

	// return a new graph
	public static GraphAL getNewEmptyGraph(int nvertices, boolean weighted, boolean directed) {
		GraphAL newGraph = new GraphAL (directed);
		newGraph.setWeighted(weighted);
		newGraph.setNVertices(nvertices);
		return newGraph;
	}
}