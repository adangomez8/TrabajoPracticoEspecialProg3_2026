import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Servicios {

    private HashMap<String, Paquete> paquetes;

    /*Complejidad temporal del constructor: O(n)
     * Se recorren todos los paquetes una sola vez
     * para cargarlos en el HashMap.
     */
    public Servicios(String pathCamiones, String pathPaquetes) {

        paquetes = new HashMap<>();

        // Leer paquetes desde CSV
        List<Paquete> listaPaquetes =
                CSVReader.leerPaquetes(pathPaquetes);
        for (Paquete paquete : listaPaquetes) {

            paquetes.put(
                    paquete.getCodigo_paquete(),
                    paquete
            );
        }


    }

    /* Complejidad temporal del servicio 1: O(1).*/

    public Paquete servicio1(String codigoPaquete) {

        return paquetes.get(codigoPaquete);
    }

    /* Complejidad temporaldel servicio 2: O(n) */

    public List<Paquete> servicio2(boolean contieneAlimentos) {
        List<Paquete> resultado = new ArrayList<>();

        for (Paquete paquete : paquetes.values()) {

            if (paquete.isContiene_alimentos() == contieneAlimentos) {
                resultado.add(paquete);
            }
        }

        return resultado;
    }

    /* Complejidad temporaldel servicio 2: O(n) */

    public List<Paquete> servicio3(int urgenciaMinima, int
            urgenciaMaxima) {
        List<Paquete> resultado = new ArrayList<>();

        for (Paquete paquete : paquetes.values()) {

            int urgencia = paquete.getNivel_urgencia();

            if (urgencia >= urgenciaMinima && urgencia <= urgenciaMaxima) {
                resultado.add(paquete);
            }
        }

        return resultado;
    }
}