import hexlet.code.Differ;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Format {
    @Test
    public void json() throws Exception {
        var actual = Differ.generate("src/main/java/hexlet/code/files/file1.json",
                "src/main/java/hexlet/code/files/file2.json");
        var expected = Files.readString(Paths.get("src/test/resources/file")).trim(); //То что нужно?
        assertEquals(expected, actual);
    }
    @Test
    public void yaml() throws Exception {
        var actual = Differ.generate("src/main/java/hexlet/code/files/file1.yaml",
                "src/main/java/hexlet/code/files/file2.yaml");
        var expected = Files.readString(Paths.get("src/test/resources/file")).trim(); //То что нужно?
        assertEquals(expected, actual);
    }
}
