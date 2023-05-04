import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class GeneradorNombresAlturas {
    public static void main(String[] args) {
        // Generar lista de nombres al azar

        caso("vacio");
        caso("unosolo", 1);
        caso("desordenada", 25);
        caso("ordenada", 25, 1);
    }

    public static void caso(String tipo){
        // Escribir los arrays en un archivo de texto
        escribirArraysEnArchivo("nombres_y_alturas"+ tipo + ".txt", 0, 0);
    }

    public static void caso(String tipo, int cantidad){
        // Escribir los arrays en un archivo de texto
        escribirArraysEnArchivo("nombres_y_alturas"+ tipo + ".txt", cantidad, 0);
    }

    public static void caso(String tipo, int cantidad, int ordenado){
        // Escribir los arrays en un archivo de texto
        escribirArraysEnArchivo("nombres_y_alturas" + tipo +".txt", cantidad, ordenado);
    }

    public static String generarNombreAleatorio(int longitud) {
        String alfabeto = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < longitud; i++) {
            int indiceAleatorio = random.nextInt(alfabeto.length());
            sb.append(alfabeto.charAt(indiceAleatorio));
        }
        return sb.toString();
    }

    public static List<Integer> generarAlturasAleatorias(int cantidad) {
        Random random = new Random();
        Set<Integer> alturasSet = new HashSet<>();
        while (alturasSet.size() < cantidad) {
            int alturaAleatoria = random.nextInt(50) + 150;  // genera un número entero aleatorio entre 150 y 199
            alturasSet.add(alturaAleatoria);
        }
        List<Integer> alturasList = new ArrayList<>(alturasSet);
        return alturasList;
    }

    public static List<Integer> generarAlturasOrdenadas(int cantidad) {
        Random random = new Random();
        Set<Integer> alturasSet = new HashSet<>();
        while (alturasSet.size() < cantidad) {
            int alturaAleatoria = random.nextInt(50) + 150;  // genera un número entero aleatorio entre 150 y 199
            alturasSet.add(alturaAleatoria);
        }
        List<Integer> alturasList = new ArrayList<>(alturasSet);
        Collections.sort(alturasList);
        Collections.reverse(alturasList);
        return alturasList;
    }

    public static void escribirArraysEnArchivo(String nombreArchivo, int cantidad, int ordenado) {
        try (PrintWriter pw = new PrintWriter(nombreArchivo)) {
            for(int j = 0; j < 25; j++) {
                List<Integer> alturas = new ArrayList<>();
                List<String> nombres = new ArrayList<>();
                while (nombres.size() < cantidad) {  // especifica la cantidad de nombres que quiere generar
                    String nuevoNombre = generarNombreAleatorio(1);  // genera un string aleatorio de 8 letras
                    if (!nombres.contains(nuevoNombre)) {
                        nombres.add(nuevoNombre);
                    }
                }
                if(ordenado == 1){
                    alturas = generarAlturasOrdenadas(nombres.size());  // genera una lista de números enteros ordenados entre 150 y 200, sin repeticiones
                }else {
                    alturas = generarAlturasAleatorias(nombres.size());  // genera una lista de números enteros al azar entre 150 y 200, sin repeticiones
                }


                for (int i = 0; i < nombres.size(); i++) {
                    pw.print(nombres.get(i) + " ");
                }
                pw.println("");
                for (int i = 0; i < alturas.size(); i++) {
                    pw.print(alturas.get(i) + " ");
                }
                pw.println("");
            }
        }catch (FileNotFoundException e) {
            System.out.println("Error al escribir el archivo.");
        }
    }
}
