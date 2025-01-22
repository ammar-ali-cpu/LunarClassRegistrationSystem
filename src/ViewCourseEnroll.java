import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class ViewCourseEnroll implements ActionListener
{
    private JFrame frame = new JFrame();
    private JTextField courseDep;
    private JPanel viewCourseEnrollPane;
    private JTextField courseNum;
    private JButton enterButton;
    private JButton backButton;
    static HashMap<String, String> signIns;
    static HashMap<String, Student> database;
    static ArrayList<Course> courseOfferings;


    public ViewCourseEnroll(HashMap<String, String> logIns, HashMap<String, Student> database, ArrayList<Course> courses)
    {
        //Basic panel setup
        frame.setContentPane(viewCourseEnrollPane);
        frame.setTitle("Viewing Course Enrollment");
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setSize(500,300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        signIns = logIns;
        this.database = database;
        courseOfferings = courses;

        //actionListeners required for actionPerformed method
        backButton.addActionListener(this);
        enterButton.addActionListener(this);
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
        if(e.getSource() == enterButton)
        {
            frame.dispose();
            try {
                CourseEnrollmentList courseList = new CourseEnrollmentList(signIns, database, courseDep.getText(), Integer.valueOf(courseNum.getText()),courseOfferings);
            }
            catch(NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(frame, "Please enter appropriate parameters ");
                ViewCourseEnroll viewCourseEnroll = new ViewCourseEnroll(signIns, database, courseOfferings);
            }
        }
        if(e.getSource() == backButton)
        {
            frame.dispose();
            RegistrarsPage regPage = new RegistrarsPage(signIns, database, courseOfferings);
        }


    }
    public void closeOp()
    {
        frame.dispose();
        GUIMain signInPage = new GUIMain(signIns, database, courseOfferings);
    }
}
