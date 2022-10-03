/**
 * Hash.java
 * @author Kai Li Tan
 * CIS 22C, Final Presentation
 */
import java.util.ArrayList;

public class Hash<Student extends Comparable<Student>> {

	private int numElements;
	private ArrayList<List<Student> > Table;

	/**
	* Constructor for the Hash.java
	* class. Initializes the Table to
	* be sized according to value passed
	* in as a parameter
	* Inserts size empty Lists into the
	* table. Sets numElements to 0
	* @param size the table size
	*/
	public Hash(int size) {
		Table = new ArrayList<List<Student>>(size);
		for (int i = 0; i < size; i++) {
			List<Student> list = new List<Student>();
			Table.add(i, list);
		}
		numElements = 0;
	}

	/**Accessors*/

	/**
	* Returns the hash value in the Table
	* for a given key by taking modulus
	* of the hashCode value for that key
	* and the size of the table
	* @param t the key
	* @return the index in the Table
	*/
	private int hash(Student student) {
	    int code = student.hashCode();
	    return code % Table.size();
	}

	/**
	* Counts the number of keys at this index
	* @param index the index in the Table
	* @precondition 0 <= index < Table.length
	* @return the count of keys at this index
	* @throws IndexOutOfBoundsException
	*/
	public int countBucket(int bucket) throws IndexOutOfBoundsException{
		if (bucket < 0 || bucket >= Table.size()) {
			throw new IndexOutOfBoundsException("countBucket(): "
					+ "Index out of bounds. Cannot count bucket.");
		}
		else {
			return Table.get(bucket).getLength();
		}
	}

	/**
	* Returns total number of keys in the Table
	* @return total number of keys
	*/
	public int getNumElements() {
	    return numElements;
	}

	/**
	* Searches for a specified key in the Table
	* @param t the key to search for
	* @return whether the key is in the Table
	* Big-O runtime: O(1)
	*/
	public boolean search(Student student) {
		int bucket = hash(student);
		if (Table.get(bucket).linearSearch(student) == -1) {
			return false;
		}
		else {
			return true;
		}
	}


	/**Manipulation Procedures*/

	/**
	* Inserts a new key in the Table
	* calls the hash method to determine placement
	* @param t the key to insert
	* Big-O runtime: O(1)
	*/
	public void insert(Student student) {
		int bucket = hash(student);
		Table.get(bucket).addLast(student);
		numElements++;
	}

	/**
	* removes the key t from the Table
	* calls the hash method on the key to
	* determine correct placement
	* has no effect if t is not in
	* the Table
	* @param t the key to remove
	* Big-O runtime: O(1)
	*/
	public void remove(Student student) {
		int bucket = hash(student);
		if (Table.get(bucket).linearSearch(student) != -1) {
			Table.get(bucket).advanceToIndex(Table.get(bucket).linearSearch(student));
			Table.get(bucket).removeIterator();
			numElements--;
		}
	}


	/**Additional Methods*/

	/**
	* Prints all the keys at a specified
	* bucket in the Table. Each key displayed
	* on its own line, with a blank line
	* separating each key
	* Above the keys, prints the message
	* "Printing bucket #<bucket>:"
	* Note that there is no <> in the output
	* @param bucket the index in the Table
	*/
	public void printBucket(int bucket) {
		System.out.println("Printing bucket #" + bucket + ":\n");
		System.out.print(Table.get(bucket));
	}

	/**
	* Prints the first key at each bucket
	* along with a count of the total keys
	* with the message "+ <count> -1 more
	* at this bucket." Each bucket separated
	* with to blank lines. When the bucket is
	* empty, prints the message "This bucket
	* is empty." followed by two blank lines
	*/
	public void printTable(){
		for (int i = 0; i < Table.size(); i++) {
			System.out.println("Bucket: " + i);
			if (Table.get(i).isEmpty()) {
				System.out.println("This bucket is empty.\n");
			}
			else {
				System.out.println(Table.get(i).getFirst());
				System.out.println("+ " + (countBucket(i) - 1) + " more at this bucket\n");
			}
		}
	}
}