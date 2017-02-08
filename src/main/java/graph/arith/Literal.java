package graph.arith;

public class Literal implements AValue {
	protected int v;
	public Literal(int v) {
		this.v = v;
	}
	public int eval(){
		return v;
	}
}