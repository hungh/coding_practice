package graph.weighted;

/*
6-20. [5] Can we modify Dijkstra’s algorithm to solve the single-source longest path problem
by changing minimum to maximum? If so, then prove your algorithm correct.
If not, then provide a counterexample
*/

public class Problem6_20 {
	/*
	Answer: No
	1. The distance array in Dijkstra is initialized with Infinite (MAX VALUE), any comparison to it will fail
	   Therefore, nothing happens.
	2. If we can solve this by changing minimum to maximum, then we can solve the problem of finding the longest 
	   path from a -> a (Hamiltonian cycle) and this is an NP hard.
	3. Counter example:
		Note: (weight) in brackets

		1 - (3) - 2
		1 - (1) - 4
		2 - (3) - 4
		2 - (1) - 3
		3 - (17) - 4
		
		Find longest path from 1 - 4 ?
		Assume that we can proceed by selecting the largest weight and update the parent from x -> y,
		then it would have picked 1 -2 - 4, but the actual longest path is 1 - 2 -3 - 4
	*/
}