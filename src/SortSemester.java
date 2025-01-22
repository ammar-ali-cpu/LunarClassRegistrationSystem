import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class SortSemester extends JFrame
{

    static HashMap<String, String> signIns;
    static HashMap<String, Student> database;
    static String username;
    static ArrayList<Course> courseOfferings;

    SortSemester(HashMap<String, String> logIns, HashMap<String, Student> database, String webID, ArrayList<Course> courses)
    {
        signIns = logIns;
        this.database = database;
        username = webID;
        courseOfferings = courses;

        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setSize(500,300);
        this.setTitle("Sorting by Semester");
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeOp();
            }
        });

        Collections.sort(database.get(webID).getCourses(), new SemesterComparator());
        ArrayList<String[]> data1 = new ArrayList<>();
        for(int i = 0; i < database.get(webID).getCourses().size(); i++)
        {
            data1.add(new String[]{database.get(webID).getCourses().get(i).getDepartment() + database.get(webID).getCourses().get(i).getNumber(), database.get(webID).getCourses().get(i).getSemester()});
        }
        String[] columnTitles = {"Course", "Semester"};
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
        StudentsPage stuPage = new StudentsPage(signIns, database, username, courseOfferings);
    }
}
