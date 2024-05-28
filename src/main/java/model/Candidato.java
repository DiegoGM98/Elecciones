package model;

public class Candidato {
    private int id;
    private String nombreCompleto;
    private String partidoPolitico;
    private String cargo;

    public Candidato(int id, String nombreCompleto, String partidoPolitico, String cargo) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.partidoPolitico = partidoPolitico;
        this.cargo = cargo;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getPartidoPolitico() {
        return partidoPolitico;
    }

    public void setPartidoPolitico(String partidoPolitico) {
        this.partidoPolitico = partidoPolitico;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}

