public class BasicSortSearch {
  public static void main(String[] args) {
    int[] a = {0, 4, 3, 5, 3, 2, 8, 9, 2, 8};
    // search(a);
    heap_sort(a);
    print(a);
  }

  public static void print(int[] a){
    for(int e: a) System.out.print(" " + e);
    System.out.println();
  }

  // incremental selection sort
  public static void search (int[] a){
    if(a == null || a.length < 2) return;
    int i, j, t;
    //a[0] = -Integer.MAX_VALUE;
    for(i = 0; i < a.length; i++){
      j = i;
      while(j > 0 && a[j] < a[j- 1]){
          // swap
          t = a[j- 1];
          a[j - 1] = a[j];
          a[j] = t;
          j = j - 1;
      }
    }
  }

  
}
