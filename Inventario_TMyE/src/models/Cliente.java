package models;

/**
 *
 * @author Chico
 */
public class Cliente {
    private int id_cliente;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private float deuda_pendiente;
    private float credito_disponible;
    private String calle;
    private String numero_casa;
    private String colonia;
    private String telefono;

    public Cliente() {
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public float getDeuda_pendiente() {
        return deuda_pendiente;
    }

    public float getCredito_disponible() {
        return credito_disponible;
    }

    public String getCalle() {
        return calle;
    }

    public String getNumero_casa() {
        return numero_casa;
    }

    public String getColonia() {
        return colonia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public void setDeuda_pendiente(float deuda_pendiente) {
        this.deuda_pendiente = deuda_pendiente;
    }

    public void setCredito_disponible(float credito_disponible) {
        this.credito_disponible = credito_disponible;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setNumero_casa(String numero_casa) {
        this.numero_casa = numero_casa;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    
    
    
}
