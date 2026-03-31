
public abstract class StackingItem {
    protected int height;
    protected int value;
    protected String color;
    protected int yBasePosition;
    protected int yTopPosition;
    protected boolean isVisible;
    
    public StackingItem(int value, boolean isVisible) {
        this.value = value;
    }
    
    public int getHeight() { return height; }
    
    public int getBasePosition() { return yBasePosition; }
    
    public int getTopPosition() { return yBasePosition + height; }
    
    public int getValue() { return value; }

    public abstract void moveDown(int value);

    public abstract void makeVisible();

    public abstract void makeInvisible();
    
    public abstract String getType();
}