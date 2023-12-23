import javax.swing.*;
import java.awt.*;

public class Circle extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Set the color and draw a filled circle
        g.setColor(Color.BLUE);
        g.fillOval(100, 100, 100, 100); // Parameters: x, y, width, height
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Circle Drawing Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(2000, 1050);

        Circle circlePanel = new Circle();
        frame.add(circlePanel);

        frame.setVisible(true);
    }
}
