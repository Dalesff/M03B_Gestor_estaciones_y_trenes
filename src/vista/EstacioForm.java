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
public class EstacioForm {

    private JFrame frame;

    private final int AMPLADA = 300;
    private final int ALCADA = 200;

    private JLabel lCodi;
    private JTextField tCodi;
    private JLabel lNom;
    private JTextField tNom;
    private JLabel lPoblacio;
    private JTextField tPoblacio;
    private JLabel lAdreca;
    private JTextField tAdreca;

    private JButton bDesar;
    private JButton bSortir;

    public EstacioForm() {

        //Definició de la finestra del menú
        frame = new JFrame("Formulari Estació");
        frame.setLayout(new GridLayout(0, 1));

        //Creació dels controls del formulari
        lCodi = new JLabel("Codi");
        tCodi = new JTextField(8);
        lNom = new JLabel("Nom");
        tNom = new JTextField(20);
        lPoblacio = new JLabel("Població");
        tPoblacio = new JTextField(20);
        lAdreca = new JLabel("Adreça");
        tAdreca = new JTextField(20);

        //Creació dels botons del formulari
        bDesar = new JButton("Desar");
        bSortir = new JButton("Sortir");

        //Addició del tot el formulari a la finestra
        frame.add(lCodi);
        frame.add(tCodi);
        frame.add(lNom);
        frame.add(tNom);
        frame.add(lPoblacio);
        frame.add(tPoblacio);
        frame.add(lAdreca);
        frame.add(tAdreca);
        frame.add(bDesar);
        frame.add(bSortir);

        //Es mostra la finestra amb propietats per defecte
        frame.setSize(AMPLADA, ALCADA);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public EstacioForm(String codi, String nom, String poblacio, String adreca) {
        this();
        tCodi.setText(codi);
        tNom.setText(nom);
        tPoblacio.setText(poblacio);
        tAdreca.setText(adreca);
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

    public JLabel getlNom() {
        return lNom;
    }

    public void setlNom(JLabel lNom) {
        this.lNom = lNom;
    }

    public JTextField gettNom() {
        return tNom;
    }

    public void settNom(JTextField tNom) {
        this.tNom = tNom;
    }

    public JLabel getlPoblacio() {
        return lPoblacio;
    }

    public void setlPoblacio(JLabel lPoblacio) {
        this.lPoblacio = lPoblacio;
    }

    public JTextField gettPoblacio() {
        return tPoblacio;
    }

    public void settPoblacio(JTextField tPoblacio) {
        this.tPoblacio = tPoblacio;
    }

    public JLabel getlAdreca() {
        return lAdreca;
    }

    public void setlAdreca(JLabel lAdreca) {
        this.lAdreca = lAdreca;
    }

    public JTextField gettAdreca() {
        return tAdreca;
    }

    public void settAdreca(JTextField tAdreca) {
        this.tAdreca = tAdreca;
    }

    public JButton getbDesar() {
        return bDesar;
    }

    public void setbDesar(JButton bDesar) {
        this.bDesar = bDesar;
    }

    public JButton getbSortir() {
        return bSortir;
    }

    public void setbSortir(JButton bSortir) {
        this.bSortir = bSortir;
    }

}

