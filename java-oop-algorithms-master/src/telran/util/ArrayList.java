package telran.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import javax.swing.plaf.synth.SynthUI;

public class ArrayList<T> implements List<T> {
	private static final int DEFAULT_CAPACITY = 16;
	private T[] array;
	private int size;

	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		array = (T[]) new Object[capacity];
	}

	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}

	@Override
	public boolean add(T obj) {
		if (size == array.length) {
			reallocate();
		}
		array[size] = obj;
		size++;
		return true;
	}

	private void reallocate() {
		array = Arrays.copyOf(array, array.length * 2);

	}

	@Override
	public void add(int index, T obj) {
		if (size == array.length) {
			reallocate();
		}
		System.arraycopy(array, index, array, index + 1, size - index);
		array[index] = obj;
		size++;
	}

	@Override
	public T remove(int index) {
		T res = array[index];

		System.arraycopy(array, index + 1, array, index, size - index - 1);
		size--;
		return res;
	}

	@Override
	public T get(int index) {
		T res = array[index];
		return res;
	}

	@Override
	public int size() {

		return size;
	}

	@Override
	public boolean remove(T pattern) {
		boolean res = false;
		int index = indexOf(pattern);
		if (index > -1) {
			res = true;
			remove(index);
		}
		return res;
	}

	@Override
	public T[] toArray(T[] ar) {
		if (ar.length < size) {
			ar = Arrays.copyOf(ar, size);
		}
		System.arraycopy(array, 0, ar, 0, size);
		if (ar.length > size) {
			ar[size] = null;
		}

		return ar;
	}

	@Override
	public int indexOf(T pattern) {
		int res = -1;
		int index = 0;
		while (index < size && res == -1) {
			if (isEqual(array[index], pattern)) {
				res = index;
			}
			index++;
		}
		return res;
	}

	private boolean isEqual(T object, T pattern) {

		return pattern == null ? object == pattern : pattern.equals(object);
	}

	@Override
	public int lastIndexOf(T pattern) {
		int res = -1;
		int index = size - 1;
		while (index >= 0 && res == -1) {
			if (isEqual(array[index], pattern)) {
				res = index;
			}
			index--;
		}
		return res;
	}



	//SORT
	//STANDART SORT
	@SuppressWarnings("unchecked")
	@Override
	public void sort() {
		//we can get comparator and pass to our sort method witch use it
		sort((Comparator<T>) Comparator.naturalOrder());
		//or use the next:
		//Arrays.sort(array, 0, size);		
	}

	@Override
	public void sort(Comparator<T> comp) {
		Arrays.sort(array, 0, size, comp);

	}

	//OWN SORT REALISATION
	//Bubble sorting with comparable - we use natural order and we can use only Comparable objects
	public void bubleSortUsingComparable() {

		boolean swaped = true;

		for(int i = 0; i < size - 1 && swaped; i++) {
			swaped = false;

			//if we never get into if - so all elements are sorted and we do not need continue
			for(int j = 0; j < size - 1 - i; j++) {
				//cast to Comparable type to use compare method to define if first more or not more 
				//then second 
				if(((Comparable<T>)array[j]).compareTo(array[j+1]) > 0) {
					T tmp = array[j+1] = array[j];
					array[j] = tmp;
					swaped = true;
				}
			}
		}
	}

	//bubble sorting with comparator - we use our own order 
	public void bubleSortUsingComparator(Comparator<T> comparator) {

		boolean swaped = true;

		for(int i = 0; i < size - 1 && swaped; i++) {
			swaped = false;

			//if we never get into if - so all elements are sorted and we do not need continue
			for(int j = 0; j < size - 1 - i; j++) {
				//cast to Comparable type to use compare method to define if first more or not more 
				//then second 
				if(comparator.compare(array[j], array[j+1]) > 0) {
					T tmp = array[j+1] = array[j];
					array[j] = tmp;
					swaped = true;
				}
			}
		}
	}

	//bubble sorting using do-while (not for )
	public void sortBubllesAnotherRealization(Comparator<T> comp) {
		int n = size;
		boolean flUnSort = true;
		do {
			flUnSort = false;
			n--;
			for(int i = 0; i < n; i++) {
				if (comp.compare(array[i], array[i + 1]) > 0) {
					swap(i);
					flUnSort = true;
				}
			}
		} while(flUnSort);

	}

	private void swap(int i) {
		T tmp = array[i];
		array[i] = array[i + 1];
		array[i + 1] = tmp;

	}


	//PREDICATE


	public int indexOf(Predicate<T> predicate) {
		//return -1 if Predicate condition won`t be executed 
		int res = -1;

		int index = 0;
		while (index < size && res == -1) {
			if (predicate.test(array[index])) {
				res = index;
			}
			index++;
		}
		return res;
	}


	public int lastIndexOf(Predicate<T> predicate) {

		int res = -1;

		int index = size - 1;
		while (index >= 0 && res == -1) {
			if(predicate.test(array[index])) {
				res = index;
			}
			index--;
		}
		return res;
	}


	public boolean removeIf(Predicate<T> predicate) {
		int startSize = size;
		for(int i = size - 1; i >= 0; i--) {
			if(predicate.test(array[i])) {
				remove(i);
			}
		}
		return startSize != size;
	}
}



