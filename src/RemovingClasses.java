import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class RemovingClasses implements ActionListener
{
    private JFrame frame = new JFrame();
    private JPanel removeClassPane;
    private JTextField courseDep;
    private JTextField courseNum;
    private JTextField semesterCode;
    private JButton removeCourseButton;
    private JButton backButton;

    static HashMap<String, String> signIns;
    static HashMap<String, Student> database;
    static ArrayList<Course> courseOfferings;

    public RemovingClasses(HashMap<String, String> logIns, HashMap<String, Student> database, ArrayList<Course> courses)
    {
        //Basic panel setup
        frame.setContentPane(removeClassPane);
        frame.setTitle("Removing a Course");
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setSize(500,300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        signIns = logIns;
        this.database = database;
        courseOfferings = courses;

        //actionListeners required for actionPerformed method
        removeCourseButton.addActionListener(this);
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
        if(e.getSource() == removeCourseButton)
        {
            frame.dispose();
            try
            {
                boolean removed = false;
                for(int i = 0; i < courseOfferings.size(); i++)
                {
                    if(courseOfferings.get(i).getDepartment().equalsIgnoreCase(courseDep.getText()) && courseOfferings.get(i).getNumber() == Integer.valueOf(courseNum.getText()) && courseOfferings.get(i).getSemester().equalsIgnoreCase(semesterCode.getText()))
                    {
                        courseOfferings.remove(i);
                        JOptionPane.showMessageDialog(frame, "Course removed");
                        removed = true;
                    }
                }
                if(!removed)
                {
                    JOptionPane.showMessageDialog(frame, "Course not found");
                }
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
