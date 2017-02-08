/*
Generic graph which has V Verteices and E Edges
Supports ArithVertex
*/

package graph.arith;

import ds.Common;

public class GraphAR {

	public static void main(String[] args){
		AValue two = new Literal(2);
		AValue three = new Literal(3);
		AValue four = new Literal(4);
		AValue five = new Literal(5);

		AValue m34 = new Multiply (three, four);
		AValue a234 = new Add(two, m34);
		AValue d345 = new Divide(m34, five);
		AValue res = new Add(a234, d345);
		
		Common.log("Result: a234= " + a234.eval());		
		Common.log("Result: d345= " + d345.eval());		
		Common.log("Result =" + res.eval());	

		Common.log("** The common expression removed");
		
		AValue m34p = new Multiply (three, four);
		AValue d345p = new Divide(m34p, five);
		AValue resp = new Add(a234, d345p);
		Common.log("Result2 =" + resp.eval());	

	}

}
