/*
4-45. Given a search string of three words, 
find the smallest snippet of the document that contains all three of the search words---i.e., 
the snippet with smallest number of words in it. You are given the index positions 
where these words occur in the document, such as word1: (1, 4, 5), word2: (3, 9, 10), and word3: (2, 6, 15).
 Each of the lists are in sorted order, as above.
*/
package ds.sortsearch.intv;

import ds.Common;

public class SmallestSnippet {
	public static void main(String[] args){
		// w1: 1, w2: 2, w3: 3
		// 0 w1 5 w3 w2 4 w1 w3 8 w3 w2 w1 0 w3 w2 

		 //w1: [1, 6, 9]  w2: [4, 8, 12]    w3: [3, 7, 11]

		 //1 2 3 4  6 7 11  8
		 int[] a = {1, 3 , 6, 7, 11, 15, 19, 22, 30};
		 int r = findClosestGap(a, Integer.parseInt(args[0]));
		 Common.log(r);
	}

	/*
	Analysys:
	scan w1:
	for each w1: do a binary Search on  w2 and w3 for the closest element to w1
	keep track of the min snippet length
	*/

	public static String findSnippet(String[] s, int[] a, int[] b, int[] c){
		int i = 0;
		for(; i < a.length; i++){

		}
		return null;
	}
	// where a is a sorted array of integers
	public static int findClosestGap(int[] a, int x){
		if(a.length == 1) return a[0];

		int s = 0, e = a.length - 1, middle;
		int min = Integer.MAX_VALUE;
		while(s <= e){
			middle = (s + e)/2;
			Common.log("amiddle=" + a[middle]);
			if(x  > a[middle]){
				s = middle + 1;
			}else if(x < a[middle]){
				e = middle - 1;
			} else {
				// will not happen
				throw new IllegalArgumentException("Index clash exception");
			}
		}

		if(s == 0){
			if(Math.abs(x - s[0]) > Math.abs(x - s[1])) return 1; else return 0;
		} else if( s == a.length - 1){

		}
		return a[s];
	}
}