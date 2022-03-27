package utilities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class MyArrayListTests {
	//attributes
	ListADT<String> list;
	
	@BeforeEach
	void setUp() throws Exception{
		list = new MyArrayList<>();
	}

	@AfterEach
	void tearDown() throws Exception {
		list = null;
	}

	@Test
	void testSizeEmpty() {
		assertEquals(0, list.size());
	}

	@Test
	void testSizeNotEmpty() {
		list.add("A");
		list.add("B");
		list.add("C");
		assertEquals(3, list.size());
	}

	@Test
	void testClear() {
		list.clear();
		assertEquals(0, list.size());
	}

	@Test
	void testAddIntEEmpty() {
		boolean added = list.add(0, "A");
		assertTrue(added);
		assertEquals(1, list.size());
		assertEquals("A", list.get(0));
	}

	@Test
	void testAddIntENotEmptyAppend() {
		list.add("A");
		boolean added = list.add(1, "B");
		assertTrue(added);
		assertEquals(2, list.size());
		assertEquals("B", list.get(1));
		
	}

	@Test
	void testAddIntENotEmptyPrepend() {
		list.add("B");
		boolean added = list.add(0, "A");
		assertTrue(added);
		assertEquals(2, list.size());
		assertEquals("A", list.get(0));
	}

	@Test
	void testAddIntENotEmptyInsert() {
		list.add("A");
		list.add("C");
		boolean added = list.add(1, "B");
		assertTrue(added);
		assertEquals(3, list.size());
		assertEquals("B", list.get(1));
	}

	@Test
	void testAddIntENullPointerException() {
		try {
			list.add(0, null);
			fail("NullPointerException wasn't thrown");
		}
		catch(NullPointerException e) {
			assertTrue(true);
		}
	}

	@Test
	void testAddIntELowestIndexOutOfBoundsException() {
		try {
			list.add(-1, "A");
			fail("IndexOutOfBoundsException wasn't thrown!");
		}
		catch(IndexOutOfBoundsException e) {
			assertTrue(true);
		}
	}

	@Test
	void testAddIntEHighestIndexOutOfBoundsException() {
		try {
			list.add(1, "A");
			fail("IndexOutOfBoundsException wasn't thrown!");
		}
		catch(IndexOutOfBoundsException e) {
			assertTrue(true);
		}
	}

	/*
	 * test method for {!link utilities.MyArrayList#add(java.lang.Object)}.
	 */
	@Test
	void testAddENonEmpty() {
		list.add("A");
		assertTrue(list.add("B"));
		assertEquals("B", list.get(1));
		assertEquals(2, list.size());
	}
	
	@Test
	void testAddEEmpty() {
		assertEquals(0, list.size());
		assertTrue(list.add("A"));
		assertEquals(1, list.size());
	}

	@Test
	void testAddAll() {
		fail ("not yet implemented");
	}

	@Test
	void testGet() {
		fail ("not yet implemented");
	}

	@Test
	void testRemoveInt() {
		fail ("not yet implemented");
	}

	@Test
	void testRemoveE() {
		fail ("not yet implemented");
	}

	@Test
	void testSet() {
		fail ("not yet implemented");
	}

	@Test
	void testIsEmpty() {
		fail ("not yet implemented");
	}

	@Test
	void testContains() {
		fail ("not yet implemented");
	}

	/*
	 * test method for {@link utilities.MyArrayList#toArray(E[])}
	 */
	@Test
	void testToArrayEArrayEmpty() {
		String[] arr = new String[3];
		arr = list.toArray(arr);
		assertEquals(3, arr.length);
		
		for(int i = 0; i < arr.length; i++) {
			assertNull(arr[i]);
		}
	}

	/*
	 * test method for {@link utilities.MyArrayList#toArray(E[])}
	 */
	@Test
	void testToArrayEArrayNonEmptySufficient() {
		list.add("A");
		list.add("B");
		list.add("C");
		String[] arr = new String[3];
		arr = list.toArray(arr);
		assertEquals(3, arr.length);
		
		for(int i = 0; i < arr.length; i++) {
			assertEquals(list.get(i), arr[i]);
		}
	}

	/*
	 * test method for {@link utilities.MyArrayList#toArray(E[])}
	 */
	@Test
	void testToArrayEArrayNonEmptyInsufficient() {
		list.add("A");
		list.add("B");
		list.add("C");
		String[] arr = new String[1];
		arr = list.toArray(arr);
		assertEquals(3, arr.length);
		
		for(int i = 0; i < arr.length; i++) {
			assertEquals(list.get(i), arr[i]);
		}
	}

	/*
	 * test method for {@link utilities.MyArrayList#toArray(E[])}
	 */
	@Test
	void testToArrayEArrayNullPointerException() {
		assertThrows(NullPointerException.class, () -> {
			list.toArray(null);
		});
	}

	@Test
	void testtoArrayEmpty() {
		Object[] arr = list.toArray();
		assertEquals(0, arr.length);
	}

	@Test
	void testtoArrayNonEmpty() {
		list.add("A");
		list.add("B");
		list.add("C");
		Object[] arr = list.toArray();
		assertEquals(3, arr.length);
		
		for(int i = 0; i < arr.length; i++) {
			assertEquals(list.get(i), arr[i]);
		}
	}

	/**
	 * test method for {@link utilities.MyArrayList#iterator()}.
	 */
	@Test
	void testIteratorEmpty() {
		Iterator<String> it = list.iterator();
		assertFalse(it.hasNext());
		assertThrows(NoSuchElementException.class, () -> {
			it.next();
		});
	}

	@Test
	void testIteratorNonEmpty() {
		list.add("A");
		list.add("B");
		list.add("C");
		Iterator<String> it = list.iterator();
		assertTrue(it.hasNext());
		int i = 0;
		while (it.hasNext()) {
			assertEquals(list.get(i++), it.next());
		}
		assertFalse(it.hasNext());
		assertThrows(NoSuchElementException.class, () -> {
			it.next();
		});
	}

}
