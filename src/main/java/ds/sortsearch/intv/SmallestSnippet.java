/*
4-45. Given a search string of three words, 
find the smallest snippet of the document that contains all three of the search words---i.e., 
the snippet with smallest number of words in it. You are given the index positions 
where these words occur in the document, such as word1: (1, 4, 5), word2: (3, 9, 10), and word3: (2, 6, 15).
 Each of the lists are in sorted order, as above.
*/
package ds.sortsearch.intv;

import ds.Common;
import ds.Pair;

public class SmallestSnippet {
	public static void main(String[] args){
		int[] a = new int[] {1, 6, 11};
		int[] b = new int[] {4, 10, 14};
		int[] c = new int[] {3, 7, 9, 13};
		String result = findSnippet(new String[] {"0", "w1", "5", "w3", "w2", "4", "w1", "w3", "8", "w3", "w2", "w1", "0","w3","w2" }, a, b, c);
		Common.log(result);
	}
	/*
	Analysys:
	scan w1:
	for each w1: do a binary Search on  w2 and w3 for the closest element to w1
	keep track of the min snippet length
	Time complexity: O(n* 2logn) ~ O(nlogn)
	*/
	public static String findSnippet(String[] s, int[] a, int[] b, int[] c){
		int clsB, clsC, t;
		Pair minMax, min = new Pair(0, Integer.MAX_VALUE);
		for(int i = 0; i < a.length; i++){
			clsB = findClosestGap(b, a[i]);
			clsC = findClosestGap(c, a[i]);
			minMax = getMinMax(a[i], clsB, clsC);
			t = minMax.b - minMax.a;
			if(min.b - min.a > t) min = minMax;
		}
		Common.log("min" + min);
		return getString(s, min.a, min.b);
	}
	// where a is a sorted array of integers
	public static int findClosestGap(int[] a, int x){
		if(a.length == 1) return a[0];
		int s = 0, e = a.length - 1, middle;
		while(s < e){
			middle = (s + e)/2;
			if(x  > a[middle]){
				s = middle + 1;
			}else if(x < a[middle]){
				e = middle - 1;
			} else {				
				return a[middle];
			}			
		}
		if(s == 0){
			if(Math.abs(x - a[0]) > Math.abs(x - a[1])) return a[1]; else return a[0];
		} else if( s == a.length - 1){
			if(Math.abs(x - a[a.length - 1]) < Math.abs(x - a[a.length - 2])) return a[a.length - 1]; else return a[a.length - 2];
		} else {
			Pair[] ts = new Pair[3];
			ts[0] = new Pair(s, Math.abs(x - a[s]));
			ts[1] = new Pair(s - 1, Math.abs(x - a[s  - 1]));
			ts[2] = new Pair(s + 1, Math.abs(x - a[s  + 1]));
			Pair min = ts[0];
			for(int i = 1; i < 3; i++) if(min.b  > ts[i].b) min = ts[i];
			return a[min.a];
		}		
	}
	// default to min if 'isMax = false'
	private static Pair getMinMax(int... a) {		
		int min = a[0], max = min;
		Pair r = new Pair(min, max);
		for(int i = 1; i < 3; i++){
			if(max < a[i]) max = a[i];
			if(min > a[i]) min = a[i];
		}
		r.a = min; 
		r.b = max;
		return r;
	}
	private static String getString(String[] s, int min, int max){
		StringBuilder buff = new StringBuilder();
		for(int i = min; i <= max; i++) buff.append(s[i]).append(' ');
		return buff.toString();
	}
}