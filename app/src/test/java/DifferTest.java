import hexlet.code.Differ;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Paths;

public class DifferTest {

    private static String resultDefault; //для зоны видимости
    private static String resultStylish;
    private static String resultPlain;
    private static String resultJson;

    @BeforeAll
    public static void expected() throws Exception { // немного уменьшает дублирование
        resultDefault = Files.readString(Paths.get("src/test/resources/file0"));
        resultStylish = Files.readString(Paths.get("src/test/resources/file12"));
        resultPlain = Files.readString(Paths.get("src/test/resources/plain"));
        resultJson = Files.readString(Paths.get("src/test/resources/file12.json"));
    }

    @Test //тесты умолчания
    public void defaultJson0() throws Exception {
        var actual = Differ.generate("src/main/java/hexlet/code/files/fileI.json",
                "src/main/java/hexlet/code/files/fileII.json");
        assertEquals(resultDefault, actual);
    }
    @Test
    public void defaultYaml0() throws Exception {
        var actual = Differ.generate("src/main/java/hexlet/code/files/fileI.yaml",
                "src/main/java/hexlet/code/files/fileII.yaml");
        assertEquals(resultDefault, actual);
    }


    @Test //рекурсивные тесты
    public void stylishJson12() throws Exception {
        var actual = Differ.generate("src/main/java/hexlet/code/files/file1.json",
                "src/main/java/hexlet/code/files/file2.json", "stylish");
        assertEquals(resultStylish, actual);
    }
    @Test
    public void stylishYaml12() throws Exception {
        var actual = Differ.generate("src/main/java/hexlet/code/files/file1.yaml",
                "src/main/java/hexlet/code/files/file2.yaml", "stylish");
        assertEquals(resultStylish, actual);
    }

    @Test //форматные тесты
    public void plainJson12() throws Exception {
        var actual = Differ.generate("src/main/java/hexlet/code/files/file1.json",
                "src/main/java/hexlet/code/files/file2.json", "plain");
        assertEquals(resultPlain, actual);
    }
    @Test
    public void plainYaml12() throws Exception {
        var actual = Differ.generate("src/main/java/hexlet/code/files/file1.yaml",
                "src/main/java/hexlet/code/files/file2.yaml", "plain");
        assertEquals(resultPlain, actual);
    }

    @Test
    public void jsonJson12() throws Exception {
        var actual = Differ.generate("src/main/java/hexlet/code/files/file1.json",
                "src/main/java/hexlet/code/files/file2.json", "json"); // как это работало с yaml?
        assertEquals(resultJson, actual);
    }
    @Test
    public void jsonYaml12() throws Exception {
        var actual = Differ.generate("src/main/java/hexlet/code/files/file1.yaml",
                "src/main/java/hexlet/code/files/file2.yaml", "json");
        assertEquals(resultJson, actual);
    }

}
