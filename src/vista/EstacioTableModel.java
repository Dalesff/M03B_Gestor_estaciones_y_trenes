package vista;

import controlador.ControladorPrincipal;
import javax.swing.table.AbstractTableModel;
import model.Estacio;
import principal.Component;

/**
 *
 * @author fta
 */
public class EstacioTableModel extends AbstractTableModel{
    
    private final String[] columnNames = {"Codi", "Nom", "Població", "Adreça"};

    String[][] data = new String[ControladorPrincipal.getMAXZONES()][4];

    public EstacioTableModel() {
        int i = 0;
        for (Component component : ControladorPrincipal.getZonaActual().getComponents()) {
            if (component instanceof Estacio) {
                data[i][0] = ((Estacio)component).getCodi();
                data[i][1] = ((Estacio)component).getNom();
                data[i][2] = ((Estacio)component).getPoblacio();
                data[i][3] = ((Estacio)component).getAdreca();
                i++;
            }
        }
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return data[0].length;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int column) {
        return data[row][column];
    }
    
}
