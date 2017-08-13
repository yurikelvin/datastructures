import java.util.Arrays;
import java.util.Comparator;

import util.Util;

/**
 * O comportamento de qualquer heap é definido pelo heapify. Neste caso o
 * heapify dessa heap deve comparar os elementos e colocar o maior sempre no
 * topo. Ou seja, admitindo um comparador normal (responde corretamente 3 > 2),
 * essa heap deixa os elementos maiores no topo. Essa comparação não é feita 
 * diretamente com os elementos armazenados, mas sim usando um comparator. 
 * Dessa forma, dependendo do comparator, a heap pode funcionar como uma max-heap 
 * ou min-heap.
 */
public class HeapImpl<T extends Comparable<T>> implements Heap<T> {

   protected T[] heap;
   protected int index = -1;
   /**
    * O comparador é utilizado para fazer as comparações da heap. O ideal é
    * mudar apenas o comparator e mandar reordenar a heap usando esse
    * comparator. Assim os metodos da heap não precisam saber se vai funcionar
    * como max-heap ou min-heap.
    */
   protected Comparator<T> comparator;

   private static final int INITIAL_SIZE = 20;
   private static final int INCREASING_FACTOR = 10;

   /**
    * Construtor da classe. Note que de inicio a heap funciona como uma
    * min-heap.
    */
   @SuppressWarnings("unchecked")
   public HeapImpl(Comparator<T> comparator) {
      this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
      this.comparator = comparator;
   }

   // /////////////////// METODOS IMPLEMENTADOS
   private int parent(int i) {
      return (i - 1) / 2;
   }

   /**
    * Deve retornar o indice que representa o filho a esquerda do elemento
    * indexado pela posicao i no vetor
    */
   private int left(int i) {
      return (i * 2 + 1);
   }

   /**
    * Deve retornar o indice que representa o filho a direita do elemento
    * indexado pela posicao i no vetor
    */
   private int right(int i) {
      return 2 * i + 2;
   }

   @Override
   public boolean isEmpty() {
      return (index == -1);
   }

   @Override
   public T[] toArray() {
      @SuppressWarnings("unchecked")
      T[] resp = Util.makeArrayOfComparable(index + 1);
      for (int i = 0; i <= index; i++) {
         resp[i] = this.heap[i];
      }
      return resp;
   }

   // ///////////// METODOS A IMPLEMENTAR
   /**
    * Valida o invariante de uma heap a partir de determinada posicao, que pode
    * ser a raiz da heap ou de uma sub-heap. O heapify deve colocar os maiores
    * (comparados usando o comparator) elementos na parte de cima da heap.
    */
   private void heapify(int position) {
      int i = this.indexMax(position, left(position), right(position));
      if (i != position) {
         Util.swap(this.heap, position, i);
         this.heapify(i);
      }

   }

   private int indexMax(int elementPosition, int elementLeft, int elementRight) {
      int position = elementPosition;

      if (elementLeft < this.size()) {
         if (this.getComparator().compare(this.heap[position], this.heap[elementLeft]) < 0)
            position = elementLeft;
      }

      if (elementRight < this.size()) {
         if (this.getComparator().compare(this.heap[position], this.heap[elementRight]) < 0)
            position = elementRight;

      }

      return position;
   }

   @Override
   public void insert(T element) {
      // ESSE CODIGO E PARA A HEAP CRESCER SE FOR PRECISO. NAO MODIFIQUE
      if (index == heap.length - 1) {
         heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
      }
      // /////////////////////////////////////////////////////////////////
      // TODO Implemente a insercao na heap aqui.
      if (element != null) {

         this.heap[++this.index] = element;

         int i = this.index;
         while (i > 0 && this.getComparator().compare(this.heap[parent(i)], this.heap[i]) < 0) {
        	Util.swap(heap, i, parent(i));
            i = parent(i);
         }
      }
   }

   @Override
   public void buildHeap(T[] array) {

      if (array != null) {

         // Clear the current heap.

         this.clearHeap();

         copyArray(heap, array);

         for (int i = parent(this.index); i >= 0; i--) {
            this.heapify(i);
         }
      }

   }

   @SuppressWarnings("unchecked")
   private void clearHeap() {
      this.heap = (T[]) (new Comparable[0]);
      this.index = -1;

   }

   @SuppressWarnings("unchecked")
   private void copyArray(T[] heap, T[] array) {
      this.heap = (T[]) (new Comparable[array.length]);
      for (int i = 0; i < array.length; i++) {
         if (array[i] != null) {
            this.heap[++this.index] = array[i];
         }
      }
   }

   @Override
   public T extractRootElement() {
      T root = null;
      if (!isEmpty()) {

         root = this.heap[0];
         Util.swap(heap, 0, this.size() - 1);
         this.index--;
         this.heapify(0);

      }

      return root;
   }

   @Override
   public T rootElement() {
      T resp = null;

      if (!isEmpty())
         resp = this.heap[0];

      return resp;
   }

   @Override
   public T[] heapsort(T[] array) {
      T[] heapsort = (T[]) (new Comparable[0]);

      if (array != null) {
    	  
    	 Comparator<T> atualComparator = this.getComparator();
    	 this.comparator = (a, b) -> b.compareTo(a);

         this.buildHeap(array);

         heapsort = (T[]) (new Comparable[this.size()]);

         
         for (int i = 0; i < heapsort.length; i++) {
            heapsort[i] = this.extractRootElement();
         }

         this.comparator = atualComparator;
         
         this.clearHeap();

      }

      return heapsort;
   }

   @Override
   public int size() {
      return this.index + 1;
   }

   public Comparator<T> getComparator() {
      return comparator;
   }

   public void setComparator(Comparator<T> comparator) {
      this.comparator = comparator;
   }

   public T[] getHeap() {
      return heap;
   }

}
