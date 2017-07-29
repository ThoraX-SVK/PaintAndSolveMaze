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
        
        preparation(Array);
        
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
                /*
                
       Toto je začiatok java okna (suradnice [0;0])
                |
                v
                |-------------------------------------------------
                |          Začiatok mriežky (zároveň aj súradniceprvého štvorca)     
                |               |
                |               v
                |   Offset      [][][][]
                |<------------->[][][][]
                |               [][][][]
                |
                |   tileSize je velkosť strany štvorca.
                
                *    Takže pre prvý štvorec (uplne v lavo hore) platí:
                *        x: 50
                *        Y: 50
                *        Offset: 50
                *                   
                *        Dostaneme: že prvý štvorec sa nachádza vo vektore na
                *        pozícii [0][0]
                *
                */
                int VectorPosX = (current.LeftUp.x - offset)/tileSize; //vektor v hlavnom vektore
                int VectorPosY = (current.LeftUp.y - offset)/tileSize; //hlavný vektor
                
                /**
                 *  0 1 2 3 4 5 6
                 * 0
                 * 1    ?
                 * 2  ? x ?
                 * 3    ?
                 * 4
                 * 5
                 * 6
                 *      Ak sme v bode x [2;2] je potrebne prehladať susedne body
                 *      [1;2],[2;3],[3;2],[2;1]
                 * 
                 */
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
    
    private static void preparation(Vector<Vector <Tile> > Array) {
        for (int i = 0; i < Array.size(); i++) {
            for (int j = 0; j < Array.get(i).size(); j++) {
                Array.get(i).get(j).seen = false;
                Array.get(i).get(j).ancestor = null;
                if (Array.get(i).get(j).C == Color.BLUE)
                    Array.get(i).get(j).C = Color.WHITE;
                
                
            }
         }
    }
    
}
