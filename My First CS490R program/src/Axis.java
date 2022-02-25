import java.awt.*;
import java.util.ArrayList;
import java.util.Hashtable;

public class Axis {

    enum ColumnType {

        NUMERIC,
        TEXT
    }

    String columnName;
    ColumnType type;

    ArrayList<String> stringData;
    ArrayList<Double> numberData;
    Hashtable<String, Point>  stringPoints;
    float max;
    float min;

    public Axis(String name, float maxValue, float minValue) {

        stringPoints = new Hashtable<>();
        numberData = new ArrayList<>();
        stringData = new ArrayList<>();
        columnName = name;
        max = maxValue;
        min = minValue;
        type = ColumnType.NUMERIC;
        for (int i = 0; i < 5; i++) {

            numberData.add((double) (i * ((maxValue - minValue) / 4)) + minValue);
        }
    }

    public Axis(String name, ArrayList<String> values) {

        stringPoints = new Hashtable<>();
        numberData = new ArrayList<>();
        columnName = name;
        type = ColumnType.TEXT;
        stringData = values;
    }

    public void addStringPoint(String name, Point p) {

        stringPoints.put(name, p);
    }

    public Point getStringPoint(String name) {

        return stringPoints.get(name);
    }
}