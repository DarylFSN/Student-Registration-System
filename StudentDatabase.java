/**
 * StudentDatabase.java
 * @author Konjac Huang
 * @author Pei Yi Chiang
 * @author Valen Chang
 * @author Daryl Foo
 * CIS 22C, Final Presentation
 */
import java.io.*;
import java.util.*;

public class StudentDatabase {
	static Scanner scanner = new Scanner(System.in);
	static int numOfStudents = 50;
	private static ArrayList<Student> studentList = new ArrayList<>();
	private static Hash<Student> database = new Hash<Student>(numOfStudents);
	private static BST BSTName = new BST();
	private static BST BSTMajor = new BST();

	public static void add() {
		String name;
		String major;
		String studentID;
		String dateOfBirth;
		String countryOfOrigin;

		System.out.println("\nAdding a New Student!\n");
		System.out.print("Enter the name: ");
		name = scanner.nextLine();
		System.out.print("Enter the major: ");
		major = scanner.nextLine();
		System.out.print("Enter the student ID number: ");
		studentID = scanner.nextLine();
		System.out.print("Enter the student Date Of Birth(MM/DD/YYYY): ");
		dateOfBirth = scanner.nextLine();
		System.out.print("Enter the country Of Origin: ");
		countryOfOrigin = scanner.nextLine();

		Student newStudent = new Student(name, major, studentID, dateOfBirth, countryOfOrigin);
		database.insert(newStudent);
		BSTName.insertByName(newStudent);
		BSTMajor.insertByMajor(newStudent);
		studentList.add(newStudent);

		System.out.println("\n" + name + " was added!\n");
	}

	public static void display() {
		String displayChoice;
		System.out.print("\nPlease choose one of the following options:\n"
				+ "H. Display unsorted data (hash table)\n"
				+ "P. Display data sorted by the primary key (name)\n"
				+ "S. Display data sorted by the secondary key (major)\n\n"
				+ "Enter your choice: ");

		displayChoice = scanner.nextLine();
		displayChoice = displayChoice.toUpperCase();

		boolean continueInput = false;
		do {
			if(displayChoice.equals("H")) {
				continueInput = false;
				System.out.println("\nDisplaying unsorted Student Database using Hash Table:\n");
				for(int i = 0; i < numOfStudents; i++) {
					database.printBucket(i);
					System.out.println();
				}
				System.out.println("End of database!\n");
			} else if(displayChoice.equals("P")) {
				continueInput = false;
				System.out.println("\nDisplaying Student Database sorted by primary key (name):");
				BSTName.inOrderPrint();
				System.out.println("End of database!\n");
			} else if(displayChoice.equals("S")) {
				continueInput = false;
				System.out.println("\nDisplaying Student Database sorted by secondary key (major):");
				BSTMajor.inOrderPrint();
				System.out.println("End of database!\n");
			} else {
				System.out.println("\nInvalid input!");
				continueInput = true;
				System.out.print("\nPlease choose one of the following options:\n"
						+ "H. Display unsorted data (hash table)\n"
						+ "P. Display data sorted by the primary key (name)\n"
						+ "S. Display data sorted by the secondary key (major)\n\n"
						+ "Enter your choice: ");

				displayChoice = scanner.nextLine();
				displayChoice = displayChoice.toUpperCase();
			}
		} while(continueInput);
	}

	public static void remove() {
		String name;
		String major;
		System.out.println("\nRemoving a Student!\n");
		System.out.print("Enter the name of the student: ");
		name = scanner.nextLine();
		System.out.print("Enter the major of the student: ");
		major = scanner.nextLine();
		Student tempStudent = new Student(name, major, "", "", "");

		if (database.search(tempStudent)) {
			database.remove(tempStudent);
			BSTName.removeByName(tempStudent);
			BSTMajor.removeByMajor(tempStudent);
			studentList.remove(tempStudent);
			System.out.println("\n" + name + " was removed!\n");
		}
		else {
			System.out.println("\n" + name + " is not in the database.\n");
		}
	}

	public static void search() {
		String name = null;
		String major = null;
		System.out.println("\nSearching for a student!");
		String searchChoice;
		System.out.print("\nPlease select one of the following options:\n"
				+ "N. Search and display the matching student by name\n"
				+ "M. Search and display the matching student by major\n\n"
				+ "Enter your choice: ");
		searchChoice = scanner.nextLine();
		searchChoice = searchChoice.toUpperCase();

		boolean continueInput = false;
		do {
			if(searchChoice.equals("N")) {
				continueInput = false;
				System.out.print("\nEnter the name of the student: ");
				name = scanner.nextLine();
				name = name.replaceAll("\n", "");
				Student tempStudent = new Student(name, "", "", "", "");
				if(!BSTName.searchByName(tempStudent)) {
					System.out.println("\n" + name + " is not in the database!\n");
				} else {
					System.out.println("\nDisplaying Student with name: " + name + "\n");
					for(int i = 0; i < studentList.size(); i++) {
						if(tempStudent.getName().compareTo(studentList.get(i).getName()) == 0) {
							System.out.println(studentList.get(i));
							System.out.println();
						}
					}
				}
			} else if(searchChoice.equals("M")) {
				continueInput = false;
				System.out.print("\nEnter the Major of Student: ");
				major = scanner.nextLine();
				major = major.replaceAll("\n", "");
				Student tempStudent = new Student("", major, "", "", "");
				if(!BSTMajor.searchByMajor(tempStudent)) {
					System.out.println("\n" + major + " is not in the database!\n");
				} else {
					System.out.println("\nDisplaying Students in major: " + major + "\n");
					for(int i = 0; i < studentList.size(); i++) {
						if(tempStudent.getMajor().compareTo(studentList.get(i).getMajor()) == 0) {
							System.out.println(studentList.get(i));
							System.out.println();
						}
					}
				}
			} else {
				System.out.println("\nInvalid input!");
				continueInput = true;
				System.out.print("\nPlease select one of the following options:\n"
						+ "N. Search and display the matching student by name\n"
						+ "M. Search and display the matching student by major\n\n"
						+ "Enter your choice: ");
				searchChoice = scanner.nextLine();
				searchChoice = searchChoice.toUpperCase();
			}
		} while(continueInput);
	}

	public static void printMenu() {
		System.out.println("Please select from one of the following options:\n");
		System.out.println("A. Add a Student\n"
						+ "D. Display all Students\n"
						+ "R. Remove a Student\n"
						+ "S. Search for a Student\n"
						+ "W. Write the data to the file\n"
						+ "X. Exit\n");
		System.out.print("Enter your choice: ");
	}

	public static void writeFile(ArrayList<Student> studentList) {
		String fileName = "studentDatabase.txt";

		PrintWriter outFile = null;
		try {
			outFile = new PrintWriter(fileName);
		} catch (FileNotFoundException e) {
			System.out.println("Exception: File not found!");
		}

		for(int i = 0; i < studentList.size(); i++) {
			outFile.println(studentList.get(i));
			outFile.println();
		}
		outFile.close();

		System.out.println("\nUpdated Student database is saved to \'studentDatabase.txt\'\n");
	}

	public static void readFile() {
		BufferedReader reader = null;

		String tempString = null;
		String name = null;
		String major = null;
		String studentID = null;
		String dateOfBirth = null;
		String countryOfOrigin =null;

		boolean continueInput = true;
		do {
			try {
				System.out.print("\nEnter the name of the file: ");
				String fileName = scanner.nextLine();

				File file = new File(fileName);

				reader = new BufferedReader(new FileReader(file));
				continueInput = false;

				int line = 1;

				while ((tempString = reader.readLine()) != null) {
					switch (line % 6) {
					case 1:
						name = tempString;
						break;
					case 2:
						major = tempString;
						break;
					case 3:
						studentID = tempString;
						break;
					case 4:
						dateOfBirth = tempString;
						break;
					case 5:
						countryOfOrigin = tempString;
						break;
					case 0:
						Student newStudent = new Student(name, major, studentID, dateOfBirth, countryOfOrigin);
						database.insert(newStudent);
						BSTName.insertByName(newStudent);
						BSTMajor.insertByMajor(newStudent);
						studentList.add(newStudent);
						break;
					}

					line++;
				}
				Student newStudent = new Student(name, major, studentID, dateOfBirth, countryOfOrigin);
				database.insert(newStudent);
				BSTName.insertByName(newStudent);
				BSTMajor.insertByMajor(newStudent);
				studentList.add(newStudent);
				reader.close();
			} catch (IOException e) {
				e.toString();
				System.out.println("File does not exist!");
			} finally {
				if (reader != null ) {
					try {
						reader.close();
					} catch (IOException e1) {
						e1.toString();
					}
				}
			}
		} while(continueInput);
	}

	public static void main(String args[]) {
		String choice;

		System.out.println("Welcome to the Students Database!");

		readFile();

		printMenu();

		choice = scanner.nextLine();
		choice = choice.toUpperCase();


		while (!choice.equalsIgnoreCase("X")) {
			switch(choice) {
			case "A":
				add();
				break;
			case "D":
				display();
				break;
			case "R":
				remove();
				break;
			case "S":
				search();
				break;
			case "W":
				writeFile(studentList);
				break;
			default:
				System.out.println("\nInvalid input!\n");
				break;
			}
			printMenu();

			choice = scanner.nextLine();
			choice = choice.toUpperCase();
		}
		System.out.println("\nThank you for using the Student Database!");
	}

}