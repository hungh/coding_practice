package ds.list;

/**
Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.
*/


public class AddNumber {
	public static void main(String[] args) {
		System.out.println("Adding 2 numbers...");
		ListNode l1 = null; //TODO: create list
		ListNode l2 = null; //TODO: create list
		ListNode r = calculate(l1, l2);
		// printList(r);
	}

	private static ListNode calculate(ListNode l1, ListNode l2) {
		ListNode resultNode = null;
		ListNode resultTemp = resultNode;
		ListNode t1, t2;
		int c1 = 0, c2 = 0;
		int total = 0;
		int p, s;
		while(true) {
			t1 = l1.next;
			t2 = l2.next;

			if(t1 != null) {
				c1 += (t1.val + c2);
			} else {
				c1 = c2;
			}

			if(t2 != null) {
				c2 += (t2.val + c1);
			} else {
				c2 = c1;
			}

			if( (c1 == 0) && (c2 == 0)) {
				return resultNode;	
			}

			s = (c1  + c2);
			if(s < 10) {
				p = s;
				c1 = 0; 
				c2 = 0;
			} else {
				p = s % 10;
				c2 = 0;
				c1 = (s - p);
			}

			if(resultNode == null) {
				resultNode = new ListNode(p);	
				resultTemp = resultNode;
			} else {
				resultTemp.next = new ListNode(p);
			}
				

		}	
		
	}

	private static class ListNode {
	    int val;
		ListNode next;
	    ListNode(int x) { val = x; }
	}


}


