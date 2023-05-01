package drumlevich.algorithms;

import java.util.Comparator;
import java.util.Iterator;

public class Algorithms {

	// Sort array of short type value
	//O[n]
	public static void sortShortPositive(short [] array) {
		int [] helper = new int[Short.MAX_VALUE];
		//pass throw array first time 
		//helper[index] => count of occurrences for number index in array
		//helper[1000] = 2 => number 1000 occurs twice in the source array
		//helper[2] = 0;
		//we use index of helper array to keep the same value of element from "array" 
		for(int i = 0; i < array.length; i++) {
			helper[array[i]]++; 
		}
		//in spite of iteration on array into array we pass throw the array 
		//only 2 times: 1. we pass throw "helper"; 2. we pass each element in "helper" as many 
		//time as value amount (and if value > 0), witch will be the same amount as many 
		//elements in array "array"
		int ind = 0;
		for(int i = 0; i < helper.length; i++) {
			for(int j = 0; j < helper[i]; j++) {
				array[ind++] = (short) i;
			}
		}
	}

	public static void bubbleSortShortsPositive(short [] array) {

		boolean wasFlipped = true;
		for(int i = 0; i < array.length - 1 && wasFlipped; i++) {
			wasFlipped = false;
			for(int j = 0; j < array.length - 1 - i; j++) {
				if(array[j] > array[j + 1]) {
					short tmp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = tmp;
					wasFlipped = true;
				}
			}
		}
	}

	//for values > 0 
	//sum - int, so it could`t be negative (if we use short it can overflow so can be negative)
	public static boolean isSum2SumCantBeNegative(short[] array, int sum) {
		//returns true if there are two numbers in the given array,
		// sum of which equals the given sum value
		//otherwise false

		//if true => we met value in array (element value in array = index value in helper) earlier
		//boolean[] helper = new boolean[Short.MAX_VALUE];
		//sum + 1 - because each of parts of this sum can`t be more this sum (max of one of the part = sum, so second part = 0)
		boolean[] helper = new boolean[sum + 1];

		for(int i = 0; i < array.length; i++) {
			if(sum < array[i]) {
				continue;
			}
			//if element < sum   and   we have value - difference (sum - element) in helper
			if(helper[sum - array[i]] && sum - array[i] >= 0) {
				return true;
			} else {
				helper[array[i]] = true;
			}
		}
		return false;
	}


	//for values > 0 
	//sum - int, so it could`t be negative (if we use short it can overflow so can be negative)
	public static boolean isSum2SumCanBeNegative(short[] array, short sum) {

		//array of the positive short numbers
		//returns true if there are two numbers in the given array,
		//sum of which equals the given sum value
		//otherwise false
		//if true => we met value in array (element value in array = index value in helper) earlier

		// size for helper - we don`t need more then sum (if it positive, because 
		//this sum could consist of 2 value that are less, so we won`t handle value more then sum
		//but if sum is negative, so one of value could be max of short, so we get size Short.MAX_VALUE + 1 (because of
		//starting from zero
		int helperSize = sum < 0 ? Short.MAX_VALUE + 1 : sum + 1;
		boolean[] helper = new boolean[helperSize];
		boolean res = false;
		//start index
		int index = 0;
		//while index less then array length and we don`t find 2 values witch give sum  
		while (index < array.length && !res) {
			short value = array[index];
			short secondValue =  (short) (sum - value);
			//second value can be negative( for ex. sum = - 30000, and value 10, so it will be -30010), and if it is negative -
			//it`s not that we looking for (incoming array can`t contain negative values) 
			if (secondValue >= 0) {
				if (helper[secondValue]) {
					res = true;
				} else {
					helper[value] = true;
				}
			}
			index++;
		}
		return res;
	}




	public static short getMaxPositiveWithNegativeReflect(short[] array) {
		//returns maximal positive number, for which there is the negative image
		//If there are not such numbers at all the method returns -1
		short[] helper = new short[Short.MAX_VALUE];

		short maxValue = -1;

		for(int i = 0; i < array.length; i++) {
			//if we have max more then abs of current value => continue
			if(maxValue >= Math.abs(array[i])) {
				continue;

			} else {	
				//if current element value > 0 => set to helper 1, otherwise set -1
				//if current element value > 0 and in helper we have -1, or otherwise,
				//=> new maxValue was found 

				if(array[i] > 0) {
					if(helper[array[i]] == -1) {
						maxValue = array[i];
					} else {
						helper[array[i]] = 1;
					}

					//if current element value < 0
				} else if(array[i] < 0) {

					if(helper[Math.abs(array[i])] == 1) {
						maxValue = (short) Math.abs(array[i]);
					} else {
						helper[Math.abs(array[i])] = -1;
					}
				}
			}
		}
		return maxValue;

	}


	//BINARY SEARCHING

	// binary search
	public static <T> int binarySearch(T [] array, T key,
			Comparator<T> comp) {
		//left index in the beginning - first (0) index in the array
		int leftIndex = 0;
		//right index in the beginning - max index in the array
		int rightIndex = array.length - 1;
		//middle
		int middleIndex = rightIndex / 2;
		//comparable result
		int compRes = 0;
		while(leftIndex <= rightIndex &&
				(compRes = comp.compare(key, array[middleIndex] )) != 0) {
			if (compRes > 0) {
				//move to right part of the array, because key more then middle
				//middleIndex + 1 because we`ve already check middle
				leftIndex = middleIndex + 1;
			} else {
				//move to right part of the array, because key less then middle
				//middleIndex - 1 because we`ve already check middle
				rightIndex = middleIndex - 1;
			}
			//new middle
			middleIndex = (leftIndex + rightIndex) / 2;

		}
		//if we get leftIndex > rightIndex so cycle finished and we didn`t find searched element
		//else, return middle, because only if comparable middle and key we can get 0 (equals)
		return leftIndex > rightIndex ? -1 : middleIndex;
	}

	
}
