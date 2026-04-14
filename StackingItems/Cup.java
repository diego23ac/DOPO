public class Cup extends StackingItem{
    private Rectangle cupRectangle[];
    private Lid lid;

    public Cup(int value,int towerMaxHeight,int towerWidth,int towerHeight,boolean isVisible,Tower tower) {
        super(value, isVisible, tower);
        this.height = 2*value -1;
        this.assignColor(value);
        this.cupRectangle = new Rectangle[3];
        this.create(towerMaxHeight,towerWidth, towerHeight);
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
        isVisible = false;
        for(int i = 0; i < 3; i++) {
            if (cupRectangle[i] != null) {
                cupRectangle[i].makeInvisible();
            }
        }
    }
    
    /**
     * @return "cup" Retorna el tipo del objeto como String, en este caso una copa.
     */
    public String getType() { return "cup"; }
    
    /**
     * @param value Asigna un color a la copa según su valor.
     */
    private void assignColor(int value) {
        String[] colors = {"CPink","CGreen","CRed","CBlue","CYellow","black"};
        String color = colors[(value - 1) % 5];
        this.color = color;
    }
    
    /**
     * Método encargado de crear la copa
     * 
     * Crea tres rectángulos que son los dos lados y la base de la copa. 
     * Las dimensiones de la copa dependen del valor asociado (value). 
     * Ubica la copa en la mitad de la torre y sobre la altura especificada.
     * 
     * Hay una excepción y es cuando el valor es 1, ya que se hace un solo rectángulo,
     * no tres.
     * 
     * @param towerMaxHeight Altura máxima de la torre
     * @param towerWidth Ancho de la torre
     * @param towerHeight Altura sobre la cual se va a ubicar la copa
     */
    private void create(int towerMaxHeight, int towerWidth, int towerHeight) {
        int middle = 10*(towerWidth - height) + 22;
        if (height > 1) {
            Rectangle base = new Rectangle(20, height*20, color,middle,20*(towerMaxHeight - towerHeight - 1));
            Rectangle ladoA = new Rectangle(height*20, 20, color,middle,20*(towerMaxHeight - towerHeight - height));
            Rectangle ladoB = new Rectangle(height*20, 20, color, middle + height*20 - 20, 20*(towerMaxHeight - towerHeight - height));
            cupRectangle[0] = base;
            cupRectangle[1] = ladoA;
            cupRectangle[2] = ladoB;
        } else {
            Rectangle base = new Rectangle(20, height*20, color, middle, 20*(towerMaxHeight - towerHeight - 1));
            cupRectangle[0] = base;
        }
        yBasePosition = towerHeight;
        //System.out.println("Copa: " + value + ", PosicionBase: " + yBasePosition + ", PosicionTope: " + this.getTopPosition());
    }
}