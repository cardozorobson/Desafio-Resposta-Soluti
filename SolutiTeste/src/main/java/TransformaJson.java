
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.Serializable;



public class TransformaJson<Classe> implements Serializable {

    private Gson gson = new Gson();

    
    public JSONObject converteJSON(Classe objeto) {
        JSONObject json = new JSONObject();
        try {
            String string = gson.toJson(objeto);
            json = new JSONObject(string);
        } catch (Exception e) {
            System.err.println("Transformar String para Json");
        }
        return json;
    }

   

}
