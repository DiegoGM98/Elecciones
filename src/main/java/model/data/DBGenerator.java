package model.data;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;

import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.*;

public class DBGenerator {

    public static void iniciarBD(String nombreBD) throws ClassNotFoundException {
        Connection connection = DBConnector.connection("", "root", "");
        DSLContext create = DSL.using(connection, SQLDialect.H2);
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
        return DSL.using(connection, SQLDialect.H2);
    }

    private static void crearBaseDato(DSLContext create, String nombreBD) {
        create.createDatabaseIfNotExists(nombreBD).execute();
    }

    private static DSLContext actualizarConexion(Connection connection, String nombreBD) throws ClassNotFoundException {
        DBConnector.closeConnection();
        connection = DBConnector.connection(nombreBD, "root", "");
        return DSL.using(connection, SQLDialect.H2);
    }

    private static void crearTablaEleccion(DSLContext create) {
        create.createTableIfNotExists(name("Eleccion"))
                .column(name("id"), INTEGER.identity(true))
                .column(name("nombre"), VARCHAR(100))
                .column(name("fecha"), DATE)
                .column(name("lugar"), VARCHAR(100))
                .constraint(primaryKey("id"))
                .execute();
    }

    private static void crearTablaCandidato(DSLContext create) {
        create.createTableIfNotExists(name("Candidato"))
                .column(name("id"), INTEGER.identity(true))
                .column(name("nombre_completo"), VARCHAR(100))
                .column(name("partido_politico"), VARCHAR(100))
                .column(name("cargo"), VARCHAR(50))
                .constraint(primaryKey("id"))
                .execute();
    }

    private static void crearTablaVotante(DSLContext create) {
        create.createTableIfNotExists(name("Votante"))
                .column(name("id"), INTEGER.identity(true))
                .column(name("nombre_completo"), VARCHAR(100))
                .column(name("email"), VARCHAR(100))
                .column(name("direccion"), VARCHAR(100))
                .constraint(primaryKey("id"))
                .execute();
    }

    private static void crearTablaVoto(DSLContext create) {
        create.createTableIfNotExists(name("Voto"))
                .column(name("id"), INTEGER.identity(true))
                .column(name("votante_id"), INTEGER)
                .column(name("candidato_id"), INTEGER)
                .column(name("eleccion_id"), INTEGER)
                .column(name("fecha"), DATE)
                .constraint(primaryKey("id"))
                .constraint(foreignKey("votante_id").references(name("Votante"), name("id")))
                .constraint(foreignKey("candidato_id").references(name("Candidato"), name("id")))
                .constraint(foreignKey("eleccion_id").references(name("Eleccion"), name("id")))
                .execute();
    }

    private static void relacionarTabla(DSLContext create, String nombreTabla, String claveForanea, String nombreTablaRelacion) {
        create.alterTable(name(nombreTabla))
                .add(foreignKey(name(claveForanea)).references(name(nombreTablaRelacion), name("id")))
                .execute();
    }
}


