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
import ds.tree.TreeToList;

public class RebuildBinTree {

	private Map<Integer, Integer> postMap = new HashMap<Integer, Integer>();

	private int[] pre;
	private int[] post;
	private int l;


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
		TreeToList.printTreeAsList(root);
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
	public Tree rebuild (int rootPosPre, int rootPosPost, int posStart, int posEnd){

		Common.log("[" + rootPosPre + ", " +  rootPosPost + "]" + "- [" + posStart + ", " + posEnd + "]");
		 // if(l >= 40) return null;
		 // l++;

		 if(posStart > rootPosPost ){
		 	Common.log("Exit early");
		 	return null;

		 } 

		// if(rootPosPre >= pre.length || rootPosPost >= post.length) return null;

		if(posStart == posEnd) { // leaf node
			return new Tree(post[posStart]);

		} else if (posStart > posEnd) {
			return null;
		}

		int rootVal = post[rootPosPost];
		Tree new_root = new Tree(rootVal);

		//  next one in pre list
		int left_root_pos = rootPosPre + 1;

		//  next one in the pos list
		int right_root_pos = postMap.get(rootVal) + 1;

		// how many elements are there on the left sub-tree
		int numberLeftElems = rootPosPost - posStart;

		int leftRootVal = pre [left_root_pos];
		int rightRootVal = pre [numberLeftElems + 1];


		Tree left = rebuild(left_root_pos, postMap.get(leftRootVal),  posStart, rootPosPost - 1);
		Tree right = rebuild(numberLeftElems + 1, postMap.get(rightRootVal), rootPosPost + 1, posEnd);

		if(left != null) {
			new_root.left = left;
		} else{
			Common.log("Left is null");
		}
		if(right != null) {
			new_root.right = right;
		}else{
			Common.log("right is null ");
		}
		Common.log("new root=" + new_root.value);
		return new_root;
	}

}