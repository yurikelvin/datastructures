import java.util.Arrays;
import java.util.Random;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class MyExtendedCountingSort {

   public void sort(Integer[] array, int leftIndex, int rightIndex) {
      if (array != null && leftIndex < rightIndex && leftIndex >= 0 && rightIndex - leftIndex >= 1
            && rightIndex < array.length) {

         int dif = findMin(array, leftIndex, rightIndex);
         Integer[] aux = new Integer[findMax(array, leftIndex, rightIndex) + 1 - (dif)];

         for (int i = 0; i < aux.length; i++) {
            aux[i] = 0;
         }

         for (int i = leftIndex; i <= rightIndex; i++) {
            aux[array[i] - (dif)]++;
         }

         for (int i = 1; i < aux.length; i++) {
            aux[i] = aux[i] + aux[i - 1];
         }

         int i = aux.length - 1, j = rightIndex;

         while (i > 0) {
            if (aux[i] > aux[i - 1]) {
               array[j--] = i + (dif);
               aux[i]--;
            } else {
               i--;
            }
         }

         while (aux[0] > 0) {
            array[j--] = 0 + (dif);
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

   private int findMin(Integer[] array, int leftIndex, int rightIndex) {
      int min = array[leftIndex];
      for (int i = leftIndex; i <= rightIndex; i++) {
         if (array[i] < min) {
            min = array[i];
         }
      }
      return min;
   }

public static void main(String[] args) {
        long start = System.nanoTime();
        MyExtendedCountingSort inst = new MyExtendedCountingSort();
        int num = Integer.parseInt(args[0]);
        Integer[] arr = new Integer[num];
        Random rand = new Random();
        for (int i = 0; i < num; i++) {
              arr[i] = rand.nextInt()/1000000;
        }

        inst.sort(arr, 0, num-1);

        System.out.println((System.nanoTime()-start)/1000000000.0);
   }
}