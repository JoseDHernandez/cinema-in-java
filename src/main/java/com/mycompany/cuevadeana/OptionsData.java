/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cuevadeana;

import Classes.User;

/**
 *
 * @author Jose
 */
public class OptionsData {

    private static OptionsData instance;
    private String URIMONGO = "mongodb://localhost:27017";
    private String DBNAME = "cine";
    private User user;

    public static OptionsData getInstance() {
        if (instance == null) {
            instance = new OptionsData();
        }
        return instance;
    }

    public String getURI() {
        return URIMONGO;
    }

    public void setURI(String uri) {
        URIMONGO = uri;
    }

    public String getDBName() {
        return DBNAME;
    }

    public void setDBName(String name) {
        DBNAME = name;
    }

    public User getUser() {
        if (user == null) {
            user = new User();
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
