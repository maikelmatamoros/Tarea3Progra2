package tarea3progra2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Tarea3Progra2 extends JPanel {

    public Tarea3Progra2() {
        this.setPreferredSize(new Dimension(800, 600));
    } // constructor

    private int numRandom(int n) {
        return (int) (Math.random() * n) + 1;
    } // numRandom

    private void draw(Graphics g) {
        for (int i = 0; i < 1000; i++) {
            g.setColor(new Color(numRandom(255), numRandom(255), numRandom(255)));
            linearFunction(g, numRandom(800), numRandom(600), numRandom(800), numRandom(600));
        } // for
    } // draw

    private void linearFunction(Graphics g, double x0, double y0, double x1, double y1) {
        double dx = x1 - x0;
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
            } // while
        } // if
    }// linearFunction

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g); // se llama al metodo draw
    } // paintComponent

    public static void main(String[] args) {
        JFrame window = new JFrame("Graphing Function");
        window.setContentPane(new Tarea3Progra2());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setResizable(false);
        window.setLocation(149, 100);
        window.setVisible(true);
    } // main

} // fin clase

