/* (Skiena)
4-35. Let M be an n×m integer matrix in which the entries of each row are sorted in increasing order 
(from left to right) and the entries in each column are in increasing order (from top to bottom). 
Give an efficient algorithm to find the position of an integer x in M, or to determine that x is not there.
 How many comparisons of x with matrix entries does your algorithm use in worst case?
*/

package ds.sortsearch.others;

import ds.Pair;
import ds.Common;

public class SortedMatrixSearch {
 	public static void main(String[] args){

 		if(args.length != 1) {
 			Common.log("Enter a number k.");
 			System.exit(1);
 		}

 		int[][]  a =  { {1, 4, 6, 9 },
 						{2, 5, 7, 10},
 						{3, 7, 8, 11},
 						{7, 9, 12,15} };

 		Pair r = search(a, Integer.parseInt(args[0]));
 		// Pair r = search(a, 14);
 		Common.log(r);
 	}

 	/*
 	Returns a pair of row, column position of the value k found.
 	Otherwise null if not found.
 	<Analysis>
 	A naive method is to do binary searches for all rows -> O( n * logm)
 	<Better>
 	start from bottm left corner and wak up with O(n + m)
 	*/
 	public static Pair search(int[][] a, int k){
 		int i = a.length - 1;
 		int j = 0; // lower left corner

 		while (i >=0  && j < a[0].length ){
 			if(a[i][j] < k){
 				j++;
 			}else if (a[i][j] > k){
 				i--;
 			}else {
 				return new Pair(i, j);
 			}
 		}

 		return null;
 	}

 }
