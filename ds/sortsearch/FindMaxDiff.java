/*
4.2
(a) Let S be an unsorted array of n integers. Give an algorithm that finds the pair
x, y ∈ S that maximizes |x − y|. Your algorithm must run in O(n) worst-case time
*/

package ds.sortsearch;

public class FindMaxDiff {
	public static void main(String[] args){
		int[] a = {6, 13, 19, 5, 4, 9, 3, 2, 6, 9, 4, 3, 2};
		int[] ret = findMaxDiffPair(a);
		System.out.println("min_idex=" + ret[0] +  ";max_index=" + ret[1] +  ";max_diff= " + ret[2]);
	}

	// scan a group of 3 items, get rid of the middle one
	// repeat the process until the end of the array
	public static int[] findMaxDiffPair(int[] a){
		if(a == null || a.length < 2) throw new IllegalArgumentException("Invalid array length.");
		int[]  maxRet = new int[3]; 
		if(a.length == 2){
			if(a[0] < a[1]) {
				maxRet = new int[]{0, 1, a[1] - a[0]};
			} else{
				maxRet = new int[]{1, 0, a[0] - a[1]};
			}
			return maxRet;
		} 

		
		int[] ret = getMaxIn3(a, 2, 0, 1);
		int s = ret[0], l = ret[1];
		maxRet[0] = ret[0]; maxRet[1] = ret[1]; maxRet[2] = ret[2];
		for(int i = 3; i < a.length; i++) {
			ret = getMaxIn3(a, i, s, l);			
			s = ret[0];
			l = ret[1];
			if(ret[2] > maxRet [2]) {
				maxRet[0] = ret[0];
				maxRet[1] = ret[1];
				maxRet[2] = ret[2];
			}
		}
		return maxRet;
	}

	public static int[] getMaxIn3(int[] a, int... i){
		int min = a[i[0]], max = a[i[0]];
		int minIdx = i[0] , maxIdx = i[0];
		for(int j = 0; j < 3; j++){
			if(a[i[j]] < min) {
				min = a[ i[j] ];
				minIdx = i[j];	
			} 
			if(a[ i[j] ] > max) {
				max = a[ i[j]];
				maxIdx = i[j];
			}
		}
		int[] ret = {minIdx, maxIdx, (max - min)};
		return ret;
	}
}

