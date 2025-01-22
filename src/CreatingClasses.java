import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class CreatingClasses implements ActionListener
{
    private JFrame frame = new JFrame();
    private JPanel createClassPane;
    private JTextField courseDep;
    private JTextField courseNum;
    private JTextField semesterCode;
    private JButton createCourseButton;
    private JButton backButton;

    static HashMap<String, String> signIns;
    static HashMap<String, Student> database;
    static ArrayList<Course> courseOfferings;

    public CreatingClasses(HashMap<String, String> logIns, HashMap<String, Student> database, ArrayList<Course> courses)
    {
        //Basic panel setup
        frame.setContentPane(createClassPane);
        frame.setTitle("Creating a Course");
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setSize(500,300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        signIns = logIns;
        this.database = database;
        courseOfferings = courses;

        //actionListeners required for actionPerformed method
        createCourseButton.addActionListener(this);
        backButton.addActionListener(this);
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
        if(e.getSource() == backButton)
        {
            frame.dispose();
            RegistrarsPage regPage = new RegistrarsPage(signIns, database, courseOfferings);
        }
        if(e.getSource() == createCourseButton)
        {
            frame.dispose();
            try {
                Course toAdd = new Course(courseDep.getText(), Integer.valueOf(courseNum.getText()), semesterCode.getText());
                courseOfferings.add(toAdd);
                JOptionPane.showMessageDialog(frame, "Course created");
                RegistrarsPage regPage = new RegistrarsPage(signIns, database, courseOfferings);

            }
            catch(NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(frame, "Please enter appropriate parameters ");
                CreatingClasses creator = new CreatingClasses(signIns, database, courseOfferings);
            }
        }
    }

    public void closeOp()
    {
        frame.dispose();
        GUIMain signInPage = new GUIMain(signIns, database, courseOfferings);
    }
}
