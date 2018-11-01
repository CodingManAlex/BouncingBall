package ballbounce;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//import java.awt.Color;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

/*
 *
 * @author Alex
 */
public class Ball {

    private double xPos, yPos, xVel, yVel;
    private int radius = 20; //range = 100;
    private double canvasX, canvasY; //camvas size
    public int ballCounter = 0;
    private boolean state;

    public Ball(double xPos, double yPos, double canvasX, double canvasY) {
        this.xPos = xPos - radius / 2;
        this.yPos = yPos - radius / 2;
        this.canvasX = canvasX;
        this.canvasY = canvasY;
        this.xVel = (Math.random() * 10) + 5;
        this.yVel = (Math.random() * 10) + 5;
        state = true;
    }

    public void draw(GraphicsContext gc) {
        // gc.setFill(Color.GRAY);
        //gc.fillOval(xPos -range /2 , yPos - range/ 2 , range, range);
        gc.setStroke(Color.RED);
        gc.strokeRect(xPos - radius * 1.5, yPos - radius * 1.5, radius * 3, radius * 3);

        gc.setFill(Color.BLACK);
        gc.fillOval(xPos - radius / 2, yPos - radius / 2, radius, radius);

        //***REDRAW ZONE TEST BALLS
        gc.setFill(Color.ORANGE);
        gc.fillOval(xPos - radius * 1.5, yPos - radius * 1.5, radius, radius);
        gc.setFill(Color.GREEN);
        gc.fillOval(xPos + radius * 1.5, yPos + radius * 1.5, radius, radius);
    }

    public void update() {
        while (state == true) {
            xPos += xVel;
            yPos += yVel;
            detect();
            System.out.println("UPDATE: " + ballCounter);
            ballCounter++;
            pause(50);
        }

    }

    public void detect() {
        if (xPos < radius / 2 || xPos > canvasX - radius / 2) {
            xVel *= -1;
        }
        if (yPos < radius / 2 || yPos > canvasY - radius / 2) {
            yVel *= -1;
        }
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    private static void pause(int duration) { //For Movement Speed
        try {
            Thread.sleep(duration);
        } catch (InterruptedException ex) {
        }
    }

}
