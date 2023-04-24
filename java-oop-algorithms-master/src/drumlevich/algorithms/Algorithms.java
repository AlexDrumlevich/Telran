package drumlevich.algorithms;

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
		
		//for values > 0 
		public static boolean isSum2(short[] array, int sum) {
			//returns true if there are two numbers in the given array,
			// sum of which equals the given sum value
			//otherwise false
	
			//if true => we met value in array (element value in array = index value in helper) earlier
			boolean[] helper = new boolean[Short.MAX_VALUE];
	
			
			for(int i = 0; i < array.length; i++) {
				//if element < sum   and   we have value - difference (sum - element) in helper
				if(helper[Math.abs(sum - array[i])] && sum - array[i] >= 0) {
					return true;
				} else {
					helper[array[i]] = true;
				}
			}
			return false;
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

	}
