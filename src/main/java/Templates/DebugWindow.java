/*
 * Este archivo define la clase DebugWindow, que proporciona métodos para mostrar ventanas de depuración.
 */
package Templates;

import javax.swing.JOptionPane;

/**
 * La clase DebugWindow proporciona métodos para mostrar ventanas de depuración.
 */
public class DebugWindow {

    /**
     * Muestra una nueva ventana de depuración.
     *
     * @param type El tipo de ventana (danger, info, warning, question).
     * @param message El mensaje que se mostrará en la ventana.
     * @param title El título de la ventana.
     */
    public void newWindow(String type, String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, typeOfWindow(type));
    }

    private int typeOfWindow(String type) {
        type = type.toLowerCase();
        return switch (type) {
            case "info" ->
                1;
            case "warning" ->
                2;
            case "question" ->
                3;
            default ->
                0;
        };
    }
}
