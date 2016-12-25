package ds.sortsearch.binary;

public class BinarySearch {
	public static void main(String[] args){
		int[] a = {2, 5, 7, 8, 12, 90};
		int f = search(a, 8, 0, a.length - 1);
		System.out.println(f);
	}

	public static int search(int[] a, int k, int l, int h){
		if(a == null || (l > h) ) return -1; // edge 
		int m = (l + h)/2;

		if( k == a[m]) return m;
		if( k < a[m])
			return search(a, k, l, m - 1);
		else
			return search(a, k, m + 1, h);
	}
}