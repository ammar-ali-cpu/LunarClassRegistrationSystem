import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class RegisterStudentPage implements ActionListener
{
    private JFrame frame = new JFrame();
    private JPanel RegisterStudentPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JButton registerButton;
    private JButton backButton;

    static HashMap<String, String> signIns;
    static HashMap<String, Student> database;
    static ArrayList<Course> courseOfferings;


    public RegisterStudentPage(HashMap<String, String> logIns, HashMap<String, Student> database, ArrayList<Course> courses)
    {
        //Basic panel setup
        frame.setContentPane(RegisterStudentPanel);
        frame.setTitle("Lunar System (Register Student)");
        //frame.setDefaultCloseOperation(EXIT_ON_CLOSE);      //switch to opening up a GUIMAIN  sign in page instead
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setSize(500,300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        signIns = logIns;
        this.database = database;
        courseOfferings = courses;

        //actionListeners required for actionPerformed method
        registerButton.addActionListener(this);
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
        String webID = textField1.getText();
        String pass = textField2.getText();
        if(e.getSource() == registerButton)
        {
            if(signIns.containsKey(webID))
            {
                JOptionPane.showMessageDialog(frame, "WebID already exists");
            }
            else
            {
                signIns.put(webID,pass);
                Student toAdd = new Student(webID);
                database.put(webID,toAdd);
                JOptionPane.showMessageDialog(frame, "WebID is now registered!");
                frame.dispose();
                RegistrarsPage regPage = new RegistrarsPage(signIns, database, courseOfferings);
            }
        }
        if(e.getSource() == backButton)
        {
            frame.dispose();
            RegistrarsPage regPage = new RegistrarsPage(signIns, database,courseOfferings);
        }
    }

    public void closeOp()
    {
        frame.dispose();
        GUIMain signInPage = new GUIMain(signIns, database, courseOfferings);
    }
}
