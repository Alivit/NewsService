package ru.clevertec.news.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;

import java.util.Locale;
import java.util.Map;

/**
 * The Mapper class is responsible for mapping data between different objects or data structures.
 * It provides methods to convert data from one type to another.
 */
public class Mapper{

    /**
     * Methods to convert data from object to map.
     * @param obj The obj containing the details of the object.
     * @return Map with converting object
     */
    public static Map<String, Object> toMap(Object obj) throws JsonProcessingException {
        return newMapper().readValue(toString(obj), new TypeReference<>() {});
    }

    /**
     * Methods to convert data from object to json.
     * @param obj The obj containing the details of the object.
     * @return JSON with converting object
     */
    public static String toString(Object obj) throws JsonProcessingException {
        return newMapper().writeValueAsString(obj);
    }

    /**
     * Methods for configuration mapper.
     * @return mapper with config properties
     */
    private static ObjectMapper newMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
        mapper.setLocale(Locale.ENGLISH);
        mapper.registerModule(new JSR310Module());
        return mapper;
    }

}
