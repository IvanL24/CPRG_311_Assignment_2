package utilities;

public class MyDLLNode<E> {
	E element;
	MyDLLNode<E> next;
	MyDLLNode<E> previous;

	public MyDLLNode(E element) {
		this.element = (E) element;
	}

}
