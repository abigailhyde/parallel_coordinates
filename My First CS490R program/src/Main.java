import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Hashtable;

public class Main extends JFrame {

    private final Vis contents;
    public static Hashtable<Integer, Integer> majorTable = new Hashtable<>();
    public static Hashtable<Integer, Integer> localeTable = new Hashtable<>();
    public static Hashtable<Integer, Float> gpaTable = new Hashtable<>();
    public static Hashtable<Integer, Integer> creditTable = new Hashtable<>();
    public static Hashtable<Integer, Integer> ageTable = new Hashtable<>();
    public static Hashtable<Integer, Integer> spgTable = new Hashtable<>();


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

    private JMenuBar createMenu() { //TOTAL OF 342 STUDENTS

        JMenuBar mb = new JMenuBar();
        JMenu file = new JMenu("Queries");
        JMenu type = new JMenu("Chart Type");

        JMenuItem major = new JMenuItem("How many students are in each major?"); //queries done :)
        major.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("Just clicked option 1");
                Vis.queryNumber = 1;
                try {

                    Connection conn = DriverManager.getConnection("jdbc:derby:/home/abbygsmith21/database/pollster");
                    Statement stmt = conn.createStatement();

                    //count IT students
                    ResultSet itcount = stmt.executeQuery("SELECT COUNT(*) FROM cis2019 WHERE (major = 'Information Technology')");
                    itcount.next();
                    int countit = itcount.getInt(1);

                    //count IS students
                    ResultSet iscount = stmt.executeQuery("SELECT COUNT(*) FROM cis2019 WHERE (major = 'Information Systems')");
                    iscount.next();
                    int countis = iscount.getInt(1);

                    //count CS students
                    ResultSet cscount = stmt.executeQuery("SELECT COUNT(*) FROM cis2019 WHERE (major = 'Computer Science')");
                    cscount.next();
                    int countcs = cscount.getInt(1);

                    //adding values to the hashtable
                    majorTable.put(0, countit);
                    majorTable.put(1, countis);
                    majorTable.put(2, countcs);

                    contents.repaint();
                    conn.close();

                } catch (SQLException throwables) {

                    throwables.printStackTrace();
                }
            }
        });

        JMenuItem locale = new JMenuItem("How many students are from each home area?"); //queries done :)
        locale.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("Just clicked option 2");
                Vis.queryNumber = 2;
                try {

                    Connection conn = DriverManager.getConnection("jdbc:derby:/home/abbygsmith21/database/pollster");
                    Statement stmt = conn.createStatement();

                    //check for hawaii
                    ResultSet counthawaii = stmt.executeQuery("SELECT COUNT(*) FROM cis2019 WHERE (home = 'Hawaii')");
                    counthawaii.next();
                    int hawaiicount = counthawaii.getInt(1);

                    //check for asia
                    ResultSet countasia = stmt.executeQuery("SELECT COUNT(*) FROM cis2019 WHERE (home = 'Asia')");
                    countasia.next();
                    int asiacount = countasia.getInt(1);

                    //check for US Mainland
                    ResultSet countus = stmt.executeQuery("SELECT COUNT(*) FROM cis2019 WHERE (home = 'US Mainland')");
                    countus.next();
                    int uscount = countus.getInt(1);

                    //check for Other International
                    ResultSet countother = stmt.executeQuery("SELECT COUNT(*) FROM cis2019 WHERE (home = 'Other International')");
                    countother.next();
                    int othercount = countother.getInt(1);

                    //check for Pacific
                    ResultSet countpacific = stmt.executeQuery("SELECT COUNT(*) FROM cis2019 WHERE (home = 'Pacific')");
                    countpacific.next();
                    int pacificcount = countpacific.getInt(1);

                    //adding values to table
                    localeTable.put(0, hawaiicount);
                    localeTable.put(1, asiacount);
                    localeTable.put(2, uscount);
                    localeTable.put(3, othercount);
                    localeTable.put(4, pacificcount);

                    contents.repaint();
                    conn.close();
                } catch (SQLException throwables) {

                    throwables.printStackTrace();
                }
            }
        });

        JMenuItem gpa = new JMenuItem("What is the average GPA for each major?"); //queries done :)
        gpa.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("Just clicked option 3");
                Vis.queryNumber = 3;
                try {

                    Connection conn = DriverManager.getConnection("jdbc:derby:/home/abbygsmith21/database/pollster");
                    Statement stmt = conn.createStatement();

                    //average for it
                    ResultSet avgit = stmt.executeQuery("SELECT avg(gpa) FROM cis2019 WHERE (major = 'Information Technology')");
                    avgit.next();
                    float itavg = avgit.getFloat(1);

                    //average for is
                    ResultSet avgis = stmt.executeQuery("SELECT avg(gpa) FROM cis2019 WHERE (major = 'Information Systems')");
                    avgis.next();
                    float isavg = avgis.getFloat(1);

                    //average for cs
                    ResultSet avgcs = stmt.executeQuery("SELECT avg(gpa) FROM cis2019 WHERE (major = 'Computer Science')");
                    avgcs.next();
                    float csavg = avgcs.getFloat(1);

                    //adding values to the table
                    gpaTable.put(0, itavg);
                    gpaTable.put(1, isavg);
                    gpaTable.put(2, csavg);

                    contents.repaint();
                    conn.close();
                } catch (SQLException throwables) {

                    throwables.printStackTrace();
                }
            }
        });

        JMenuItem credits = new JMenuItem("What is the average number of credits attempted per year?"); //queries done :)
        credits.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("Just clicked option 4");
                Vis.queryNumber = 4;
                try {

                    Connection conn = DriverManager.getConnection("jdbc:derby:/home/abbygsmith21/database/pollster");
                    Statement stmt = conn.createStatement();

                    //2009
                    ResultSet ohnineavg = stmt.executeQuery("SELECT avg(credits_attempted) FROM cis2019 WHERE (gradyear = 2009)");
                    ohnineavg.next();
                    int avgohnine = ohnineavg.getInt(1);

                    //2010
                    ResultSet tenavg = stmt.executeQuery("SELECT avg(credits_attempted) FROM cis2019 WHERE (gradyear = 2010)");
                    tenavg.next();
                    int avgten = tenavg.getInt(1);

                    //2011
                    ResultSet elevenavg = stmt.executeQuery("SELECT avg(credits_attempted) FROM cis2019 WHERE (gradyear = 2011)");
                    elevenavg.next();
                    int avgeleven = elevenavg.getInt(1);

                    //2012
                    ResultSet twelveavg = stmt.executeQuery("SELECT avg(credits_attempted) FROM cis2019 WHERE (gradyear = 2012)");
                    twelveavg.next();
                    int avgtwelve = twelveavg.getInt(1);

                    //2013
                    ResultSet thirteenavg = stmt.executeQuery("SELECT avg(credits_attempted) FROM cis2019 WHERE (gradyear = 2013)");
                    thirteenavg.next();
                    int avgthirteen = thirteenavg.getInt(1);

                    //2014
                    ResultSet fourteenavg = stmt.executeQuery("SELECT avg(credits_attempted) FROM cis2019 WHERE (gradyear = 2014)");
                    fourteenavg.next();
                    int avgfourteen = fourteenavg.getInt(1);

                    //2015
                    ResultSet fifteenavg = stmt.executeQuery("SELECT avg(credits_attempted) FROM cis2019 WHERE (gradyear = 2015)");
                    fifteenavg.next();
                    int avgfifteen = fifteenavg.getInt(1);

                    //2016
                    ResultSet sixteenavg = stmt.executeQuery("SELECT avg(credits_attempted) FROM cis2019 WHERE (gradyear = 2016)");
                    sixteenavg.next();
                    int avgsixteen = sixteenavg.getInt(1);

                    //2017
                    ResultSet seventeenavg = stmt.executeQuery("SELECT avg(credits_attempted) FROM cis2019 WHERE (gradyear = 2017)");
                    seventeenavg.next();
                    int avgseventeen = seventeenavg.getInt(1);

                    //2018
                    ResultSet eighteenavg = stmt.executeQuery("SELECT avg(credits_attempted) FROM cis2019 WHERE (gradyear = 2018)");
                    eighteenavg.next();
                    int avgeighteen = eighteenavg.getInt(1);

                    //2019
                    ResultSet nineteenavg = stmt.executeQuery("SELECT avg(credits_attempted) FROM cis2019 WHERE (gradyear = 2019)");
                    nineteenavg.next();
                    int avgnineteen = nineteenavg.getInt(1);

                    //puting the info in a table
                    creditTable.put(0, avgohnine);
                    creditTable.put(1, avgten);
                    creditTable.put(2, avgeleven);
                    creditTable.put(3, avgtwelve);
                    creditTable.put(4, avgthirteen);
                    creditTable.put(5, avgfourteen);
                    creditTable.put(6, avgfifteen);
                    creditTable.put(7, avgsixteen);
                    creditTable.put(8, avgseventeen);
                    creditTable.put(9, avgeighteen);
                    creditTable.put(10, avgnineteen);

                    contents.repaint();
                    conn.close();
                } catch (SQLException throwables) {

                    throwables.printStackTrace();
                }
            }
        });

        JMenuItem age = new JMenuItem("How many students are from each age group?"); //queries done :)
        age.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("Just clicked option 5");
                Vis.queryNumber = 5;
                try {

                    Connection conn = DriverManager.getConnection("jdbc:derby:/home/abbygsmith21/database/pollster");
                    Statement stmt = conn.createStatement();

                    //18-21
                    ResultSet as = stmt.executeQuery("SELECT COUNT(*) FROM cis2019 WHERE (agegroup = '18-21')");
                    as.next();
                    int acount = as.getInt(1);

                    //22-24
                    ResultSet bs = stmt.executeQuery("SELECT COUNT(*) FROM cis2019 WHERE (agegroup = '22-24')");
                    bs.next();
                    int bcount = bs.getInt(1);

                    //25-29
                    ResultSet cs = stmt.executeQuery("SELECT COUNT(*) FROM cis2019 WHERE (agegroup = '25-29')");
                    cs.next();
                    int ccount = cs.getInt(1);

                    //30-34
                    ResultSet ds = stmt.executeQuery("SELECT COUNT(*) FROM cis2019 WHERE (agegroup = '30-34')");
                    ds.next();
                    int dcount = ds.getInt(1);

                    //35-64
                    ResultSet es = stmt.executeQuery("SELECT COUNT(*) FROM cis2019 WHERE (agegroup = '35-64')");
                    es.next();
                    int ecount = es.getInt(1);

                    ageTable.put(0, acount);
                    ageTable.put(1, bcount);
                    ageTable.put(2, ccount);
                    ageTable.put(3, dcount);
                    ageTable.put(4, ecount);

                    contents.repaint();
                    conn.close();
                } catch (SQLException throwables) {

                    throwables.printStackTrace();
                }
            }
        });

        JMenuItem studentsPerGpa = new JMenuItem("Number of students per GPA");
        studentsPerGpa.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("Just clicked option 6");
                Vis.queryNumber = 6;
                try {

                    Connection conn = DriverManager.getConnection("jdbc:derby:/home/abbygsmith21/database/pollster");
                    Statement stmt = conn.createStatement();

                    ResultSet onecount = stmt.executeQuery("SELECT COUNT(*) FROM cis2019 WHERE (gpa >=  2.25 AND gpa < 2.5)");
                    onecount.next();
                    int countone = onecount.getInt(1);

                    ResultSet twocount = stmt.executeQuery("SELECT COUNT(*) FROM cis2019 WHERE (gpa >=  2.5 AND gpa < 2.75)");
                    twocount.next();
                    int counttwo = twocount.getInt(1);

                    ResultSet threecount = stmt.executeQuery("SELECT COUNT(*) FROM cis2019 WHERE (gpa >=  2.75 AND gpa < 3.0)");
                    threecount.next();
                    int countthree = threecount.getInt(1);

                    ResultSet fourcount = stmt.executeQuery("SELECT COUNT(*) FROM cis2019 WHERE (gpa >=  3.0 AND gpa < 3.25)");
                    fourcount.next();
                    int countfour = fourcount.getInt(1);

                    ResultSet fivecount = stmt.executeQuery("SELECT COUNT(*) FROM cis2019 WHERE (gpa >=  3.25 AND gpa < 3.5)");
                    fivecount.next();
                    int countfive = fivecount.getInt(1);

                    ResultSet sixcount = stmt.executeQuery("SELECT COUNT(*) FROM cis2019 WHERE (gpa >=  3.5 AND gpa < 3.75)");
                    sixcount.next();
                    int countsix = sixcount.getInt(1);

                    ResultSet sevencount = stmt.executeQuery("SELECT COUNT(*) FROM cis2019 WHERE (gpa >=  3.75 AND gpa < 4.0)");
                    sevencount.next();
                    int countseven = sevencount.getInt(1);

                    //adding values to the hashtable
                    spgTable.put(0, countone);
                    spgTable.put(1, counttwo);
                    spgTable.put(2, countthree);
                    spgTable.put(3, countfour);
                    spgTable.put(4, countfive);
                    spgTable.put(5, countsix);
                    spgTable.put(6, countseven);

                    contents.repaint();
                    conn.close();

                } catch (SQLException throwables) {

                    throwables.printStackTrace();
                }
            }
        });

        JMenuItem bar = new JMenuItem("Bar");
        bar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("Bar chart selected");
                Vis.chartType = "bar";
            }
        });

        JMenuItem line = new JMenuItem("Line");
        line.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("Line chart selected");
                Vis.chartType = "line";
            }
        });

        //adding questions to the menu bar
        file.add(major);
        file.add(locale);
        file.add(gpa);
        file.add(credits);
        file.add(age);
        file.add(studentsPerGpa);
        mb.add(file);

        type.add(bar);
        type.add(line);
        mb.add(type);

        return mb;
    }

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(Main::new);
    }
}
