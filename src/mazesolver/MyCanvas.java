package mazesolver;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;


/**
 *
 * @author Tom
 */
public class MyCanvas extends grafiky.DoubleBuffer implements MouseListener, MouseMotionListener{
    MyFrame F;
    int GridSizeX;
    int GridSizeY;
    
    int TileSize;
    
    int OffsetFromEdge;
    
    Tile tmp;
    Point LU;
    Point RD;
    
    Tile StarTile;
    Tile EndTile;
    
    boolean DragShouldPaintBlack;
    
    Vector<Vector <Tile> > Array;
    
    public MyCanvas(MyFrame F) {
        this.F = F;
        GridSizeX = 10;
        GridSizeY = 10;
        
        TileSize = 30;
        OffsetFromEdge = 50;
        BuildArray();
        
        
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        
        DragShouldPaintBlack = true;
        
    }
    
    public void BuildArray() {
        Array = new Vector<>();
        
        for (int i = 0; i < GridSizeY; i++) {
            Array.add(new Vector<>());
        }
        
        for (int i = 0; i < Array.size();i++) {
            for (int j = 0; j < GridSizeX; j++) {
                LU = new Point(OffsetFromEdge + j*TileSize, OffsetFromEdge + i*TileSize);
                RD = new Point(LU.x + TileSize, LU.y + TileSize);
                tmp = new Tile(LU, RD);
                Array.get(i).add(tmp);
            }
        }
        StarTile = null;
        EndTile = null;
    }
    
    @Override
    public void paintBuffer(Graphics g) {
        g.setColor(Color.BLACK);
        for (int i = 0; i < GridSizeX+1; i++) {
            g.drawLine(OffsetFromEdge + i*TileSize, OffsetFromEdge,
                    OffsetFromEdge + i*TileSize, OffsetFromEdge + GridSizeY*TileSize);
        }
        for (int i = 0; i < GridSizeY+1; i++) {
            g.drawLine(OffsetFromEdge, OffsetFromEdge + i*TileSize,
                    OffsetFromEdge + GridSizeX*TileSize, OffsetFromEdge + i*TileSize);
        }
        
        for (int i = 0; i < Array.size(); i++) {
            for (int j = 0; j < Array.get(i).size(); j++) {
                
                if (Array.get(i).get(j).C == Color.WHITE)
                    continue;
                else {
                    g.setColor(Array.get(i).get(j).C);
                    g.fillRect(Array.get(i).get(j).LeftUp.x, Array.get(i).get(j).LeftUp.y, TileSize, TileSize);
                }
            }
        }
       
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println(e);
        for (int i = 0; i < Array.size(); i++) {
            for (int j = 0; j < Array.get(i).size(); j++) {
                if (Array.get(i).get(j).isPointInside(e.getPoint())) {  
                    
                   if (Array.get(i).get(j).C == Color.BLACK) {
                        
                        if (F.mod == 0) {
                            Array.get(i).get(j).C = Color.WHITE;
                            DragShouldPaintBlack = false;
                        }
                        else if (F.mod == 1) {
                            if (StarTile != null)
                                StarTile.C = Color.WHITE;
                            
                            Array.get(i).get(j).C = Color.GREEN;
                            StarTile = Array.get(i).get(j);
                        }
                        else if (F.mod == 2) {
                            if (EndTile != null)
                                EndTile.C = Color.WHITE;
                            
                            Array.get(i).get(j).C = Color.RED;
                            EndTile = Array.get(i).get(j);
                        }
                    }          
                    else if (Array.get(i).get(j).C == Color.WHITE) {
                        
                        if (F.mod == 0) {
                            Array.get(i).get(j).C = Color.BLACK;
                            DragShouldPaintBlack = true;
                        }
                        else if (F.mod == 1) {
                            if (StarTile != null)
                                StarTile.C = Color.WHITE;
                            
                            Array.get(i).get(j).C = Color.GREEN;
                            StarTile = Array.get(i).get(j);
                        }
                        else if (F.mod == 2) {
                            if (EndTile != null)
                                EndTile.C = Color.WHITE;
                            
                            Array.get(i).get(j).C = Color.RED;
                            EndTile = Array.get(i).get(j);
                        }
                    }
                    else if (Array.get(i).get(j).C == Color.BLUE) {
                        
                        if (F.mod == 0) {
                            Array.get(i).get(j).C = Color.BLACK;
                            DragShouldPaintBlack = true;
                        }
                        else if (F.mod == 1) {
                            if (StarTile != null)
                                StarTile.C = Color.WHITE;
                            
                            Array.get(i).get(j).C = Color.GREEN;
                            StarTile = Array.get(i).get(j);
                        }
                        else if (F.mod == 2) {
                            if (EndTile != null)
                                EndTile.C = Color.WHITE;
                            
                            Array.get(i).get(j).C = Color.RED;
                            EndTile = Array.get(i).get(j);
                        }
                    }
                    else if (Array.get(i).get(j).C == Color.GREEN) {
                        
                        if (F.mod == 0) {
                            Array.get(i).get(j).C = Color.WHITE;
                            StarTile = null;
                        }
                        else if (F.mod == 1) {
                            if (StarTile != null)
                                StarTile.C = Color.WHITE;
                            
                            Array.get(i).get(j).C = Color.GREEN;
                            StarTile = Array.get(i).get(j);
                            
                        }
                        else if (F.mod == 2) {
                            if (EndTile != null) {
                                EndTile.C = Color.WHITE;
                                EndTile = null;
                            }
                            
                            Array.get(i).get(j).C = Color.RED;
                            EndTile = Array.get(i).get(j);
                            StarTile = null;
                        }
                    }
                    else if (Array.get(i).get(j).C == Color.RED) {
                        
                        if (F.mod == 0) {                         
                                Array.get(i).get(j).C = Color.WHITE;
                                EndTile = null;    
                        }
                        else if (F.mod == 1) {
                            
                            if (StarTile != null) {
                                StarTile.C = Color.WHITE;
                                StarTile = null;
                            }
                            
                            Array.get(i).get(j).C = Color.GREEN;
                            StarTile = Array.get(i).get(j);
                            EndTile = null;
                            
                        }
                        else if (F.mod == 2) {
                            if (EndTile != null)
                                EndTile.C = Color.WHITE;
                            
                            Array.get(i).get(j).C = Color.RED;
                            EndTile = Array.get(i).get(j);
                        }
                    }
                    
                    this.repaint();
                    System.out.println(StarTile);
                    System.out.println(EndTile);
                    break;
                }
                
                    
            }
        }
        
        
        
        
    }
    
    public void ClearAll() {
        for (int i = 0; i < Array.size(); i++) {
            Array.get(i).clear();
        }
        Array.clear();
        this.StarTile = null;
        this.EndTile = null;
        
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
        System.out.println(DragShouldPaintBlack);
        if (F.mod == 0) {
            if (DragShouldPaintBlack) {
                for (int i = 0; i < Array.size(); i++) {
                    for (int j = 0; j < Array.get(i).size(); j++) {
                        if (Array.get(i).get(j).isPointInside(e.getPoint())) {  

                           if (Array.get(i).get(j).C == Color.BLACK) {
                               
                                    Array.get(i).get(j).C = Color.BLACK;                   
                            }          
                            else if (Array.get(i).get(j).C == Color.WHITE) {
                                
                                    Array.get(i).get(j).C = Color.BLACK;
                            }
                            else if (Array.get(i).get(j).C == Color.BLUE) {
                                
                                    Array.get(i).get(j).C = Color.BLACK;
                            }
                            else if (Array.get(i).get(j).C == Color.GREEN) { 
                                
                                    Array.get(i).get(j).C = Color.BLACK;
                                    StarTile = null;
                            }
                            else if (Array.get(i).get(j).C == Color.RED) {  
                                
                                        Array.get(i).get(j).C = Color.BLACK;
                                        EndTile = null;     
                            }

                            this.repaint();
                            System.out.println(StarTile);
                            System.out.println(EndTile);
                            break;
                        }
                
                    
                    }
                }
                
            }
            else {
                for (int i = 0; i < Array.size(); i++) {
                    for (int j = 0; j < Array.get(i).size(); j++) {
                        if (Array.get(i).get(j).isPointInside(e.getPoint())) {  

                           if (Array.get(i).get(j).C == Color.BLACK) {
                               
                                    Array.get(i).get(j).C = Color.WHITE;                   
                            }          
                            else if (Array.get(i).get(j).C == Color.WHITE) {
                                
                                    Array.get(i).get(j).C = Color.WHITE;
                            }
                            else if (Array.get(i).get(j).C == Color.BLUE) {
                                
                                    Array.get(i).get(j).C = Color.WHITE;
                            }
                            else if (Array.get(i).get(j).C == Color.GREEN) { 
                                
                                    Array.get(i).get(j).C = Color.WHITE;
                                    StarTile = null;
                            }
                            else if (Array.get(i).get(j).C == Color.RED) {  
                                
                                        Array.get(i).get(j).C = Color.WHITE;
                                        EndTile = null;     
                            }

                            this.repaint();
                            System.out.println(StarTile);
                            System.out.println(EndTile);
                            break;
                        }
                
                    
                    }
                }
                
                
                
                
                
            }
        }
        
        
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
