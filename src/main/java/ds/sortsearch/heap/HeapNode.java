package ds.sortsearch.heap;

public class HeapNode implements Comparable<HeapNode> {
	private int value;
	private int pos;

	public HeapNode(int value, int pos){
		this.value = value;
		this.pos = pos;
	}

	public int value() { return this.value; }
	public int pos() { return this.pos; }

	@Override
	public boolean equals(Object o){
		if(o instanceof HeapNode){
			HeapNode a = (HeapNode)o;
			return (value == a.value) && (pos == a.pos);
		}
		return false;
	}

	@Override
	public int compareTo(HeapNode obj){
		if(obj == null) return 1;
		return this.value - obj.value;
	}

	@Override
	public int hashCode () {
		return new StringBuilder(value).append(pos).toString().hashCode();
	}
}