package utilities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyDLLTests {

	//attributes
	MyDLL<String> list;
	MyDLL<String> newlist;
	String[] toHold;
	Object [] container;
	Iterator <String> iterator;
	
	@BeforeEach
	void setUp() throws Exception {
		list = new MyDLL<>();
		newlist = new MyDLL<>();

	}
	
	/*
	 * if list is not empty
	 */
	@Test
	void testSizeNonEmpty() {
		list.add("A");
		list.add("B");
		assertEquals(2, list.size());
	}
	
	/*
	 * test for {@link ulitities.MyDLL#size}
	 * if list is empty
	 */
	@Test
	void testSizeEmpty() {
		assertEquals(0, list.size());
	}
	
	/*
	 * test for {@link ulitities.MyDLL#clear}
	 * if list is not empty
	 */
	@Test
	void testClearNonEmpty() {
		list.add("A");
		list.clear();
		assertEquals(0, list.size());
	}
	
	/*
	 * test for {@link ulitities.MyDLL#clear}
	 * if list is not empty
	 */
	@Test
	void testClearEmpty() {
		list.clear();
		assertEquals(0, list.size());
	}
	
	/**
	 * Test method for {@link utility.MyDLL#add(int, java.lang.Object)}.
	 * if list is empty
	 */
	@Test
	void testAddIntEEmpty() {
		boolean added = list.add(0, "A");
		assertTrue(added);
		assertEquals(1, list.size());
		assertEquals("A", list.get(0));
	}
	
	/**
	 * Test method for {@link utility.DLL#add(int, java.lang.Object)}.
	 * if list is not empty 
	 * added at the beginning
	 */
	@Test
	void testAddIntE_NotEmpty_Beginning() {
		list.add("A");
		assertTrue(list.add(0, "B"));
		assertEquals(2, list.size());
		assertEquals("B", list.head.element);
		assertEquals("A", list.tail.element);
		assertEquals("B", list.get(0));
		assertEquals("A", list.get(1));
	}
	
	/**
	 * Test method for {@link utility.DLL#add(int, java.lang.Object)}.
	 * when list is not empty, adding in the middle
	 */
	@Test
	void testAddIntE_NotEmpty_Middle() {
		list.add("A");
		list.add("C");
		assertTrue(list.add(1, "B"));
		assertEquals(3, list.size());
		assertEquals("A", list.head.element);
		assertEquals("C", list.tail.element);
		assertEquals("A", list.get(0));
		assertEquals("B", list.get(1));
		assertEquals("C", list.get(2));
	}
	
	/**
	 * Test method for {@link utility.DLL#add(int, java.lang.Object)}.
	 * when list is not empty, adding at the end
	 */
	@Test
	void testAddIntE_NotEmpty_End() {
		list.add("A");
		list.add("B");
		assertTrue(list.add(2, "C"));
		assertEquals(3, list.size());
		assertEquals("A", list.get(0));
		assertEquals("B", list.get(1));
		assertEquals("C", list.get(2));
		assertEquals("A", list.head.element);
		assertEquals("C", list.tail.element);
	}
	
	/**
	 * Test method for {@link utility.DLL#add(int, java.lang.Object)}.
	 * when list is not empty, IndexOutOfBounds
	 */
	@Test
	void testAddIntE_NotEmpty_IndexOutOfBounds() {
		list.add("A");
		
		try {
			list.add(-1, "A");
			fail("IndexOutOfBoundsException wasn't thrown.");
		} catch(IndexOutOfBoundsException e) {
			assertTrue(true);
		}
		
		try {
			list.add(2, "A");
			fail("IndexOutOfBoundsException wasn't thrown.");
		} catch(IndexOutOfBoundsException e) {
			assertTrue(true);
		}
		
	}
	
	/**
	 * Test method for {@link utility.DLL#add(int, java.lang.Object)}.
	 * when list is empty, IndexOutOfBounds
	 */
	@Test
	void testAddIntE_Empty_IndexOutOfBounds() {
		try {
			list.add(-1, "A");
			fail("IndexOutOfBoundsException wasn't thrown.");
		} catch(IndexOutOfBoundsException e) {
			assertTrue(true);
		}
		
		try {
			list.add(1, "A");
			fail("IndexOutOfBoundsException wasn't thrown.");
		} catch(IndexOutOfBoundsException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link utility.DLL#add(java.lang.Object)}.
	 * when list is empty
	 */
	@Test
	void testAddE_Empty() {
		assertTrue(list.add("A"));
		assertEquals(1, list.size());
		assertEquals("A", list.get(0));
		assertEquals("A", list.head.element);
		assertEquals("A", list.tail.element);
	}
	
	/**
	 * Test method for {@link utility.DLL#add(java.lang.Object)}.
	 * when list is empty, NullPointerException
	 */
	@Test
	void testAddE_Empty_NullPointerException() {
		try {
			list.add(null);
			fail("NullPointerException wasn't thrown.");
		} catch(NullPointerException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link utility.DLL#add(java.lang.Object)}.
	 * when list is not empty
	 */
	@Test
	void testAddE_NotEmpty() {
		list.add("A");
		assertTrue(list.add("B"));
		assertEquals(2, list.size());
		assertEquals("B", list.get(1));
		assertEquals("A", list.head.element);
		assertEquals("B", list.tail.element);
	}

	/**
	 * Test method for {@link utility.DLL#add(java.lang.Object)}.
	 * when list is not empty, NullPointerException
	 */
	@Test
	void testAddE_NotEmpty_NullPointerException() {
		list.add("A");
		try {
			list.add(null);
			fail("NullPointerException wasn't thrown.");
		} catch(NullPointerException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link utility.DLL#addAll(utility.List)}.
	 * when list is empty, and toAdd is not empty
	 */
	@Test
	void testAddAll_Empty_NotEmpty() {
		newlist.add("A");
		assertTrue(list.addAll(newlist));
		assertEquals(1, list.size());
		assertEquals("A", list.get(0));
		assertEquals("A", list.head.element);
		assertEquals("A", list.tail.element);
	}

	/**
	 * Test method for {@link utility.DLL#addAll(utility.List)}.
	 * when list is empty, and toAdd is empty
	 */
	@Test
	void testAddAll_Empty_Empty() {
		try {
			assertTrue(list.addAll(newlist));
			fail("NullPointerException wasn't thrown.");
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link utility.DLL#addAll(utility.List)}.
	 * when list is not empty, and toAdd is not empty
	 */
	@Test
	void testAddAll_NotEmpty_NotEmpty() {
		list.add("A");
		newlist.add("B");
		assertTrue(list.addAll(newlist));
		assertEquals(2, list.size());
		assertEquals("A", list.get(0));
		assertEquals("B", list.get(1));
		assertEquals("A", list.head.element);
		assertEquals("B", list.tail.element);
	}
	
	/**
	 * Test method for {@link utility.DLL#addAll(utility.List)}.
	 * when list is not empty, and toAdd is empty
	 */
	@Test
	void testAddAll_NotEmpty_Empty() {
		try {
			list.add("A");
			assertTrue(list.addAll(newlist));
			fail("NullPointerException wasn't thrown.");
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link utility.DLL#get(int)}.
	 * when list is empty
	 */
	@Test
	@SuppressWarnings("unused")
	void testGet_Empty() {
		try {
			String element = list.get(-1);
			fail("IndexOutOfBoundsException wasn't thrown.");
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		}
		
		try {
			String element = list.get(0);
			fail("IndexOutOfBoundsException wasn't thrown.");
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		}
		
		try {
			String element = list.get(1);
			fail("IndexOutOfBoundsException wasn't thrown.");
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link utility.DLL#get(int)}.
	 * when list is not empty, and within bounds
	 */
	@Test
	void testGet_NotEmpty_WithinBounds() {
		list.add("A");
		list.add("B");
		assertEquals("A", list.get(0));
		assertEquals("B", list.get(1));
	}
	
	/**
	 * Test method for {@link utility.DLL#get(int)}.
	 * when list is not empty, and out of bounds
	 */
	@Test
	@SuppressWarnings("unused")
	void testGet_NotEmpty_OutOfBounds() {
		list.add("A");
		list.add("B");
		try {
			String element = list.get(3);
			fail("IndexOutOfBoundsException wasn't thrown.");
		} catch(IndexOutOfBoundsException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link utility.DLL#remove(int)}.
	 * when list is not empty, and within bounds, beginning
	 */
	@Test
	void testRemoveInt_NotEmpty_WithinBounds_Beginning() {
		list.add("A");
		list.add("B");
		list.add("C");
		
		String element = list.remove(0);
		assertEquals("A", element);
		assertEquals(2, list.size());
		assertEquals("C", list.get(1));
		
		assertEquals("B", list.head.element);
		assertEquals("C", list.tail.element);
	}
	
	/**
	 * Test method for {@link utility.DLL#remove(int)}.
	 * when list is not empty, and within bounds, middle
	 */
	@Test
	void testRemoveInt_NotEmpty_WithinBounds_Middle() {
		list.add("A");
		list.add("B");
		list.add("C");
		
		String element = list.remove(1);
		assertEquals("B", element);
		assertEquals(2, list.size());
		assertEquals("C", list.get(1));
		
		assertEquals("A", list.head.element);
		assertEquals("C", list.tail.element);
	}
	
	/**
	 * Test method for {@link utility.DLL#remove(int)}.
	 * when list is not empty, and within bounds, last
	 */
	@Test
	void testRemoveInt_NotEmpty_WithinBounds_Last() {
		list.add("A");
		list.add("B");
		list.add("C");
		
		String element = list.remove(list.size()-1);
		assertEquals("C", element);
		assertEquals(2, list.size());
		assertEquals("B", list.get(1));
		
		assertEquals("A", list.head.element);
		assertEquals("B", list.tail.element);
	}
	
	/**
	 * Test method for {@link utility.DLL#remove(int)}.
	 * when list is not empty, and out of bounds
	 */
	@Test
	@SuppressWarnings("unused")
	void testRemoveInt_NotEmpty_OutOfBounds() {
		list.add("A");
		list.add("B");
		list.add("C");
		try {
			String element = list.remove(-1);
			fail("IndexOutOfBoundsException wasn't thrown.");
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		}
		
		try {
			String element = list.remove(5);
			fail("IndexOutOfBoundsException wasn't thrown.");
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		}
	}

	
	
	/**
	 * Test method for {@link utility.DLL#remove(int)}.
	 * when list is empty, handling exceptions
	 */
	@Test
	@SuppressWarnings("unused")
	void testRemoveIntEmpty() {
		try {
			String element = list.remove(-1);
			fail("IndexOutOfBoundsException wasn't thrown.");
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		}
		
		try {
			String element = list.remove(0);
			fail("IndexOutOfBoundsException wasn't thrown.");
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		}
		
		try {
			String element = list.remove(1);
			fail("IndexOutOfBoundsException wasn't thrown.");
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link utility.DLL#remove(java.lang.Object)}.
	 * when list is not empty, and toRemove is not null, beginning
	 */
	@Test
	void testRemoveE_NotEmpty_NotNull_Beginning() {
		list.add("A");
		list.add("B");
		list.add("C");
		
		assertEquals(list.remove("A"), "A");
		assertEquals(2, list.size());
		
		assertEquals("B", list.head.element);
		assertEquals("C", list.tail.element);
	}
	
	/**
	 * Test method for {@link utility.DLL#remove(java.lang.Object)}.
	 * when list is not empty, and toRemove is not null, middle
	 */
	@Test
	void testRemoveE_NotEmpty_NotNull_Middle() {
		list.add("A");
		list.add("B");
		list.add("C");
		
		assertEquals(list.remove("B"), "B");
		assertEquals(2, list.size());
		
		assertEquals("A", list.get(0));
		assertEquals("C", list.get(1));
	}
	
	/**
	 * Test method for {@link utility.DLL#remove(java.lang.Object)}.
	 * when list is not empty, and toRemove is not null, last
	 */
	@Test
	void testRemoveE_NotEmpty_NotNull_Last() {
		list.add("A");
		list.add("B");
		list.add("C");
		
		assertEquals(list.remove("C"), "C");
		assertEquals(2, list.size());
		
		assertEquals("A", list.head.element);
		assertEquals("B", list.tail.element);
	}
	
	/**
	 * Test method for {@link utility.DLL#remove(java.lang.Object)}.
	 * when list is not empty, and toRemove is null
	 */
	@Test
	@SuppressWarnings("unused")
	void testRemoveE_NotEmpty_Null() {
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("B");
		try {
			String toRemove = list.remove(null);
			fail("NullPointerException wasn't thrown.");
		} catch(NullPointerException e) {
			assertTrue(true);
		}
	}

	/**
	 * Test method for {@link utility.DLL#remove(java.lang.Object)}.
	 * when list is empty
	 */
	@Test
	@SuppressWarnings("unused")
	void testRemoveE_Empty_Null() {
		try {
			String toRemove = list.remove("B");
			fail("NullPointerException wasn't thrown.");
		} catch(NullPointerException e) {
			assertTrue(true);
		}
	}
	
	
	/**
	 * Test method for {@link utility.DLL#set(int, java.lang.Object)}.
	 * when list is not empty, and element to set is not null, and index is within bounds
	 */
	@Test
	void testSet_NotEmpty_NotNull_WithinBounds() {
		list.add("A");
		list.add("C");
		list.add("C");
		
		String oldelement = list.set(1, "B");
		assertEquals("C", oldelement);
		assertEquals("B", list.get(1));
	}
	
	/**
	 * Test method for {@link utility.DLL#set(int, java.lang.Object)}.
	 * when list is not empty, and element to set is not null, and index is out of bounds
	 */
	@Test
	@SuppressWarnings("unused")
	void testSet_NotEmpty_NotNull_OutOfBounds() {
		list.add("A");
		list.add("C");
		list.add("C");
		try {
			String oldelement = list.set(-1, "B");
			assertEquals("C", list.get(1));
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		}
		
		try {
			String oldelement = list.set(4, "B");
			assertEquals("C", list.get(1));
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link utility.DLL#set(int, java.lang.Object)}.
	 * when list is not empty, and element to set is null
	 */
	@Test
	void testSet_NotEmpty_Null() {
		list.add("A");
		list.add("C");
		list.add("C");
		try {
			String oldelement = list.set(1, null);
			assertEquals(null, oldelement);
			fail("NullPointerExeption wasn't thrown.");
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}

	/**
	 * Test method for {@link utility.DLL#isEmpty()}.
	 * when list is empty
	 */
	@Test
	void testIsEmpty_Empty() {
		assertEquals(true, list.isEmpty());
	}

	/**
	 * Test method for {@link utility.DLL#isEmpty()}.
	 * when list is not empty
	 */
	@Test
	void testIsEmpty_NotEmpty() {
		list.add("A");
		assertEquals(false, list.isEmpty());
	}
	
	/**
	 * Test method for {@link utility.DLL#contains(java.lang.Object)}.
	 * when list is not null, and toFind element is not null
	 */
	@Test
	void testContains_NotNull_NotNull() {
		list.add("A");
		list.add("B");
		assertEquals(true, list.contains("A"));
		assertEquals(false, list.contains("C"));
	}

	/**
	 * Test method for {@link utility.DLL#contains(java.lang.Object)}.
	 * when list is not null, and toFind element is null
	 */
	@Test
	@SuppressWarnings("unused")
	void testContains_NotNull_Null() {
		list.add("A");
		list.add("B");

		try {
			boolean found = list.contains(null);
			fail("NullPointerException wasn't thrown.");
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link utility.DLL#contains(java.lang.Object)}.
	 * when list is empty
	 */
	@Test
	@SuppressWarnings("unused")
	void testContains_Empty() {
		try {
			boolean found = list.contains("A");
			fail("NullPointerException wasn't thrown.");
		} catch (NullPointerException e) {
			assertTrue(true);
		}
		
		try {
			boolean found = list.contains(null);
			fail("NullPointerException wasn't thrown.");
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link utility.DLL#toArray(E[])}.
	 * when list is not empty, and toHold is not null, and size is small
	 */
	@Test
	void testToArrayEArrayNonEmptyInsufficient() {
		list.add("A");
		list.add("B");
		list.add("C");
		
		toHold = new String[1];
		toHold = list.toArray(toHold);
		assertEquals(3, toHold.length);
		
		for(int i = 0; i < toHold.length; i++) {
			assertEquals(list.get(i), toHold[i]);
		}
		
	}
	
	/**
	 * Test method for {@link utility.DLL#toArray(E[])}.
	 * when list is not empty, and toHold is not null, and size is big
	 */
	@Test
	void testToArrayEArrayNonEmptySufficient() {
		list.add("A");
		list.add("B");
		list.add("C");
		
		toHold = new String[3];
		toHold = list.toArray(toHold);
		assertEquals(3, toHold.length);
		
		for(int i = 0; i < toHold.length; i++) {
			assertEquals(list.get(i), toHold[i]);
		}
		
	}
	
	/**
	 * Test method for {@link utility.DLL#toArray(E[])}.
	 * when list is not empty, and toHold is null
	 */
	@Test
	void testToArrayEArray_NotEmpty_Null() {
		list.add("A");
		list.add("B");
		list.add("C");
		
		try {
			toHold = list.toArray(toHold);
			
			assertEquals(list.get(0), toHold[0]);
			assertEquals(list.get(1), toHold[1]);
			assertEquals(list.get(2), toHold[2]);
			assertEquals(null, toHold[3]);
			fail("NullPointerException wasn't thrown.");
		} catch (NullPointerException e) {
			assertTrue(true);
		}
		
	}
	
	/**
	 * Test method for {@link utility.DLL#toArray(E[])}.
	 * when list is empty
	 */
	@Test
	void testToArrayEArray_Empty() {
		// toHold is not empty
		try {
			toHold = new String [1];
			toHold[0] = "A";
			toHold = list.toArray(toHold);
			fail("NullPointerException wasn't thrown.");
		} catch (NullPointerException e) {
			assertTrue(true);
		}
		
		// toHold is empty
		try {
			toHold = null;
			toHold = list.toArray(toHold);
			fail("NullPointerException wasn't thrown.");
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test method for {@link utility.DLL#toArray()}.
	 * when list is not empty
	 */
	@Test
	void testToArray_NotEmpty() {
		list.add("A");
		list.add("B");
		list.add("C");

		container = (Object[]) list.toArray();
		
		boolean test = true;
		
		for(int i=0; i<list.size() && test; i++) {
			if(!(list.get(i).equals(container[i]))) {
				test = false;
			}
		}
		
		assertTrue(test);
		assertEquals(list.size(), container.length);
	}
	
	/**
	 * Test method for {@link utility.DLL#toArray()}.
	 * when list is empty
	 */
	@Test
	void testToArray_Empty() {
		container = (Object[]) list.toArray();
		
		boolean test = true;
		
		for(int i=0; i<list.size() && test; i++) {
			if(!(list.get(i).equals(container[i]))) {
				test = false;
			}
		}
		
		assertTrue(test);
		assertEquals(list.size(), container.length);
	}
	
	/**
	 * Test method for {@link utility.DLL#iterator()}.
	 * when list is not empty
	 */
	@Test
	void testIteratorNonEmpty() {
		list.add("A");
		list.add("B");
		list.add("C");
		
		iterator = list.iterator();
		assertTrue(iterator.hasNext());
		int i = 0;
		while (iterator.hasNext()) {
			assertEquals(list.get(i++), iterator.next());
		}
		assertFalse(iterator.hasNext());
		assertThrows(NoSuchElementException.class, () -> {
			iterator.next();
		});
	}
	
	/**
	 * Test method for {@link utility.DLL#iterator()}.
	 * when list is empty
	 */
	@Test
	void testIteratorEmpty() {
		iterator = list.iterator();
		assertFalse(iterator.hasNext());
		assertThrows(NoSuchElementException.class, () -> {
			iterator.next();
		});
	}

	
	

}
