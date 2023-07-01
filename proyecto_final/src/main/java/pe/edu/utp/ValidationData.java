package pe.edu.utp;

public class ValidationData
{
    // Constructores (Autor: Juan Bladimir Romero Collazos)
    public ValidationData() {
    }
    public ValidationData(int id, String fechaUTC, String horaUTC, double latitud, int profundidad, double magnitud, String fechaCorte) {
        this.id = id;
        this.fechaUTC = fechaUTC;
        this.horaUTC = horaUTC;
        this.latitud = latitud;
        this.profundidad = profundidad;
        this.magnitud = magnitud;
        this.fechaCorte = fechaCorte;
    }

    // Getters y Setters (Autor: Juan Bladimir Romero Collazos)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFechaUTC() {
        return fechaUTC;
    }

    public void setFechaUTC(String fechaUTC) {
        this.fechaUTC = fechaUTC;
    }

    public String getHoraUTC() {
        return horaUTC;
    }

    public void setHoraUTC(String horaUTC) {
        this.horaUTC = horaUTC;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public int getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(int profundidad) {
        this.profundidad = profundidad;
    }

    public double getMagnitud() {
        return magnitud;
    }

    public void setMagnitud(double magnitud) {
        this.magnitud = magnitud;
    }

    public String getFechaCorte() {
        return fechaCorte;
    }

    public void setFechaCorte(String fechaCorte) {
        this.fechaCorte = fechaCorte;
    }

    // toString (Autor: Juan Bladimir Romero Collazos)
    @Override
    public String toString() {
        return "ValidationData{" +
                "id=" + id +
                ", fechaUTC='" + fechaUTC + '\'' +
                ", horaUTC='" + horaUTC + '\'' +
                ", latitud=" + latitud +
                ", profundidad=" + profundidad +
                ", magnitud=" + magnitud +
                ", fechaCorte='" + fechaCorte + '\'' +
                '}';
    }

    // Declaración de variables (Autor: Juan Bladimir Romero Collazos)
    private int id;
    private String fechaUTC;
    private String horaUTC;
    private double latitud;
    private int profundidad;
    private double magnitud;
    private String fechaCorte;
}
