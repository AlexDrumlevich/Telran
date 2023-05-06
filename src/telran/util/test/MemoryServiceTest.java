package telran.util.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import drumlevich.algorithms.MemoryService;

public class MemoryServiceTest {
	byte[] array;
	@Test
	void test() {
		
		int size = MemoryService.getMaxAvailableSize();
		System.out.println(size);
		array = new byte[size];
		boolean flException = false;
		
		try {
			array = null;
			array = new byte[size + 1];
		} catch (OutOfMemoryError e) {
			flException = true;
		}
		assertTrue(flException);
	}



}


