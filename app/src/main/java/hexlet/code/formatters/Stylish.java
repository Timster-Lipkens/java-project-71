package hexlet.code.formatters;

import java.util.LinkedHashMap;

public class Stylish {
    public static String format(LinkedHashMap<String, Object> resultMap) {
        var answer = new StringBuilder();
        answer.append("{");
        for (String key : resultMap.keySet()) {
            answer.append("\n  " + key + ": " + resultMap.get(key));
        }
        answer.append("\n}");
        return answer.toString();
    }
}
