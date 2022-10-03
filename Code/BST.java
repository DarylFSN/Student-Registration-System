/**
 * BST.java
 * @author Pei Yi Chiang
 * CIS 22C, Final Presentation
 */
import java.util.NoSuchElementException;

public class BST {
	private class Node {
		private Student student;
		private Node left;
		private Node right;

		public Node(Student student) {
			this.student = student;
			left = null;
			right = null;
		}
	}

	private Node root;

	/*** CONSTRUCTORS ***/

	/**
	 * Default constructor for BST sets root to null
	 */
	public BST() {
		root = null;
	}

	/**
	 * Copy constructor for BST
	 *
	 * @param bst the BST to make a copy of
	 */
	public BST(BST bst) {
		root = copyHelper(bst.root);
	}

	/**
	 * Helper method for copy constructor
	 *
	 * @param node the node containing data to copy
	 */
	private Node copyHelper(Node node) {
		if(node == null) {
			return node;
		} else {
			Node n = new Node(null);
			n.student = node.student;
			n.left = copyHelper(node.left);
			n.right = copyHelper(node.right);
			return n;
		}
	}

	/*** ACCESSORS ***/

	/**
	 * Returns the data stored in the root
	 *
	 * @precondition !isEmpty()
	 * @return the data stored in the root
	 * @throws NoSuchElementException when preconditon is violated
	 */
	public Student getRoot() throws NoSuchElementException {
		if(isEmpty()) {
			throw new NoSuchElementException("getRoot(): " +
					"The bst is empty. Cannot access.");
		} else {
			return root.student;
		}
	}

	/**
	 * Determines whether the tree is empty
	 *
	 * @return whether the tree is empty
	 */
	public boolean isEmpty() {
		if(root == null) {
			return true;
		} else{
			return false;
		}
	}

	/**
	 * Returns the current size of the tree (number of nodes)
	 *
	 * @return the size of the tree
	 */
	public int getSize() {
		if(isEmpty()) {
			return 0;
		} else {
			return getSize(root);
		}
	}

	/**
	 * Helper method for the getSize method
	 *
	 * @param node the current node to count
	 * @return the size of the tree
	 */
	private int getSize(Node node) {

		if(node == null) {
			return 0;
		} else {
			return 1 + getSize(node.left) + getSize(node.right);
		}
	}

	/**
	 * Returns the height of tree by counting edges.
	 *
	 * @return the height of the tree
	 */
	public int getHeight() {
		return getHeight(root);
	}

	/**
	 * Helper method for getHeight method
	 *
	 * @param node the current node whose height to count
	 * @return the height of the tree
	 */
	private int getHeight(Node node) {
		if(node == null) {
			return -1;
		} else {
			if(node.left == null && node.right == null) {
				return 0;
			} else if(node.left != null && node.right != null) {
				if(getHeight(node.left) > getHeight(node.right)){
					return 1 + getHeight(node.left);
				} else {
					return 1 + getHeight(node.right);
				}
			} else if(node.left != null && node.right == null) {
				return 1 + getHeight(node.left);
			} else {
				return 1 + getHeight(node.right);
			}
		}
	}

	/**
	 * Returns the smallest value in the tree
	 *
	 * @precondition !isEmpty()
	 * @return the smallest value in the tree
	 * @throws NoSuchElementException when the precondition is violated
	 */
	public Student findMin() throws NoSuchElementException {
		if(isEmpty()) {
			throw new NoSuchElementException("findMin(): " +
					"The bst is empty. Cannot find min");
		} else {
			return findMin(root);
		}
	}

	/**
	 * Helper method to findMin method
	 *
	 * @param node the current node to check if it is the smallest
	 * @return the smallest value in the tree
	 */
	private Student findMin(Node node) {
		if(node.left == null) {
			return node.student;
		} else {
			return findMin(node.left);
		}
	}

	/**
	 * Returns the largest value in the tree
	 *
	 * @precondition !isEmpty()
	 * @return the largest value in the tree
	 * @throws NoSuchElementException when the precondition is violated
	 */
	public Student findMax() throws NoSuchElementException {
		if(isEmpty()) {
			throw new NoSuchElementException("findMax(): " +
					"The bst is empty. Cannot find max");
		} else {
			return findMax(root);
		}
	}

	/**
	 * Helper method to findMax method
	 *
	 * @param node the current node to check if it is the largest
	 * @return the largest value in the tree
	 */
	private Student findMax(Node node) {
		if(node.right == null) {
			return node.student;
		} else {
			return findMax(node.right);
		}
	}

	/**
	 * Searches for a specified value in the tree
	 *
	 * @param student the Student to search for
	 * @return whether the Student is stored in the tree
	 */
	public boolean searchByName(Student student) {
		if(root == null) {
			return false;
		} else {
			return searchByName(student, root);
		}
	}

	/**
	 * Helper method for the search method
	 *
	 * @param student the Student to search for
	 * @param node the current node to check
	 * @return whether the Student is stored in the tree
	 */
	private boolean searchByName(Student student, Node node) {
		if(student.getName().compareTo(node.student.getName()) == 0) {
			return true;
		} else if(student.getName().compareTo(node.student.getName()) < 0) {
			if(node.left == null) {
				return false;
			} else {
				return searchByName(student, node.left);
			}
		} else {
			if(node.right == null) {
				return false;
			} else{
				return searchByName(student, node.right);
			}
		}
	}

	/**
	 * Searches for a specified value in the tree
	 *
	 * @param student the Student to search for
	 * @return whether the Student is stored in the tree
	 */
	public boolean searchByMajor(Student student) {
		if(root == null) {
			return false;
		} else {
			return searchByMajor(student, root);
		}
	}

	/**
	 * Helper method for the search method
	 *
	 * @param student the Student to search for
	 * @param node the current node to check
	 * @return whether the Student is stored in the tree
	 */
	private boolean searchByMajor(Student student, Node node) {
		if(student.getMajor().compareTo(node.student.getMajor()) == 0) {
			return true;
		} else if(student.getMajor().compareTo(node.student.getMajor()) < 0) {
			if(node.left == null) {
				return false;
			} else {
				return searchByMajor(student, node.left);
			}
		} else {
			if(node.right == null) {
				return false;
			} else{
				return searchByMajor(student, node.right);
			}
		}
	}

	/**
	 * Determines whether two trees store identical data in the same structural
	 * position in the tree
	 *
	 * @param o another Object
	 * @return whether the two trees are equal
	 */
	@Override
	public boolean equals(Object o) {
		BST b = (BST) o;
		return equals(root, b.root);
	}

	/**
	 * Helper method for the equals method
	 *
	 * @param node1 the node of the first bst
	 * @param node2 the node of the second bst
	 * @return whether the two trees contain identical data stored in the same
	 *         structural position inside the trees
	 */
	private boolean equals(Node node1, Node node2) {
		if(node1 == node2) {
			return true;
		} else if(node1 == null || node2 == null) { // to prevent NullPointerException
			return false;
		} else if(!node1.student.equals(node2.student)) {
			return false;
		} else {
			return equals(node1.left, node2.left) && equals(node1.right, node2.right);
		}
	}

	/*** MUTATORS ***/

	/**
	 * Inserts a new node in the tree
	 *
	 * @param student the Student to insert
	 */
	public void insertByName(Student student) {
		insertByName(student, root);
	}

	/**
	 * Helper method to insert a new Student in the tree
	 *
	 * @param student the Student to insert
	 * @param node the current node in the search for the correct location in which
	 *             to insert
	 */
	private void insertByName(Student student, Node node) {
		if(node == null) {
			root = new Node(student);
		} else if(student.getName().compareTo(node.student.getName()) < 0) {
			if(node.left == null) {
				node.left = new Node(student);
			} else {
				insertByName(student, node.left);
			}
		} else {
			if(node.right == null) {
				node.right = new Node(student);
			} else {
				insertByName(student, node.right);
			}
		}
	}

	/**
	 * Inserts a new node in the tree
	 *
	 * @param student the student to insert
	 */
	public void insertByMajor(Student student) {
		insertByMajor(student, root);
	}

	/**
	 * Helper method to insert a new Student in the tree
	 *
	 * @param student the Student to insert
	 * @param node the current node in the search for the correct location in which
	 *             to insert
	 */
	private void insertByMajor(Student student, Node node) {
		if(node == null) {
			root = new Node(student);
		} else if(student.getMajor().compareTo(node.student.getMajor()) < 0) {
			if(node.left == null) {
				node.left = new Node(student);
			} else {
				insertByMajor(student, node.left);
			}
		} else {
			if(node.right == null) {
				node.right = new Node(student);
			} else {
				insertByMajor(student, node.right);
			}
		}
	}

	/**
	 * Removes a value from the BST
	 *
	 * @param student the Student to remove
	 * @precondition !isEmpty()
	 * @precondition the Student is located in the tree
	 * @throws NoSuchElementException when the precondition is violated
	 */
	public void remove(Student student) throws NoSuchElementException {
		if(isEmpty()) {
			throw new NoSuchElementException("remove(): " +
					"The bst is empty. Cannot remove.");
		} else if(!searchByName(student) && !searchByMajor(student)) {
			throw new NoSuchElementException("remove(): " +
					"The data is not located in the tree. Cannot remove.");
		} else {
			root = remove(student, root);
		}
	}

	/**
	 * Helper method to the remove method
	 *
	 * @param student the Student to remove
	 * @param node the current node
	 * @return an updated reference variable
	 */
	private Node remove(Student student, Node node) {
		if(node == null) {
			return node;
		} else if(student.compareTo(node.student) < 0) {
			node.left = remove(student, node.left); //to have the parent of (the node you want to remove)
												 //point to null
		} else if(student.compareTo(node.student) > 0) {
			node.right = remove(student, node.right);
		} else {
			if(node.left == null && node.right == null) {
				node = null;
			} else if(node.left != null && node.right == null) {
				node = node.left;
			} else if(node.left == null && node.right != null) {
				node = node.right;
			} else {
				node.student = findMin(node.right);
				node.right = remove(node.student, node.right);
			}
		}
		return node;
	}

	/**
	 * Removes a value from the BST
	 *
	 * @param student the Student to remove
	 * @precondition !isEmpty()
	 * @precondition the Student is located in the tree
	 * @throws NoSuchElementException when the precondition is violated
	 */
	public void removeByName(Student student) throws NoSuchElementException {
		if(isEmpty()) {
			throw new NoSuchElementException("removeByName(): " +
					"The bst is empty. Cannot remove.");
		} else if(!searchByName(student) && !searchByMajor(student)) {
			throw new NoSuchElementException("removeByName(): " +
					"The data is not located in the tree. Cannot remove.");
		} else {
			root = removeByName(student, root);
		}
	}

	/**
	 * Helper method to the remove method
	 *
	 * @param student the Student to remove
	 * @param node the current node
	 * @return an updated reference variable
	 */
	private Node removeByName(Student student, Node node) {
		if(node == null) {
			return node;
		} else if(student.getName().compareTo(node.student.getName()) < 0) {
			node.left = removeByName(student, node.left); //to have the parent of (the node you want to remove)
												 //point to null
		} else if(student.getName().compareTo(node.student.getName()) > 0) {
			node.right = removeByName(student, node.right);
		} else {
			if(node.left == null && node.right == null) {
				node = null;
			} else if(node.left != null && node.right == null) {
				node = node.left;
			} else if(node.left == null && node.right != null) {
				node = node.right;
			} else {
				node.student = findMin(node.right);
				node.right = removeByName(node.student, node.right);
			}
		}
		return node;
	}

	/**
	 * Removes a value from the BST
	 *
	 * @param student the Student to remove
	 * @precondition !isEmpty()
	 * @precondition the Student is located in the tree
	 * @throws NoSuchElementException when the precondition is violated
	 */
	public void removeByMajor(Student student) throws NoSuchElementException {
		if(isEmpty()) {
			throw new NoSuchElementException("removeByMajor(): " +
					"The bst is empty. Cannot remove.");
		} else if(!searchByName(student) && !searchByMajor(student)) {
			throw new NoSuchElementException("removeByMajor(): " +
					"The data is not located in the tree. Cannot remove.");
		} else {
			root = removeByMajor(student, root);
		}
	}

	/**
	 * Helper method to the remove method
	 *
	 * @param student the Student to remove
	 * @param node the current node
	 * @return an updated reference variable
	 */
	private Node removeByMajor(Student student, Node node) {
		if(node == null) {
			return node;
		} else if(student.getMajor().compareTo(node.student.getMajor()) < 0) {
			node.left = removeByMajor(student, node.left); //to have the parent of (the node you want to remove)
												 //point to null
		} else if(student.getMajor().compareTo(node.student.getMajor()) > 0) {
			node.right = removeByMajor(student, node.right);
		} else {
			if(node.left == null && node.right == null) {
				node = null;
			} else if(node.left != null && node.right == null) {
				node = node.left;
			} else if(node.left == null && node.right != null) {
				node = node.right;
			} else {
				node.student = findMin(node.right);
				node.right = removeByMajor(node.student, node.right);
			}
		}
		return node;
	}
	/*** ADDITIONAL OPERATIONS ***/

	/**
	 * Prints the data in pre order to the console
	 */
	public void preOrderPrint() {
		preOrderPrint(root);
		System.out.println();
	}

	/**
	 * Helper method to preOrderPrint method Prints the data in pre order to the
	 * console
	 */
	private void preOrderPrint(Node node) {
		if (node == null) {
			System.out.println("\n");
			return;
		} else {
			System.out.print(node.student + " ");
			preOrderPrint(node.left);
			preOrderPrint(node.right);
		}
	}

	/**
	 * Prints the data in sorted order to the console
	 */
	public void inOrderPrint() {
		inOrderPrint(root);
		System.out.println();
	}

	/**
	 * Helper method to inOrderPrint method Prints the data in sorted order to the
	 * console
	 */
	private void inOrderPrint(Node node) {
		if (node == null) {
			System.out.println("\n");
			return;
		} else {
			inOrderPrint(node.left);
			System.out.print(node.student + " ");
			inOrderPrint(node.right);
		}
	}

	/**
	 * Prints the data in post order to the console
	 */
	public void postOrderPrint() {
		postOrderPrint(root);
		System.out.println();
	}

	/**
	 * Helper method to postOrderPrint method Prints the data in post order to the
	 * console
	 */
	private void postOrderPrint(Node node) {
		if (node == null) {
			System.out.println("\n");
			return;
		} else {
			postOrderPrint(node.left);
			postOrderPrint(node.right);
			System.out.print(node.student + " ");
		}
	}
}
