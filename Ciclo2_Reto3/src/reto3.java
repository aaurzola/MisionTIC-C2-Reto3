import model.Paciente;

import java.util.*;

public class reto3 {
    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);
        int cantidadPacientes = Integer.parseInt(lector.nextLine());
        Paciente[] paciente = new Paciente[cantidadPacientes];
        Map<String, Integer> ciudadesMap = new LinkedHashMap<String, Integer>();

        for (int i = 0; i < cantidadPacientes; i++) {
            String[] datos = lector.nextLine().split("-");
            Paciente persona = new Paciente(
                    datos[0],Long.parseLong(datos[1]), Integer.parseInt(datos[2]),
                    datos[3], datos[4], datos[5]);
            paciente[i] = persona;
            //verificar si la ciudad hace parte del Map de ciudades.
            if (ciudadesMap.containsKey(persona.getCiudad())) {
                int counter = ciudadesMap.get(persona.getCiudad()) + 1;
                ciudadesMap.replace(persona.getCiudad(), counter); //reemplaza valor de contador
            } else {
                ciudadesMap.put(persona.getCiudad(), 1); //indexa nueva ciudad con contador en 1
            }
        }

        //imprime pacientes por ciudades
        for (String key : ciudadesMap.keySet()) {
            System.out.println(key + " " + ciudadesMap.get(key));
        }

        //imprime primera ciudad con menor cantidad de pacientes
        int min = Collections.min(ciudadesMap.values());
        for(String key : ciudadesMap.keySet()){
            if (ciudadesMap.get(key) == min) {
                System.out.println(key);
                break;
            }
        }

        //imprime pacientes de tercera edad
        for (int i = 0; i < paciente.length; i++) {
            if (paciente[i].clasificarEdad().equalsIgnoreCase("Tercera edad")) {
                System.out.println(paciente[i].getNombre() + " " + paciente[i].getCedula());
            }
        }
    }
}
