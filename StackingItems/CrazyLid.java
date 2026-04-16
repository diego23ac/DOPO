/**
 * CrazyLid es un tipo de tapa que en lugar de tapar a su copa como
 * haría una tapa normal, se ubica en su base
 */
public class CrazyLid extends Lid {
    /**
     * Constructor de la tapa de tipo crazy
     */
    public CrazyLid(int value, int towerMaxHeight, int towerHeight, int towerWidth, boolean isVisible, Tower tower) {
        super(value, towerMaxHeight, towerHeight, towerWidth, isVisible, tower);
    }
    
    /**
     * @return "crazy" Retorna el tipo del objeto como String, en este caso una tapa "loca".
     */
    @Override
    public String getType() {
        return "crazy";
    }
}
