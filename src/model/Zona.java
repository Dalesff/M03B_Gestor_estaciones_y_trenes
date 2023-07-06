/*
 * Classe que defineix una zona. Una zona es defineix pel seu codi, nom del o de la 
 * responsable, un vector d'estacions, un vector de trens de passatgers, un vector 
 * de trens de mercaderies i un vector de línies. 
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import principal.Component;
import principal.GestorLiniesTrensException;

/**
 *
 * @author fta
 */
public class Zona implements Component, Serializable {

    private int codi;
    private static int properCodi = 1; //El proper codi a assignar
    private String responsable;
    private List<Component> components = new ArrayList();

    /*
     TODO CONSTRUCTOR
    
     Paràmetres: valors per tots els atributs de la classe menys els vectors i el codi.
    
     Accions:
     - Assignar als atributs corresponents els valors passats com a paràmetres.
     - Assignar a l'atribut codi el valor de l'atribut properCodi i actualitzar
     properCodi amb el següent codi a assignar.
     */
    public Zona(String responsable) {
        codi = properCodi;
        properCodi++;
        this.responsable = responsable;
    }
    
    /*Nou constructor per crear una zona a partir de les dades guardades en un
      document XML
     */
    public Zona(int codi, String responsable) {
        this.codi = codi;
        this.responsable = responsable;
    }

    /*
     TODO Heu d'implementar tots els mètodes accessors possibles.
     */
    public int getCodi() {
        return codi;
    }

    public void setCodi(int codi) {
        this.codi = codi;
    }

    public static int getProperCodi() {
        return properCodi;
    }

    public static void setProperCodi(int properCodi) {
        Zona.properCodi = properCodi;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    public static Zona addZona() {

        System.out.println("\nNom del responsable:");

        return new Zona(DADES.nextLine());
    }

    public void updateComponent() {
        System.out.println("\nNom del responsable de la zona: " + responsable);
        System.out.println("\nEntra el nou nom del responsable:");
        responsable = DADES.nextLine();
    }

    public void showComponent() {
        System.out.println("\nEl nom del responsable de la zona amb codi " + codi + " és:");
        System.out.println("\nNom del responsable: " + responsable);
    }

    /*
     ESTACIÓ
     */
    public void addEstacio(Estacio estacio) throws GestorLiniesTrensException {

        if (estacio == null) {
            estacio = Estacio.addEstacio();
        }

        if (selectComponent(1, estacio.getCodi()) == -1) {
            components.add(estacio);
        } else {
            throw new GestorLiniesTrensException("5");
        }
    }

    /*
     TREN PASSATGERS
     */
    public void addTrenPassatgers(TrenPassatgers trenPassatgers) throws GestorLiniesTrensException {

        if (trenPassatgers == null) {
            trenPassatgers = TrenPassatgers.addTrenPassatgers();
        }

        if (selectComponent(2, trenPassatgers.getMatricula()) == -1) {
            components.add(trenPassatgers);
        } else {
            throw new GestorLiniesTrensException("6");
        }
    }

    /*
     TREN MERCADERIES
     */
    public void addTrenMercaderies(TrenMercaderies trenMercaderies) throws GestorLiniesTrensException {

        if (trenMercaderies == null) {
            trenMercaderies = TrenMercaderies.addTrenMercaderies();
        }

        if (selectComponent(3, trenMercaderies.getMatricula()) == -1) {
            components.add(trenMercaderies);
        } else {
            throw new GestorLiniesTrensException("7");
        }
    }

    public void addLinia(Linia linia) throws GestorLiniesTrensException {

        if (linia == null) {
            linia = Linia.addLinia();
        }

        if (selectComponent(4, linia.getNomId()) == -1) {
            components.add(linia);
        } else {
            throw new GestorLiniesTrensException("8");
        }
    }

    public int selectComponent(int tipusComponent, String id) {

        if (id == null) {
            //Demanem quin tipus de component vol seleccionar i el seu identificador (id)
            switch (tipusComponent) {
                case 1:
                    System.out.println("Codi de l'estació?:");
                    break;
                case 2:
                    System.out.println("Matrícula del tren de passatgers?:");
                    break;
                case 3:
                    System.out.println("Matrícula del tren de mercaderies?:");
                    break;
                case 4:
                    System.out.println("Nom identificador de la línia?:");
                    break;
            }

            id = DADES.next();
        }

        int posElement = -1; //Posició que ocupa el component seleccionat dins el vector de components de la zona

        //Seleccionem la posició que ocupa el component dins el vector de components
        //de la zona
        for (int i = 0; i < components.size(); i++) {
            if (components.get(i) instanceof Estacio && tipusComponent == 1) {
                if (((Estacio) components.get(i)).getCodi().equals(id)) {
                    return i;
                }
            } else if (components.get(i) instanceof TrenPassatgers && tipusComponent == 2) {
                if (((TrenPassatgers) components.get(i)).getMatricula().equals(id)) {
                    return i;
                }
            } else if (components.get(i) instanceof TrenMercaderies && tipusComponent == 3) {
                if (((TrenMercaderies) components.get(i)).getMatricula().equals(id)) {
                    return i;
                }
            } else if (components.get(i) instanceof Linia && tipusComponent == 4) {
                if (((Linia) components.get(i)).getNomId().equals(id)) {
                    return i;
                }
            }
        }

        return posElement;
    }

    public void addEstacioLinia() throws GestorLiniesTrensException {

        Linia liniaSel;
        int pos = selectComponent(4, null);

        if (pos >= 0) {

            liniaSel = (Linia) this.getComponents().get(pos);

            pos = selectComponent(1, null);

            if (pos >= 0) {
                liniaSel.addEstacio((Estacio) this.getComponents().get(pos));
            } else {
                System.out.println("\nNo existeix aquesta estació");
            }

        } else {
            System.out.println("\nNo existeix aquesta linia");
        }

    }

    public void addTrenPassatgersLinia() throws GestorLiniesTrensException {

        Linia liniaSel;
        int pos = selectComponent(4, null);

        if (pos >= 0) {

            liniaSel = (Linia) this.getComponents().get(pos);

            pos = selectComponent(2, null);

            if (pos >= 0) {
                System.out.println("\nQuants vagons s'han d'assignar al tren de passatgers?");
                liniaSel.addTren((TrenPassatgers) this.getComponents().get(pos), DADES.nextInt());
            } else {
                System.out.println("\nNo existeix aquest tren de passatgers");
            }

        } else {
            System.out.println("\nNo existeix aquesta línia");
        }

    }

    public void addTrenMercaderiesLinia() throws GestorLiniesTrensException {

        Linia liniaSel;
        int pos = selectComponent(4, null);

        if (pos >= 0) {

            liniaSel = (Linia) this.getComponents().get(pos);

            pos = selectComponent(3, null);

            if (pos >= 0) {
                System.out.println("\nQuants vagons s'han d'assignar al tren de mercaderies?");
                liniaSel.addTren((TrenMercaderies) this.getComponents().get(pos), DADES.nextInt());
            } else {
                System.out.println("\nNo existeix aquest tren de mercaderies");
            }

        } else {
            System.out.println("\nNo existeix aquesta línia");
        }

    }
}
