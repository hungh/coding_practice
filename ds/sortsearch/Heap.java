public class Heap {
	public static void main(String[] args){
		int[] a = {0, 3, 15, 4, 3, 12, 9, 2, 1, 39, 22, 56, -8, 32};
		Heap heap = new Heap(a);
		// System.out.println("m = " + heap.extract_min());
		// System.out.println("m = " + heap.extract_min());
		// System.out.println("m = " + heap.extract_min());
		// System.out.println("m = " + heap.extract_min());
		
		int[] ret = heap.heap_sort();
		heap.print(ret); 	
	}

	private int n;
	private int[] a;

	public Heap(int[] ai){
		this.a = new int[ai.length + 2];		
		heapify(ai);
		print(this.a); 
	}
	
	// insert a value into the heap
	public void insert(int v){
		a[++n] = v;		
		bubble_up(n);
	}

	public void print(int[] r){
		for(int e: r) System.out.print(" " + e);
		System.out.println();
	}

	public int[] heap_sort(){
		int size = n;
		int[] ret = new int[n];
		int min;
		
		for(int i = 0; i < size; i++){
			min = extract_min();
			ret[i] = min;
		}
		return ret;
	}

	public int extract_min(){
		int min = a[1];
		a[1] = a[n];
		n--;
		bubble_down(1);
		return min;
	}


	  // heap sort - is a selection sort with data represented in heap
	  // build array heap (min heap), index 1
	  // take nlogn
	public void heapify (int[] input_array){
		int  i;
		for(i = 0; i < input_array.length; i++){
			insert(input_array[i]);
		}
	}

	private void bubble_down(int i){
		int min_index = i;
		int left_child = i * 2;
		for(int j = 0;  j <= 1; j++){
			if(left_child + j <= n)
				if( a[left_child + j] < a[min_index]) min_index = left_child + j;
		}
		if(min_index != i) {
			swap(i, min_index);
			bubble_down(min_index);
		}
		
	}

	  // take log(n)
	private void bubble_up( int i){
		if(i ==  1) return;
		int parent = i/2;
		if(a[i] < a[parent]){
			swap(i, parent);
			bubble_up(parent);
		}
		
	}

	public void swap(int i, int j){
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
}