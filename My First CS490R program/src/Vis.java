import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class Vis extends JPanel implements ActionListener, MouseInputListener {

    public static int table;
    public float yMax; public float yMin; public float xMax; public float xMin;
    public static boolean collision;
    public float verticalLength; public float horizontalLength;
    private Rectangle box;
    private Student mouseDown;

    public Vis() {

        table = 2012; //default table is 2012

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

        if (table == 2012) {

            //draw six lines
            for (int i = 0; i < 6; i++) {
                int x = (int) (i * (getWidth() / 5.25) + 15);
                g.drawLine(x, 10, x, getHeight() - 10);
            }
        } else if (table == 2019) {

            //draw eight lines
            for (int i = 0; i < 8; i++) {
                int x = (int) (i * (getWidth() / 7.25) + 15);
                g.drawLine(x, 10, x, getHeight() - 10);
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
        mouseDown = new Student(x,y);
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

        box.setFrameFromDiagonal(mouseDown.x, mouseDown.y, e.getX(), e.getY());
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

