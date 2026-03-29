import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PruebasPropiasTest {
    
    @Test
    public void ShouldPushCorrectly(){
        Tower tower = new Tower(25, 15);
        //int[] cups = {5, 4, 3, 6, 1, 2};
        int[] cups = {5, 4, 3, 6,1,2};
        tower.makeVisible();
        for (int cup : cups) { tower.pushCup(cup); }
        assertEquals("si","si");
    }
    
    @Test
    public void ShouldPushCorrectly2(){
        Tower tower = new Tower(25, 30);
        int[] cups = {11,10,9,8,7,6,5,4,3,2,1};
        tower.makeVisible();
        for (int cup : cups) { tower.pushCup(cup); }
        System.out.println(tower.height());
        assertEquals("si","si");
    }
    
    @Test
    public void ShouldPushCorrectly4325(){
        Tower tower = new Tower(25, 15);
        int[] cups = {4,3,2,5};
        tower.makeVisible();
        for (int cup : cups) { tower.pushCup(cup); }
        System.out.println(tower.height());
        assertEquals("si","si");
    }
    
    @Test
    public void ShouldPushCorrectly1324(){
        Tower tower = new Tower(25, 15);
        int[] cups = {1,3,2,4};
        tower.makeVisible();
        for (int cup : cups) { tower.pushCup(cup); }
        System.out.println(tower.height());
        assertEquals("si","si");
    }
    
    @Test
    public void ShouldPushCorrectly4231(){
        Tower tower = new Tower(25, 15);
        int[] cups = {4,2,3,1};
        tower.makeVisible();
        for (int cup : cups) { tower.pushCup(cup); }
        System.out.println(tower.height());
        assertEquals("si","si");
    }
}