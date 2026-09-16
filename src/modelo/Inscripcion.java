package modelo;

import java.time.LocalDate;

public class Inscripcion {
    private Estudiante estudiante;
    private LocalDate fecha;
    private String estado;

    public Inscripcion(Estudiante estudiante, LocalDate fecha, String estado) {
        this.estudiante = estudiante;
        this.fecha = fecha;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "  * " + estudiante.getNombre() + " (Legajo: " + estudiante.getLegajo() +
                ") - Fecha: " + fecha + " - Estado: " + estado;
    }
}
