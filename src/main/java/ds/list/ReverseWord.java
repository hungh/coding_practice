package ds.list;

import ds.Common;

/*
Reverse the words in a sentence. Optimize time and space
My name is chris. Chris is name My.
This is a good table. table good a is This.
albat doog a si siht.

*/
public class ReverseWord {
	public static void main(String[] args){
		String s = "My name is Steve.";
		String r = reverseWord (s);
		System.out.println(r);
	}

	public static String reverseWord(String s) {
		char[] cs = s.toCharArray();
		reverse(cs, 0, cs.length - 1);
		int start = 0, end;
		for(int i = 0; i < cs.length; i++){
			if(cs[i] == ' ' || (i == cs.length - 1)){
				if(start >= 0 ){	
					if(cs[i] != ' ') reverse (cs, start, i); else reverse(cs, start, i - 1);
					//
					start = -1;
				}
			}else if(start < 0) {
				start = i;
			}
		} 
		return new String(cs);
	}

	public static void reverse(char[] a, int s, int e){
		int i = s, j = e;
		while(i < j){
			Common.swap(a, i, j);
			i++; j--;
		}
	}


}