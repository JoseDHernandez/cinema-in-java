/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import org.bson.Document;

/**
 *
 * @author Jose
 */
public class User {

    private String userName;
    private String id;
    private String password;
    private String name;
    private String identification;
    private String rol;
    private String cashRegister;

    public User() {
        userName = "";
        id = "";
        password = "";
        name = "";
        identification = "";
        rol = "Cajero";
        cashRegister = "";
    }

    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public void setCashRegister(String cashRegister) {
        this.cashRegister = cashRegister;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = hashPassword(password);
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getUserName() {
        return userName;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getRol() {
        return rol;
    }

    public String getIdentification() {
        return identification;
    }

    public String getName() {
        return name;
    }

    public String getCashRegister() {
        return cashRegister;
    }

    //Methods
    public String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(password.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public void createUserName() {
        String[] partsName = name.split(" ");
        String temp = (rol.equals("Administrador") ? "AD" : "CR") + "";
        Random random = new Random();
        for (String text : partsName) {
            temp += text.substring(0, text.length() - random.nextInt(1, text.length() - 1));
        }
        System.out.println(temp);
        userName = temp;
    }

    //Converters
    public Document convert() {
        return new Document().append("UserName", userName)
                .append("Name", name)
                .append("Identification", identification)
                .append("Rol", rol)
                .append("CashRegister", rol.equals("Administrador") ? null : cashRegister)
                .append("Password", password);
    }
}
