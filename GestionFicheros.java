import java.awt.Component;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GestionFicheros{
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
                crear();
                break;
            case 1:
                // llamamos al metodo leer
                Leer();
                break;
            case 2:
            // llamamos al metodo escribir
                Escribir();
                break;
            case 3:
            //  metodo salir
                Salida = salir(Salida);
                break;
            default:
                break;
        }
        }while(Salida != true);
    }
    
    private static void crear() throws IOException {
        // Creamos un file JFileChooser para poder seleccionar donde crear el fichero 
        JFileChooser busqueda = new JFileChooser();
        
        // Pone texto a JFilechooser 
        busqueda.setDialogTitle("Crear fichero");

        // Define el directorio 
        int seleccion = busqueda.showOpenDialog(busqueda);

        if(seleccion == JFileChooser.APPROVE_OPTION){//comprueba que el boton a sido accionado para crear el fichero

            busqueda.getSelectedFile().getParent();// comprueba el directorio para definirlo 

            File Fichero = new File(busqueda.getSelectedFile().getAbsolutePath()); // crea el dichero en el directorio 

            //Comprobar si el fichero existe o no existe y si no existe lo crea
            if(Fichero.exists()){
                JOptionPane.showMessageDialog(null, "El fichero ya existe");
            }else{
                Fichero.createNewFile();
            }
        }
    }

    private static void Escribir() throws IOException, FileNotFoundException {
        // Creamos un file JFileChooser para poder seleccionar donde esta el fichero
        JFileChooser busqueda = new JFileChooser();

        // Define el directorio 
        int seleccion = busqueda.showOpenDialog(busqueda);

        if(seleccion == JFileChooser.APPROVE_OPTION){//comprueba que el boton a sido accionado para editar el fichero
            // selecciona el fichero para editarlo
            File Fichero = new File(busqueda.getSelectedFile().getAbsolutePath());

            /* lee linea linea para comprobar que lineas estan escritas y cuales no  */
            try (BufferedReader leer = new BufferedReader(new FileReader(Fichero))) {
                    String linea = leer.readLine();
                    String texto = linea;
                    linea = leer.readLine();
                    while(linea!=null){
                        texto = texto+"\n"+ linea;
                        linea = leer.readLine();
                    }
                    
                    // define un area de texto en la cual se escribira luego
                JTextArea areaTexto = new JTextArea(texto);
                areaTexto.setLineWrap(true);
                areaTexto.setEditable(true);
                // Definimos una scroll bar para que la ventana no de deforme dandole tamaño
                JScrollPane scroll = new JScrollPane();
                scroll.setPreferredSize(new Dimension(300,200));
                scroll.getViewport().setView(areaTexto);

                JOptionPane.showMessageDialog(null, scroll, Fichero.getName(), JOptionPane.PLAIN_MESSAGE);
                FileWriter guardar = new FileWriter(Fichero,false);//reescribimos el fichero 
                BufferedWriter text = new BufferedWriter(guardar);
                text.write(areaTexto.getText());

                text.flush();
                text.close();
                } catch (HeadlessException e) {
                    
                    e.printStackTrace();
                }
            }
    }

    private static void Leer() throws IOException, FileNotFoundException {
       
                // Creamos un file JFileChooser para poder seleccionar donde esta el fichero
        JFileChooser busqueda = new JFileChooser();

                        // Define el directorio 
        int seleccion = busqueda.showOpenDialog(busqueda);
                        //comprueba que el boton a sido accionado para editar el fichero
        if(seleccion == JFileChooser.APPROVE_OPTION){
                    // selecciona el fichero para leer
            File Fichero = new File(busqueda.getSelectedFile().getAbsolutePath());

                try (BufferedReader leer = new BufferedReader(new FileReader(Fichero))) {//lee las lineas una a una seleccionando hasta que se haya leido completamente
                    String linea = leer.readLine();
                    String texto = linea;
                    linea = leer.readLine();
                    while(linea!=null){
                        texto = texto+"\n"+ linea;
                        linea = leer.readLine();
                    }
                    
                    JOptionPane.showMessageDialog(null, texto, Fichero.getName(),JOptionPane.PLAIN_MESSAGE);
                } catch (HeadlessException e) {
                    e.printStackTrace();
                }
            }
    }
    private static boolean salir(boolean Salida) { //definie el metodo salida para salir 
        int ventanaYesNot = JOptionPane.showConfirmDialog(null, "¿Quieres salir del programa?", 
        "Gestión de ficheros", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if(ventanaYesNot == 0 ){
                JOptionPane.showMessageDialog(null,"Has salido con exito");
                Salida = true;
            }
        return Salida;
    }     
} 