package utilities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyStackTests{

	//attributes
	StackADT<String> list;
	
	@BeforeEach
	void setUp() throws Exception{
		list = new MyStack<>();
	}

	@AfterEach
	void tearDown() throws Exception {
		list = null;
	}
	
	@Test
	void testClear() {
		list.clear();
		assertEquals(0, list.size());
	}
	
	void testPush() {
		
	}

}
