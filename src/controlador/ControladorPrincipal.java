package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import model.Zona;
import persistencia.GestorPersistencia;
import vista.MenuPrincipal;

/**
 *
 * @author fta
 */
public class ControladorPrincipal implements ActionListener {

    static private MenuPrincipal menuPrincipal;
    static private final int MAXZONES = 4;
    static private Zona[] zones = new Zona[MAXZONES];
    static private int pZones = 0; //Priemra posició buida del vector zones
    static private Zona zonaActual = null;
    static private int tipusComponent = 0;
    static private GestorPersistencia gp = new GestorPersistencia();
    static private final String[] METODESPERSISTENCIA = {"XML","Serial","JDBC","DB4O"};

    public ControladorPrincipal() {
        /*
        TODO
        
        S'inicialitza l'atribut menuPrincipal (això mostrarà el menú principal)
        A cada botó del menú, s'afegeix aquest mateix objecte (ControladorPrincipal) com a listener
        
         */

        menuPrincipal = new MenuPrincipal();

        //S'AFEGEIX EL CONTROLADOR COM A LISTENER DELS BOTONS
        for (JButton boto : menuPrincipal.getMenuButtons()) {
            boto.addActionListener(this);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*
        TODO

        S'ha de cridar a seleccionarOpcio segons l'opció premuda. Penseu que l'opció es 
        correspon amb la posició que el botó ocupa a l'array de botons de menuPrincipal.
        
         */

        JButton[] botons = menuPrincipal.getMenuButtons();
        for (int i = 0; i < botons.length; i++) {
            if (e.getSource() == botons[i]) {
                seleccionarOpcio(i);
            }
        }
    }

    private void seleccionarOpcio(int opcio) {

        switch (opcio) {
            case 0:
                System.exit(0);
                break;
            case 1:
                menuPrincipal.getFrame().setVisible(false);
                ControladorZona controladorZona = new ControladorZona();
                break;
            case 2:
                menuPrincipal.getFrame().setVisible(false);
                ControladorEstacio controladorEstacio = new ControladorEstacio();
                break;
        }

    }

    public static MenuPrincipal getMenuPrincipal() {
        return menuPrincipal;
    }

    public static void setMenuPrincipal(MenuPrincipal menuPrincipal) {
        ControladorPrincipal.menuPrincipal = menuPrincipal;
    }

    public static int getMAXZONES() {
        return MAXZONES;
    }

    public static String[] getMETODESPERSISTENCIA() {
        return METODESPERSISTENCIA;
    }   

    public static Zona[] getZones() {
        return zones;
    }

    public static void setZones(Zona[] zones) {
        ControladorPrincipal.zones = zones;
    }   

    public static int getpZones() {
        return pZones;
    }

    public static void setpZones() {
        ControladorPrincipal.pZones++;
    }

    public static Zona getZonaActual() {
        return zonaActual;
    }

    public static void setZonaActual(Zona zonaActual) {
        ControladorPrincipal.zonaActual = zonaActual;
    }

    public static int getTipusComponent() {
        return tipusComponent;
    }

    public static void setTipusComponent(int tipusComponent) {
        ControladorPrincipal.tipusComponent = tipusComponent;
    }

    public static GestorPersistencia getGp() {
        return gp;
    }

    public static void setGp(GestorPersistencia gp) {
        ControladorPrincipal.gp = gp;
    }
}
