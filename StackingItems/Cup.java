public class Cup {
    private int value;
    private int height;
    private String color;
    private int yBasePosition;
    private Rectangle cupRectangle[];
    private Lid lid;
    private boolean isVisible;

    public Cup(int value,int towerMaxHeight,int towerWidth,int towerHeight,boolean isVisible) {
        this.value = value;
        this.height = 2*value -1;
        this.assignColor(value);
        this.cupRectangle = new Rectangle[3];
        this.createCup(towerMaxHeight,towerWidth, towerHeight);
        if (isVisible) { makeVisible(); }
    }
    
    public void moveDown(int value){
        for(int i = 0; i < 3; i++){
            if (cupRectangle[i] != null) {
                cupRectangle[i].moveDown(value);
            }
        }
        yBasePosition += value * 20;
    }

    public void makeVisible(){
        isVisible = true;
        for(int i = 0; i < 3; i++){
            if (cupRectangle[i] != null) {
                cupRectangle[i].makeVisible();
            }
        }
    }
    
    public void makeInvisible() {
        for(int i = 0; i < 3; i++) {
            if (cupRectangle[i] != null) {
                cupRectangle[i].makeInvisible();
            }
        }
    }

    public int getHeight() { return height; }
    
    public int getBasePosition() { return yBasePosition; }
    
    public int getValue() { return value; }
    
    private void assignColor(int value) {
        String[] colors = {"blue","green","red","yellow","magenta","black"};
        String color = colors[(value - 1) % 5];
        this.color = color;
    }
    
    private void createCup(int towerMaxHeight, int towerWidth, int towerHeight) {
        int middle = (towerWidth*20 - height*20)/2 +22;
        if (height > 1) {
            Rectangle base = new Rectangle(20, height*20, color,middle,towerMaxHeight*20 - 20 - towerHeight*20);
            Rectangle ladoA = new Rectangle(height*20, 20, color,middle,towerMaxHeight*20 - height*20 - towerHeight*20);
            Rectangle ladoB = new Rectangle(height*20, 20, color, middle + height*20-20, towerMaxHeight*20 - height*20 - towerHeight*20);
            cupRectangle[0] = base;
            cupRectangle[1] = ladoA;
            cupRectangle[2] = ladoB;
        } else {
            Rectangle base = new Rectangle(20, height*20, color, middle, towerMaxHeight*20 - 20 - towerHeight*20);
            cupRectangle[0] = base;
        }
        yBasePosition = 280 - towerHeight*20;
    }
}