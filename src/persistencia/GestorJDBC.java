package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Estacio;
import model.Zona;
import principal.GestorLiniesTrensException;
import principal.Component;

public class GestorJDBC implements ProveedorPersistencia {

    private Zona zona;

    private Connection conn; //Connexió a la base de dades

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    private static String codiZonaSQL = "" + " SELECT * " + " FROM zones " + " WHERE codi = ?";

    private PreparedStatement codiZonaSt;

    private static String insereixZonaSQL = "" + " INSERT INTO zones(codi, responsable) " + " VALUES (?, ?)";

    private PreparedStatement insereixZonaSt;

    private static String actualitzaZonaSQL = "" + " UPDATE zones " + " SET responsable = ? " + " WHERE codi = ? ";

    private PreparedStatement actualitzaZonaSt;

    private static String eliminaEstacionsSQL = "" + " DELETE FROM estacions " + " WHERE zona = ?";

    private PreparedStatement eliminaEstacionsSt;

    private static String insereixEstacioSQL = "" + "INSERT INTO estacions(codi, nom, poblacio, adreca, zona) " + " VALUES (?, ?, ?, ?, ?)";

    private PreparedStatement insereixEstacioSt;

    private static String selEstacionsSQL = "" + " SELECT * " + " FROM estacions " + " WHERE zona = ?";

    private PreparedStatement selEstacionsSt;

    public void estableixConnexio() throws SQLException {
        String urlBaseDades = "jdbc:derby://localhost:1527/EAC112021S2";
        String usuari = "root";
        String contrasenya = "root123";
        
        try {
            conn = DriverManager.getConnection(urlBaseDades, usuari, contrasenya);
            codiZonaSt = conn.prepareStatement(codiZonaSQL);
            insereixZonaSt = conn.prepareStatement(insereixZonaSQL);
            actualitzaZonaSt = conn.prepareStatement(actualitzaZonaSQL);
            eliminaEstacionsSt = conn.prepareStatement(eliminaEstacionsSQL);
            insereixEstacioSt = conn.prepareStatement(insereixEstacioSQL);
            selEstacionsSt = conn.prepareStatement(selEstacionsSQL);
        } catch (SQLException e) {
            conn = null;
            System.out.println(e.getMessage());
            throw e;            
        }
    }

    public void tancaConnexio() throws SQLException {
        try {
            conn.close();
        } finally {
            conn = null;
        }
    }

    @Override
    public void desarZona(String nomFitxer, Zona zona) throws GestorLiniesTrensException {
        
        try {
            if (conn == null) {
                estableixConnexio();
            }
            
            codiZonaSt.setInt(1, zona.getCodi());
            ResultSet regZona = codiZonaSt.executeQuery();
            
            if (regZona.next()) {
                actualitzaZonaSt.setString(1, zona.getResponsable());
                actualitzaZonaSt.setInt(2, zona.getCodi());
                actualitzaZonaSt.executeUpdate();
                
                eliminaEstacionsSt.setInt(1, zona.getCodi());
                eliminaEstacionsSt.executeUpdate();
            } else {
                insereixZonaSt.setInt(1, zona.getCodi());
                insereixZonaSt.setString(2, zona.getResponsable());
                insereixZonaSt.executeUpdate();
            }
            
            for (Component c : zona.getComponents()) {
                if (c != null && c instanceof Estacio) {
                    insereixEstacioSt.setString(1, ((Estacio) c).getCodi());
                    insereixEstacioSt.setString(2, ((Estacio) c).getNom());
                    insereixEstacioSt.setString(3, ((Estacio) c).getPoblacio());
                    insereixEstacioSt.setString(4, ((Estacio) c).getAdreca());
                    insereixEstacioSt.setInt(5, zona.getCodi());
                    insereixEstacioSt.executeUpdate();
                }
            }
            
            tancaConnexio();
            
        } catch (SQLException e) {
            throw new GestorLiniesTrensException("GestorJDCB.desar");
        }
    }

    @Override
    public void carregarZona(String nomFitxer) throws GestorLiniesTrensException {
        
        try {
            if (conn == null) {
                estableixConnexio();
            }
            
            codiZonaSt.setInt(1, Integer.parseInt(nomFitxer));
            ResultSet regZonas = codiZonaSt.executeQuery();
            
            if (regZonas.next()) {
                
                zona = new Zona(regZonas.getInt("codi"), regZonas.getString("responsable"));
                
                selEstacionsSt.setInt(1, zona.getCodi());
                
                ResultSet regEstacions = selEstacionsSt.executeQuery();
                
                while (regEstacions.next()) {
                    zona.addEstacio(new Estacio(regEstacions.getString("codi"), regEstacions.getString("nom"), regEstacions.getString("poblacio"), regEstacions.getString("adreca")));
                }
            } else {
                throw new GestorLiniesTrensException("GestorJDBC.noExisteix");
            }
            
            tancaConnexio();
            
        } catch (SQLException e) {
            throw new GestorLiniesTrensException("GestorJDBC.carrega");
        }
        
    }

}
