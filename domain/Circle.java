/*
 * To change this license heapder, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author maikel
 */
public class Circle extends Thread {

    private double originX;
    private double originY;
    private double finalX;
    private double finalY;
    
    public Circle() {
        this.originX = new Random().nextInt(800 - 20);
        this.originY = new Random().nextInt(600 - 20);
        this.finalX = new Random().nextInt(800 - 20);
        this.finalY = new Random().nextInt(600 - 20);

    }

    public void draw(GraphicsContext gc) {

        gc.fillRect(originX, originY, 20, 20);

    }//draw

    private int velocidad=(int) (Math.random() * 10 + 1);
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(this.velocidad);
                double dx = finalX - originX;
                if (dx != 0) {
                    double m = (originY - finalY) / (originX - finalX);
                    double b = originY - ((originY - finalY) / (originX - finalX)) * originX;
                    if (finalX > originX) {
                        dx = 1;
                    } else {
                        dx = -1;
                    }

                    originX += dx;
                    originY = Math.round(m * originX + b);

                } // if
                if (originX == finalX && originY == finalY) {
                    this.finalX = new Random().nextInt(800 - 20);
                    this.finalY = new Random().nextInt(600 - 20);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Circle.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
