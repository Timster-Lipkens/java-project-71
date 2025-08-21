package hexlet.code;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

//import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.LinkedHashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
//import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
//import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public class Differ {

    public static Object generate(String filepath1, String filepath2, String format) throws Exception {
        TreeMap<String, Object> sortedMap1 = new TreeMap<>(getData(filepath1)); //авто-сортировка от дерева.
        TreeMap<String, Object> sortedMap2 = new TreeMap<>(getData(filepath2)); //может сортед Мап лучше?
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();

        while (!sortedMap1.isEmpty() && !sortedMap2.isEmpty()) { //должен пересчитываться.
            var entry1 = sortedMap1.firstEntry();
            var entry2 = sortedMap2.firstEntry();
            String value1 = entry1.getValue() == null ? "null" : entry1.getValue().toString();
            String value2 = entry2.getValue() == null ? "null" : entry2.getValue().toString();

            if ((entry1.getKey()).equals(entry2.getKey())) {  //имеется ли такой же ключ?
                if (value1.equals(value2)) {
                    resultMap.put("  " + entry1.getKey(), value1);
                } else {
                    resultMap.put("- " + entry1.getKey(), value1);
                    resultMap.put("+ " + entry2.getKey(), value2);
                }
                sortedMap1.remove(entry1.getKey()); //удаление первого в этом случае (оптимальный ли метод?)
                sortedMap2.remove(entry2.getKey());
            } else {
                if ((entry1.getKey()).compareTo(entry2.getKey()) < 0) { //1 до 2 (то есть выше).
                    resultMap.put("- " + entry1.getKey(), value1);
                    sortedMap1.remove(entry1.getKey());
                } else {
                    resultMap.put("+ " + entry2.getKey(), value2);
                    sortedMap2.remove(entry2.getKey());
                }
            }
        }

        if (!sortedMap1.isEmpty() || !sortedMap2.isEmpty()) {
            while (!sortedMap1.isEmpty()) {
                var entry1 = sortedMap1.pollFirstEntry();
                String value1 = entry1.getValue() == null ? "null" : entry1.getValue().toString();
                resultMap.put("+ " + entry1.getKey(), value1);
            }
            while (!sortedMap2.isEmpty()) {
                var entry2 = sortedMap2.pollFirstEntry();
                String value2 = entry2.getValue() == null ? "null" : entry2.getValue().toString();
                resultMap.put("+ " + entry2.getKey(), value2);
            }
        }
        return Formatter.choice(resultMap, format);
    }

    public static Map<String, Object> getData(String filepath) throws Exception { //Parser? в отдельный класс?
        Path filePath = Paths.get(filepath).toAbsolutePath().normalize(); //Абсолютный путь.
        if (!Files.exists(filePath)) {
            throw new Exception("File '" + filePath + "' not found"); //Проверка пути (один из вариантов).
        }
        String content = Files.readString(filePath); //Чтение в строку. //Может далее проверку совсем кривого формата.

        if (content.charAt(0) == '{') { //полу-костыль для двух форматов //может напрямую content[0]? не работает
            return new ObjectMapper().readValue(content, new TypeReference<Map<String, Object>>() { }); //json
        } else {
            return new ObjectMapper(new YAMLFactory()).readValue(content, new TypeReference<Map<String, Object>>() { });
            //на основе ИИ, своих соображений и аналогии - документация кривая.
        }
    }

}
