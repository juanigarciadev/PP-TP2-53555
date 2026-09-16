import modelo.Estudiante;
import modelo.EventoUniversitario;
import modelo.Sala;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        String opcion = "";

        // Poblado de datos de prueba
        List<Estudiante> estudiantes = new ArrayList<>();
        List<EventoUniversitario> eventos = new ArrayList<>();
        List<Sala> salas = new ArrayList<>();
        Utilidades.poblarSistema(estudiantes, eventos, salas);

        do {
            Utilidades.limpiarConsola();
            System.out.println(Utilidades.BLUE + "======================================");
            System.out.println("          SELECTOR DE MÓDULOS          ");
            System.out.println("======================================" + Utilidades.RESET);
            System.out.println("1. Gestión de eventos universitarios");
            System.out.println("2. Gestión de estudiantes");
            System.out.println("3. Inscripción a eventos");
            System.out.println("4. Gestión de salas");
            System.out.println("5. Salir del sistema");
            System.out.print("Seleccione una opción: ");

            opcion = teclado.nextLine();

            switch (opcion) {
                case "1":
                    GestionEventos moduloEventos = new GestionEventos();
                    moduloEventos.iniciar(teclado, eventos, salas);
                    break;
                case "2":
                    GestionEstudiantes moduloEstudiantes = new GestionEstudiantes();
                    moduloEstudiantes.iniciar(teclado, estudiantes);
                    break;
                case "3":
                    GestionInscripcion moduloInscripciones = new GestionInscripcion();
                    moduloInscripciones.iniciar(teclado, eventos, estudiantes);
                    break;
                case "4":
                    GestionSalas moduloSalas = new GestionSalas();
                    moduloSalas.iniciar(teclado, salas);
                    break;
                case "5":
                    System.out.println("Saliendo del sistema principal...");
                    break;
                default:
                    System.out.println(Utilidades.RED + "======================================");
                    System.out.println("Opción no válida. Intente de nuevo.");
                    System.out.println("======================================" + Utilidades.RESET);
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        System.out.println("El sleep fue interrumpido");
                    }
            }
        } while (!opcion.equals("5"));

        teclado.close();
    }
}