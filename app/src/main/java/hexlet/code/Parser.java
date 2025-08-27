package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.Map;

public class Parser {
    public static Map<String, Object> getData(String content, String type) throws Exception { //название сохранить?
        switch (type) { // тип в отличии от (выводного) формата - вводной
            case "json":
                return new ObjectMapper()
                        .readValue(content, new TypeReference<Map<String, Object>>() { });
            case "yaml", "yml":
                return new ObjectMapper(new YAMLFactory())
                        .readValue(content, new TypeReference<Map<String, Object>>() { });
            default:
                throw new RuntimeException("This file type not supported: " + type);
        }
    }
}
