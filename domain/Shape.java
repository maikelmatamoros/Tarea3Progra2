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
    private int speed;
    private Color color;
    private int i;
    private int aux;

    public Shape(int i) {
        this.i = i;
        this.aux = 0;
        this.originX = new Random().nextInt(1200 - 20);
        this.originY = new Random().nextInt(700 - 20);
        this.finalX = new Random().nextInt(1200 - 20);
        this.finalY = new Random().nextInt(700 - 20);
        this.speed = (int) (Math.random() * 10) + 5;
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
                Thread.sleep(this.speed);
                route();
            } catch (InterruptedException ex) {
                Logger.getLogger(Shape.class.getName()).log(Level.SEVERE, null, ex);
            }
        } // while
    } // run

    private void route() { // crea la ruta del punto (originX,originY) al punto(finalX,finalY)
        double hipotenusa = Math.sqrt(Math.pow(this.finalX - this.originX, 2) + Math.pow(this.finalY - this.originY, 2));
        this.originX += (this.finalX - this.originX) / hipotenusa;
        this.originY += (this.finalY - this.originY) / hipotenusa;

        if (originY > finalY) {
            aux = 1;
        }
        if (aux == 1 && this.originY <= this.finalY) {
            this.finalX = new Random().nextInt(1200 - 20);
            this.finalY = new Random().nextInt(700 - 20);
            aux = 0;
        } else if (aux == 0 && this.originY >= this.finalY) {
            this.finalX = new Random().nextInt(1200 - 20);
            this.finalY = new Random().nextInt(700 - 20);
            aux = 0;
        }
    } // route

} // fin de la clase
