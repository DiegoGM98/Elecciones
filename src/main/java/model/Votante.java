package model;

public class Votante {
    private int id;
    private String nombreCompleto;
    private String email;
    private String direccion;

    public Votante(int id, String nombreCompleto, String email, String direccion) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.direccion = direccion;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}

