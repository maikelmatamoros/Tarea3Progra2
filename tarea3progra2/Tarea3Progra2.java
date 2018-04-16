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
        if (y0 > y1) {
            double aux = y0;
            y0 = y1;
            y1 = aux;
        }
        while (y0 <= y1) {
            double hypotenuse = Math.sqrt(Math.pow(x1 - x0, 2) + Math.pow(y1 - y0, 2));
            x0 += (x1 - x0) / hypotenuse;
            y0 += (y1 - y0) / hypotenuse;
            g.drawLine((int) x0, (int) y0, (int) x0, (int) y0);
        } // while
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

