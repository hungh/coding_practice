/*
12 coins : indentify the lighter or heavier coin
Simulation of the brain teaser 12 coin problem. 
Number of times to weight is 3
**/

package ds.sortsearch.intv;

import ds.Common;

public class TwelveCointTeaser {
	public static void main(String[] args){
		if(args.length != 12) {
			System.err.println("Please enter 12 numbers. 11 of them are the same.");
			System.exit(1);
		}
		int fPos = getFakeCoint(toInts(args));
		Common.log("Fake coint position=" + fPos);
	}

	public static int getFakeCoint(int[] a){
		int fL = weight(a, 1, 2, 3, 4);
		int fR = weight(a, 5, 6, 7, 8);
		// we spent 1 time already here (a scale has 2 sides)
		if(fL == fR){
			// one of 9-12  is a fake one - 2nd weighing
			fL = weight(a, 8, 9); 
			fR = weight(a, 10, 11);
			if(fL == fR){
				if(a[12] > a[1]) Common.log("Heavier One Found."); else Common.log("Ligher one Found"); // 3 weighing
			 	return 12;
			}else if(fL > fR){
				// eight 9 is heavier or (10 is lighter or 11 is lighter)
				if(a[10] == a[11]) {
					Common.log("Heavier one Found");
					return 9;
				}else if(a[10] > a[11]) {
					Common.log("Ligher");
					return 11;
				}else {
					Common.log("Ligher");
					return 10;
				}
				
			}else{
				// either (10 or 11) is heaver or 9 is lighter 
				if(a[10] == a[11]) {
					Common.log("Ligher one Found");
					return 9;
				} else if(a[10]> a[11]){
					Common.log("Heavier found");
					return 10;
				} else{
					Common.log("Heavier found");
					return 11;
				}
			}

		} else if (fL > fR){
			// 1 2 3 4 > 5 6 7 8 
			// 9 -- 12 are all good
			fL = weight(a, 1, 2, 5);
			fR = weight(a, 3, 6, 9);// 9 is a good one
			if(fL == fR){ // 4 is heavier or (7 or 8 is lighter)
				if(a[7] == a[8]) {
					Common.log("Heavier found");
					return 4;
				}else if(a[7] > a[8]) {
					Common.log("Ligher found");
					return 8;
				}else{
					Common.log("Ligher found");
					return 7;
				}

			}else if (fL > fR){
				// 1 2  5 (good) > 3 (good) 6 9
				// either ( 1 or 2 is heaver ) or 6 ligher
				if(a[1] == a[2]){
					Common.log("Ligher Found");
					return 6;
				}else if (a[1] > a[2]){
					Common.log("HEavier found");
					return 1;
				}else{
					Common.log("HEavier found");
					return 2;
				}
			}else{
				// 1(good) 2(good) 5 < 3 6(good) 9(food)
				if(a[5] == a[9]) {
					Common.log("Heavier Found");
					return 3;
				}else if(a[5] < a[9]){
					Common.log("Ligher found");
					return 5;
				} 
			}

		} else {
			// 1 2 3 4 < 5 6 7 8  (9 -12 are good)
			fL = weight(a, 4, 5, 6);
			fR = weight(a, 3, 7, 9);
			if(fL == fR){
				// 1 or 2 is ligher or 8 is heavier
				if(a[1] == a[2]){
					Common.log("Heavier found");
					return 8;
				} else if(a[1] < a[2]) {
					Common.log("Ligher found");
					return 1;
				} else{
					Common.log("Ligher found");
					return 2;
				}
			} else if (fL < fR) {
				// 4 5(G)  6(G)< 3(G) 7 9(G)
				if(a[4] < a[9]){
					Common.log("Ligher found");
					return 4;
				} else if(a[4] == a[9]){
					Common.log("Heavier found");
					return 7;
				}

			} else {
				// 4(G) 5 6 > 3 7(G) 9(G)
				if(a[5] == a[6]) {
					Common.log("Lighter foud");
					return 3;
				} else if(a[5] > a[6]){
					Common.log("Heaiver foud");
					return 5;
				} else {
					Common.log("Heaiver foud");
					return 6;
				}
			}
		}
		return -1;
	}

	private static int[] toInts(String[] args){
		// we are not using index 0
		int[] r = new int[args.length + 1];
		int i = 1;
		for(String arg: args) r[i++] = Integer.parseInt(arg);
		return r;
	}

	private static int weight(int[] a, int... pos){
		int total = 0;
		for(int i = 0; i < pos.length; i++) total += a[pos[i]];
		return total;
	}

}



