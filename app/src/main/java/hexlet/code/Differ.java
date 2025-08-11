package hexlet.code;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

//import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

public class Differ {

    public static String generate(String filepath1, String filepath2) throws Exception {
        TreeMap<String, Object> sortedMap1 = new TreeMap<>(getData(filepath1)); //авто-сортировка от дерева.
        TreeMap<String, Object> sortedMap2 = new TreeMap<>(getData(filepath2));
        var answer = new StringBuilder();
        answer.append("{ "); //перенос строк в строке?

        while (!sortedMap1.isEmpty() && !sortedMap2.isEmpty()) { //должен пересчитываться.
            var entry1 = sortedMap1.firstEntry();
            var entry2 = sortedMap2.firstEntry();
            if ( (entry1.getKey()).equals(entry2.getKey()) ) {  //имеется ли такой же ключ?
                 if ( (entry1.getValue()).equals(entry2.getValue()) ) {
                     answer.append("\n   " + entry1.getKey() + ": " + entry1.getValue());
                 } else {
                     answer.append("\n - " + entry1.getKey() + ": " + entry1.getValue());
                     answer.append("\n + " + entry2.getKey() + ": " + entry2.getValue());
                 }
                sortedMap1.remove(entry1.getKey()); //удаление первого в этом случае (оптимальный ли метод?)
                sortedMap2.remove(entry2.getKey());
            } else {
                if ( (entry1.getKey()).compareTo(entry2.getKey()) < 0 ) { //1  до 2 (то есть выше).
                    answer.append("\n - " + entry1.getKey() + ": " + entry1.getValue());
                    sortedMap1.remove(entry1.getKey());
                } else {
                    answer.append("\n + " + entry2.getKey() + ": " + entry2.getValue());
                    sortedMap2.remove(entry2.getKey());
                }
            }
        }

        if (!sortedMap1.isEmpty() || !sortedMap2.isEmpty()) {
            while (!sortedMap1.isEmpty()) {
                var entry1 = sortedMap1.pollFirstEntry();
                answer.append("\n + " + entry1.getKey() + ": " + entry1.getValue());
            }
            while (!sortedMap2.isEmpty()) {
                var entry2 = sortedMap2.pollFirstEntry();
                answer.append("\n + " + entry2.getKey() + ": " + entry2.getValue());
            }
        }
        answer.append("\n }");
        return answer.toString();
    }

    public static Map<String,Object> getData(String filepath) throws Exception {
        Path filePath = Paths.get(filepath).toAbsolutePath().normalize(); //Абсолютный путь.
        if (!Files.exists(filePath)) {
            throw new Exception("File '" + filePath + "' not found"); //Проверка пути (один из вариантов).
        }
        String content = Files.readString(filePath); //Чтение в строку. //Может далее проверку совсем кривого формата.
        //ObjectMapper objectMapper = new ObjectMapper(); //Парсинг Мап из документации (нужен прям отдельный new?).
        return new ObjectMapper().readValue(content, new TypeReference<Map<String,Object>>(){}); //Без специфики Host.
        //Host host = new ObjectMapper().readValue(content, Host.class); //Вариант Хекслета (с моим new).
    }

}
