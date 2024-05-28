package principal;

import gui.VentanaPrincipal;
import model.data.DBGenerator;

public class Principal {
    public static void main(String[] args) {
        try {
            // Iniciar la base de datos
            DBGenerator.iniciarBD("Elecciones");

            // Mostrar la ventana principal
            VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
            ventanaPrincipal.setVisible(true); // Mostrar la ventana
        } catch (ClassNotFoundException e) {
            System.err.println("Error al iniciar la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}



