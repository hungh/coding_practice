/*

5-23. [5] Your job is to arrange n ill-behaved children in a straight line, facing front. You
are given a list of m statements of the form “i hates j”. If i hates j, then you do not
want put i somewhere behind j, because then i is capable of throwing something
at j.

	(a) Give an algorithm that orders the line, (or says that it is not possible) in
		O(m + n) time.

	(b) Suppose instead you want to arrange the children in rows such that if i hates
		j, then i must be in a lower numbered row than j. Give an efficient algorithm
		to find the minimum number of rows needed, if it is possible.
*/


package graph.directed;

import ds.Common;
import graph.common.*;

public class Problem5_23 extends GraphAL {
	public static void main(String[] args){}

	public Problem5_23 (boolean directed) {
		super(directed);
	}
}