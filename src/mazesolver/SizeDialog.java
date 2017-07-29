package mazesolver;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author Tom
 */
public class SizeDialog extends Frame implements ActionListener{
    MyFrame parrent;
    
    TextField Collums;
    TextField Rows;
    
    TextField TileSize;
    
    Button OK;
    
    public SizeDialog(MyFrame parrent) {
        this.parrent = parrent;
        
        this.setLocationRelativeTo(this);
        this.setSize(550, 130);
        this.setResizable(false);
        
        Collums = new TextField("Počet stĺpcov, momentálne: " + this.parrent.C.GridSizeX);
        Collums.addActionListener(this);
        Collums.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
               Collums.setText("");
            }
        });
        
        TileSize = new TextField("Velkosť štvorčeka ,pixely, momentálne: " + this.parrent.C.TileSize);
        TileSize.addActionListener(this);
        TileSize.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
               TileSize.setText("");
            }
        });
        
        addWindowListener(new WindowAdapter ()
                                {   public void windowClosing(WindowEvent e) {
                                    System.out.println(getSize());
                                    dispose();
                                    }
                                }
        );
        
        Rows = new TextField("Počet riadkov, momentálne: " +this.parrent.C.GridSizeY);
        Rows.addActionListener(this);
        Rows.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
               Rows.setText("");
            }
        });
        
        OK = new Button("Potvrď");
        OK.addActionListener(this);
        
        Panel P = new Panel();
        Panel P2 = new Panel();
        
        P.add(Collums);
        P.add(Rows);
        P.add(OK);
        
        P2.add(TileSize);
        
        this.add("North",P);
        this.add("Center",P2);
        
        this.setVisible(true);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == OK) {
            
            try {
                parrent.C.GridSizeY = Integer.parseInt(Rows.getText());
            }
            catch (NumberFormatException ex) {
                
            }
            
            try {
                parrent.C.GridSizeX = Integer.parseInt(Collums.getText());
            }
            catch (NumberFormatException ex) {
                
            }
            try {
                parrent.C.TileSize = Integer.parseInt(TileSize.getText());
            }
            catch (NumberFormatException ex) {
                
            }
            
            parrent.C.ClearAll();
            parrent.C.BuildArray();
            parrent.C.repaint();
            dispose();
            
            
        }
    }
    
}
