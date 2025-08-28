package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.LinkedList;

public class Formatter {
    public static String choice(LinkedList<Status> statusList, String format) throws Exception {
        switch (format) {
            case "stylish":
                return Stylish.format(statusList);
            case "plain":
                return Plain.format(statusList);
            case "json":
                return Json.format(statusList);
            default:
                throw new RuntimeException("Format not supported: " + format); //почему не нужен throws Exception?
        }
    }
}
