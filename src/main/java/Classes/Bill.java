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
    private int Code;
    private String cashRegister;
    private String cashier;
    private String movie;
    private Showtime showtime;
    private String seats;
    private double price;
    private final double iva = 0.19;
    private double totalPrice;
    private LocalDateTime purchaseDate;

    public Bill(String nit, int codigoFactura, String asientos,
            double valor) {
        this.nit = nit;
        this.Code = codigoFactura;
        this.seats = asientos;
        this.price = valor;
        this.totalPrice = valor;
        /* datos desde clase usuario y funcion
        this.fechaHoraCompra = fechaHoraCompra;
        this.caja = caja;
        this.Cajero = Cajero;
        this.pelicula = pelicula;
        this.fechaFuncion = fechaFuncion;
        this.ubicacionSala = ubicacionSala;*/
    }

    // Getters and setters
    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
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
