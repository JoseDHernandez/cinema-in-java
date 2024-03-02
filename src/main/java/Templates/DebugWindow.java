/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Templates;

import javax.swing.JOptionPane;

/**
 *
 * @author Jose
 */
public class DebugWindow {

    /*
    0 danger
     1 info
     2 warning
     3 question
     */

    public void newWindow(String type, String message, String title) {
        
        JOptionPane.showMessageDialog(null, message, title, typeOfWindow(type));

    }

    private int typeOfWindow(String type) {
       type = type.toLowerCase();
        return switch (type) {
            case "info" -> 1;
            case "warning" -> 2;
            case "question" -> 3;
            default -> 0;
        };
    }
}
