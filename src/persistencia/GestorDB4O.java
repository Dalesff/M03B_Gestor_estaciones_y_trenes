package persistencia;


import com.db4o.*;
import com.db4o.config.EmbeddedConfiguration;
import model.Zona;
import principal.GestorLiniesTrensException;
import com.db4o.query.Predicate;
import java.util.List;

/**
 *
 * @author fta
 */
public class GestorDB4O implements ProveedorPersistencia {

    private ObjectContainer db;
    private Zona zona;

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }    

    public void estableixConnexio() {
        EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
        config.common().objectClass(Zona.class).cascadeOnUpdate(true);
        db = Db4oEmbedded.openFile(config, "EAC112021S2.db4o");
    }

    public void tancaConnexio() {
        db.close();
    }

    @Override
    public void desarZona(String nomFitxer, Zona pZona) throws GestorLiniesTrensException {
        
        estableixConnexio();
        final int codi = Integer.parseInt(nomFitxer);
        
        List<Zona> zonas = db.query(new Predicate<Zona>() {
            public boolean match(Zona zona){
                return zona.getCodi() == codi;
            }
        });
        
        if (zonas.size() != 1) {
            db.store(pZona);
            db.commit();
        } else {
            zona = zonas.iterator().next();
            zona.setCodi(pZona.getCodi());
            zona.setResponsable(pZona.getResponsable());
            db.store(zona);
            db.commit();
        }
        
        tancaConnexio();
    }

    @Override
    public void carregarZona(String nomFitxer) throws GestorLiniesTrensException {
        
        estableixConnexio();
        final int codi = Integer.parseInt(nomFitxer);
        
        List<Zona> zonas = db.query(new Predicate<Zona>() {
            public boolean match(Zona zona){
                return zona.getCodi() == codi;
            }
        });
        
        if (zonas.size() != 1) {
            throw new GestorLiniesTrensException("GestorDB3O.noExisteix");
        } else {
            zona = zonas.iterator().next();
        }
        tancaConnexio();
    }
}
