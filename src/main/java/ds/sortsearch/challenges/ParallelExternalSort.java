/*
This is an improvement of ds.sortsearch.challenges.Problem4D37.
Parallel External Sort to take advantage of Processors' cores.
Most of the code will be the same as 4D37 but has multi-threading
*/

package ds.sortsearch.challenges;

 import java.net.*;
 import java.io.*;
 import ds.PairG;
 import ds.Common;
 import ds.list.LinkListGR;
 import ds.sortsearch.MergeSortS;

public class ParallelExternalSort{
	private static final String SORTED_OUT_PATH = "/tmp/out.txt";
 	private static final String SORTED_UNIQ_PATH = "/tmp/unique_out.txt";
 	private static final String TEMP_PATH = "/tmp/c";
 	private static final int LINE_SIZE = 200;
 	/*
 	For 1.2GB of data and 2GB heap space. Best parameters are 20 4
 	*/
	public static void main(String[] args) throws Exception{
		if(args.length != 3) {
			System.err.println("Please enter file (url), memory limit (in MB) and number of threads");
			System.exit(1);
		}
		int memLimit = Integer.parseInt(args[1]);
		int numThreads = Integer.parseInt(args[2]);
		sort_large_text(args[0], memLimit* 1048576, numThreads);
 		writeUnique();

	}
	public static String scanUnique(String s, BufferedWriter out, String lastWord) throws Exception {
 		StringBuilder buffer = new StringBuilder();
 		String str;
 		char c;
 		int len = s.length();
 		for(int i = 0; i < len; i++){
 			c = s.charAt(i);
 			if(c == ' ' || c == '\n' ){
 				if(buffer.length() == 0) continue;
 				str = buffer.toString();
 				if(! str.equals(lastWord))  {
 					out.write(str + ' ');
 				}
 				lastWord = str;
 				buffer.setLength(0);
 			}else{
 				buffer.append(c);
 			}
 		}
		if(buffer.length() > 0 && !(buffer.toString().equals(lastWord))){
			str = buffer.toString();
			out.write(buffer.toString() + ' ');
			lastWord = str;
		} 
		return lastWord;		
 	}

 	// write all unique words from the sorted text file into another file
 	public static void writeUnique() throws Exception {
 		BufferedWriter writer = null;
 		BufferedReader reader = null;
 		String lastWord = null;
 		String line;
 		StringBuilder buff = new StringBuilder();
 		int i;
 		try{
 			writer = new BufferedWriter (new FileWriter (SORTED_UNIQ_PATH));	 
 			reader = new BufferedReader (new InputStreamReader ( new FileInputStream (SORTED_OUT_PATH)));

 			while ( (line = reader.readLine()) != null ) {
 				lastWord = scanUnique(line, writer, lastWord);
 			}
 		} finally {
 			writer.close();
 			reader.close();
 		}
 		
 	}

 	public static Reader getReader(String path) throws Exception{
 		if(path == null) throw new IllegalArgumentException("path name cannot be null");
 		if(path.indexOf("http") == 0){
 			URLConnection conn =  new URL (path).openConnection();
 			return new InputStreamReader( conn.getInputStream());
 		}else{
 			return new FileReader( path);
 		}
 	}
 	/*
 	read a large text file from url, write it down into 
 	small chunks and merge sorted chunks into one.
 	*/
 	public static boolean sort_large_text(String url_str, long sizeLim, int numThreads){
 		BufferedReader buff = null;
 		BufferedWriter out = null;
 		StringBuilder buffer = new StringBuilder();
 		long len = 0;
 		int chunkNum = 0;
 		int threadCount = numThreads;
 		try{
			buff = new BufferedReader (getReader(url_str));	
			out = new BufferedWriter(new FileWriter(SORTED_OUT_PATH));
			String line;
			LinkListGR<String> h;
			while( (line = buff.readLine()) != null) {
				line += " ";
				if(len + line.length() > sizeLim){
					// write out to a smaller file 
					h = toWords(buffer.toString()).a;
					// write h out into disk in a thread
					if(threadCount < 1){
						toDisk(MergeSortS.merge_sort(h), TEMP_PATH + chunkNum);
					}else {
						//TODO: sort in parallel also
						LinkListGR<String> sortedh  = MergeSortS.merge_sort(h);
						new SortToDisk(sortedh, TEMP_PATH + chunkNum).start();
						threadCount--;
					}
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
		Common.log("in memory size=" + (double)(inMemSize/1000000.0) + ";MB");

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
 				if( tc < 65 || tc > 122 || tc == '_'){
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
 		trimInvalidChars(s);
 		int len = s.length();
 		char c;
 		if(len == 0) return false;
 		for(int i = 0; i < len; i++){
 			c = s.charAt(i);
 			if(c == '\'' || c == '-' || c =='_') continue;
 			if(c < 65 || c > 122) return false;
 		}
 		return true;
 	}

 	private static void trimInvalidChars(StringBuilder s){
 		if(s == null) return;
 		int b = 0, e = s.length() - 1, olde = e;
 		char cb, ce;
 		while(b <= e){
 			cb = s.charAt(b);
 			olde = e;
 			if(cb < 65 || cb > 122 || cb == '_') {
 				s.deleteCharAt(b);
 				e--;
 			}
 			if(e < 0) break;
 			ce = s.charAt(e);
 			if(ce < 65 || ce > 122 || ce == '_'){
 				s.deleteCharAt(e);
 				e--;
 			}
 			if(olde == e) break;

 		}
 	}

 	private static class SortToDisk extends Thread {
 		private LinkListGR<String> list;
 		private String path;
 		public SortToDisk(LinkListGR<String> list, String path){
 			this.list = list;
 			this.path = path;
 		}

 		@Override
 		public void run (){
 			try{
 				Common.log(new StringBuilder("Sort and save into disk in a thread for path:").append(path));
 				toDisk(list, path);
 			}catch(Exception e){
 				e.printStackTrace();
 			}
 			
 		}
 	}
}