// four edge cases of LinkedLists: will this work if there is nothing? will this work if there is only one thing? will this work if I am messing with the beginning? will this work if I am messing with the end?\
// assume obj is not null

public class SinglyLinkedList<E> {
	private ListNode<E> head;
	private ListNode<E> tail;

	// Constructor: creates an empty list
	public SinglyLinkedList() {
		head = null;
		tail = null;
	}

	// Constructor: creates a list that contains
	// all elements from the array values, in the same order
	public SinglyLinkedList(E[] values) {
		if (values.length == 0) {
			head = null;
			tail = null;
		}
		for (int i = 0; i < values.length; i++) {

		}
	}

	public ListNode<E> getHead() {
		return head;
	}

	public ListNode<E> getTail() {
		return tail;
	}

	public ListNode<E> getNode(int index) {
		if (index < 0 || index > this.size() - 1) {
			throw new IndexOutOfBoundsException();
		}
		ListNode<E> node = head;
		for (int i = 0; i < index; i++) {
			if (node == null) {
				throw new IndexOutOfBoundsException();
			}
			node = node.getNext();
		}
		return node;
	}

	// Returns true if this list is empty; otherwise returns false.
	public boolean isEmpty() {
		if (head == null) {
			return true;
		}
		return false;
	}

	// Returns the number of elements in this list.
	public int size() {
		ListNode<E> node = head;
		int count = 0;
		while (node != null) {
			count++;
			node = node.getNext();
		}
		return count;
	}

	// Returns true if this list contains an element equal to obj;
	// otherwise returns false.
	public boolean contains(E obj) {
		for (ListNode<E> node = head; node != null; node = node.getNext()) {
			if (node != null && node.getValue().equals(obj)) {
				return true;
			}
		}
		return false;
	}

	// Returns the index of the first element in equal to obj;
	// if not found, returns -1.
	public int indexOf(E obj) {
		int count = 0;
		for (ListNode<E> node = head; node != null; node = node.getNext()) {
			if (node != null && node.getValue().equals(obj)) {
				return count;
			}
			count++;
		}
		return -1;
	}

	// Adds obj to this collection. Returns true if successful;
	// otherwise returns false.
	public boolean add(E obj) {
		ListNode<E> newNode = new ListNode<>(obj);
		if (head == null) {
			head = newNode;
		} else {
			tail.setNext(newNode);
		}
		tail = newNode;
		return true;
	}

	// Removes the first element that is equal to obj, if any.
	// Returns true if successful; otherwise returns false.
	// assume obj is not null
	public boolean remove(E obj) {
		ListNode<E> previous = null;
		for (ListNode<E> node = head; node != null; node = node.getNext()) {
			if (node != null && node.getValue().equals(obj)) {
				if (previous == null) {
					head = node.getNext();
				} else {
					previous.setNext(node.getNext());
				}
				if (node == tail) {
					tail = previous;
				}
				return true;
			}
			previous = node;
		}
		return false;
	}

	// Returns the i-th element.
	public E get(int i) {
		return this.getNode(i).getValue();
	}

	// Replaces the i-th element with obj and returns the old value.
	public E set(int i, E obj) {
		ListNode<E> node = getNode(i);
		E oldValue = node.getValue();
		node.setValue(obj);
		return oldValue;
	}

	// Inserts obj to become the i-th element. Increments the size
	// of the list by one.
	public void add(int i, E obj) {
		if (i < 0 || i > this.size()) {
			throw new IndexOutOfBoundsException();
		}
		if (i == 0) {
			head = new ListNode<>(obj, head);
			if (tail == null) {
				tail = head;
			}
			return;
		}
		ListNode<E> previous = getNode(i - 1);
		ListNode<E> addition = new ListNode<>(obj, previous.getNext());
		previous.setNext(addition);
		if (addition.getNext() == null) {
			tail = addition;
		}
	}

	// Removes the i-th element and returns its value.
	// Decrements the size of the list by one.
	public E remove(int i) {
		if (i < 0 || i > this.size()) {
			throw new IndexOutOfBoundsException();
		}
		if (i == 0) {
			if (head == null) {
				throw new IndexOutOfBoundsException();
			}
			E oldValue = head.getValue();
			head = head.getNext();
			if (head == null) {
				tail = null;
			}
			return oldValue;
		}
		ListNode<E> previous = getNode(i - 1);
		ListNode<E> removed = previous.getNext();
		if (removed == null) {
			throw new IndexOutOfBoundsException();
		}
		previous.setNext(removed.getNext());
		if (removed == tail) {
			tail = previous;
		}
		return removed.getValue();
	}

	// Returns a string representation of this list exactly like that for
	// MyArrayList.
	public String toString() {
		StringBuilder output = new StringBuilder("[");
		for (ListNode<E> node = head; node != null; node = node.getNext()) {
			if (node != head) {
				output.append(", ");
			}
			output.append(node.getValue());
		}
		return output.append("]").toString();
	}

}
