/*
 * Este archivo define la clase Window, que proporciona métodos para mostrar ventanas.
 */
package Classes;

import javax.swing.JOptionPane;

/**
 * La clase Window proporciona métodos para mostrar ventanas de depuración.
 */
public class Window {

    /**
     * Muestra una nueva ventana de depuración.
     *
     * @param type El tipo de ventana (danger, info, warning, question).
     * @param message El mensaje que se mostrará en la ventana.
     * @param title El título de la ventana.
     */
    public static void Message(String type, String message, String title) {
        type = type.toLowerCase();
        int num;
        switch (type) {
            case "info" ->
                num = 1;
            case "warning" ->
                num = 2;
            case "question" ->
                num = 3;
            default ->
                num = 0;
        };
        JOptionPane.showMessageDialog(null, message, title, num);
    }
}
