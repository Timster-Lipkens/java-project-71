package hexlet.code;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
//import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Differ {

    public static void generate(String[] args) {
        System.out.println("Hello World!");
    }

    public static void getData() throws Exception {
        String filepath1 = "src/main/java/hexlet/code/files/file1.json"; //Относительный путь.
        String filepath2 = "src/main/java/hexlet/code/files/file2.json";
        Path filePath1 = Paths.get(filepath1).toAbsolutePath().normalize(); //Абсолютный путь.
        Path filePath2 = Paths.get(filepath2).toAbsolutePath().normalize();
        if (!Files.exists(filePath1)) {
            throw new Exception("File '" + filePath1 + "' not found"); //Проверка (один из вариантов).
        }
        if (!Files.exists(filePath2)) {
            throw new Exception("File '" + filePath2 + "' not found");
        }
        String content1 = Files.readString(filePath1); //Чтение в строку.
        String content2 = Files.readString(filePath2);

        //Вариант Хекслета.
        ObjectMapper objectMapper1 = new ObjectMapper();
        Host host1 = objectMapper1.readValue(content1, Host.class);
        ObjectMapper objectMapper2 = new ObjectMapper();
        Host host2 = objectMapper2.readValue(content2, Host.class);

        //Вариант Мапа из первоначальной документации библиотеки (с моим new objectMapper).
        //Map<String, Integer> map1 = new ObjectMapper().readValue(content1, Map.class);
        //Map<String, Integer> map2 = new ObjectMapper().readValue(content2, Map.class);

        //Вариант Мапа из вторичной документации библиотеки (и в первичной при сложности).
        //Map<String, Object> map1 = new objectMapper.readValue(content1, new TypeReference<Map<String,Object>>(){});
        //Map<String, Object> map2 = new objectMapper.readValue(content2, new TypeReference<Map<String,Object>>(){});
    }

    //public static Map getData(String content) throws Exception { //Непонятное из инструкции проекта.
        //return parse(content);
    //}

}
