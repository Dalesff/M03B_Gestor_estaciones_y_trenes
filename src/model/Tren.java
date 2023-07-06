package model;

import principal.Component;
import principal.GestorLiniesTrensException;

/**
 *
 * @author fta
 */
public abstract class Tren implements Component {
    
    private String matricula;
    private int anyAdquisicio;
    private String tipus;

    public Tren(String matricula, int anyAdquisicio, String tipus) {
        this.matricula = matricula;
        this.anyAdquisicio = anyAdquisicio;
        this.tipus = tipus;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getAnyAdquisicio() {
        return anyAdquisicio;
    }

    public void setAnyAdquisicio(int anyAdquisicio) {
        this.anyAdquisicio = anyAdquisicio;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public void updateComponent() throws GestorLiniesTrensException {
        System.out.println("\nMatrícula del tren: " + matricula);
        System.out.println("\nEntra la nova matrícula:");
        matricula = DADES.next();        
        System.out.println("\nAny d'adquisició del tren: " + anyAdquisicio);
        System.out.println("\nEntra el nou any d'adquisició:");
        anyAdquisicio = DADES.nextInt();
        DADES.nextLine(); //Neteja buffer
        System.out.println("\nTipus del tren: " + tipus);
        System.out.println("\nEntra el nou tipus:");
        tipus = DADES.nextLine();
    }
    
    public void showComponent() {
        System.out.println("\nLes dades del tren amb matrícula " + matricula + " són:");
        System.out.println("\nAny d'adquisició: " + anyAdquisicio);
        System.out.println("\nTipus: " + tipus);
    }
}
