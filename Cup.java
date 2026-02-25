/**
 * Representa una taza (cup) apilable con número, altura, color y (opcionalmente) una tapa asociada.
 */
public class Cup {
    private static final int RECT_DEFAULT_X = 70;
    private static final int RECT_DEFAULT_Y = 15;

    private int number;
    private int height;
    private String color;

    private Rectangle[] cupRectangles;
    private Lid lid;
    private boolean isVisible;

    /**
     * Crea una taza usando el mismo valor para número y altura.
     * @param height altura lógica de la taza (cm)
     * @param color color de la taza
     */
    public Cup(int height, String color) {
        this.number = height;
        this.height = height;
        this.color = color;
        this.cupRectangles = new Rectangle[3];
        this.lid = null;
        this.isVisible = false;
    }

    /**
     * Crea una taza con número, altura y color definidos explícitamente.
     * @param number número identificador de la taza
     * @param height altura lógica de la taza (cm)
     * @param color color de la taza
     */
    public Cup(int number, int height, String color) {
        this.number = number;
        this.height = height;
        this.color = color;
        this.cupRectangles = new Rectangle[3];
        this.lid = null;
        this.isVisible = false;
    }

    /**
     * Retorna el número identificador de la taza.
     * @return número de la taza
     */
    public int getNumber() {
        return number;
    }

    /**
     * Retorna la altura lógica de la taza.
     * @return altura de la taza (cm)
     */
    public int getHeight() {
        return height;
    }

    /**
     * Retorna el color de la taza.
     * @return color de la taza
     */
    public String getColor() {
        return color;
    }

    /**
     * Retorna la tapa asociada a la taza (si existe).
     * @return objeto Lid asociado o null si no tiene tapa
     */
    public Lid getLid() {
        return lid;
    }

    /**
     * Asocia una tapa a la taza (o la desasocia si se envía null).
     * @param lid tapa a asociar (puede ser null)
     */
    public void setLid(Lid lid) {
        this.lid = lid;
    }

    /**
     * Indica si la taza se encuentra tapada (tiene una tapa asociada).
     * @return true si tiene tapa asociada, false en caso contrario
     */
    public boolean isLided() {
        return lid != null;
    }

    /**
     * Indica si la taza está marcada como visible.
     * @return true si está visible, false si está invisible
     */
    public boolean isVisible() {
        return isVisible;
    }

    /**
     * Borra las figuras gráficas asociadas a la taza del canvas (si existen).
     */
    public void eraseGraphics() {
        if (cupRectangles != null) {
            for (int i = 0; i < cupRectangles.length; i++) {
                if (cupRectangles[i] != null) {
                    cupRectangles[i].makeInvisible();
                    cupRectangles[i] = null;
                }
            }
        }
        isVisible = false;
    }

    /**
     * Dibuja la taza como una "U" usando tres rectángulos (pared izquierda, pared derecha y base).
     * @param xLeft coordenada X (pixeles) del borde izquierdo
     * @param yBottom coordenada Y (pixeles) del borde inferior
     * @param widthPx ancho (pixeles) de la taza
     * @param heightPx alto (pixeles) de la taza
     * @param thicknessPx grosor (pixeles) de paredes y base
     */
    public void drawAt(int xLeft, int yBottom, int widthPx, int heightPx, int thicknessPx) {
        eraseGraphics();

        int yTop = yBottom - heightPx;

        cupRectangles[0] = buildRect(xLeft, yTop, heightPx, thicknessPx, color);
        cupRectangles[1] = buildRect(xLeft + widthPx - thicknessPx, yTop, heightPx, thicknessPx, color);
        cupRectangles[2] = buildRect(xLeft, yBottom - thicknessPx, thicknessPx, widthPx, color);

        isVisible = true;
    }

    /**
     * Crea y configura un rectángulo del paquete shapes en una posición y tamaño específicos.
     * @param x coordenada X (pixeles) del borde izquierdo
     * @param y coordenada Y (pixeles) del borde superior
     * @param h alto (pixeles) del rectángulo
     * @param w ancho (pixeles) del rectángulo
     * @param c color del rectángulo
     * @return rectángulo configurado y visible
     */
    private Rectangle buildRect(int x, int y, int h, int w, String c) {
        Rectangle r = new Rectangle();
        r.changeColor(c);
        r.changeSize(h, w);
        r.moveHorizontal(x - RECT_DEFAULT_X);
        r.moveVertical(y - RECT_DEFAULT_Y);
        r.makeVisible();
        return r;
    }
}
