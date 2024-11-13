package models;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Chico
 */
public class TablaPersonalizada extends DefaultTableModel{
    // Constructor que recibe los datos y nombres de columnas
    public TablaPersonalizada(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }

    // Sobrescribir el método isCellEditable
    public boolean isCellEditable(int row, int column) {
        // Si la columna es la del ID (en este caso la columna 0), no es editable
        if (column == 0) {
            return false;
        }
        // Para las demás columnas, permitir la edición
        return true;
    }
}
