package domain;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Shape extends Thread {

    private double originX;
    private double originY;
    private double finalX;
    private double finalY;
    private int velocidad;
    private Color color;
    private int i;

    public Shape(int i) {
        this.i = i;
        this.originX = new Random().nextInt(800 - 20);
        this.originY = new Random().nextInt(600 - 20);
        this.finalX = new Random().nextInt(800 - 20);
        this.finalY = new Random().nextInt(600 - 20);
        this.velocidad = (int) (Math.random() * 10) + 5;
        this.color = Color.rgb(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));
    } // constructor

    public void draw(GraphicsContext gc) {
        gc.setFill(this.color);
        if (this.i % 2 == 0) {
            gc.fillRect(originX, originY, 20, 20);
        } else {
            gc.fillOval(originX, originY, 20, 20);
        }
    } // draw

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(this.velocidad);
                way();
            } catch (InterruptedException ex) {
                Logger.getLogger(Shape.class.getName()).log(Level.SEVERE, null, ex);
            }
        } // while
    } // run

    private void way() {
        double dx = this.finalX - this.originX;
        if (dx != 0) {
            double m = (this.originY - this.finalY) / (this.originX - this.finalX);
            double b = this.originY - ((this.originY - this.finalY) / (this.originX - this.finalX)) * originX;
            if (this.finalX > this.originX) {
                dx = 1;
            } else {
                dx = -1;
            }
            this.originX += dx;
            this.originY = Math.round(m * this.originX + b);
        } // if
        if (this.originX == this.finalX) {
            this.finalX = new Random().nextInt(800 - 20);
            this.finalY = new Random().nextInt(600 - 20);
        }
    } // way

} // fin de la clase
