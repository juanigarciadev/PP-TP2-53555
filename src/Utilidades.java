import modelo.Estudiante;
import modelo.EventoUniversitario;
import modelo.Sala;

import java.util.List;
import java.util.UUID;

public class Utilidades {
    public static void limpiarConsola() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static void poblarSistema(List<Estudiante> estudiantes, List<EventoUniversitario> eventos, List<Sala> salas) {
        // Estudiantes de prueba
        estudiantes.add(new Estudiante("1001", "Ana Pérez"));
        estudiantes.add(new Estudiante("1002", "Juan López"));
        estudiantes.add(new Estudiante("1003", "María Gómez"));

        // Salas de prueba
        Sala sala1 = new Sala(1, "Auditorio Principal");
        Sala sala2 = new Sala(2, "modelo.Sala 3B");
        salas.add(sala1);
        salas.add(sala2);

        // Evento 1: gratuito, con dos modelo.actividades
        EventoUniversitario evento1 = new EventoUniversitario(
                UUID.randomUUID().toString(), "Hackathon UTN", 0, true);
        evento1.asignarSala(sala1);
        evento1.crearActividad("Desarrollo de Prototipo", 20, "modelo.actividades.Taller", null, true);
        evento1.crearActividad("modelo.actividades.Charla de Cierre", 50, "modelo.actividades.Charla", "Ing. Roberto Sosa", false);

        // Evento 2: pago, con una actividad
        EventoUniversitario evento2 = new EventoUniversitario(
                UUID.randomUUID().toString(), "Jornada de Innovación", 500.0, false);
        evento2.asignarSala(sala2);
        evento2.crearActividad("modelo.actividades.Taller de Robótica", 15, "modelo.actividades.Taller", null, true);

        eventos.add(evento1);
        eventos.add(evento2);

        // Inscripciones de prueba
        evento1.getActividades().get(0).inscribir(estudiantes.get(0)); // Ana -> Desarrollo de Prototipo
        evento1.getActividades().get(0).inscribir(estudiantes.get(1)); // Juan -> Desarrollo de Prototipo
        evento1.getActividades().get(1).inscribir(estudiantes.get(2)); // María -> modelo.actividades.Charla de Cierre
        evento2.getActividades().get(0).inscribir(estudiantes.get(0)); // Ana también en modelo.actividades.Taller de Robótica
    }

    public static void mostrarEstudiantes(List<Estudiante> estudiantes) {
        System.out.println("\n--- Lista de estudiantes ---");

        if (estudiantes.isEmpty()) {
            System.out.println(RED + "No hay estudiantes registrados." + RESET);
            return;
        }

        for (Estudiante estudiante : estudiantes) {
            System.out.println(estudiante);
        }
    }

    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    public static final String GREEN  = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE   = "\u001B[34m";
}
