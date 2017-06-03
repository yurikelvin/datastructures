import java.util.Arrays;
import java.util.Random;


/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 * 
 */
public class MyCountingSort {

   public void sort(Integer[] array, int leftIndex, int rightIndex) {
      if (array != null && leftIndex < rightIndex && leftIndex >= 0 && rightIndex - leftIndex >= 1
            && rightIndex < array.length) {

         Integer[] aux = new Integer[findMax(array, leftIndex, rightIndex) + 1];

         for (int i = 0; i < aux.length; i++) {
            aux[i] = 0;
         }

         for (int i = leftIndex; i <= rightIndex; i++) {
            aux[array[i]]++;
         }

         for (int i = 1; i < aux.length; i++) {
            aux[i] = aux[i] + aux[i - 1];
         }

         int i = aux.length - 1, j = rightIndex;

         while (i > 0) {
            if (aux[i] > aux[i - 1]) {
               array[j--] = i;
               aux[i]--;
            } else {
               i--;
            }
         }

         while (aux[0] > 0) {
            array[j--] = 0;
            aux[0]--;
         }
      }
   }

   private int findMax(Integer[] array, int leftIndex, int rightIndex) {
      int maxi = array[leftIndex];
      for (int i = leftIndex; i <= rightIndex; i++) {
         if (array[i] > maxi) {
            maxi = array[i];
         }
      }
      return maxi;
   }
}