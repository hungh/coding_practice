/*
(Skiena)
4-15. [5] (a) Give an efficient algorithm to find the second-largest key among n keys.
You can do better than 2n − 3 comparisons.

(b) Then, give an efficient algorithm to find the third-largest key among n keys.
How many key comparisons does your algorithm do in the worst case? Must your
algorithm determine which key is largest and second-largest in the process?

*/
package ds.sortsearch.heap;

import ds.Common;

/*
Note: you can use LinearSelection
*/

public class SecondLargestKey {
	public static void main(String[] args){
		int[] a = {5, 3, 1, 22, 90, 28, 76, 33, 102, 44, -7};
		Common.printArray(a);
		int r = secondLargest(a);
		Common.log(r);

		int t= thirdLargest(a);
		Common.log(t);

		int t2= thirdLargest2(a);
		Common.log(t2);
	}
	/*
	after n comparisions
	*/
	public static int secondLargest(int[] a){
		int largest = a[0];
		int sndLargest = a[0];

		for(int i = 0; i < a.length; i++){
			if(a[i] > largest) {
				sndLargest = largest;
				largest = a[i];
			}
		}
		return sndLargest;
	}

	// take 2n comparisions
	public static int thirdLargest(int[] a){
		int sL = secondLargest(a); // takes n comparisions
		int thrdL = Integer.MIN_VALUE;
		for(int i = 0; i < a.length; i++){
			if(a[i] >= sL) continue;
			if(a[i] > thrdL) thrdL = a[i];
		}
		return thrdL;
	}

	// n comparisions
	public static int thirdLargest2(int[] a){
		int largest = Integer.MIN_VALUE;
		int sndLargest = Integer.MIN_VALUE;
		int thrdLargest = Integer.MIN_VALUE;

		for(int i = 0; i < a.length; i++){
			if(a[i] > largest) {
				thrdLargest = sndLargest;
				sndLargest  = largest;
				largest = a[i];
			} else{
				// 
				if(a[i] > sndLargest) {
					thrdLargest = sndLargest;
					sndLargest = a[i];
				}else if(a[i] > thrdLargest) {
					thrdLargest = a[i];
				}

			}
		}
		return thrdLargest;
	}
}