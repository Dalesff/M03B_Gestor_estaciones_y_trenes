package vista;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author fta
 */
public class MenuZona {

    private JFrame frame;

    private JButton[] menuButtons = new JButton[7];

    private final int AMPLADA = 800;
    private final int ALCADA = 600;

    public MenuZona() {
        
        /*
        TODO
        
        Amb els atributs d'aquesta classe, heu de fer el següent (no afegiu cap listener a cap control)
            
            Heu de crear l'objecte JFrame amb títol "Menú Zones" i layout Grid d'una columna
            Heu de crear els botons del formulari. Cada botó serà un element de l'array de botons amb les següents etiquetes:
                        "0. Sortir"
                        "1. Alta Zona"
                        "2. Seleccionar Zona"
                        "3. Modificar Zona"
                        "4. LListar Zones"
                        "5. Carregar Zona"
                        "6. Desar Zona"
            Heu d'afegir-ho tot al frame
            Heu de fer visible el frame amb l'amplada i alçada assignat als atributs amb d'aquests noms
            Heu de fer que la finestra es tanqui quan l'usuari ho fa amb el control "X" de la finestra
        
         */
        
        //Definició de la finestra del menú
        frame = new JFrame("Menú Zones");
        frame.setLayout(new GridLayout(0, 1));

        //Creació dels botons a la llista
        menuButtons[0] = new JButton("0. Sortir");
        menuButtons[1] = new JButton("1. Alta Zona");
        menuButtons[2] = new JButton("2. Seleccionar Zona");
        menuButtons[3] = new JButton("3. Modificar Zona");
        menuButtons[4] = new JButton("4. LListar Zones");
        menuButtons[5] = new JButton("5. Carregar Zona");
        menuButtons[6] = new JButton("6. Desar Zona");

        //Addició dels botons a la finestra
        for (JButton boto : menuButtons) {
            frame.add(boto);
        }
        
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

    public JButton[] getMenuButtons() {
        return menuButtons;
    }

    public void setMenuButtons(JButton[] menuButtons) {
        this.menuButtons = menuButtons;
    }
}
