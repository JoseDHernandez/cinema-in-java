/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author Jose
 */
public class Bill {

    private String nit;
    private User cashier;
    private Showtime showtime;
    private String dateShow;
    private List<String> seats;
    private double price;
    private final double iva;
    private double totalPrice;
    private LocalDateTime purchaseDate;
    private String nameClient;
    private String identification;

    public Bill() {
        nit = "00000000000";
        seats = new ArrayList<>();
        cashier = null;
        showtime = null;
        price = 0.0;
        iva = 0.19;
        totalPrice = 0.0;
        purchaseDate = null;
        nameClient = "";
        identification = "";
    }

    public void setNameClient(String name) {
        nameClient = name;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    // Getters and setters
    public void setDateShow(String date) {
        dateShow = date;
    }

    public String getDateShow() {
        return dateShow;
    }

    public String getNit() {
        return nit;
    }

    public User getCashier() {
        return cashier;
    }

    public void setCashier(User cashier) {
        this.cashier = cashier;
    }

    public void setShowtime(Showtime showtime) {
        this.showtime = showtime;
    }

    public List<String> getSeats() {
        return seats;
    }

    public void setSeats(List<String> seats) {
        this.seats = seats;
    }

    public double getPrice() {
        return price;
    }

    /**
     * Establece el precio total de la venta, incluyendo el IVA.
     *
     * @param price El precio sin impuestos. (Double)
     */
    public void setPrice(double price) {
        this.price = price;
        totalPrice = price + (price * iva);
    }

    public double getIva() {
        return iva;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Obtiene la fecha de compra en la zona horaria de Colombia. Si la fecha de
     * compra no está establecida, se devuelve la fecha y hora actual.
     *
     * @return La fecha y hora actual de Colombia.
     */
    public LocalDateTime getPurchaseDate() {
        if (purchaseDate != null) {
            LocalDateTime date = LocalDateTime.now();
            ZoneId zone = ZoneId.of("America/Bogota");
            ZonedDateTime zonaHoraria = date.atZone(zone);
            LocalDateTime localDate = zonaHoraria.toLocalDateTime();
            purchaseDate = localDate;
        }
        return purchaseDate;
    }

    /*
    Cueva de ana
    NIT
    Dirrecion
    Codigo factura
    Caja
    Cajero/a:
    Cliente
    ---Boleto de entrada de cine---
    pelicula:
    Fecha de funcion:
    Ubicacion
        Sala
        Asientos
    Valor
    iva
    valor total + iva
    ----
    Fecha y hora de la compra
     */
    public String toString() {
        return "Cueva de ana \n"
                + "NIT: " + nit
                + "\nDireción Calle 85 # 87 CC Cometa Local 105-110 \n"
                + "Cajero/a: " + cashier.getName() + "\nCaja: " + cashier.getCashRegister()
                + "\nCliente: " + nameClient + "\nIdentificación: " + identification + "\n"
                + "--------Información de la compra-------\n"
                + "Pelicula: " + showtime.getMovie() + "\n"
                + "Fecha de la función: " + dateShow + " " + showtime.getStartHour() + " - " + showtime.getEndHour() + "\n"
                + "Sala: " + showtime.getTheater() + "\n"
                + "Asientos: " + seats + "\n"
                + "Valor: " + price + "  IVA: 19% \n"
                + "Valor total: " + totalPrice
                + "\n--------------------------------------\n"
                + "Fecha de la compra: " + getPurchaseDate();
    }

    public Document converter() {
        Document document = new Document("CashRegister", cashier.getCashRegister())
                .append("Cashier", cashier.getName())
                .append("NameClient", nameClient)
                .append("Identification", identification)
                .append("Movie", showtime.getMovie())
                .append("Date", dateShow)
                .append("StartTime", showtime.getStartHour())
                .append("EndHour", showtime.getEndHour())
                .append("Theater", showtime.getTheater())
                .append("Seats", seats)
                .append("Price", price)
                .append("Tax", iva)
                .append("TotalPrice", totalPrice)
                .append("PurchaseDate", getPurchaseDate());
        return document;
    }
}
