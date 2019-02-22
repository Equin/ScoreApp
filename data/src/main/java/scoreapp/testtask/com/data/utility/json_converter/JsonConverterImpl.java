package scoreapp.testtask.com.data.utility.json_converter;

import com.google.gson.Gson;

public class JsonConverterImpl implements JsonConverter {

    private final Gson gson;

    public JsonConverterImpl(Gson gson) {
        this.gson = gson;
    }

    @Override
    public String toJson(Object o) {
        return this.gson.toJson(o);
    }

    @Override
    public <T> T toObject(String json, Class<T> type) {
        return this.gson.fromJson(json, type);
    }
}
