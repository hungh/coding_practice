/* (Skiena)
4-33. Suppose that you are given a sorted sequence of distinct integers {a1,a2,…,an}.
 Give an O(lgn) algorithm to determine whether there exists an i index such as ai=i.
 For example, in {−10,−3,3,5,7}, a3=3. In {2,3,4,5,6,7}, there is no such i.
*/

package ds.sortsearch.others;

import ds.Common;

public class SearchByIndex  {
  public static void main(String[] args) {
  	int[] a = {-10, -3, 3, 5, 7};
  	// int[] a = {2, 3, 4, 5, 6, 7};
  	// int[] a = {1, 3, 5, 6};
  	int r = getItemIndex(a);
  	Common.log(r);
  }


  public static int getItemIndex(int[] a){
  	int begin = 0, end = a.length - 1;
  	int middle;
  	while(begin <= end){

  		middle = (begin + end)/2;
  		if(middle + 1 < a[middle]){
  			end = middle - 1;
  		}else if(middle + 1 > a[middle]){
  			begin = middle + 1;
  		}else{
  			return a[middle];
  		}
  	}
  	return Integer.MIN_VALUE;
  }


}
