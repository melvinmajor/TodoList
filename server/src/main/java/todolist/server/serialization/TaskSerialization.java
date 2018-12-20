package todolist.server.serialization;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class TaskSerialization {
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    public static String toJson(SerializableTask[] serializableTasks) {
        String output = null;
        try {
            output = mapper.writeValueAsString(serializableTasks);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return output;
    }

    public static SerializableTask[] fromJson(String serializableTaskArrayJson) {
        SerializableTask[] output = null;

        try {
            output = mapper.readValue(serializableTaskArrayJson, SerializableTask[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return output;
    }

}
