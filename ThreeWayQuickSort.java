package sorting.divideAndConquer.threeWayQuicksort;

import sorting.AbstractSorting;
import util.Util;

public class ThreeWayQuickSort<T extends Comparable<T>> extends AbstractSorting<T> {

   /**
    * No algoritmo de quicksort, selecionamos um elemento como pivot,
    * particionamos o array colocando os menores a esquerda do pivot 
    * e os maiores a direita do pivot, e depois aplicamos a mesma estrategia 
    * recursivamente na particao a esquerda do pivot e na particao a direita do pivot. 
    * 
    * Considerando um array com muitoe elementos repetidos, a estrategia do quicksort 
    * pode ser otimizada para lidar de forma mais eficiente com isso. Essa melhoria 
    * eh conhecida como quicksort tree way e consiste da seguinte ideia:
    * - selecione o pivot e particione o array de forma que
    *   * arr[l..i] contem elementos menores que o pivot
    *   * arr[i+1..j-1] contem elementos iguais ao pivot.
    *   * arr[j..r] contem elementos maiores do que o pivot. 
    *   
    * Obviamente, ao final do particionamento, existe necessidade apenas de ordenar
    * as particoes contendo elementos menores e maiores do que o pivot. Isso eh feito
    * recursivamente. 
    **/
   @Override
   public void sort(T[] array, int leftIndex, int rightIndex) {
      if (array == null) {
         return;
      }

      if (leftIndex < rightIndex) {

         // ----------------------------
         // Partition 3 way do quickSort
         // ----------------------------
         // Pega os menores que o pivot e coloca a esquerda.
         // Coloca os elementos iguais ao pivot no meio.
         // Coloca os elementos maiores que o pivot a direita.
         // Importante: Particao Estavel.
         // ----------------------------

         T pivot = array[leftIndex];

         int indexPivot = leftIndex;
         int i = leftIndex + 1;
         int j = rightIndex;

         int pivotRepetitions = 0;

         while (i <= j) { // Enquanto o inicio nao for menor igual ao indice do ultimo elemento.

            if (pivot.compareTo(array[i]) > 0) { // Se pivot for maior que o elemento, troca.
               swapBackward(array, indexPivot, i);
               i++;
               indexPivot++;

            } else if (pivot.compareTo(array[i]) == 0) { // Se o pivot for igual ao elemento.
               i++;
               pivotRepetitions++;

            } else { // Caso o elemento seja maior que o pivot, manda ele para o final do array.
               swapFoward(array, i, rightIndex);
               j--;
            }

         }

         // Passo recursivo e ordenacao.
         sort(array, leftIndex, indexPivot - 1); // Pega a primeira particao.
         sort(array, indexPivot + 1 + pivotRepetitions, rightIndex); // Pega a terceira particao.
      }
   }

   // Algoritmos auxiliares a ordenacao.

   /**
    * Dado um array, move um elemento do indice begin para o indice end, de modo estavel.
    * Move os elementos em ordem decrescente, do indice de inicio ao indice final.
    * @param array
    * 				Array a ser utilizado o swap.
    * @param end
    * 				Indice final do elemento.
    * @param begin
    * 				Indice inicial em que o elemento se encontra.
    * 				
    */

   private void swapBackward(T[] array, int end, int begin) {
      for (int i = begin; i > end; i--) {
         Util.swap(array, i, i - 1);
      }
   }

   /**
    * Dado um array, move um elemento do indice begin para o indice end, de modo estavel.
    * Move os elementos em ordem crescente, do indice de inicio ao indice final.
    * @param array
    * 				Array a ser utilizado o swap.
    * @param begin
    * 				Indice inicial em que o elemento se encontra.
    * @param end
    * 				Indice final de destino do elemento.
    */

   private void swapFoward(T[] array, int begin, int end) {
      for (int i = begin; i < end; i++) {
         Util.swap(array, i, i + 1);
      }
   }
  

}
