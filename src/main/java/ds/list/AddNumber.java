package ds.list;

/**
Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.
*/


public class AddNumber {
	public static void main(String[] args) {
		System.out.println("Adding 2 numbers...");
	}

	private static int calculate(ListNode l1, ListNode l2) {
		if(l1 == null || l2 == null) {
			throw new IllegalArgumentException("Invalid input for list: must be not null.");
		}
		//TODO: 
		return 0;
	}
}




public class ListNode {
    int val;
	ListNode next;
    ListNode(int x) { val = x; }
}