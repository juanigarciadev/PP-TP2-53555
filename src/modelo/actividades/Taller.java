package modelo.actividades;

public class Taller extends Actividad {
    private boolean requiereNotebook;

    public Taller(String titulo, int cupoMaximo, boolean requiereNotebook) {
        super(titulo, cupoMaximo);
        this.requiereNotebook = requiereNotebook;
    }

    @Override
    public double calcularCostoMateriales() {
        return requiereNotebook ? 5000 : 2000;
    }

    @Override
    public String getTipo() { return "modelo.actividades.Taller"; }
}