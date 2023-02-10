import java.awt.Component;
import java.io.IOException;

import javax.swing.JOptionPane;

public class GestionFicheros1{
    //array con los componentes del content panen junto con el contentPane
    static Component contentPane;
    static String[] opciones ={"Crear", "Leer", "Escribir","Salir"};
    
    
    public static void main (String[]args) throws IOException{
       //boolean para el bucle
         boolean Salida= false;
        //inicio de bucle
       do{
           //ventana inicial del Panel
        int ventana = JOptionPane.showOptionDialog(null, "Seleccione lo que desa hacer" , 
        "Gestión de ficheros",JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
        //Swicth con las opciones del array creado anteriormente
        switch (ventana){
            case 0:
            //llamamos al metodo crear
               
                break;
            case 1:
                // llamamos al metodo leer
                
                break;
            case 2:
            // llamamos al metodo escribir
              
                break;
            case 3:
            //  metodo salir
                
                break;
            default:
                break;
        }
        }while(Salida != true);
    }
}
    
   