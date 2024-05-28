package model.data.dao;

import model.Candidato;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Result;

import java.util.ArrayList;
import java.util.List;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

public class CandidatoDAO {

    public static void agregarCandidato(DSLContext query, Candidato candidato) {
        query.insertInto(table("Candidato"),
                        field("nombre_completo"),
                        field("partido_politico"),
                        field("cargo"))
                .values(candidato.getNombreCompleto(),
                        candidato.getPartidoPolitico(),
                        candidato.getCargo())
                .execute();
    }

    public static boolean validarExistenciaCandidato(DSLContext query, String columnaTabla, Object dato) {
        Record1<Integer> resultado = query.selectCount()
                .from(table("Candidato"))
                .where(field(columnaTabla).eq(dato))
                .fetchOne();
        return resultado != null && resultado.component1() > 0;
    }

    public static List<Candidato> obtenerCandidatos(DSLContext query, String columnaTabla, Object dato) {
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
