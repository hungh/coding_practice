package graph.arith;

public class Multiply extends Op {
	public Multiply(AValue op1, AValue op2){
		super(op1, op2);
	}

	public int eval(){
		return op1.eval() * op2.eval();
	}
}