import modelo.actividades.Actividad;
import modelo.Estudiante;
import modelo.EventoUniversitario;

import java.util.List;
import java.util.Scanner;

public class GestionInscripcion {

    public void iniciar(Scanner teclado, List<EventoUniversitario> eventos, List<Estudiante> estudiantes) {
        if (eventos.isEmpty()) {
            System.out.println(Utilidades.RED + "No hay eventos registrados. Cree uno primero." + Utilidades.RESET);
            return;
        }
        if (estudiantes.isEmpty()) {
            System.out.println(Utilidades.RED + "No hay estudiantes registrados. Cree uno primero." + Utilidades.RESET);
            return;
        }

        String respuestaInscribirMas = "";

        do {
            Utilidades.limpiarConsola();
            System.out.println(Utilidades.BLUE + "\n--- Módulo: Inscripción de Estudiantes ---" + Utilidades.RESET);

            System.out.println("Eventos disponibles:");
            for (int i = 0; i < eventos.size(); i++) {
                System.out.println((i + 1) + ". " + eventos.get(i));
            }

            EventoUniversitario evento;
            int opcionEvento;
            do {
                System.out.println("Seleccione un evento por número: ");
                opcionEvento = teclado.nextInt();
                teclado.nextLine();
            } while (opcionEvento < 1 || opcionEvento > eventos.size());
            evento = eventos.get(opcionEvento - 1);

            List<Actividad> actividades = evento.getActividades();
            if (actividades.isEmpty()) {
                System.out.println(Utilidades.RED + "Este evento no tiene modelo.actividades cargadas." + Utilidades.RESET);
                return;
            }

            System.out.println("Actividades de '" + evento + "':");
            for (int i = 0; i < actividades.size(); i++) {
                System.out.println((i + 1) + ". " + actividades.get(i));
            }

            Actividad actividad;
            int opcionActividad;
            do {
                System.out.println("Seleccione una actividad por número: ");
                opcionActividad = teclado.nextInt();
                teclado.nextLine();
            } while (opcionActividad < 1 || opcionActividad > actividades.size());
            actividad = actividades.get(opcionActividad - 1);

            Utilidades.mostrarEstudiantes(estudiantes);

            Estudiante estudianteAInscribir = null;
            do {
                System.out.println("Ingrese el legajo del estudiante a inscribir: ");
                String legajo = teclado.nextLine();

                for (Estudiante estudiante : estudiantes) {
                    if (estudiante.getLegajo().equals(legajo)) {
                        estudianteAInscribir = estudiante;
                        break;
                    }
                }

                if (estudianteAInscribir == null) {
                    System.out.println(Utilidades.RED + "No se encontró un estudiante con ese legajo. Intente nuevamente." + Utilidades.RESET);
                }
            } while (estudianteAInscribir == null);

            actividad.inscribir(estudianteAInscribir);
            System.out.println(Utilidades.GREEN + "======================");
            System.out.println(estudianteAInscribir.getNombre() + " inscripto con éxito en '" + actividad + "'.");
            System.out.println("Inscriptos actuales:");
            actividad.mostrarInscripciones();
            System.out.println("======================" + Utilidades.RESET);

            System.out.println();
            System.out.println(Utilidades.YELLOW + "¿Desea inscribir más estudiantes? S/N" + Utilidades.RESET);
            respuestaInscribirMas = teclado.nextLine();

        } while (respuestaInscribirMas.equalsIgnoreCase("S"));
    }
}