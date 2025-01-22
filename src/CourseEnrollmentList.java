import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CourseEnrollmentList extends JFrame
{

    static HashMap<String, String> signIns;
    static HashMap<String, Student> database;
    static ArrayList<Course> courseOfferings;

    static String coDep;
    static int coNum;
    CourseEnrollmentList(HashMap<String, String> logIns, HashMap<String, Student> database, String courseDep, int courseNum, ArrayList<Course> courses)
    {
        signIns = logIns;
        this.database = database;
        coDep = courseDep;
        coNum = courseNum;
        courseOfferings = courses;

        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setSize(500,300);
        this.setTitle("Enrollment for " + coDep + " " + coNum);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeOp();
            }
        });

        ArrayList<String[]> data1 = new ArrayList<>();
        for(Map.Entry<String, Student> entry: database.entrySet())
        {
            try{
            for(int i = 0; i < entry.getValue().getCourses().size();i++)
            {
                if (entry.getValue().getCourses().get(i).getDepartment().equalsIgnoreCase(courseDep) && entry.getValue().getCourses().get(i).getNumber() == courseNum)
                {
                    //System.out.println(thisStudent.getWebID() + "     " + thisStudent.getCourses().get(i).getSemester());
                    data1.add(new String[]{entry.getValue().getWebID(), entry.getValue().getCourses().get(i).getSemester()});
                }
            }
            }
            catch(ClassCastException exception)
            {
                break;
            }
        }

        String[] columnTitles = {"Student", "Semester"};
        Object[][] data = (Object[][]) data1.toArray(new String[][] {});

        JTable table = new JTable(data, columnTitles);
        table.getTableHeader().setBounds(50, 0 , 400, 50);
        table.setBounds(50, 50, 400, 200);


        this.add(table.getTableHeader());
        this.add(table);

        this.setVisible(true);
    }

    public void closeOp()
    {
        this.dispose();
        ViewCourseEnroll viewCourseEnroll = new ViewCourseEnroll(signIns, database, courseOfferings);
    }
}
