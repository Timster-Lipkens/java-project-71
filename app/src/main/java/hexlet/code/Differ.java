package hexlet.code;

//import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.TreeMap;
import java.util.LinkedHashMap;

public class Differ {

    public static Object generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish"); //перегрузка под тесты Хекслета. //Optional<Integer>
    }

    public static Object generate(String filepath1, String filepath2, String format) throws Exception {
        TreeMap<String, Object> sortedMap1 = new TreeMap<>(Parser.getData(readData(filepath1))); //авто-сортировка
        TreeMap<String, Object> sortedMap2 = new TreeMap<>(Parser.getData(readData(filepath2))); //от дерева.
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        Comparator.analyzeData(sortedMap1, sortedMap2, resultMap);
        return Formatter.choice(resultMap, format);
    }

    public static String readData(String filepath) throws Exception {
        Path filePath = Paths.get(filepath).toAbsolutePath().normalize(); //Абсолютный путь.
        if (!Files.exists(filePath)) { //Проверка пути (один из вариантов).
            throw new RuntimeException("File '" + filePath + "' not found"); //RuntimeException? Чем плох Exception?
        }
        return Files.readString(filePath); //Чтение в строку. //Может далее проверку совсем кривого формата.
    }

}
