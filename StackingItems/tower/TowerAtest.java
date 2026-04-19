package tower;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import javax.swing.JOptionPane;
import shapes.Canvas;

/**
 * Clase de pruebas de aceptación del Ciclo 4
 */
public class TowerAtest {

    /**
     * Evidencia visualmente que una copa opener elimina las tapas
     * que le impiden el paso al entrar a la torre.
     */
    @Test
    public void shouldAcceptOpenerCupBehavior() {
        Tower tower = new Tower(25, 15);
        tower.makeVisible();

        tower.pushLid(3);
        Canvas.getCanvas().wait(700);

        tower.pushLid(4);
        Canvas.getCanvas().wait(700);

        tower.pushLid(5);
        Canvas.getCanvas().wait(700);

        tower.pushLid(2);
        Canvas.getCanvas().wait(700);

        tower.pushCup("opener", 3);
        Canvas.getCanvas().wait(1500);

        int answer = JOptionPane.showConfirmDialog(
            null,
            "¿Acepta la prueba?\n\n¿Se observó que la copa opener eliminó las tapas que le impedían el paso?",
            "TowerAtest - OpenerCup",
            JOptionPane.YES_NO_OPTION
        );

        assertEquals(JOptionPane.YES_OPTION, answer);
    }

    /**
     * Evidencia visualmente que una tapa crazy no queda como tapa normal,
     * sino que se ubica en la base de su copa compañera.
     */
    @Test
    public void shouldAcceptCrazyLidBehavior() {
        Tower tower = new Tower(25, 15);
        tower.makeVisible();

        tower.pushLid(3);
        Canvas.getCanvas().wait(700);

        tower.pushCup(4);
        Canvas.getCanvas().wait(700);

        tower.pushLid("crazy", 4);
        Canvas.getCanvas().wait(1500);

        int answer = JOptionPane.showConfirmDialog(
            null,
            "¿Acepta la prueba?\n\n¿Se observó que la tapa crazy no quedó tapando a la copa 4, sino ubicada en su base?",
            "TowerAtest - CrazyLid",
            JOptionPane.YES_NO_OPTION
        );

        assertEquals(JOptionPane.YES_OPTION, answer);
    }
}