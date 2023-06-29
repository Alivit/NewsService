package ru.clevertec.news.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.yaml.snakeyaml.Yaml;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Map;

public class Mapper {

    public static final String ApplicationYamlFileName = "src/main/resources/application.yml";

    public static Map<String, Map<String,Object>> getProperties(){
        try {
            return new Yaml().load(new FileReader(ApplicationYamlFileName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static Map<String, Object> toMap(Object obj) throws JsonProcessingException {
        return newMapper().readValue(toString(obj), new TypeReference<>() {});
    }

    public static String toString(Object obj) throws JsonProcessingException {
        return newMapper().writeValueAsString(obj);
    }

    private static ObjectMapper newMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
        mapper.setLocale(Locale.ENGLISH);
        mapper.registerModule(new JSR310Module());
        return mapper;
    }
}
