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
	public boolean equals(Object o){
		if(o instanceof EdgeNode) {
			EdgeNode obj = (EdgeNode)o;
			return (y == obj.y) && (weight == obj.weight);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return new StringBuilder().append(y).append(weight).hashCode();
	}

	@Override
	public int compareTo(EdgeNode o){
		return weight - o.weight;
	}
}