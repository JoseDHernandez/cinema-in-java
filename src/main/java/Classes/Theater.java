/*
 * Este archivo define la clase Theater, que representa una sala de cine con su información asociada.
 */
package Classes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.bson.Document;

/**
 * La clase Theater representa una sala de cine con su información asociada.
 */
public class Theater {

    private String name;
    private String date;
    private List<Showtime> showtimes;
    private String features;

    /**
     * Crea una instancia de Theater con valores predeterminados.
     */
    public Theater() {
        this.showtimes = new ArrayList<>();
        name = "";
        date = null;
        features = "";
    }

    /**
     * Agrega una función de cine a la sala.
     *
     * @param showtime La función de cine a agregar.
     */
    public void addShowtime(Showtime showtime) {
        this.showtimes.add(showtime);
    }

    /**
     * Establece el nombre de la sala de cine.
     *
     * @param name El nombre de la sala de cine.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Establece la fecha de la sala de cine en la que sera usada.
     *
     * @param date La fecha de la sala de cine.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Obtiene las funciones de cine de la sala.
     *
     * @return La lista de funciones de cine.
     */
    public List<Showtime> getShowtimes() {
        return showtimes;
    }

    /**
     * Obtiene el nombre de la sala de cine.
     *
     * @return El nombre de la sala de cine.
     */
    public String getName() {
        return name;
    }

    /**
     * Obtiene la fecha de la sala de cine en la que sera usada.
     *
     * @return La fecha de la sala de cine.
     */
    public String getDate() {
        return date;
    }

    /**
     * Obtiene las características de la sala de cine como: 2D, 3D, 4D, etc .
     *
     * @return Las características de la sala de cine.
     */
    public String getFeatures() {
        return features;
    }

    /**
     * Establece las características de la sala de cine.
     *
     * @param features Las características de la sala de cine (2D, 3D, 4D, etc).
     */
    public void setFeatures(String features) {
        this.features = features;
    }

    /**
     * Elimina una función de cine de la sala.
     *
     * @param index El índice de la función de cine a eliminar.
     */
    public void removeShowtime(int index) {
        showtimes.remove(index);
    }

    /**
     * Convierte la sala de cine en un documento BSON para su almacenamiento en
     * la base de datos.
     *
     * @return El documento BSON.
     */
    public Document converter() {
        // Convertir la lista de funciones de cine a documentos
        List<Document> showtimesList = showtimes.stream()
                .map(Showtime::converter)
                .collect(Collectors.toList());
        // Convertir el objeto en documento
        Document document = new Document("Name", name)
                .append("Date", date)
                .append("Features", features)
                .append("Showtimes", showtimesList);
        return document;
    }
}
