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
 		LinkListGR<String> h = toWords("Hello Word. 123 You've completed.");
 		LinkListGR<String> curr = h;
 		while(curr != null){
 			System.out.print(curr.value + " ");
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
 		LinkListGR<String> head = new LinkListGR<String>(null); 		
 		LinkListGR<String> curr = head;
 		LinkListGR<String> prev = curr;
 		char sc; //  char
 		int lastc = -1;// last nonspace char
 		int space_idx = -1;
 		int lastInvalidIndex = -1;
 		String tmp;
 		StringBuilder mbuff = new StringBuilder();
 		for(int i = 0; i < s.length(); i++) { 			
 			sc = s.charAt(i); 			

 			if(sc == ' ' || sc == '\n' || sc == '\t'){
 				space_idx = i;

 				if(lastInvalidIndex >= 0){
 					if(! isValidWord(mbuff, lastInvalidIndex)){
						mbuff.setLength(0); 						
 						lastc = -1; 						
 					}
 					lastInvalidIndex = -1; 					
 				}
 				
 				if(lastc > 0) { 	 					
 					if(curr == null){
 						curr = new LinkListGR<String>(null); 		
 						prev.next = curr;
 					} 
	 				curr.value = mbuff.toString();	
 					prev = curr;
 					curr = curr.next;
 					mbuff = new StringBuilder();
 				}
 				lastc = -1; 				

 			}else{
 				lastc  = i;
 				space_idx = -1; // no space yet
 				char tc = s.charAt(i); 				
 				if( tc < 65 || tc > 122){
 					lastInvalidIndex = mbuff.length();
 				}
 				mbuff.append(tc);
 				if(i == s.length() - 1) { // last word
 					if(isValidWord(mbuff, lastInvalidIndex)){
 						curr = new LinkListGR<String>(mbuff.toString()); 
 						prev.next = curr;
 					}
 					
 				}
 			}

 		}
 		
 		return head;
 	}

 	private static boolean isValidWord(StringBuilder s, int lastInvalidIndex){
 		char tc;
 		if(lastInvalidIndex == 0){
 			tc = s.charAt(0);
 			if(tc != '"' && tc != '\'' ) return false;
 			s.deleteCharAt(0);
 		}
 		int len = s.length(); 		
 		tc = s.charAt(len - 1);
 		if(lastInvalidIndex == len - 1){
 			if ( tc != '!' && tc != '?' && tc != '.' && tc != ')' && tc != ';' 
 				&& tc != ',' && tc == '\'' && tc == '"') return false;
 			s.deleteCharAt(lastInvalidIndex);
 			lastInvalidIndex--;
 		}
 		
 		for(int i = lastInvalidIndex; i >= 0; i--){
 			tc = s.charAt(i); 			
 			if(tc != '-' && tc != '\'' && (tc < 65 || tc > 122)) return false;
 		}
 		return true;
 	}

 } 		
