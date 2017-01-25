/*
Solution to Problem 5-2 
*/

package graph.ex;

import java.util.*;
import ds.Common;
import graph.apps.TopoSorting;

public class TopoSorting5D2 extends TopoSorting { // TODO: not correct 
	private static Map<Integer, Character> vertexMap = new HashMap<Integer, Character>();

	static {
		int j = 0;
		for(char i = 'A'; i <= 'J'; i++){
			vertexMap.put(j++, i);
		}
	}

	public TopoSorting5D2(boolean directed){
		super(directed);
	}

	public static void main(String[] args){
		TopoSorting5D2 graph = new TopoSorting5D2(true);
		graph.test("/graph5D12.txt");
		graph.print_stack();
	}

	@Override
	public void print_stack() {
		if(sorted == null) return;
		while(!sorted.isEmpty()){
			Common._log(" " + vertexMap.get(sorted.pop()));
		}
		Common.log("");
	}


}