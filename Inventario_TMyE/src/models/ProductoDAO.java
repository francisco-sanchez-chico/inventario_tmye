package models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Chico
 */
public class ProductoDAO {
    private static ConexionMysql conectar = new ConexionMysql();

    public ProductoDAO() {
    }
    
    public static ResultSet ListarTabla(String consulta){
        Connection cn = conectar.establecerConexion();
        Statement sql;
        ResultSet datos = null;
        
        try{
            sql = cn.createStatement();
            datos = sql.executeQuery(consulta);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error al tratar de listar los registros "+e.toString());
        }
        return datos;
    }
    
    public static void consultarTodov2(JTable tabla) {
        // Define tus columnas
        String[] columnNames = {
            "ID Producto",
            "Nombre producto",
            "Tipo producto",
            "Tamaño",
            "Almacen",
            "Precio de venta",
            "Costo a empresa",
            "Fecha de llegada",
            "Cantidad en inventario",
            "Porcentaje de ganancia"
        };

        // Inicializar modelo de tabla personalizado
        TablaPersonalizada md = new TablaPersonalizada(null, columnNames);

        ResultSet rs = ProductoDAO.ListarTabla("SELECT \n"
                + "    p.id_producto,\n"
                + "    p.nombre AS nombre_producto,\n"
                + "    tp.nombre_tipo AS tipo_producto,\n"
                + "    t.tamano AS tamano,\n"
                + "    a.almacen AS almacen,\n"
                + "    p.precio_venta,\n"
                + "    p.costo_empresa,\n"
                + "    p.fecha_llegada,\n"
                + "    p.cantidad_inventario,\n"
                + "    ((p.precio_venta - p.costo_empresa) / p.costo_empresa) * 100 AS porcentaje_ganancia\n"
                + "FROM \n"
                + "    producto p\n"
                + "JOIN \n"
                + "    tipo_producto tp ON p.id_tipo_producto = tp.id_tipo_producto\n"
                + "JOIN \n"
                + "    tamano t ON p.id_tamano = t.id_tamano\n"
                + "JOIN \n"
                + "    almacen a ON p.id_almacen = a.id_almacen\n"
                + "ORDER BY \n"
                + "    p.nombre;");

        try {
            while (rs.next()) {
                md.addRow(new Object[]{
                    rs.getInt("id_producto"),
                    rs.getString("nombre_producto"),
                    rs.getString("tipo_producto"),
                    rs.getString("tamano"),
                    rs.getString("almacen"),
                    rs.getFloat("precio_venta"),
                    rs.getFloat("costo_empresa"),
                    rs.getDate("fecha_llegada"),
                    rs.getInt("cantidad_inventario"),
                    rs.getFloat("porcentaje_ganancia")
                });
            }
            // Asigna el modelo personalizado a la tabla
            tabla.setModel(md);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al hacer la consulta: " + e.toString());
        }
    }
    
    public static void consultarTodo(JTable tabla){
        DefaultTableModel md = new DefaultTableModel();
                ResultSet rs = ProductoDAO.ListarTabla("SELECT *, \n"
                        + "       ((precio_venta - costo_empresa) / costo_empresa) * 100 AS porcentaje_ganancia\n"
                        + "FROM producto;");
                md.setColumnIdentifiers(new Object[]{
                    "ID Producto",
                    "ID Almacén",
                    "Nombre",
                    "Tamaño",
                    "Precio de venta",
                    "Costo para la empresa",
                    "Fecha de llegada",
                    "Cantidad en inventario",
                    "Tipo de producto",
                    "Porcentaje de ganacia"});

                try {
                    while (rs.next()) {
                        md.addRow(new Object[]{
                            rs.getInt("id_producto"),
                            rs.getInt("id_almacen"),
                            rs.getString("nombre"),
                            rs.getInt("id_tamano"),
                            rs.getFloat("precio_venta"),
                            rs.getFloat("costo_empresa"),
                            rs.getDate("fecha_llegada"),
                            rs.getInt("cantidad_inventario"),
                            rs.getInt("id_tipo_producto"),
                            rs.getFloat("porcentaje_ganancia")
                        });
                        tabla.setModel(md);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,"Error al hacer la consulta: "+e.toString());
                }
    }
    
    public static void consultaFiltro(JTable tabla, String campo, String filtro) {
        if (campo.equals("nombre")) {
            DefaultTableModel md = new DefaultTableModel();
            ResultSet rs = ProductoDAO.ListarTabla("SELECT \n"
                    + "    p.id_producto,\n"
                    + "    p.nombre AS nombre_producto,\n"
                    + "    tp.nombre_tipo AS tipo_producto,\n"
                    + "    t.tamano AS tamano,\n"
                    + "    a.almacen AS almacen,\n"
                    + "    p.precio_venta,\n"
                    + "    p.costo_empresa,\n"
                    + "    p.fecha_llegada,\n"
                    + "    p.cantidad_inventario,\n"
                    + "    ((p.precio_venta - p.costo_empresa) / p.costo_empresa) * 100 AS porcentaje_ganancia\n"
                    + "FROM \n"
                    + "    producto p\n"
                    + "JOIN \n"
                    + "    tipo_producto tp ON p.id_tipo_producto = tp.id_tipo_producto\n"
                    + "JOIN \n"
                    + "    tamano t ON p.id_tamano = t.id_tamano\n"
                    + "JOIN \n"
                    + "    almacen a ON p.id_almacen = a.id_almacen\n"
                    + "WHERE \n"
                    + "    p.nombre LIKE CONCAT('%', '"+filtro+"', '%') -- Aquí el ? será el nombre del producto que quieras buscar\n"
                    + "ORDER BY \n"
                    + "    p.nombre;");
            md.setColumnIdentifiers(new Object[]{
                "ID Producto",
                "Nombre producto",
                "Tipo producto",
                "Tamaño",
                "Almacen",
                "Precio de venta",
                "Costo a empresa",
                "Fecha de llegada",
                "Cantidad en inventario",
                "Porcentaje de ganacia"});

            try {
                while (rs.next()) {
                    md.addRow(new Object[]{
                        rs.getInt("id_producto"),
                        rs.getString("nombre_producto"),
                        rs.getString("tipo_producto"),
                        rs.getString("tamano"),
                        rs.getString("almacen"),
                        rs.getFloat("precio_venta"),
                        rs.getFloat("costo_empresa"),
                        rs.getDate("fecha_llegada"),
                        rs.getInt("cantidad_inventario"),
                        rs.getFloat("porcentaje_ganancia")
                    });
                    tabla.setModel(md);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al hacer la consulta: " + e.toString());
            }
        }else if(campo.equals("fecha_llegada")){
            DefaultTableModel md = new DefaultTableModel();
            ResultSet rs = ProductoDAO.ListarTabla("SELECT \n"
                    + "    p.id_producto,\n"
                    + "    p.nombre AS nombre_producto,\n"
                    + "    tp.nombre_tipo AS tipo_producto,\n"
                    + "    t.tamano AS tamano,\n"
                    + "    a.almacen AS almacen,\n"
                    + "    p.precio_venta,\n"
                    + "    p.costo_empresa,\n"
                    + "    p.fecha_llegada,\n"
                    + "    p.cantidad_inventario,\n"
                    + "    ((p.precio_venta - p.costo_empresa) / p.costo_empresa) * 100 AS porcentaje_ganancia\n"
                    + "FROM \n"
                    + "    producto p\n"
                    + "JOIN \n"
                    + "    tipo_producto tp ON p.id_tipo_producto = tp.id_tipo_producto\n"
                    + "JOIN \n"
                    + "    tamano t ON p.id_tamano = t.id_tamano\n"
                    + "JOIN \n"
                    + "    almacen a ON p.id_almacen = a.id_almacen\n"
                    + "WHERE \n"
                    + "    p.fecha_llegada = \""+filtro+"\"\n"
                    + "ORDER BY \n"
                    + "    p.nombre;");
            md.setColumnIdentifiers(new Object[]{
                "ID Producto",
                "Nombre producto",
                "Tipo producto",
                "Tamaño",
                "Almacen",
                "Precio de venta",
                "Costo a empresa",
                "Fecha de llegada",
                "Cantidad en inventario",
                "Porcentaje de ganacia"});

            try {
                while (rs.next()) {
                    md.addRow(new Object[]{
                        rs.getInt("id_producto"),
                        rs.getString("nombre_producto"),
                        rs.getString("tipo_producto"),
                        rs.getString("tamano"),
                        rs.getString("almacen"),
                        rs.getFloat("precio_venta"),
                        rs.getFloat("costo_empresa"),
                        rs.getDate("fecha_llegada"),
                        rs.getInt("cantidad_inventario"),
                        rs.getFloat("porcentaje_ganancia")
                    });
                    tabla.setModel(md);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al hacer la consulta: " + e.toString());
            }
        }else{
            DefaultTableModel md = new DefaultTableModel();
            ResultSet rs = ProductoDAO.ListarTabla("SELECT \n"
                    + "    p.id_producto,\n"
                    + "    p.nombre AS nombre_producto,\n"
                    + "    tp.nombre_tipo AS tipo_producto,\n"
                    + "    t.tamano AS tamano,\n"
                    + "    a.almacen AS almacen,\n"
                    + "    p.precio_venta,\n"
                    + "    p.costo_empresa,\n"
                    + "    p.fecha_llegada,\n"
                    + "    p.cantidad_inventario,\n"
                    + "    ((p.precio_venta - p.costo_empresa) / p.costo_empresa) * 100 AS porcentaje_ganancia\n"
                    + "FROM \n"
                    + "    producto p\n"
                    + "JOIN \n"
                    + "    tipo_producto tp ON p.id_tipo_producto = tp.id_tipo_producto\n"
                    + "JOIN \n"
                    + "    tamano t ON p.id_tamano = t.id_tamano\n"
                    + "JOIN \n"
                    + "    almacen a ON p.id_almacen = a.id_almacen\n"
                    + "WHERE \n"
                    + "    p." + campo + "=" + filtro + "\n"
                    + "ORDER BY \n"
                    + "    p.nombre;");
            md.setColumnIdentifiers(new Object[]{
                "ID Producto",
                "Nombre producto",
                "Tipo producto",
                "Tamaño",
                "Almacen",
                "Precio de venta",
                "Costo a empresa",
                "Fecha de llegada",
                "Cantidad en inventario",
                "Porcentaje de ganacia"});

            try {
                while (rs.next()) {
                    md.addRow(new Object[]{
                        rs.getInt("id_producto"),
                        rs.getString("nombre_producto"),
                        rs.getString("tipo_producto"),
                        rs.getString("tamano"),
                        rs.getString("almacen"),
                        rs.getFloat("precio_venta"),
                        rs.getFloat("costo_empresa"),
                        rs.getDate("fecha_llegada"),
                        rs.getInt("cantidad_inventario"),
                        rs.getFloat("porcentaje_ganancia")
                    });
                    tabla.setModel(md);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al hacer la consulta: " + e.toString());
            }
        }
    } 
    
    public static void Modificar(Producto p){
        Connection con = conectar.establecerConexion();
        String sql = "UPDATE producto SET id_almacen=?, nombre=?, id_tamano=?, precio_venta=?, costo_empresa=?, fecha_llegada=?, cantidad_inventario=?, id_tipo_producto=? WHERE id_producto=?";
        
        try{
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, p.getId_almacen());
            pstmt.setString(2, p.getNombre());
            pstmt.setInt(3, p.getId_tamanio());
            pstmt.setFloat(4, p.getPrecio_venta());
            pstmt.setFloat(5, p.getCosto_empresa());
            pstmt.setDate(6, (Date) p.getFecha_llegada());
            pstmt.setInt(7, p.getCantidad_inventario());
            pstmt.setInt(8, p.getTipo_producto());
            pstmt.setInt(9, p.getId_producto());  // id_producto
            pstmt.executeUpdate();
            con.close();
            JOptionPane.showMessageDialog(null,"¡Producto actualizado!");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error al modificar el registro "+e.toString());
        }
    }
    
    public static boolean Agregar(Producto p){
        try{
            Connection con = conectar.establecerConexion();  
            String sql = "INSERT INTO producto (id_almacen, nombre, id_tamano, precio_venta, costo_empresa, fecha_llegada, cantidad_inventario, id_tipo_producto) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, p.getId_almacen());
            pstmt.setString(2, p.getNombre());
            pstmt.setInt(3, p.getId_tamanio());
            pstmt.setFloat(4, p.getPrecio_venta());
            pstmt.setFloat(5, p.getCosto_empresa());
            pstmt.setDate(6, (Date) p.getFecha_llegada());
            pstmt.setInt(7, p.getCantidad_inventario());
            pstmt.setInt(8,p.getTipo_producto());
            pstmt.executeUpdate();
            con.close();
            return true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error al querer agregar el registro: "+e.toString());
            return false;
        }
    }
    
    public static boolean eliminarProducto(int idProducto) {
        String sql = "DELETE FROM producto WHERE id_producto = ?;";
        try (Connection conn = conectar.establecerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idProducto);
            int filasAfectadas = ps.executeUpdate();

            return filasAfectadas > 0; // Retorna true si se eliminó al menos un registro

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error eliminar registro: "+e.toString());
            return false;
        }
    }
}
