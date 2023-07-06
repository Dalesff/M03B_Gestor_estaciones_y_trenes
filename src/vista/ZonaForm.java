package vista;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author fta
 */
public class ZonaForm {
    
    private JFrame frame;
    
    private final int AMPLADA = 300;
    private final int ALCADA = 200;

    private JLabel lCodi;
    private JTextField tCodi;
    private JLabel lResponsable;
    private JTextField tResponsable;

    private JButton desar;   
    private JButton sortir;   

    public ZonaForm() {
        /*
        TODO
        
        Amb els atributs d'aquesta classe, heu de fer el següent (no afegiu cap listener a cap control)
            
            Heu de crear l'objecte JFrame amb títol "Formulari Zona" i layout Grid d'una columna
            Heu de crear els controls del formulari (labels i textFields).
            Heu de crear els botons del formulari
            Heu d'afegir-ho tot al frame
            Heu de fer visible el frame amb l'amplada i alçada dels valors ssignat als atributs amb d'aquests noms
            Heu de fer que la finestra es tanqui quan l'usuari ho fa amb el control "X" de la finestra
       
        */
        
        //Definició de la finestra del menú
        frame = new JFrame("Formulari Zona");
        frame.setLayout(new GridLayout(0, 1));

        //Creació dels controls del formulari
        lCodi = new JLabel("Codi");
        tCodi = new JTextField(20);
        lResponsable = new JLabel("Responsable");
        tResponsable = new JTextField(20);

        //Creació dels botons del formulari
        desar = new JButton("Desar");
        sortir = new JButton("Sortir");

        //Addició del tot el formulari a la finestra
        frame.add(lCodi);
        frame.add(tCodi);
        frame.add(lResponsable);
        frame.add(tResponsable);
        frame.add(desar);       
        frame.add(sortir);

        //Es mostra la finestra amb propietats per defecte
        frame.setSize(AMPLADA, ALCADA);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    public ZonaForm(int codi, String responsable){
        this();
        tCodi.setText(String.valueOf(codi));
        tResponsable.setText(responsable);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JLabel getlCodi() {
        return lCodi;
    }

    public void setlCodi(JLabel lCodi) {
        this.lCodi = lCodi;
    }

    public JTextField gettCodi() {
        return tCodi;
    }

    public void settCodi(JTextField tCodi) {
        this.tCodi = tCodi;
    }

    public JLabel getlResponsable() {
        return lResponsable;
    }

    public void setlResponsable(JLabel lResponsable) {
        this.lResponsable = lResponsable;
    }

    public JTextField gettResponsable() {
        return tResponsable;
    }

    public void settResponsable(JTextField tResponsable) {
        this.tResponsable = tResponsable;
    }   

    public JButton getDesar() {
        return desar;
    }

    public void setDesar(JButton desar) {
        this.desar = desar;
    }

    public JButton getSortir() {
        return sortir;
    }

    public void setSortir(JButton sortir) {
        this.sortir = sortir;
    } 
    
}
