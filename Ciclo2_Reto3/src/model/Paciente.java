package model;

public class Paciente extends Persona{
    private String eps;
    private String enfermedad;

    public Paciente(String nombre, Long cedula, int edad, String ciudad, String eps, String enfermedad) {
        super(nombre, cedula, edad, ciudad);
        this.eps = eps;
        this.enfermedad = enfermedad;
    }
    public String clasificarEdad() {
        String clasificado = "sin clasificar";
        if (this.edad >= 21 && this.edad <= 30) {
            clasificado = "Joven adulto";
        } else if (this.edad > 30 && this.edad <= 60) {
            clasificado = "Adulto";
        } else if (this.edad > 60) {
            clasificado = "Tercera edad";
        }
        return clasificado;
    }
    public String getEps() {
        return eps;
    }
    public void setEps(String eps) {
        this.eps = eps;
    }
    public String getEnfermedad() {
        return enfermedad;
    }
    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }
}
