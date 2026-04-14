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
        tower.pushCup(4);
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
        tower.pushLid(5);
        tower.pushLid(2);
        tower.pushCup("hierarchical", 2);
        tower.pushCup("hierarchical", 3);
    }
}