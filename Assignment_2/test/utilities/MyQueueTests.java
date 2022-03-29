package utilities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MyQueueTests {
	
	
	QueueADT<String> List;
	MyQueue queue = new MyQueue<String>();
	
	@Test 
	void testEnqueue() {
		queue.enqueue("Tagalog");
		assertEquals(queue.dequeue(), "Tagalog");
		
	}
	
	@Test 
	void testDequeue() {
		queue.enqueue("French");
		assertEquals(queue.dequeue(), "French");
		
	}
	@Test 
	void testEquals() {
		MyQueue test = new MyQueue<String>();
		queue.enqueue("Tagalog");
		test.enqueue("Arabic");
		
		assertFalse(queue.equals(test));
		test.dequeue();
		test.enqueue("Tagalog");
		assertTrue(queue.equals(test));
	}
	
	@Test 
	void testIterator() {
		queue.enqueue("Tagalog");
		queue.enqueue("Mandarin");
		queue.enqueue("Japanese");
		
		String[] expected = {"Tagalog", "Mandarin", "Japanese"};
		int i = 0;
		
		for (String Languages : expected) {
			assertEquals(expected[i++], Languages);
		}
 	}
	
	@Test 
	void testSize() {
		queue.enqueue("French");
		queue.enqueue("French");
		assertEquals(queue.size(), 2);
		queue.dequeue(); 
		queue.dequeue();
		
	}
	@Test 
	void testPeek() {
		queue.enqueue("FrenchCanadian");
		assertEquals(queue.peek(), "FrenchCanadian");
	}
	
	@Test 
	void testIsEmpty() {
		assertTrue(queue.isEmpty());
		
	}
	@Test
	void testDequeueAll() {
		
	}

}
