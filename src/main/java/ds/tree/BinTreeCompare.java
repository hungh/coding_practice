/*
3-21 .Write a function to compare whether two binary trees are identical. Identical trees have the same key value at each position and the same structure.
*/

package ds.tree;

public class BinTreeCompare {
	public static void main(String[] args){
		Tree a = new Tree(5);
		a.left = new Tree(3);
		a.right = new Tree(8);

		a.right.right = new Tree(11);
		a.right.left = new Tree(7);

		Tree b = new Tree(5);
		b.left = new Tree(3);
		b.right = new Tree(8);

		b.right.right = new Tree(11);

		boolean isSame = new BinTreeCompare().compare(a, b);
		System.out.println("isSame=" + isSame);
	}

	public boolean compare (Tree a, Tree b){
		if(a == null && b == null) return true;
		if(a == null) return false;
		if(b == null) return false;
		if(a.value == b.value){
			return compare(a.left, b.left) && compare(a.right, b.right);
		}else {
			return false;
		}
	}
}

