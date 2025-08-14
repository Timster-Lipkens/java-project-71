import hexlet.code.Differ;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Paths;
import java.nio.file.Files;

public class Json {
    @Test
    public void test() throws Exception {
        var actual = Differ.generate("src/main/java/hexlet/code/files/file1.json",
                "src/main/java/hexlet/code/files/file2.json");
        var expected = Files.readString(Paths.get("src/test/resources/files.json")).trim();
        assertEquals(expected, actual);
    }
}
