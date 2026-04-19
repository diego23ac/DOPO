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
    protected Tower tower;
    protected boolean removable = true;
    
    /**
     * Constructor de la clase StackingItem
     * 
     * @param value Valor asociado al item
     * @param isVisible visibilidad de la torre
     * @param tower Torre a la cual pertenece
     */
    public StackingItem(int value, boolean isVisible, Tower tower) {
        this.value = value;
        this.tower = tower;
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
    
    /**
     * @return true si el objeto es removible, false de lo contrario
     */
    public boolean isRemovable() { return removable; }
    
    /**
     * Cambia el atributo removable al valor booleano deseado
     */
    public void setRemovable(boolean removable) { this.removable = removable; }

    public abstract void moveDown(int value);

    public abstract void makeVisible();

    public abstract void makeInvisible();
    
    public abstract String getType();
}