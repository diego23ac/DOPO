package shapes;

import java.awt.*;

/**
 * Un triángulo que puede ser manipulado y que se dibuja en Canvas.
 * 
 * Hereda de Figure los campos xPosition, yPosition, color, isVisible y todos
 * los métodos de movimiento, visibilidad y color. Sobreescribe draw() con su forma.
 */
public class Triangle extends Figure {
    
    public static int VERTICES=3;
    
    private int height;
    private int width;

    /**
     * Crea un triángulo.
     * 
     * @param height Altura del triángulo
     * @param width Ancho del triángulo
     * @param color Color del triángulo
     * @param xPos Posición en x
     * @param yPos Posición en y
     */
     public Triangle(int height,int width,String color,int xPos,int yPos) {
        super(color,xPos,yPos);
        this.height = height;
        this.width = width;
    }
    
    /**
     * Cambia el tamaño del triángulo.
     * 
     * @param newHeight Nuevo alto en píxeles. Debe ser >= 0
     * @param newWidth  Nuevo ancho en píxeles. Debe ser >= 0
     */
    public void changeSize(int newHeight, int newWidth) {
        erase();
        height = newHeight;
        width = newWidth;
        draw();
    }
 
    /**
     * Dibuja el triángulo en el canvas con sus especificaciones.
     */
    @Override
    protected void draw() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas();
            int[] xpoints = { xPosition, xPosition + (width/2), xPosition - (width/2) };
            int[] ypoints = { yPosition, yPosition + height, yPosition + height };
            canvas.draw(this, color, new Polygon(xpoints, ypoints, 3));
            canvas.wait(10);
        }
    }
}
