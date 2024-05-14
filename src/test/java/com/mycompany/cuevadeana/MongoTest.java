/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.cuevadeana;

import Classes.Movie;
import Classes.User;
import com.mongodb.client.MongoCursor;
import java.util.List;
import org.bson.Document;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author User
 */
public class MongoTest {

    public MongoTest() {
    }

    protected final String uri = "mongodb://localhost:27017";
    protected final String name = "cine";

    /**
     * Test de conexion
     */
    @org.junit.jupiter.api.Test
    public void testConnection() {
        System.out.println("Test de conexion");
        //Instancia de mongo
        Mongo mongoDB = new Mongo(uri, name);
        //Lista de bases de datos
        MongoCursor databases = mongoDB.getClient().listDatabaseNames().iterator();
        //varaible de control
        boolean test = false;
        //Buscar base de datos
        String DBName = "cine";
        while (databases.hasNext()) {
            String dbname = (String) databases.next();
            if (dbname.equals(DBName)) {
                test = true;
            }
        }
        mongoDB.closeConnection();
        //test correcto
        assertTrue(test, "Base de datos " + DBName + " no encontrada");
        ///test incorecto
        boolean test2 = false;
        //Buscar base de datos
        String DBNameError = "cine87495";
        while (databases.hasNext()) {
            String dbname = (String) databases.next();
            if (dbname.equals(DBNameError)) {
                test2 = true;
            }
        }
        //test correcto
        assertFalse(test2, "Base de datos " + DBNameError + " fue encontrada");
    }

    /**
     * Registro de usuario
     */
    @org.junit.jupiter.api.Test
    public void testInsert_User() {
        System.out.println("Test de registro de un usuario");
        //varaibles
        String userName = "Jhon steven rojas cruz";
        String pass = "123456";
        //Crear usuario
        User user = new User();
        user.setName(userName);
        user.setIdentification("C.C. 1358965748");
        user.setRol("Cajero");
        user.setCashRegister("Caja 2");
        user.setPassword(pass);
        user.createUserName();
        String USERNAME = user.getUserName();
        //insercion
        Mongo mongoDB = new Mongo(uri, name);
        mongoDB.insert(user);
        //obtener usuario
        Document userDB = mongoDB.findUser(user.hashPassword(pass), USERNAME);
        mongoDB.closeConnection();
        //test
        assertEquals(userDB.getString("Name"), user.getName(), "Los nombres no coinciden");
        assertEquals(userDB.getString("Identification"), user.getIdentification(), "La identificacion no coincide");
        assertEquals(userDB.getString("Rol"), user.getRol(), "Los roles no coinciden");
        assertEquals(userDB.getString("CashRegister"), user.getCashRegister(), "La caja registradora no coincide");
        assertEquals(userDB.getString("Password"), user.hashPassword(pass), "Las contraseñas no coinciden");
        assertEquals(userDB.getString("UserName"), user.getUserName(), "Los nombres de usaurio no coinciden");
        //test de nombre de usuario
        user.createUserName();
        assertFalse(userDB.getString("UserName").equals(user.getUserName()), "El nuevo nombre del usuario: "
                + user.getUserName() + ", es igual al anterior: " + userDB.getString("UserName"));
    }

    /*
    Test de solicitud de 3 peliculas 
    
    NOTA: se deben tener 3 o mas peliculas registradas previamente
     */
    @org.junit.jupiter.api.Test
    public void testGet_Movies() {
        System.out.println("Test de obtener peliculas");
        //Conecto y solicito 3 peliculas
        Mongo mongoDB = new Mongo(uri, name);
        List<Movie> listMovies = mongoDB.getMovies(0, 3);
        mongoDB.closeConnection();
        //test
        assertFalse(listMovies.isEmpty(), "La lista de peliculas esta vacia");
        assertTrue(listMovies.size() == 3, "La cantidad de peliculas solicitadas es diferente");

    }
}
