/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Jose
 */
public class REGEX {

    private final String RegexText = "A-Za-z-ZáéíóúÁÉÍÓÚüÜñÑ";
    private final String[] patterns = {
        /*0*/"^([" + RegexText + "\\s\'-,.()\\\"!¡?¿/%#0-9]{2,})+\\S$", //normal text (Description)
        /*1*/ "^([" + RegexText + "\\s\'-.]{2,})+\\S$",//Text without characters
        /*2*/ "^([" + RegexText + "\\s\'-,.]{2,})+\\S$",//Text with dot and  comma
        /*3*/ "^([0-9]{2}-){2}[0-9]{4}$",//Date dd-mm-yyyy
    };

    public boolean validate(int type, String text) {
        Pattern pattern = Pattern.compile(patterns[type], Pattern.UNICODE_CASE);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }

}
