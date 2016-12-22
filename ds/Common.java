package ds;
public class Common {
	public static void log(Object obj) {
		System.out.println(obj);
	}

	public static void _log(Object obj) {
		System.out.print(obj);
	}

	public static void swap(char[] s, int i, int j){
		char temp = s[i];
		s[i] = s[j];
		s[j] = temp;

	}

	public static void printArray(int[] a){
		for(int e: a) System.out.print(" " + e);
		System.out.println();
	}

}