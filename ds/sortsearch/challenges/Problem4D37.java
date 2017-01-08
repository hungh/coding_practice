/*
4-37. Implement versions of several different sorting algorithms,
 such as selection sort, insertion sort, heapsort, mergesort, and quicksort.
 Conduct experiments to assess the relative performance of these algorithms in 
 a simple application that reads a large text file and reports exactly one instance of each word that 
 appears within it. 
 This application can be efficiently implemented by sorting all the words that occur in the text and then passing through 
 the sorted sequence to identify one instance of each distinct word. Write a brief report with your conclusions.
*/
 // KEY WORD: external search
 package ds.sortsearch.challenges;

 import java.net.*;
 import java.io.*;
 import ds.PairG;
 import ds.Common;
 import ds.list.LinkListGR;
 import ds.sortsearch.MergeSortS;

 public class Problem4D37 {

 	private static final String TEMP_PATH = "/tmp/c";
 	private static final int LINE_SIZE = 200;

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
					h = toWords(buffer.toString()).a;
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
				h = toWords(buffer.toString()).a;
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

 	public static void mergeFromDisk(int chunkNum, long sizeLim, BufferedWriter out) throws Exception {
 		// merge all chunks together
		// we need 1 chunk for the merge results
		BufferedReader[] mReaders = new BufferedReader[chunkNum];
		long inMemSize = (long) sizeLim / (chunkNum + 1);
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
		PairG<LinkListGR<String>, LinkListGR<String>> tmp;
		while(!exshausted) {
			exshausted = true;
			for(i = 0; i < chunkNum; i++) {
				buffLen = 0;
				while((buffLen < inMemSize) && (line = mReaders[i].readLine()) != null) {
					exshausted = false;
					tmp = toWords(line);
					if(tmp.a == null) continue;
					if(buffs[i] == null){
						buffs[i] = tmp.a; // head
						currs[i] = tmp.b;						
					}else{
						currs[i].next = tmp.a;
						currs[i] = tmp.b;
					}
					buffLen += line.length();
				}
			}
			// write to disk
			toDisk( kWayMerge(buffs, 0, buffs.length - 1), out) ;
			// reset the buffer
			for(i = 0; i < buffs.length; i++) { buffs[i] = null; currs[i] = null; }
		}
		for(i = 0; i < chunkNum; i++) mReaders [i].close();
 	}

 	public static void toDisk(LinkListGR<String> h, String filePath) throws Exception{
 		toDisk (h, new BufferedWriter(new FileWriter(filePath)));
 	}

 	public static void toDisk(LinkListGR<String> h, BufferedWriter out) throws Exception{
 		if(h == null) return;
		long len = 0;
 		LinkListGR<String> curr = h;
 		while(curr != null){
 			len += curr.value.length();
 			if(len > LINE_SIZE){
 				out.write(curr.value + '\n');	
 				len = 0;
 			} else {
 				out.write(curr.value + ' ');
 			}
 			curr = curr.next;
 		}
 	}

 	// parse a string into words 
 	// return head and tail
 	public static PairG<LinkListGR<String>, LinkListGR<String>>  toWords(String s){
 		// can use String.split, but here we do a little different
 		if(s == null) return null;
 		LinkListGR<String> head = null;
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
 				
 				if(lastc >= 0) { 	 				
 					if(curr == null){
 						curr = new LinkListGR<String>(null); 
 						if(prev == null) {
 							head = curr; prev = curr;  
 						}else{
 							prev.next = curr;	
 						} 						
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
 						if(prev == null) {
 							head = curr;
 							prev = curr;
 						}else{
 							prev.next = curr;	
 						} 						
 					}
 					
 				}
 			}
 		}
 		LinkListGR<String> tail;
 		if(curr == null) tail = prev; else tail = curr;
 		return new PairG<LinkListGR<String>, LinkListGR<String>>(head, tail);
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
