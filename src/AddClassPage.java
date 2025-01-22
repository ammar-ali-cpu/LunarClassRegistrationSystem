import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class AddClassPage implements ActionListener
{

    private JFrame frame = new JFrame();
    private JPanel AddClassPane;
    private JTextField courseDep;
    private JTextField courseNum;
    private JTextField semester;
    private JButton addClassButton;
    private JButton backButton;

    static HashMap<String, String> signIns;
    static HashMap<String, Student> database;
    static String username;
    static ArrayList<Course> courseOfferings;


    public AddClassPage(HashMap<String, String> logIns, HashMap<String, Student> database, String webID, ArrayList<Course> courses)
    {
        //Basic panel setup
        frame.setContentPane(AddClassPane);
        frame.setTitle("Adding a Class");
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
        addClassButton.addActionListener(this);
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
            StudentsPage studentsPage = new StudentsPage(signIns, database, username, courseOfferings);
        }
        if(e.getSource() == addClassButton)
        {
            if(courseDep.getText().equalsIgnoreCase("") || courseNum.getText().equalsIgnoreCase("") || semester.getText().equalsIgnoreCase(""))
            {
                JOptionPane.showMessageDialog(frame, "Please fill in each field" );
            }
            else
            {
                boolean added = false;
                for(int j = 0; j < courseOfferings.size(); j++)
                {
                    if(courseOfferings.get(j).getDepartment().equalsIgnoreCase(courseDep.getText()) && courseOfferings.get(j).getNumber() == Integer.valueOf(courseNum.getText()) && courseOfferings.get(j).getSemester().equalsIgnoreCase(semester.getText()))
                    {
                        database.get(username).getCourses().add(courseOfferings.get(j));
                        added = true;
                        JOptionPane.showMessageDialog(frame, "Course " + courseDep.getText() + " " + courseNum.getText() + " added");
                        frame.dispose();
                        StudentsPage studentsPage = new StudentsPage(signIns, database, username, courseOfferings);
                    }
                }
                if(!added) {
                    JOptionPane.showMessageDialog(frame, "Course not found");
                }

                /**
                Course toAdd = new Course(courseDep.getText(), Integer.valueOf(courseNum.getText()), semester.getText());
                database.get(username).getCourses().add(toAdd);
                JOptionPane.showMessageDialog(frame, "Course " + courseDep.getText() + " " + courseNum.getText() + " added");
                frame.dispose();
                StudentsPage studentsPage = new StudentsPage(signIns, database, username, courseOfferings);
                 */
            }
        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public void closeOp()
    {
        frame.dispose();
        GUIMain signInPage = new GUIMain(signIns, database, courseOfferings);
    }
}
