import java.util.*;

public class MinRange {
	/*
	x (i) where 1 <= i <= N
	expect O(1)
	Space (n^2)
	4 5 8 1 2 9 4 3 1 2 11 23
	0 1 2 3 4 5 6 7 8 9 10 11
	Dictionary to store 
	The tree to store the values of the array

	Min (0,1) = 4
	Min(0, 2) = 4
	Min(0,....n)

	Min(1,2) , Min(1,3) .. Min(1, n)
	.. O(n^2)

	Put all of the values into a Dictionary
	Key???  Key "1,3"  as string
	convert this into range. -> retrieve data from there

	*/
	public static void main(String... args){
		int[] data = {4 ,5 ,8 ,12 ,2 ,9 ,4 ,3 ,33 ,22 ,11 ,23};
		MinRange bc = new MinRange (data);
		bc.printCache();

		System.out.println("Find min---");
		int min = bc.findMin2 (5, 8);
		System.out.println(min);
	}

	private Map<String, Integer> cache = new HashMap<String, Integer>();


	public MinRange (int[] array){
		int i , j, len = array.length;
		String key ;
		int min;
		for(i = 0; i < len; i++) {
			min = array[i];
			for(j = i; j < len; j++){
				if(array[j] < min) min = array[j];
				key = new StringBuilder(Integer.toString(i)).append(',').append(Integer.toString(j)).toString();
				cache.put (key, min);				
			}
		}
	}

	/*
	Create a data structure that use O(n) space
	and answer queries in O(logn)

	4 5 8 1 2 9 4 3 1 2 11 23
	0 1 2 3 4 5 6 7 8 9 10 11

    1. when a range is provided,  build a binary search tree from it O(nlogn) -> Search for min O(logn) [ Could be Q(n)]

     using binary search  
	*/
	public void init_linear_space(int[] array){
		int i = 0, j = array.length - 1;
		int middle = i + (i + j)/2;

		while() {

		}
	}
	static class BinTree {
		public String value;
		public BinTree left;
		public BinTree right;
	}

	public void binary_insert(BinTree root, BinTree node){
		
	}

	public int min (int[] array, int start, int end) {
		int min = array[start];
		for(int i = start; i <= end; i++){
			if(array[i] < min) min = array[i];
		}
		return min;
	}

	public int findMin2(int start, int end){
		String key = new StringBuilder(Integer.toString(start)).append(',').append(Integer.toString(end)).toString();
		Integer v =  cache.get(key);
		System.out.println(key);
		if(key == null) { return -1;} else return v;
	}
	/*
	Print the contents of the cache
	*/
	public void printCache(){
		for(Map.Entry<String, Integer> ec: cache.entrySet()){
			System.out.print( "[ " + ec.getKey()  + " ; " + ec.getValue() + " ]");
		}
	}

	public static void log(Object obj){
		System.out.print(obj);
	}
	public static void logln(Object obj){
		log(obj);
		System.out.println();
	}


}