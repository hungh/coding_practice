import java.util.*;

public class MinRange {
	/*
	x (i) where 1 <= i <= N
	expect O(1)
	Space (n^2)
	*/
	public static void main(String... args){
		int[] data = {4 ,5 ,8 ,12 ,2 ,9 ,4 ,3 ,33 ,22 ,11 ,23};
		MinRange bc = new MinRange (data);
		// bc.printCache();

		System.out.println("Find min---");
		int min = bc.findMin2 (5, 8);
		System.out.println(min);

		// another approachc is Space(n) and Time Complexity O(logn) [with AVL tree]
		BinTree root = bc.init_tree(data);
		System.out.println("Total nodes of the tree=" + bc.count_tree_size(root));
		bc.printTree(root);
		int min2 = bc.getMinLogN(root, 3, 5);
		System.out.println("min2=" + min2);

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

	public int getMinLogN(BinTree root, int s, int e){
		if(root == null) return Integer.MAX_VALUE;

		if(root.s == s && root.e == e) return root.min;
		int middle = s + (e - s)/2;
		int r_middle = root.s +  (root.e - root.s)/2;
		// if the range falls into the left
		if (root.s <= s && e <= r_middle) {
			return getMinLogN(root.left, s, e);
		}else if (r_middle >= s && e <= root.e){
			return getMinLogN(root.right, s, e);
		}else{
			return Math.min ( getMinLogN( root.left, s, e), getMinLogN(root.right, s, e));
		}
	}


	public int count_tree_size(BinTree root){
		if(root == null) return 0;
		return 1 + count_tree_size (root.right)  + count_tree_size (root.right);

	}

	/*
	Create a data structure that use O(n) space
	and answer queries in O(logn)
    1. when a range is provided,  build a binary search tree from it O(nlogn) -> Search for min O(logn) [ Could be Q(n)]
     using binary search
	*/
	public void init_linear_space(int[] array, int start, int end, BinTree tree) {
		if(start >= end) return;
		tree.binary_insert(new BinTree (start, end, min (array, start, end)));
		int middle = start + (end - start)/2;
		init_linear_space(array, start, middle, tree);
		init_linear_space(array, middle + 1, end, tree);

	}

	public void printTree(BinTree root){
		if(root != null) {
			System.out.print(" ; Left: [ ");
			printTree(root.left);
			System.out.print(" ;VAL: { v:" + root.range() + ", min: " + root.min + "} ");
			System.out.println(" ; Right: ");
			printTree(root.right);
			System.out.println(" ] ;  ");
		}
	}

	public BinTree init_tree (int[] array){
		int min_root = min(array, 0, array.length -1);
		BinTree root = new BinTree (0, array.length, min_root);
		init_linear_space(array, 0, array.length - 1, root);
		return root;
	}

	static class BinTree {
		public BinTree(int s, int e, int min){
			this.value = s * 10 + e;
			this.min = min;
			this.s = s;
			this.e = e;
		}
		public String range() {
			return  s + "," + e;
		}
		public int value;
		public int min;
		public int s;
		public int e;
		public BinTree left;
		public BinTree right;

		// this structure is not a balanced BIN tree
		/// we wil implement an AVL tree to guarrantee Ologn queries
		public void binary_insert(BinTree node){
			if(node == null) return;
			if(node.value < this.value){
				if(this.left == null){
					// do insert
					this.left = node;
				}else{
					// keep searching for a spot
					this.left.binary_insert(node);
				}

			}else{
				if(this.right == null){
					// insert
					this.right = node;
				}else{
					this.right.binary_insert(node);
				}
			}

		}

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
