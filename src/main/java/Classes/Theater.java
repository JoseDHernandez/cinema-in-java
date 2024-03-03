/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.bson.Document;

/**
 *
 * @author Jose
 */
public class Theater {

    private String name;
    private String date;
    private List<Showtime> showtimes;
    private String features;

    public Theater() {
        this.showtimes = new ArrayList<>();
        name = "";
        date = null;
        features = "";
    }

    public void addShowtime(Showtime showtime) {
        this.showtimes.add(showtime);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Showtime> getShowtimes() {
        return showtimes;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public void removeShowtime(int index) {
        showtimes.remove(index);
    }

    public Document converter() {
        //Pasar lista de showtimes a documentos
        List<Document> showtimesList = showtimes.stream()
                .map(Showtime::converter)
                .collect(Collectors.toList());
        //Convertir objeto en documento
        Document document = new Document("Name", name)
                .append("Date", date)
                .append("Features", features)
                .append("Showtimes", showtimesList);
        return document;
    }
}
