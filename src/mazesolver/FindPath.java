package mazesolver;

import java.awt.Color;
import java.util.Vector;
import sun.misc.Queue;

/**
 *
 * @author Tom
 */
public class FindPath {
    
    public static void FromStartToEnd(Tile start, Tile end, int tileSize, Vector<Vector <Tile> > Array, int offset) {
       
        Queue<Tile> Q = new Queue<>();
        Tile current = null;
        
         for (int i = 0; i < Array.size(); i++) {
            for (int j = 0; j < Array.get(i).size(); j++) {
                Array.get(i).get(j).seen = false;
                Array.get(i).get(j).ancestor = null;
                if (Array.get(i).get(j).C == Color.BLUE)
                    Array.get(i).get(j).C = Color.WHITE;
                
                
            }
         }
        
        start.seen = true;
        Q.enqueue(start);
        
        
        while (!Q.isEmpty()) {
            
            try {
            current = Q.dequeue();
            }
            catch (InterruptedException e) {
                
            }
            
            if (current.C == Color.RED) {
                Tile tmp = current.ancestor;
                
                while (tmp != null && tmp.C != Color.GREEN) {
                    tmp.C = Color.BLUE;
                    tmp = tmp.ancestor;
                }
                
            }
            else {
                int VectorPosX = (current.LeftUp.x - offset)/tileSize; //vektor v hlavnom vektore
                int VectorPosY = (current.LeftUp.y - offset)/tileSize; //hlavný vektor
                
                try {
                    if (Array.get(VectorPosY-1).get(VectorPosX).C != Color.BLACK &&
                            Array.get(VectorPosY-1).get(VectorPosX).seen == false) {
                        Q.enqueue(Array.get(VectorPosY-1).get(VectorPosX));
                        
                        Array.get(VectorPosY-1).get(VectorPosX).ancestor = current;
                        Array.get(VectorPosY-1).get(VectorPosX).seen = true;
                        
                    }
                }
                catch (Exception e) {
                    
                }
                try {
                    if (Array.get(VectorPosY+1).get(VectorPosX).C != Color.BLACK &&
                            Array.get(VectorPosY+1).get(VectorPosX).seen == false) {
                        Q.enqueue(Array.get(VectorPosY+1).get(VectorPosX));
                        
                        Array.get(VectorPosY+1).get(VectorPosX).ancestor = current;
                        Array.get(VectorPosY+1).get(VectorPosX).seen = true;
                    }
                }
                catch (Exception e) {
                    
                }
                try {
                    if (Array.get(VectorPosY).get(VectorPosX-1).C != Color.BLACK &&
                            Array.get(VectorPosY).get(VectorPosX-1).seen == false) {
                        Q.enqueue(Array.get(VectorPosY).get(VectorPosX-1));
                        
                        Array.get(VectorPosY).get(VectorPosX-1).ancestor = current;
                        Array.get(VectorPosY).get(VectorPosX-1).seen = true;
                    }
                }
                catch (Exception e) {
                    
                }
                try {
                    if (Array.get(VectorPosY).get(VectorPosX+1).C != Color.BLACK &&
                            Array.get(VectorPosY).get(VectorPosX+1).seen == false) {
                        Q.enqueue(Array.get(VectorPosY).get(VectorPosX+1));
                        
                        Array.get(VectorPosY).get(VectorPosX+1).ancestor = current;
                        Array.get(VectorPosY).get(VectorPosX+1).seen = true;
                    }
                }
                catch (Exception e) {
                    
                }
                
                
            }
            
            
            
            
            
            
        }
        
        
        
        
    }
    
}
