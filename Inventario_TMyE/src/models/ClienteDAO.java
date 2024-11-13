package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Chico
 */
public class ClienteDAO {

    private static ConexionMysql conectar = new ConexionMysql();

    public ClienteDAO() {
    }

    public static ResultSet ListarTabla(String consulta) {
        Connection cn = conectar.establecerConexion();
        Statement sql;
        ResultSet datos = null;

        try {
            sql = cn.createStatement();
            datos = sql.executeQuery(consulta);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al tratar de listar los registros " + e.toString());
        }
        return datos;
    }

    public static void ConsultarTodov2(JTable tabla) {
        DefaultTableModel md = new DefaultTableModel();
        ResultSet rs = ProductoDAO.ListarTabla("SELECT \n"
                + "    id_cliente AS ID,\n"
                + "    CONCAT(nombre, ' ', apellido1, ' ', apellido2) AS Nombre,\n"
                + "    CONCAT(calle, ' ', numero_casa, ', ', colonia) AS Domicilio,\n"
                + "    telefono AS Telefono,\n"
                + "    deuda_pendiente AS 'Deuda pendiente',\n"
                + "    credito_disponible AS 'Credito disponible'\n"
                + "FROM \n"
                + "    cliente;");
        md.setColumnIdentifiers(new Object[]{
            "ID",
            "Nombre",
            "Domicilio",
            "Telefono",
            "Deuda pendiente",
            "Crédito disponible"
        });

        try {
            while (rs.next()) {
                md.addRow(new Object[]{
                    rs.getInt("ID"),
                    rs.getString("Nombre"),
                    rs.getString("Domicilio"),
                    rs.getString("Telefono"),
                    rs.getFloat("Deuda pendiente"),
                    rs.getFloat("Credito disponible"),});
                tabla.setModel(md);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al hacer la consulta: " + e.toString());
        }
    }

    public static void ConsultarTodo(JTable tabla) {
        // Define tus columnas
        String[] columnNames = {
            "ID",
            "Nombre",
            "Primer apellido",
            "Segundo apellido",
            "Deuda pendiente",
            "Crédito disponible",
            "Calle",
            "Número de casa",
            "Colonia",
            "Telefono"
        };

        // Inicializar modelo de tabla personalizado
        TablaPersonalizada md = new TablaPersonalizada(null, columnNames);

        ResultSet rs = ProductoDAO.ListarTabla("SELECT * FROM bd_inventario.cliente;");

        try {
            while (rs.next()) {
                md.addRow(new Object[]{
                    rs.getInt("id_cliente"),
                    rs.getString("nombre"),
                    rs.getString("apellido1"),
                    rs.getString("apellido2"),
                    rs.getFloat("deuda_pendiente"),
                    rs.getFloat("credito_disponible"),
                    rs.getString("calle"),
                    rs.getString("numero_casa"),
                    rs.getString("colonia"),
                    rs.getString("telefono")
                });
            }
            // Asigna el modelo personalizado a la tabla
            tabla.setModel(md);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al hacer la consulta: " + e.toString());
        }
    }

    public static void ConsultarFiltro(JTable tabla, String campo, String filtro) {
        if (campo.equals("nombre")) {
            DefaultTableModel md = new DefaultTableModel();
            ResultSet rs = ProductoDAO.ListarTabla("SELECT \n"
                    + "    id_cliente AS ID,\n"
                    + "    CONCAT(nombre, ' ', apellido1, ' ', apellido2) AS Nombre,\n"
                    + "    CONCAT(calle, ' ', numero_casa, ', ', colonia) AS Domicilio,\n"
                    + "    telefono AS Telefono,\n"
                    + "    deuda_pendiente AS 'Deuda pendiente',\n"
                    + "    credito_disponible AS 'Credito disponible'\n"
                    + "FROM \n"
                    + "    cliente\n"
                    + "WHERE \n"
                    + "    CONCAT(nombre, ' ', apellido1, ' ', apellido2) = \"" + filtro + "\";");
            md.setColumnIdentifiers(new Object[]{
                "ID",
                "Nombre",
                "Domicilio",
                "Telefono",
                "Deuda pendiente",
                "Crédito disponible"
            });

            try {
                while (rs.next()) {
                    md.addRow(new Object[]{
                        rs.getInt("ID"),
                        rs.getString("Nombre"),
                        rs.getString("Domicilio"),
                        rs.getString("Telefono"),
                        rs.getFloat("Deuda pendiente"),
                        rs.getFloat("Credito disponible"),});
                    tabla.setModel(md);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al hacer la consulta: " + e.toString());
            }
        } else if (campo.equals("domicilio")) {
            DefaultTableModel md = new DefaultTableModel();
            ResultSet rs = ProductoDAO.ListarTabla("SELECT \n"
                    + "    id_cliente AS ID,\n"
                    + "    CONCAT(nombre, ' ', apellido1, ' ', apellido2) AS Nombre,\n"
                    + "    CONCAT(calle, ' ', numero_casa, ', ', colonia) AS Domicilio,\n"
                    + "    telefono AS Telefono,\n"
                    + "    deuda_pendiente AS 'Deuda pendiente',\n"
                    + "    credito_disponible AS 'Credito disponible'\n"
                    + "FROM \n"
                    + "    cliente\n"
                    + "WHERE \n"
                    + "    CONCAT(calle, ' ', numero_casa, ', ', colonia) = \"" + filtro + "\";");
            md.setColumnIdentifiers(new Object[]{
                "ID",
                "Nombre",
                "Domicilio",
                "Telefono",
                "Deuda pendiente",
                "Crédito disponible"
            });

            try {
                while (rs.next()) {
                    md.addRow(new Object[]{
                        rs.getInt("ID"),
                        rs.getString("Nombre"),
                        rs.getString("Domicilio"),
                        rs.getString("Telefono"),
                        rs.getFloat("Deuda pendiente"),
                        rs.getFloat("Credito disponible"),});
                    tabla.setModel(md);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al hacer la consulta: " + e.toString());
            }
        } else if (campo.equals("telefono")) {
            DefaultTableModel md = new DefaultTableModel();
            ResultSet rs = ProductoDAO.ListarTabla("SELECT \n"
                    + "    id_cliente AS ID,\n"
                    + "    CONCAT(nombre, ' ', apellido1, ' ', apellido2) AS Nombre,\n"
                    + "    CONCAT(calle, ' ', numero_casa, ', ', colonia) AS Domicilio,\n"
                    + "    telefono AS Telefono,\n"
                    + "    deuda_pendiente AS 'Deuda pendiente',\n"
                    + "    credito_disponible AS 'Credito disponible'\n"
                    + "FROM \n"
                    + "    cliente\n"
                    + "WHERE \n"
                    + "    telefono = \"" + filtro + "\";");
            md.setColumnIdentifiers(new Object[]{
                "ID",
                "Nombre",
                "Domicilio",
                "Telefono",
                "Deuda pendiente",
                "Crédito disponible"
            });

            try {
                while (rs.next()) {
                    md.addRow(new Object[]{
                        rs.getInt("ID"),
                        rs.getString("Nombre"),
                        rs.getString("Domicilio"),
                        rs.getString("Telefono"),
                        rs.getFloat("Deuda pendiente"),
                        rs.getFloat("Credito disponible"),});
                    tabla.setModel(md);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al hacer la consulta: " + e.toString());
            }
        } else {
            DefaultTableModel md = new DefaultTableModel();
            ResultSet rs = ProductoDAO.ListarTabla("SELECT \n"
                    + "    id_cliente AS ID,\n"
                    + "    CONCAT(nombre, ' ', apellido1, ' ', apellido2) AS Nombre,\n"
                    + "    CONCAT(calle, ' ', numero_casa, ', ', colonia) AS Domicilio,\n"
                    + "    telefono AS Telefono,\n"
                    + "    deuda_pendiente AS 'Deuda pendiente',\n"
                    + "    credito_disponible AS 'Credito disponible'\n"
                    + "FROM \n"
                    + "    cliente\n"
                    + "WHERE \n"
                    + "    " + campo + " = " + filtro + ";");
            md.setColumnIdentifiers(new Object[]{
                "ID",
                "Nombre",
                "Domicilio",
                "Telefono",
                "Deuda pendiente",
                "Crédito disponible"
            });

            try {
                while (rs.next()) {
                    md.addRow(new Object[]{
                        rs.getInt("ID"),
                        rs.getString("Nombre"),
                        rs.getString("Domicilio"),
                        rs.getString("Telefono"),
                        rs.getFloat("Deuda pendiente"),
                        rs.getFloat("Credito disponible"),});
                    tabla.setModel(md);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al hacer la consulta: " + e.toString());
            }
        }
    }

    public static void ConsultarFiltroV2(JTable tabla, String campo, String filtro) {
        if (campo.equals("nombre") || campo.equals("apellido1") || campo.equals("apellido2")
                || campo.equals("calle") || campo.equals("numero_casa") || campo.equals("colonia")
                || campo.equals("telefono")) {
            // Define tus columnas
            String[] columnNames = {
                "ID",
                "Nombre",
                "Primer apellido",
                "Segundo apellido",
                "Deuda pendiente",
                "Crédito disponible",
                "Calle",
                "Número de casa",
                "Colonia",
                "Telefono"
            };

            // Inicializar modelo de tabla personalizado
            TablaPersonalizada md = new TablaPersonalizada(null, columnNames);

            ResultSet rs = ProductoDAO.ListarTabla("SELECT * FROM bd_inventario.cliente\n"
                    + "WHERE " + campo + " = \"" + filtro + "\";");

            try {
                while (rs.next()) {
                    md.addRow(new Object[]{
                        rs.getInt("id_cliente"),
                        rs.getString("nombre"),
                        rs.getString("apellido1"),
                        rs.getString("apellido2"),
                        rs.getFloat("deuda_pendiente"),
                        rs.getFloat("credito_disponible"),
                        rs.getString("calle"),
                        rs.getString("numero_casa"),
                        rs.getString("colonia"),
                        rs.getString("telefono")
                    });
                }
                // Asigna el modelo personalizado a la tabla
                tabla.setModel(md);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al hacer la consulta: " + e.toString());
            }
        } else {
            String[] columnNames = {
                "ID",
                "Nombre",
                "Primer apellido",
                "Segundo apellido",
                "Deuda pendiente",
                "Crédito disponible",
                "Calle",
                "Número de casa",
                "Colonia",
                "Telefono"
            };

            // Inicializar modelo de tabla personalizado
            TablaPersonalizada md = new TablaPersonalizada(null, columnNames);

            ResultSet rs = ProductoDAO.ListarTabla("SELECT * FROM bd_inventario.cliente\n"
                    + "WHERE " + campo + " = " + filtro + ";");

            try {
                while (rs.next()) {
                    md.addRow(new Object[]{
                        rs.getInt("id_cliente"),
                        rs.getString("nombre"),
                        rs.getString("apellido1"),
                        rs.getString("apellido2"),
                        rs.getFloat("deuda_pendiente"),
                        rs.getFloat("credito_disponible"),
                        rs.getString("calle"),
                        rs.getString("numero_casa"),
                        rs.getString("colonia"),
                        rs.getString("telefono")
                    });
                }
                // Asigna el modelo personalizado a la tabla
                tabla.setModel(md);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al hacer la consulta: " + e.toString());
            }
        }
    }

    public static void Modificar(Cliente c) {
        Connection con = conectar.establecerConexion();

        try {
            String sql = "UPDATE cliente SET nombre=?, apellido1=?, apellido2=?, deuda_pendiente=?, credito_disponible=?, calle=?, numero_casa=?, colonia=?, telefono=? WHERE id_cliente=?";
            PreparedStatement pstmt = con.prepareStatement(sql);

            // Asignar los valores para cada columna en la base de datos
            pstmt.setString(1, c.getNombre());
            pstmt.setString(2, c.getApellido1());
            pstmt.setString(3, c.getApellido2());
            pstmt.setFloat(4, c.getDeuda_pendiente());
            pstmt.setFloat(5, c.getCredito_disponible());
            pstmt.setString(6, c.getCalle());            // nuevo campo calle
            pstmt.setString(7, c.getNumero_casa());        // nuevo campo numero_casa
            pstmt.setString(8, c.getColonia());           // nuevo campo colonia
            pstmt.setString(9, c.getTelefono());          // nuevo campo telefono
            pstmt.setInt(10, c.getId_cliente());  // id_cliente

            pstmt.executeUpdate();
            con.close();
            JOptionPane.showMessageDialog(null, "!Datos del cliente actualizado!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al modificar el registro " + e.toString());
        }
    }

    public static boolean Agregar(Cliente c) {
        try {
            Connection con = conectar.establecerConexion();
            String sql = "INSERT INTO cliente (nombre, apellido1, apellido2, deuda_pendiente, credito_disponible, calle, numero_casa, colonia, telefono) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, c.getNombre());
            pstmt.setString(2, c.getApellido1());
            pstmt.setString(3, c.getApellido2());
            pstmt.setFloat(4, c.getDeuda_pendiente());
            pstmt.setFloat(5, c.getCredito_disponible());
            pstmt.setString(6, c.getCalle());
            pstmt.setString(7, c.getNumero_casa());
            pstmt.setString(8, c.getColonia());
            pstmt.setString(9, c.getTelefono());
            pstmt.executeUpdate();
            con.close();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al querer hacer el registro: " + e.toString());
            return false;
        }
    }
    
    public static boolean eliminarCliente(int idCliente) {
        String sql = "DELETE FROM cliente WHERE id_cliente = ?";
        try (Connection conn = conectar.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idCliente);
            int filasAfectadas = ps.executeUpdate();

            return filasAfectadas > 0;

        } catch (Exception e) {
            System.out.println("Error al eliminar el cliente: " + e.getMessage());
            return false;
        }
    }

}
