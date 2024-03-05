/*
 * Este archivo define la clase Movie, que representa una película con su información asociada.
 */
package Classes;

import java.util.List;
import org.bson.Document;
import org.bson.types.Binary;

/**
 * La clase Movie representa una película con su información asociada.
 */
public class Movie {

    private String title;
    private List<String> genres;
    private List<String> actors;
    private String classification;
    private Binary poster;
    private String date;
    private String description;
    private String director;
    private int duration;

    /**
     * Establece el título de la película.
     *
     * @param title El título de la película.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Establece los géneros de la película.
     *
     * @param genres Los géneros de la película.
     */
    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    /**
     * Agrega un género a la lista de géneros de la película.
     *
     * @param genre El género a agregar.
     */
    public void addGenres(String genre) {
        genres.add(genre);
    }

    /**
     * Establece los actores de la película.
     *
     * @param actors Los actores de la película.
     */
    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    /**
     * Agrega un actor a la lista de actores de la película.
     *
     * @param actor El actor a agregar.
     */
    public void addActors(String actor) {
        actors.add(actor);
    }

    /**
     * Establece el póster de la película.
     *
     * @param poster El póster de la película (Binario).
     */
    public void setPoster(Binary poster) {
        this.poster = poster;
    }

    /**
     * Establece la clasificación de la película.
     *
     * @param classification La clasificación de la película.
     */
    public void setClassification(String classification) {
        this.classification = classification;
    }

    /**
     * Establece la fecha de la película.
     *
     * @param date La fecha de la película.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Establece la descripción de la película.
     *
     * @param description La descripción de la película.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Establece la duración de la película.
     *
     * @param duration La duración de la película.
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Establece el director de la película.
     *
     * @param director El director de la película.
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * Obtiene el póster de la película.
     *
     * @return El póster de la película en binario.
     */
    public byte[] getPoster() {
        return poster.getData();
    }

    /**
     * Obtiene el título de la película.
     *
     * @return El título de la película.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Obtiene la lista de géneros de la película.
     *
     * @return La lista de géneros de la película.
     */
    public List<String> getGenresList() {
        return genres;
    }

    /**
     * Obtiene la lista de actores de la película.
     *
     * @return La lista de actores de la película.
     */
    public List<String> getActorsList() {
        return actors;
    }

    /**
     * Obtiene la clasificación de la película.
     *
     * @return La clasificación de la película.
     */
    public String getClassification() {
        return classification;
    }

    /**
     * Obtiene la fecha de la película.
     *
     * @return La fecha de la película.
     */
    public String getDate() {
        return date;
    }

    /**
     * Obtiene la descripción de la película.
     *
     * @return La descripción de la película.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Obtiene la duración de la película.
     *
     * @return La duración de la película.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Obtiene el director de la película.
     *
     * @return El director de la película.
     */
    public String getDirector() {
        return director;
    }

    /**
     * Convierte la película en un documento BSON para su almacenamiento en la
     * base de datos.
     *
     * @return El documento BSON.
     */
    public Document converter() {
        JSON json = new JSON();
        Document document = new Document("Title", title)
                .append("Classification", classification)
                .append("Date", getDate())
                .append("Description", description)
                .append("Director", director)
                .append("Duration", duration)
                .append("Poster", poster);

        // Verificar si actors y genres no son nulos antes de convertirlos a JSON
        if (actors != null) {
            document.append("Actors", json.listToJson(actors));
        }
        if (genres != null) {
            document.append("Genres", json.listToJson(genres));
        }

        return document;
    }
}
