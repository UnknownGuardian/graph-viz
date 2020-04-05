package vizGenerator;

import com.google.gson.GsonBuilder;

public class JsonEncoder {

    public static String encode(Object object) {
        return (new GsonBuilder().setPrettyPrinting().create()).toJson(object);
    }

}
