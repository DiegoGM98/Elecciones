package principal;

import gui.VentanaPrincipal;
import model.data.DBGenerator;

public class Principal {
    public static void main(String[] args) throws ClassNotFoundException {
        // Iniciar la base de datos
        DBGenerator.iniciarBD("Elecciones");

        // Crear e iniciar la ventana principal
        VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
    }
}
