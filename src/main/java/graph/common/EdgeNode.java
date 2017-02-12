package graph.common;

public class EdgeNode {
	public EdgeNode(){}
	public EdgeNode(int y) { this.y = y; }
	public int y;  // adjancency info
	public Integer weight;
	public EdgeNode next;
}