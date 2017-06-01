package vector;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

   @Override
   public void sort(T[] array, int leftIndex, int rightIndex) {

      if (array == null) {
         return;
      }

      if (leftIndex < rightIndex) {

         int med = (leftIndex + rightIndex) / 2; // meio do array
         sort(array, leftIndex, med);
         sort(array, med + 1, rightIndex);
         merge(array, leftIndex, med, rightIndex);
      }

   }

   private void merge(T[] array, int leftIndex, int med, int rightIndex) {

      // Helper para ajudar na troca de indices
      T[] helper = (T[]) new Comparable[array.length];

      for (int i = 0; i < array.length; i++) {
         helper[i] = array[i];

      }

      int i = leftIndex;
      int j = med + 1;
      int k = leftIndex;

      while (i <= med && j <= rightIndex) {
         if (helper[i].compareTo(helper[j]) < 0) {
            array[k] = helper[i];
            i++;
         } else {
            array[k] = helper[j];
            j++;
         }

         k++;
      }

      // Caso a primeira lista falte adicionar elementos.
      while (i <= med) {
         array[k] = helper[i];
         i++;
         k++;
      }

      // Caso a segunda lista falte adicionar elementos.		
      while (j <= rightIndex) {
         array[k] = helper[j];
         j++;
         k++;
      }
   }
   
   public static void main(String[] args) {
	MergeSort teste = new MergeSort();
	Integer[] test = new Integer[] {0,0,0,0,0,2};
	teste.sort(test, 0, 1);
}

}
