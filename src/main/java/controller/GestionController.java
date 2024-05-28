package controller;

import model.Candidato;
import model.Eleccion;
import model.data.DBConnector;
import model.data.DBGenerator;
import model.data.dao.CandidatoDAO;
import model.data.dao.EleccionDAO;
import org.jooq.DSLContext;

import java.util.Date;

public class GestionController {

    public static boolean crearEleccion(String nombre, Date fecha, String lugar) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD("Elecciones");
        if (!EleccionDAO.validarExistenciaEleccion(query, "nombre", nombre)) {
            Eleccion eleccion = new Eleccion(0, nombre, fecha, lugar);
            EleccionDAO.agregarEleccion(query, eleccion);
            DBConnector.closeConnection();
            return true;
        } else {
            DBConnector.closeConnection();
            return false;
        }
    }

    public static boolean registrarCandidato(String nombreCompleto, String partidoPolitico, String cargo) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD("Elecciones");
        if (!CandidatoDAO.validarExistenciaCandidato(query, "nombre_completo", nombreCompleto)) {
            Candidato candidato = new Candidato(0, nombreCompleto, partidoPolitico, cargo);
            CandidatoDAO.agregarCandidato(query, candidato);
            DBConnector.closeConnection();
            return true;
        } else {
            DBConnector.closeConnection();
            return false;
        }
    }

    public static boolean registrarVotante(String nombre, String apellido, String dni, String direccion, String preferenciasPoliticas) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD("Elecciones");
        // Lógica para validar la existencia del votante
        // Si no existe, se registra el votante en la base de datos
        // return true si se registra correctamente, false si ya existe
        return false;
    }
}
