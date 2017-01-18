/* Skiena (Algo DeMaual)
4-3. [3] Take a sequence of 2n real numbers as input. Design an O(n log n) algorithm that
partitions the numbers into n pairs, with the property that the partition minimizes
the maximum sum of a pair. For example, say we are given the numbers (1,3,5,9).
The possible partitions are ((1,3),(5,9)), ((1,5),(3,9)), and ((1,9),(3,5)). The pair
sums for these partitions are (4,14), (6,12), and (10,8). Thus the third partition has
10 as its maximum sum, which is the minimum over the three partitions.

1 3 5 8 9 10
(1, 9), (3, 8),  (5, 10)
*/
package ds.sortsearch;

import ds.Pair;
import ds.list.QuickSort;

public class MinOfMaxSumPair {
	public static void main(String[] args){
		int[] a = {1 ,3 ,5 ,8 ,9 ,10};
		Pair[]  ret = findPairs(a);
		Pair.print(ret);
	}

	public static Pair[] findPairs(int[] a){
		if(a == null || a.length % 2 > 0) throw new IllegalArgumentException("Invalid array size input.");
		QuickSort.quick_sort(a, 0, a.length - 1);
		Pair[] ret = new Pair[a.length/2];
		for(int i = 0, j = a.length - 1, k = 0; i < j; i++, j--, k++){
			ret[k] = new Pair(a[i], a[j]);
		}
		return ret;
	}



}