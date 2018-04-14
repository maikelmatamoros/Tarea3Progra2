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
import javafx.scene.paint.Color;

/**
 *
 * @author maikel
 */
public class Circle extends Thread {

    private double originX;
    private double originY;
    private double finalX;
    private double finalY;
    private int velocidad;
    private Color color;

    public Circle() {
        this.originX = new Random().nextInt(800 - 20);
        this.originY = new Random().nextInt(600 - 20);
        this.finalX = new Random().nextInt(800 - 20);
        this.finalY = new Random().nextInt(600 - 20);
        this.velocidad = (int) (Math.random() * 10) + 5;
        this.color = Color.rgb(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));
    }
    
    public void draw(GraphicsContext gc) {

        gc.setFill(this.color);
        gc.fillRect(originX, originY, 20, 20);
    }//draw

    @Override
    public void run() {

        while (true) {

            try {
                Thread.sleep(this.velocidad);
                way();
            } catch (InterruptedException ex) {
                Logger.getLogger(Circle.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
  
    private void way() {
        
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
        if (originX == finalX) {
            this.finalX = new Random().nextInt(800 - 20);
            this.finalY = new Random().nextInt(600 - 20);
           
        }
    }
}
