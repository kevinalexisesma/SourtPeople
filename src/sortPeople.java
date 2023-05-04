import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.io.*;

public class sortPeople {
    public static void main(String[] args) {
        System.out.println("Con archivo txt vacio: ");
        leerTxt("nombres_y_alturasvacio.txt");

        System.out.println("Con archivo txt con un solo dato: ");
        leerTxt("nombres_y_alturasunosolo.txt");

        System.out.println("Con archivo txt ordenado: ");
        leerTxt("nombres_y_alturasordenada.txt");

        System.out.println("Con archivo txt desordenado: ");
        leerTxt("nombres_y_alturasdesordenada.txt");
    }

    public static void leerTxt(String nombreTxt){
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            archivo = new File(nombreTxt);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            String linea;
            while((linea=br.readLine())!=null){
                String[] nombres = linea.split(" ");
                linea=br.readLine();
                String[] alturas = linea.split(" ");
                int[] alturasInt = new int[alturas.length];
                for (int i = 0; i < alturasInt.length; i++) {
                    if(alturas[i] != null && !alturas[i].equals("")) {
                        alturasInt[i] = Integer.parseInt(alturas[i]);
                    }else if(i == 0){
                        System.out.println("El array es vacio.");
                    }
                }
                String[] ordenado =  sortPeople(nombres, alturasInt);
                for (int i = 0; i < ordenado.length; i++) {
                    System.out.print(ordenado[i] + " ");
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static String[] sortPeople(String[] nombres, int[] alturas) {
        Map<Integer, String> map = new TreeMap<>();
        for (int i = 0; i < nombres.length; i++) {
            map.put(alturas[i], nombres[i]);
        }
        String[] ordenada = new String[nombres.length];
        int pos = 0;
        Arrays.sort(alturas);
        for (int i = alturas.length - 1; i >= 0; i--) {
            ordenada[pos++] = map.get(alturas[i]);
        }
        return ordenada;
    }

}
