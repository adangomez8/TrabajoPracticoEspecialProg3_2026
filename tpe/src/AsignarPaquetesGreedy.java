import java.util.ArrayList;
import java.util.HashMap;

public class AsignarPaquetesGreedy {

    private HashMap<Camion, ArrayList<Paquete>> sol;
    private double pesoNoAsignado = Double.MAX_VALUE;

    public AsignarPaquetesGreedy() {
        this.sol = new HashMap<>();
        this.pesoNoAsignado = 0;
    }

    public double getPesoNoAsignado() {
        return this.pesoNoAsignado;
    }

    /*
    * La estrategia greedy utilizada consiste en asignar paquetes de manera secuencial tomando decisiones locales.
    *
    * Primero los paquetes se ordenan de mayor a menor peso.
    *
    * Luego, para cada paquete se busca el primer camión compatible que tenga capacidad disponible, y el paquete se asigna inmediatamente.
    *
    * Si ningún camión puede transportarlo el paquete queda sin asignar.
    *
    *La idea de ordenar por peso busca priorizar la ubicación de los paquetes más grandes para reducir el espacio desperdiciado.
    *
    *Esta técnica tiene un costo computacional mucho menor que backtracking y obtiene soluciones rápidamente, aunque no garantiza encontrar la solución óptima.
    */

    public HashMap<Camion, ArrayList<Paquete>> asignarPaquetes(String pathCamiones, String pathPaquetes) {

        ArrayList<Camion> camiones = new ArrayList<>(CSVReader.leerCamiones(pathCamiones));

        ArrayList<Paquete> paquetes = new ArrayList<>(CSVReader.leerPaquetes(pathPaquetes));

        // Ordenar paquetes de mayor a menor peso
        paquetes.sort((p1, p2) -> Double.compare(p2.getPeso_kg() , p1.getPeso_kg()));

        HashMap<Camion, ArrayList<Paquete>> sol = new HashMap<>();

        for (Camion camion : camiones) {
            sol.put(camion, new ArrayList<>());
        }

        for (Paquete paquete : paquetes) {

            boolean asignado = false;

            for (Camion camion : camiones) {
                if (puedeCargar(camion, paquete, sol)) {

                    sol.get(camion).add(paquete);
                    asignado = true;
                    break;
                }
            }

            if (!asignado) {

                pesoNoAsignado += paquete.getPeso_kg();

                System.out.println("No se pudo asignar: " + paquete.getCodigo_paquete()
                );
            }
        }
        return sol;
    }

    private boolean puedeCargar(Camion camion, Paquete paquete, HashMap<Camion, ArrayList<Paquete>> actual) {

        if (paquete.isContiene_alimentos() && !camion.isEsta_refrigerado()) {

            return false;
        }

        double pesoActual = 0;

        for (Paquete p : actual.get(camion)) {
            pesoActual += p.getPeso_kg();
        }

        return pesoActual + paquete.getPeso_kg() <= camion.getCapacidad_kg();
    }
}
