/*
Write a function to find the middle node of a singly-linked list
*/

public class AllLists {
	public static void main(String[] args){
		int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		AllLists al = new AllLists();
		LinkList head = al.createList(a);
		al.printList(head);
		LinkList middle = al.findMiddleNode(head);
		System.out.println("Middle=" + middle.value);
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

	public void printList(LinkList head){
		LinkList curr = head;
		while(curr != null) {
			System.out.print(" " + curr.value);
			curr = curr.next;
		}
		System.out.println();
	}
}