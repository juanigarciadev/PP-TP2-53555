import modelo.Sala;

import java.util.List;
import java.util.Scanner;

public class GestionSalas {
    public void iniciar(Scanner teclado, List<Sala> salas) {

        String opcion;

        do {
            Utilidades.limpiarConsola();
            System.out.println(Utilidades.BLUE + "\n--- Módulo: Salas ---" + Utilidades.RESET);
            System.out.println("1. Crear sala");
            System.out.println("2. Mostrar salas");
            System.out.println("3. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = teclado.nextLine();

            switch (opcion) {
                case "1":
                    crearSala(teclado, salas);
                    break;
                case "2":
                    mostrarSalas(salas);
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

    private void crearSala(Scanner teclado, List<Sala> salas) {
        System.out.println(Utilidades.BLUE + "\n--- Creación de sala ---" + Utilidades.RESET);

        int id;
        System.out.println("Ingrese el ID de la sala:");
        do {
            id = teclado.nextInt();
            teclado.nextLine();
        } while (id < 0);

        String nombre;
        System.out.println("Ingrese el nombre de la sala: ");
        do {
            nombre = teclado.nextLine();
        } while (nombre.isEmpty());

        Sala sala = new Sala(id, nombre);
        salas.add(sala);

        System.out.println(Utilidades.GREEN + "======================");
        System.out.println("¡modelo.Sala creada con éxito!");
        System.out.println(sala);
        System.out.println("======================" + Utilidades.RESET);
    }

    public void mostrarSalas(List<Sala> salas) {
        System.out.println(Utilidades.BLUE + "\n--- Lista de salas ---" + Utilidades.RESET);

        if (salas.isEmpty()) {
            System.out.println(Utilidades.RED + "No hay salas creadas." + Utilidades.RESET);
            return;
        }

        for (Sala sala : salas) {
            System.out.println(sala);
        }
    }
}
