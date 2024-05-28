package model.data.dao;

import model.Eleccion;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Result;

import java.util.ArrayList;
import java.util.List;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

public class EleccionDAO {

    public static void agregarEleccion(DSLContext query, Eleccion eleccion) {
        query.insertInto(table("Eleccion"),
                        field("nombre"),
                        field("fecha"),
                        field("lugar"))
                .values(eleccion.getNombre(),
                        eleccion.getFecha(),
                        eleccion.getLugar())
                .execute();
    }

    public static boolean validarExistenciaEleccion(DSLContext query, String columnaTabla, Object dato) {
        Record1<Integer> resultado = query.selectCount()
                .from(table("Eleccion"))
                .where(field(columnaTabla).eq(dato))
                .fetchOne();
        return resultado != null && resultado.component1() > 0;
    }

    public static List<Eleccion> obtenerTodasElecciones(DSLContext query) {
        Result resultados = query.select()
                .from(table("Eleccion"))
                .fetch();
        return obtenerListaElecciones(resultados);
    }

    public static void eliminarEleccion(DSLContext query, int id) {
        query.delete(table("Eleccion"))
                .where(field("id").eq(id))
                .execute();
    }

    private static List<Eleccion> obtenerListaElecciones(Result resultados) {
        List<Eleccion> elecciones = new ArrayList<>();
        for (int fila = 0; fila < resultados.size(); fila++) {
            int id = (int) resultados.getValue(fila, "id");
            String nombre = (String) resultados.getValue(fila, "nombre");
            java.sql.Date fecha = (java.sql.Date) resultados.getValue(fila, "fecha");
            String lugar = (String) resultados.getValue(fila, "lugar");
            elecciones.add(new Eleccion(id, nombre, fecha, lugar));
        }
        return elecciones;
    }
}


