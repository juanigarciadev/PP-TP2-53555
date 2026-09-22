import excepciones.CupoExcedidoException;
import modelo.Estudiante;
import modelo.EventoUniversitario;
import modelo.Sala;
import modelo.actividades.Actividad;

import java.io.File;

public class App {

    public static void main(String[] args) {
        System.out.println(Utilidades.BLUE + "=== TP2 - EJERCICIO 1 ===" + Utilidades.RESET);

        System.out.println(Utilidades.BLUE + "\n--- Creación de estudiantes, sala y evento ---" + Utilidades.RESET);
        Estudiante ana = new Estudiante("1001", "Ana Pérez");
        Estudiante juan = new Estudiante("1002", "Juan López");
        Estudiante maria = new Estudiante("1003", "María Gómez");

        Sala sala = new Sala(1, "Auditorio Principal");

        EventoUniversitario evento = new EventoUniversitario("EVT-001", "Semana de la Ingeniería", 1000.0, false);
        evento.asignarSala(sala);
        evento.crearActividad("Introducción a IA", 2, "Charla", "Lic. Gómez", false);
        evento.crearActividad("Taller de Robótica", 15, "Taller", null, true);
        evento.mostrarDatos();

        Actividad charla = evento.getActividades().get(0);

        System.out.println(Utilidades.BLUE + "\n--- Caso exitoso: inscripciones dentro del cupo ---" + Utilidades.RESET);
        try {
            charla.inscribir(ana);
            charla.inscribir(juan);
            System.out.println(Utilidades.GREEN + "Ana y Juan inscriptos correctamente." + Utilidades.RESET);
            boolean guardadoOk = evento.persistirEvento();
            System.out.println("Evento guardado: " + guardadoOk);
            EventoUniversitario recuperado = evento.recuperarEvento(evento.getEventoId());
            System.out.println("Evento recuperado: " + recuperado);
        } catch (CupoExcedidoException e) {
            System.out.println(Utilidades.RED + e.getMessage() + Utilidades.RESET);
        } finally {
            System.out.println("=================================");
            System.out.println(Utilidades.YELLOW + "Realizando limpieza..." + Utilidades.RESET);
            File eventoArchivo = new File(evento.getEventoId()+".dat");
            eventoArchivo.delete();
            System.out.println(Utilidades.GREEN + "Limpieza realizada con éxito" + Utilidades.RESET);
        }

        System.out.println(Utilidades.BLUE + "\n--- Caso fallido: cupo excedido ---" + Utilidades.RESET);
        try {
            charla.inscribir(maria);
            System.out.println(Utilidades.GREEN + "María inscripta correctamente." + Utilidades.RESET);
        } catch (CupoExcedidoException e) {
            System.out.println(Utilidades.RED + e.getMessage() + Utilidades.RESET);
        }

        System.out.println(Utilidades.BLUE + "\n--- Estado final del evento ---" + Utilidades.RESET);
        evento.mostrarDatos();
        System.out.println("Cantidad de eventos: " + EventoUniversitario.getCantidadEventos());
    }
}
