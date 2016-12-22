/*
Write a function to find the middle node of a singly-linked list
*/
package ds.list;

public class AllLists {
	public static void main(String[] args){
		int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		AllLists al = new AllLists();
		LinkList head = al.createList(a);
		printList(head);
		LinkList middle = al.findMiddleNode(head);
		System.out.println("Middle=" + middle.value);

		testCyclicLinkList();
		testCyclicLinkList2();
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

	public LinkList createList(int[] a){
		LinkList head = new LinkList(a[0]);
		LinkList curr = head;
		for(int i = 1; i < a.length; i++) {
			LinkList nextNode = new LinkList(a[i]);
			curr.next = nextNode;
			curr = nextNode;
		}	
		return head;	
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