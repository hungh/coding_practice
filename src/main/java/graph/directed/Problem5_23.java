/*

5-23. [5] Your job is to arrange n ill-behaved children in a straight line, facing front. You
are given a list of m statements of the form "i hates j". If i hates j, then you do not
want put i somewhere behind j, because then i is capable of throwing something
at j.

	(a) Give an algorithm that orders the line, (or says that it is not possible) in
		O(m + n) time.

	(b) Suppose instead you want to arrange the children in rows such that if i hates
		j, then i must be in a lower numbered row than j. Give an efficient algorithm
		to find the minimum number of rows needed, if it is possible.
*/


package graph.directed;

import java.util.*;
import ds.Common;
import graph.common.*;

public class Problem5_23 extends GraphAL {
	protected Map<Integer, Set<Integer>> rowMap = new HashMap<Integer, Set<Integer>>();

	public static void main(String[] args){
		Problem5_23 g= new Problem5_23(true);
		g.read_graph("/problem5_23.txt"); //problem5_23.txt
		g.print_graph();
		int max = g.findMinRows(1, 0) + 1;
		Common.log("Max rows=" + max);
		g.printRowMap();
		// TODO: ovbservation - if any row that has less then 2 vertices then the graph is not possible
	}

	public Problem5_23 (boolean directed) {
		super(directed);
	}

	//(a)
	// to find if the graph has a circle or not, see graph.apps.GraphCyc

	//(b)
	public int findMinRows(int s, int row) {
		int r = row;
		LinkedList<Integer> queue = new LinkedList<Integer>();

		queue.push(s);
		EdgeNode p;
		int begin;
		while(! queue.isEmpty()) {
			begin = queue.pop();
			updateRowMap(row, begin);
			p  = edges[begin];
			while(p != null) {
				if(! discovered[p.y]){
					if(begin < p.y) {
						Common.log("\t\t Adding to group [" + row + "] vertex " + p.y);
						queue.push (p.y);
						discovered[p.y] = true;
						updateRowMap(row, p.y);
					}else {
						r = findMinRows(p.y, row + 1);
					}	
				}
				p = p.next;
			}
			discovered[begin] = true;
		}
		return Math.max(row, r);
	}

	public void printRowMap() {
		for(Map.Entry<Integer, Set<Integer>> kv: rowMap.entrySet()) {
			Common._log("row " + kv.getKey() + " ~ no.vertex " + kv.getValue());
			Common.log("");
		}
	}

	protected void updateRowMap(int row, int i) {
		Set<Integer> v = rowMap.get(row);
		if(v == null) {
			v = new HashSet<Integer>();
		}
		v.add(i);
		rowMap.put(row, v);
	}
 }