package tarea3progra2;

import domain.Circle;
import java.util.ArrayList;
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
    private long espera;
    private int FPS = 30;
    private long tiempo = 1000 / FPS;
    private ArrayList<Circle> list;

    public void initObjects() {
        this.list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            this.list.add(new Circle());
            this.list.get(i).start();
        }

    }

    @Override
    public void run() {
        GraphicsContext gc = this.canvas.getGraphicsContext2D();
        initObjects();
        long start;
        long elapsed;

        while (true) {

            try {
                gc.clearRect(0, 0, 800, 600);
                for (int i = 0; i < 2; i++) {
                    
                    this.list.get(i).draw(gc);
                }

                start = System.nanoTime();
                // llamar a dibujar
                elapsed = System.nanoTime() - start;
                this.espera = this.tiempo - elapsed / 1000000;
                if (this.espera < 0) {
                    this.espera = 5;
                }
                Thread.sleep(this.espera);
            } catch (InterruptedException ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public static void main(String arg[]) {
        launch(arg);
    }

}
