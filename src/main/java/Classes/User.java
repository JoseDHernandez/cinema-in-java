/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.bson.Document;

/**
 *
 * @author Jose
 */
public class User {

    private String userName;
    private String id;
    private String password;
    private String nombre;
    private String identificacion;
    private String rol;
    private String Caja;

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
            e.printStackTrace();
            return null;
        }
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    //Converters
    public Document convert() {
        return new Document().append("UserName", userName)
                .append("Password", password)
                .append("Rol", rol);
    }
}
