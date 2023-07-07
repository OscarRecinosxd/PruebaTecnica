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
                AgendaTelefonica.mostrarMensaje("Ingresa un numero valido");
                break;
        }
    }

    private void vaciarAgenda() {
        if (estaLaAgendaVacia()) {
            return;
        }
        Contacto[] nuevaAgenda = new Contacto[0];
        numeroDeContactos = 0;
        agenda = nuevaAgenda;
        AgendaTelefonica.mostrarMensaje("## Agenda vaciada con exito");

    }

    private void mostrarContactos() {
        if (estaLaAgendaVacia()) {
            return;
        }
        int i = 1;
        AgendaTelefonica.mostrarMensaje("  ### CONTACTOS ###");
        for (Contacto contacto : agenda) {
            AgendaTelefonica.mostrarMensaje("   $ Contacto " + i + ": " + contacto.toString());
            i++;
        }

    }

    private void eliminarContacto() {
        if (estaLaAgendaVacia()) {
            return;
        }
        mostrarContactos();
        try {
            AgendaTelefonica.mostrarMensaje("# Ingrese el indice del contacto a eliminar");
            int opcion = in.nextInt();
            in.nextLine();
            int i = 0;

            if (opcion < 1 || opcion > numeroDeContactos) {
                AgendaTelefonica.mostrarMensaje("### Selecciona un numero valido");
                return;
            }

            Contacto[] nuevaAgenda = new Contacto[numeroDeContactos - 1];
            for (Contacto contacto : agenda) {
                if (i == opcion - 1) {
                    AgendaTelefonica.mostrarMensaje("# Contacto eliminado!");
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
            AgendaTelefonica.mostrarMensaje("### Hubo un error");
            in.nextLine();
        }
    }

    private void modificarContacto() {
        if (estaLaAgendaVacia()) {
            return;
        }
        mostrarContactos();
        try {
            AgendaTelefonica.mostrarMensaje("# Elija el indice del contacto a modificar");
            int opcion = in.nextInt();
            in.nextLine();
            if (opcion < 1 || opcion > numeroDeContactos) {
                AgendaTelefonica.mostrarMensaje("### Selecciona un numero valido");
                return;
            }

            Contacto contactoEditado = crearEditarContactoHandler();
            if (contactoEditado.getNombre().length() > 0 && contactoEditado.getTelefono().length() == 8){
                agenda[opcion - 1] = contactoEditado;
                AgendaTelefonica.mostrarMensaje("# Contacto modificado con exito");
                ordenarAgenda();
            } 
        } catch (Exception e) {
            AgendaTelefonica.mostrarMensaje("### Ingresa datos validos");
            in.nextLine();
        }
    }

    private void buscarContacto() {
        if (estaLaAgendaVacia()) {
            return;
        }
        AgendaTelefonica.mostrarMensaje("# Ingresa el nombre de usuario a buscar");
        String usuario = in.nextLine();
        boolean existeContacto = false;

        for (Contacto contacto : agenda) {
            if (contacto.getNombre().equalsIgnoreCase(usuario.trim())) {
                AgendaTelefonica.mostrarMensaje("# Contacto encontrado: " + contacto.toString());
                existeContacto = true;
            }
        }

        if (!existeContacto) {
            AgendaTelefonica.mostrarMensaje("### Parece que no hay ningun contacto con ese usuario");
        }

    }

    private void agregarContacto() {
        if (numeroDeContactos == 10) {
            AgendaTelefonica.mostrarMensaje("### La agenda esta llena");
            return;
        }

        Contacto nuevoContacto = crearEditarContactoHandler();
        if (nuevoContacto.getNombre().length() > 0 && nuevoContacto.getTelefono().length() == 8) {
            numeroDeContactos++;
            Contacto[] nuevaAgenda = new Contacto[numeroDeContactos];
            nuevaAgenda[0] = nuevoContacto;
            int aux = 1;
            for (Contacto contacto : agenda) {
                nuevaAgenda[aux] = contacto;
                aux++;
            }
            agenda = nuevaAgenda;
            AgendaTelefonica.mostrarMensaje("# Contacto agregado");
            ordenarAgenda();
        }
    }

    private void ordenarAgenda() {
        Arrays.sort(agenda);
    }

    private Contacto crearEditarContactoHandler() {
        Contacto contacto = new Contacto("", "");
        AgendaTelefonica.mostrarMensaje("# Ingresa su usuario");
        String nombreContacto = in.nextLine();

        if (!esNombreValido(nombreContacto)) {
            return contacto;
        }
        for (Contacto c : agenda) {
            if (c.getNombre().equalsIgnoreCase(nombreContacto)) {
                AgendaTelefonica.mostrarMensaje("## Ya hay un registro con ese nombre, continuando...");
                break;
            }
        }

        AgendaTelefonica.mostrarMensaje("# Ingresa su telefono sin guion (8 digitos)");
        String numeroTelefono = in.nextLine();
        if (!esNumeroTelefonicoValido(numeroTelefono)) {
            return contacto;
        }
        contacto.setNombre(nombreContacto.toUpperCase());
        contacto.setTelefono(numeroTelefono);
        return contacto;
    }

    private boolean esNumeroTelefonicoValido(String numero) {
        if (numero.trim().matches("[0-9]+") && numero.trim().length() == 8) {
            return true;
        }
        AgendaTelefonica.mostrarMensaje("### Telefono invalido, debe tener 8 digitos");
        return false;
    }

    private boolean esNombreValido(String nombre) {
        if (nombre.trim().length() > 0) {
            return true;
        }
        AgendaTelefonica.mostrarMensaje("### Nombre invalido, no debe estar en blanco");
        return false;
    }

    private boolean estaLaAgendaVacia() {
        if (numeroDeContactos == 0) {
            AgendaTelefonica.mostrarMensaje("### La agenda esta vacia");
            return true;
        } else {
            return false;
        }

    }
}
