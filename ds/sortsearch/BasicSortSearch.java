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

  public static void heap_sort(int[] a){
      for(int i = 0; i < a.length; i++){

      }
  }


  // heap sort - is a selection sort with data represented in heap
  // build array heap (min heap), index 1
  // take nlogn
  public static void heapify (int[] a){
    int lchild, len = a.length/2;
    int j, i;
    for(i = a.length - 1; i > 0; i-- ){
      bubble_up(a, i);
    }
  }

  private static void bubble_down(int[] a, int i){

  }
  // take log(n)
  private static void bubble_up(int[] a, int i){
    if(i ==  1) return;
    int parent = i/2;
    if(a[i] < a[parent]){
      swap(a, i, parent);
    }
    bubble_up(a, parent);
  }

  public static void swap(int[] a, int i, int j){
    int t = a[i];
    a[i] = a[j];
    a[j] = t;
  }
}
