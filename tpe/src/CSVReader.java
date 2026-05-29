import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public static List<Camion> leerCamiones(String path) {

        List<Camion> camiones = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            // Primera línea: cantidad de registros
            int cantidad = Integer.parseInt(br.readLine());

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(";");

                int idCamion = Integer.parseInt(datos[0]);
                String patente = datos[1];

                // 1 = true, 0 = false
                boolean estaRefrigerado = datos[2].equals("1");

                double capacidadKg = Double.parseDouble(datos[3]);

                Camion camion = new Camion(
                        idCamion,
                        patente,
                        estaRefrigerado,
                        capacidadKg
                );

                camiones.add(camion);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return camiones;
    }

    public static List<Paquete> leerPaquetes(String path) {

        List<Paquete> paquetes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            // Primera línea: cantidad de registros
            int cantidad = Integer.parseInt(br.readLine());

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(";");

                int idPaquete = Integer.parseInt(datos[0]);
                String codigoPaquete = datos[1];
                double pesoKg = Double.parseDouble(datos[2]);

                // 1 = true, 0 = false
                boolean contieneAlimentos = datos[3].equals("1");

                int nivelUrgencia = Integer.parseInt(datos[4]);

                Paquete paquete = new Paquete(
                        idPaquete,
                        codigoPaquete,
                        pesoKg,
                        contieneAlimentos,
                        nivelUrgencia
                );

                paquetes.add(paquete);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return paquetes;
    }
}