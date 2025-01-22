import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class RegistrarsPage implements ActionListener
{
    private JFrame frame = new JFrame();
    private JButton registerAStudentButton;
    private JButton deRegisterAStudentButton;
    private JButton viewCourseEnrollmentButton;
    private JButton logoutButton;
    private JPanel RegistrarsPanel;
    private JButton createACourseButton;
    private JButton removeACourseButton;

    static HashMap<String, String> signIns;
    static HashMap<String, Student> database;
    static ArrayList<Course> courseOfferings;

    public RegistrarsPage(HashMap<String, String> logIns, HashMap<String, Student> database, ArrayList<Course> courses)
    {
        //Basic panel setup
        frame.setContentPane(RegistrarsPanel);
        frame.setTitle("Lunar System (Registrar's Portal)");
        //frame.setDefaultCloseOperation(EXIT_ON_CLOSE);         //switch to opening up a GUIMAIN  sign in page instead
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setSize(500,300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        signIns = logIns;
        this.database = database;
        courseOfferings = courses;

        //actionListeners required for actionPerformed method
        registerAStudentButton.addActionListener(this);
        deRegisterAStudentButton.addActionListener(this);
        createACourseButton.addActionListener(this);
        removeACourseButton.addActionListener(this);
        viewCourseEnrollmentButton.addActionListener(this);
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
       if(e.getSource() == registerAStudentButton)
       {
           frame.dispose();
           RegisterStudentPage regStudent = new RegisterStudentPage(signIns, database, courseOfferings);
       }
       if(e.getSource() == deRegisterAStudentButton)
       {
           frame.dispose();
           DeregisterStudentPage deregStudent = new DeregisterStudentPage(signIns, database, courseOfferings);
       }
       if(e.getSource() == createACourseButton)
       {
           frame.dispose();
           CreatingClasses createClass = new CreatingClasses(signIns, database, courseOfferings);
       }
       if(e.getSource() == removeACourseButton)
       {
           frame.dispose();
           RemovingClasses removeClass = new RemovingClasses(signIns, database, courseOfferings);
       }
       if(e.getSource() == viewCourseEnrollmentButton)
       {
           frame.dispose();
           ViewCourseEnroll viewCourseEnroll = new ViewCourseEnroll(signIns, database, courseOfferings);
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
