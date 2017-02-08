package graph.arith;

public abstract class Op implements AValue {
	protected AValue op1;
	protected AValue op2; 

	public Op(AValue op1, AValue op2){
		this.op1 = op1;
		this.op2 = op2;
	}
}