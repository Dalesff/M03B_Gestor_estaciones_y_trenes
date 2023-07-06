package principal;

import model.Linia;
import model.Zona;
import model.Estacio;
import model.TrenMercaderies;
import model.TrenPassatgers;
import java.util.InputMismatchException;
import java.util.Scanner;
import persistencia.GestorPersistencia;
import persistencia.GestorXML;

/**
 *
 * @author fta
 */
public class Application {

    private final static Scanner DADES = new Scanner(System.in);

    private static Zona[] zones = new Zona[10];
    private static int pZones = 0; //Priemra posició buida del vector zones
    private static Zona zonaActual = null;
    static private String FITXER = "zona";
    static private GestorPersistencia gp = new GestorPersistencia();

    public static void main(String[] args) {
        try {
            menuPrincipal();
        } catch (GestorLiniesTrensException e) {
            System.out.println("\n" + e.getMessage());
        }
    }

    private static void menuPrincipal() throws GestorLiniesTrensException {
        int opcio = 0;

        do {
            try {
            System.out.println("\nSelecciona una opció");
            System.out.println("\n0. Sortir");
            System.out.println("\n1. Gestió de zones");
            System.out.println("\n2. Gestió de línies");
            System.out.println("\n3. Gestió de estacions");
            System.out.println("\n4. Gestió de trens de passatgers");
            System.out.println("\n5. Gestió de trens de mercaderies");
            opcio = DADES.nextInt();

            switch (opcio) {
                case 0:
                    break;
                case 1:
                    menuZones();
                    break;
                case 2:
                    if (zonaActual != null) {
                        menuLinies();
                    } else {
                        System.out.println("\nPrimer s'ha de seleccionar la zona al menú Gestió de zones");
                    }
                    break;
                case 3:
                    if (zonaActual != null) {
                        menuComponents(1);
                    } else {
                        System.out.println("\nPrimer s'ha de seleccionar la zona al menú Gestió de zones");
                    }
                    break;
                case 4:
                    if (zonaActual != null) {
                        menuComponents(2);
                    } else {
                        System.out.println("\nPrimer s'ha de seleccionar la zona al menú Gestió de zones");
                    }
                    break;
                case 5:
                    if (zonaActual != null) {
                        menuComponents(3);
                    } else {
                        System.out.println("\nPrimer s'ha de seleccionar la zona al menú Gestió de zones");
                    }
                    break;
                default:
                    System.out.println("\nS'ha de seleccionar una opció correcta del menú.");
                    break;
            }
            } catch (InputMismatchException e) {
                throw new GestorLiniesTrensException("1");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new GestorLiniesTrensException("9");
            }
        } while (opcio != 0);
    }

    public static void menuZones() throws GestorLiniesTrensException, InputMismatchException, ArrayIndexOutOfBoundsException {
        int opcio = 0;

        do {
            int indexSel = -1;
            System.out.println("\nSelecciona una opció");
            System.out.println("\n0. Sortir");
            System.out.println("\n1. Alta");
            System.out.println("\n2. Seleccionar");
            System.out.println("\n3. Modificar");
            System.out.println("\n4. Llista de zones");
            System.out.println("\n5. Carregar zona");
            System.out.println("\n6. Desar zona");
            opcio = DADES.nextInt();
            switch (opcio) {
                case 0:
                    break;
                case 1:
                    zones[pZones] = Zona.addZona();
                    pZones++;
                    break;
                case 2:
                    indexSel = selectZona();
                    if (indexSel >= 0) {
                        zonaActual = zones[indexSel];
                    } else {
                        System.out.println("\nNo existeix aquesta zona");
                    }
                    break;
                case 3:
                    indexSel = selectZona();
                    if (indexSel >= 0) {
                        zones[indexSel].updateComponent();
                    } else {
                        System.out.println("\nNo existeix aquesta zona");
                    }
                    break;
                case 4:
                    for (int i = 0; i < pZones; i++) {
                        zones[i].showComponent();
                    }
                    break;
                case 5: //Carregar zona
                    pZones = 0;
                    zones = new Zona[1]; //Per facilitar la feina, només podem carregar una zona
                    gp.carregarZona("XML", FITXER);
                    zones[pZones] = ((GestorXML)gp.getGestor()).getZona();
                    pZones++;
                    break;
                case 6: //Desar zona
                    int pos = selectZona();
                    if (pos >= 0) {
                        gp.desarZona("XML", FITXER, zones[pos]);
                    } else {
                        System.out.println("\nNo existeix aquesta zona");
                    }
                    break;
                default:
                    System.out.println("\nS'ha de seleccionar una opció correcta del menú.");
                    break;
            }
        } while (opcio != 0);
    }

    public static void menuComponents(int tipus) throws GestorLiniesTrensException, InputMismatchException, ArrayIndexOutOfBoundsException {
        int opcio = 0;

        do {
            System.out.println("\nSelecciona una opció");
            System.out.println("\n0. Sortir");
            System.out.println("\n1. Alta");
            System.out.println("\n2. Modificar");
            System.out.println("\n3. Llistat");
            opcio = DADES.nextInt();
            switch (opcio) {
                case 0:
                    break;
                case 1:
                    switch (tipus) {
                        case 1:
                            zonaActual.addEstacio(null);
                            break;
                        case 2:
                            zonaActual.addTrenPassatgers(null);
                            break;
                        case 3:
                            zonaActual.addTrenMercaderies(null);
                            break;
                    }
                    break;
                case 2:
                    int indexSel = zonaActual.selectComponent(tipus, null);
                    if (indexSel >= 0) {
                        zonaActual.getComponents().get(indexSel).updateComponent();
                    } else {
                        System.out.println("\nNo existeix aquest component");
                    }
                    break;
                case 3:
                    for (int i = 0; i < zonaActual.getComponents().size(); i++) {
                        if (zonaActual.getComponents().get(i) instanceof Estacio && tipus == 1) {
                            zonaActual.getComponents().get(i).showComponent();
                        } else if (zonaActual.getComponents().get(i) instanceof TrenPassatgers && tipus == 2) {
                            zonaActual.getComponents().get(i).showComponent();
                        } else if (zonaActual.getComponents().get(i) instanceof TrenMercaderies && tipus == 3) {
                            zonaActual.getComponents().get(i).showComponent();
                        }
                    }
                default:
                    System.out.println("\nS'ha de seleccionar una opció correcta del menú.");
                    break;
            }
        } while (opcio != 0);
    }


    /*
     TODO Heu de desenvolupar el menuLinies amb les opcions que podeu veure.
     Nota: penseu que quan arribem aquí, l'atribut zonaActual no és null
     
     Opció 0. Sortir -->       Surt del menú i retorna al menú principal
     Opció 1. Alta -->         Crea una línia en la zona actual. Penseu que Zona sap crear línies      
     Opció 2. Modificar ->     Permet modificar una línia que existeix a la zona actual
     (per comprovar l'existència de la línia tenim un mètode en la classe Zona que ens ajuda)
     Opció 3. Assignar estació ->   Assigna una estació a una línia, però penseu que Zona sap com fer-ho.
     Opció 4. Assignar tren de passatgers ->   Assigna un tren de passatgers i els seus vagons a una línia, però penseu que Zona sap com fer-ho.
     Opció 5. Assignar tren de mercaderies ->   Assigna un tren de mercaderies i els seus vagons a una línia, però penseu que Zona sap com fer-ho.
     Opció 6. Llista de línies-> Imprimeix les dades de les línies de la zona actual.
        
     A més, heu de fer una estructura iterativa per tornar a mostrar el menú sempre que no es premi l'opció 0 de sortida
     Recomanacions:
     - estructura de control switch-case per bifurcar les opcions
     - si no s'ha introduït cap opció de les de la llista, s'ha de mostrar el missatge
     "S'ha de seleccionar una opció correcta del menú."
     - definiu una variable opcio de tipus enter
     */
    public static void menuLinies() throws GestorLiniesTrensException, InputMismatchException, ArrayIndexOutOfBoundsException{
        int opcio = 0;

        do {
            System.out.println("\nSelecciona una opció");
            System.out.println("\n0. Sortir");
            System.out.println("\n1. Alta");
            System.out.println("\n2. Modificar");
            System.out.println("\n3. Assignar estació");
            System.out.println("\n4. Assignar tren de passatgers");
            System.out.println("\n5. Assignar tren de mercaderies");
            System.out.println("\n6. Llista de línies");
            opcio = DADES.nextInt();
            switch (opcio) {
                case 0:
                    break;
                case 1:
                    zonaActual.addLinia(null);
                    break;
                case 2:
                    int indexSel = zonaActual.selectComponent(4, null);
                    if (indexSel >= 0) {
                        zonaActual.getComponents().get(indexSel).updateComponent();
                    } else {
                        System.out.println("\nNo existeix aquesta línia");
                    }
                    break;
                case 3:
                    zonaActual.addEstacioLinia();
                    break;
                case 4:
                    zonaActual.addTrenPassatgersLinia();
                    break;
                case 5:
                    zonaActual.addTrenMercaderiesLinia();
                    break;
                case 6:
                    for (int i = 0; i < zonaActual.getComponents().size(); i++) {
                        if (zonaActual.getComponents().get(i) instanceof Linia) {
                            zonaActual.getComponents().get(i).showComponent();
                        }
                    }
                    break;
                default:
                    System.out.println("\nS'ha de seleccionar una opció correcta del menú.");
                    break;
            }
        } while (opcio != 0);
    }

    public static Integer selectZona() {

        System.out.println("\nCodi de la zona?:");
        int codi = DADES.nextInt();

        for (int i = 0; i < pZones; i++) {
            if (zones[i].getCodi() == codi) {
                return i;
            }
        }
        return -1;
    }
}
