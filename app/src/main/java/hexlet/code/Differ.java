package hexlet.code;

//import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.LinkedList;
import java.util.Map;

public class Differ {

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish"); //перегрузка под тесты Хекслета. //Optional<Integer>
    }

    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        var type = filepath1.substring(filepath1.lastIndexOf('.') + 1); //должен совпадать с filepath2
        String content1 = readData(filepath1); //более читабельно
        String content2 = readData(filepath2); //но кажется из-за этого билд вместо 5 секунд теперь 10
        Map<String, Object> parserMap1 = Parser.getData(content1, type);
        Map<String, Object> parserMap2 = Parser.getData(content2, type);
        LinkedList<Status> resultMap = Comparator.analyzeData(parserMap1, parserMap2);
        return Formatter.choice(resultMap, format);
    }

    public static String readData(String filepath) throws Exception {
        Path filePath = Paths.get(filepath).toAbsolutePath().normalize(); //Абсолютный путь.
        if (!Files.exists(filePath)) { //Проверка пути (один из вариантов).
            throw new RuntimeException("File '" + filePath + "' not found");
        }
        return Files.readString(filePath); //Чтение в строку. //Может далее проверку совсем кривого формата.
    }

}
