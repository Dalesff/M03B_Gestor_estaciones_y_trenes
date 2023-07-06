/*
 * Classe que defineix un tren de passatgers. Es defineixen per la seva matrícula, 
 * any d’adquisició, tipus de tren (alta velocitat, regional, etc.).
 */
package model;

/**
 *
 * @author fta
 */
public class TrenPassatgers extends Tren{

    /*
     TODO CONSTRUCTOR
    
     Paràmetres: valors per tots els atributs de la classe.
    
     Accions:
     - Assignar als atributs els valors passats com a paràmetres.    
     */
    public TrenPassatgers(String matricula, int anyAdquisicio, String tipus) {
        super(matricula,anyAdquisicio, tipus);
    }

    /*
    TODO
    Paràmetres: cap
    Accions:
    - Demanar a l'usuari les dades per consola per crear un nou tren de passatgers. 
    Les dades a demanar són les que necessita el constructor.
    - També heu de tenir en compte que el tipus, pot ser una frase, per exemple,
    alta velocitat.
    Retorn: El nou tren de passatgers creat.
     */
    public static TrenPassatgers addTrenPassatgers() {
        String matricula, tipus;
        int any;
        
        System.out.println("\nMatrícula del tren de passatgers:");
        matricula = DADES.next();        
        System.out.println("\nAny d'adquisició del tren de passatgers:");
        any = DADES.nextInt();
        DADES.nextLine(); //Neteja buffer
        System.out.println("\nTipus del tren de passatgers:");
        tipus = DADES.nextLine();
        
        return new TrenPassatgers(matricula, any, tipus);
    }
}
