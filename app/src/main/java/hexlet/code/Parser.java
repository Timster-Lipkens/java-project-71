package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.Map;

public class Parser {

    public static Map<String, Object> getData(String content) throws Exception { //название сохранить?
        if (content.charAt(0) == '{') { //работает для двух форматов //может напрямую content[0]? не работает
            return new ObjectMapper().readValue(content, new TypeReference<Map<String, Object>>() { }); //json
        } else { //на основе ИИ, своих соображений и аналогии - документация кривая.
            return new ObjectMapper(new YAMLFactory()).readValue(content, new TypeReference<Map<String, Object>>() { });
        }
    }

}
