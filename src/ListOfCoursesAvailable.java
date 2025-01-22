import java.awt.event.WindowEvent;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ListOfCoursesAvailable extends JFrame
{

    static ArrayList<Course> courseOfferings;

    ListOfCoursesAvailable(ArrayList<Course> courses)
    {

        courseOfferings = courses;

        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setSize(400,500);
        this.setTitle("Courses Available");
        this.setLocationRelativeTo(null);
        this.setLayout(null);



        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeOp();
            }
        });

        String[][] data = new String[courseOfferings.size()][2];
        for(int i = 0; i < courseOfferings.size(); i++)
        {
            data[i][0] = "" + courseOfferings.get(i).getDepartment() + " " + courseOfferings.get(i).getNumber();
            data[i][1] = "" + courseOfferings.get(i).getSemester();
        }

        String[] columnTitles = {"Course", "Semester"};

        JTable table = new JTable(data, columnTitles);
        //table.getTableHeader().setBounds(50, 0 , 300, 50);
        //table.setBounds(50, 50, 300, 200);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(50,50,300, 200);
        this.add(scroll);

        //this.add(table.getTableHeader());
        //this.add(table);

        this.setVisible(true);
    }

    public void closeOp()
    {
        this.dispose();
    }
}
