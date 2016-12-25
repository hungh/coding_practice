package ds.sortsearch.binary;


/*
Given an array of a limited 0s followed by unbound 1s.
Find the first occurrence of 1 in the array.
Expected time complexity: O(logn)
*/
public class BoundarySearch {
	public static void main(String[] args){
		int[] a = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
		 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,1 , 1,1, 1, 1, 1, 1, 1, 1, 1, 1};
		int b = getBoundaryIndex(a);
		System.out.println(b);
	}



	public static int getBoundaryIndex(int[] a){
		int i = 0, j = 1;

		while(a[i] == 0 && (i < a.length)){
			j = j*2;
			i = j;
		}
		System.out.println(a[i/2] + " - " + a[i]);
		return BinarySearch.search(a, 1, i/2, i);
	}


}