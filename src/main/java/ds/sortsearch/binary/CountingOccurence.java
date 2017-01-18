package ds.sortsearch.binary;

/*
Counting the numnber of  occurrences of a value K in a sorted array
Example:
Count occurrence of 6s
1 3 5 6 6 6 6 6 8 12 14
Expect:  5
Expected time complexity: O(logn)
         space complexity: O(1)
*/
public class CountingOccurence {
	public static void main(String[] args){
		int[]  a = {1, 2, 5, 6, 6, 6, 6, 6, 8, 12, 14};
		int b = findOccurrence(a, 6);
		System.out.println(b);
	}

	public static int findOccurrence(int[] a, int k){
		return getBoundaryIndex(a, k, 0, a.length - 1, false) - getBoundaryIndex(a, k, 0 , a.length - 1, true);
	}

	public static int getBoundaryIndex(int[] a, int k, int l, int h, boolean reversed){
		if(l > h) return l; // return the boundary

		int middle =  (l + h)/2;

		if(k < a[middle]){
			return getBoundaryIndex(a, k, l, middle - 1, reversed);
		}else {
			if(reversed && (k == a[middle])){
				return getBoundaryIndex(a, k, l, middle - 1, reversed);	
			}else{
				return getBoundaryIndex(a, k, middle + 1, h, reversed);
			}
		}
	}


}