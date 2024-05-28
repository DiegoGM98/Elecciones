package model.data.dao;

import model.Eleccion;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.Table;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

import static org.jooq.impl.DSL.*;

public class EleccionDAO {

    public static void agregarEleccion(DSLContext query, Eleccion eleccion) {
        Table tablaEleccion = table(name("Eleccion"));
        query.insertInto(tablaEleccion,
                        field("nombre"),
                        field("fecha"),
                        field("lugar"))
                .values(eleccion.getNombre(),
                        eleccion.getFecha(),
                        eleccion.getLugar())
                .execute();
    }

    public static void modificarEleccion(DSLContext query, int id, String columnaTabla, Object dato) {
        query.update(table("Eleccion"))
                .set(field(columnaTabla), dato)
                .where(field("id").eq(id))
                .execute();
    }

    public static List<Eleccion> obtenerEleccion(DSLContext query, String columnaTabla, Object dato) {
        Result resultados = query.select().from(table("Eleccion"))
                .where(field(columnaTabla).eq(dato))
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

