package controller;

import java.util.Arrays;
import java.util.Scanner;

import model.Contacto;
import view.AgendaTelefonica;

public class AgendaControlador {
    private Contacto[] agenda = new Contacto[0];
    private int numeroDeContactos = 0;
    private static Scanner in = new Scanner(System.in);

    public void opcionHandler(int opcion) {
        switch (opcion) {
            case 1:
                agregarContacto();
                break;
            case 2:
                buscarContacto();
                break;
            case 3:
                modificarContacto();
                break;
            case 4:
                eliminarContacto();
                break;
            case 5:
                mostrarContactos();
                break;
            case 6:
                vaciarAgenda();
                break;

            default:
                break;
        }
    }

    private void vaciarAgenda() {
        Contacto[] nuevaAgenda = new Contacto[0];
        numeroDeContactos = 0;
        agenda = nuevaAgenda;
        AgendaTelefonica.mostrarMensaje("Agenda vaciada con exito");

    }

    private void mostrarContactos() {
        if (numeroDeContactos == 0) {
            AgendaTelefonica.mostrarMensaje("La agenda esta vacia");
        }
        int i = 1;
        for (Contacto contacto : agenda) {
            AgendaTelefonica.mostrarMensaje("Contacto " + i + ": " + contacto.toString());
            i++;
        }

    }

    private void eliminarContacto() {
        mostrarContactos();
        try {
            AgendaTelefonica.mostrarMensaje("Ingrese el indice del contacto a eliminar");
            int opcion = in.nextInt();
            in.nextLine();
            int i = 0;

            Contacto[] nuevaAgenda = new Contacto[numeroDeContactos - 1];
            for (Contacto contacto : agenda) {
                if (i == opcion - 1) {
                    AgendaTelefonica.mostrarMensaje("Contacto eliminado!");
                    numeroDeContactos--;
                    opcion = -1;
                } else {
                    nuevaAgenda[i] = contacto;
                    i++;
                }
            }

            agenda = nuevaAgenda;
            ordenarAgenda();

        } catch (Exception e) {
            AgendaTelefonica.mostrarMensaje("Hubo un error");
            in.nextLine();
        }
    }

    private void modificarContacto() {
        mostrarContactos();
        try {
            AgendaTelefonica.mostrarMensaje("Elija el indice del contacto a modificar");
            int opcion = in.nextInt();
            in.nextLine();
            AgendaTelefonica.mostrarMensaje("Ingrese el nuevo nombre");
            String nuevoNombre = in.nextLine();
            AgendaTelefonica.mostrarMensaje("Ingrese el nuevo numero");
            String nuevoNumero = in.nextLine();
            agenda[opcion - 1].setNombre(nuevoNombre);
            agenda[opcion - 1].setTelefono(nuevoNumero);
            AgendaTelefonica.mostrarMensaje("Contacto modificado con exito");

        } catch (Exception e) {
            AgendaTelefonica.mostrarMensaje("Ingresa datos validos");
        }

        ordenarAgenda();
    }

    private void buscarContacto() {
        AgendaTelefonica.mostrarMensaje("Ingresa el nombre de usuario a buscar");
        String usuario = in.nextLine();
        boolean existeContacto = false;

        for (Contacto contacto : agenda) {
            if (contacto.getNombre().equalsIgnoreCase(usuario.trim())) {
                AgendaTelefonica.mostrarMensaje(contacto.toString());
                existeContacto = true;
            }
        }

        if (!existeContacto) {
            AgendaTelefonica.mostrarMensaje("Parece que no hay ningun contacto con ese usuario");
        }

    }

    private void agregarContacto() {
        if (numeroDeContactos == 10) {
            AgendaTelefonica.mostrarMensaje("La agenda esta llena");
            return;
        }

        AgendaTelefonica.mostrarMensaje("Ingresa su usuario");
        String nuevoUsuario = in.nextLine();
        AgendaTelefonica.mostrarMensaje("Ingresa su telefono");
        String nuevoTelefono = in.nextLine();

        if (nuevoUsuario.trim().length() >= 1 && nuevoTelefono.trim().length() == 8) {
            Contacto nuevoContacto = new Contacto(nuevoUsuario, nuevoTelefono);
            numeroDeContactos++;
            Contacto[] nuevaAgenda = new Contacto[numeroDeContactos];
            nuevaAgenda[0] = nuevoContacto;
            int aux = 1;
            for (Contacto contacto : agenda) {
                nuevaAgenda[aux] = contacto;
                aux++;
            }
            agenda = nuevaAgenda;

        } else {
            AgendaTelefonica.mostrarMensaje("Ingresa datos correctos");
        }
        ordenarAgenda();
    }

    private void ordenarAgenda() {
        Arrays.sort(agenda);
    }
}
