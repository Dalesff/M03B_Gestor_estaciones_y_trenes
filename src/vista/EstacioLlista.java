package vista;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author fta
 */
public class EstacioLlista {
    
    private JFrame frame;
    
    private final int AMPLADA = 600;
    private final int ALCADA = 200;
    
    private JTable tEstacions;

    private JButton bSortir;   
    

    public EstacioLlista() {
        
        //Definició de la finestra del menú
        frame = new JFrame("Llista d'estacions");
        frame.setLayout(new GridLayout(0, 1));

        //Creació de la taula en base al model
        tEstacions = new JTable(new EstacioTableModel());
        

        //Creació dels botons del formulari
        bSortir = new JButton("Sortir");

        //Addició del tot el formulari a la finestra
        frame.add(new JScrollPane(tEstacions));  
        frame.add(bSortir);

        //Es mostra la finestra amb propietats per defecte
        frame.setSize(AMPLADA, ALCADA);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
       
    }


    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JTable gettEstacions() {
        return tEstacions;
    }

    public void settEstacions(JTable tEstacions) {
        this.tEstacions = tEstacions;
    }   
    
    public JButton getbSortir() {
        return bSortir;
    }

    public void setbSortir(JButton bSortir) {
        this.bSortir = bSortir;
    }
}
