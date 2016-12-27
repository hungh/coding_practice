package ds.sortsearch;

import ds.list.QuickSort;
import ds.Common;
import java.util.List;
import java.util.ArrayList;

public class UnionSet {
	public static void main(String[] args){
		int[] a = {0 ,3 ,4 ,6 ,1 ,8 ,9 ,12};
		int[] b =  {2 ,1 ,4 ,8 ,5};
		int[] r = union(a, b);
		Common.printArray(r);
	}

	public static int[] union(int[] a, int[] b){
		if(a == null) return a;
		if(b == null) return b;
		// sort both a and b
		QuickSort.quick_sort(a, 0, a.length - 1);
		QuickSort.quick_sort(b, 0, b.length - 1);
		int i = 0, j = 0;
		List<Integer> list = new ArrayList<Integer>();
		while(i < a.length && j < b.length){
			if(a[i] < b[j]) {
				list.add(a[i++]);
			}else if(a[i] > b[j]) {
				list.add(b[j++]);
			}else{
				list.add(a[i++]);
				j++;
			}
		}
		

		while(i < a.length) list.add(a[i++]);
		while(j < b.length) list.add(b[j++]);
		
		return Common.toArray(list);

	}
}