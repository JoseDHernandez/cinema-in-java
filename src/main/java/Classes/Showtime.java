/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.time.LocalTime;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author Jose
 */
public class Showtime {

    private List<String> seatsSold;
    private LocalTime startHour;
    private LocalTime endHour;
    private String movie;
    private int duration;

    public List<String> getSeatsSold() {
        return seatsSold;
    }

    public void setSeatsSold(List<String> seatsSold) {
        this.seatsSold = seatsSold;
    }

    public LocalTime getStartHour() {
        return startHour;
    }

    public void setStartHour(LocalTime startHour) {
        this.startHour = startHour;
    }

    public LocalTime getEndHour() {
        return endHour;
    }

    public void setEndHour(LocalTime endHour) {
        this.endHour = endHour;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie.getTitle();
        this.duration = movie.getDuration();
    }

    public int getDuration() {
        return duration;
    }

    public Document converter() {
        JSON json = new JSON();
        Document document = new Document("Title", movie)
                .append("StartHour", startHour.toString())
                .append("End Hour", endHour.toString())
                .append("Duration", duration)
                .append("SeatsSold", json.listToJson(seatsSold));
        return document;
    }
}
