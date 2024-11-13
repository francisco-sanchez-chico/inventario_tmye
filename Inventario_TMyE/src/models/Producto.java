
package models;

import java.sql.Date;

/**
 *
 * @author Chico
 */
public class Producto {
    private int id_producto;
    private int id_almacen;
    private String nombre;
    private int id_tamanio;
    private float precio_venta;
    private float costo_empresa;
    private java.sql.Date fecha_llegada;
    private int cantidad_inventario;
    private int tipo_producto;
    
    public Producto(){
        
    }

    public int getId_producto() {
        return id_producto;
    }

    public int getId_almacen() {
        return id_almacen;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId_tamanio() {
        return id_tamanio;
    }

    public float getPrecio_venta() {
        return precio_venta;
    }

    public float getCosto_empresa() {
        return costo_empresa;
    }

    public java.sql.Date getFecha_llegada() {
        return fecha_llegada;
    }

    public int getCantidad_inventario() {
        return cantidad_inventario;
    }
    
    public int getTipo_producto() {
        return tipo_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public void setId_almacen(int id_almacen) {
        this.id_almacen = id_almacen;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setId_tamanio(int id_tamanio) {
        this.id_tamanio = id_tamanio;
    }

    public void setPrecio_venta(float precio_venta) {
        this.precio_venta = precio_venta;
    }

    public void setCosto_empresa(float costo_empresa) {
        this.costo_empresa = costo_empresa;
    }

    public void setFecha_llegada(java.sql.Date fecha_llegada) {
        this.fecha_llegada = fecha_llegada;
    }

    public void setCantidad_inventario(int cantidad_inventario) {
        this.cantidad_inventario = cantidad_inventario;
    }
    
    public void setTipo_producto(int tipo_producto) {
        this.tipo_producto = tipo_producto;
    }
}
