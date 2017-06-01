package vector;

import sorting.AbstractSorting;
import util.Util;

/**
 * Quicksort is based on the divide-and-conquer paradigm. The algorithm chooses
 * a pivot element and rearranges the elements of the interval in such a way
 * that all elements lesser than the pivot go to the left part of the array and
 * all elements greater than the pivot, go to the right part of the array. Then
 * it recursively sorts the left and the right parts. Notice that if the list
 * has length == 1, it is already sorted.
 */
public class QuickSort<T extends Comparable<T>> extends AbstractSorting<T> {

   @Override
   public void sort(T[] array, int leftIndex, int rightIndex) {

      if (array == null) {
         return;
      }

      // Caso base da recursao, enquanto inicio do array for menor que o fim.
      if (leftIndex < rightIndex) {

         // indice do pivot.
         int indexPivot = partition(array, leftIndex, rightIndex);
         // passando array do comeco ao index anterior ao pivot.
         sort(array, leftIndex, indexPivot - 1);
         // passando array do index mais proximo superior ao pivot e fim do array.
         sort(array, indexPivot + 1, rightIndex);
      }
   }

   /**
    * Determina uma particao.
    * Os elementos a esquerda sao menores que o pivot.
    * Os elementos a direita sao maiores ou iguais ao pivot.
    * Pivot escolhido como sendo o primeiro elemento do array inicialmente.
    * 
    * @param array 
    * 				Array a ser particionado.
    * @param leftIndex
    * 				Comeco da particao a ser efetuada.
    * @param rightIndex
    * 				Fim da particao desejada.
    * @return
    * 				O indice da posicao do pivot.
    */

   private int partition(T[] array, int leftIndex, int rightIndex) {

      T pivot = array[leftIndex]; // pivot sendo o primeiro elemento do array
      int indexPivot = leftIndex;
      for (int j = leftIndex + 1; j <= rightIndex; j++) {
         if (pivot.compareTo(array[j]) > 0) {
            // Realiza troca estavel dos elementos menores que o pivot.
            swapStable(array, indexPivot, j);
            indexPivot++;
         }
      }

      return indexPivot;
   }

   /**
    * Realiza uma troca estavel dos elementos de um array.
    * Do limite superior ao limite inferior em ordem decrescente.
    * 
    * @param array
    * 				Array a fazer a troca estavel.
    * @param begin
    * 				Index de destino da troca. (Limite Superior)
    * @param end
    * 				Index do destino final da troca. (Limite Inferior)
    */

   private void swapStable(T[] array, int end, int begin) {
      for (int i = begin; i > end; i--) {
         Util.swap(array, i, i - 1);
      }
   }

   
}
