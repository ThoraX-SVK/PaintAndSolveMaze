package mazesolver;

import java.awt.Color;
import java.awt.Point;

/**
 *
 * @author Tom
 */
public class Tile {
    
    Point LeftUp;
    Point RightDOwn;
    Color C;
    
    Tile ancestor;
    boolean seen;
    
    public Tile(Point LU, Point RD) {
        this.LeftUp = LU;
        this.RightDOwn = RD;
        C = Color.WHITE;
        
        
    }
    
    public boolean isPointInside(Point p) {
        return p.x > LeftUp.x && p.y > LeftUp.y &&
                p.x < RightDOwn.x && p.y < RightDOwn.y;
    }
          
}
