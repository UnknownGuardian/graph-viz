package vizGenerator;

import com.google.gson.Gson;

public class JsonDecoder {

    public static <T> T decode(String value, Class<T> returnType) {
        return (new Gson()).fromJson(value, returnType);
    }

}