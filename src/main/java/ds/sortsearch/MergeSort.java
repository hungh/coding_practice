package ds.sortsearch;

import ds.list.*;

/*
Merge sort a link list
*/
public class MergeSort {
	public static void main(String[] args){
		testMerge();
		splitTest();
		mergeSortTest();
	}

	public static LinkList merge_sort(LinkList head){
		if(head == null || head.next == null) return head;
		int i, middle;

		LinkList[] list2 = split2(head);
		LinkList firstHalf = merge_sort(list2[0]);
		LinkList secondHalf = merge_sort(list2[1]);
		return merge(firstHalf, secondHalf);
	}


	public static LinkList[] split2(LinkList head){
		LinkList prev= null;
		LinkList slow = head;
		LinkList fast = head;

		while(fast != null && fast.next != null){
			prev = slow;
			slow = slow.next;
			fast = fast.next.next;
		}
		prev.next = null;
		return new LinkList[] {head, slow};
	}



	public static LinkList merge(LinkList firstHalf, LinkList secondHalf){
		if(firstHalf == null) return secondHalf;
		if(secondHalf == null) return firstHalf;
		LinkList new_head;
		// determine the head
		if(firstHalf.value < secondHalf.value){
			new_head = insert_list(secondHalf, firstHalf);
		} else {
			new_head = insert_list(firstHalf, secondHalf);
		} 
			
		return new_head;
	}
	
	// insert a list1 into list2 (the first element of list1 < of list2)
	public static LinkList insert_list (LinkList list2, LinkList list1){
		LinkList prev_curr1 = list1;
		LinkList curr1 = list1;
		LinkList curr2 = list2;
		while(curr2 != null && curr1 != null){
			if(curr1.value <= curr2.value){
				prev_curr1 = curr1;
				curr1 = curr1.next;
			}else{
				LinkList t = curr2.next;
				// insert before curr2 before curr1
				prev_curr1.next = curr2;
				curr2.next = curr1;

				// shift forward
				prev_curr1 = curr2;
				curr2 = t;
			}
		}
		// if list1's size < list2's size
		if(curr1 == null) prev_curr1.next = curr2;
		return list1;
	}


	// TEST CASES

	public static void mergeSortTest() {
		int[] a = {4, 1, 2, 7, 8, 3, 2, 1, 6, 7, 8, 11, 23, 21, 54, 12};
		LinkList head = merge_sort(AllLists.createList(a));
		AllLists.printList(head);


		int[] a1 = {4, 1};
		LinkList head1 = merge_sort(AllLists.createList(a1));
		AllLists.printList(head1);

		int[] a2 = {43};
		LinkList head2 = merge_sort(AllLists.createList(a2));
		AllLists.printList(head2);
	}


	public static void splitTest(){
		int[] a = {1, 2};
		LinkList head = AllLists.createList(a);
		AllLists.printList(head);
		LinkList[] h2 = split2(head);
		
		AllLists.printList(h2[0]);
		AllLists.printList(h2[1]);
	}
	public static void testMerge(){
		int[] a1 = {1, 2, 4, 6, 6, 8, 12, 14};
		int[] a2 = {1, 2, 3, 4, 5, 6, 7, 12, 15, 16};
		LinkList f = AllLists.createList(a1);

		LinkList s = AllLists.createList(a2);
		LinkList h = insert_list(s, f);
		AllLists.printList(h);
	}


}