package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {

    public static Map<String, Object> getData(String filepath) throws Exception { //название сохранить?
        Path filePath = Paths.get(filepath).toAbsolutePath().normalize(); //Абсолютный путь.
        if (!Files.exists(filePath)) { //Проверка пути (один из вариантов).
            throw new RuntimeException("File '" + filePath + "' not found"); //RuntimeException? Чем плох Exception?
        }
        String content = Files.readString(filePath); //Чтение в строку. //Может далее проверку совсем кривого формата.

        if (content.charAt(0) == '{') { //работает для двух форматов //может напрямую content[0]? не работает
            return new ObjectMapper().readValue(content, new TypeReference<Map<String, Object>>() { }); //json
        } else { //на основе ИИ, своих соображений и аналогии - документация кривая.
            return new ObjectMapper(new YAMLFactory()).readValue(content, new TypeReference<Map<String, Object>>() { });
        }
    }

}
