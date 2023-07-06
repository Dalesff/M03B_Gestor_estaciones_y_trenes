package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import model.Zona;
import persistencia.GestorDB4O;
import persistencia.GestorJDBC;
import persistencia.GestorPersistencia;
import persistencia.GestorSerial;
import persistencia.GestorXML;
import principal.GestorLiniesTrensException;
import vista.ZonaForm;
import vista.ZonaLlista;
import vista.MenuZona;

/**
 *
 * @author fta
 */
public class ControladorZona implements ActionListener {

    private MenuZona menuZona;
    private ZonaForm zonaForm = null;
    private ZonaLlista zonaLlista = null;
    private int opcioSelec = 0;

    public ControladorZona() {

        /*
        TODO
        
        S'inicialitza l'atribut menuZona (això mostrarà el menú de zones)
        Es crida a afegirListenersMenu
        
         */
        
        menuZona = new MenuZona();
        afegirListenersMenu();

    }

    //El controlador com a listener dels controls de les finestres que gestionen les zones
    //S'AFEGEIX EL CONTROLADOR COM A LISTENER DELS BOTONS DEL MENU
    private void afegirListenersMenu() {
        /*
        TODO
        
        A cada botó del menú de zones, s'afegeix aquest mateix objecte (ControladorZona) com a listener
        
         */
        
        for (JButton boto : menuZona.getMenuButtons()) {
            boto.addActionListener(this);
        }

    }

    //S'AFEGEIX EL CONTROLADOR COM A LISTENER DELS BOTONS DEL FORMULARI
    private void afegirListenersForm() {
        /*
        TODO
        
        A cada botó del formulari de la zona, s'afegeix aquest mateix objecte (ControladorZona) com a listener
        
         */
        
        zonaForm.getDesar().addActionListener(this);
        zonaForm.getSortir().addActionListener(this);

    }

    //S'AFEGEIX EL CONTROLADOR COM A LISTENER DEL BOTO DE LA LLISTA
    private void afegirListenersLlista() {
        /*
        TODO
        
        Al botó de sortir de la llista de zones, s'afegeix aquest mateix objecte (ControladorZona) com a listener
        */
        
        zonaLlista.getSortir().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*
        TODO
        
        Nota:
            Com ControladorZona és listener del menú de zones, del formulari i de la llista, llavors en aquest mètode
            actionPerformed heu de controlar si l'usuari ha premut algun botó de qualsevol dels esmentats frames.
            Ull! En el cas del formulari i de la llista, com provenen del menú (els llança el menú de zones), heu de verificar
            primer que els objectes zonaForm o zonaLlista no són nulls, per tal de saber si podeu comparar-los amb
            alguns dels botons d'aquests frames.
        
        Accions per al menú:
            S'ha de cridar a seleccionarOpcio segons l'opció premuda. Penseu que l'opció es correspon amb
            la posició que el botó ocupa a l'array de botons de menuZones
            També, heu d'actualitzar la propietat opcioSelec (amb l'opció que ha premut l'usuari)
        
        Accions per al formulari de zona:
            
            ---- DESAR ----
            Si el botó premut per l'usuari és el botó de desar del formulari de zones, llavors
                Si l'opció seleccionada (al menú de zones) és 1 (alta), llavors:  
                      - Es crea un nou objecte Zona amb les dades del formulari.
                      - S'afegeix la zona creada al vector de ControladorPrincipal
                      - Es posa aquesta zona com zonaActual (de ControladorPrincipal) i es canvia l'atribut
                        opcioSelec a 2.
                Si l'opció seleccionada (al menú de zones) és 3 (modificació), llavors:
                      - Es modifica l'objecte zona amb les dades del formulari (penseu que és la zonaActual de ControladorPrincipal)
                        Nota: no es validen dades amb aquesta opció (per simplificar)
            ---- SORTIR ----
            Si el botó premut per l'usuari és el botó de sortir del formulari de zones, llavors:
                      - Heu de tornar al menú de zones (i amagar el formulari)
        
        Accions per a la llista de zones:
            
            ---- SORTIR ----
            Si el botó premut per l'usuari és el botó de sortir de la llista de zones, llavors
                Heu de tornar al menú de zones (i amagar la llista)
         
         */
        
         //Accions per al menú
        JButton[] botons = menuZona.getMenuButtons();

        for (int i = 0; i < botons.length; i++) {
            if (e.getSource() == botons[i]) {
                menuZona.getFrame().setVisible(false);
                opcioSelec = i;
                seleccionarOpcio(i);
            }
        }

        //Accions per al formulari de zones
        if (zonaForm != null) {

            if (e.getSource() == zonaForm.getDesar()) {

                if (opcioSelec == 1) {//Nova zona

                        Zona zona = new Zona(zonaForm.gettResponsable().getText());
                        ControladorPrincipal.getZones()[ControladorPrincipal.getpZones()] = zona;
                        ControladorPrincipal.setpZones();
                        zonaForm.gettCodi().setText(String.valueOf(zona.getCodi()));
                        ControladorPrincipal.setZonaActual(zona);
                        opcioSelec = 2;

                } else if (opcioSelec == 3) {//Modificar zona

                    ControladorPrincipal.getZonaActual().setResponsable(zonaForm.gettResponsable().getText());

                }

            } else if (e.getSource() == zonaForm.getSortir()) { //Sortir

               zonaForm.getFrame().setVisible(false);
                menuZona.getFrame().setVisible(true);

            }

        }

        if (zonaLlista != null) {

            if (e.getSource() == zonaLlista.getSortir()) {

                zonaLlista.getFrame().setVisible(false);
                menuZona.getFrame().setVisible(true);

            }

        }

    }

    private void seleccionarOpcio(Integer opcio) {

        switch (opcio) {

            case 0: //sortir
                ControladorPrincipal.getMenuPrincipal().getFrame().setVisible(true);
                break;

            case 1: // alta
                if (ControladorPrincipal.getpZones()< ControladorPrincipal.getMAXZONES()) {
                    zonaForm = new ZonaForm();
                    zonaForm.gettCodi().setEnabled(false);
                    afegirListenersForm();
                } else {
                    menuZona.getFrame().setVisible(true);
                    JOptionPane.showMessageDialog(menuZona.getFrame(), "Màxim nombre de zones assolit.");
                }
                break;

            case 2: //seleccionar
                menuZona.getFrame().setVisible(true);
                if (ControladorPrincipal.getZones()[0] != null) {
                    seleccionarZona();
                } else {
                    JOptionPane.showMessageDialog(menuZona.getFrame(), "Abans s'ha de crear al menys una zona");
                }
                break;

            case 3: //modificar
                if (ControladorPrincipal.getZones()[0] != null) {
                    seleccionarZona();
                    zonaForm = new ZonaForm(ControladorPrincipal.getZonaActual().getCodi(), ControladorPrincipal.getZonaActual().getResponsable());
                    zonaForm.gettCodi().setEnabled(false);
                    afegirListenersForm();
                } else {
                    menuZona.getFrame().setVisible(true);
                    JOptionPane.showMessageDialog(menuZona.getFrame(), "Abans s'ha de crear al menys una zona");
                }
                break;

            case 4: // llistar
                if (ControladorPrincipal.getZones()[0] != null) {
                    zonaLlista = new ZonaLlista();
                    afegirListenersLlista();
                } else {
                    menuZona.getFrame().setVisible(true);
                    JOptionPane.showMessageDialog(menuZona.getFrame(), "Abans s'ha de crear al menys una zona");
                }
                break;

            case 5: //carregar
            /*
            TODO
                
            Es mostra un dialog (JOptionPane.showOptionDialog) amb botons, on cadascun d'ells és un mètode de càrrega 
            (propietat a Controlador Principal: ara XML i Serial)
            Un cop seleccionat el mètode, amb un altre dialog, es demana el codi de la zona a carregar 
            (recordeu que el nom del fitxer és el codi de la zona i l'extensió)
            Un cop l'usuari ha entrat el codi i ha premut OK:
                - Es crea un objecte Zona (novaZona) com a resultat de cridar al mètode carregarZona del gestor de persistència. Penseu que la
                  carrega pots ser d'un fitxer XML o bé d'un fitxer serial.
                - Es comprova si el codi de la novaZona ja existeix al vector de zones (això donarà la posició on s'ha trobat a la llista). Penseu
                  que en aquesta classe teniu un mètode per fer la comprovació.
                - Si existeix:
                    - Es mostra un dialog notificant a l'usuari si vol substituir la zona del vector per la que es carregarà des de el fitxer (JOptionPane.showOptionDialog)
                    - Si l'usuari cancela, no es fa res
                    - Si l'usuari accepta, llavors es posa la nova zona al vector a la mateixa posició on s'havia trobat aquest codi
                - Si no existeix:
                    - S'afegeix la nova zona al vector de zones a la darrera posició
                    - Es mostra un missatge confirmant l'addició (JOptionPane.showMessageDialog)
            
            */
                
                menuZona.getFrame().setVisible(true);
                
                int code = JOptionPane.showOptionDialog(null, "Selecciona un mètode", "Carregar zona", 0, JOptionPane.QUESTION_MESSAGE, null, ControladorPrincipal.getMETODESPERSISTENCIA(), "XML");
                
                if (code != JOptionPane.CLOSED_OPTION) {
                    
                    GestorPersistencia gestor = new GestorPersistencia();
                    
                    Zona zona;
                    
                    try {
                        
                        String codi = JOptionPane.showInputDialog("Quin és el codi de la zona que vols carregar?");
                        
                        gestor.carregarZona(ControladorPrincipal.getMETODESPERSISTENCIA()[code], codi);
                        
                        if(ControladorPrincipal.getMETODESPERSISTENCIA()[code].equals("XML")){
                            zona = ((GestorXML)gestor.getGestor()).getZona();
                        }else if(ControladorPrincipal.getMETODESPERSISTENCIA()[code].equals("Serial")){
                            zona = ((GestorSerial)gestor.getGestor()).getZona();
                        }else if(ControladorPrincipal.getMETODESPERSISTENCIA()[code].equals("JDBC")){
                            zona = ((GestorJDBC)gestor.getGestor()).getZona();
                        }else{
                            zona = ((GestorDB4O)gestor.getGestor()).getZona(); 
                        }
                        
                        int pos = comprovarZona(zona.getCodi());
                        
                        if (pos >= 0) {
                            
                            Object[] options = {"OK", "Cancel·lar"};                            
                            int i = JOptionPane.showOptionDialog(null, "Premeu OK per substituir-la.", "Zona ja existent",
                                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                                    null, options, options[0]);
                            
                            if (i == 0) {
                                ControladorPrincipal.getZones()[pos] = zona;
                            }
                            
                        } else {
                            
                            ControladorPrincipal.getZones()[ControladorPrincipal.getpZones()] = zona;
                            ControladorPrincipal.setpZones();
                            JOptionPane.showMessageDialog(menuZona.getFrame(), "Zona afegida correctament");
                        
                        }

                    } catch (GestorLiniesTrensException e) {
                        JOptionPane.showMessageDialog(menuZona.getFrame(), e.getMessage());
                    }
                }
                
                break;

            case 6: //desar
                /*
                TODO
                
                Es comprova si s'ha seleccionat la zona, mostrant, si correspon, missatges d'error (JOptionPane.showMessageDialog)
                Si s'ha seleccionat la zona:
                    - Es mostra un dialog (JOptionPane.showOptionDialog) amb botons, on cadascun d'ells és un mètode de càrrega
                    (propietat a Controlador Principal: ara XML i Serial)
                    - Un cop escollit el mètode, es desa la zona cridant a desarZona del gestor de persistència.
                 */
                
                menuZona.getFrame().setVisible(true);
                
                if (ControladorPrincipal.getZonaActual() != null) {
                    
                    int messageTyped = JOptionPane.QUESTION_MESSAGE;
                    int coded = JOptionPane.showOptionDialog(null, "Selecciona un mètode", "Desar zona", 0, messageTyped, null, ControladorPrincipal.getMETODESPERSISTENCIA(), "XML");
                    
                    if (coded != JOptionPane.CLOSED_OPTION) {
                        
                        GestorPersistencia gestor = new GestorPersistencia();
                        
                        try {
                            gestor.desarZona(ControladorPrincipal.getMETODESPERSISTENCIA()[coded], String.valueOf(ControladorPrincipal.getZonaActual().getCodi()), ControladorPrincipal.getZonaActual());
                        } catch (GestorLiniesTrensException e) {
                            JOptionPane.showMessageDialog(menuZona.getFrame(), e.getMessage());
                        }
                        
                    }
                    
                } else {
                    JOptionPane.showMessageDialog(menuZona.getFrame(), "Abans s'ha de seleccionar una zona");
                }

                break;

        }

    }

    private void seleccionarZona() {

        String[] codi = new String[ControladorPrincipal.getpZones()];

        int i = 0;

        for (Zona zona : ControladorPrincipal.getZones()) {

            if (zona != null) {
                codi[i] = String.valueOf(zona.getCodi());
            }

            i++;

        }

        int messageType = JOptionPane.QUESTION_MESSAGE;
        int code = JOptionPane.showOptionDialog(null, "Selecciona una Zona", "Selecció de la zona", 0, messageType, null, codi, "A");
        
        if (code != JOptionPane.CLOSED_OPTION) {
            ControladorPrincipal.setZonaActual(ControladorPrincipal.getZones()[code]);
        }

    }

    private int comprovarZona(int codi) {

        boolean trobat = false;

        int pos = -1;

        for (int i = 0; i < ControladorPrincipal.getZones().length && !trobat; i++) {

            if (ControladorPrincipal.getZones()[i] != null) {
                if (ControladorPrincipal.getZones()[i].getCodi() == codi) {
                    pos = i;
                    trobat = true;
                }
            }
        }

        return pos;
    }

}