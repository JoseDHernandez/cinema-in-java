/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.time.LocalDateTime;

/**
 *
 * @author Jose
 */
public class Bill {

    /*
    Cueva de ana
    NIT
    Dirrecion
    Codigo factura
    Caja
    Cajero/a:
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
    private String nit;
    private String Code;
    private String cashRegister;
    private String cashier;
    private String movie;
    private Showtime showtime;
    private String seats;
    private double price;
    private final double iva;
    private double totalPrice;
    private LocalDateTime purchaseDate;

    public Bill() {
        nit = "00000000000";
        Code = "";
        seats = "";
        cashRegister = "";
        cashier = "";
        movie = null;
        showtime = null;
        seats = "";
        price = 0.0;
        iva = 0.19;
        totalPrice = 0.0;
        purchaseDate = null;

    }

    // Getters and setters
    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public String getCashRegister() {
        return cashRegister;
    }

    public void setCashRegister(String cashRegister) {
        this.cashRegister = cashRegister;
    }

    public String getCashier() {
        return cashier;
    }

    public void setCashier(String cashier) {
        this.cashier = cashier;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public void setShowtime(Showtime showtime) {
        this.showtime = showtime;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getIva() {
        return iva;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

}
