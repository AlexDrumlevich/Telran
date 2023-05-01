package telran.util.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
	//sum can`t be negative	
	assertTrue(Algorithms.isSum2SumCantBeNegative(numbers, 10));
	assertTrue(Algorithms.isSum2SumCantBeNegative(numbers, 0));
	assertTrue(Algorithms.isSum2SumCantBeNegative(numbers, 130));

	assertFalse(Algorithms.isSum2SumCantBeNegative(numbers, 300));
	assertFalse(Algorithms.isSum2SumCantBeNegative(numbers, 24));
	
	short[] array3 = {30000, 1, 5, 2, 10000, 0, 500,0};
	assertTrue(Algorithms.isSum2SumCantBeNegative(array3, (short)30000));
	assertTrue(Algorithms.isSum2SumCantBeNegative(array3, (short)7));
	assertFalse(Algorithms.isSum2SumCantBeNegative(array3, (short)30003));
	assertFalse(Algorithms.isSum2SumCantBeNegative(array3, (short)8));
	
	// sum can be negative
	short[] array4 = {30000, 1, 5, 2, 10000, 0, 500,0, Short.MAX_VALUE};
	assertTrue(Algorithms.isSum2SumCanBeNegative(array3, (short)30000));
	assertTrue(Algorithms.isSum2SumCanBeNegative(array3, (short)7));
	assertFalse(Algorithms.isSum2SumCanBeNegative(array3, (short)30003));
	assertFalse(Algorithms.isSum2SumCanBeNegative(array3, (short)8));
	assertTrue(Algorithms.isSum2SumCanBeNegative(array4, Short.MIN_VALUE));
	}
	
	
	
	@Test
	void testGetMaxPositiveWithNegativeReflect() {
		assertEquals(80, Algorithms.getMaxPositiveWithNegativeReflect(numbers2));
		assertEquals(-1, Algorithms.getMaxPositiveWithNegativeReflect(numbers));
		
		short[] array3 = {1, 1, 1, -1, 20, 100,200, 100 -100, -100, -20, -40, 80};
		short[] array4 = {-40, 1, -40, -6, 2, 3, 40};
		short[] array5 = {40, 1, 2, 3, 40, -30};
		assertEquals(100,
				Algorithms.getMaxPositiveWithNegativeReflect(array3));
		assertEquals(40,
				Algorithms.getMaxPositiveWithNegativeReflect(array4));
		assertEquals(-1,
				Algorithms.getMaxPositiveWithNegativeReflect(array5));
		
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
	
	
	@Test
	@Disabled
	void maxValueComplexityNTest() {
		assertEquals(Long.MAX_VALUE, getMaxValueComplexityN());
	}
	@Test
	void maxValueComplexityLogNTest() {
		assertEquals(Long.MAX_VALUE, getMaxValueComplexityLogN());
	}
	//after overflowing it`ll be max negative, so we just take previous value  
	private Long getMaxValueComplexityN() {
		long res = 1;
	
		while(res > 0) {
			res++;
		}
		return res - 1;
	}
	//we can multiplication by 2 (because of binary system, last figure always EVEN)    
	private Long getMaxValueComplexityLogN() {
		long res = 1;
		while(res > 0) {
			res *= 2;
		}
		return res - 1;
	}
	
	
}
