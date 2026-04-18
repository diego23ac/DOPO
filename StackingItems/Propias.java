import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Propias {
    @Test
    public void ShouldMakeAnOpenerCup() {
        Tower tower = new Tower(21, 12);
        tower.makeVisible();
        tower.pushLid(3);
        tower.pushLid(4);
        tower.pushLid(5);
        tower.pushLid(2);
        tower.pushCup("opener", 3);
        
        tower.pushLid(2);
        tower.pushLid(5);
        tower.pushCup("opener", 2);
        tower.pushLid(4);
    }
    
    @Test
    public void ShouldMakeAnHierarchicalCup() {
        Tower tower = new Tower(21, 12);
        tower.makeVisible();
        tower.pushLid(3);
        tower.pushCup(4);
        tower.pushLid(4);
        tower.pushCup("hierarchical", 5);
        tower.pushCup("hierarchical", 6);
        tower.removeCup(6);
        tower.removeCup(5);
    }
    
    @Test
    public void ShouldMakeAFearfulLid() {
        Tower tower = new Tower(21, 12);
        tower.makeVisible();
        tower.pushLid(3);
        tower.pushCup(4);
        tower.pushLid(4);
        tower.pushCup(5);
        tower.pushLid("fearful", 5);
        tower.popCup();
    }
    
    @Test
    public void ShouldMakeACrazyLid() {
        Tower tower = new Tower(21, 12);
        tower.makeVisible();
        tower.pushLid(3);
        tower.pushCup(4);
        tower.pushLid("crazy", 4);
    }
    
    @Test
    public void ShouldPushCorrecly() {
        Tower tower = new Tower(21, 12);
        tower.makeVisible();
        tower.pushCup(4);
        tower.pushCup(3);
        tower.pushCup(5);
        tower.pushLid(2);
        tower.pushLid(1);
        tower.pushCup(2);
    }
    
    @Test
    public void accordingCOShouldReorderItemsUsingThreeSwaps(){
        Tower tower = new Tower(25, 15);
        tower.makeVisible();
        int[] cups = {1, 2, 3, 4};
        int[] lids = {4, 1, 3, 2};
        for (int cup : cups) { tower.pushCup(cup); }
        
        for (int lid : lids) { tower.pushLid(lid); }
        
        tower.swap(new String[]{"cup","4"}, new String[]{"lid","1"});
        tower.swap(new String[]{"cup","2"}, new String[]{"lid","3"});
        tower.swap(new String[]{"lid","4"}, new String[]{"cup","1"});
        
        /*
        assertTrue(sameItem(items[0], "lid", "4"));
        assertTrue(sameItem(items[1], "lid", "3"));
        assertTrue(sameItem(items[2], "cup", "3"));
        assertTrue(sameItem(items[3], "lid", "1"));
        assertTrue(sameItem(items[4], "cup", "1"));
        assertTrue(sameItem(items[5], "cup", "4"));
        assertTrue(sameItem(items[6], "cup", "2"));
        */
    }
}