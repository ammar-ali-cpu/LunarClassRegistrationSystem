/**
 * This is the CourseNameComparator class, used to compare courses by their name for sorting purposes.
 *
 * @author Ammar Ali
 */

/**
 * Imported Comparator to implement it into this class, and Serializable to save data.
 */
import java.util.Comparator;
import java.io.Serializable;

public class CourseNameComparator implements Comparator, Serializable
{

    /**
     * This is the compare method, which overrides the Comparator class' compare method, comparing two Courses by name.
     *
     * @param o1
     * the first object to be compared.
     *
     * @param o2
     * the second object to be compared.
     *
     * @return
     * -1 if the first Course's department name is alphabetically before the second Course's name, or if it has the lower course number if they are the same department
     * 1 if the first Course's department name is alphabetically after the second Course's name, or if it has the higher course number if they are the same department
     * 0 if they are the same course
     */
    @Override
    public int compare(Object o1, Object o2)
    {
        Course left = (Course)o1;
        Course right = (Course)o2;

        int nameComp = left.getDepartment().compareTo(right.getDepartment());
        if(nameComp != 0)
        {
            return nameComp;
        }
        if(left.getNumber() < right.getNumber())
        {
            return -1;
        }
        if(left.getNumber() > right.getNumber())
        {
            return 1;
        }
        return 0;
    }
}

