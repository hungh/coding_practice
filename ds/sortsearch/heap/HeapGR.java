package ds.sortsearch.heap;

import java.lang.reflect.Array;
/*
Heap that supports heap nodes as objects
*/
@SuppressWarnings("unchecked")
public class HeapGR <T extends Comparable> {
	private T[] a;
	private int n;

	public HeapGR(Class<T> c){
		a = (T[]) Array.newInstance(c, 10);		
	}

	public HeapGR(T[] ai, Class<T> c){
		this.a = (T[]) Array.newInstance(c, ai.length + 2);		
		heapify(ai);
	}

	public boolean isEmpty(){
		return n < 1;
	}

	// insert a value into the heap
	public void insert(T v){
		if(n == a.length - 1) expand();
		a[++n] = v;		
		bubble_up(n);
	}

	public T extract_min(){
		if(n < 1) return null;
		T min = a[1];
		a[1] = a[n];
		n--;
		bubble_down(1);
		return min;
	}

	public void heapify (T[] input_array){
		int  i;
		for(i = 0; i < input_array.length; i++){
			insert(input_array[i]);
		}
	}

	private void expand(){
		T[]  new_mem =  (T[])Array.newInstance(a[0].getClass(), 2 * a.length);
		for(int i = 0; i < a.length; i++){
			new_mem[i] = a[i];
		}
		this.a = new_mem;
	}

	private void bubble_down(int i){
		int min_index = i;
		int left_child = i * 2;
		for(int j = 0;  j <= 1; j++){
			if(left_child + j <= n)
				if( a[left_child + j].compareTo(a[min_index]) < 0 ) min_index = left_child + j;
		}
		if(min_index != i) {
			swap(i, min_index);
			bubble_down(min_index);
		}
		
	}

	// take log(n)
	private void bubble_up(int i){
		if(i ==  1) return;
		int parent = i/2;
		if(a[i].compareTo(a[parent]) < 0){
			swap(i, parent);
			bubble_up(parent);
		}
		
	}

	public void swap(int i, int j){
		T t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

}