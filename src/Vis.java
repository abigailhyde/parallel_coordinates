import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Vis extends JPanel implements ActionListener, MouseInputListener {

    public static boolean collision;
    public float verticalLength; public float horizontalLength;
    private Rectangle box;
    private Point mouseDown;
    ImageIcon sparkleIcon;
    private Timer timer;

    private boolean sparkleNow;

    public Vis() {
        super();
        timer = new Timer(1,null);

        addMouseListener(this);
        addMouseMotionListener(this);

        sparkleIcon = new ImageIcon("res/sparkles.png");
        Image image = sparkleIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(1300, 1000,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        sparkleIcon = new ImageIcon(newimg);  // transform it back

        int delay = 500; //milliseconds
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                sparkleNow = !sparkleNow;
                repaint();
            }
        };
        timer = new Timer(delay, taskPerformer);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        verticalLength = (getHeight() - 75) - 40;
        horizontalLength = (getWidth() - 50) - 50;

        //draw lines and labels below them
        for (int i = 0; i < Main.getAxes().size(); i++) {

            int x = (int) (i * (getWidth() / Main.getAxes().size() - 0.5) + 50);
            g.setColor(Color.BLACK);
            g.drawLine(x, 10, x, getHeight() - 20);
            g.drawString(Main.getAxes().get(i).columnName, x, getHeight()-5);
            if (Main.getAxes().get(i).type == Axis.ColumnType.NUMERIC) {

                for (int j = 0; j < Main.getAxes().get(i).numberData.size(); j++) {

                    DecimalFormat df = new DecimalFormat("###.##");
                    g.setColor(Color.RED);
                    g.drawString(df.format(Main.getAxes().get(i).numberData.get(j)),x+2,(getHeight() - 20 - 10)*(4-j)/4+10);
                }
            } else if (Main.getAxes().get(i).type == Axis.ColumnType.TEXT) {

                for (int j = 0; j < Main.getAxes().get(i).stringData.size(); j++) {

                    g.setColor(Color.RED);
                    g.drawString(Main.getAxes().get(i).stringData.get(j),x+2,(getHeight() - 20 - 10)*(j+1)/(Main.getAxes().get(i).stringData.size()+1)+10);
                    Main.getAxes().get(i).addStringPoint(Main.getAxes().get(i).stringData.get(j), new Point(x,(getHeight() - 20 - 10)*(j+1)/(Main.getAxes().get(i).stringData.size()+1)+10));
                }
            }
        }

        //draw poly line for each entry

        ArrayList<Entry> hover = new ArrayList<>();
        ArrayList<Entry> select = new ArrayList<>();
        ArrayList<Entry> rest = new ArrayList<>();
        for (Entry e: Main.entries) {

            e.calcPolyLine(Main.getAxes(),getWidth(),getHeight());
            if (e.hover) {

                hover.add(e);
            } else if (e.selected) {

                select.add(e);
            }
            else {

                rest.add(e);
            }
        }

        for (Entry e : rest) {
            e.draw(g);

        }

        for (Entry e : select) {
            e.draw(g);
        }

        for (Entry e : hover) {
            e.draw(g);
        }

        //draw lines and labels below them
        for (int i = 0; i < Main.getAxes().size(); i++) {

            int x = (int) (i * (getWidth() / Main.getAxes().size() - 0.5) + 50);
            g.setColor(Color.BLACK);
            g.drawLine(x, 10, x, getHeight() - 20);
            g.drawString(Main.getAxes().get(i).columnName, x, getHeight()-5);
            if (Main.getAxes().get(i).type == Axis.ColumnType.NUMERIC) {

                for (int j = 0; j < Main.getAxes().get(i).numberData.size(); j++) {

                    DecimalFormat df = new DecimalFormat("###.##");
                    g.setColor(Color.RED);
                    g.drawString(df.format(Main.getAxes().get(i).numberData.get(j)),x+2,(getHeight() - 20 - 10)*(4-j)/4+10);
                }
            } else if (Main.getAxes().get(i).type == Axis.ColumnType.TEXT) {

                for (int j = 0; j < Main.getAxes().get(i).stringData.size(); j++) {

                    g.setColor(Color.RED);
                    g.drawString(Main.getAxes().get(i).stringData.get(j),x+2,(getHeight() - 20 - 10)*(j+1)/(Main.getAxes().get(i).stringData.size()+1)+10);
                    Main.getAxes().get(i).addStringPoint(Main.getAxes().get(i).stringData.get(j), new Point(x,(getHeight() - 20 - 10)*(j+1)/(Main.getAxes().get(i).stringData.size()+1)+10));
                }
            }
        }

        if (sparkleNow) {
            sparkleIcon.paintIcon(null, g, -10, -10);
        }

        if (box != null) {

            //drawing the selection box
            Color color = new Color(240,40,145, 60);
            g.setColor(color);
            g.fillRect(box.x, box.y, box.width, box.height);
        }
    }

    public void sparkleOn() {
        timer.start();
    }

    public void sparkleOff() {
        sparkleNow = false;
        timer.stop();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        int x = e.getX();
        int y = e.getY();
        mouseDown = new Point(x,y);
        box = new Rectangle();
        for (Entry m: Main.entries) {
            m.selected = false;
            m.selection = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

        box = null;
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

        box.setFrameFromDiagonal(mouseDown.x, mouseDown.y, e.getX(), e.getY());

        for (Entry m: Main.entries) {
            m.selected = false;
            if (m.intersects(box)) {
                m.selected = true;
                System.out.println("We found an line/box intersection!!!");
                //TODO
            }
        }
        repaint();
    }

    public void checkCollision(int x, int y) {

        collision = false;
    }

    //adding tool tips to each point
    @Override
    public void mouseMoved(MouseEvent e) {
        Entry selectedEntry = null;
        int x = e.getX();
        int y = e.getY();
        Rectangle hoverBox = new Rectangle(x-1,y-1,3,3);

        for (Entry m: Main.entries) {
            m.hover = false;
        }
        for (Entry m: Main.entries) {
            if (m.intersects(hoverBox)) {
                m.hover = true;
                selectedEntry = m;
                break;
            }
        }
        repaint();
        if (selectedEntry != null) {
            setToolTipText(selectedEntry.toolTipText());
        } else {
            setToolTipText(null);
        }

        checkCollision(x, y);
    }
}

