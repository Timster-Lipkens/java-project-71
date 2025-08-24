import hexlet.code.Differ;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Format {

    //по инструкции избыточное //можно сделать здесь по-умолчанию через опционал
    @Test
    public void defaultJson0() throws Exception {
        var actual = Differ.generate("src/main/java/hexlet/code/files/fileI.json",
                "src/main/java/hexlet/code/files/fileII.json");
        var expected = Files.readString(Paths.get("src/test/resources/file0")).trim();
        assertEquals(expected, actual);
    }
    @Test
    public void defaultYaml0() throws Exception {
        var actual = Differ.generate("src/main/java/hexlet/code/files/fileI.yaml",
                "src/main/java/hexlet/code/files/fileII.yaml");
        var expected = Files.readString(Paths.get("src/test/resources/file0")).trim();
        assertEquals(expected, actual);
    }

    //рекурсивные тесты
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

    //форматные тесты
    @Test
    public void plainJson12() throws Exception {
        var actual = Differ.generate("src/main/java/hexlet/code/files/file1.json",
                "src/main/java/hexlet/code/files/file2.json", "plain");
        var expected = Files.readString(Paths.get("src/test/resources/plain")).trim();
        assertEquals(expected, actual);
    }
    @Test
    public void plainYaml12() throws Exception {
        var actual = Differ.generate("src/main/java/hexlet/code/files/file1.yaml",
                "src/main/java/hexlet/code/files/file2.yaml", "plain");
        var expected = Files.readString(Paths.get("src/test/resources/plain")).trim();
        assertEquals(expected, actual);
    }

    @Test
    public void jsonJson12() throws Exception {
        var actual = Differ.generate("src/main/java/hexlet/code/files/file1.json",
                "src/main/java/hexlet/code/files/file2.yaml", "json");
        var expected = Files.readString(Paths.get("src/test/resources/file12.json")).trim();
        assertEquals(expected, actual);
    }
    @Test
    public void jsonYaml12() throws Exception {
        var actual = Differ.generate("src/main/java/hexlet/code/files/file1.yaml",
                "src/main/java/hexlet/code/files/file2.yaml", "json");
        var expected = Files.readString(Paths.get("src/test/resources/file12.json")).trim();
        assertEquals(expected, actual);
    }

}
