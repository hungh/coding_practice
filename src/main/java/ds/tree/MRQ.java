package ds.tree;

public class MRQ {
	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int[] ret = init_tree (arr);
		print(ret);
		int ps = getPartialSum (ret, 8);
		System.out.println("Partial Sum = " + ps);
		
	}

	public static int getPartialSum(int[] array, int i){
		System.out.println("len = " + array.length);
		i  = (array.length + 1)/2 - 1 + i;
		System.out.println("i = " + i);
		int pi = getParentIdx(i);
		int sum = array[i];
		while( pi >= 0) {
			System.out.println("pi=" + pi);
			if(pi * 2 + 2 == i ) {
				if(pi*2 + 1 < array.length)
					sum += array[ pi * 2 + 1]; //addd the left subtree sum
			}
			if(pi == 0) break;
			i = pi;
			pi = getParentIdx(i);
		}
		return sum;
	}

	public static int getParentIdx (int i){
		if(i %2 == 0)
			return i/2 - 1;
		else
			return i/2;
	}

	public static int[] init_tree (int[] array){
		int len = array.length;

		if(len < 2) return array;

		int[] ret_array = new int[2 * len - 1];

		int i = 0;
		for(; i < len; i++)		ret_array [ (len - 1) + i] = array[i];

		// populate the first half of the return array
		for(i = len - 2; i >= 0; i--){
			ret_array [i] = ret_array[2 * i + 1] + ret_array[ 2 * i + 2];
		}
		return ret_array;
	}

	public static void print(int[] a){
		for(int e: a) System.out.print (" " + e);
		System.out.println();
	}


}
