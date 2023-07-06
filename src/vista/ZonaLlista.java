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
public class ZonaLlista {
    
    private JFrame frame;
    
    private final int AMPLADA = 600;
    private final int ALCADA = 200;
    
    private JTable tZona;

    private JButton sortir;   
    

    public ZonaLlista() {
        /*
        TODO
        
        Amb els atributs d'aquesta classe, heu de fer el següent (no afegiu cap listener a cap control)
            
            Heu de crear l'objecte JFrame amb títol "Llista de zones" i layout Grid d'una columna
            Heu de crear la taula amb un nou objecte ZonaTableModel com a model
            Heu de crear el botó del formulari
            Heu d'afegir-ho tot al frame
            Heu de fer visible el frame amb l'amplada i alçada dels valors assignat als atributs amb d'aquests noms
            Heu de fer que la finestra es tanqui quan l'usuari ho fa amb el control "X" de la finestra
        
        */ 
        
        //Definició de la finestra del menú
        frame = new JFrame("Llista de Zones");
        frame.setLayout(new GridLayout(0, 1));

        //Creació de la taula en base al model
        tZona = new JTable(new ZonaTableModel());
        

        //Creació dels botons del formulari
        sortir = new JButton("Sortir");

        //Addició del tot el formulari a la finestra
        frame.add(new JScrollPane(tZona));  
        frame.add(sortir);

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

    public JTable gettZona() {
        return tZona;
    }

    public void settZona(JTable tZona) {
        this.tZona = tZona;
    }      
    
    public JButton getSortir() {
        return sortir;
    }

    public void setSortir(JButton sortir) {
        this.sortir = sortir;
    }
}
