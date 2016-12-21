package ds.list;

import ds.Common;
// Reverse a linked list with/without recursion
public class ReverseList {
	public static void main(String[] args){
		int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		AllLists al = new AllLists();
		LinkList head = al.createList(a);

		AllLists.printList(head);		
		LinkList new_head = reverse_(head);	
		AllLists.printList(new_head);

		Common.log("The non-recursive solution- back to normal");
		LinkList orignal = reverse(new_head);
		AllLists.printList(orignal);
	}

	// recursion solution
	public static LinkList reverse_ (LinkList head){
		if(head == null) return null;
		return p_reverse (null, head);
	}

	private static LinkList p_reverse(LinkList a, LinkList b){				
		if(b == null)  return a; // new head

		LinkList bNext = b.next;
		b.next = a;
		if(bNext != null){			
			return p_reverse(b, bNext);	
		}else{
			return b;
		}		
	}

	// without recursion solution
	public static LinkList reverse(LinkList head){
		LinkList prev = null;
		LinkList next = head;

		LinkList nnext;
		while(next != null) {
			nnext = next.next;
			next.next = prev;
			//
			prev = next;
			next = nnext;
		}
		return prev;
	}
}