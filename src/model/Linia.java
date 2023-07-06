/*
 * Classe que defineix una línia. Una línia es defineix pel seu nom identificador, 
 * un vector amb les estacions que té assignades,  un vector amb els trens de passatgers 
 * que té assignats, un vector amb el nombre de vagons assignats als trens de passatgers 
 * d’aquesta línia, un vector amb els trens de mercaderies que té assignats i un vector 
 * amb el nombre de vagons assignats als trens de mercaderies.
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import principal.Component;
import principal.GestorLiniesTrensException;

/**
 *
 * @author fta
 */
public class Linia implements Component {

    private String nomId;
    private List<Estacio> estacions = new ArrayList();
    private Map<Tren, Integer> trens = new HashMap();

    /*
     TODO CONSTRUCTOR
    
     Paràmetres: valors per tots els atributs de la classe menys els vectors.
    
     Accions:
     - Assignar als atributs corresponents els valors passats com a paràmetres
     */
    public Linia(String nomId) {
        this.nomId = nomId;
    }

    /*
     TODO Heu d'implementar tots els mètodes accessors possibles.
     */
    public String getNomId() {
        return nomId;
    }

    public void setNomId(String nomId) {
        this.nomId = nomId;
    }

    public List<Estacio> getEstacions() {
        return estacions;
    }

    public void setEstacions(List<Estacio> estacions) {
        this.estacions = estacions;
    }

    public Map<Tren, Integer> getTrens() {
        return trens;
    }

    public void setTrens(Map<Tren, Integer> trens) {
        this.trens = trens;
    }


    /*
    TODO
    Paràmetres: cap
    
    Accions:
    - Demanar a l'usuari les dades per consola per crear una nova línia. Les dades
    a demanar són les que passem per paràmetre en el constructor.
    
    Retorn: La nova mescla creada.
     */
    public static Linia addLinia() {

        System.out.println("\nNom identificador de la línia:");

        return new Linia(DADES.next());
    }

    /*
     TODO
    
     Paràmetres: cap
    
     Accions:
     - Demanar a l'usuari que introdueixi les noves dades de l'objecte actual i
       modificar els atributs corresponents d'aquest objecte. Els únics atributs que
       modificarem són els que hem inicialitzat en el constructor amb els paràmetres passats.
     - Li heu de mostrar a l'usuari els valors dels atributs abans de modificar-los.
     
    Retorn: cap
     */
    public void updateComponent() {
        System.out.println("\nNom identificador de la línia: " + nomId);
        System.out.println("\nEntra el nou nom identificador:");
        nomId = DADES.next();
    }

    /*
     TODO
    
     Paràmetres: estació a assignar
    
     Accions:
    
     - Si l'estació passada com a paràmetre no ha estat assignada a la línia actual, 
       s'afegeix al vector d'estacions i s'actualitza la posició del vector.
    
     - Si l'estació ja està assignat a la línia actual, no fem res.
    
     Retorn: cap
     */
    public void addEstacio(Estacio estacio) throws GestorLiniesTrensException {

        for (int i = 0; i < estacions.size(); i++) {
            if (estacions.get(i).getCodi().equals(estacio.getCodi())) {
                throw new GestorLiniesTrensException("3");
            }
        }

        estacions.add(estacio);

    }

    public void addTren(Tren tren, int vagons) throws GestorLiniesTrensException {

        if (trens.containsKey(tren)) {
            throw new GestorLiniesTrensException("4");
        } else {
            trens.put(tren, vagons);
        }
    }

    public void showComponent() {
        Tren tren;
        
        System.out.println("\nLes dades de la línia amb nom identificador " + nomId + " són:");

        for (int i = 0; i < estacions.size(); i++) {
            estacions.get(i).showComponent();
        }

        Iterator<Tren> clau = trens.keySet().iterator();

        while (clau.hasNext()) { //Mentres la clau no sigui l'última...
            tren= clau.next();
            tren.showComponent();
            System.out.println("\nvagons: " + trens.get(tren));
        }
    }

}
