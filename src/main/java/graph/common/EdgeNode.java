package graph.common;

public class EdgeNode implements Comparable <EdgeNode>{
	public EdgeNode(){}
	public EdgeNode(int y) { this.y = y; }
	public int y;  // adjancency info
	public Integer weight;
	public EdgeNode next;


	@Override
	public String toString() {
		return new StringBuilder().append("y: ").append(y).append("; weight: ").append(weight).toString();
	}

	@Override
	public int compareTo(EdgeNode o){
		return weight - o.weight;
	}
}