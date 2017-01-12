/*
4-45. Given a search string of three words, 
find the smallest snippet of the document that contains all three of the search words---i.e., 
the snippet with smallest number of words in it. You are given the index positions 
where these words occur in the document, such as word1: (1, 4, 5), word2: (3, 9, 10), and word3: (2, 6, 15).
 Each of the lists are in sorted order, as above.
*/
package ds.sortsearch.intv;

public class SmallestSnippet {
	public static void main(String[] args){
		// w1: 1, w2: 2, w3: 3
		 0 w1 5 w3 w2 4 w1 w3 8 w3 w2 w1 0 w3 w2 

		 w1: [1, 6, 9]  w2: [4, 8, 12]    w3: [3, 7, 11]
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
	}
}