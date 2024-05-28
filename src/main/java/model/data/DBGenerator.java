package model.data;

import org.jooq.DSLContext;
import org.jooq.DataType;
import org.jooq.impl.DSL;

import java.sql.Connection;

import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.*;

public class DBGenerator {

    public static void iniciarBD(String nombreBD) throws ClassNotFoundException {
        Connection connection = DBConnector.connection("", "root", "");
        DSLContext create = DSL.using(connection);
        crearBaseDato(create, nombreBD);
        create = actualizarConexion(connection, nombreBD);
        crearTablaEleccion(create);
        crearTablaCandidato(create);
        crearTablaVotante(create);
        crearTablaVoto(create);
        relacionarTabla(create, "Voto", "votante_id", "Votante");
        relacionarTabla(create, "Voto", "candidato_id", "Candidato");
        relacionarTabla(create, "Voto", "eleccion_id", "Eleccion");
        DBConnector.closeConnection();
    }

    public static DSLContext conectarBD(String nombre) throws ClassNotFoundException {
        Connection connection = DBConnector.connection(nombre, "root", "");
        return DSL.using(connection);
    }

    private static void crearBaseDato(DSLContext create, String nombreBD) {
        create.createDatabaseIfNotExists(nombreBD).execute();
    }

    private static DSLContext actualizarConexion(Connection connection, String nombreBD) {
        DBConnector.closeConnection();
        connection = DBConnector.connection(nombreBD, "root", "");
        return DSL.using(connection);
    }

    private static void crearTablaEleccion(DSLContext create) {
        create.createTableIfNotExists("Eleccion")
                .column("id", INTEGER.identity(true))
                .column("nombre", VARCHAR(100))
                .column("fecha", DATE)
                .column("lugar", VARCHAR(100))
                .constraint(primaryKey("id"))
                .execute();
    }

    private static void crearTablaCandidato(DSLContext create) {
        create.createTableIfNotExists("Candidato")
                .column("id", INTEGER.identity(true))
                .column("nombre_completo", VARCHAR(100))
                .column("partido_politico", VARCHAR(100))
                .column("cargo", VARCHAR(50))
                .constraint(primaryKey("id"))
                .execute();
    }

    private static void crearTablaVotante(DSLContext create) {
        create.createTableIfNotExists("Votante")
                .column("id", INTEGER.identity(true))
                .column("nombre_completo", VARCHAR(100))
                .column("email", VARCHAR(100))
                .column("direccion", VARCHAR(100))
                .constraint(primaryKey("id"))
                .execute();
    }

    private static void crearTablaVoto(DSLContext create) {
        create.createTableIfNotExists("Voto")
                .column("id", INTEGER.identity(true))
                .column("votante_id", INTEGER)
                .column("candidato_id", INTEGER)
                .column("eleccion_id", INTEGER)
                .column("fecha", DATE)
                .constraint(primaryKey("id"))
                .constraint(foreignKey("votante_id").references("Votante", "id"))
                .constraint(foreignKey("candidato_id").references("Candidato", "id"))
                .constraint(foreignKey("eleccion_id").references("Eleccion", "id"))
                .execute();
    }

    private static void relacionarTabla(DSLContext create, String nombreTabla, String claveForanea, String nombreTablaRelacion) {
        create.alterTableIfExists(nombreTabla)
                .addConstraint(foreignKey(claveForanea).references(nombreTablaRelacion, "id"))
                .execute();
    }
}

