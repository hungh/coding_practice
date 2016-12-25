/*
Write a function to find the middle node of a singly-linked list
*/
package ds.list;

import ds.Common;

public class AllLists {
	public static void main(String[] args){
		int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		AllLists al = new AllLists();
		LinkList head = createList(a);
		printList(head);
		LinkList middle = al.findMiddleNode(head);
		System.out.println("Middle=" + middle.value);

		testCyclicLinkList();
		testCyclicLinkList2();

		// findProduct
		int[] arr = {1, 2, 3, 4};
		int[] m = findProduct(arr);
		Common.printArray(m);

	}

	public static void testCyclicLinkList(){
		LinkList head = new LinkList (1);
		head.next = new LinkList(2);
		head.next.next = new LinkList(3);
		head.next.next = new LinkList(4);
		// head.next.next.next = head;

		int lloc = findLoopLocation(head);
		System.out.println("Loop location:" + lloc);
	}

	public static void testCyclicLinkList2(){
		LinkList head = new LinkList (1);
		head.next = new LinkList(2);
		head.next.next = new LinkList(3);
		head.next.next = new LinkList(4);
		head.next.next.next = head;

		int lloc = findLoopLength(head);
		System.out.println("Loop location_2:" + lloc);
	}

	public LinkList findMiddleNode(LinkList head){
		LinkList curr = head;
		LinkList jumper = curr;

		while(jumper.next != null){
			curr = curr.next;
			// jumper moves twice
			jumper = jumper.next;
			if(jumper != null) jumper = jumper.next; else break;
		}
		return curr;
	}

	/*
	3-27. [5] Determine whether a linked list contains a loop as quickly as possible without using any extra storage. 
	Also, identify the location of the loop.
	2 -> 3 -> 4 -> 5 -> 6
	*/
	public static int findLoopLocation(LinkList head){
		if(head == null) return 0;
		LinkList curr = head;
		LinkList next = curr.next;

		while(next != null){

			if(next == head) return next.value;;
			curr = next;
			next = curr.next;
		}
		return Integer.MIN_VALUE;
	}

	/*
	Returns the length of the loop if there is
	*/
	public static int findLoopLength(LinkList p){
		if(p == null) return 0;
		LinkList slow = p;
		LinkList fast = slow;
		int len = 0;

		while(slow != null){
			if(fast.next != null) fast = fast.next.next; else {
				return 0;
			}
			if(slow == fast) return len - 1;
			slow = slow.next;
			len += 2;
		}
		// no loop found
		return -1;
	}

	public static LinkList createList(int[] a){
		LinkList head = new LinkList(a[0]);
		LinkList curr = head;
		for(int i = 1; i < a.length; i++) {
			LinkList nextNode = new LinkList(a[i]);
			curr.next = nextNode;
			curr = nextNode;
		}	
		return head;	
	}
	/* (Algorithm Design and Manual)
	3-28. You have an unordered array X of n integers. 
	Find the array M containing n elements where Mi is the product of all integers in X except for Xi. 
	You may not use division. You can use extra memory. (Hint: There are solutions faster than O(n2).)
	X { n integers }
	Find M {n}, where Mi = X0*X1*...XN-1
	example
	X = { 2, 3, 4, 5, 6}
	M0 = (3*4*5*6) = C1,N 
	M1 = (2*4*5*6) = C0,0 * C2,N
	M2= (2*3*5*6) = C0,1 * C3,N
	M3 = C0,2 * C4,N
	M4 = C0,3 * C5,N
	M5 = C0,4 * C6,N
	M6 = C0,5 * C7,N
	Observation:
	C0,2 = C0,1 * C2
	C0,3 = C0,2 * C3
	C0,4 = C0,3 * C4
	..
	C1,N = C1 * C2,N
	C(n-2, n) = C(n-2) * C(N-1)
	C(n-1, n) = C(n-1) * Cn
	Cn = n

	...
	*/
	public static int[] findProduct(int[] a){
		if(a == null) return a;
		int[] m = new int [a.length];
		int[] c = new int[a.length];
		int[] q = new int[a.length];
		// calculate M0
		int i;	
		c[0] = a[0];
		for(i = 1; i < a.length - 1; i++){
			c[i] = c[i - 1] * a[i];
		}

		q[a.length - 1] = a[a.length - 1]; 
		for(i = a.length - 2; i >= 0; i--){
			q[i] = a[i] * q[i + 1];
		}
		
		for(i = 0; i < a.length; i++){
			if(i == 0) {
				m[i] = q[i+ 1];
			}else if(i == a.length - 1){
				m[i] = c[i - 1];
			}else{
				m[i] = c[i-1] * q[i+ 1];		
			}		
		}
		return m;

	}

	public static void printList(LinkList head){
		LinkList curr = head;
		while(curr != null) {
			System.out.print(" " + curr.value);
			curr = curr.next;
		}
		System.out.println();
	}
}