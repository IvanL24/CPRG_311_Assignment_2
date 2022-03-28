package utilities;

import java.util.NoSuchElementException;

public class MyQueue<E> implements QueueADT<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -568716973545361617L;
	//attributes
	MyDLL<E> list = new MyDLL<>();

	@Override
	public void enqueue(E toAdd) throws NullPointerException {
		list.add(toAdd);
	}

	@Override
	public E dequeue() throws NoSuchElementException{
		E dequeuedElement = list.remove(0);
		return dequeuedElement;
	}
	
	public void dequeueAll(){
		list.clear();
	}

	public E peek() throws NoSuchElementException{
		if(size() == 0) {
			throw new NoSuchElementException();
		}
		
		E firstElement = list.get(0);
		return firstElement;
		
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return list.size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return list.isEmpty();
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return list.iterator();
	}

	@Override
	public boolean equals(QueueADT<E> that) {
		// TODO Auto-generated method stub
		if(that == null) throw new NullPointerException();
		if(that.size() == 0 || size() == 0) throw new NullPointerException();
		
		Iterator<E> i1 = list.iterator();
		Iterator<E> i2 = that.iterator();
		boolean flag = true;
		
		if(list.size() == that.size()) {
			while(i1.hasNext() && i2.hasNext()) {
				if(!(i1.next().equals(i2.next()))) {
					flag = false;
				}
			}	
		} else {
			flag = false;
		}

		return flag;
	}

	@Override
	public Object[] toArray() {
		return list.toArray();
	}

	@Override
	public E[] toArray(E[] holder) throws NullPointerException {
		return list.toArray(holder);
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return false;
	}

}
