/*
4-36. Consider an n×n array A containing integer elements (positive, negative, and zero).
 Assume that the elements in each row of A are in strictly increasing order, 
 and the elements of each column of A are in strictly decreasing order. 
 (Hence there cannot be two zeroes in the same row or the same column.) 
 Describe an efficient algorithm that counts the number of occurrences of the element 0 in A. Analyze its running time.
 */

package ds.sortsearch.challenges;

import ds.Common;

public class Problem4D36 {
 	public static void main(String[] args){
 		/*int[][] a = { { 0,  1,  3, 5 },
 					  {-2,  0,  2, 4 },
 					  {-3, -2,  0, 2 },
 					  {-5, -3, -1, 0 } };*/

 		int[][] a =  { {-1,  0, 2,  5,  6,  8, 12},
 					   {-2, -1, 1,  3,  4,  5,  7 },
 					   {-3, -2, 0,  1,  3,  4,  5},
 					   {-4, -3, -1, 0,  2,  3,  4},
 					   {-5, -4, -2, -1, 0,  1,  3 },
 					   {-6, -5, -3, -2, -1, 0,  2 },
 					   {-7, -6, -4, -3, -2,-1,  0}

 					 };

 		/*int[][] a = { {8, 9, 11, 12},
 					  {7, 8, 10, 11},
 					  {6, 7, 9,  10},
 					  {5, 6, 8,  9}
 					}; */

 		/*int[][] a =  { {-9, -8, -7, -6},
 					   {-10, -9, -8, -7},
 					   {-11, -10, -9, -8},
 					   {-12, -11, -10, -9}
 	                 };*/

 		int r = getZeroCount(a);

 		Common.log("Total=" + r);
 	}


 	public static int getZeroCount(int[][] a){
 		int total = 0;
 		int i = 0, j = 0;

 		// measure
 		int loop = 0;

 		while(i < a.length &&  (j < a[0].length)){
 			if(a[i][j] == 0) {
	 			total++;
	 			i++;
	 			j++;
	 		}else if (a[i][j] > 0){
	 			i++;
	 		} else if(a[i][j] < 0){
	 			j++;
	 		}
	 		loop++;
 		}

 		Common.log("#loop=" + loop + ";size=" + (a.length * a[0].length));
 		
 		return total;
 	}
}

