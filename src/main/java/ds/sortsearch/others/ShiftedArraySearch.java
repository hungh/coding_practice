/*
* Given a sorted array with k (unknown) elements were shifted to the array circularly.
Find the maximum value in the array in O(logn) Time
*/
package ds.sortsearch.others;

import ds.Common;

public class ShiftedArraySearch {
  public static void main(String[] args) {
    //  int[] a = {22, 24, 25, 49, 1, 4, 7, 12, 19};
    int[] a = {2, 3, 4, 1};
     int m = findMaxValue(a);
     Common.log(m);
  }

  public static int findMaxValue(int[] a){
    int begin = 0, end = a.length - 1;
    if(a[begin] < a[end]) return a[end];
    int middle;
    while(begin < end - 1){
      middle = (begin + end)/2;
      if(a[middle] < a[end]){
        end = middle;
      }else if(a[middle] > a[end]){
        begin = middle;
      }
    }
    return a[begin];
  }
}
