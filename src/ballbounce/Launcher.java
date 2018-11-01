package ballbounce;

import javafx.scene.control.Button;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
//import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * Use this template to create drawings in FX. Change the name of the class and
 * put your own name as author below. Change the size of the canvas and the
 * window title where marked and add your drawing code where marked.
 *
 * @author Alex
 */
public class Launcher extends Application {

    private final double canvasMaxX = 700, canvasMaxY = 500;
    private Ball ball = new Ball(canvasMaxX / 2, canvasMaxY / 2, canvasMaxX, canvasMaxY);
    public int counter = 0;

    /**
     * Start method (use this instead of main).
     *
     * @param stage The FX stage to draw on
     * @throws Exception
     */
    
    private void canvasClick(MouseEvent e){
        if(ball.getState() == true){
            ball.setState(false);
        }
        else if(ball.getState() == false){
            ball.setState(true);
        }
        System.out.println(ball.getState());
    }
   
            @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root, canvasMaxX, canvasMaxY);
        Canvas canvas = new Canvas(scene.getWidth(), scene.getHeight()); // Set canvas Size in Pixels
        stage.setScene(scene);

        stage.setTitle("BallBounce");
        stage.sizeToScene();
        stage.centerOnScreen();
        stage.show();

        //stage.setScene(scene);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
//         gc.setFill(Color.ALICEBLUE);
//         gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
       canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, this::canvasClick);
                
        Thread t = new Thread(() -> animate(gc));
        Thread update = new Thread(() -> ball.update());
        t.start();
        update.start();

    }

    public void animate(GraphicsContext gc) {
        while (ball.getState()== true) {
            gc.setFill(Color.ALICEBLUE);
            gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
            ball.draw(gc);
            //counter++;
            //System.out.println("animate"+counter);
            pause(4);
        }
    }
    
    public static void pause(int duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException ex) {
        }
    }

    // YOUR CODE STARTS HERE 
    //Ball b = new Ball((int)c.getWidth()/2, (int)c.getHeight()/2);
    // b.draw(gc);
    // YOUR CODE STOPS HERE
    /* int radius = 100;
       
        

        //Top Left
        gc.setFill(Color.RED);
        gc.fillOval(0, 0 ,radius,radius);
        //TopRight
        gc.setFill(Color.GREEN);
        gc.fillOval(c.getWidth()-radius, 0,radius,radius);
        //Bottom Left
        gc.setFill(Color.BLUE);
        gc.fillOval(0, c.getHeight()-radius ,radius,radius);
        //Bottom Rright
        gc.setFill(Color.ORANGE);
        gc.fillOval(c.getWidth()-radius,c.getHeight()-radius ,radius,radius);
       
        gc.setFill(Color.BLACK);
        gc.fillRect(50, 50, c.getWidth()-100, c.getHeight()-100);*/
    //stage.show();
    /**
     * The actual main method that launches the app.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }
}
