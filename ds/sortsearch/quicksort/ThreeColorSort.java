/* (Skiena)
4-18. [5] Suppose an array A consists of n elements, each of which is red, white, or blue.
We seek to sort the elements so that all the reds come before all the whites, which
come before all the blues The only operation permitted on the keys are
• Examine(A,i) – report the color of the ith element of A.
• Swap(A,i,j) – swap the ith element of A with the jth element.
*/
package ds.sortsearch.quicksort;

import ds.Common;

public class ThreeColorSort {
	private static final int RED = 0;
	private static final int WHITE = 1;
	private static final int BLUE = 2;

	public static void main(String[] args){
		int[] a = {BLUE, RED, WHITE, BLUE, RED, WHITE, BLUE, BLUE, RED, RED, WHITE};
		sort(a);
		Common.printArray(a);

	}
	// RED BLUE WHITE
	public static void sort(int[] a){
		int rend = 0, wend = 1, bend = 2;
		int color, i;
		for(i = 0; i < a.length; i++) {
			color = examine(a, i);
			if(color == RED){
				swap(a, rend, i);
				rend++;
			}
		}
		// partition the rest 2 colors
		//white and blue
		wend = rend;
		for(i = rend; i < a.length; i++) {
			color = examine(a, i);
			if(color == WHITE){
				swap(a, wend, i);
				wend++;
			}
		}
	}

	private static int examine(int[] a, int i){
		return a[i];
	}

	private static void swap(int[] a, int i, int j){
		int temp = a[j];
		a[j] = a[i];
		a[i] = temp;
	}
}