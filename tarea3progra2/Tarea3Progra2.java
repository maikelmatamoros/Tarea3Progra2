/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea3progra2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Nelson
 */
public class Tarea3Progra2 extends JPanel {

    public Tarea3Progra2() {
        this.setPreferredSize(new Dimension(800, 600));
    } // constructor

    private void draw(Graphics g) {
        // dibujar los ejes del plano cartesiano
        g.setColor(Color.red);

        g.setColor(Color.black);
//        linearFunction(g, 110, 600, 100, 400);
        for(int i=0;i<1000;i++){
            int x0=(int) (Math.random() * 800 + 1);
            int y0=(int) (Math.random() * 600 + 1);
            int x1=(int) (Math.random() * 800 + 1);
            int y1=(int) (Math.random() * 600 + 1);
            linearFunction(g, x0, y0, x1, y1);
            
        }
        

    } // draw

    // funcion lineal f(x) = m x + b
    // x1 y x2 es el rango en el que se graficara la funcion
    private void linearFunction(Graphics g, double x0, double y0, double x1, double y1) {

        double dx = x1 - x0;
        
        //double punto;
        g.drawLine((int) x0, (int) y0, (int) x1, (int) y1);
        if (dx != 0) {
            double m = (y0 - y1) / (x0 - x1);
            double b = y0 - ((y0 - y1) / (x0 - x1)) * x0;
            if (x1 > x0) {
                dx = 1;
            } else {
                dx = -1;
            }
            while (x0 != x1) {
                x0 += dx;
                y0 = Math.round(m * x0 + b);
                g.drawLine((int) x0, (int) y0, (int) x0, (int) y0);
            }
        }

    }// linearFunction

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // se llama al meto draw
        draw(g);

    }

    public static void main(String[] args) {
        System.out.println("Hola Mundo!! XD");
        
        JFrame window = new JFrame("Graphing Function");
        window.setContentPane(new Tarea3Progra2());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setResizable(false);
        window.setLocation(149, 100);
        window.setVisible(true);
    }

} // fin clase

