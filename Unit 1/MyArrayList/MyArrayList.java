/* See ArrayList documentation here:
 * http://docs.oracle.com/javase/7/docs/api/java/util/ArrayList.html
 */

/*
 * Your indexed functions should throw IndexOutOfBoundsException if index is invalid!
 */

public class MyArrayList<E> {

	/* Internal Object counter */
	protected int objectCount;

	/* Internal Object array */
	protected E[] internalArray;

	/* Constructor: Create it with whatever capacity you want? */
	@SuppressWarnings("unchecked")
	public MyArrayList() {
		this.internalArray = (E[]) new Object[100];
	}

	/* Constructor with initial capacity */
	@SuppressWarnings("unchecked")
	public MyArrayList(int initialCapacity) {
		this.internalArray = (E[]) new Object[initialCapacity];
	}

	/* Return the number of active slots in the array list */
	public int size() {
		return this.objectCount;
	}

	/* Are there zero objects in the array list? */
	public boolean isEmpty() {
		return this.objectCount == 0;
	}

	/* Get the index-th object in the list. */
	public E get(int index) {
		if (index < 0 || index >= this.objectCount) {
			throw new IndexOutOfBoundsException();
		}
		return internalArray[index];
	}

	/* Replace the object at index with obj. returns object that was replaced. */
	public E set(int index, E obj) {
		if (index < 0 || index >= this.objectCount) {
			throw new IndexOutOfBoundsException();
		}
		E output = this.internalArray[index];
		this.internalArray[index] = obj;
		return output;
	}

	/*
	 * Returns true if this list contains an element equal to obj;
	 * otherwise returns false.
	 */
	public boolean contains(E obj) {
		for (int i = 0; i < this.objectCount; i++) {
			if (obj == null) {
				if (this.internalArray[i] == null) {
					return true;
				}
			} else if (obj.equals(this.internalArray[i])) {
				return true;
			}
		}
		return false;
	}

	/* Insert an object at index */
	public void add(int index, E obj) {
		if (index < 0 || index > this.objectCount) {
			throw new IndexOutOfBoundsException();
		}
		if (this.objectCount == this.internalArray.length) {
			int newSize = this.internalArray.length * 2;
			if (newSize == 0) {
				newSize = 1;
			}

			E[] largerArray = (E[]) new Object[newSize];
			for (int i = 0; i < this.objectCount; i++) {
				largerArray[i] = this.internalArray[i];
			}
			this.internalArray = largerArray;
		}

		for (int i = this.objectCount; i > index; i--) {
			this.internalArray[i] = this.internalArray[i - 1];
		}

		this.internalArray[index] = obj;
		this.objectCount++;
	}

	/* Add an object to the end of the list; returns true */
	public boolean add(E obj) {
		this.add(this.objectCount, obj);
		return true;
	}

	/* Remove the object at index and shift. Returns removed object. */
	public E remove(int index) {
		if (index < 0 || index >= this.objectCount) {
			throw new IndexOutOfBoundsException();
		}
		E obj = this.internalArray[index];
		for (int i = index; i < this.objectCount - 1; i++) {
			this.internalArray[i] = this.internalArray[i + 1];
		}
		this.objectCount--;
		this.internalArray[this.objectCount] = null;
		return obj;
	}

	/*
	 * Removes the first occurrence of the specified element from this list,
	 * if it is present. If the list does not contain the element, it is unchanged.
	 * More formally, removes the element with the lowest index i such that
	 * (o==null ? get(i)==null : o.equals(get(i))) (if such an element exists).
	 * Returns true if this list contained the specified element (or equivalently,
	 * if this list changed as a result of the call).
	 */
	public boolean remove(E obj) {
		for (int i = 0; i < this.objectCount; i++) {
			if (obj == null) {
				if (this.internalArray[i] == null) {
					remove(i);
					return true;
				}
			} else if (obj.equals(this.internalArray[i])) {
				remove(i);
				return true;
			}
		}
		return false;
	}

	/*
	 * For testing; your string should output as "[X, X, X, X, ...]" where X, X, X,
	 * X, ... are the elements in the ArrayList.
	 * If the array is empty, it should return "[]". If there is one element, "[X]",
	 * etc.
	 * Elements are separated by a comma and a space.
	 */
	public String toString() {
		StringBuilder output = new StringBuilder("[");
		for (int i = 0; i < this.objectCount; i++) {
			if (i > 0) {
				output.append(", ");
			}
			output.append(this.internalArray[i]);
		}
		return output.append("]").toString();
	}
}
