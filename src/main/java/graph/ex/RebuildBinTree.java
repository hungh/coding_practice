/*
Problem 5-7. 
Is it possible to rebuild a binary tree from a pre order and in order traversal. YES
Is it possible to rebuild the same tree from a pre order and post order traversal. NO unless it is a full binary tree.
Why can't you do it?
Because for (pre and post), the sub-tree is on one side of the root (left or right). Therefore, we can't 
determine where to split unless it is a full tree (requires 2 children or none at all).
Below is my algorithm for the Pre and In order traversal rebuild because it is possible.
*/

package graph.ex;

import java.util.*;

import ds.Common;
import ds.tree.Tree;

public class RebuildBinTree {

	private Map<Integer, Integer> postMap = new HashMap<Integer, Integer>();
	private int[] pre;
	private int[] post;

	public RebuildBinTree(int[] pre, int[] post){
		this.pre = pre;
		this.post = post;
		init();
	}

	public Map<Integer, Integer> getPostMap() {
		return postMap;
	}

	public static void main(String[] args){
		int[] pre = new int[] {1, 2, 4, 5, 7, 8, 3, 6};
		int[] post = new int[] {4, 2, 7, 5, 8, 1, 3, 6};

		RebuildBinTree rtree = new RebuildBinTree(pre, post);
		Tree root = rtree.rebuild(0, rtree.getPostMap().get(pre[0]), 0, post.length - 1);

		List<Integer> preOut = new ArrayList<Integer>();
		preOrder(root, preOut);
		Common.log(preOut);
		
		List<Integer> postOut = new ArrayList<Integer>();
		inOrder(root, postOut);
		Common.log(postOut);
	}

	public void init () {
		for(int i = 0; i < post.length; i++) {
			postMap.put(post[i], i);
		}
	}

	/*
	Assume that both arrays don't have any duplicates and the tree has integer values
	(In my opion, it might not be possible to rebuild if we have duplicates in the tree)
	'pre': array as the result of a pre-order traversal
	'post': array as the result of a post-order traversal
	*/
	public Tree rebuild (int rootPre, int rootPost, int startPost, int endPost){
		if(startPost > rootPost) return null;
		if(startPost == endPost)  // leaf node
			return new Tree(post[startPost]);
		else if (startPost > endPost) 
			return null;

		int rootVal = post[rootPost];
		Tree root = new Tree(rootVal);

		int leftChildPre = rootPre + 1;		
		int numberOfLeftNodes = rootPost - startPost + 1;

		int leftValuePre = pre [leftChildPre];
		int rightValuePre = pre [rootPre + numberOfLeftNodes];


		Tree left =  rebuild ( leftChildPre, postMap.get(leftValuePre),  startPost, rootPost - 1);
		Tree right = rebuild ( rootPre + numberOfLeftNodes, postMap.get(rightValuePre), rootPost + 1, endPost);

		if(left != null)  root.left = left;
		if(right != null) root.right = right;
		return root;
	}

	// for visual testing purpose
	public static void inOrder(Tree root, List<Integer> out){
		if(root == null) return ;
		inOrder(root.left, out);
		out.add(root.value);
		inOrder(root.right, out);
	}

	public static void preOrder(Tree root, List<Integer> out){
		if(root == null) return ;
		out.add(root.value);
		preOrder(root.left, out);		
		preOrder(root.right, out);		
	}
}