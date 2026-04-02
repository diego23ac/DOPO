/**
 * Representa un elemento apilable en la torre.
 */
public abstract class StackingItem {
    protected int height;
    protected int value;
    protected String color;
    protected int yBasePosition;
    protected int yTopPosition;
    protected boolean isVisible;
    
    /**
     * Constructor de la clase StackingItem
     * 
     * @param value Valor asociado al item
     * @param isVisible visibilidad de la torre
     */
    public StackingItem(int value, boolean isVisible) {
        this.value = value;
    }
    
    /**
     * @return height La altura del objeto
     */
    public int getHeight() { return height; }
    
    /**
     * @return yBasePosition La posición en y de la base del objeto
     */
    public int getBasePosition() { return yBasePosition; }
    
    /**
     * @return yTopPosition La posición en y de la parte superior del objeto
     */
    public int getTopPosition() { return yBasePosition + height; }
    
    /**
     * @return value El valor asociado al objeto
     */
    public int getValue() { return value; }

    public abstract void moveDown(int value);

    public abstract void makeVisible();

    public abstract void makeInvisible();
    
    public abstract String getType();
}