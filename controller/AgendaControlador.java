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

    }

    private void mostrarContactos() {
        int i = 1;
        for (Contacto contacto : agenda) {
            System.out.println("Contacto " + i +": "+ contacto.toString());
            i++;
        }
    }

    private void eliminarContacto() {
        mostrarContactos();
        System.out.println("Ingrese el indice del contacto a eliminar");
        int opcion = in.nextInt();
        in.nextLine();
        int i = 0;

        Contacto[] nuevaAgenda = new Contacto[numeroDeContactos-1];
        for (Contacto contacto : agenda) {
            if (i == opcion-1) {
                System.out.println("Contacto eliminado!");
                numeroDeContactos--;
                opcion = -1;
            } else{
                nuevaAgenda[i] = contacto;
                i++;
            }
        }
        agenda = nuevaAgenda;
    }

    private void modificarContacto() {

    }

    private void buscarContacto() {

    }

    private void agregarContacto() {
        if(numeroDeContactos == 10){
            System.out.println("La agenda esta llena");
            return;
        }
        System.out.println("Ingresa su usuario");
        String nuevoUsuario = in.nextLine();
        System.out.println("Ingresa su telefono");
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
        }
        else{
            System.out.println("Ingresa datos correctos");
        }

 
    }

    private void ordenarAgenda(){

    }

}
