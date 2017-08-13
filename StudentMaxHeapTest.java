import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

public class StudentMaxHeapTest {

	Heap<Integer> heap;

	@Before
	public void setUp() {
		// TODO Instancie seu comparator para fazer sua estrutura funcionar como
		// uma max heap aqui. Use instanciacao anonima da interface
		// Comparator!!!!
		Comparator<Integer> comparator = new ComparatorTest();
		heap = new HeapImpl<Integer>(comparator);
	}

	@Test
	public void testBuild() {
		heap.buildHeap(new Integer[] { 82, 6, 99, 12, 34, 64, 58, 1 });

		assertEquals(8, heap.size());
		assertFalse(heap.isEmpty());

		verifyHeap(new Integer[] { 99, 12, 82, 6, 34, 64, 58, 1 });
	}

	@Test
	public void testInsert() {
		heap.insert(8);
		heap.insert(12);
		heap.insert(-2);
		heap.insert(7);
		heap.insert(8);
		heap.insert(-5);
		heap.insert(14);
		heap.insert(3);
		heap.insert(-10);
		heap.insert(0);

		assertEquals(10, heap.size());
		assertFalse(heap.isEmpty());

		verifyHeap(new Integer[] { 14, 8, 12, 7, 8, -5, -2, 3, -10, 0 });
	}

	@Test	
	public void testRemove() {
		heap.insert(22);
		heap.insert(45);
		heap.insert(38);
		heap.insert(17);
		heap.insert(40);
		heap.insert(15);
		heap.insert(26);
		heap.insert(79);
		heap.insert(53);
		heap.insert(30);

		assertEquals(new Integer(79), heap.extractRootElement());
		assertEquals(new Integer(53), heap.extractRootElement());
		assertEquals(new Integer(45), heap.extractRootElement());
		assertEquals(new Integer(40), heap.extractRootElement());
		assertEquals(new Integer(38), heap.extractRootElement());

		assertEquals(5, heap.size());
		assertFalse(heap.isEmpty());

		verifyHeap(new Integer[] { 22, 17, 15, 26, 30 });
	}

	@Test
	public void testSort() {
		assertArrayEquals(new Integer[] { 5, 6, 12, 20, 34, 43, 49, 92 },
				heap.heapsort(new Integer[] { 34, 92, 5, 12, 49, 20, 43, 6 }));

		assertEquals(0, heap.size());
		assertTrue(heap.isEmpty());

		assertArrayEquals(new Integer[] {}, heap.toArray());
	}
	
	@Test
	public void testInsert1() {
		heap.insert(0);
		heap.insert(68);
		heap.insert(100);
		heap.insert(38);
		heap.insert(11);
		heap.insert(13);
		heap.insert(78);
		heap.insert(21);
		heap.insert(4);
		heap.insert(59);
		
		assertArrayEquals(new Integer[] {100, 59, 78, 21, 38, 13, 68, 0, 4, 11}, heap.toArray());
		
		heap.extractRootElement();
		
		assertArrayEquals(new Integer[] {78, 59, 68, 21, 38, 13, 11, 0, 4}, heap.toArray());
		
		heap.buildHeap(new Integer[] {16, 15, 4, 12, 6, 7, 23, 20, 55, 5, 11, 27, 9, 8, 14});
		
		assertArrayEquals(new Integer[] {55, 20, 27, 16, 11, 9, 23, 15, 12, 5, 6, 7, 4, 8, 14}, heap.toArray());
		
		heap.buildHeap(new Integer[] {1, 67, 39, 44, 10, 11, 12, 46, 47, 48, 14, 41, 73, 55, 53});
		
		assertArrayEquals(new Integer[] {73, 67, 55, 47, 48, 41, 53, 46, 44, 10, 14, 39, 11, 12, 1}, heap.toArray());
		
		
	}

	private void verifyHeap(Integer[] expected) {
		boolean isHeap = true;

		Comparable<Integer>[] original = heap.toArray();

		Arrays.sort(expected);
		Arrays.sort(original);

		if (Arrays.equals(expected, original) == false)
			isHeap = false;

		original = heap.toArray();

		for (int i = 0; i < original.length; i++) {
			if (2 * i + 1 < original.length && original[i].compareTo((Integer) original[2 * i + 1]) < 0)
				isHeap = false;
			if (2 * i + 2 < original.length && original[i].compareTo((Integer) original[2 * i + 2]) < 0)
				isHeap = false;
		}

		assertTrue(isHeap);
	}

}
