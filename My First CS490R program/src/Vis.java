import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

public class Vis extends JPanel implements ActionListener, MouseInputListener {

    public static boolean collision;
    public float verticalLength; public float horizontalLength;
    private Rectangle box;
    //private Entry mouseDown;

    public Vis() {

        addMouseListener(this);
        addMouseMotionListener(this);

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
        for (Entry e: Main.entries) {

            e.calcPolyLine(Main.getAxes(),getWidth(),getHeight());
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


        if (box != null) {

            //drawing the selection box
            Color color = new Color(103,101,196, 60);
            g.setColor(color);
            g.fillRect(box.x, box.y, box.width, box.height);
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        int x = e.getX();
        int y = e.getY();
        box = new Rectangle();
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

        //box.setFrameFromDiagonal(mouseDown.x, mouseDown.y, e.getX(), e.getY());
        repaint();
    }

    public void checkCollision(int x, int y) {

        collision = false;
    }

    //adding tool tips to each point
    @Override
    public void mouseMoved(MouseEvent e) {

        int x = e.getX();
        int y = e.getY();

        checkCollision(x, y);
    }
}

