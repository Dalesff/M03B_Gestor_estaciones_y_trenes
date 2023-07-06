/*
 * Classe que defineix una estació. Es defineixen pel seu codi d'identificació dins de la seva zona, 
 * nom, població i adreça.
 */
package model;

import principal.Component;

/**
 *
 * @author fta
 */
public class Estacio implements Component {

    private String codi;
    private String nom;
    private String poblacio;
    private String adreca;

    /*
     TODO CONSTRUCTOR
    
     Paràmetres: valors per tots els atributs de la classe.
    
     Accions:
     - Assignar als atributs els valors passats com a paràmetres.    
     */
    public Estacio(String codi, String nom, String poblacio, String adreca) {
        this.codi = codi;
        this.nom = nom;
        this.poblacio = poblacio;
        this.adreca = adreca;
    }

    /*
     TODO Heu d'implementar tots els mètodes accessors possibles.  
     */

    public String getCodi() {
        return codi;
    }

    public void setCodi(String codi) {
        this.codi = codi;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPoblacio() {
        return poblacio;
    }

    public void setPoblacio(String poblacio) {
        this.poblacio = poblacio;
    }

    public String getAdreca() {
        return adreca;
    }

    public void setAdreca(String adreca) {
        this.adreca = adreca;
    }
    
    /*
    TODO
    
     Paràmetres: cap
    
     Accions:
     - Demanar a l'usuari les dades per consola per crear una nova estació. Les dades a demanar 
       són les que necessita el constructor.
     - També heu de tenir en compte que el nom, població o adreça, poden ser frases, per exemple, 
       Estació de l'est.
     
     Retorn: La nova estació creada.
     */
    public static Estacio addEstacio() {

        String codi, nom, poblacio, adreca;

        System.out.println("\nCodi de l'estació:");
        codi = DADES.next();
        DADES.nextLine(); //Neteja buffer
        System.out.println("\nNom de l'estació:");
        nom = DADES.nextLine();
        System.out.println("\nPoblació de l'estació:");
        poblacio = DADES.nextLine();
        System.out.println("\nAdreça de l'estació:");
        adreca = DADES.nextLine();

        return new Estacio(codi, nom, poblacio, adreca);
    }
    
    /*
     TODO
    
     Paràmetres: cap
    
     Accions:
     - Demanar a l'usuari que introdueixi les noves dades de l'objecte actual i
       modificar els atributs corresponents d'aquest objecte.
     - També heu de tenir en compte que el nom, població o adreça, poden ser frases, per exemple, 
       Estació de l'est.
     - Li heu de mostrar a l'usuari els valors dels atributs abans de modificar-los.
     
    Retorn: cap
     */
    public void updateComponent() {
        System.out.println("\nCodi de l'estació: " + codi);
        System.out.println("\nEntra el nou codi:");
        codi = DADES.next();
        DADES.nextLine(); //Neteja buffer
        System.out.println("\nNom de l'estació: " + nom);
        System.out.println("\nEntra el nou nom:");
        nom = DADES.nextLine();
        System.out.println("\nPoblació de l'estació: " + poblacio);
        System.out.println("\nEntra la nova població:");
        poblacio = DADES.next();
        System.out.println("\nAdreça de l'estació: " + adreca);
        System.out.println("\nEntra la nova adreça:");
        adreca = DADES.next();
    }
    
    public void showComponent() {
        System.out.println("\nLes dades de l'estació amb codi " + codi + " són:");
        System.out.println("\nNom: " + nom);
        System.out.println("\nPoblació: " + poblacio);
        System.out.println("\nAdreça: " + adreca);
    }
}
