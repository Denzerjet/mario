import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

public class Circle extends JPanel implements KeyListener {
    // start should be (5, 435)
    private int circleX = 5;
    private int circleY = 435;

    private static int frameWidth = 500;
    private static int frameHeight = 500;

    private int yBottomBoundary = frameHeight - 75;
    private int yTopBoundary = 0;
    private int xRightBoundary = frameWidth - 38;
    private int xLeftBoundary = 0;

    private boolean moveUp = false;
    private boolean moveDown = false;
    private boolean moveRight = false;
    private boolean moveLeft = false;

    private int gravity = 2;

    private Timer timer;

    public Circle() {
        setFocusable(true); // allow JPanel to receive keyboard focus
        addKeyListener(this); // register the JPanel as a keylistener
        setFocusTraversalKeysEnabled(false); // allow simult input

        // init and start timer
        timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applyGravity();
            }
        });
        timer.start();
    }

   @Override
   protected void paintComponent(Graphics g) {
        super.paintComponent(g);

       // set color and draw
       g.setColor(Color.RED);
       g.fillOval(circleX, circleY, 25, 25);
   }

   @Override
   public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

       if (key == KeyEvent.VK_W) {
           // if player is already in the air, do not let them jump mid-air
           // TODO: add ability to jump higher if key initially held longer
           if (circleY >= yBottomBoundary) {
               moveUp = true;
           }
       } else if (key == KeyEvent.VK_S) {
           // moveDown = true;
           // do not let the player move down, will allow them to fall faster when holding down
           // and in the air in the future TODO
       } else if (key == KeyEvent.VK_A) {
           moveLeft = true;
       } else if (key == KeyEvent.VK_D) {
           moveRight = true;
       }

        // update drawing
       updateCirclePosition();
   }

   @Override
   public void keyTyped(KeyEvent e) {
        // do nothing
   }

   @Override
   public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

       if (key == KeyEvent.VK_W) {
           moveUp = false;
       } else if (key == KeyEvent.VK_S) {
           moveDown = false;
       } else if (key == KeyEvent.VK_A) {
           moveLeft = false;
       } else if (key == KeyEvent.VK_D) {
           moveRight = false;
       }

       updateCirclePosition();
   }

   private void updateCirclePosition() {
        if (moveUp && !moveDown) {
            // move up
            if (circleY - 5 >= yTopBoundary && !(circleY < yTopBoundary)) {
                // this was previously 5
                circleY -= 50;
            }

        } else if (moveDown && !moveUp) {
            if (circleY + 5 <= yBottomBoundary) {
                circleY += 5;
            }
        }

        if (moveLeft && !moveRight) {
            if (circleX -5 >= xLeftBoundary) {
                circleX -= 5;
            }
        } else if (moveRight && !moveLeft) {
            if (circleX + 5 <= xRightBoundary) {
                circleX += 5;
            }
        }

        repaint();
   }

   private void applyGravity() {
        if (circleY < yBottomBoundary) {
            circleY += gravity;
        }

        // catch the char if it is somehow falling thru the map
       if (circleY >= yBottomBoundary) {
           circleY = yBottomBoundary;
       }

       repaint();
   }

    public static void main(String[] args) {
        // make the frame
        JFrame frame = new JFrame("mario_level");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameWidth, frameHeight);


        // make and add the circle
        Circle circle = new Circle();
        frame.add(circle);

        frame.setVisible(true);


    }
}