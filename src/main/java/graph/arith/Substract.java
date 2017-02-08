package graph.arith;

public class Substract extends Op {
	public Substract(AValue op1, AValue op2){
		super(op1, op2);
	}

	public int eval(){
		return op1.eval() - op2.eval();
	}
}