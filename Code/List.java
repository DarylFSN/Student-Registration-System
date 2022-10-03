/**
 * List.java
 * @author Kai Li Tan
 * CIS 22C, Final Presentation
 */
import java.util.NoSuchElementException;

public class List<Student extends Comparable<Student>> {
    private class Node {
        private Student student;
        private Node next;
        private Node prev;

        public Node(Student student) {
            this.student = student;
            this.next = null;
            this.prev = null;
        }
    }

    private int length;
    private Node first;
    private Node last;
    private Node iterator;

    /****CONSTRUCTOR****/

    /**
     * Instantiates a new List with default values
     * @postcondition initializes first, last, iterator
     * and length with default values
     */
    public List() {
    	length = 0;
    	first = null;
    	last = null;
    	iterator = null;
    }

    /**
     * Instantiates a new List by copying another List
     * @param original the List to make a copy of
     * @postcondition a new List object, which is an identical
     * but separate copy of the List original
     */
    public List(List<Student> original) {
        if (original.length == 0) {
            length = 0;
            first = null;
            last = null;
            iterator = null;
        } else {
            Node temp = original.first;
            while (temp != null) {
                addLast(temp.student);
                temp = temp.next;
            }
            iterator = null;
        }
    }

    /****ACCESSORS****/

    /**
     * Returns the value stored in the first node
     * @precondition !isEmpty()
     * @return the value stored at node first
     * @throws NoSuchElementException when precondition is violated
     */
    public Student getFirst() throws NoSuchElementException{
        if (length == 0) {
            throw new NoSuchElementException("getFirst: List is Empty. No data to access!");
        }
        return first.student;
    }

    /**
     * Returns the value stored in the last node
     * @precondition !isEmpty()
     * @return the value stored in the node last
     * @throws NoSuchElementException when precondition is violated
     */
    public Student getLast() throws NoSuchElementException{
        if (length == 0) {
        	throw new NoSuchElementException("getLast: List is Empty. No data to access!");
        }
    	return last.student;
    }

    /**
     * Returns the current length of the list
     * @return the length of the list from 0 to n
     */
    public int getLength() {
        return length;
    }

    /**
     * Returns whether the list is currently empty
     * @return whether the list is empty
     */
    public boolean isEmpty() {
        return length == 0;
    }

    /**
     * Returns the element currently pointed
     * at by the iterator
     * @precondition the iterator cannot be null
     * @return the value pointed at by the iterator
     * @throws NullPointerException when precondition is violated
     */
    public Student getIterator() throws NullPointerException{
        if (offEnd()) {
            throw new NullPointerException("getIterator(): "
        + "the iterator is off end. Cannot return data.");
        }
        return iterator.student;
    }

    /**
     * Returns whether the iterator is off the end of the
     * list, i.e. is NULL
     * @return whether the iterator is null
     */
    public boolean offEnd() {
    	return iterator == null;
    }

    /**
     *  Overrides the equals method for object to
     *  compares this list to another list to see if they
     *  contain the same data in the same order
     *  @param L the List to compare to this List
     *  @return whether the two Lists are equal
     */
    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        if(o == this) {
            return true;
        } else if (!(o instanceof List)) {
            return false;
        } else {
            List<Student> L = (List<Student>) o;
            if (this.length != L.length) {
                return false;
            } else {
                Node temp1 = this.first;
                Node temp2 = L.first;
                while (temp1 != null) {
                    if (temp1.student != temp2.student) {
                        return false;
                    }
                    temp1 = temp1.next;
                    temp2 = temp2.next;
                }
                return true;
            }
        }
    }

    /**
     * Determines whether a List is sorted
     * by calling its recursive helper method
     * isSorted
     * Note: An empty List can be
     * considered to be (trivially) sorted
     * @return whether this List is sorted
     */
    public boolean inSortedOrder() {
    	return inSortedOrder(first);
    }

    /**
     * Helper method to inSortedOrder
     * Determines whether a List is
     * sorted in ascending order recursively
     * @return whether this List is sorted
	*/
    private boolean inSortedOrder(Node node) {
    	if (isEmpty() || node.next == null) {
    		return true;
    	}
    	else if (node.student.compareTo(node.next.student) <= 0) {
    		return inSortedOrder(node.next);
    	}
    	else {
    		return false;
    	}
    }

    /**
     * Returns the index of the iterator
     * from 1 to n. Note that there is
     * no index 0. Does not use recursion.
     * @precondition !offEnd()
     * @return the index of the iterator
     * @throws NullPointerException when
     * the precondition is violated
     */
    public int getIndex() throws NullPointerException{
    	if (offEnd()) {
    		throw new NullPointerException("getIndex(): "
    				+ "The iterator is off end. Cannot access data!");
    	}
    	else {
    		int index = 0;
    		Node temp = iterator;

    		while (iterator != null) {
    			reverseIterator();
    			index ++;
    		}

    		iterator = temp;
    		return (index);
    	}
    }

    /**
     * Uses the iterative linear search
     * algorithm to locate a specific
     * element in the list
     * @param element the value to search for
     * @return the location of value in the
     * List or -1 to indicate not found
     * Note that if the List is empty we will
     * consider the element to be not found
     * @postcondition: position of the iterator remains
     * unchanged!
     */
    public int linearSearch(Student element) {
    	Node N = first;
		for (int x = 1; x <= length; x++) {
			if (N.student.compareTo(element) == 0) {
				return x;
			}
			N = N.next;
		}
        return -1;
    }

    /**
     * Returns the index from 1 to length
     * where value is located in the List
     * by calling the private helper method
     * binarySearch
     * @param value the value to search for
     * @return the index where value is
     * stored from 0 to length - 1, or -1 to
     * indicate not found
     * @precondition isSorted()
     * @postcondition the position of the
     * iterator must remain unchanged!
     * @throws IllegalStateException when the
     * precondition is violated.
     */
    public int binarySearch(Student value) throws IllegalStateException {
    	if (!inSortedOrder()) {
    		throw new IllegalStateException("binarySeaarch(): "
    				+ "List is not in ascending order. Cannot search!");
    	}
    	else {
    		int low = 0;
    		int high = (length-1);
    		return binarySearch(low, high, value);
    	}
    }

    /**
     * Searches for the specified value in
     * the List by implementing the recursive
     * binarySearch algorithm
     * @param low the lowest bounds of the search
     * @param high the highest bounds of the search
     * @param value the value to search for
     * @return the index at which value is located
     * or -1 to indicate not found
     * @postcondition the location of the iterator
     * must remain unchanged
     */
    private int binarySearch(int low, int high, Student value) {
    	if (high < low) {
    		return -1;
    	}

        int mid = low + (high - low) / 2;
        Node N = first;
        for (int x = 1; x < mid; x++) {
        	N = N.next;
        }

        if (N.student.compareTo(value) == 0) {
        	return mid;
        }

        else if (N.student.compareTo(value) > 0) {
        	return binarySearch(low, mid - 1, value);
        }

        else {
        	return binarySearch(mid + 1, high, value);
        }
    }

    /****MUTATORS****/

    /**
     * Creates a new first element
     * @param data the data to insert at the
     * front of the list
     * @postcondition adds a first node into the list if the list is empty,
     * otherwise inserts a new node into the list at the first position
     */
    public void addFirst(Student data) {
    	if (first == null) {
            first = last = new Node(data);
        } else {
            Node N = new Node(data);
            N.next = first;
            first.prev = N;
            first = N;
        }
        length++;
    }

    /**
     * Creates a new last element
     * @param data the data to insert at the
     * end of the list
     * @postcondition adds a first node into the list if the list is empty,
     * otherwise inserts a new node into the list at the last position
     */
    public void addLast(Student data) {
    	if (last == null) {
            first = last = new Node(data);
        } else {
            Node N = new Node(data);
            last.next = N;
            N.prev = last;
            last = N;
        }
        length++;
    }

    /**
    * Removes the element at the front of the list
    * @precondition !isEmpty()
    * @postcondition first node is removed from list
    * @throws NoSuchElementException when precondition is violated
    */
    public void removeFirst() throws NoSuchElementException{
        if (length == 0) {
            throw new NoSuchElementException("removeFirst(): "
            		+ "Cannot remove from an empty List!");
        } else if (length == 1) {
            first = last = null;
        } else {
            first = first.next;
            first.prev = null;
        }
        length--;
    }

    /**
     * Removes the element at the end of the list
     * @precondition !isEmpty()
     * @postcondition last node is removed from list
     * @throws NoSuchElementException when precondition is violated
     */
    public void removeLast() throws NoSuchElementException{
    	if (length == 0) {
    		throw new NoSuchElementException("removeLast(): "
    				+ "Cannot remove from an empty List!");
    	} else if (length == 1) {
            first = last = null;
        } else {
            last = last.prev;
            last.next = null;
        }
        length--;
    }

    /**
     * Moves the iterator to the start of the list
     * @postcondition iterator set to first node of list
     */
    public void pointIterator()
    {
        iterator = first;
    }

    /**
     * Removes the element currently pointed to by the iterator
     * @precondition iterator != null
     * @postcondition iterator == null
     * @throws NullPointerException when the precondition is violated
     */
    public void removeIterator() throws NullPointerException{
            //precondition
            if (offEnd()) {
                throw new NullPointerException("removeIterator(): "
                		+ "iterator is off end. Cannot remove.");
            }
            //edge case #1
            else if (iterator == last) {
                removeLast();
            }
            //edge case #2
            else if (iterator == first) {
                removeFirst();
             }
            //general case
            else {
                iterator.prev.next = iterator.next;
                iterator.next.prev = iterator.prev;
                length--;
            }
            iterator = null;
    }

    /**
     * Inserts an element after the node currently
     * pointed to by the iterator
     * @param data the data to insert
     * @precondition iterator != null
     * @throws NullPointerException when the
     * precondition is violated
     */
    public void addIterator(Student data) {
    	//precondition
    	if (offEnd()) {
    		throw new NullPointerException("addIterator: "
    	+ "iterator is off end. Cannot insert.");
    	}
    	//edge case
    	else if (iterator == last) {
    		addLast(data);
    	}
    	//general case
    	else {
    		Node N = new Node(data);
    		N.next = iterator.next;
    		N.prev = iterator;
    		iterator.next.prev = N;
    		iterator.next = N;
    		length++;
    	}
    }

    /**
     * Advances the iterator by one node towards the last node
     * @precondition !offEnd()
     * @throws NullPointerException when
     * the precondition is violated
     */
    public void advanceIterator() {
    	if (iterator == null) {
    		throw new NullPointerException("advanceIterator(): "
    				+ "iterator is off end. Cannot advance.");
    	}
    	else {
    		iterator = iterator.next;
    	}
    }

    /**
     * Advances the iterator by one node towards the first node
     * @precondition !offEnd()
     * @throws NullPointerException when
     * the precondition is violated
     */
    public void reverseIterator() {
    	if (iterator == null) {
    		throw new NullPointerException("advanceIterator(): "
    				+ "iterator is off end. Cannot advance.");
    	}
    	else {
    		iterator = iterator.prev;
    	}
    }

    /**
     * Places the iterator at first
     * and then iteratively advances
     * it to the specified index
     * no recursion
     * @param index the index where
     * the iterator should be placed
     * @precondition 1 <= index <= length
     * @throws IndexOutOfBoundsException
     * when precondition is violated
     */
    public void advanceToIndex(int index) throws IndexOutOfBoundsException{
    	if (index < 1 || index > length) {
    		throw new IndexOutOfBoundsException("advanceToIndex():"
    				+ " Index is out of bounds. Cannot access data!");
    	}
    	else {
    		pointIterator();
    		for (int x = 1; x < index; x++) {
    			advanceIterator();
    		}
    	}

    }

    /****ADDITIONAL OPERATIONS****/

    /**
     * List with each value on its own line
     * At the end of the List a new line
     * @return the List as a String for display
     */
    @Override public String toString() {
        String result = "";
        Node temp = first;
        while(temp != null) {
            result += temp.student + "\n\n";
            temp = temp.next;
        }
        return result;
    }

    /**
     * Prints the contents of the linked list to the
     * screen in the format #. <element> followed by a newline
     */
    public void printNumberedList() {
        Node temp = first;
        int nodeNum = 1;
        while(temp != null) {
            System.out.println(nodeNum + ". " + temp.student);
            temp = temp.next;
            nodeNum ++;
        }
    }

    /**
     * Prints a linked list to the console
     * in reverse by calling the private
     * recursive helper method printInReverse
     */
    public void printInReverse() {
    	printInReverse(last);
    }

    /**
     * Recursively prints a linked list to the console
     * in reverse order from last to first (no loops)
     * Each element separated by a space
     * Should print a new line after all
     * elements have been displayed
     */

    private void printInReverse(Node node) {
    	if (node == null) {
    		System.out.println();
    	}
    	else {
    		System.out.print(node.student + " ");
    		printInReverse(node.prev);
    	}
    }
}