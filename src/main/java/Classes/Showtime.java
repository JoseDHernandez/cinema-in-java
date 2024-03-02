/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Jose
 */
public class Showtime {

    private String sala;
    private List<String> seatsSold;
    private LocalDateTime hour;
    private String movie;
    private String features;

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public List<String> getSeatsSold() {
        return seatsSold;
    }

    public void setSeatsSold(List<String> seatsSold) {
        this.seatsSold = seatsSold;
    }

    public LocalDateTime getHour() {
        return hour;
    }

    public void setHour(LocalDateTime hour) {
        this.hour = hour;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie.getTitle();
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }
}
