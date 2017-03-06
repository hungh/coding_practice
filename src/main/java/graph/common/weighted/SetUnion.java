package graph.common.weighted;

public class SetUnion {
	public static final int SET_SIZE = 1000;
	public int[] p = new int[SET_SIZE + 1];  // parent elements
	public int[] size = new int[SET_SIZE + 1]; // number of  elements in subtree i
	public int n; // number of elements in set

}