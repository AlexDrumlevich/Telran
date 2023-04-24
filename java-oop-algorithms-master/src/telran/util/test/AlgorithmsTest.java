package telran.util.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import telran.util.*;
import org.junit.jupiter.api.Test;

import drumlevich.algorithms.Algorithms;



class AlgorithmsTest {
	

private static final int BIG_LENGTH = 100000;
List<Short> list;
short[] numbers = {10, 0, 7, 50, 0, 20, 100, 80, 30, 15, 3};
@BeforeEach
void setUp() {
	list = new ArrayList<>(1);
	for(short i = 0; i < numbers.length; i++) {
		list.add(numbers[i]);
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
		short[] numbers2 = {10, 0, -80, 7, 50, 0, 20, -20, 100, 80, 30, -50, 15, 3};
		assertEquals(80, Algorithms.getMaxPositiveWithNegativeReflect(numbers2));
		assertEquals(-1, Algorithms.getMaxPositiveWithNegativeReflect(numbers));
	}
	
}
