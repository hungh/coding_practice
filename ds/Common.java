package ds;
import java.util.List;

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

	public static void printArray(Object[] a){
		for(Object e: a) System.out.println(" " + e);
		System.out.println();
	}

	public static int[] toArray(List<Integer> a){
		int[] r = new int[a.size()];
		int i = 0;
		for(Integer e: a) {
			r[i++] = e.intValue();
		}
		return r;
	}

	public static int max(int a, int b){
		return (a > b)? a: b;
	}

}