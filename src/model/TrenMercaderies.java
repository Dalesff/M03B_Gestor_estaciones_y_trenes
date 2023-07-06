/*
 * Classe que defineix un tren de mercaderies. Es defineixen per la seva matrícula, 
 * any d’adquisició, tipus de tren (regional, metropolita, etc.), si transporta mercaderies 
 * perilloses o no i el nombre de locomotores que fa servir.
 */
package model;

import principal.GestorLiniesTrensException;

/**
 *
 * @author fta
 */
public class TrenMercaderies extends Tren {

    private boolean mercaderiesPerilloses;
    private int locomotores;

    /*
     TODO CONSTRUCTOR
    
     Paràmetres: valors per tots els atributs de la classe.
    
     Accions:
     - Assignar als atributs els valors passats com a paràmetres.    
     */
    public TrenMercaderies(String matricula, int anyAdquisicio, String tipus, boolean mercaderiesPerilloses, int locomotores) {
        super(matricula, anyAdquisicio, tipus);
        this.mercaderiesPerilloses = mercaderiesPerilloses;
        this.locomotores = locomotores;
    }

    /*
    TODO Heu d'implementar tots els mètodes accessors possibles.    
     */
    public boolean isMercaderiesPerilloses() {
        return mercaderiesPerilloses;
    }

    public void setMercaderiesPerilloses(boolean mercaderiesPerilloses) {
        this.mercaderiesPerilloses = mercaderiesPerilloses;
    }

    public int getLocomotores() {
        return locomotores;
    }

    public void setLocomotores(int locomotores) {
        this.locomotores = locomotores;
    }

    /*
    TODO
    Paràmetres: cap
    Accions:
    - Demanar a l'usuari les dades per consola per crear un nou tren de mercaderies. 
    Les dades a demanar són les que necessita el constructor.
    - En el cas de mercaderies perilloses, li heu d'indicar a l'usuari que us contesti "Si",
    si transporta mercaderies perilloses o "No" en cas contrari. No heu de comprovar si el
    valor introduït és correcte, això ho farem més endavant. De moment suposarem que el
    valor introduït sempre és un "Si" o un "No". Penseu que la variable que recull
    aquest valor és un booleà i un booleà no accepta un String com "Si" o "No".
    - També heu de tenir en compte que el tipus, pot ser una frase, per exemple,
    alta velocitat.
    Retorn: El nou tren de mercaderies creat.
     */
    public static TrenMercaderies addTrenMercaderies() throws GestorLiniesTrensException {
        String matricula, tipus, resposta;
        int any, locomotores;
        boolean mercaderiesPerilloses = true;

        System.out.println("\nMatrícula del tren de mercaderies:");
        matricula = DADES.next();
        System.out.println("\nAny d'adquisició del tren de mercaderies:");
        any = DADES.nextInt();
        DADES.nextLine(); //Neteja buffer
        System.out.println("\nTipus del tren de mercaderies:");
        tipus = DADES.nextLine();

        System.out.println("\nEl tren de mercaderies transporta mercaderies perilloses? (Resposta: 'Si' o 'No'):");

        resposta = DADES.next();
        
        if (resposta.equals("No") || resposta.equals("SI")) {
            if (resposta.equals("No")) {
                mercaderiesPerilloses = false;
            }
        } else {
            throw new GestorLiniesTrensException("2");
        }

        System.out.println("\nNombre de locomotores del tren de mercaderies:");
        locomotores = DADES.nextInt();

        return new TrenMercaderies(matricula, any, tipus, mercaderiesPerilloses, locomotores);
    }

    /*
     TODO
    
     Paràmetres: cap
    
     Accions:
     - Demanar a l'usuari que introdueixi les noves dades de l'objecte actual i
       modificar els atributs corresponents d'aquest objecte.
     - En el cas de mercaderies perilloses, li heu d'indicar a l'usuari que us contesti "Si",
       si transporta mercaderies perilloses o "No" en cas contrari. No heu de comprovar si el
       valor introduït és correcte, això ho farem més endavant. De moment suposarem que el
       valor introduït sempre és un "Si" o un "No". Penseu que la variable que recull
       aquest valor és un booleà i un booleà no accepta un String com "Si" o "No".
     - També heu de tenir en compte que el tipus, pot ser una frase, per exemple,
       alta velocitat.
     - Li heu de mostrar a l'usuari els valors dels atributs abans de modificar-los.
       En el cas de les mercaderies perilloses, li heu de mostrar "Si" si les transporta
       i en cas contrari "No".
     
    Retorn: cap
     */
    public void updateComponent()throws GestorLiniesTrensException {
        super.updateComponent();

        if (mercaderiesPerilloses) {
            System.out.println("\nEl tren de mercaderies si transporta mercaderies perilloses");
        } else {
            System.out.println("\nEl tren de mercaderies no transporta mercaderies perilloses");
        }

        String resposta = DADES.next();
        
        if (resposta.equals("No") || resposta.equals("SI")) {
            if (resposta.equals("No")) {
                mercaderiesPerilloses = false;
            }
        } else {
            throw new GestorLiniesTrensException("2");
        }

        System.out.println("\nNombre de locomotores del tren de mercaderies: " + locomotores);
        System.out.println("\nEntra el nou nombre de locomotores:");
        locomotores = DADES.nextInt();

    }

    public void showComponent() {
        super.showComponent();
        if (mercaderiesPerilloses) {
            System.out.println("\nEl tren de mercaderies si transporta mercaderies perilloses");
        } else {
            System.out.println("\nEl tren de mercaderies no transporta mercaderies perilloses");
        }
        System.out.println("\nNombre de locomotores: " + locomotores);
    }
}
