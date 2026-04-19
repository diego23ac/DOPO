package tower;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Clase de pruebas del Ciclo 4
 */
public class TowerCC4Test {

    /**
     * Verifica que una copa de tipo opener elimine las tapas
     * que le impiden el paso al entrar en la torre.
     */
    @Test
    public void accordingCOShouldDeleteBlockingLidsWhenPushingOpenerCup() {
        Tower tower = new Tower(25, 15);

        tower.pushLid(3);
        tower.pushLid(4);
        tower.pushLid(5);
        tower.pushLid(2);

        tower.pushCup("opener", 3);

        String[][] items = tower.stackingItems();

        assertEquals(1, items.length);
        assertTrue(sameItem(items[0], "opener", "3"));
        assertEquals(5, tower.height());
        assertTrue(tower.ok());
    }

    /**
     * Verifica que una copa de tipo hierarchical desplace
     * hacia arriba los objetos de menor tamaño.
     */
    @Test
    public void accordingORShouldMoveHierarchicalCupBelowSmallerItems() {
        Tower tower = new Tower(25, 15);

        tower.pushCup(4);
        tower.pushLid(4);
        tower.pushCup("hierarchical", 5);

        String[][] items = tower.stackingItems();

        assertEquals(3, items.length);
        assertTrue(sameItem(items[0], "hierarchical", "5"));
        assertTrue(sameItem(items[1], "cup", "4"));
        assertTrue(sameItem(items[2], "lid", "4"));
        assertTrue(tower.ok());
    }

    /**
     * Verifica que una copa de tipo hierarchical no se deje remover
     * cuando logra llegar a la base de la torre.
     */
    @Test
    public void accordingSShouldNotRemoveHierarchicalCupWhenItReachesBase() {
        Tower tower = new Tower(25, 15);

        tower.pushCup(4);
        tower.pushLid(4);
        tower.pushCup("hierarchical", 5);
        tower.removeCup(5);

        String[][] items = tower.stackingItems();

        assertFalse(tower.ok());
        assertEquals(3, items.length);
        assertTrue(sameItem(items[0], "hierarchical", "5"));
    }

    /**
     * Verifica que una tapa de tipo fearful no entre
     * si su copa compañera no está en la torre.
     */
    @Test
    public void accordingBGShouldNotPushFearfulLidWithoutCompanionCup() {
        Tower tower = new Tower(20, 12);

        tower.pushLid("fearful", 4);

        assertFalse(tower.ok());
        assertEquals(0, tower.stackingItems().length);
        assertEquals(0, tower.height());
    }

    /**
     * Verifica que una tapa de tipo fearful no se deje quitar
     * cuando está tapando a su copa compañera.
     */
    @Test
    public void accordingMVShouldNotRemoveFearfulLidWhenItIsCoveringItsCup() {
        Tower tower = new Tower(20, 12);

        tower.pushCup(4);
        tower.pushLid("fearful", 4);
        tower.removeLid(4);

        String[][] items = tower.stackingItems();

        assertFalse(tower.ok());
        assertEquals(2, items.length);
        assertTrue(sameItem(items[0], "cup", "4"));
        assertTrue(sameItem(items[1], "fearful", "4"));
    }

    /**
     * Compara si un item tiene el tipo y valor esperados.
     */
    private boolean sameItem(String[] item, String type, String value) {
        return item[0].equals(type) && item[1].equals(value);
    }
}