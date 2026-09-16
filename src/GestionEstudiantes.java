import modelo.Estudiante;

import java.util.List;
import java.util.Scanner;

public class GestionEstudiantes {

    public void iniciar(Scanner teclado, List<Estudiante> estudiantes) {
        String opcion;

        do {
            Utilidades.limpiarConsola();
            System.out.println(Utilidades.BLUE + "\n--- Módulo: Estudiantes ---" + Utilidades.RESET);
            System.out.println("1. Crear estudiante");
            System.out.println("2. Mostrar estudiantes");
            System.out.println("3. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = teclado.nextLine();

            switch (opcion) {
                case "1":
                    crearEstudiante(teclado, estudiantes);
                    break;
                case "2":
                    Utilidades.mostrarEstudiantes(estudiantes);
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

    private void crearEstudiante(Scanner teclado, List<Estudiante> estudiantes) {
        System.out.println(Utilidades.BLUE + "\n--- Creación de estudiante ---" + Utilidades.RESET);

        String legajo;
        System.out.println("Ingrese el legajo del estudiante:");
        do {
            legajo = teclado.nextLine();
        } while (legajo.isEmpty());

        String nombre;
        System.out.println("Ingrese el nombre del estudiante: ");
        do {
            nombre = teclado.nextLine();
        } while (nombre.isEmpty());

        Estudiante estudiante = new Estudiante(legajo, nombre);
        estudiantes.add(estudiante);

        System.out.println(Utilidades.GREEN + "======================");
        System.out.println("¡modelo.Estudiante creado con éxito!");
        System.out.println(estudiante);
        System.out.println("======================" + Utilidades.RESET);
    }
}