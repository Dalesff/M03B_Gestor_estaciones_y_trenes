package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import model.Estacio;
import principal.Component;
import vista.EstacioForm;
import vista.EstacioLlista;
import vista.MenuEstacio;

/**
 *
 * @author fta
 */
public class ControladorEstacio implements ActionListener {

    private MenuEstacio menuEstacio;
    private Estacio estacio = null;
    private EstacioForm estacioForm = null;
    private EstacioLlista estacioLlista = null;
    private int opcioSelec = 0;

    public ControladorEstacio() {

        menuEstacio = new MenuEstacio();
        afegirListenersMenu();

    }

    private void afegirListenersMenu() {

        for (JButton boto : menuEstacio.getMenuButtons()) {
            boto.addActionListener(this);
        }

    }

    private void afegirListenersForm() {

        estacioForm.getbDesar().addActionListener(this);
        estacioForm.getbSortir().addActionListener(this);

    }

    private void afegirListenersLlista() {

        estacioLlista.getbSortir().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //Accions per al menú
        JButton[] botons = menuEstacio.getMenuButtons();

        for (int i = 0; i < botons.length; i++) {
            if (e.getSource() == botons[i]) {
                menuEstacio.getFrame().setVisible(false);
                opcioSelec = i;
                seleccionarOpcio(i);
            }
        }

        //Accions per al formulari d'estacions
        if (estacioForm != null) {

            if (e.getSource() == estacioForm.getbDesar()) {

                if (opcioSelec == 1) {//Nova estació

                    ControladorPrincipal.getZonaActual().getComponents().add(new Estacio(estacioForm.gettCodi().getText(), estacioForm.gettNom().getText(), estacioForm.gettPoblacio().getText(), estacioForm.gettAdreca().getText()));

                } else if (opcioSelec == 2) {//Modificar estació

                    estacio.setCodi(estacioForm.gettCodi().getText());
                    estacio.setNom(estacioForm.gettNom().getText());
                    estacio.setPoblacio(estacioForm.gettPoblacio().getText());
                    estacio.setAdreca(estacioForm.gettAdreca().getText());
                    
                }

            } else if (e.getSource() == estacioForm.getbSortir()) { //Sortir

                estacioForm.getFrame().setVisible(false);
                menuEstacio.getFrame().setVisible(true);

            }

        }

        if (estacioLlista != null) {

            if (e.getSource() == estacioLlista.getbSortir()) {

                estacioLlista.getFrame().setVisible(false);
                menuEstacio.getFrame().setVisible(true);

            }

        }

    }

    private void seleccionarOpcio(Integer opcio) {

        switch (opcio) {
            case 0: //sortir
                ControladorPrincipal.getMenuPrincipal().getFrame().setVisible(true);
                break;
            case 1: // alta
                estacioForm = new EstacioForm();
                afegirListenersForm();
                break;
            case 2: // modificar
                int pos = ControladorPrincipal.getZonaActual().selectComponent(1, seleccionarEstacio());
                estacio = (Estacio) ControladorPrincipal.getZonaActual().getComponents().get(pos);
                estacioForm = new EstacioForm(estacio.getCodi(), estacio.getNom(), estacio.getPoblacio(), estacio.getAdreca());
                afegirListenersForm();
                break;
            case 3: // llista
                estacioLlista = new EstacioLlista();
                afegirListenersLlista();
                break;
        }

    }

    private String seleccionarEstacio() {

        int i = 0;

        for (Component component : ControladorPrincipal.getZonaActual().getComponents()) {

            if (component instanceof Estacio) {
                i++;
            }

        }

        String[] codis = new String[i];

        i = 0;

        for (Component component : ControladorPrincipal.getZonaActual().getComponents()) {

            if (component instanceof Estacio) {
                codis[i] = ((Estacio) component).getCodi();
            }

            i++;

        }

        int messageType = JOptionPane.QUESTION_MESSAGE;
        int code = JOptionPane.showOptionDialog(null, "Selecciona una estació", "Selecció d'estació", 0, messageType, null, codis, null);

        if (code != JOptionPane.CLOSED_OPTION) {
            return codis[code];
        }

        return null;
    }
}
