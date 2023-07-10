package pe.edu.utp;

import java.time.LocalDate;

/**
 * Esta clase nos permite validar los datos
 * @author Daniel Ramos Marrufo.
 */

public class ValidationData
{
    // Constructores (Autor: Juan Bladimir Romero Collazos)

    public ValidationData() {
    }

    /**
     * Este constructor nos permite almacenar la data
     * @param id Es el identificador de cada dato
     * @param fechaUTC Es la fecha coordinada, que se basa en el tiempo atómico internacional.
     * @param horaUTC Es la hora universal coordinada, que se basa en el tiempo atómico internacional.
     * @param latitud Es la distancia angular entre la línea ecuatoria.
     * @param longitud La distancia angular entre un punto cualquiera del globo terráqueo y el meridiano cero.
     * @param profundidad  la distancia de un elemento con respecto a un plano horizontal de referencia cuando
     *                     dicho elemento se encuentra por debajo de la referencia.
     * @param magnitud Es un valor de medida según la unidad usada.
     * @param fechaCorte Es la fecha que se finalizo el analisis de datos.
     */

    public ValidationData(long id, String fechaUTC, String horaUTC, double latitud, double longitud,
                          int profundidad, double magnitud, String fechaCorte) {
        this.id = id;
        this.fechaUTC = fechaUTC;
        this.horaUTC = horaUTC;
        this.latitud = latitud;
        this.longitud = longitud;
        this.profundidad = profundidad;
        this.magnitud = magnitud;
        this.fechaCorte = fechaCorte;
    }

    public ValidationData(long id, LocalDate parse, String horaUTC, double latitud, double longitud, int profundidad, double magnitud, String fechaCorte) {
    }

    // Getters y Setters (Autor: Daniel Ramos Marrufo)

    /**
     * Es un método que nos permite obtener la id del registro.
     * @return Nos devuelve el valor correspondiente del id.
     */
    public long getId() {
        return id;
    }

    /**
     * Nos permite asignar la id.
     * @param id Aquí nos permite pasarle el dato de la id.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Es un método que nos permite obtener la fechaUTC.
     * @return Nos devuelve la fechaUTC.
     */
    public String getFechaUTC() {
        return fechaUTC;
    }

    /**
     * Nos permite asignar la fechaUTC.
     * @param fechaUTC Aquí nos permite pasarle el dato de la fechaUTC.
     */
    public void setFechaUTC(String fechaUTC) {
        this.fechaUTC = fechaUTC;
    }

    /**
     * Es un método que nos permite obtener la horaUTC.
     * @return Nos devuelve la horaUTC.
     */
    public String getHoraUTC() {
        return horaUTC;
    }

    /**
     * Nos permite asignar la horaUTC.
     * @param horaUTC Aquí nos permite pasarle el dato de la horaUTC.
     */
    public void setHoraUTC(String horaUTC) {
        this.horaUTC = horaUTC;
    }

    /**
     * Es un método que nos permite obtener la latitud.
     * @return Nos devuelve la latitud.
     */
    public double getLatitud() {
        return latitud;
    }

    /**
     * Nos permite asignar la latitud.
     * @param latitud Aquí nos permite pasarle el dato de la latitud.
     */
    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    /**
     * Es un método que nos permite obtener la longitud.
     * @return Nos devuelve la longitud.
     */
    public double getLongitud() {
        return longitud;
    }

    /**
     * Nos permite asignar la latitud.
     * @param longitud Aquí nos permite pasarle el dato de la longitud.
     */
    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    /**
     * Es un método que nos permite obtener la profundidad.
     * @return Nos devuelve la profundidad.
     */
    public int getProfundidad() {
        return profundidad;
    }

    /**
     * Nos permite asignar la profundidad.
     * @param profundidad Aquí nos permite pasarle el dato de la profundidad.
     */
    public void setProfundidad(int profundidad) {
        this.profundidad = profundidad;
    }

    /**
     * Es un método que nos permite obtener la magnitud.
     * @return Nos devuelve la magnitud.
     */
    public double getMagnitud() {
        return magnitud;
    }

    /**
     * Nos permite asignar la magnitud.
     * @param magnitud Aquí nos permite pasarle el dato de la magnitud.
     */
    public void setMagnitud(double magnitud) {
        this.magnitud = magnitud;
    }

    /**
     * Es un método que nos permite obtener la fecha que finalizo el registro de datos.
     * @return Nos devuelve la fecha que finalizo el registro de datos.
     */
    public String getFechaCorte() {
        return fechaCorte;
    }

    /**
     * Nos permite asignar la fecha que finalizo el registro de datos.
     * @param fechaCorte Aquí nos permite pasarle el dato de la fecha que finalizo el registro de datos.
     */
    public void setFechaCorte(String fechaCorte) {
        this.fechaCorte = fechaCorte;
    }

    // toString (Autor: Daniel Ramos Marrufo)

    /**
     * Proporciona una representación personalizada de sus objetos como una cadena de caracteres,
     * que incluye información específica de las variables de instancia del objeto. Esto es útil
     * cuando se desea obtener una representación legible del objeto para su visualización o depuración.
     * @return Va a retornar una cadena de texto donde se veran cada uno de los valores.
     */

    @Override
    public String toString() {
        return "ValidationData{" +
                "id=" + id +
                ", fechaUTC='" + fechaUTC + '\'' +
                ", horaUTC='" + horaUTC + '\'' +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                ", profundidad=" + profundidad +
                ", magnitud=" + magnitud +
                ", fechaCorte='" + fechaCorte + '\'' +
                '}';
    }

    // Declaración de variables (Autor: Daniel Ramos Marrufo)
    /**
     * Declaracion de cada atributo.
     */
    private long id;
    private String fechaUTC;
    private String horaUTC;
    private double latitud;
    private double longitud;
    private int profundidad;
    private double magnitud;
    private String fechaCorte;
}
