package model;

import java.util.Date;

public class Voto {
    private int id;
    private int votanteId;
    private int candidatoId;
    private int eleccionId;
    private Date fecha;

    public Voto(int id, int votanteId, int candidatoId, int eleccionId, Date fecha) {
        this.id = id;
        this.votanteId = votanteId;
        this.candidatoId = candidatoId;
        this.eleccionId = eleccionId;
        this.fecha = fecha;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVotanteId() {
        return votanteId;
    }

    public void setVotanteId(int votanteId) {
        this.votanteId = votanteId;
    }

    public int getCandidatoId() {
        return candidatoId;
    }

    public void setCandidatoId(int candidatoId) {
        this.candidatoId = candidatoId;
    }

    public int getEleccionId() {
        return eleccionId;
    }

    public void setEleccionId(int eleccionId) {
        this.eleccionId = eleccionId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}

