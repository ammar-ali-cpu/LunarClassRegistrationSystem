/**
 * This is the SemesterComparator class, used to compare courses by their semester for sorting purposes.
 *
 * @author Ammar Ali
 */

/**
 * Imported Comparator to implement it into this class, and Serializable to save data.
 */
import java.util.Comparator;
import java.io.Serializable;

public class SemesterComparator implements Comparator, Serializable
{
    /**
     * This is the compare method, which overrides the Comparator class' compare method, comparing two Courses by Semester.
     *
     * @param o1
     * the first object to be compared.
     *
     * @param o2
     * the second object to be compared.
     *
     * @return
     * -1 if the first Course's semester is chronologically before the second Course's
     * 0 if they are in the same semester
     * 1 if the first Course's semester is chronologically after the second Course's
     */
    @Override
    public int compare(Object o1, Object o2)
    {
        Course left = (Course)o1;
        Course right = (Course)o2;

        if(left.getSemester().substring(1).equalsIgnoreCase(right.getSemester().substring(1)))
        {
            if(right.getSemester().substring(0,1).equalsIgnoreCase("S") && !(left.getSemester().substring(0,1).equalsIgnoreCase("S")) )
            {
                return 1;
            }
            if(left.getSemester().substring(0,1).equalsIgnoreCase("S") && !(right.getSemester().substring(0,1).equalsIgnoreCase("S")))
            {
                return -1;
            }
            return 0;
        }
        return left.getSemester().substring(1).compareTo(right.getSemester().substring(1));
    }
}
