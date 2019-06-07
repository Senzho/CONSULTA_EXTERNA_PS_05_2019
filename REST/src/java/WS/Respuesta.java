package WS;

import org.json.JSONObject;

/**
 *
 * @author Victor Javier
 */
public class Respuesta {
    private final JSONObject json;
    
    public Respuesta(boolean token) {
        this.json = new JSONObject();
        this.json.put("token", token);
    }
    
    public JSONObject getJson() {
        return this.json;
    }
    
    @Override
    public String toString() {
        return this.json.toString();
    }
}
