/**
 * FearfulLid es un tipo de tapa que cuando su compañera (copa) no está
 * en la torre, no entra, y si está tapando a su copa, no sale.
 */
public class FearfulLid extends Lid {
    /**
     * Constructor de la tapa de tipo fearful
     */
    public FearfulLid(int value, int towerMaxHeight, int towerHeight, int towerWidth, boolean isVisible, Tower tower) {
        super(value, towerMaxHeight, towerHeight, towerWidth, isVisible, tower);
    }

    /**
     * @return "fearful" Retorna el tipo del objeto como String, en este caso una tapa "miedosa".
     */
    @Override
    public String getType() {
        return "fearful";
    }
}
