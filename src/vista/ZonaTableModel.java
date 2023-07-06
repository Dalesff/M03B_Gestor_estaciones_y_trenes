package vista;

import controlador.ControladorPrincipal;
import javax.swing.table.AbstractTableModel;
import model.Zona;

/**
 *
 * @author fta
 */
public class ZonaTableModel extends AbstractTableModel{
    
    private final String[] columnNames = {"Codi", "Responsable"};

    String[][] data = new String[ControladorPrincipal.getMAXZONES()][2];

    public ZonaTableModel() {
        int i = 0;
        for (Zona zona : ControladorPrincipal.getZones()) {
            if (zona != null) {
                data[i][0] = String.valueOf(zona.getCodi());
                data[i][1] = zona.getResponsable();
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
