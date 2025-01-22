/**
 * Ammar Ali
 *
 * This is the Student class, representing a Student object in the Lunar System.
 * Class implements Serializable.
 *
 * @author Ammar Ali
 */

/**
 * Imported Serializable and ArrayList files to be used throughout the class
 */
import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable
{
    /**
     * Here are the instance variables used in this class.
     *
     * webID is a String representation of a Student's online webID used to log in
     *
     * courses is an ArrayList of courses the Student is registered for
     */
    private String webID;
    private ArrayList<Course> courses = new ArrayList<>();

    /**
     * This is a default constructor for the Student class
     */
    public Student()
    {
    }

    /**
     * This is another constructor for the Student class with parameters.
     *
     * @param webID
     * the String webID to be set as this Student Object's webID
     */
    public Student(String webID)
    {
        this.webID = webID;
    }

    /**
     * This is a getter method for the webID variable
     *
     * @return webID
     * The Students online log in ID
     */
    public String getWebID()
    {
        return webID;
    }

    /**
     * This is a getter method for the courses variable
     *
     * @return courses
     * The courses the Student is registered in
     */
    public ArrayList<Course> getCourses()
    {
        return courses;
    }

    /**
     * This is a setter method for the webID variable.
     *
     * @param webID
     * the new String webID to be set as this Student Object's webID
     */
    public void setWebID(String webID)
    {
        this.webID = webID;
    }

    /**
     * This is a setter method for the courses variable.
     *
     * @param courses
     * the new ArrayList of Course objects to be set as this Student object's courses ArrayList
     */
    public void setCourses(ArrayList<Course> courses)
    {
        this.courses = courses;
    }
}