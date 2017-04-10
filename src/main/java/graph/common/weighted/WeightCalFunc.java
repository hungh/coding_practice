package graph.common.weighted;

import graph.common.EdgeNode;

public interface WeightCalFunc {
	void calculate(int v, EdgeNode p, int[] distance, int[] parent, int[] vweights);
}