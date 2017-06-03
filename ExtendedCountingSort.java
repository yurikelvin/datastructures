import java.util.Arrays;
import java.util.Random;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort {

   public void sort(Integer[] array, int leftIndex, int rightIndex) {
      if (array == null || leftIndex < 0 || leftIndex >= rightIndex || rightIndex >= array.length || array.length <= 1) {
         return;
      }

      Integer[] arrayOrdenado = counting(array, leftIndex, rightIndex);

      copyArray(arrayOrdenado, array, leftIndex, rightIndex);

   }

   private void copyArray(Integer[] arrayOrdenado, Integer[] array, int leftIndex, int rightIndex) {

      // Adequando parte do array entre o range do leftIndex ao rightIndex do array original.
      int j = 0;
      for (int i = leftIndex; i <= rightIndex; i++) {
         array[i] = arrayOrdenado[j++];
      }

   }

   private Integer[] counting(Integer[] array, int leftIndex, int rightIndex) {

      // Vetor auxiliar de destino dos elementos ordenados.
      int tamanhoArrayOrdenado = rightIndex - leftIndex + 1;
      Integer[] elementosOrdenados = new Integer[tamanhoArrayOrdenado];

      int[] indexOfMaxAndMin = findMaxAndMin(array, leftIndex, rightIndex);

      int maxElement = array[indexOfMaxAndMin[1]].intValue();
      int minElement = array[indexOfMaxAndMin[0]].intValue();

      // Importante: Salto de cada elemento no vetor auxiliar de contagem.
      int salto = minElement;

      // Array auxiliar do counting com otimizacao.
      int[] auxContagem = new int[maxElement - minElement + 1];

      // Frequencia dos termos.

      for (int i = leftIndex; i <= rightIndex; i++)
         auxContagem[array[i].intValue() - salto] += 1;

      // Acumulativa dos termos.
      for (int i = 1; i < auxContagem.length; i++)
         auxContagem[i] += auxContagem[i - 1];

      // Processo de encaixe dos elementos ordenados no vetor auxiliar.

      for (int i = rightIndex; i >= leftIndex; i--) {
         elementosOrdenados[auxContagem[array[i] - salto] - 1] = array[i];
         auxContagem[array[i] - salto] -= 1;
      }

      return elementosOrdenados;

   }

   private int[] findMaxAndMin(Integer[] array, int leftIndex, int rightIndex) {

      int indexMaxElement = rightIndex;
      int indexMinElement = leftIndex;

      for (int i = leftIndex; i <= rightIndex; i++) {
         if (array[indexMaxElement].compareTo(array[i]) < 0) {
            indexMaxElement = i;
         }

         if (array[indexMinElement].compareTo(array[i]) > 0) {
            indexMinElement = i;
         }
      }

      return new int[] { indexMinElement, indexMaxElement };
   }
public static void main(String[] args) {
        long start = System.nanoTime();
        ExtendedCountingSort inst = new ExtendedCountingSort();
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
