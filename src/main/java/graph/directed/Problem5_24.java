/*
5-24. [3] Adding a single directed edge to a directed graph can reduce the number of
weakly connected components, but by at most how many components? What about
the number of strongly connected components?
*/

package graph.directed;

public class Problem5_24 {
	/*
	1. If the vertex is not connected back to any body and it is connected from another SCC (Strongly Connected Component), then
	adding an edge from that vertex back to the oldest vertex of the SCC will reduce the number of SCCs by 1.

	2. IF the vertex is part of a chain of direction ( 1->2 -> 3 .. ->N and 1 is not part of any other SCCs), connecting N to 1
	will reduce the number of SCC by 1 from +N.

	3. Like #2 but 1 is part of a SCC, connecting N to 1 will reduce the number of SCCs by 2.
	*/

}