package modelo;

import modelo.actividades.Actividad;
import modelo.actividades.Charla;
import modelo.actividades.Taller;

import java.util.ArrayList;
import java.util.List;

public class EventoUniversitario {
    private final String Id;
    private String titulo;
    private double costoBase;
    private boolean gratuito;
    private Sala sala;
    private List<Actividad> actividades;

    private static int cantidadEventos = 0;

    public EventoUniversitario(String Id, String titulo, double costoBase, boolean gratuito){
        this.Id = Id;
        this.titulo = titulo;
        this.costoBase = costoBase;
        this.gratuito = gratuito;
        cantidadEventos++;
        this.actividades = new ArrayList<>();
    }

    public EventoUniversitario(EventoUniversitario copiaEvento) {
        this(copiaEvento.Id + "-COPIA", copiaEvento.titulo, copiaEvento.costoBase, copiaEvento.gratuito);
        cantidadEventos--; // una copia no es un evento nuevo, no debe sumar al contador
        this.actividades = new ArrayList<>(copiaEvento.actividades);
        this.sala = copiaEvento.sala;
    }

    public static int getCantidadEventos() {
        return cantidadEventos;
    }

    public double calcularCostoEstimado() {
        if (gratuito) return 0;
        double totalActividades = 0;
        for (Actividad a : actividades) {
            totalActividades += a.calcularCostoMateriales();
        }
        return (costoBase + totalActividades) * 1.21;
    }

    public void asignarSala(Sala sala) {
        this.sala = sala;
    }

    public void crearActividad(String titulo, int cupo, String tipo, String disertanteOrNull, boolean requiereNotebook) {
        Actividad actividad;
        if (tipo.equalsIgnoreCase("modelo.actividades.Charla")) {
            actividad = new Charla(titulo, cupo, disertanteOrNull);
        } else if (tipo.equalsIgnoreCase("modelo.actividades.Taller")) {
            actividad = new Taller(titulo, cupo, requiereNotebook);
        } else {
            throw new IllegalArgumentException("Tipo de actividad desconocido: " + tipo);
        }
        actividades.add(actividad);
    }

    public void mostrarActividades() {
        for (Actividad actividad: actividades) {
            actividad.mostrarIdentificacion();
        }
    }

    public void mostrarDatos() {
        System.out.println(this);
        System.out.println("Costo estimado: $" + calcularCostoEstimado());
        if (!actividades.isEmpty()) {
            System.out.println("Actividades:");
            mostrarActividades();
        }
    }

    // Usado solamente para poblar
    public List<Actividad> getActividades() {
        return actividades;
    }

    @Override
    public String toString() {
        return "Evento [ID: " + Id +
                ", Título: " + titulo +
                ", Costo: " + costoBase +
                ", Gratuito: " + (gratuito ? "Si" : "No") +
                ", modelo.Sala: " + (sala != null ? sala.getNombre() : "Sin asignar") + "]";
    }

}


