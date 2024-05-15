/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Classes;

import com.mycompany.cuevadeana.Mongo;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.Document;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author User
 */
public class BillIT {

    public BillIT() {
    }
    protected final String uri = "mongodb://localhost:27017";
    protected final String name = "cine";

    @Test
    public void testSomeMethod() {
        //Variables
        //Fecha
        final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        final String dateString = sdf.format(new Date());
        //tiempos de funcion
        final int openTime = 7;//Tiempo de apertura
        final int delay = 20;//Teimpo entre proyecciones
        //nombre de la sala
        final String nameTheater = "Sala 1";
        //Crear usuario
        User user = new User();
        user.setName("Pedro Mascal Hurtado Perdomo");
        user.setIdentification("C.C. 123456789");
        user.setPassword("password");
        user.setRol("Cajero");
        user.setCashRegister("Caja 1");
        user.createUserName();
        // Crear pelicula
        Movie movie = new Movie();
        movie.setTitle("Inception");
        movie.setDirector("Christopher Nolan");
        movie.setDescription("Una pelicula de un viaje en el universo");
        movie.setDuration(148);
        movie.setDate("2010-07-16");
        List<String> genres = new ArrayList<>();
        genres.add("Accción");
        genres.add("Aventura");
        genres.add("Ciencia ficción");
        movie.setGenres(genres);
        movie.setClassification("T");
        List<String> actors = new ArrayList<>();
        actors.add("Silvio Lemus");
        actors.add("Jhonn P.");
        actors.add("Roberto Hernandez");
        movie.setActors(actors);
        //crear sala
        Theater theater = new Theater();
        theater.setDate(dateString);
        theater.setName(nameTheater);
        theater.setFeatures("2D");
        // Creatar funcion / proyeccion
        //Clase showtime 1
        Showtime showtime = new Showtime();
        showtime.setMovie(movie);
        //Horario
        LocalTime startTime = LocalTime.of(openTime, 0);
        LocalTime endTime = startTime.plusMinutes(movie.getDuration());
        showtime.setStartHour(startTime);
        showtime.setEndHour(endTime);
        showtime.setTheater(nameTheater);
        List<String> seatsSold = new ArrayList<>();
        seatsSold.add("A1");
        seatsSold.add("A2");
        showtime.setSeatsSold(seatsSold);
        //Agregar showtime a theater
        theater.addShowtime(showtime);
        //Clase showtime 2
        Showtime showtime2 = new Showtime();
        showtime2.setMovie(movie);
        //Horario
        LocalTime startTime2 = endTime.plusMinutes(delay);//Agrego 20m
        LocalTime endTime2 = startTime.plusMinutes(movie.getDuration());
        showtime2.setStartHour(startTime2);
        showtime2.setEndHour(endTime2);
        showtime2.setTheater(nameTheater);
        List<String> seatsSold2 = new ArrayList<>();
        seatsSold2.add("B5");
        seatsSold2.add("A6");
        seatsSold2.add("A4");
        showtime2.setSeatsSold(seatsSold2);
        //Agregar showtime a theater
        theater.addShowtime(showtime2);
        // Creacion factura
        Bill bill = new Bill();
        bill.setCashier(user);
        bill.setNameClient("Luis Alajandro Diaz Torres");
        final String ident = "C.C. 84354676466";
        bill.setIdentification(ident);
        bill.setShowtime(showtime);// Showtime 1
        bill.setDateShow(dateString);
        List<String> seats = new ArrayList<>();
        seats.add("A3");
        seats.add("A4");
        bill.setSeats(seats);
        final double PRECIO_BASE_2D = 10000.0;
        bill.setPrice(PRECIO_BASE_2D * seats.size());
        //Registrar registros
        Mongo mongoDB = new Mongo(uri, name);
        mongoDB.insert(user);
        mongoDB.insert(movie);
        mongoDB.insert(theater);
        mongoDB.insert(bill);
        //Obtencion de bill y salas
        Document billDB = mongoDB.findBill(dateString, ident, movie.getTitle());
        List<Theater> listTeahers = mongoDB.getTheater(movie.getTitle(), dateString);
        mongoDB.closeConnection();
        //Test a bill
        assertNotNull(billDB, "No se obtuvo factura");
        assertEquals(bill.getNameClient(), billDB.getString("NameClient"), "Error en la coincidencia del nombre del cliente");
        assertEquals(bill.getIdentification(), billDB.getString("Identification"), "Identificaciones diferentes");
        assertEquals(user.getName(), billDB.getString("Cashier"), "Cajero diferente");
        assertEquals(movie.getTitle(), billDB.getString("Movie"), "Titulo de pelicula incorrecto");
        assertEquals(dateString, billDB.getString("Date"), "Fechas distintas");
        assertEquals(showtime.getStartHour().toString(), billDB.getString("StartHour"), "Horas de inicio distintas");
        assertEquals(showtime.getEndHour().toString(), billDB.getString("EndHour"), "Horas de finalizacion distintas");
        assertEquals(theater.getName(), billDB.getString("Theater"), "Sala diferente");
        //Sillas de la factura
        List<String> seatsDB = billDB.getList("Seats", String.class);
        assertEquals(bill.getSeats(), seatsDB);
        assertEquals(bill.getPrice(), billDB.getDouble("Price"), 0.0001, "Precios distintos");
        assertEquals(bill.getTotalPrice(), billDB.getDouble("TotalPrice"), 0.0001, "Precios totales diferentes");
        //Test de theater y showtime
        assertFalse(listTeahers.isEmpty(), "No se encontro la sala");
        Showtime showTimeDB = listTeahers.getFirst().getShowtimes().getFirst();//Primer showtime (showtime 1)
        //Comprar si se actualizo la cantidad de silla ventidas
        assertNotEquals(showtime.getSeatsSold(), showTimeDB.getSeatsSold(), "Cantidad de sillas igual");
        //Cantidades iguales de sillas ventidas a 4
        assertTrue(showTimeDB.getSeatsSold().size() == (bill.getSeats().size() + showtime.getSeatsSold().size()), "Cantidad de silla vendidas diferente");
    }

}
