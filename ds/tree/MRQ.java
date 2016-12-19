public class MRQ {
	public Tree buildBalancedTree (int[] array){
		if(array == null) return null;

		// build the tree with n - 1 node (where n is the length of the array)
		Tree root = new Tree ();
		// scan through the array
		int i  = 0, len = array.length - 1, j = 0;
		Tree[] cache = new Tree[2*len - 1];


		int start = 0; 
		int end = len - (len % 2);

		for(; i <= end; i++){
			
		}




	}

	private class Tree {
		public Tree (int value) { this.value = value; }
		public int value;
		public Tree left;
		public Tree right;
	}
}