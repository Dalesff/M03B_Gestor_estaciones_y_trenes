package persistencia;

import principal.GestorLiniesTrensException;
import model.Zona;

/**
 *
 * @author fta
 */
public class GestorPersistencia {

    private ProveedorPersistencia gestor;

    public ProveedorPersistencia getGestor() {
        return gestor;
    }

    public void setGestor(GestorXML pGestor) {
        gestor = pGestor;
    }

    public void desarZona(String tipusPersistencia, String nomFitxer, Zona zona) throws GestorLiniesTrensException {
        switch (tipusPersistencia) {

            case "XML":
                gestor = new GestorXML();
                break;
            case "Serial":
                gestor = new GestorSerial();
                break;
            case "JDBC":
                gestor = new GestorJDBC();
                break;
            default:
                gestor = new GestorDB4O();
                break;                

        }

        gestor.desarZona(nomFitxer, zona);
    }

    public void carregarZona(String tipusPersistencia, String nomFitxer) throws GestorLiniesTrensException {

        switch (tipusPersistencia) {

            case "XML":
                gestor = new GestorXML();
                break;
            case "Serial":
                gestor = new GestorSerial();
                break;
            case "JDBC":
                gestor = new GestorJDBC();
                break;
            default:
                gestor = new GestorDB4O();
                break;
        }

        gestor.carregarZona(nomFitxer);
    }
}
