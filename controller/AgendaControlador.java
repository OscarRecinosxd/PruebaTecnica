package controller;

import java.util.Arrays;
import java.util.Scanner;

import model.Contacto;
import view.AgendaTelefonica;

public class AgendaControlador {
    private static int longitudAgenda = 1;
    private static Contacto[] agenda = new Contacto[longitudAgenda];
    private static int ultimoBorrado = -1;
    private static Scanner in = new Scanner(System.in);

    public void opcionHandler(int opcion) {
            System.out.println("Longitud es " + longitudAgenda);
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
        agenda = new Contacto[1];
        longitudAgenda = 1;
        ultimoBorrado = -1;
    }

    private void mostrarContactos() {
        if (agenda[0] == null ) {
            System.out.println("la agenda esta vacia");
            return;
        }
        for (int i = 0; i < longitudAgenda-1; i++) {
            AgendaTelefonica.mostrarRespuestaDePeticion("Contacto #" + (i+1) + " " + agenda[i].toString());
        }

    }

    private void eliminarContacto() {
        mostrarContactos();
        System.out.println("Digita el indice del contacto a borrar");
        int opcion = in.nextInt();
        Contacto[] nuevaAgenda = new Contacto[longitudAgenda-1];
        int aux = 0;
        for (int i = 0; i < longitudAgenda; i++) {
            if(opcion-1 != i){
                nuevaAgenda[aux] = agenda[i];
                aux++;
            } else{
                AgendaTelefonica.mostrarRespuestaDePeticion("El contacto borrado es : " + agenda[i].toString());
                
            }
        }
        agenda = nuevaAgenda;
        //ordenarAgenda();
        return;

    }

    private void modificarContacto() {
        System.out.println("Digita el nombre del contacto a modificar");
        String nombre = in.nextLine();
        for (int i = 0; i < longitudAgenda; i++) {
            Contacto contactoActual = agenda[i];
            if (contactoActual.getNombre().equalsIgnoreCase(nombre)) {
                AgendaTelefonica.mostrarRespuestaDePeticion("El contacto que modificaras es : " + contactoActual.toString());
                AgendaTelefonica.mostrarMensaje("Ingresa nuevo nombre");
                String nuevoNombre = in.nextLine().trim();
                AgendaTelefonica.mostrarMensaje("Ingresa nuevo numero");
                String nuevoNumero = in.nextLine().trim();
                if (nuevoNombre.length() > 0 && nuevoNumero.length() == 8) {
                    agenda[i].setNombre(nuevoNombre);
                    agenda[i].setTelefono(nuevoNumero);
                } else {
                    AgendaTelefonica.mostrarMensaje("Ingresa valores validos");
                }
                ordenarAgenda();
                return;
                
            }
        }
    }

    private void buscarContacto() {
        System.out.println("Digita el nombre del contacto a buscar");
        String nombre = in.nextLine();
        for (int i = 0; i < longitudAgenda; i++) {
            Contacto contactoActual = agenda[i];
            if (contactoActual.getNombre().equalsIgnoreCase(nombre)) {
                AgendaTelefonica.mostrarRespuestaDePeticion("El contacto es : " + contactoActual.toString());
                return;
            }
        }
        AgendaTelefonica.mostrarMensaje("No hay contacto encontrado");
        return;
    }

    private void agregarContacto() {
        // try {
        if (longitudAgenda == 10) {
            System.out.println("La agenda esta llena!");
            return;
        }
        AgendaTelefonica.mostrarMensaje("Digita el nombre");
        String nombre = in.nextLine();
        AgendaTelefonica.mostrarMensaje("Digita el numero de telefono sin guion");
        String telefono = in.nextLine();
        if (telefono.length() == 8 && nombre.length() > 0) {
            Contacto[] nuevaAgenda = new Contacto[longitudAgenda];
            Contacto nuevoContacto = new Contacto(nombre, telefono);
            nuevaAgenda[0] = nuevoContacto;
            for (int i = 1; i < longitudAgenda; i++) {
                nuevaAgenda[i] = agenda[i-1];
            }
            agenda = nuevaAgenda;

        } else {
            AgendaTelefonica.mostrarMensaje("Ingrese un numero de telefono de 8 digito o no deje campos en blanco");
        }
        // } catch (Exception e) {
        // System.out.println("Ingresa datos validos");
        //
        longitudAgenda++;
    }

    private void ordenarAgenda(){
        Arrays.sort(agenda);
    }

}
