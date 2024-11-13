package main;

import views.MainWindow;
import javax.swing.JOptionPane;

/**
 *
 * @author Chico
 */
public class Main {

    public static void main(String[] args) {
        try{
            MainWindow abrir = new MainWindow();
            abrir.setVisible(true);
            abrir.setLocationRelativeTo(null);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error: "+e.toString());
        }
    }
    
}