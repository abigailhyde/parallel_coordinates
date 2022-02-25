import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class Main extends JFrame {

    private final Vis contents;
    public static ArrayList<Entry> entries = new ArrayList<>();
    public static int NumOfCol;
    public static ArrayList<Axis> axes;
    public ArrayList<String> yAxis;

    public Main() {
        NumOfCol = 0;
        axes = new ArrayList<>();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300,700);
        contents = new Vis();
        setContentPane(contents);
        setTitle("Parallel Coordinates");
        var abigail = createMenu();
        setJMenuBar(abigail);
        setVisible(true);
    }


    public static ArrayList<Axis> getAxes() {
        return axes;
    }

    private JMenuBar createMenu() {

        JMenuBar mb = new JMenuBar();
        JMenu file = new JMenu("Table");

        JMenuItem twelve = new JMenuItem("2012"); //queries done :)
        twelve.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                entries = new ArrayList<>();
                axes = new ArrayList<>();
                try {

                    Connection conn = DriverManager.getConnection("jdbc:derby:/home/abigail/database/pollster");
                    Statement stmt = conn.createStatement();

                    //query herey
                    ResultSet query = stmt.executeQuery("SELECT gpa, credits_attempted, credits_passed, current_credits, age, gender FROM cis2012");

                    while (query.next()) {

                        // add row to hashtable
                        entries.add(new Entry(query.getFloat(1),query.getFloat(2),query.getFloat(3),query.getFloat(4),query.getFloat(5),query.getString(6)));
                    }

                    //gets number of lines to draw, should be 6
                    String quer="SELECT * FROM cis2012 where 1=2";
                    ResultSet rs=  stmt.executeQuery(quer);
                    ResultSetMetaData rsmd = rs.getMetaData();
                    NumOfCol=rsmd.getColumnCount();

                    for (int m = 1; m <= NumOfCol; m++) {

                        int type = rsmd.getColumnType(m);
                        if (type == 8) { //for some reason a float is 8... don't ask me why

                            String name = rsmd.getColumnName(m);

                            String q = "SELECT MAX(" + name + ") FROM cis2012";
                            ResultSet rq =  stmt.executeQuery(q);
                            rq.next();
                            float max = rq.getFloat(1);

                            String o = "SELECT MIN(" + name + ") FROM cis2012";
                            ResultSet ro = stmt.executeQuery(o);
                            ro.next();
                            int min = ro.getInt(1);

                            axes.add(new Axis(name, max, min));
                        } else if (type == 4){ //integer

                            String name = rsmd.getColumnName(m);

                            String q = "SELECT MAX(" + name + ") FROM cis2012";
                            ResultSet rq = stmt.executeQuery(q);
                            rq.next();
                            int max = rq.getInt(1);

                            String o = "SELECT MIN(" + name + ") FROM cis2012";
                            ResultSet ro = stmt.executeQuery(o);
                            ro.next();
                            int min = ro.getInt(1);

                            axes.add(new Axis(name, max, min));
                        } else { //string

                            yAxis = new ArrayList<>();
                            String name = rsmd.getColumnName(m);
                            String q = "SELECT DISTINCT " + name + " FROM cis2012";
                            ResultSet rq = stmt.executeQuery(q);

                            while (rq.next()) {

                                yAxis.add(rq.getString(1));
                            }

                            axes.add(new Axis(name, yAxis));
                        }
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

                entries = new ArrayList<>();
                axes = new ArrayList<>();
                try {

                    Connection conn = DriverManager.getConnection("jdbc:derby:/home/abigail/database/pollster");
                    Statement stmt = conn.createStatement();

                    //query herey
                    ResultSet query = stmt.executeQuery("SELECT gender, agegroup, credits_attempted, credits_passed, gpa, gradyear, home, major FROM cis2019");

                    while (query.next()) {

                        // add row to hashtable
                        entries.add(new Entry(query.getString(1), query.getString(2), query.getFloat(3), query.getFloat(4), query.getFloat(5), query.getInt(6), query.getString(7), query.getString(8)));
                    }

                    //gets number of lines to draw, should be 6
                    String quer="SELECT * FROM cis2019 where 1=2";

                    ResultSet rs=  stmt.executeQuery(quer);
                    ResultSetMetaData rsmd = rs.getMetaData();
                    NumOfCol=rsmd.getColumnCount();

                    for (int m = 1; m <= NumOfCol; m++) {

                        int type = rsmd.getColumnType(m);
                        if (type == 8) { //for some reason a float is 8... don't ask me why

                            String name = rsmd.getColumnName(m);

                            String q = "SELECT MAX(" + name + ") FROM cis2019";
                            ResultSet rq =  stmt.executeQuery(q);
                            rq.next();
                            float max = rq.getFloat(1);

                            String o = "SELECT MIN(" + name + ") FROM cis2019";
                            ResultSet ro = stmt.executeQuery(o);
                            ro.next();
                            int min = ro.getInt(1);

                            axes.add(new Axis(name, max, min));
                        } else if (type == 4){ //integer

                            String name = rsmd.getColumnName(m);

                            String q = "SELECT MAX(" + name + ") FROM cis2019";
                            ResultSet rq = stmt.executeQuery(q);
                            rq.next();
                            int max = rq.getInt(1);

                            String o = "SELECT MIN(" + name + ") FROM cis2019";
                            ResultSet ro = stmt.executeQuery(o);
                            ro.next();
                            int min = ro.getInt(1);

                            axes.add(new Axis(name, max, min));
                        } else { //string

                            yAxis = new ArrayList<>();
                            String name = rsmd.getColumnName(m);
                            String q = "SELECT DISTINCT " + name + " FROM cis2019";
                            ResultSet rq = stmt.executeQuery(q);

                            while (rq.next()) {

                                yAxis.add(rq.getString(1));
                            }

                            axes.add(new Axis(name, yAxis));
                        }
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
