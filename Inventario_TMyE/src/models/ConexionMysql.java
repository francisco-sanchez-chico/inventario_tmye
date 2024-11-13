package models;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Chico
 */
public class ConexionMysql {
    private Connection conectar = null;
    private String usuario ="root";
    private String contrasenia ="qwerty124";
    private String bd ="bd_inventario";
    private String ip ="localhost";
    private String puerto ="3306";
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String cadena ="jdbc:mysql://"+ip+":"+puerto+"/"+bd;

    public ConexionMysql() {
    }
    
    public Connection establecerConexion(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = DriverManager.getConnection(cadena,usuario,contrasenia);
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"No se conecto a la base de datos Error: "+e.toString());
        }
        return conectar;
    }
    
    public void cerrarConexion(){
        try{
            if(conectar != null){
                conectar.close();
                JOptionPane.showMessageDialog(null,"Conexión a la base de datos terminada");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error al querere terminar la conexión a la base de datos: "+e.toString());
        }
    }
    
    public void exportarBaseDeDatos() {
        // Obtener la fecha y hora actual para agregar al nombre del archivo
        SimpleDateFormat sdf = new SimpleDateFormat("ss_mm_HH_dd_MM_yyyy");
        String timestamp = sdf.format(new Date());
 
        // Ruta del archivo de salida con la extensión .sql
        String archivoSalida = "C:\\Users\\Dell\\Desktop\\esc\\Uni\\6\\Gestion_de_calidad\\programa_testear\\respaldo_bd\\ocm_respaldo_"+timestamp+".sql";

        // Comando mysqldump para exportar la base de datos
        String comando = "mysqldump -u " + this.usuario + " -p" + this.contrasenia + " " + this.bd + " -r " + archivoSalida;

        try {
            // Ejecutar el comando
            Process proceso = Runtime.getRuntime().exec(comando);

            // Esperar a que el proceso termine
            int procesoCompletado = proceso.waitFor();

            // Verificar si el proceso se completó correctamente
            if (procesoCompletado == 0) {
                JOptionPane.showMessageDialog(null,"La base de datos fue exportada exitosamente a: " + archivoSalida);
            } else {
                JOptionPane.showMessageDialog(null,"Hubo un error durante la exportación.");
            }

        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }
  
    public Connection getConectar() {
        return conectar;
    }

    public void setConectar(Connection conectar) {
        this.conectar = conectar;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getBd() {
        return bd;
    }

    public void setBd(String bd) {
        this.bd = bd;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    public PreparedStatement getPs() {
        return ps;
    }

    public void setPs(PreparedStatement ps) {
        this.ps = ps;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }
}