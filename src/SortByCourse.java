import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class SortByCourse implements ActionListener
{

    private JFrame frame = new JFrame();
    private JTable table1;
    private JPanel sortByCoursePane;
    private JButton backButton;


    static HashMap<String, String> signIns;
    static HashMap<String, Student> database;
    static String username;
    static ArrayList<Course> courseOfferings;

    public SortByCourse(HashMap<String, String> logIns, HashMap<String, Student> database, String webID, ArrayList<Course> courses)
    {
        //Basic panel setup
        frame.setContentPane(sortByCoursePane);
        frame.setTitle("Courses Sorted by Name");
        //frame.setDefaultCloseOperation(EXIT_ON_CLOSE);     //switch to opening up a GUIMAIN  sign in page instead
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setSize(500,300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        signIns = logIns;
        this.database = database;
        username = webID;
        courseOfferings = courses;

        //action Listeners
        backButton.addActionListener(this);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeOp();
            }
        });

        Collections.sort(database.get(webID).getCourses(), new CourseNameComparator());
        ArrayList<String[]> data1 = new ArrayList<>();
        for(int i = 0; i < database.get(webID).getCourses().size(); i++)
        {
            data1.add(new String[]{database.get(webID).getCourses().get(i).getDepartment() + database.get(webID).getCourses().get(i).getNumber(), database.get(webID).getCourses().get(i).getSemester()});
        }
        String[] columnTitles = {"Course", "Semester"};
        Object[][] data = (Object[][]) data1.toArray(new String[][] {});

        //JTable table = new JTable(data, columnTitles);
        //table.setBounds(50, 50, 300, 200);
        //table.getTableHeader().setBounds(50, 0 , 300, 200);

        //frame.add(table.getTableHeader());
        //frame.add(table);

        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(25, 25));
        for (int i = 0; i < data.length; i++)
        {
            for (int n = 0; n < data[i].length; n++)
            {
                grid.add(new JLabel(String.valueOf(data[i][n])));
            }
        }
        frame.add(grid);
        for(int i = 0; i < data.length; i++)
        {
            for(int j =0; j<data[i].length; j++) {
                System.out.print(data[i][j]);
            }
            System.out.println();
        }


    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == backButton)
        {
            frame.dispose();
            StudentsPage stuPage = new StudentsPage(signIns, database, username, courseOfferings);
        }
    }

    public void closeOp()
    {
        frame.dispose();
        GUIMain signInPage = new GUIMain(signIns, database, courseOfferings);
    }
}
