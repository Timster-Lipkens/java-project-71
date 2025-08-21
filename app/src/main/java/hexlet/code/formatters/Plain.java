package hexlet.code.formatters;

import java.util.LinkedHashMap;

public class Plain {

    public static String format(LinkedHashMap<String, Object> resultMap) {
        for (String key : resultMap.keySet()) {
            if (key.charAt(0) == '[' || key.charAt(0) == '{') {
                resultMap.put(key, "[complex value]");
            }
        }
        var answer = new StringBuilder();
        boolean link = false; //прошлое значение отрицательное (подозрение на связь)
        String past = null; //сохранение прошлого ключа в таком случае

        for (String key : resultMap.keySet()) { //проблема лишней пустой строки в конце
            if (!link) { //нет связи
                if (key.charAt(0) == '+') { //чистая добавка
                    answer.append("Property '" + key.substring(2)
                            + "' was added with value: '" + resultMap.get(key) + "'\n");
                }
                if (key.charAt(0) == '-') { //подозрение на связь
                    link = true;
                    past = key;
                }
                //в случае ни плюса ни минуса - ничего по формату
            } else {
                String key0 = past.substring(2);
                if (key0.equals(key.substring(2))) { //подозрение оказалось верным - обновление
                    answer.append("Property '" + key0 + "' was updated. From "
                            + resultMap.get(past) + " to " + resultMap.get(key) + "\n");
                    link = false;
                } else {
                    answer.append("Property '" + key0 + "' was removed\n"); //подозрение не оправдалось
                    if (key.charAt(0) != '-') {
                        link = false;
                    }
                }
            }
        }
        return answer.toString();
    }

}
