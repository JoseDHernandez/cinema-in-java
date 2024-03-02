/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.util.List;
import org.bson.Document;
import org.bson.types.Binary;

/**
 *
 * @author Jose
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public void addGenres(String genre) {
        genres.add(genre);
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public void addActors(String actor) {
        actors.add(actor);
    }

    public void setPoster(Binary poster) {
        this.poster = poster;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public byte[] getPoster() {
        return poster.getData();
    }

    public String getTitle() {
        return title;
    }

    public List<String> getGenresList() {
        return genres;
    }

    public List<String> getActorsList() {
        return actors;
    }

    public String getClassification() {
        return classification;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public int getDuration() {
        return duration;
    }

    public String getDirector() {
        return director;
    }

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
