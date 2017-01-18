package ds;

public class Pair {
	public int a;
	public int b;
	public Pair(int a, int b){
		this.a = a;
		this.b = b;
	}

	@Override
	public String toString() {
		return "(" + a + "," + b + ") ";
	}

	public static void print(Pair[] a){
		for(Pair e: a) System.out.print("(" + e.a + "," + e.b + ") ");
		System.out.println();
	}
}