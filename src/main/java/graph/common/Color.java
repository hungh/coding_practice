package graph.common;

public enum Color {
	WHITE(0), BLACK(1), BLUE(2), RED(3), YELLOW(4),  GREEN(5), UNCOLORED(-1);

	private int seq;
	private Color(int seq) {
		this.seq = seq;
	}

	public int toInt(){
		return this.seq;
	}

	public static String stringValue(int seq){
		for(Color e: Color.values()) {
			if(e.toInt() == seq) return e.toString();
		}
		return null;
	}
}