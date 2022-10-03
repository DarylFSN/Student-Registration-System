/**
 * Student.java
 * @author Pei Yi Chiang
 * CIS 22C, Final Presentation
 */
public class Student implements Comparable<Student>{
    private String name;
    private String major;
    private String studentID;
    private String dateOfBirth;
    private String countryOfOrigin;
    
    /**
     * Constructor for the Student class
     * @param name the Student's name
     * @param major the Student's major
     * @param studentID the ID of the Student
     * @param dateOfBirth the date the Student was born on
     * @param countryOfOrigin the country the Student is from
     */
    public Student(String name, String major, String studentID,
    	String dateOfBirth, String countryOfOrigin) {
        this.name = name;
        this.major = major;
        this.studentID = studentID;
        this.dateOfBirth = dateOfBirth;
        this.countryOfOrigin = countryOfOrigin;
    }
    
    /**
     * Accesses the name of the Student
     * @return the Student's name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Accesses the major of the Student
     * @return the Student's major
     */
    public String getMajor() {
        return major;
    }
    
    /**
     * Access the ID of the Student
     * @return the Student's ID
     */
    public String getID() {
        return studentID;
    }
    
    /**
     * Access the date the Student was born
     * @return the Student's date of birth
     */
    public String getDate() {
        return dateOfBirth;
    }
    
    /**
     * Access the country the Student is from
     * @return the Student's country of origin
     */
    public String getCountry() {
        return countryOfOrigin;
    }
    
    /**
     * Sets the name of the Student
     * @param name the Student's name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Sets the major of the Student
     * @param major the Student's major
     */
    public void setMajor(String major) {
        this.major = major;
    }
    
    /**
     * Sets the ID of the Student
     * @param studentID the Student's ID
     */
    public void setID(String studentID) {
        this.studentID = studentID;
    }
    
    /**
     * Sets the date the Student was born on
     * @param dateOfBirth date the Student was born on
     */
    public void setDate(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    /**
     * Sets the country the Student is from 
     * @param countryOfOrigin the Student's country of origin
     */
    public void setCountry(String countryOfOrigin) {
    	this.countryOfOrigin = countryOfOrigin;
    }
    
    /**
     * Formats the Student information for display, using
     * the following format:
     * Name: <name>
     * Major: <major>
     * Student ID: <studentID>
     * Date of Birth: <dateOfBirth>
     * Country of Origin: <countryOfOrigin>
     * Note that there should be no <>
     * in the resulting String
     */
    @Override public String toString() {
        String result = "Name: " + name
                + "\nMajor: " + major
                + "\nStudent ID: " + studentID
                + "\nDate of Birth: " + dateOfBirth
        		+ "\nCountry of Origin: " + countryOfOrigin;
        return result;
    }
    
    /**
     * Determines whether two Student objects are 
     * equal by comparing names and majors
     * @param otherStudent the second Student object
     * @return whether the Students are equal
     */
    @Override public boolean equals(Object o) {
        if(this == o) {
        	return true;
        } else if(!(o instanceof Student)) {
        	return false;
        } else {
        	Student s = (Student) o;
            if(this.name.equals(s.name) && this.major.equals(s.major)) {
            	return true;
            } else {
            	return false;
            }
        }
    }
    
    /**
     * Compares two Student objects to determine ordering
     * Returns 0 if the two items are equal
     * Return -1 if this student's name comes alphabetically
     * before the other student's name.
     * Returns 1 if the other student's name comes
     * alphabetically before this student's name
     * If the two students' names are the same, will
     * differentiate by majors (alphabetical
     * comparison)
     * @param the other Student object to compare to this
     * @return 0 (same student), -1 (this student ordered first)
     * or 1 (the other student ordered first) 
     */
    public int compareTo(Student otherStudent) {
    	if(this.equals(otherStudent)) {
    		return 0;
    	} else if(!this.name.equals(otherStudent.name)) {
    		return this.name.compareTo(otherStudent.name);
    	} else {
    		return this.major.compareTo(otherStudent.major);
    	}
    }
    
    /**
     * Returns a consistent hash code for 
     * each Student by summing the Unicode values
     * of each character in the key
     * Key = name + major
     * @return the hash code
     */
    @Override public int hashCode() {
        String key = name + major;
        int sum = 0;
        
        for(int i = 0; i < key.length(); i++) {
        	sum += (int)key.charAt(i);
        }
        
        return sum;
    }

}
