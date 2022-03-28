package utilities;

@SuppressWarnings("serial")
public class MyStack<E> implements StackADT<E>  {

	MyArrayList<E> list = new MyArrayList<>();
	
	public MyStack(){
		// TODO Auto-generated constructor stub
	}


	public void push(E toAdd) throws NullPointerException {
		// TODO Auto-generated method stub
		list.add(toAdd);
	}

	@Override
	public E pop() {
		E removedElement = list.remove(list.size()-1);
		return removedElement;
	}

	@Override
	public E peek() {
		E peekElement = list.get(list.size()-1);
		return peekElement;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public void clear() {
		list.clear();
		
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return list.isEmpty();
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return list.toArray();
	}

	@Override
	public E[] toArray(E[] holder) throws NullPointerException {
		// TODO Auto-generated method stub
		return list.toArray(holder);
	}

	@Override
	public boolean contains(E toFind) throws NullPointerException {
		// TODO Auto-generated method stub
		return list.contains(toFind);
	}

	@Override
	public int search(E toFind) {
		// TODO Auto-generated method stub
		if(contains(toFind)) {
			for(int i = 0; i < list.size(); i++) {
				if(list.get(i).equals(toFind)) {
					return i+1;
				}
			}
		}
		return -1;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return list.iterator();
	}

	@Override
	public boolean equals(StackADT<E> that) {
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

}
