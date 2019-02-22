package scoreapp.testtask.com.data.utility.json_converter;

public interface JsonConverter {
    String toJson(Object o);
    <T> T toObject(String json, Class<T> type);
}
