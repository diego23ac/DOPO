package shapes;

public abstract class Figure {
    
    protected int xPosition;
    protected int yPosition;
    protected String color;
    protected boolean isVisible;
    
    /**
     * Constructor de la clase Figure
     */
     public Figure(String color,int xPos,int yPos) {
        this.xPosition = xPos;
        this.yPosition = yPos;
        this.color = color;
        isVisible = false;
    }
    
    /**
     * Hace la figura visible, si ya era visible, no hace nada.
     */
    public void makeVisible() {
        isVisible = true;
        draw();
    }
    
    /**
     * Hace la figura invisible, si ya era invisible, no hace nada.
     */
    public void makeInvisible() {
        erase();
        isVisible = false;
    }
    
    /**
     * Mueve la figura unos pocos pixeles a la derecha.
     */
    public void moveRight() {
        moveHorizontal(20);
    }

    /**
     * Mueve la figura unos pocos pixeles a la izquierda.
     */
    public void moveLeft() {
        moveHorizontal(-20);
    }

    /**
     * Mueve la figura unos pocos pixeles a la arriba.
     */
    public void moveUp() {
        moveVertical(-20);
    }

    /**
     * Mueve la figura unos pocos pixeles a la abajo, según el valor.
     * 
     * @param value Valor de las unidades que se va a mover abajo la figura
     */
    public void moveDown(int value) {
        moveVertical(20 * value);
    }

    /**
     * Mueve la figura horizontalmente.
     * 
     * @param distance La distancia deseada en pixeles
     */
    public void moveHorizontal(int distance) {
        erase();
        xPosition += distance;
        draw();
    }

    /**
     * Mueve la figura verticalmente.
     * 
     * @param distance La distancia deseada en pixeles
     */
    public void moveVertical(int distance){
        erase();
        yPosition += distance;
        draw();
    }

    /**
     * Mueve lentamente la figura de manera horizontal.
     * 
     * @param distance La distancia deseada en pixeles
     */
    public void slowMoveHorizontal(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            xPosition += delta;
            draw();
        }
    }

    /**
     * Mueve lentamente la figura de manera vertical.
     * 
     * @param distance La distancia deseada en pixeles
     */
    public void slowMoveVertical(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            yPosition += delta;
            draw();
        }
    }
    
    /**
     * Cambia el color de la figura. 
     * @param color El nuevo color. Los colores válidos son " "CRed", "black", "CBlue", 
     * "CYellow", "CGreen", "CPink", "white", "LBlue", "LYellow", "LGreen", "LPink", 
     * "LRed" y "LYellowDiamond"
     */
    public void changeColor(String newColor){
        color = newColor;
        draw();
    }

    /**
     * Dibuja la figura en el canvas con sus especificaciones actuales.
     */
    protected abstract void draw();
 
    /**
     * Borra la figura del canvas.
     */
    protected void erase() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
}