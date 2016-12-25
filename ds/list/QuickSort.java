package ds.list;

public class QuickSort {
	public static void main(String[] args){
		int[] a = {2, 3, 1, 5, 6, 12, 9, 6, 8, 7};
		System.out.println("Before partitino");
		print(a);
		int pi = partition(a, 0, a.length - 1);
		System.out.println("Partition position:" + pi);
		System.out.println("After partition");
		print(a);

		// quick sort
		quick_sort(a, 0, a.length - 1);
		System.out.println("After quick sort:");
		print(a);

	}

	public static void quick_sort(int[] a, int l , int h) {
		int p;
		if( l < h){
			p = partition(a, l, h);
			quick_sort(a, l, p- 1);
			quick_sort(a, p+1, h);
		}
	}
	
	public static void print(int[] a) {
		for(int e: a) System.out.print(" " + e);
		System.out.println();
	}

	public static int partition(int[] a, int l, int h) {
		int i, p =  h;
		int firsthigh = l ;
		for(i = l; i < h; i++){
			if(a[i] < a[p]) {
				swap(a, i, firsthigh);
				firsthigh++;
			}
		}
		swap(a, p, firsthigh);
		return firsthigh;

	}

	private static void swap(int[] a, int i, int j){
		int temp = a[j];
		a[j] = a[i];
		a[i] = temp;
	}
}

/**

4 5 3 1 3 5 2

*/
