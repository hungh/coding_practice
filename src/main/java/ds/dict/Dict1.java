

public class Dict1 {
  // Reference : http://stackoverflow.com/questions/1050253/help-me-understand-this-programming-pearls-bitsort-program

  private static final int BITSPERWORD = 32;
	private static final int SHIFT = 5;
	private static final int MASK = 0x1F;
	private static final int N = 10000000;


  public static void main(String[] args) {
    Dict1 dic1 = new Dict1(N);

    dic1.insert(42);

    System.out.println("Search 43=" + dic1.search(43));
    System.out.println("Search 42=" + dic1.search(42));

    dic1.delete(42);
    System.out.println("Search 42=" + dic1.search(42));
  }

  private int[] a;

  public Dict1 (int init_size) {
    if(init_size < 1 ) throw new IllegalArgumentException ("size is not positive");
    this.a = new int [init_size];
  }


  public boolean search(int value){
    return test(value) > 0;
  }

  public void delete(int value){
      clear(value);
  }

  public void insert(int value){
    set(value);
  }

  public void set(int i) {
     a [i >> SHIFT] |= (1 << (i & MASK ));
   }

  public int test (int i) {
     return  a[i >> SHIFT] & ( 1 << (i & MASK));
  }

  public void clear(int i){
    a [ i >> SHIFT] &= ~(1 << (i & MASK));
  }

}
