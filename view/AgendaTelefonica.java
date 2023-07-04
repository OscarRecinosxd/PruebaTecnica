package view;

import java.util.Scanner;

import controller.AgendaControlador;
import model.Contacto;

public class AgendaTelefonica {
    private AgendaControlador agendaControlador;

    public void iniciar() {
        agendaControlador = new AgendaControlador();
        iniciarAgenda();
    }

    public void iniciarAgenda() {
        int opcion = -1;
        Scanner in = new Scanner(System.in);
        do {
            try {
                mostrarMenu();
                opcion = in.nextInt();
                agendaControlador.opcionHandler(opcion);
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Ingresa una opción válida!");
                in.next();
                opcion = -1;
            }

        } while (opcion != 7);
        in.close();
    }

    public void mostrarMenu() {
        System.out.println("=====================================");
        System.out.println("# ¿Qué operación quieres realizar? " +
                " \n# 1.Añadir contacto \n# 2.Buscar contacto \n# 3.Modificar contacto \n# 4.Eliminar contacto \n# 5.Mostrar contactos "
                +
                " \n# 6.Vaciar contactos \n# 7.Salir");
        System.out.println("=====================================");

    }

    public static void mostrarRespuestaDePeticion(String respuesta) {
        System.out.println(respuesta);
    }

    public static void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

}
