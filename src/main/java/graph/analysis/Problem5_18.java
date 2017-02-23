/*
5-18. [5] Consider a set of movies M1,M2, . . . , Mk. There is a set of customers, each one
of which indicates the two movies they would like to see this weekend. Movies are
shown on Saturday evening and Sunday evening. Multiple movies may be screened
at the same time.
You must decide which movies should be televised on Saturday and which on Sunday,
so that every customer gets to see the two movies they desire. Is there a
schedule where each movie is shown at most once? Design an efficient algorithm to
find such a schedule if one exists.
*/


package graph.analysis;

import ds.Common;

public class Problem5_18 {
	public static void main(String[] args) {
		Common.log("Each customer Ci wants to see 2 movies Mi. \n" +
		"C1(m1, m2) C2(m5,m3) C3(m2,m5) C4(m1,m4). Where mi is the movie name.\n" +
		"To avoid televised time conflicts on Saturday and Sunday. We have to split mi(s) into 2 groups.\n" +
		"This problem is reduced to a graph coloring problem, Where each customer's movie pair is an edge\n" +
		"and each movie mi is a vertex. Or, is it possible to color this graph with 2 colors?\n" +
		"Solution: see graph.apps.ColorGraph");
	}
}