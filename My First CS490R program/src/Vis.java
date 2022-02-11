import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.text.DecimalFormat;
import java.awt.geom.Line2D;

public class Vis extends JPanel implements ActionListener {

    public static int queryNumber;
    public int bins;
    public float max;
    public static String chartType;

    public int lineStartX;
    public int lineEndX;
    public int lineStartY;
    public int lineEndY;

    ArrayList<String> majorStrings = new ArrayList<>();
    ArrayList<String> localeStrings = new ArrayList<>();
    ArrayList<String> gpaStrings = new ArrayList<>();
    ArrayList<String> creditStrings = new ArrayList<>();
    ArrayList<String> ageStrings = new ArrayList<>();
    ArrayList<String> spgStrings = new ArrayList<>();
    ArrayList<Float> yLabels = new ArrayList<>();


    public Vis() {
        majorStrings.add("IT"); majorStrings.add("IS"); majorStrings.add("CS");
        localeStrings.add("Hawaii"); localeStrings.add("Asia"); localeStrings.add("US"); localeStrings.add("Other Int."); localeStrings.add("Pacific");
        gpaStrings.add("IT"); gpaStrings.add("IS"); gpaStrings.add("CS");
        creditStrings.add("2009"); creditStrings.add("2010"); creditStrings.add("2011"); creditStrings.add("2012"); creditStrings.add("2013"); creditStrings.add("2014"); creditStrings.add("2015"); creditStrings.add("2016"); creditStrings.add("2017"); creditStrings.add("2018"); creditStrings.add("2019");
        ageStrings.add("18-21"); ageStrings.add("22-24"); ageStrings.add("25-29"); ageStrings.add("30-34"); ageStrings.add("35-64");
        spgStrings.add("2.25 – 2.5"); spgStrings.add("2.5 – 2.75"); spgStrings.add("2.75 – 3.0"); spgStrings.add("3.0 – 3.25"); spgStrings.add("3.25 – 3.5"); spgStrings.add("3.5 – 3.75"); spgStrings.add("3.75 – 4.0");

        chartType = "bar";
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawLine(50,40,50,getHeight() - 75); //draw vertical axis
        g.drawLine(50,getHeight() - 75, getWidth() - 50,getHeight() - 75); //draw horizontal axis

        float verticalLength = (getHeight() - 75) - 40;
        float horizontalLength = (getWidth() - 50) - 50;

        DecimalFormat df = new DecimalFormat("###.##");

        if (chartType.equals("bar")) {

            repaint();

            if (queryNumber == 1) { //number of students in each major

                bins = Main.majorTable.size();
                max = Collections.max(Main.majorTable.values()); //gets the maximum value from the hashtable
                yLabels.add(max); yLabels.add((max/5)*4);yLabels.add((max/5)*3); yLabels.add((max/5)*2); yLabels.add((max/5)); yLabels.add(0.0F);
                g.drawString("How many students are in each major?", 50, 20);
                //drawing the columns
                for (int i = 0; i < bins; i++) {

                    int ratio = (int)((Main.majorTable.get(i)/max)*verticalLength);
                    g.setColor(Color.PINK);
                    g.fillRect((int) (((horizontalLength/bins)*i) + 75),(int)verticalLength-ratio+40,getWidth()/bins/2, ratio);
                    g.setColor(Color.BLACK);
                    g.drawString(majorStrings.get(i),(int)(horizontalLength/bins*i)+105,(int)(verticalLength+60));
                }
                for (int j = 0; j < 5; j++) {

                    g.drawString(df.format(yLabels.get(j)),10,(int) ((verticalLength/5)*j)+50);
                }
            } else if (queryNumber == 2) { //home area

                bins = Main.localeTable.size();
                max = Collections.max(Main.localeTable.values());
                yLabels.add(max); yLabels.add((max/5)*4);yLabels.add((max/5)*3); yLabels.add((max/5)*2); yLabels.add((max/5));yLabels.add(0.0F);
                g.drawString("How many students are from each home area?", 50, 20);
                //drawing the columns
                for (int i = 0; i < bins; i++) {

                    int ratio = (int)((Main.localeTable.get(i)/max)*verticalLength);
                    g.setColor(Color.PINK);
                    g.fillRect((int) (((horizontalLength/bins)*i) + 75),(int)verticalLength-ratio+40,getWidth()/bins/2, ratio);
                    g.setColor(Color.BLACK);
                    g.drawString(localeStrings.get(i),(int)(horizontalLength/bins*i)+105,(int)(verticalLength+60));
                }
                for (int j = 0; j < 5; j++) {

                    g.drawString(df.format(yLabels.get(j)),10,(int) ((verticalLength/5)*j)+50);
                }
            } else if (queryNumber == 3) { //gpa per major

                bins = Main.gpaTable.size();
                max = Collections.max(Main.gpaTable.values());
                yLabels.add(max); yLabels.add((max/5)*4);yLabels.add((max/5)*3); yLabels.add((max/5)*2); yLabels.add((max/5));yLabels.add(0.0F);
                g.drawString("What is the average GPA for each major?", 50, 20);
                //drawing the columns
                for (int i = 0; i < bins; i++) {

                    int ratio = (int)((Main.gpaTable.get(i)/max)*verticalLength);
                    g.setColor(Color.PINK);
                    g.fillRect((int) (((horizontalLength/bins)*i) + 75),(int)verticalLength-ratio+40,getWidth()/bins/2, ratio);
                    g.setColor(Color.BLACK);
                    g.drawString(gpaStrings.get(i),(int)(horizontalLength/bins*i)+105,(int)(verticalLength+60));
                }
                for (int j = 0; j < 5; j++) {

                    g.drawString(df.format(yLabels.get(j)),10,(int) ((verticalLength/5)*j)+50);
                }
            } else if (queryNumber == 4) { //credits attempted

                bins = Main.creditTable.size();
                max = Collections.max(Main.creditTable.values());
                yLabels.add(max); yLabels.add((max/5)*4);yLabels.add((max/5)*3); yLabels.add((max/5)*2); yLabels.add((max/5));yLabels.add(0.0F);
                g.drawString("What is the average number of credits attempted per year?", 50, 20);
                //drawing the columns
                for (int i = 0; i < bins; i++) {

                    int ratio = (int)((Main.creditTable.get(i)/max)*verticalLength);
                    g.setColor(Color.PINK);
                    g.fillRect((int) (((horizontalLength/bins)*i) + 75),(int)verticalLength-ratio+40,getWidth()/bins/2, ratio);
                    g.setColor(Color.BLACK);
                    g.drawString(creditStrings.get(i),(int)(horizontalLength/bins*i)+105,(int)(verticalLength+60));
                }
                for (int j = 0; j < 5; j++) {

                    g.drawString(df.format(yLabels.get(j)),10,(int) ((verticalLength/5)*j)+50);
                }
            } else if (queryNumber == 5) { //age group

                bins = Main.ageTable.size();
                max = Collections.max(Main.ageTable.values());
                yLabels.add(max); yLabels.add((max/5)*4);yLabels.add((max/5)*3); yLabels.add((max/5)*2); yLabels.add((max/5));yLabels.add(0.0F);
                g.drawString("How many students are from each age group?", 50, 20);
                //drawing the columns
                for (int i = 0; i < bins; i++) {

                    int ratio = (int)((Main.ageTable.get(i)/max)*verticalLength);
                    g.setColor(Color.PINK);
                    g.fillRect((int) (((horizontalLength/bins)*i) + 75),(int)verticalLength-ratio+40,getWidth()/bins/2, ratio);
                    g.setColor(Color.BLACK);
                    g.drawString(ageStrings.get(i),(int)(horizontalLength/bins*i)+105,(int)(verticalLength+60));
                }
                for (int j = 0; j < 5; j++) {

                    g.drawString(df.format(yLabels.get(j)),10,(int) ((verticalLength/5)*j)+50);
                }
            } else if (queryNumber == 6) {

                bins = Main.spgTable.size();
                max = Collections.max(Main.spgTable.values());
                yLabels.add(max); yLabels.add((max/5)*4);yLabels.add((max/5)*3); yLabels.add((max/5)*2); yLabels.add((max/5));yLabels.add(0.0F);
                g.drawString("Number of students per GPA", 50, 20);
                //drawing the columns
                for (int i = 0; i < bins; i++) {

                    int ratio = (int)((Main.spgTable.get(i)/max)*verticalLength);
                    g.setColor(Color.PINK);
                    g.fillRect((int) (((horizontalLength/bins)*i) + 75),(int)verticalLength-ratio+40,getWidth()/bins/2, ratio);

                    g.setColor(Color.BLACK);
                    g.drawString(spgStrings.get(i),(int)(horizontalLength/bins*i)+105,(int)(verticalLength+60));
                }
                for (int j = 0; j < 5; j++) {

                    g.drawString(df.format(yLabels.get(j)),10,(int) ((verticalLength/5)*j)+50);
                }
            }
        } else if (chartType.equals("line")) { //if the user selected the line graph option

            repaint();
            if (queryNumber == 1) { //number of students in each major

                bins = Main.majorTable.size();
                max = Collections.max(Main.majorTable.values()); //gets the maximum value from the hashtable
                yLabels.add(max); yLabels.add((max/5)*4);yLabels.add((max/5)*3); yLabels.add((max/5)*2); yLabels.add((max/5)); yLabels.add(0.0F);
                g.drawString("How many students are in each major?", 50, 20);
                //drawing the columns
                for (int i = 0; i < bins; i++) {

                    int ratio = (int)((Main.majorTable.get(i)/max)*verticalLength);
                    g.setColor(Color.PINK);
                    g.fillOval((int) (((horizontalLength/bins)*i) + 75), (int)verticalLength-ratio+20, getWidth()/bins/6,getWidth()/bins/6);
                    if (i > 0 && i < bins) {

                        lineEndX = (int)((horizontalLength/bins)*i) + 50 + (getWidth()/bins/6);
                        lineEndY = (int)verticalLength-ratio+(getWidth()/bins/6);

                        Graphics2D g2 = (Graphics2D) g;
                        g2.setStroke(new BasicStroke(10));
                        g2.draw(new Line2D.Float(lineStartX, lineStartY, lineEndX, lineEndY));

                        lineStartX = lineEndX;
                        lineStartY = lineEndY;
                    } else if (i == 0) {

                        lineStartX = (int)((horizontalLength/bins)*i) + 50 + (getWidth()/bins/6);
                        lineStartY = (int)verticalLength-ratio+(getWidth()/bins/6);
                    }
                    g.setColor(Color.BLACK);
                    g.drawString(majorStrings.get(i),(int)(horizontalLength/bins*i)+105,(int)(verticalLength+60));
                }
                for (int j = 0; j < 5; j++) {

                    g.drawString(df.format(yLabels.get(j)),10,(int) ((verticalLength/5)*j)+50);
                }
            } else if (queryNumber == 2) { //home area

                bins = Main.localeTable.size();
                max = Collections.max(Main.localeTable.values());
                yLabels.add(max); yLabels.add((max/5)*4);yLabels.add((max/5)*3); yLabels.add((max/5)*2); yLabels.add((max/5));yLabels.add(0.0F);
                g.drawString("How many students are from each home area?", 50, 20);
                //drawing the columns
                for (int i = 0; i < bins; i++) {
                    int ratio = (int)((Main.localeTable.get(i)/max)*verticalLength);
                    g.setColor(Color.PINK);
                    g.fillOval((int) (((horizontalLength/bins)*i) + 75), (int)verticalLength-ratio+20, getWidth()/bins/6,getWidth()/bins/6);
                    if (i > 0 && i < bins) {
                        lineEndX = (int)((horizontalLength/bins)*i) + 50 + (getWidth()/bins/6);
                        lineEndY = (int)verticalLength-ratio+(getWidth()/bins/6);

                        Graphics2D g2 = (Graphics2D) g;
                        g2.setStroke(new BasicStroke(10));
                        g2.draw(new Line2D.Float(lineStartX, lineStartY, lineEndX, lineEndY));

                        lineStartX = lineEndX;
                        lineStartY = lineEndY;

                    } else if (i == 0) {

                        lineStartX = (int)((horizontalLength/bins)*i) + 50 + (getWidth()/bins/6);
                        lineStartY = (int)verticalLength-ratio+(getWidth()/bins/6);
                    }
                    g.setColor(Color.BLACK);
                    g.drawString(localeStrings.get(i),(int)(horizontalLength/bins*i)+105,(int)(verticalLength+60));
                }
                for (int j = 0; j < 5; j++) {

                    g.drawString(df.format(yLabels.get(j)),10,(int) ((verticalLength/5)*j)+50);
                }
            } else if (queryNumber == 3) { //gpa per major

                bins = Main.gpaTable.size();
                max = Collections.max(Main.gpaTable.values());
                yLabels.add(max); yLabels.add((max/5)*4);yLabels.add((max/5)*3); yLabels.add((max/5)*2); yLabels.add((max/5));yLabels.add(0.0F);
                g.drawString("What is the average GPA for each major?", 50, 20);
                //drawing the columns
                for (int i = 0; i < bins; i++) {

                    int ratio = (int)((Main.gpaTable.get(i)/max)*verticalLength);
                    g.setColor(Color.PINK);
                    g.fillOval((int) (((horizontalLength/bins)*i) + 75), (int)verticalLength-ratio+20, getWidth()/bins/6,getWidth()/bins/6);
                    if (i > 0 && i < bins) {

                        lineEndX = (int)((horizontalLength/bins)*i) + 50 + (getWidth()/bins/6);
                        lineEndY = (int)verticalLength-ratio+(getWidth()/bins/6);

                        Graphics2D g2 = (Graphics2D) g;
                        g2.setStroke(new BasicStroke(10));
                        g2.draw(new Line2D.Float(lineStartX, lineStartY, lineEndX, lineEndY));

                        lineStartX = lineEndX;
                        lineStartY = lineEndY;
                    } else if (i == 0) {

                        lineStartX = (int)((horizontalLength/bins)*i) + 50 + (getWidth()/bins/6);
                        lineStartY = (int)verticalLength-ratio+(getWidth()/bins/6);
                    }
                    g.setColor(Color.BLACK);
                    g.drawString(gpaStrings.get(i),(int)(horizontalLength/bins*i)+105,(int)(verticalLength+60));
                }
                for (int j = 0; j < 5; j++) {

                    g.drawString(df.format(yLabels.get(j)),10,(int) ((verticalLength/5)*j)+50);
                }
            } else if (queryNumber == 4) { //credits attempted

                bins = Main.creditTable.size();
                max = Collections.max(Main.creditTable.values());
                yLabels.add(max); yLabels.add((max/5)*4);yLabels.add((max/5)*3); yLabels.add((max/5)*2); yLabels.add((max/5));yLabels.add(0.0F);
                g.drawString("What is the average number of credits attempted per year?", 50, 20);
                //drawing the columns
                for (int i = 0; i < bins; i++) {

                    int ratio = (int)((Main.creditTable.get(i)/max)*verticalLength);
                    g.setColor(Color.PINK);
                    g.fillOval((int) (((horizontalLength/bins)*i) + 75), (int)verticalLength-ratio+20, getWidth()/bins/6,getWidth()/bins/6);
                    if (i > 0 && i < bins) {

                        lineEndX = (int)((horizontalLength/bins)*i) + 50 + (getWidth()/bins/6);
                        lineEndY = (int)verticalLength-ratio+(getWidth()/bins/6);

                        Graphics2D g2 = (Graphics2D) g;
                        g2.setStroke(new BasicStroke(10));
                        g2.draw(new Line2D.Float(lineStartX, lineStartY, lineEndX, lineEndY));

                        lineStartX = lineEndX;
                        lineStartY = lineEndY;
                    } else if (i == 0) {

                        lineStartX = (int)((horizontalLength/bins)*i) + 50 + (getWidth()/bins/6);
                        lineStartY = (int)verticalLength-ratio+(getWidth()/bins/6);
                    }
                    g.setColor(Color.BLACK);
                    g.drawString(creditStrings.get(i),(int)(horizontalLength/bins*i)+105,(int)(verticalLength+60));
                }
                for (int j = 0; j < 5; j++) {

                    g.drawString(df.format(yLabels.get(j)),10,(int) ((verticalLength/5)*j)+50);
                }
            } else if (queryNumber == 5) { //age group

                bins = Main.ageTable.size();
                max = Collections.max(Main.ageTable.values());
                yLabels.add(max); yLabels.add((max/5)*4);yLabels.add((max/5)*3); yLabels.add((max/5)*2); yLabels.add((max/5));yLabels.add(0.0F);
                g.drawString("How many students are from each age group?", 50, 20);
                //drawing the columns
                for (int i = 0; i < bins; i++) {

                    int ratio = (int)((Main.ageTable.get(i)/max)*verticalLength);
                    g.setColor(Color.PINK);
                    g.fillOval((int) (((horizontalLength/bins)*i) + 75), (int)verticalLength-ratio+20, getWidth()/bins/6,getWidth()/bins/6);
                    if (i > 0 && i < bins) {

                        lineEndX = (int)((horizontalLength/bins)*i) + 50 + (getWidth()/bins/6);
                        lineEndY = (int)verticalLength-ratio+(getWidth()/bins/6);

                        Graphics2D g2 = (Graphics2D) g;
                        g2.setStroke(new BasicStroke(10));
                        g2.draw(new Line2D.Float(lineStartX, lineStartY, lineEndX, lineEndY));

                        lineStartX = lineEndX;
                        lineStartY = lineEndY;
                    } else if (i == 0) {

                        lineStartX = (int)((horizontalLength/bins)*i) + 50 + (getWidth()/bins/6);
                        lineStartY = (int)verticalLength-ratio+(getWidth()/bins/6);
                    }
                    g.setColor(Color.BLACK);
                    g.drawString(ageStrings.get(i),(int)(horizontalLength/bins*i)+105,(int)(verticalLength+60));
                }
                for (int j = 0; j < 5; j++) {

                    g.drawString(df.format(yLabels.get(j)),10,(int) ((verticalLength/5)*j)+50);
                }
            } else if (queryNumber == 6) {

                bins = Main.spgTable.size();
                max = Collections.max(Main.spgTable.values());
                yLabels.add(max); yLabels.add((max/5)*4);yLabels.add((max/5)*3); yLabels.add((max/5)*2); yLabels.add((max/5));yLabels.add(0.0F);
                g.drawString("Number of students per GPA", 50, 20);
                //drawing the columns
                for (int i = 0; i < bins; i++) {

                    int ratio = (int)((Main.spgTable.get(i)/max)*verticalLength);
                    g.setColor(Color.PINK);
                    g.fillOval((int) (((horizontalLength/bins)*i) + 75), (int)verticalLength-ratio+20, getWidth()/bins/6,getWidth()/bins/6);
                    if (i > 0 && i < bins) {

                        lineEndX = (int)((horizontalLength/bins)*i) + 50 + (getWidth()/bins/6);
                        lineEndY = (int)verticalLength-ratio+(getWidth()/bins/6);

                        Graphics2D g2 = (Graphics2D) g;
                        g2.setStroke(new BasicStroke(10));
                        g2.draw(new Line2D.Float(lineStartX, lineStartY, lineEndX, lineEndY));

                        lineStartX = lineEndX;
                        lineStartY = lineEndY;
                    } else if (i == 0) {

                        lineStartX = (int)((horizontalLength/bins)*i) + 50 + (getWidth()/bins/6);
                        lineStartY = (int)verticalLength-ratio+(getWidth()/bins/6);
                    }
                    g.setColor(Color.BLACK);
                    g.drawString(spgStrings.get(i),(int)(horizontalLength/bins*i)+105,(int)(verticalLength+60));
                }
                for (int j = 0; j < 5; j++) {

                    g.drawString(df.format(yLabels.get(j)),10,(int) ((verticalLength/5)*j)+50);
                }
            }
        }
    }
}
