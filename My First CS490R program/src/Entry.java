import java.awt.*;
import java.util.ArrayList;
import java.util.Hashtable;

public class Entry {

    Hashtable<String, String> strings;
    Hashtable<String, Float> floats;
    ArrayList<Point> points;



    public Entry(float gpa, float taken, float passed, float current, float age, String gender) {

        points = new ArrayList<>();
        strings = new Hashtable<>();
        floats = new Hashtable<>();
        floats.put("GPA",gpa);
        floats.put("CREDITS_ATTEMPTED", taken);
        floats.put("CREDITS_PASSED", passed);
        floats.put("CURRENT_CREDITS", current);
        floats.put("AGE", age);
        strings.put("GENDER", gender);
    }

    public Entry(String gender, String ageGroup, float attempted, float passed, float gpa, int gradYear, String home, String major) {

        points = new ArrayList<>();
        strings = new Hashtable<>();
        floats = new Hashtable<>();
        strings.put("GENDER", gender);
        strings.put("AGEGROUP", ageGroup);
        floats.put("CREDITS_ATTEMPTED", attempted);
        floats.put("CREDITS_PASSED", passed);
        floats.put("GPA", gpa);
        floats.put("GRADYEAR", (float) gradYear);
        strings.put("HOME", home);
        strings.put("MAJOR", major);
    }

    public void calcPolyLine(ArrayList<Axis> a, int w, int h) {

        points.clear();
        for (int i = 0; i < a.size(); i++) {

            if (a.get(i).type == Axis.ColumnType.NUMERIC) {

                float min = a.get(i).min;
                float max = a.get(i).max;
                float value = floats.get(a.get(i).columnName);
                int x = (int) (i * (w / a.size() - 0.5) + 50);
                int y = (int) ((1 - (value - min)/(max - min))*(h - 20 - 10) + 10);
                points.add(new Point(x,y));
            } else if (a.get(i).type == Axis.ColumnType.TEXT) {

                String value = strings.get(a.get(i).columnName);
                Point p = a.get(i).getStringPoint(value);
                points.add(p);
            }
        }
    }

    public void draw(Graphics g) {

        int[] xVals = new int[points.size()];
        int[] yVals = new int[points.size()];
        for(int i = 0; i < points.size(); i++) {

            xVals[i] = points.get(i).x;
            yVals[i] = points.get(i).y;
        }

        g.setColor(Color.BLACK);
        g.drawPolyline(xVals, yVals, points.size());
    }
}