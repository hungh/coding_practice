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

	public class BigSpaceDict{
		private Map<String, Integer> cache = new HashMap<String, Integer>();

		public BigSpaceDict (int[] array){
			int i, j , len = array.length;
			String key ;
			int min
			for(i = 0; i < len) {
				for(j = 0; j < len; j++){
					key = new StringBuilder(i).append(',').append(j);
				}
			}
		}

		public int findMin2(int start, int end){

		}
	}

	private int[] array;
	/*
	To store indice of the array (as keys) and its values
	*/
	private Map<Integer, Integer> cache = new HashMap<Integer, Integer>();
	private Tree<Integer, Integer> sortTree = new TreeMap<Integer, Integer>();

	public FastDictionary(int[] array){
		this.array = array;
	}

	public void append(int a){
		cache.put()
	}

	public static int findMinRange(int[] array, int i, int j){
		if(i < j &&  (j >= 0) && (j < array.length)){

		}
	}
}