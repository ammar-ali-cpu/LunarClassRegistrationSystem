import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class DropClassPage implements ActionListener
{
    private JFrame frame = new JFrame();
    private JTextField courseDep;
    private JPanel DropClassPane;
    private JTextField courseNum;
    private JButton dropButton;
    private JButton backButton;

    static HashMap<String, String> signIns;
    static HashMap<String, Student> database;
    static String username;
    static ArrayList<Course> courseOfferings;


    public DropClassPage(HashMap<String, String> logIns, HashMap<String, Student> database, String webID, ArrayList<Course> courses)
    {
        //Basic panel setup
        frame.setContentPane(DropClassPane);
        frame.setTitle("Dropping a Class");
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
        dropButton.addActionListener(this);
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
        if(e.getSource() == dropButton)
        {
            int coursesRemoved = 0;
            for(int i = 0; i < database.get(username).getCourses().size(); i++)
            {
                if(database.get(username).getCourses().get(i).getDepartment().equalsIgnoreCase(courseDep.getText()) && database.get(username).getCourses().get(i).getNumber() == Integer.valueOf(courseNum.getText()))
                {
                    database.get(username).getCourses().remove(i);
                    coursesRemoved++;
                    JOptionPane.showMessageDialog(frame, "Course " + courseDep.getText() + " " + courseNum.getText() + " dropped" );
                }
            }
            if(coursesRemoved == 0)
            {
                JOptionPane.showMessageDialog(frame, "Course not found" );
            }
        }
    }

    public void closeOp()
    {
        frame.dispose();
        GUIMain signInPage = new GUIMain(signIns, database, courseOfferings);
    }
}





