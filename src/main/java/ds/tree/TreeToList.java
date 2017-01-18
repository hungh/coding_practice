/*
3-22. Write a program to convert a binary search tree into a linked list.
*/
package ds.tree;

public class TreeToList {
	public static void main(String[] args){
		Tree root = new Tree(11);

		root.left = new Tree(12);
		root.right = new Tree(15);

		root.right.right = new Tree(19);
		root.right.left = new Tree(14);

		root.right.left.right = new Tree(16);
		root.right.left.left = new Tree(13);
		
		convert(root);

		printTreeAsList(root);
	}
	// flatten left and make it to the right
	public static void convert(Tree root){
		if(root == null) return;		
		if(root.left == null){
			convert(root.right);	
		}else if(root.right == null){			
			root.right = root.left;			
			convert(root.right);				
		}else {
			convert(root.left);			
			Tree curr = root.left;			
			while(curr != null) {
				if (curr.right == null) break;
				curr = curr.right;
			}
			Tree myRight = root.right;
			root.right = root.left;
			curr.right = myRight;
			convert(myRight);
		}		
	}

	// convert the tree clock wise
	public static void convert_cw(Tree root){

	}

	public static void printTreeAsList(Tree root){
		Tree curr = root;
		while(curr != null) {
			System.out.print(" " + curr.value);
			curr = curr.right;
		}
		System.out.println();
	}
}
