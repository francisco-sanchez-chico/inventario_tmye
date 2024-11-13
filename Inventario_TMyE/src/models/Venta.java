package models;

import java.util.Date;

/**
 *
 * @author Chico
 */
public class Venta {
    private int id_venta;
    private int id_cliente;
    private java.sql.Date fecha;
    private float total;
    private float pago_inicial;
    private float pago_credito;
    private int id_producto;
    private int cantidad;

    public Venta() {
    }

    public int getId_venta() {
        return id_venta;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public float getTotal() {
        return total;
    }

    public float getPago_inicial() {
        return pago_inicial;
    }

    public float getPago_credito() {
        return pago_credito;
    }

    public int getId_producto() {
        return id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public void setFecha(java.sql.Date fecha) {
        this.fecha = fecha;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public void setPago_inicial(float pago_inicial) {
        this.pago_inicial = pago_inicial;
    }

    public void setPago_credito(float pago_credito) {
        this.pago_credito = pago_credito;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
    
}
