public class Camion {

    private int id_camion;
    private String patente;
    private boolean esta_refrigerado;
    private double capacidad_kg;

    public Camion(int idCamion, String patente, boolean estaRefrigerado, double capacidadKg) {
        this.id_camion = idCamion;
        this.patente = patente;
        this.esta_refrigerado = estaRefrigerado;
        this.capacidad_kg = capacidadKg;
    }

    public int getId_camion() {
        return id_camion;
    }

    public void setId_camion(int id_camion) {
        this.id_camion = id_camion;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public boolean isEsta_refrigerado() {
        return esta_refrigerado;
    }

    public void setEsta_refrigerado(boolean esta_refrigerado) {
        this.esta_refrigerado = esta_refrigerado;
    }

    public double getCapacidad_kg() {
        return capacidad_kg;
    }

    public void setCapacidad_kg(double capacidad_kg) {
        this.capacidad_kg = capacidad_kg;
    }

    @Override
    public String toString() {
        return "Camion{" +
                "id_camion=" + id_camion +
                ", patente='" + patente + '\'' +
                ", esta_refrigerado=" + esta_refrigerado +
                ", capacidad_kg=" + capacidad_kg +
                '}';
    }
}
