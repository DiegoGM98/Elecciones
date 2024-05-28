package model.data.dao;

import model.Votante;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.Table;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

import static org.jooq.impl.DSL.*;

public class VotanteDAO {

    public static void agregarVotante(DSLContext query, Votante votante) {
        Table tablaVotante = table(name("Votante"));
        query.insertInto(tablaVotante,
                        field("nombre_completo"),
                        field("email"),
                        field("direccion"))
                .values(votante.getNombreCompleto(),
                        votante.getEmail(),
                        votante.getDireccion())
                .execute();
    }

    public static void modificarVotante(DSLContext query, int id, String columnaTabla, Object dato) {
        query.update(table("Votante"))
                .set(field(columnaTabla), dato)
                .where(field("id").eq(id))
                .execute();
    }

    public static List<Votante> obtenerVotante(DSLContext query, String columnaTabla, Object dato) {
        Result resultados = query.select().from(table("Votante"))
                .where(field(columnaTabla).eq(dato))
                .fetch();
        return obtenerListaVotantes(resultados);
    }

    public static void eliminarVotante(DSLContext query, int id) {
        query.delete(table("Votante"))
                .where(field("id").eq(id))
                .execute();
    }

    private static List<Votante> obtenerListaVotantes(Result resultados) {
        List<Votante> votantes = new ArrayList<>();
        for (int fila = 0; fila < resultados.size(); fila++) {
            int id = (int) resultados.getValue(fila, "id");
            String nombreCompleto = (String) resultados.getValue(fila, "nombre_completo");
            String email = (String) resultados.getValue(fila, "email");
            String direccion = (String) resultados.getValue(fila, "direccion");
            votantes.add(new Votante(id, nombreCompleto, email, direccion));
        }
        return votantes;
    }
}

