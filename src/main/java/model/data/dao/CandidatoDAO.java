package model.data.dao;

import model.Candidato;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.Table;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

import static org.jooq.impl.DSL.*;

public class CandidatoDAO {

    public static void agregarCandidato(DSLContext query, Candidato candidato) {
        Table tablaCandidato = table(name("Candidato"));
        query.insertInto(tablaCandidato,
                        field("nombre_completo"),
                        field("partido_politico"),
                        field("cargo"))
                .values(candidato.getNombreCompleto(),
                        candidato.getPartidoPolitico(),
                        candidato.getCargo())
                .execute();
    }

    public static void modificarCandidato(DSLContext query, int id, String columnaTabla, Object dato) {
        query.update(table("Candidato"))
                .set(field(columnaTabla), dato)
                .where(field("id").eq(id))
                .execute();
    }

    public static List<Candidato> obtenerCandidato(DSLContext query, String columnaTabla, Object dato) {
        Result resultados = query.select().from(table("Candidato"))
                .where(field(columnaTabla).eq(dato))
                .fetch();
        return obtenerListaCandidatos(resultados);
    }

    public static void eliminarCandidato(DSLContext query, int id) {
        query.delete(table("Candidato"))
                .where(field("id").eq(id))
                .execute();
    }

    private static List<Candidato> obtenerListaCandidatos(Result resultados) {
        List<Candidato> candidatos = new ArrayList<>();
        for (int fila = 0; fila < resultados.size(); fila++) {
            int id = (int) resultados.getValue(fila, "id");
            String nombreCompleto = (String) resultados.getValue(fila, "nombre_completo");
            String partidoPolitico = (String) resultados.getValue(fila, "partido_politico");
            String cargo = (String) resultados.getValue(fila, "cargo");
            candidatos.add(new Candidato(id, nombreCompleto, partidoPolitico, cargo));
        }
        return candidatos;
    }
}
