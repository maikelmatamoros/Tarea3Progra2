package tarea3progra2;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Window extends Application implements Runnable {

    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private Pane pane;
    private Scene scene;
    private Canvas canvas;
    private Thread thread;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Random Graphics");
        initComponents(primaryStage);
        primaryStage.show();
    }

    private void initComponents(Stage primaryStage) {
        this.pane = new Pane();
        this.scene = new Scene(this.pane, WIDTH, HEIGHT);
        this.canvas = new Canvas(WIDTH, HEIGHT);

        this.thread = new Thread(this);
        this.thread.start();

        this.pane.getChildren().add(this.canvas);
        primaryStage.setScene(this.scene);
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });
    }

    private void myDraw(GraphicsContext gc) {
        Random rand = new Random();
        int xSalida, ySalida, xLlegada, yLllegada;
        xSalida = 0;
        ySalida = 0;
        while (true) {
            xLlegada = rand.nextInt(WIDTH - 100) + 1;
            yLllegada = rand.nextInt(WIDTH - 100) + 1;
            trayectoria(gc, xSalida, ySalida, xLlegada, yLllegada);
            xSalida = xLlegada;
            ySalida = yLllegada;
        }
    } // myDraw

    private void trayectoria(GraphicsContext gc, double x0, double y0, double x1, double y1) {
        double dx = x1 - x0;
        if (dx != 0) {
            double m = (y0 - y1) / (x0 - x1);
            double b = y0 - ((y0 - y1) / (x0 - x1)) * x0;
            if (x1 > x0) {
                dx = 1;
            } else {
                dx = -1;
            }
            while (x0 != x1) {
                try {
                    gc.clearRect(0, 0, WIDTH, HEIGHT);
                    x0 += dx;
                    y0 = Math.round(m * x0 + b);
                    gc.fillRect(x0, y0, 20, 20);
                    Thread.sleep(15);
                } // while
                catch (InterruptedException ex) {
                    Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } // if
    }// linearFunction

    @Override
    public void run() {
        GraphicsContext gc = this.canvas.getGraphicsContext2D();
        myDraw(gc);
    }

}
