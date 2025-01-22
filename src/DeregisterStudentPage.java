import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class DeregisterStudentPage implements ActionListener
{
    private JFrame frame = new JFrame();
    private JTextField textField1;
    private JPanel DeregisterStudentPanel;
    private JButton deregisterButton;
    private JButton backButton;

    static HashMap<String, String> signIns;
    static HashMap<String, Student> database;
    static ArrayList<Course> courseOfferings;


    public DeregisterStudentPage(HashMap<String, String> logIns, HashMap<String, Student> database, ArrayList<Course> courses)
    {
        frame.setContentPane(DeregisterStudentPanel);
        frame.setTitle("Lunar System (Deregister Student)");
        //frame.setDefaultCloseOperation(EXIT_ON_CLOSE);      //switch to opening up a GUIMAIN  sign in page instead
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setSize(500,300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        signIns = logIns;
        this.database = database;
        courseOfferings = courses;

        deregisterButton.addActionListener(this);
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
        String webId = textField1.getText();
        if(e.getSource() == deregisterButton)
        {
            if(signIns.containsKey(webId))
            {
                signIns.remove(webId);
                database.remove(webId);
                JOptionPane.showMessageDialog(frame, "Student deregistered");
                frame.dispose();
                RegistrarsPage regPage = new RegistrarsPage(signIns, database, courseOfferings);
            }
            else
            {
                JOptionPane.showMessageDialog(frame, "Error: Could not find student in database");
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
