package vector;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

   @Override
   public void sort(Integer[] array, int leftIndex, int rightIndex) {
    
	   if (array == null || leftIndex < 0 || leftIndex >= rightIndex || rightIndex >= array.length || array.length <= 1) {
         return;
	   }

      Integer[] arrayOrdenado = counting(array, leftIndex, rightIndex);

      copyArray(arrayOrdenado, array, leftIndex, rightIndex);

   }

   private void copyArray(Integer[] arrayOrdenado, Integer[] array, int leftIndex, int rightIndex) {

      // Adequando parte do array entre o range do leftIndex ao rightIndex ao array original.
      int j = 0;
      for (int i = leftIndex; i <= rightIndex; i++) {
         array[i] = arrayOrdenado[j++];
      }

   }

   private Integer[] counting(Integer[] array, int leftIndex, int rightIndex) {

	  // Vetor auxiliar de destino dos elementos ordenados.
	  // Left Index a Right Index.
	   int tamanhoArrayOrdenado = rightIndex - leftIndex + 1;
	  Integer[] elementosOrdenados 	= new Integer[tamanhoArrayOrdenado];

	  int 		indexMaxElement 	= findMax(array, leftIndex, rightIndex);
      Integer 	maxElement 			= array[indexMaxElement];
      
      int[] 	auxContagem 		= new int[maxElement + 1];

      // Frequencia dos termos

      for (int i = leftIndex; i <= rightIndex; i++)
         auxContagem[array[i].intValue()] += 1;

      // Acumulativa dos termos
      for (int i = 1; i < auxContagem.length; i++)
         auxContagem[i] += auxContagem[i - 1];

      // Processo de encaixe dos elementos no vetor dos elementos ordenados.

      for (int i = rightIndex; i >= leftIndex; i--) {
         elementosOrdenados[auxContagem[array[i]] - 1]	 = array[i];
         auxContagem[array[i]]							-= 1;
      }

      return elementosOrdenados;

   }

   private int findMax(Integer[] array, int leftIndex, int rightIndex) {
    
	   int indexMaxElement = leftIndex;
	
	   for (int i = leftIndex + 1; i <= rightIndex; i++) {
		   if (array[indexMaxElement].compareTo(array[i]) < 0) {
			   indexMaxElement = i;
		   }
	   }
	   return indexMaxElement;
	}


}
