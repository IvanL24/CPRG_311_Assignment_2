package utilities;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;

public class MyDLL<E> implements ListADT<E> {


	private static final long serialVersionUID = -7140796753013938413L;
	MyDLLNode<E> head;
	MyDLLNode<E> tail;
	int size;

	public MyDLL() {
		head = null;
		tail = null;
		size = 0;
		
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
		head = null;
		tail = null;
		size = 0;
		
	}

	@Override
	public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
		if(index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		if(toAdd == null) {
			throw new NullPointerException();
		}
		
		MyDLLNode<E> newNode = new MyDLLNode<E>(toAdd);
		MyDLLNode<E> currNode = head;
		
		if((index == 0) && head == null) {
			head = newNode;
			tail = newNode;
		}
		else if (index == size()) {
			newNode.previous = tail;
			tail.next = newNode;
			tail = newNode;
		} else {
			if (index == 0) {
				newNode.next = head;
				head.previous = newNode;
				head = newNode;
				
			}
			else {
				int count = 0;
				while((count + 1) != index) {
					currNode = currNode.next;
					count++;
				}
				newNode.next = currNode.next;
				newNode.previous = currNode;
				currNode.next = newNode;
				newNode.next.previous = newNode;
			}
		}
		
		size++;
		return true;
	}

	@Override
	public boolean add(E toAdd) throws NullPointerException, IndexOutOfBoundsException {
		if(toAdd == null) {
			throw new NullPointerException();
		}
		
		MyDLLNode<E> newNode = new MyDLLNode<E>(toAdd);
		
		if(head == null) {
			head = newNode;
			tail = newNode;
		}
		else {
			newNode.previous = tail;
			tail.next = newNode;
			tail = newNode;
		}
		
		size++;
		return true;
	}

	@Override
	public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
		if((toAdd == null) || (toAdd.size() <= 0)) {
			throw new NullPointerException();
		}
		
		if(size() <= 0) {
			MyDLLNode<E> newNode = new MyDLLNode<E>(toAdd.get(0));
			head = newNode;
			tail = newNode;
			loadList(1, toAdd);
		}
		else {
			loadList(0, toAdd);
		}
		size += toAdd.size();
		return true;
	}

	/*
	 * load element that implements a list into DLL
	 */
	private void loadList(int i, ListADT<? extends E> toAdd) {
		MyDLLNode<E> lastNode = tail;
		while (i<toAdd.size()) {
			MyDLLNode<E> newNode = new MyDLLNode<E>(toAdd.get(i));
			lastNode = newNode;
			lastNode.previous = tail;
			tail.next = newNode;
			tail = newNode;
			i++;
		}
		
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		int it = 0;
		MyDLLNode<E> currNode = head;
		while(it != index) {
			currNode = currNode.next;
			it++;
		} 
		return currNode.element;
	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		
		MyDLLNode<E> currNode = head;
		MyDLLNode<E> toRemove = currNode;
		int count = 0;
		
		if(index == 0) {
			head = head.next;
			head.previous = null;
			toRemove.next = null;
		}
		else if (index == size() -1) {
			toRemove = tail;
			tail = tail.previous;
			tail.next = null;
			toRemove.previous = null;
		} else {
			while(count != index) {
				currNode = currNode.next;
				count++;
			}
			toRemove = currNode;
			currNode.next.previous = currNode.previous;
			currNode.previous.next = currNode.next;
			toRemove.next = null;
			toRemove.previous = null;
		}
		
		size--;
		return toRemove.element;
	}

	@Override
	public E remove(E toRemove) throws NullPointerException {
		if(toRemove == null) throw new NullPointerException();
		if(size() == 0) throw new NullPointerException();
		
		boolean notfound = true;
		MyDLLNode<E> currNode = head;
		E removedElement = null;

		while(currNode != null && notfound) {
			if(currNode.element.equals(toRemove)) {
				//first
				if(currNode.previous == null) {
					removedElement = this.head.element;
					this.head = currNode.next;
					this.head.previous = null;
					currNode.next = null;
					notfound = false;
				//last
				} else if(currNode.next == null) {
					removedElement = this.tail.element;
					this.tail = this.tail.previous;
					this.tail.next = null;
					currNode.previous = null;
					notfound = false;
				//middle
				} else {
					removedElement = currNode.element;
					currNode.next.previous = currNode.previous;
					currNode.previous.next = currNode.next;
					notfound = false;
				}
				
			}
				currNode = currNode.next;
		}
		
		size--;
		return removedElement;
	}

	@Override
	public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
		if((index < 0) || (index > size())) throw new IndexOutOfBoundsException();
		if(toChange == null) throw new NullPointerException();
		
		MyDLLNode<E> currNode = head;
		E oldElement = null;
		
		//beginning
		if(index == 0) {
			oldElement = this.head.element;
			this.head.element = toChange;
		//last
		} else if(index == size()) {
			oldElement = this.tail.element;
			this.tail.element = toChange;
		} else {
			int count = 0;
			while(count != index) {
				currNode = currNode.next;
				count++;
			}
			oldElement = currNode.element;
			currNode.element = toChange;
		}
		
		return oldElement;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public boolean contains(E toFind) throws NullPointerException {
		if(toFind == null || size() == 0) {
			throw new NullPointerException();
		}
		
		boolean exists = false;
		MyDLLNode<E> currNode = head;
		
		while ((!exists) && currNode != null) {
			if (currNode.element.equals(toFind)) {
				exists = true;
			}
			else {
				currNode = currNode.next;
			}
		}
		
		return exists;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public E[] toArray(E[] toHold) throws NullPointerException {
		if(toHold == null || size() == 0) {
			throw new NullPointerException();
		}
		
		if(toHold.length < size()) {
			toHold = (E[]) Array.newInstance(toHold.getClass().getComponentType(), size);
		}

		MyDLLNode<E> currNode = head;
		
		for(int i=0; i<size() && currNode != null; i++) {
			toHold[i] = currNode.element;
			currNode = currNode.next;
		}
		
		return toHold;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object[] toArray() {
		Object[] toReturn = (E[]) (Array.newInstance(Object.class, size));		

		MyDLLNode<E> currNode = head;
		for(int i=0; i<toReturn.length && currNode != null; i++, currNode = currNode.next) {
			toReturn[i] = currNode.element;
		}
		
		return toReturn;
	}

	@Override
	public Iterator<E> iterator() {
		return new ArrayBasedIterator();

	}

	
	private class ArrayBasedIterator implements Iterator<E>{

		private int pos;
		
		@Override
		public boolean hasNext() {
			return pos < size;
		}

		@Override
		public E next() throws NoSuchElementException {
			if(size() <=0 ) throw new NoSuchElementException();
			if(pos >= size) throw new NoSuchElementException();
			
			E toReturn = get(pos);
			pos++;
			return toReturn;			
		}
		
	}
	
	
}
