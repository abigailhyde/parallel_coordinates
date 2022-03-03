import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Hashtable;


public class Entry {

    enum Type {
        TWELVE,
        NINETEEN
    }

    Hashtable<String, String> strings;
    Hashtable<String, Float> floats;
    ArrayList<Point> points;
    boolean selected;
    boolean selection;
    boolean hover;
    Type type;

    public Entry(float gpa, float taken, float passed, float current, float age, String gender) {
        type = Type.TWELVE;
        points = new ArrayList<>();
        strings = new Hashtable<>();
        floats = new Hashtable<>();
        floats.put("GPA",gpa);
        floats.put("CREDITS_ATTEMPTED", taken);
        floats.put("CREDITS_PASSED", passed);
        floats.put("CURRENT_CREDITS", current);
        floats.put("AGE", age);
        strings.put("GENDER", gender);
        selected = false;
        selection = false;
        hover = false;
    }

    public Entry(String gender, String ageGroup, float attempted, float passed, float gpa, int gradYear, String home, String major) {
        type = Type.NINETEEN;
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
        selected = false;
        selection = false;
        hover = false;
    }

    public boolean intersects(Rectangle r) {
        boolean result = false;
        for (int i=1; i<points.size(); i++) {
            var p1 = points.get(i-1);
            var p2 = points.get(i);
            //TODO creating a new Line2D object here might slow us down
            //if there are hundreds of polylines on the screen.
            //Consider optimizing if this becomes a bottleneck.
            var segment = new Line2D.Double(p1, p2);
            result = segment.intersects(r);
            if (result == true) {

                break;
            }
        }
        return result;
    }

    public String toolTipText() {
        if (type == type.TWELVE) {
            return "GPA: " +  floats.get("GPA") + " ATTEMPTED: " + floats.get("CREDITS_ATTEMPTED") + " PASSED: " + floats.get("CREDITS_PASSED") + " CURRENT: " + floats.get("CURRENT_CREDITS") + " AGE: " + floats.get("AGE") + " GENDER: " + strings.get("GENDER");
        } else {
            return "GENDER: " + strings.get("GENDER") + " AGEGROUP: " + strings.get("AGEGROUP") + " ATTEMPTED: " + floats.get("CREDITS_ATTEMPTED") + " PASSED: " +floats.get("CREDITS_PASSED") + " GPA: " + floats.get("GPA") + " GRADYEAR: " + floats.get("GRADYEAR") + " HOME: " + strings.get("HOME") + " MAJOR: " + strings.get("MAJOR");
        }
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

        if (hover) {
            g.setColor(Color.RED);
        }
        else if (selected) {
            g.setColor(Color.BLACK);
        } else if (!selected && selection) {
            g.setColor(Color.LIGHT_GRAY);
        } else if (!selected && !selection) {
            g.setColor(Color.BLACK);
        }
         g.drawPolyline(xVals, yVals, points.size());
    }
}