import java.util.ArrayList;
import java.util.HashMap;

public class AsignarPaquetesBacktracking {

    private HashMap<Camion, ArrayList<Paquete>> sol;
    private double pesoNoAsignado = Double.MAX_VALUE;
    private double mejorPesoNoAsignado;
    private int estadosGenerados;

    public AsignarPaquetesBacktracking() {
        this.sol = new HashMap<>();
        this.pesoNoAsignado = 0;
        this.mejorPesoNoAsignado = Double.MAX_VALUE;
        this.estadosGenerados = 0;
    }

    public double getPesoNoAsignado() {
        return this.pesoNoAsignado;
    }

    public int getEstadosGenerados() {
        return this.estadosGenerados;
    }

    /*
    *
    * La estrategia de backtracking consiste en explorar recursivamente todas las posibles asignaciones de paquetes a camiones para encontrar la solución óptima.
    *
    * Para cada paquete, el algoritmo evalúa asignarlo a alguno de los camiones compatibles que tengan capacidad disponible, o dejarlo sin asignar.
    *
    *Durante la exploración se controla que los paquetes con alimentos sólo se asignen a camiones refrigerados, y que no se exceda la capacidad máxima de cada camión.
    *
    * El algoritmo mantiene la mejor solución encontrada, es decir, aquella que minimiza el peso total de paquetes no asignados.
    *
    * Además, se implementó una poda: si el peso no asignado actual ya es mayor o igual al mejor encontrado, la rama deja de explorarse.
    *
    * Esta técnica garantiza encontrar la solución óptima, aunque tiene un costo computacional elevado debido a la gran cantidad de combinaciones posibles.
    */
    public HashMap<Camion, ArrayList<Paquete>> asignarPaquetes(String pathCamiones, String pathPaquetes) {

        ArrayList<Camion> camiones = new ArrayList<>(CSVReader.leerCamiones(pathCamiones));
        ArrayList<Paquete> paquetes =  new ArrayList<>(CSVReader.leerPaquetes(pathPaquetes));

        HashMap<Camion, ArrayList<Paquete>> actual = new HashMap<>();

        for (Camion c : camiones) {
            actual.put(c, new ArrayList<>());
        }

        back(camiones, paquetes, 0, actual,0);

        return sol;
    }

    private void back(ArrayList<Camion> camiones, ArrayList<Paquete> paquetes, int indexPaquete, HashMap<Camion, ArrayList<Paquete>> actual, double pesoNoAsignado) {

        estadosGenerados++;

        //poda
        if (pesoNoAsignado <= mejorPesoNoAsignado) {
            if (indexPaquete == paquetes.size()) {

                if (pesoNoAsignado < mejorPesoNoAsignado) {

                    mejorPesoNoAsignado = pesoNoAsignado;
                    sol = copiar(actual);
                    this.pesoNoAsignado = pesoNoAsignado;
                }
                return;
            }

            Paquete paquete = paquetes.get(indexPaquete);

            for (Camion camion : camiones) {

                if (puedeCargar(camion, paquete, actual)) {

                    actual.get(camion).add(paquete);

                    back(camiones, paquetes, indexPaquete + 1, actual, pesoNoAsignado);

                    actual.get(camion).remove(paquete);
                }
            }

            // No asignarlo
            back(camiones, paquetes, indexPaquete + 1, actual, pesoNoAsignado + paquete.getPeso_kg());
        }
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

    private HashMap<Camion, ArrayList<Paquete>> copiar(HashMap<Camion, ArrayList<Paquete>> original) {

        HashMap<Camion, ArrayList<Paquete>> copia = new HashMap<>();

        for (Camion camion : original.keySet()) {
            copia.put(camion, new ArrayList<>(original.get(camion)));
        }

        return copia;
    }
}
