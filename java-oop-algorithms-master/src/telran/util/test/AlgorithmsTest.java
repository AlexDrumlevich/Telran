package telran.util.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import drumlevich.algorithms.Algorithms;



class AlgorithmsTest {
	

private static final int BIG_LENGTH = 100000;

short[] numbers = {10, 0, 7, 50, 0, 20, 100, 80, 30, 15, 3};
short[] numbers2 = {10, 0, -80, 7, 50, 0, 20, -20, 100, 80, 30, -50, 15, 3};

static short[] randomArray;
static short[] sortedArray;

@BeforeEach
void setUp() {
	createArraysForTest(BIG_LENGTH);
	
}
	
private void createArraysForTest(int size) {
	//create and initialize random array
	randomArray = new short[size];
	for(int i = 0; i < size; i++) {
		randomArray[i] = (short)(Math.random() * Short.MAX_VALUE);
	}

	//create sorted array
	sortedArray = Arrays.copyOf(randomArray, size);
	Arrays.sort(sortedArray);
	
	//if random array was created like sorted => call current method one more time
	if(Arrays.compare(randomArray, sortedArray) == 0) {
		createArraysForTest(size);
	}

}

	@Test
	void testIsSum2() {
	assertTrue(Algorithms.isSum2(numbers, 10));
	assertTrue(Algorithms.isSum2(numbers, 0));
	assertTrue(Algorithms.isSum2(numbers, 130));

	assertFalse(Algorithms.isSum2(numbers, 300));
	assertFalse(Algorithms.isSum2(numbers, 24));
	}
	
	@Test
	void testGetMaxPositiveWithNegativeReflect() {
		assertEquals(80, Algorithms.getMaxPositiveWithNegativeReflect(numbers2));
		assertEquals(-1, Algorithms.getMaxPositiveWithNegativeReflect(numbers));
	}
	
	@Test
	void testSortShortPositiveWithHelperArray() {
	    assertNotEquals(0, Arrays.compare(randomArray, sortedArray));
		Algorithms.sortShortPositive(randomArray);
		assertArrayEquals(randomArray, sortedArray);

	}
	
	@Test
	void testBubbleSortShort() {
		assertNotEquals(0, Arrays.compare(randomArray, sortedArray));
		Algorithms.bubbleSortShortsPositive(randomArray);
		assertArrayEquals(randomArray, sortedArray);

	
	}
	
	
}
