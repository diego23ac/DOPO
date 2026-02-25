/**
 * Representa una tapa (lid) apilable con número, ancho, color y (opcionalmente) una taza asociada.
 */
public class Lid {
    public static final int HEIGHT = 1;

    private static final int RECT_DEFAULT_X = 70;
    private static final int RECT_DEFAULT_Y = 15;

    private int number;
    private int width;
    private String color;

    private Rectangle lidRectangle;
    private Cup cup;
    private boolean isVisible;

    /**
     * Crea una tapa con un ancho lógico, usando valores por defecto para número y color.
     * @param width ancho lógico de la tapa
     */
    public Lid(int width) {
        this.number = -1;
        this.width = width;
        this.color = "black";
        this.lidRectangle = null;
        this.cup = null;
        this.isVisible = false;
    }

    /**
     * Crea una tapa con número, ancho y color definidos.
     * @param number número identificador de la tapa
     * @param width ancho lógico de la tapa
     * @param color color de la tapa
     */
    public Lid(int number, int width, String color) {
        this.number = number;
        this.width = width;
        this.color = color;
        this.lidRectangle = null;
        this.cup = null;
        this.isVisible = false;
    }

    /**
     * Retorna el número identificador de la tapa.
     * @return número de la tapa
     */
    public int getNumber() {
        return number;
    }

    /**
     * Retorna el ancho lógico de la tapa.
     * @return ancho de la tapa
     */
    public int getWidth() {
        return width;
    }

    /**
     * Retorna el color de la tapa.
     * @return color de la tapa
     */
    public String getColor() {
        return color;
    }

    /**
     * Retorna la taza asociada a la tapa (si existe).
     * @return objeto Cup asociado o null si no está asociada
     */
    public Cup getCup() {
        return cup;
    }

    /**
     * Asocia una taza a la tapa (o la desasocia si se envía null).
     * @param cup taza a asociar (puede ser null)
     */
    public void setCup(Cup cup) {
        this.cup = cup;
    }

    /**
     * Indica si la tapa está marcada como visible.
     * @return true si está visible, false si está invisible
     */
    public boolean isVisible() {
        return isVisible;
    }

    /**
     * Borra la figura gráfica asociada a la tapa del canvas (si existe).
     */
    public void eraseGraphics() {
        if (lidRectangle != null) {
            lidRectangle.makeInvisible();
            lidRectangle = null;
        }
        isVisible = false;
    }

    /**
     * Dibuja la tapa como un rectángulo.
     * @param xLeft coordenada X (pixeles) del borde izquierdo
     * @param yTop coordenada Y (pixeles) del borde superior
     * @param widthPx ancho (pixeles) de la tapa
     * @param heightPx alto (pixeles) de la tapa
     */
    public void drawAt(int xLeft, int yTop, int widthPx, int heightPx) {
        eraseGraphics();
        lidRectangle = buildRect(xLeft, yTop, heightPx, widthPx, color);
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
