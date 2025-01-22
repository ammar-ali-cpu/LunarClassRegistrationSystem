import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import java.io.FileNotFoundException;
import java.io.IOException;


import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class GUIMain //extends JFrame
{
    private JFrame frame = new JFrame();
    private JLabel fnUsername;
    private JTextField UsernameField;
    private JPanel SignInPanel;
    private JLabel fnPassword;
    private JPasswordField PassField;
    private JButton SignInButton;

    public GUIMain(HashMap<String, String> logIns, HashMap<String, Student> database, ArrayList<Course> courses)
    {
        frame.setContentPane(SignInPanel);
        frame.setTitle("Lunar System (Class Registration System)");
        //frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setSize(500,300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                close();
            }
        });

        SignInButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String enteredUsername = UsernameField.getText();
                String enteredPass = PassField.getText();
                if(!(signIns.containsKey(enteredUsername)))
                {
                    JOptionPane.showMessageDialog(frame, "User not found");
                }
                else if(signIns.containsKey(enteredUsername) && signIns.get(enteredUsername).equals(enteredPass))
                {
                    JOptionPane.showMessageDialog(frame, "Welcome " + enteredUsername);
                    if(enteredUsername.equalsIgnoreCase("REGISTRAR"))
                    {
                        frame.dispose();
                        RegistrarsPage registrarsPage = new RegistrarsPage(signIns, database, courseOfferings);
                    }
                    else
                    {
                        frame.dispose();
                        StudentsPage studentPage = new StudentsPage(signIns, database, enteredUsername, courseOfferings);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(frame, "Incorrect Password");

                }
            }
        });
    }
    static HashMap<String, String> signIns = new HashMap<>();
    static HashMap<String, Student> database = new HashMap<>();
    static ArrayList<Course> courseOfferings = new ArrayList<>();

    public static void main(String[] args)
    {
        File lunar = new File("LunarClassRegistrationSystem.ser");
        if(lunar.exists())
        {
            FileInputStream file = null;
            try {
                file = new FileInputStream("LunarClassRegistrationSystem.ser");
            } catch (FileNotFoundException e) {
                System.out.println("File not found exception for LunarClassRegistrationSystem.ser");
            }
            ObjectInputStream inStream = null;
            try {
                inStream = new ObjectInputStream(file);
            } catch (IOException e) {
                System.out.println("inStream is null");
            }
            try {
                signIns = (HashMap<String, String>) inStream.readObject();
                database = (HashMap<String, Student>) inStream.readObject();
                courseOfferings = (ArrayList<Course>) inStream.readObject();
            } catch (IOException e) {
                System.out.println("io exception for database");
            } catch (ClassNotFoundException e) {
                System.out.println("class not found exception for database");
            }
            try {
                inStream.close();
            } catch (IOException e) {
                System.out.println("inStream close io exception");
            }
            if(!signIns.containsKey("REGISTRAR"))
            {
                signIns.put("REGISTRAR", "RegistrarsPassword");
            }
            System.out.println("Previous data loaded.");
            new GUIMain(signIns, database, courseOfferings);
        }
        else if(!lunar.exists())
        {
            signIns.put("REGISTRAR", "RegistrarsPassword");
            new GUIMain(signIns, database, courseOfferings);
        }
    }

    public void close()
    {
        FileOutputStream file = null;
        try {
            file = new FileOutputStream("LunarClassRegistrationSystem.ser");
        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
        }
        ObjectOutputStream outStream = null;
        try {
            outStream = new ObjectOutputStream(file);
        } catch (IOException e) {
            System.out.println("IO Exception was thrown");
        }
        try {
            outStream.writeObject(signIns);
            outStream.writeObject(database);
            outStream.writeObject(courseOfferings);
        } catch (IOException e) {
            System.out.println("IO Exception was thrown");
        }
        try {
            outStream.close();
        } catch (IOException e) {
            System.out.println("IO exception was thrown");
        }
        System.out.println("System state saved, system shut down for maintenance.");
        System.exit(0);
    }

}
