/*
 * Este archivo define la clase Showtime, que representa una función/ presentación de cine con su información asociada.
 */
package Classes;

import java.time.LocalTime;
import java.util.List;
import org.bson.Document;

/**
 * La clase Showtime representa una función de cine con su información asociada.
 */
public class Showtime {

    private List<String> seatsSold;
    private LocalTime startHour;
    private LocalTime endHour;
    private String movie;
    private int duration;
    private String theater;

    /**
     * Obtiene los asientos vendidos.
     *
     * @return La lista de asientos vendidos.
     */
    public List<String> getSeatsSold() {
        return seatsSold;
    }

    /**
     * Establece la sala de cine.
     *
     * @param theater La sala.
     */
    public void setTheater(String theater) {
        this.theater = theater;
    }

    /**
     * Obtiene la sala de cine.
     *
     * @return La sala de cine.
     */
    public String getTheater() {
        return theater;
    }

    /**
     * Establece los asientos vendidos.
     *
     * @param seatsSold La lista de asientos vendidos.
     */
    public void setSeatsSold(List<String> seatsSold) {
        this.seatsSold = seatsSold;
    }

    /**
     * Obtiene la hora de inicio.
     *
     * @return La hora de inicio.
     */
    public LocalTime getStartHour() {
        return startHour;
    }

    /**
     * Establece la hora de inicio.
     *
     * @param startHour La hora de inicio.
     */
    public void setStartHour(LocalTime startHour) {
        this.startHour = startHour;
    }

    /**
     * Obtiene la hora de finalización.
     *
     * @return La hora de finalización.
     */
    public LocalTime getEndHour() {
        return endHour;
    }

    /**
     * Establece la hora de finalización.
     *
     * @param endHour La hora de finalización.
     */
    public void setEndHour(LocalTime endHour) {
        this.endHour = endHour;
    }

    /**
     * Obtiene el titulo de la película asociada a la función.
     *
     * @return La película asociada.
     */
    public String getMovie() {
        return movie;
    }

    /**
     * Establece la película asociada a la función.
     *
     * @param movie La película asociada.
     */
    public void setMovie(Movie movie) {
        this.movie = movie.getTitle();
        this.duration = movie.getDuration();
    }

    /**
     * Establece el titulo de la película asociada a la función.
     *
     * @param movie La película asociada.
     */
    public void setMovie(String movie) {
        this.movie = movie;
    }

    /**
     * Obtiene la duración de la función.
     *
     * @return La duración de la función.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Convierte la función en un documento BSON para su almacenamiento en la
     * base de datos.
     *
     * @return El documento BSON.
     */
    public Document converter() {
        Document document = new Document("Title", movie)
                .append("StartHour", startHour.toString())
                .append("EndHour", endHour.toString())
                .append("Duration", duration)
                .append("SeatsSold", JSON.listToJson(seatsSold));
        return document;
    }
}
