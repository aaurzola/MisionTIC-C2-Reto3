import model.Paciente;

import java.util.Scanner;

public class reto3 {
    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);
        int cantidadPacientes = Integer.parseInt(lector.nextLine());
        Paciente[] listaPacientes = new Paciente[cantidadPacientes];
        for (int i = 0; i < cantidadPacientes; i++) {
            Scanner datosSeparados = new Scanner(lector.nextLine()).useDelimiter("-");
            Paciente persona = new Paciente();
            persona.setNombre(datosSeparados.next());
            persona.setCedula(Long.parseLong(datosSeparados.next()));
            persona.setEdad(Integer.parseInt(datosSeparados.next()));
            persona.setCiudad(datosSeparados.next());
            persona.setEps(datosSeparados.next());
            persona.setEnfermedad(datosSeparados.next());
            listaPacientes[i] = persona;

        }
        boolean flag = true;
        int k = 0; //variable para indexar nuevas ciudades.
        String[] ciudades = new String[cantidadPacientes]; //arreglo de ciudades únicas
        for (int i = 0; i < listaPacientes.length; i++) {
            flag = true;
            for (int j = 0; j < ciudades.length; j++) {
                if (listaPacientes[i].getCiudad().equalsIgnoreCase(ciudades[j])) {
                    flag = false;
                }
            }
            if (flag) {
                ciudades[k] = listaPacientes[i].getCiudad();
                k++;
            }
        }
        int[] contadorCiudades = new int[ciudades.length];
        for (int i = 0; i < ciudades.length; i++) {
            for (int j = 0; j < listaPacientes.length; j++) {
                if(listaPacientes[j].getCiudad().equalsIgnoreCase(ciudades[i])){
                    contadorCiudades[i] ++;
                }
            }
        }
        for (int i = 0; i < ciudades.length; i++) {
            if(ciudades[i] != null) {
                System.out.println(ciudades[i] + " " + contadorCiudades[i]);
            }
        }
        int menorCantidad = contadorCiudades[0];
        int indiceMenorCantidad = 0;
        for (int i = 0; i < contadorCiudades.length; i++) {
            if (contadorCiudades[i] < menorCantidad && contadorCiudades[i] != 0) {
                menorCantidad = contadorCiudades[i];
                indiceMenorCantidad = i;
            }
        }
        System.out.println(ciudades[indiceMenorCantidad]);
        for (int i = 0; i < listaPacientes.length; i++) {
            if (listaPacientes[i].clasificarEdad().equalsIgnoreCase("Tercera edad")) {
                System.out.println(listaPacientes[i].getNombre() + " " + listaPacientes[i].getCedula());
            }
        }
    }
}
