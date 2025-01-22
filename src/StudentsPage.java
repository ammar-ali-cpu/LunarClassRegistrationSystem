import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class StudentsPage implements ActionListener
{
    private JFrame frame = new JFrame();
    private JButton addAClassButton;
    private JButton dropAClassButton;
    private JButton viewClassesSortedByButton;
    private JButton viewClassesSortedByButton1;
    private JButton logoutButton;
    private JPanel StudentsPanel;

    static HashMap<String, String> signIns;
    static HashMap<String, Student> database;
    static String username;
    static ArrayList<Course> courseOfferings;


    public StudentsPage(HashMap<String, String> logIns, HashMap<String, Student> database, String webID, ArrayList<Course> courses)
    {
        //Basic panel setup
        frame.setContentPane(StudentsPanel);
        frame.setTitle("Lunar System (Student View)");
        //frame.setDefaultCloseOperation(EXIT_ON_CLOSE);     //switch to opening up a GUIMAIN  sign in page instead
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setSize(500,300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        signIns = logIns;
        this.database = database;
        username = webID;
        courseOfferings = courses;

        //ActionListeners required for ActionPerformed method
        addAClassButton.addActionListener(this);
        dropAClassButton.addActionListener(this);
        viewClassesSortedByButton.addActionListener(this);
        viewClassesSortedByButton1.addActionListener(this);
        logoutButton.addActionListener(this);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeOp();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == addAClassButton)
        {
            frame.dispose();
            ListOfCoursesAvailable listy = new ListOfCoursesAvailable(courseOfferings);
            AddClassPage addClass = new AddClassPage(signIns, database, username, courseOfferings);
        }
        if(e.getSource() == dropAClassButton)
        {
            frame.dispose();
            DropClassPage dropClass = new DropClassPage(signIns, database, username, courseOfferings);
        }
        if(e.getSource() == viewClassesSortedByButton)
        {
            frame.dispose();
            SortCourse2 sorty = new SortCourse2(signIns,database, username, courseOfferings);
        }
        if(e.getSource() == viewClassesSortedByButton1)
        {
            frame.dispose();
            SortSemester sortSemester = new SortSemester(signIns,database, username, courseOfferings);
        }
        if(e.getSource() == logoutButton)
        {
            //maybe a save is required here
            frame.dispose();
            GUIMain signInPage = new GUIMain(signIns, database, courseOfferings);
        }
    }

    public void closeOp()
    {
        frame.dispose();
        GUIMain signInPage = new GUIMain(signIns, database, courseOfferings);
    }
}
