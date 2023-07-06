package persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;
import model.Zona;
import principal.GestorLiniesTrensException;

/**
 *
 * @author fta
 */
public class GestorSerial implements ProveedorPersistencia{
    
    private Zona zona;

    public Zona getZona() {
        return zona;
    }

    public void setBotiga(Zona zona) {
        this.zona = zona;
    }    

    @Override
    public void desarZona(String nomFitxer, Zona zona) throws GestorLiniesTrensException {
        /*
         *TODO
         *
         *Paràmetres: nom del fitxer i zona
         *
         *Acció:
         * - Ha de desar l'objecte Zona serialitzat sobre un fitxer del sistema 
         *   operatiu amb nom nomFitxer i extensió ".ser".
         * - Heu de controlar excepcions d'entrada/sortida i en cas de produïrse alguna, 
         *   llavors llançar GestorLiniesTrensException amb codi GestorSerial.desar 
         *
         *Nota: podeu comprovar que la classe Zona implementa Serializable  
         *
         *Retorn: cap
         */
        
        try(ObjectOutputStream oos =new ObjectOutputStream(new FileOutputStream(new File(nomFitxer + ".ser")))) {
            oos.writeObject(zona);
        } catch (IOException ex) {
            throw new GestorLiniesTrensException("GestorSerial.desar");
        }

    }

    @Override
    public void carregarZona(String nomFitxer) throws GestorLiniesTrensException {
        /*
         *TODO
         *
         *Paràmetres: nom del fitxer
         *
         *Acció:
         * - Ha de carregar el fitxer del sistema operatiu amb nom nomFitxer i extensió 
         *   ".ser" sobre un nou objecte Zona que es retornarà com a resultat.               
         * - Heu de controlar excepcions d'entrada/sortida i en cas de produïrse alguna, 
         *   llavors llançar GestorLiniesTrensException amb codi GestorSerial.carrega 
         *
         *Retorn: cap
         */
        
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(nomFitxer + ".ser")))) {
            zona = (Zona) ois.readObject();
        } catch (IOException ex) {
            throw new GestorLiniesTrensException("GestorSerial.carregar");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error de classe: " + ex.getMessage());
        }

    }
}