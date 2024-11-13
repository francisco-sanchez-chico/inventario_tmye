package models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.ConexionMysql;

/**
 *
 * @author Chico
 */
public class VentaDAO {

    private static ConexionMysql conectar = new ConexionMysql();

    public VentaDAO() {
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

    public static void consultarTodov2(JTable tabla) {
        // Define tus columnas
        String[] columnNames = {
            "ID venta",
            "ID Cliente",
            "Nombre del cliente",
            "Domicilio",
            "Telefono",
            "Producto vendido",
            "Tipo producto",
            "Cantidad vendido",
            "Precio",
            "Monto pagado",
            "Deuda pendiente",
            "Fecha de venta"
        };

        // Inicializar modelo de tabla personalizado
        TablaPersonalizada md = new TablaPersonalizada(null, columnNames);

        ResultSet rs = ProductoDAO.ListarTabla("SELECT \n"
                + "    v.id_venta,\n"
                + "    v.id_cliente,\n"
                + "    CONCAT(c.nombre, ' ', c.apellido1, ' ', c.apellido2) AS nombre_cliente,\n"
                + "    CONCAT(c.calle, ' ', c.numero_casa, ', ', c.colonia) AS domicilio,\n"
                + "    c.telefono AS Telefono,\n"
                + "    p.nombre AS producto_vendido,\n"
                + "    tp.nombre_tipo AS tipo_producto,\n"
                + "    v.cantidad AS cantidad_vendida,\n"
                + "    p.precio_venta AS precio_venta,\n"
                + "    v.pago_inicial AS monto_pagado,\n"
                + "    ((p.precio_venta * v.cantidad) - v.pago_inicial) AS deuda_pendiente,\n"
                + "    v.fecha AS fecha_venta\n"
                + "FROM \n"
                + "    venta v\n"
                + "JOIN \n"
                + "    cliente c ON v.id_cliente = c.id_cliente\n"
                + "JOIN \n"
                + "    producto p ON v.id_producto = p.id_producto\n"
                + "JOIN \n"
                + "    tipo_producto tp ON p.id_tipo_producto = tp.id_tipo_producto;");

        try {
            while (rs.next()) {
                md.addRow(new Object[]{
                    rs.getInt("id_venta"),
                    rs.getInt("id_cliente"),
                    rs.getString("nombre_cliente"),
                    rs.getString("domicilio"),
                    rs.getString("Telefono"),
                    rs.getString("Producto_vendido"),
                    rs.getString("tipo_producto"),
                    rs.getInt("cantidad_vendida"),
                    rs.getFloat("precio_venta"),
                    rs.getFloat("monto_pagado"),
                    rs.getFloat("deuda_pendiente"),
                    rs.getDate("fecha_venta")
                });
            }
            // Asigna el modelo personalizado a la tabla
            tabla.setModel(md);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al hacer la consulta: " + e.toString());
        }
    }

    public static void consultarTodo(JTable tabla) {
        DefaultTableModel md = new DefaultTableModel();
        ResultSet rs = ProductoDAO.ListarTabla("SELECT * FROM bd_inventario.venta;");
        md.setColumnIdentifiers(new Object[]{
            "ID Venta",
            "ID Cliente",
            "Fecha",
            "Total",
            "Pago inicial",
            "Pago a crédito"
        });

        try {
            while (rs.next()) {
                md.addRow(new Object[]{
                    rs.getInt("id_venta"),
                    rs.getInt("id_cliente"),
                    rs.getDate("fecha"),
                    rs.getFloat("total"),
                    rs.getFloat("pago_inicial"),
                    rs.getFloat("pago_credito"),});
                tabla.setModel(md);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al hacer la consulta: " + e.toString());
        }
    }

    public static void consultarFiltro(JTable tabla, String tTabla, String campo, String filtro) {
        if (campo.equals("fecha") || campo.equals("telefono") || (campo.equals("nombre")&& tTabla.equals("p"))
                || campo.equals("telefono") || campo.equals("telefono")) {
            // Define tus columnas
            String[] columnNames = {
                "ID venta",
                "ID Cliente",
                "Nombre del cliente",
                "Domicilio",
                "Telefono",
                "Producto vendido",
                "Tipo producto",
                "Cantidad vendido",
                "Precio",
                "Monto pagado",
                "Deuda pendiente",
                "Fecha de venta"
            };

            // Inicializar modelo de tabla personalizado
            TablaPersonalizada md = new TablaPersonalizada(null, columnNames);

            ResultSet rs = ProductoDAO.ListarTabla("SELECT \n"
                    + "    v.id_venta,\n"
                    + "    v.id_cliente,\n"
                    + "    CONCAT(c.nombre, ' ', c.apellido1, ' ', c.apellido2) AS nombre_cliente,\n"
                    + "    CONCAT(c.calle, ' ', c.numero_casa, ', ', c.colonia) AS domicilio,\n"
                    + "    c.telefono AS Telefono,\n"
                    + "    p.nombre AS producto_vendido,\n"
                    + "    tp.nombre_tipo AS tipo_producto,\n"
                    + "    v.cantidad AS cantidad_vendida,\n"
                    + "    p.precio_venta AS precio_venta,\n"
                    + "    v.pago_inicial AS monto_pagado,\n"
                    + "    ((p.precio_venta * v.cantidad) - v.pago_inicial) AS deuda_pendiente,\n"
                    + "    v.fecha AS fecha_venta\n"
                    + "FROM \n"
                    + "    venta v\n"
                    + "JOIN \n"
                    + "    cliente c ON v.id_cliente = c.id_cliente\n"
                    + "JOIN \n"
                    + "    producto p ON v.id_producto = p.id_producto\n"
                    + "JOIN \n"
                    + "    tipo_producto tp ON p.id_tipo_producto = tp.id_tipo_producto\n"
                    + "WHERE \n"
                    + "    " + tTabla + "." + campo + " = \"" + filtro + "\";");

            try {
                while (rs.next()) {
                    md.addRow(new Object[]{
                        rs.getInt("id_venta"),
                        rs.getInt("id_cliente"),
                        rs.getString("nombre_cliente"),
                        rs.getString("domicilio"),
                        rs.getString("Telefono"),
                        rs.getString("Producto_vendido"),
                        rs.getString("tipo_producto"),
                        rs.getInt("cantidad_vendida"),
                        rs.getFloat("precio_venta"),
                        rs.getFloat("monto_pagado"),
                        rs.getFloat("deuda_pendiente"),
                        rs.getDate("fecha_venta")
                    });
                }
                // Asigna el modelo personalizado a la tabla
                tabla.setModel(md);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al hacer la consulta: " + e.toString());
            }
        } else if (campo.equals("nombre")) {
            // Define tus columnas
            String[] columnNames = {
                "ID venta",
                "ID Cliente",
                "Nombre del cliente",
                "Domicilio",
                "Telefono",
                "Producto vendido",
                "Tipo producto",
                "Cantidad vendido",
                "Precio",
                "Monto pagado",
                "Deuda pendiente",
                "Fecha de venta"
            };

            // Inicializar modelo de tabla personalizado
            TablaPersonalizada md = new TablaPersonalizada(null, columnNames);

            ResultSet rs = ProductoDAO.ListarTabla("SELECT \n"
                    + "    v.id_venta,\n"
                    + "    v.id_cliente,\n"
                    + "    CONCAT(c.nombre, ' ', c.apellido1, ' ', c.apellido2) AS nombre_cliente,\n"
                    + "    CONCAT(c.calle, ' ', c.numero_casa, ', ', c.colonia) AS domicilio,\n"
                    + "    c.telefono AS Telefono,\n"
                    + "    p.nombre AS producto_vendido,\n"
                    + "    tp.nombre_tipo AS tipo_producto,\n"
                    + "    v.cantidad AS cantidad_vendida,\n"
                    + "    p.precio_venta AS precio_venta,\n"
                    + "    v.pago_inicial AS monto_pagado,\n"
                    + "    ((p.precio_venta * v.cantidad) - v.pago_inicial) AS deuda_pendiente,\n"
                    + "    v.fecha AS fecha_venta\n"
                    + "FROM \n"
                    + "    venta v\n"
                    + "JOIN \n"
                    + "    cliente c ON v.id_cliente = c.id_cliente\n"
                    + "JOIN \n"
                    + "    producto p ON v.id_producto = p.id_producto\n"
                    + "JOIN \n"
                    + "    tipo_producto tp ON p.id_tipo_producto = tp.id_tipo_producto\n"
                    + "WHERE \n"
                    + "    CONCAT(c.nombre, ' ', c.apellido1, ' ', c.apellido2) = \"" + filtro + "\";");

            try {
                while (rs.next()) {
                    md.addRow(new Object[]{
                        rs.getInt("id_venta"),
                        rs.getInt("id_cliente"),
                        rs.getString("nombre_cliente"),
                        rs.getString("domicilio"),
                        rs.getString("Telefono"),
                        rs.getString("Producto_vendido"),
                        rs.getString("tipo_producto"),
                        rs.getInt("cantidad_vendida"),
                        rs.getFloat("precio_venta"),
                        rs.getFloat("monto_pagado"),
                        rs.getFloat("deuda_pendiente"),
                        rs.getDate("fecha_venta")
                    });
                }
                // Asigna el modelo personalizado a la tabla
                tabla.setModel(md);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al hacer la consulta: " + e.toString());
            }
        } else if (campo.equals("domicilio")) {
            // Define tus columnas
            String[] columnNames = {
                "ID venta",
                "ID Cliente",
                "Nombre del cliente",
                "Domicilio",
                "Telefono",
                "Producto vendido",
                "Tipo producto",
                "Cantidad vendido",
                "Precio",
                "Monto pagado",
                "Deuda pendiente",
                "Fecha de venta"
            };

            // Inicializar modelo de tabla personalizado
            TablaPersonalizada md = new TablaPersonalizada(null, columnNames);

            ResultSet rs = ProductoDAO.ListarTabla("SELECT \n"
                    + "    v.id_venta,\n"
                    + "    v.id_cliente,\n"
                    + "    CONCAT(c.nombre, ' ', c.apellido1, ' ', c.apellido2) AS nombre_cliente,\n"
                    + "    CONCAT(c.calle, ' ', c.numero_casa, ', ', c.colonia) AS domicilio,\n"
                    + "    c.telefono AS Telefono,\n"
                    + "    p.nombre AS producto_vendido,\n"
                    + "    tp.nombre_tipo AS tipo_producto,\n"
                    + "    v.cantidad AS cantidad_vendida,\n"
                    + "    p.precio_venta AS precio_venta,\n"
                    + "    v.pago_inicial AS monto_pagado,\n"
                    + "    ((p.precio_venta * v.cantidad) - v.pago_inicial) AS deuda_pendiente,\n"
                    + "    v.fecha AS fecha_venta\n"
                    + "FROM \n"
                    + "    venta v\n"
                    + "JOIN \n"
                    + "    cliente c ON v.id_cliente = c.id_cliente\n"
                    + "JOIN \n"
                    + "    producto p ON v.id_producto = p.id_producto\n"
                    + "JOIN \n"
                    + "    tipo_producto tp ON p.id_tipo_producto = tp.id_tipo_producto\n"
                    + "WHERE \n"
                    + "    CONCAT(c.calle, ' ', c.numero_casa, ', ', c.colonia) = \"" + filtro + "\";");

            try {
                while (rs.next()) {
                    md.addRow(new Object[]{
                        rs.getInt("id_venta"),
                        rs.getInt("id_cliente"),
                        rs.getString("nombre_cliente"),
                        rs.getString("domicilio"),
                        rs.getString("Telefono"),
                        rs.getString("Producto_vendido"),
                        rs.getString("tipo_producto"),
                        rs.getInt("cantidad_vendida"),
                        rs.getFloat("precio_venta"),
                        rs.getFloat("monto_pagado"),
                        rs.getFloat("deuda_pendiente"),
                        rs.getDate("fecha_venta")
                    });
                }
                // Asigna el modelo personalizado a la tabla
                tabla.setModel(md);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al hacer la consulta: " + e.toString());
            }
        } else if (campo.equals("deuda_pendiente")) {
            // Define tus columnas
            String[] columnNames = {
                "ID venta",
                "ID Cliente",
                "Nombre del cliente",
                "Domicilio",
                "Telefono",
                "Producto vendido",
                "Tipo producto",
                "Cantidad vendido",
                "Precio",
                "Monto pagado",
                "Deuda pendiente",
                "Fecha de venta"
            };

            // Inicializar modelo de tabla personalizado
            TablaPersonalizada md = new TablaPersonalizada(null, columnNames);

            ResultSet rs = ProductoDAO.ListarTabla("SELECT \n"
                    + "    v.id_venta,\n"
                    + "    v.id_cliente,\n"
                    + "    CONCAT(c.nombre, ' ', c.apellido1, ' ', c.apellido2) AS nombre_cliente,\n"
                    + "    CONCAT(c.calle, ' ', c.numero_casa, ', ', c.colonia) AS domicilio,\n"
                    + "    c.telefono AS Telefono,\n"
                    + "    p.nombre AS producto_vendido,\n"
                    + "    tp.nombre_tipo AS tipo_producto,\n"
                    + "    v.cantidad AS cantidad_vendida,\n"
                    + "    p.precio_venta AS precio_venta,\n"
                    + "    v.pago_inicial AS monto_pagado,\n"
                    + "    ((p.precio_venta * v.cantidad) - v.pago_inicial) AS deuda_pendiente,\n"
                    + "    v.fecha AS fecha_venta\n"
                    + "FROM \n"
                    + "    venta v\n"
                    + "JOIN \n"
                    + "    cliente c ON v.id_cliente = c.id_cliente\n"
                    + "JOIN \n"
                    + "    producto p ON v.id_producto = p.id_producto\n"
                    + "JOIN \n"
                    + "    tipo_producto tp ON p.id_tipo_producto = tp.id_tipo_producto\n"
                    + "WHERE \n"
                    + "    ((p.precio_venta * v.cantidad) - v.pago_inicial) = "+filtro+";");

            try {
                while (rs.next()) {
                    md.addRow(new Object[]{
                        rs.getInt("id_venta"),
                        rs.getInt("id_cliente"),
                        rs.getString("nombre_cliente"),
                        rs.getString("domicilio"),
                        rs.getString("Telefono"),
                        rs.getString("Producto_vendido"),
                        rs.getString("tipo_producto"),
                        rs.getInt("cantidad_vendida"),
                        rs.getFloat("precio_venta"),
                        rs.getFloat("monto_pagado"),
                        rs.getFloat("deuda_pendiente"),
                        rs.getDate("fecha_venta")
                    });
                }
                // Asigna el modelo personalizado a la tabla
                tabla.setModel(md);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al hacer la consulta: " + e.toString());
            }
        } else {
            // Define tus columnas
            String[] columnNames = {
                "ID venta",
                "ID Cliente",
                "Nombre del cliente",
                "Domicilio",
                "Telefono",
                "Producto vendido",
                "Tipo producto",
                "Cantidad vendido",
                "Precio",
                "Monto pagado",
                "Deuda pendiente",
                "Fecha de venta"
            };

            // Inicializar modelo de tabla personalizado
            TablaPersonalizada md = new TablaPersonalizada(null, columnNames);

            ResultSet rs = ProductoDAO.ListarTabla("SELECT \n"
                    + "    v.id_venta,\n"
                    + "    v.id_cliente,\n"
                    + "    CONCAT(c.nombre, ' ', c.apellido1, ' ', c.apellido2) AS nombre_cliente,\n"
                    + "    CONCAT(c.calle, ' ', c.numero_casa, ', ', c.colonia) AS domicilio,\n"
                    + "    c.telefono AS Telefono,\n"
                    + "    p.nombre AS producto_vendido,\n"
                    + "    tp.nombre_tipo AS tipo_producto,\n"
                    + "    v.cantidad AS cantidad_vendida,\n"
                    + "    p.precio_venta AS precio_venta,\n"
                    + "    v.pago_inicial AS monto_pagado,\n"
                    + "    ((p.precio_venta * v.cantidad) - v.pago_inicial) AS deuda_pendiente,\n"
                    + "    v.fecha AS fecha_venta\n"
                    + "FROM \n"
                    + "    venta v\n"
                    + "JOIN \n"
                    + "    cliente c ON v.id_cliente = c.id_cliente\n"
                    + "JOIN \n"
                    + "    producto p ON v.id_producto = p.id_producto\n"
                    + "JOIN \n"
                    + "    tipo_producto tp ON p.id_tipo_producto = tp.id_tipo_producto\n"
                    + "WHERE \n"
                    + "    " + tTabla + "." + campo + " = " + filtro + ";");

            try {
                while (rs.next()) {
                    md.addRow(new Object[]{
                        rs.getInt("id_venta"),
                        rs.getInt("id_cliente"),
                        rs.getString("nombre_cliente"),
                        rs.getString("domicilio"),
                        rs.getString("Telefono"),
                        rs.getString("Producto_vendido"),
                        rs.getString("tipo_producto"),
                        rs.getInt("cantidad_vendida"),
                        rs.getFloat("precio_venta"),
                        rs.getFloat("monto_pagado"),
                        rs.getFloat("deuda_pendiente"),
                        rs.getDate("fecha_venta")
                    });
                }
                // Asigna el modelo personalizado a la tabla
                tabla.setModel(md);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al hacer la consulta: " + e.toString());
            }
        }

    }

    public static void Modificar(Venta v) {
        Connection con = conectar.establecerConexion();
        String sql = "UPDATE venta \n"
                + "SET id_cliente=?, fecha=?, total=?, pago_inicial=?, id_producto=?, cantidad=? \n"
                + "WHERE id_venta=?;";

        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, v.getId_cliente());
            pstmt.setDate(2, (Date) v.getFecha());
            pstmt.setFloat(3, v.getTotal());
            pstmt.setFloat(4, v.getPago_inicial());
            pstmt.setInt(5, v.getId_producto());
            pstmt.setInt(6, v.getCantidad());
            pstmt.setInt(7, v.getId_venta());  // id_producto
            pstmt.executeUpdate();
            con.close();
            JOptionPane.showMessageDialog(null, "¡Venta actualizada!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al modificar el registro " + e.toString());
        }
    }

    public static boolean Agregar(Venta v) {
        try {
            Connection con = conectar.establecerConexion();
            String sql = "INSERT INTO venta (id_cliente, fecha, total, pago_inicial, pago_credito, id_producto, cantidad) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, v.getId_cliente());  // id_cliente
            pstmt.setDate(2, (Date) v.getFecha());     // fecha en formato java.sql.Date
            pstmt.setFloat(3, v.getTotal());  // total
            pstmt.setFloat(4, v.getPago_inicial());   // pago_inicial
            pstmt.setFloat(5, v.getPago_credito());   // pago_credito
            pstmt.setInt(6, v.getId_producto());    // id_producto
            pstmt.setInt(7, v.getCantidad());   //cantidad
            pstmt.executeUpdate();

            con.close();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al querer agregar el registro: " + e.toString());
            return false;
        }
    }
    
    public static boolean eliminarVenta(int idVenta) {
    String sql = "DELETE FROM venta WHERE id_venta = ?";
    try (Connection conn = conectar.establecerConexion();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, idVenta);
        int filasAfectadas = ps.executeUpdate();

        return filasAfectadas > 0;

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al borrar registro");
        return false;
    }
}
    
}
