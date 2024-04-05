/*
 * Este archivo define la clase JSON, que proporciona métodos para convertir listas de cadenas a formato JSON.
 */
package Classes;

import Templates.DebugWindow;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * La clase JSON proporciona métodos para convertir listas de cadenas a formato
 * JSON.
 */
public class JSON {

    /**
     * Convierte una lista de cadenas a un objeto JSONArray en formato JSON.
     *
     * @param list La lista de cadenas a convertir.
     * @return El objeto JSONArray resultante (Retorna null en caso de error).
     */
    public static JSONArray listToJson(List<String> list) {
        JSONArray jsonArray;
        String temp = "";
        try {
            if (list == null) {
                temp = "";
            } else if (list.size() == 1) {
                temp = "\"" + list.get(0) + "\"";
            } else {
                for (String t : list) {
                    temp += ",\"" + t + '"';
                }
                temp = temp.substring(1);
            }
            jsonArray = new JSONArray("[" + temp + "]");
        } catch (JSONException e) {
            DebugWindow.Message("danger", "JSON Error:\n" + e.toString(), "JSON Error");
            return null; // Manejar el error según sea necesario
        }
        return jsonArray;
    }
}
