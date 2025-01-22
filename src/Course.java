/**
 * Ammar Ali
 *
 * This is the Course class, representing a class a Student can take in the university.
 *
 * @author Ammar Ali
 */

/**
 * Imported Serializable to save data
 */
import java.io.Serializable;

public class Course implements Serializable
{
    /**
     * These are the instance variables used throughout this class
     *
     * department is a String representation of the three letter course department code (ex. CSE for a Computer Science course)
     *
     * number is an int representation of the number of the course (ex. 214 for this course, CSE 214)
     *
     * semester is a String representation of the semester the course will be offered in (ex. F2023 for Fall 2023)
     */
    private String department;
    private int number;
    private String semester;

    /**
     * This is a default constructor for this class.
     */
    public Course()
    {}

    /**
     * This is another constructor for this class with parameters.
     *
     * @param d
     * the String parameter to be set as department
     *
     * @param n
     * the int parameter to be set as number
     *
     * @param s
     * the String parameter to be set as semester
     */
    public Course(String d, int n, String s)
    {
        department = d;
        number = n;
        semester = s;
    }

    /**
     * This is the getter method for the department variable.
     *
     * @return department
     * a String representation of the three letter course department code (ex. CSE for a Computer Science course)
     */
    public String getDepartment()
    {
        return department;
    }

    /**
     * This is the getter method for the number variable.
     *
     * @return number
     * an int representation of the number of the course (ex. 214 for this course, CSE 214)
     */
    public int getNumber()
    {
        return number;
    }

    /**
     * This is the getter method for the semester variable.
     *
     * @return semester
     * a String representation of the semester the course will be offered in (ex. F2023 for Fall 2023)
     */
    public String getSemester()
    {
        return semester;
    }

    /**
     * This is the setter method for the department variable.
     *
     * @param d
     * the new String to replace department
     */
    public void setDepartment(String d)
    {
        department = d;
    }

    /**
     * This is the setter method for the number variable.
     *
     * @param number
     * the new int to replace number
     */
    public void setNumber(int number)
    {
        this.number = number;
    }

    /**
     * This is the setter method for the semester variable.
     *
     * @param semester
     * the new String to replace semester
     *
     * @throws InvalidSemesterFormatException
     * if the semester enter is not in the correct format (correct example: F2023)
     */
    public void setSemester(String semester) throws InvalidSemesterFormatException {
        if(semester.length() != 5)
        {
            throw new InvalidSemesterFormatException();
        }
        this.semester = semester;
    }



}