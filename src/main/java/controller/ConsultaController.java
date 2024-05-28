package controller;

import model.Eleccion;
import model.data.DBConnector;
import model.data.DBGenerator;
import model.data.dao.EleccionDAO;
import org.jooq.DSLContext;

import java.util.List;

public class ConsultaController {

    public static List<Eleccion> obtenerElecciones() throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD("Elecciones");
        List<Eleccion> elecciones = EleccionDAO.obtenerTodasElecciones(query);
        DBConnector.closeConnection();
        return elecciones;
    }

    // Otros métodos de consulta según sea necesario...
}
