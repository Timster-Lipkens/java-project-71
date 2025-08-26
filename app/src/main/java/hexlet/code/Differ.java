package hexlet.code;

//import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.TreeMap;

public class Differ {

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish"); //перегрузка под тесты Хекслета. //Optional<Integer>
    }

    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        var type = filepath1.substring(filepath1.lastIndexOf('.') + 1); //должен совпадать с filepath2
        TreeMap<String, Object> sortedMap1 = new TreeMap<>(Parser.getData(readData(filepath1), type)); //сортировка
        TreeMap<String, Object> sortedMap2 = new TreeMap<>(Parser.getData(readData(filepath2), type)); //от дерева.
        return Formatter.choice(Comparator.analyzeData(sortedMap1, sortedMap2), format); // может стоит разделить
    }

    public static String readData(String filepath) throws Exception {
        Path filePath = Paths.get(filepath).toAbsolutePath().normalize(); //Абсолютный путь.
        if (!Files.exists(filePath)) { //Проверка пути (один из вариантов).
            throw new RuntimeException("File '" + filePath + "' not found");
        }
        return Files.readString(filePath); //Чтение в строку. //Может далее проверку совсем кривого формата.
    }

}
