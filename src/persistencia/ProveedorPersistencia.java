package persistencia;

import model.Zona;
import principal.GestorLiniesTrensException;

/**
 *
 * @author fta
 */
public interface ProveedorPersistencia {
    public void desarZona(String nomFitxer, Zona zona)throws GestorLiniesTrensException;
    public void carregarZona(String nomFitxer)throws GestorLiniesTrensException; 
}
