package ds.list;

public class LinkListGR<T extends Comparable>{

	public T value;
	public LinkListGR next;
	public LinkListGR(T value){
		this.value = value;
	}
}