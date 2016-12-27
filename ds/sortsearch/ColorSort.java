/* Skiena (Algorithm Design and Manual)
4-4. [3] Assume that we are given n pairs of items as input, where the first item is a
number and the second item is one of three colors (red, blue, or yellow). Further
assume that the items are sorted by number. Give an O(n) algorithm to sort the
items by color (all reds before all blues before all yellows) such that the numbers
for identical colors stay sorted.
For example: 
(1,blue), (3,red), (4,blue), (6,yellow), (9,red) 

should become:

(3,red), (9,red), (1,blue), (4,blue), (6,yellow).

*/
package ds.sortsearch;

import ds.list.QuickSort;
import ds.Pair;
import java.util.List;
import java.util.ArrayList;
public class ColorSort {
	public static final int RED = 0;
	public static final int BLUE = 1;
	public static final int YELLOW = 2;

	public static void main(String[] args){
		Pair[] a = {new Pair(1,BLUE), new Pair(3,RED), new Pair(4,BLUE), new Pair(6,YELLOW), new Pair(9,RED) };
		sortPairs(a);
		Pair.print(a);
	}

	public static void sortPairs (Pair[] a){
		List<Pair> redBuck = new ArrayList<Pair>();
		List<Pair> blueBuck = new ArrayList<Pair>();
		List<Pair> yellowBuck = new ArrayList<Pair>();
		int i;
		for(i = 0; i < a.length; i++){
			if(a[i].b == RED) redBuck.add(a[i]);
			if(a[i].b == BLUE) blueBuck.add(a[i]);
			if(a[i].b == YELLOW) yellowBuck.add(a[i]);
		}
		i = 0;
		for(Pair e: redBuck) { a[i++] = e;}
		for(Pair e: blueBuck) { a[i++] = e;}
		for(Pair e: yellowBuck) { a[i++] = e;}
	}


}