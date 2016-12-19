public class MRQ {
	public static void main(String[] args) {
		int[] arr = {3, 4, 5, 8 , 7, 8, 9, 1, 2, 12, 9};
		int[] ret = init_tree (arr);
		print(ret);
	}

	public static int getPartialSum(int start, int i, int[] array, int sum){
		if(start == i){
			return sum + array[i];
		}else if(start < i) {
			return getPartialSum(2 * i + 2, i, array, sum + array[2*i + 1]); // move to the right
		}
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
