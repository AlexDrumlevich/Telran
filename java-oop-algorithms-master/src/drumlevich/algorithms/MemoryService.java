package drumlevich.algorithms;

public class MemoryService {
	public static int getMaxAvailableSize() {
		
		int leftBorderSize = 1;
		int rightBorderSize = Integer.MAX_VALUE;
		int middle = getMiddle(leftBorderSize, rightBorderSize);
		
		//while our right border not point at available size 
		while (!isMemoryAvailable(rightBorderSize)) {
		
			if(isMemoryAvailable(middle)) {
				leftBorderSize = middle + 1;
				middle = getMiddle(leftBorderSize, rightBorderSize);
			} else {
				rightBorderSize = middle - 1;
				middle = getMiddle(leftBorderSize, rightBorderSize);
			}
		}
		return rightBorderSize;
	}
	
	// if the required memory has been allocated => true
	// if OutOfMemoryError has been caught => false
	private static boolean isMemoryAvailable(int size) {
		boolean testPast;
		try {
			byte[] array = new byte[size];
			testPast = true;
		} catch (OutOfMemoryError e) {
			testPast = false;
		}
		return testPast;
	}
	
	// we can`t add leftBorderSize, rightBorderSize and then divide by 2, cause we may get overflow of int
	//this function first of all divide incoming values by 2 and then add results of division
	//if there are two odds, its add 1 to result, not to lose reminders of division by 2, witch together would become 1 (3/2=1, 5/2=2, middle=3(not correct), middle should be 4=(5+3)/2)   
	private static int getMiddle(int leftBorderSize, int rightBorderSize) {
		int middle = (leftBorderSize / 2) + (rightBorderSize / 2);
		return (leftBorderSize % 2 != 0 && rightBorderSize % 2 != 0) ? middle + 1 : middle;	
	}
	
}


