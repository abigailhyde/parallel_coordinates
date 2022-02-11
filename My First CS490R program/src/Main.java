import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Hashtable;

public class Main extends JFrame {

    private final Vis contents;
    public static Hashtable<Integer, Student> students = new Hashtable<>();

    public Main() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,400);
        contents = new Vis();
        setContentPane(contents);
        setTitle("Line Charts and Bar Charts");
        var abigail = createMenu();
        setJMenuBar(abigail);
        setVisible(true);
    }

    private JMenuBar createMenu() {

        JMenuBar mb = new JMenuBar();
        JMenu file = new JMenu("Table");

        JMenuItem twelve = new JMenuItem("2012"); //queries done :)
        twelve.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("2012 selected");
                Vis.table = 2012;
                try {

                    Connection conn = DriverManager.getConnection("jdbc:derby:/home/abigail/database/pollster");
                    Statement stmt = conn.createStatement();

                    //query herey
                    ResultSet query = stmt.executeQuery("SELECT gpa, credits_attempted, credits_passed, current_credits, age, gender FROM cis2012");

                    int i = 0;
                    while (query.next()) {

                        // add row to hashtable
                        students.put(i, new Student(query.getFloat(1),query.getFloat(2),query.getFloat(3),query.getFloat(4),query.getFloat(5),query.getString(6)));
                        i++;
                    }

                    contents.repaint();
                    conn.close();

                } catch (SQLException throwables) {

                    throwables.printStackTrace();
                }
            }
        });

        JMenuItem nineteen = new JMenuItem("2019"); //queries done :)
        nineteen.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("2019 selected");
                Vis.table = 2019;
                try {

                    Connection conn = DriverManager.getConnection("jdbc:derby:/home/abigail/database/pollster");
                    Statement stmt = conn.createStatement();

                    //query herey
                    ResultSet query = stmt.executeQuery("SELECT gender, agegroup, credits_attempted, credits_passed, gpa, gradyear, home, major FROM cis2019");

                    int i = 0;
                    while (query.next()) {

                        // add row to hashtable
                        students.put(i, new Student(query.getString(1), query.getString(2), query.getFloat(3), query.getFloat(4), query.getFloat(5), query.getInt(6), query.getString(7), query.getString(8)));
                        i++;
                    }

                    contents.repaint();
                    conn.close();

                } catch (SQLException throwables) {

                    throwables.printStackTrace();
                }
            }
        });

        //adding questions to the menu bar
        file.add(twelve);
        file.add(nineteen);
        mb.add(file);

        return mb;
    }

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(Main::new);
    }
}
