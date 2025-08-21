import hexlet.code.Differ;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Format {
    //по инструкции избыточное
    @Test
    public void stylishJson12() throws Exception {
        var actual = Differ.generate("src/main/java/hexlet/code/files/file1.json",
                "src/main/java/hexlet/code/files/file2.json", "stylish");
        var expected = Files.readString(Paths.get("src/test/resources/file12")).trim();
        assertEquals(expected, actual);
    }
    @Test
    public void stylishYaml12() throws Exception {
        var actual = Differ.generate("src/main/java/hexlet/code/files/file1.yaml",
                "src/main/java/hexlet/code/files/file2.yaml", "stylish");
        var expected = Files.readString(Paths.get("src/test/resources/file12")).trim();
        assertEquals(expected, actual);
    }
    //рекурсивные тесты
    @Test
    public void stylishJson1122() throws Exception {
        var actual = Differ.generate("src/main/java/hexlet/code/files/file11.json",
                "src/main/java/hexlet/code/files/file22.json", "stylish");
        var expected = Files.readString(Paths.get("src/test/resources/file1122")).trim();
        assertEquals(expected, actual);
    }
    @Test
    public void stylishYaml1122() throws Exception {
        var actual = Differ.generate("src/main/java/hexlet/code/files/file11.yaml",
                "src/main/java/hexlet/code/files/file22.yaml", "stylish");
        var expected = Files.readString(Paths.get("src/test/resources/file1122")).trim();
        assertEquals(expected, actual);
    }
    //форматные тесты
    @Test
    public void plain() throws Exception {
        var actual = Differ.generate("src/main/java/hexlet/code/files/file11.yaml",
                "src/main/java/hexlet/code/files/file22.yaml", "plain");
        var expected = Files.readString(Paths.get("src/test/resources/plain")).trim();
        assertEquals(expected, actual);
    }
    @Test
    public void json() throws Exception {
        var actual = Differ.generate("src/main/java/hexlet/code/files/file11.yaml",
                "src/main/java/hexlet/code/files/file22.yaml", "json");
        var expected = Files.readString(Paths.get("src/test/resources/file1122.json")).trim();
        assertEquals(expected, actual);
    }

}
