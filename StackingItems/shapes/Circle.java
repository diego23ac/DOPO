package shapes;

import java.awt.*;
import java.awt.geom.*;

/**
 * Un círculo que puede ser manipulado y que se dibuja en Canvas.
 * 
 * Hereda de Figure los campos xPosition, yPosition, color, isVisible y todos
 * los métodos de movimiento, visibilidad y color. Sobreescribe draw() con su forma. 
 */
public class Circle extends Figure {

    public static final double PI=3.1416;
    
    private int diameter;

    /**
     * Crea un círculo.
     * 
     * @param diameter Diámetro del círculo
     * @param color Color del rectángulo
     * @param xPos Posición en x
     * @param yPos Posición en y
     */
     public Circle(int diameter,String color,int xPos,int yPos) {
        super(color,xPos,yPos);
        this.diameter = diameter;
    }

    /**
     * Cambia el tamaño del círculo.
     * 
     * @param newDiameter Nuevo diámetro en píxeles. Debe ser >= 0
     */
    public void changeSize(int newDiameter) {
        erase();
        diameter = newDiameter;
        draw();
    }
 
    /**
     * Dibuja el círculo en el canvas con sus especificaciones.
     */
    @Override
    protected void draw() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color,
                new Ellipse2D.Double(xPosition, yPosition, diameter, diameter));
            canvas.wait(10);
        }
    }
}
