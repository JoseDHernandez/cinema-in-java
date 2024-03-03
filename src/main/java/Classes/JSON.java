/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import Templates.DebugWindow;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

/**
 *
 * @author Jose
 */
public class JSON {

    public JSONArray listToJson(List<String> list) {
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
            DebugWindow window = new DebugWindow();
            window.newWindow("danger", "JSON Error:\n" + e.toString(), "JSON Error");
            return null; // Manejar el error según sea necesario
        }
        return jsonArray;
    }
}
