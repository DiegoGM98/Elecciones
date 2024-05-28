package model.data.dao;

import model.Voto;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.Table;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

import static org.jooq.impl.DSL.*;

public class VotoDAO {

    public static void agregarVoto(DSLContext query, Voto voto) {
        Table tablaVoto = table(name("Voto"));
        query.insertInto(tablaVoto,
                        field("votante_id"),
                        field("candidato_id"),
                        field("eleccion_id"),
                        field("fecha"))
                .values(voto.getVotanteId(),
                        voto.getCandidatoId(),
                        voto.getEleccionId(),
                        voto.getFecha())
                .execute();
    }

    public static void modificarVoto(DSLContext query, int id, String columnaTabla, Object dato) {
        query.update(table("Voto"))
                .set(field(columnaTabla), dato)
                .where(field("id").eq(id))
                .execute();
    }

    public static List<Voto> obtenerVoto(DSLContext query, String columnaTabla, Object dato) {
        Result resultados = query.select().from(table("Voto"))
                .where(field(columnaTabla).eq(dato))
                .fetch();
        return obtenerListaVotos(resultados);
    }
    public static void eliminarVoto(DSLContext query, int id) {
        query.delete(table("Voto"))
                .where(field("id").eq(id))
                .execute();
    }

    private static List<Voto> obtenerListaVotos(Result resultados) {
        List<Voto> votos = new ArrayList<>();
        for (int fila = 0; fila < resultados.size(); fila++) {
            int id = (int) resultados.getValue(fila, "id");
            int votanteId = (int) resultados.getValue(fila, "votante_id");
            int candidatoId = (int) resultados.getValue(fila, "candidato_id");
            int eleccionId = (int) resultados.getValue(fila, "eleccion_id");
            java.sql.Date fecha = (java.sql.Date) resultados.getValue(fila, "fecha");
            votos.add(new Voto(id, votanteId, candidatoId, eleccionId, fecha));
        }
        return votos;
    }
}


