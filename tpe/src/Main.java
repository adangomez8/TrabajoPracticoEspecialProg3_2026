import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Servicios servicios = new Servicios("./src/datasets/Camiones.csv", "./src/datasets/Paquetes.csv");

        System.out.println("Servicio 1:");
        System.out.println(servicios.servicio1("P007"));
        System.out.println();
        System.out.println("Servicio 2:");
        System.out.println(servicios.servicio2(true));
        System.out.println();
        System.out.println("Servicio 3:");
        System.out.println(servicios.servicio3(30, 70));


        /**BACKTRACKING*/

        System.out.println("BACKTRACKING");
        AsignarPaquetesBacktracking asignarPaquetesBack = new AsignarPaquetesBacktracking();
        HashMap<Camion, ArrayList<Paquete>> back = asignarPaquetesBack.asignarPaquetes("./src/datasets/Camiones.csv", "./src/datasets/Paquetes.csv");

        System.out.println();
        System.out.println("SOLUCIÓN OBTENIDA");

        for (Map.Entry<Camion, ArrayList<Paquete>> entry: back.entrySet()) {

            Camion camion = entry.getKey();

            ArrayList<Paquete> paquetes = entry.getValue();

            System.out.println("Camión " + camion.getPatente() + ": ");

            for (Paquete paquete : paquetes) {
                System.out.println(paquete.getCodigo_paquete());
            }
            System.out.println();
        }

        System.out.println("Peso no asignado: " + asignarPaquetesBack.getPesoNoAsignado() + " kg");

        System.out.println("Estados generados: " + asignarPaquetesBack.getEstadosGenerados());

        /**GREEDY*/

        System.out.println();
        System.out.println("GREEDY");
        AsignarPaquetesGreedy asignarPaquetesGreedy = new AsignarPaquetesGreedy();
        HashMap<Camion, ArrayList<Paquete>> greegy = asignarPaquetesGreedy.asignarPaquetes("./src/datasets/Camiones.csv", "./src/datasets/Paquetes.csv");

        System.out.println("SOLUCIÓN OBTENIDA");

        for (Map.Entry<Camion, ArrayList<Paquete>> entry: greegy.entrySet()) {

            Camion camion = entry.getKey();

            ArrayList<Paquete> paquetes = entry.getValue();

            System.out.println("Camión " + camion.getPatente() + ": ");

            for (Paquete paquete : paquetes) {
                System.out.println(paquete.getCodigo_paquete());
            }
        }

        System.out.println("Peso no asignado: " + asignarPaquetesGreedy.getPesoNoAsignado() + " kg");

    }
}