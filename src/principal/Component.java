/*
 * Interfície que implementarà qualsevol component de la zona
 */
package principal;

import java.util.Scanner;

/**
 *
 * @author fta
 */
public interface Component {  
    
    public final static Scanner DADES = new Scanner(System.in);
    public void updateComponent()throws GestorLiniesTrensException; 
    public void showComponent();     
    
}
