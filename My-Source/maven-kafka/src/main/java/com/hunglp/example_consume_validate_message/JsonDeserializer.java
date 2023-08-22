package com.hunglp.example_consume_validate_message;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class JsonDeserializer<T> implements Deserializer<T> {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private Class<T> className;
    public static final String KEY_CLASS_NAME_CONFIG = "key.class.name";
    public static final String VALUE_CLASS_NAME_CONFIG = "value.class.name";



    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        if (isKey) {
            //noinspection unchecked
            className = (Class<T>) configs.get(KEY_CLASS_NAME_CONFIG);
            return;
        }
        //noinspection unchecked
        className = (Class<T>) configs.get(VALUE_CLASS_NAME_CONFIG);
    }

    @Override
    public T deserialize(String topic, byte[] data) {
        if (data == null) {
            return null;
        }
        try {
            return objectMapper.readValue(data, className);
        } catch (Exception e) {
            throw new SerializationException(e);
        }
    }

}