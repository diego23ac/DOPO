/**
 * FearfulLid es un tipo de tapa que cuando su compañera (copa) no está
 * en la torre, no entra, y si está tapando a su copa, no sale.
 */
public class FearfulLid extends Lid {
    /**
     * Constructor de la tapa de tipo fearful
     * 
     * @param value Valor asociado a la tapa
     * @param towerMaxHeight Altura máxima de la torre
     * @param towerWidth Ancho de la torre
     * @param towerHeight Altura de la torre
     * @param isVisible Visibilidad de la torre
     * @param tower Torre a la cual pertenece
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
