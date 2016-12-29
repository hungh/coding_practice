/*
4-14. [5] Give an O(n log k)-time algorithm that merges k sorted lists with a total of n
elements into one sorted list. (Hint: use a heap to speed up the elementary O(kn)-
time algorithm).
*/
package ds.sortsearch.heap;

import ds.sortsearch.Heap;
import ds.Common;


public class MergeSortedList {
	public static void main(String[] args){
		int[] a = {1, 3, 5,7, 9};
		int[] b = {5, 8 ,13 ,15, 19};
		int[] c = {4, 9 ,16 ,19 ,22, 33};
		int[] d = {3, 5, 10, 14, 91 ,116 ,191 ,202, 233};
		int[] e = {9, 12, 25, 77, 89, 102};

		Common.printArray(a);
		Common.printArray(b);
		Common.printArray(c);
		Common.printArray(d);
		Common.printArray(e);
		int[] t = mergeSortedLists(a, b, c, d, e);
		Common.log("--- Result---");
		Common.printArray(t);
	}
	/*
	Maintain heap size k (where k is the length of the input arrays 'as')
	Bubble action when doing extract_min is O(logk)
	Since we have n (= total size of all 'as'). The time complexity on average: n*O(logk)
	*/
	public static int[] mergeSortedLists(int[]... as){
		HeapGR<HeapNode> heap = new HeapGR<HeapNode>(HeapNode.class);
		int i, len = 0;
		int pos, n = 0;
		for(i = 0; i < as.length; i++) len += as[i].length;
		int[] ret = new int[len];
		int[] iters = new int[as.length]; // iterator for each element of arrays
		HeapNode node;

		// initialize the heap
		for(i = 0; i < as.length; i++){
			heap.insert(new HeapNode(as[i][iters[i]], i));
			iters[i]++;
		}

		while(true){
			node = heap.extract_min(); 
			if(node == null) break;					
			ret[n++] = node.value();
			pos = node.pos();
			if(iters[pos] < as[pos].length){
				heap.insert(new HeapNode(as[pos][iters[pos]], pos));
				iters[pos]++;
			}
		}
		return ret;		
	}
}
		

