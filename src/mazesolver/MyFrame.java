package mazesolver;

import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 *
 * @author Tom
 */
public class MyFrame extends Frame implements ActionListener{
    
    MyCanvas C;
    Button Start;
    Button End;
    Button SearchForPath;
    
    MenuBar MB;
    Menu M1;
    MenuItem Options;
    
    int mod;
    
    public MyFrame(String str) {
        super(str);
        this.setLocationRelativeTo(null);
        this.setSize(600, 600);
        
        addWindowListener(new WindowAdapter ()
                                {   public void windowClosing(WindowEvent e) {
                                    
                                    System.exit(0);
                                    }
                                }
        );
        
        C = new MyCanvas(this);
        this.add(C);
        
        Start = new Button("Vyber začiatočný bod");
        Start.addActionListener(this);
        
        End = new Button("Vyber konečný bod");
        End.addActionListener(this);
       
        SearchForPath = new Button("Najdi cestu");
        SearchForPath.addActionListener(this);
        
        mod = 0;
        
        Panel P = new Panel();
        
        P.add(Start);
        P.add(End);
        P.add(SearchForPath);
        
        MB = new MenuBar();
        this.setMenuBar(MB);
        
        M1 = new Menu("Možnosti");
        Options = new MenuItem("Nastav velkosť");
        Options.addActionListener(this);
        
        M1.add(Options);
        MB.add(M1);
        
        
        this.add("South",P);
        
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Start) {
            if (mod != 1) {
                Start.setBackground(Color.CYAN);
                End.setBackground(null);
                mod = 1;
            }
            else {
                Start.setBackground(null);
                mod = 0;
            }
        }
        
        else if (e.getSource() == End) {
            if (mod != 2) {
                End.setBackground(Color.CYAN);
                Start.setBackground(null);
                mod = 2;
            }
            else {
                End.setBackground(null);
                mod = 0;
            }
          
        }
        else if (e.getSource() == SearchForPath) {
            if (C.StarTile != null && C.EndTile != null) {
                FindPath.FromStartToEnd(C.StarTile, C.EndTile, C.TileSize, C.Array,C.OffsetFromEdge);
                C.repaint();
            }
        }
        else if (e.getSource() == Options) {
            SizeDialog SD = new SizeDialog(this);
        }
    }
    
}
