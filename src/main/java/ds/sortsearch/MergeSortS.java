package ds.sortsearch;

import ds.list.*;
import ds.Common;

/*
Merge sort a link list
*/
public class MergeSortS {

	public static void main(String[] args){
		LinkListGR<String> head = new LinkListGR<String>("you");
		head.next = new LinkListGR<String>("are");	
		head.next.next = new LinkListGR<String>("the");
		head.next.next.next = new LinkListGR<String>("coder");
		head.next.next.next.next = new LinkListGR<String>("ard");
		LinkListGR<String> new_head = merge_sort(head);

		LinkListGR<String> curr = new_head;
		while(curr != null) {
			Common.log(curr.value);
			curr = curr.next;
		}
	}
	
	public static LinkListGR<String> merge_sort(LinkListGR<String> head){
		if(head == null || head.next == null) return head;
		int i, middle;

		LinkListGR<String>[] list2 = split2(head);
		LinkListGR<String> firstHalf = merge_sort(list2[0]);
		LinkListGR<String> secondHalf = merge_sort(list2[1]);
		return merge(firstHalf, secondHalf);
	}


	public static LinkListGR<String>[] split2(LinkListGR<String> head){
		LinkListGR<String> prev= null;
		LinkListGR<String> slow = head;
		LinkListGR<String> fast = head;

		while(fast != null && fast.next != null){
			prev = slow;
			slow = slow.next;
			fast = fast.next.next;
		}
		prev.next = null;
		return new LinkListGR[] {head, slow};
	}



	public static LinkListGR<String> merge(LinkListGR<String> firstHalf, LinkListGR<String> secondHalf){
		if(firstHalf == null) return secondHalf;
		if(secondHalf == null) return firstHalf;
		LinkListGR<String> new_head;
		// determine the head
		if(firstHalf.value.compareTo(secondHalf.value) < 0){
			new_head = insert_list(secondHalf, firstHalf);
		} else {
			new_head = insert_list(firstHalf, secondHalf);
		} 
			
		return new_head;
	}
	
	public static LinkListGR<String> insert_list (LinkListGR<String> list2, LinkListGR<String> list1){
		LinkListGR<String> prev_curr1 = list1;
		LinkListGR<String> curr1 = list1;
		LinkListGR<String> curr2 = list2;
		while(curr2 != null && curr1 != null){
			if(curr1.value.compareTo(curr2.value) <= 0){
				prev_curr1 = curr1;
				curr1 = curr1.next;
			}else{
				LinkListGR<String> t = curr2.next;
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

}