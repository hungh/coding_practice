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
 // KEY WORD: external search

 package ds.sortsearch.challenges;

 import java.net.*;
 import java.io.*;
 import ds.Common;
 import ds.list.LinkListGR;
 import ds.sortsearch.MergeSortS;

 public class Problem4D37 {

 	private static final String TEMP_PATH = "/tmp/c";

 	public static void main(String[] args){
 		sort_large_text("http://www.gutenberg.org/files/2600/2600-0.txt", 800000);
 	}

 	/*
 	read a large text file from url, write it down into 
 	small chunks and merge sorted chunks into one.
 	*/
 	public static boolean sort_large_text(String url_str, long sizeLim){
 		URLConnection conn = null;
 		BufferedReader buff = null;
 		BufferedWriter out = null;
 		StringBuilder buffer = new StringBuilder();

 		long len = 0;
 		int chunkNum = 0;
 		try{
 			URL url = new URL (url_str);
 			conn = url.openConnection();
			buff = new BufferedReader (new InputStreamReader( conn.getInputStream()));	

			out = new BufferedWriter(new FileWriter("/tmp/out.txt"));
			String line;
			LinkListGR<String> h;
			while( (line = buff.readLine()) != null) {
				line += " ";
				
				if(len + line.length() > sizeLim){
					// write out to a smaller file 
					h = toWords(buffer.toString());
					
					// write h out into disk
					toDisk(MergeSortS.merge_sort(h), TEMP_PATH + chunkNum);
					// adjust buffer
					buffer.setLength(0);
					len = 0;
					chunkNum++;
				}
				buffer.append(line);
				len += line.length();
			}

			// last chunk
			if(buffer.length() > 0){
				h = toWords(buffer.toString());
				toDisk(MergeSortS.merge_sort(h), TEMP_PATH + chunkNum);
			}
			// merge
			mergeFromDisk(chunkNum, sizeLim, out);

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
 	// k-way list merging
 	public static LinkListGR<String> kWayMerge(LinkListGR<String>[] one, int start, int end){
 		if(start > end) return null;
 		if(end == start) return one [start];
 		if(end - start == 1) {
 			return MergeSortS.merge(one[start], one[end]);
 		}
 		return MergeSortS.merge( kWayMerge(one, start, (end + start)/2), kWayMerge (one, (end + start)/2 + 1, end) );
 	}

 	//TODO
 	public static void mergeFromDisk(int chunkNum, long sizeLim, BufferedWriter out) throws Exception {
 		BufferedReader[] mReaders = null;
 		// merge all chunks together
		// we need 1 chunk for the merge results
		mReaders = new BufferedReader[chunkNum];
		long inMemSize = (long) sizeLim / (chunkNum + 1);
		if(inMemSize < 900000)  inMemSize = 900000; // hack  for problem when file is not big enough
		LinkListGR[] buffs = new LinkListGR[chunkNum]; 
		LinkListGR[] currs = new LinkListGR[chunkNum]; 
		String line;
		int i;
		boolean exshausted = false;
		Common.log("in memory size=" + inMemSize);

		// init all readers
		for(i = 0; i < chunkNum; i++){
			mReaders [i] = new BufferedReader (new InputStreamReader( new FileInputStream(TEMP_PATH + i) ));
		}
		int buffLen;
		LinkListGR<String> tmp;
		while(!exshausted) {
			exshausted = true;
			for(i = 0; i < chunkNum; i++) {
				buffLen = 0;
				while( (line = mReaders[i].readLine()) != null) {
					exshausted = false;
					if(buffLen + line.length() < inMemSize) {
						tmp = new LinkListGR<String>(line);
						if(currs[i] == null){
							currs[i] = tmp;
							buffs[i] = tmp; // head
						}else{
							currs[i].next = tmp;
							currs[i] = currs[i].next;
						}
						buffLen += line.length();
						
					}else {
						break;
					}
					
				}
			}


			// write to disk
			toDisk( kWayMerge(buffs, 0, buffs.length - 1), out) ;
			// reset the buffer
			for(i = 0; i < buffs.length; i++) buffs[i] = null;
		}
 	}

 	public static void toDisk(LinkListGR<String> h, String filePath) throws Exception{
 		toDisk (h, new BufferedWriter(new FileWriter(filePath)));
 	}

 	public static void toDisk(LinkListGR<String> h, BufferedWriter out) throws Exception{
 		if(h == null) return;
 		try{
	 		LinkListGR<String> curr = h;
	 		while(curr != null){
	 			out.write(curr.value + " ");
	 			curr = curr.next;
	 		}
	 	}finally {
	 		try{
	 			out.close();
	 		}catch(Exception e) {}
	 	}
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
