/*
4-37. Implement versions of several different sorting algorithms,
 such as selection sort, insertion sort, heapsort, mergesort, and quicksort.
 Conduct experiments to assess the relative performance of these algorithms in 
 a simple application that reads a large text file and reports exactly one instance of each word that 
 appears within it. 
 This application can be efficiently implemented by sorting all the words that occur in the text and then passing through 
 the sorted sequence to identify one instance of each distinct word. Write a brief report with your conclusions.
*/

 // I only implement merge sort to sort a large text file 
 // write it into a disk
 // read the sorted file to scan for each instance of words in the file

 // assume that we have limited memory size 
 // text is from http://www.gutenberg.org/files/2600/2600-0.txt

 package ds.sortsearch.challenges;

 import java.net.*;
 import java.io.*;
 import ds.Common;
 import ds.list.LinkListGR;
 import ds.sortsearch.MergeSortS;

 public class Problem4D37 {
 	public static void main(String[] args){
 		// sort_large_text("http://www.gutenberg.org/files/2600/2600-0.txt");
 		LinkListGR<String> h = toWords("This is a   \n\n\n  \n  test.   \n   Please do not take it seriously! Bye.  \n  \n\n\n");
 		LinkListGR<String> curr = h;
 		while(curr != null){
 			Common.log(curr.value);
 			curr = curr.next;
 		}
 	}

 	/*
 	read a large text file from url, write it down into 
 	small chunks and merge sorted chunks into one.
 	*/
 	public static boolean sort_large_text(String url_str){
 		URLConnection conn = null;
 		BufferedReader buff = null;
 		BufferedWriter out = null;
 		try{
 			URL url = new URL (url_str);
 			conn = url.openConnection();
			buff = new BufferedReader (new InputStreamReader( conn.getInputStream()));	

			out = new BufferedWriter(new FileWriter("out.txt"));
			String line;
			while( (line = buff.readLine()) != null) {
				out.write(line + " ");
			}
 		}catch(Exception e) {
 			e.printStackTrace();
 		}finally {
 			try{
 				if(out != null) out.close();
 				if(buff != null) buff.close();	
 			}catch(Exception e1){
 				e1.printStackTrace();
 			}
 			
 		} 
 		return true;
 			
 	}

 	// parse a string into words
 	public static LinkListGR<String> toWords(String s){
 		// can use String.split, but here we do a little different
 		if(s == null) return null;
 		LinkListGR<String> head = new LinkListGR<String>("");
 		LinkListGR<String> curr = head;
 		char sc; //  char
 		int lastc = -1;; // last nonspace char
 		int space_idx = -1;
 		StringBuilder mbuff = new StringBuilder();
 		for(int i = 0; i < s.length(); i++) {
 			// ignore special characters
 			sc = s.charAt(i);
 			
 			// space 
 			if(sc == ' ' || sc == '\n'){
 				space_idx = i;
 				if(lastc > 0) {
 					String buffStr = mbuff.toString();
 					// Common.log(mbuff);
 					curr.value = buffStr; 
 					curr.next = new LinkListGR<String>("");
 					curr = curr.next;
 					// create a new buffer
 					mbuff = new StringBuilder();
 				}
 				lastc = -1;
 			}else{
 				lastc  = i;
 				space_idx = -1; // no space yet
 				mbuff.append(s.charAt(i));
 				if(i == s.length() - 1) { // last word
 					curr.value = mbuff.toString();
 				}
 			}

 		}
 		
 		return head;
 	}


 } 		
