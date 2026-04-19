package shapes;

/**
 * Un rectángulo que puede ser manipulado y que se dibuja en Canvas.
 * 
 * Hereda de Figure los campos xPosition, yPosition, color, isVisible y todos
 * los métodos de movimiento, visibilidad y color. Sobreescribe draw() con su forma.
 */
public class Rectangle extends Figure {

    public static int EDGES = 4;
    
    private int height;
    private int width;

    /**
     * Crea un rectángulo.
     * 
     * @param height Altura del rectángulo
     * @param width Ancho del rectángulo
     * @param color Color del rectángulo
     * @param xPos Posición en x
     * @param yPos Posición en y
     */
     public Rectangle(int height,int width,String color,int xPos,int yPos) {
        super(color,xPos,yPos);
        this.height = height;
        this.width = width;
    }
    
    /**
     * Cambia el tamaño al nuevo tamaño.
     * 
     * @param newHeight La altura nueva en pixeles. newHeight debe ser >=0.
     * @param newWidht El ancho nuevo en pixeles. newWidth debe ser >=0.
     */
    public void changeSize(int newHeight, int newWidth) {
        erase();
        height = newHeight;
        width = newWidth;
        draw();
    }
    
    /**
     * Dibuja el rectángulo en el canvas con sus especificaciones.
     */
    @Override
    protected void draw() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color,
                new java.awt.Rectangle(xPosition + 80, yPosition + 80, width, height));
            canvas.wait(2);
        }
    }
}

