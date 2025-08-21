package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.LinkedHashMap;

public class Formatter {
    public static Object choice(LinkedHashMap<String, Object> resultMap, String format) {
        switch (format) {
            case "plain":
                return Plain.format(resultMap); //может переназвать.
            case "json":
                return Json.format(resultMap);
            default:
                return Stylish.format(resultMap);
        }
    }
}
