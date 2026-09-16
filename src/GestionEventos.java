import modelo.EventoUniversitario;
import modelo.Sala;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class GestionEventos {

    public void iniciar(Scanner teclado, List<EventoUniversitario> eventos, List<Sala> salas) {
        String opcion;

        do {
            Utilidades.limpiarConsola();
            System.out.println( Utilidades.BLUE + "\n--- Módulo: Eventos Universitarios ---" + Utilidades.RESET);
            System.out.println("1. Crear evento");
            System.out.println("2. Mostrar eventos");
            System.out.println("3. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = teclado.nextLine();

            switch (opcion) {
                case "1":
                    crearEvento(teclado, eventos, salas);
                    break;
                case "2":
                    mostrarEventos(eventos);
                    break;
                case "3":
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println(Utilidades.RED + "Opción no válida." + Utilidades.RESET);
            }

            if (!opcion.equals("3")) {
                System.out.println("\nPresione Enter para continuar...");
                teclado.nextLine();
            }

        } while (!opcion.equals("3"));
    }

    private void crearEvento(Scanner teclado, List<EventoUniversitario> eventos, List<Sala> salas) {
        System.out.println(Utilidades.BLUE + "\n--- Creación de evento ---" + Utilidades.RESET);

        String id = UUID.randomUUID().toString();

        System.out.println("Ingrese el nombre del evento: ");
        String titulo;
        do {
            titulo = teclado.nextLine();
        } while (titulo.isEmpty());

        boolean gratuito = false;
        double costoBase;

        System.out.println("Ingrese el costo base: ");
        do {
            costoBase = teclado.nextDouble();
        } while (costoBase < 0);
        teclado.nextLine(); // limpieza de buffer

        if (costoBase == 0) {
            gratuito = true;
            System.out.println(Utilidades.YELLOW + "Evento configurado como gratuito (costo base es 0)" + Utilidades.RESET);
        }

        EventoUniversitario evento = new EventoUniversitario(id, titulo, costoBase, gratuito);

        String respuestaCrearMasAct;
        System.out.println();
        System.out.println(Utilidades.BLUE + "Cree al menos una actividad para este evento." + Utilidades.RESET);

        do {
            String tituloActividad;
            System.out.println("Titulo de la actividad: ");
            tituloActividad = teclado.nextLine();

            int cupoMaximoActividad;
            System.out.println("Cupo máximo para la actividad '" + tituloActividad + "': ");
            cupoMaximoActividad = teclado.nextInt();
            teclado.nextLine();

            String tipoActividad;
            do {
                System.out.println("Tipo de actividad (modelo.actividades.Charla/modelo.actividades.Taller): ");
                tipoActividad = teclado.nextLine();
            } while (!tipoActividad.equalsIgnoreCase("modelo.actividades.Charla") && !tipoActividad.equalsIgnoreCase("modelo.actividades.Taller"));

            String disertante = null;
            boolean requiereNotebook = false;

            if (tipoActividad.equalsIgnoreCase("modelo.actividades.Charla")) {
                System.out.println("Nombre del disertante: ");
                disertante = teclado.nextLine();
            } else {
                System.out.println("¿Requiere notebook? S/N");
                requiereNotebook = teclado.nextLine().equalsIgnoreCase("S");
            }

            evento.crearActividad(tituloActividad, cupoMaximoActividad, tipoActividad, disertante, requiereNotebook);

            System.out.println();
            System.out.println(Utilidades.YELLOW + "¿Desea crear más modelo.actividades? S/N" + Utilidades.RESET);
            respuestaCrearMasAct = teclado.nextLine();

        } while (respuestaCrearMasAct.equalsIgnoreCase("S"));

        asignarSalaAEvento(teclado, evento, salas);

        eventos.add(evento);

        System.out.println(Utilidades.GREEN + "======================");
        System.out.println("Evento creado con éxito:");
        evento.mostrarDatos();
        System.out.println(Utilidades.RESET);

        System.out.println(Utilidades.YELLOW + "Creando copia del evento...");
        EventoUniversitario copiaEvento = new EventoUniversitario(evento);
        System.out.println("Datos de la copia:");
        copiaEvento.mostrarDatos();

        System.out.println("======================" + Utilidades.RESET);
        System.out.println("Cantidad de eventos: " + EventoUniversitario.getCantidadEventos());
    }

    private void mostrarEventos(List<EventoUniversitario> eventos) {
        System.out.println(Utilidades.BLUE + "\n--- Lista de eventos ---" + Utilidades.RESET);

        if (eventos.isEmpty()) {
            System.out.println(Utilidades.RED + "No hay eventos registrados." + Utilidades.RESET);
            return;
        } else {
            System.out.println("Cantidad de eventos creados: " + EventoUniversitario.getCantidadEventos());
        }

        for (EventoUniversitario evento : eventos) {
            evento.mostrarDatos();
            System.out.println(Utilidades.BLUE + "----------------------" + Utilidades.RESET);
        }
    }

    private void asignarSalaAEvento(Scanner teclado, EventoUniversitario evento, List<Sala> salas) {

        if (salas.isEmpty()) {
            System.out.println(Utilidades.RED + "No hay salas registradas. El evento quedará sin sala asignada." + Utilidades.RESET);
            return;
        }

        System.out.println();
        System.out.println(Utilidades.BLUE + "Asigne una sala: " + Utilidades.RESET);
        for (Sala sala : salas) {
            System.out.println("- " + sala);
        }

        Sala salaSeleccionada = null;
        int idSala;

        do {
            System.out.println("Ingrese el ID de la sala a asignar: ");
            idSala = teclado.nextInt();
            teclado.nextLine();

            for (Sala sala : salas) {
                if(sala.getId() == idSala) {
                    salaSeleccionada = sala;
                    break;
                }
            }

            if (salaSeleccionada == null) {
                System.out.println(Utilidades.RED + "ID de sala inválido. Intente nuevamente." + Utilidades.RESET);
            }
        } while (salaSeleccionada == null);

        evento.asignarSala(salaSeleccionada);
        System.out.println(Utilidades.GREEN + "modelo.Sala asignada: " + salaSeleccionada + Utilidades.RESET);
    }
}