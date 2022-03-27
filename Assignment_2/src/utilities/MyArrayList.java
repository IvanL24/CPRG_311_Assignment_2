package utilities;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;

public class MyArrayList<E> implements ListADT<E>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6737923255335121677L;
	//attributes
	private E[] array;
	private int size;
	private int length;

	//contructor
	@SuppressWarnings("unchecked")
	public MyArrayList() {
		array = (E[]) new Object[10];
		size = 0;
		array = (E[]) new Object [length];
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub.
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
		if(index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		if(toAdd == null) {
			throw new NullPointerException();
		}
		
		E []newarray;
		
		if(size == array.length) {
			//create new array (bigger than the original) x2
			//use loop to copy everything from the original array into the new array
			//get array to reference the new array
			this.length += 10;
			newarray = (E[]) new Object [length];
			
			for (int i = 0; i<size; i++) {
				newarray[i] = array[i];
			}
			
			newarray[index] = toAdd;
			array = newarray;
			size++;
			newarray = null;
		}
		else {
			array[index] = toAdd;
			size++;
		}
		
		//TODO Insert toAdd into index position (requires a loop to shift everything from index forward)
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean add(E toAdd) throws NullPointerException {
		if(toAdd == null) {
			throw new NullPointerException();
		}
		
		E []newarray;
		//check for capacity
		if(size == array.length) {
			//create new array (bigger than the original) x2
			//use loop to copy everything from the original array into the new array
			//get array to reference the new array
			this.length += 10;
			newarray = (E[]) new Object[length];
			
			for(int i=0; i<size(); i++) {
				newarray[i] = this.array[i];
			}
			
			newarray[size] = toAdd;
			this.array = newarray;
			size++;
			newarray = null;
			
		}
		else {
		array[size()] = toAdd;
		size++;
		}
		return true;
	}

	@Override
	public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
		if((toAdd == null) || (toAdd.size() <= 0)) {
			throw new NullPointerException();
		}
		
		int newsize = size() + toAdd.size();

		if(newsize > length) {
			while(newsize > length) {
				length += 10;
			}
		}
		
		loadElementsToArray(newsize, length, toAdd);
		return true;
	}

	@SuppressWarnings("unchecked")
	private void loadElementsToArray(int newsize, int length2, ListADT<? extends E> toAdd) {
		int index = 0;
		E []newarray;	
		
		newarray = (E[]) new Object[length];
		
		for(int i=0; i<this.size; i++, index++) {
			newarray[index] = this.array[i];
		}
		
		for(int i=0; i<toAdd.size(); i++, index++) {
			newarray[index] = toAdd.get(i);
		}
		
		this.size = newsize;
		this.array = newarray;
		newarray = null;
	}
		

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException();
		}
		return array[index];
	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= size()) throw new IndexOutOfBoundsException();
		
		E toRemove = this.array[index];
		shiftElementsToLeft(index);
		
		return toRemove;
	}

	@Override
	public E remove(E toRemove) throws NullPointerException {
		if(toRemove == null) { 
			throw new NullPointerException();
		}
		if(size() == 0) {
			throw new NullPointerException();
		}
		
		E removedElement = null;
		boolean toRemoveElementInList = false;
		int toRemoveIndex = 0;
		
		for(int i=0; i<size(); i++) {
			if(this.array[i] == toRemove) {
				removedElement = this.array[i];
				toRemoveIndex = i;
				toRemoveElementInList = true;
				break;
			}
		}
		
		if(toRemoveElementInList) {
			shiftElementsToLeft(toRemoveIndex);
		}
		
		return removedElement;
	}

	private void shiftElementsToLeft(int index) {
		while(index < size()-1) {
			this.array[index] = this.array[index+1];
			index++;
		}
		
		size--;
		
	}

	@Override
	public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(E toFind) throws NullPointerException {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E[] toArray(E[] toHold) throws NullPointerException {
		if(toHold.length < this.size) {
			toHold = (E[]) Array.newInstance(array[0].getClass(), this.size);
		}
		
		System.arraycopy(array, 0, toHold, 0, this.size);
		
		return toHold;
	}

	@Override
	public Object[] toArray() {
//		Object[] toHold = (E[]) Array.newInstance(array[0].getClass(), this.size);
		Object[] toHold = (E[]) new Object[this.size];
		System.arraycopy(array, 0, toHold, 0, this.size);

		return toHold;
	}

	@Override
	public Iterator<E> iterator() {
		
		return new ArrayBasedIterator();
	}
	
	/*********************************** INNER CLASS ******************************************/
	
	private class ArrayBasedIterator implements Iterator<E> {
		
		private int pos;
		private E[] copy;
		
		public ArrayBasedIterator() {
			copy = (E[]) new Object[size];
			System.arraycopy(array, 0, copy, 0, size);
		}
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return pos < size;
		}

		@Override
		public E next() throws NoSuchElementException {
			if(pos >= size) {
				throw new NoSuchElementException();
			}
			E toReturn = array[pos++];
			return toReturn;
		}
		
	}


}
