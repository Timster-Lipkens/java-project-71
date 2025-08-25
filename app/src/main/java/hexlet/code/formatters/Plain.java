package hexlet.code.formatters;

import java.util.LinkedHashMap;

public class Plain {

    public static String format(LinkedHashMap<String, Object> resultMap) {
        for (String key : resultMap.keySet()) {
            var value = resultMap.get(key);
            char value0 = value.toString().charAt(0);
            if (value0 == '[' || value0 == '{' || value0 == '-') { //специфика json и yaml
                resultMap.put(key, "[complex value]");
            } else {
                if (value instanceof String && !value.equals("null")) { //специфика plain
                    resultMap.put(key, "'" + value + "'");
                }
            }
        }
        var answer = new StringBuilder();
        boolean link = false; //прошлое значение отрицательное (подозрение на связь)
        String past = null; //сохранение прошлого ключа в таком случае

        for (String key : resultMap.keySet()) { //проблема лишней пустой строки в конце
            if (!link) { //нет связи
                if (key.charAt(0) == '+') { //чистая добавка
                    answer.append("Property '" + key.substring(2)
                            + "' was added with value: " + resultMap.get(key) + "\n");
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
                        link = false; //не продолжаем проверки
                        if (key.charAt(0) == '+') {
                            answer.append("Property '" + key.substring(2)
                                    + "' was added with value: " + resultMap.get(key) + "\n");
                        }
                    }
                }
            }
        } //гигантский цикл ради фиксации обновлений..
        return answer.toString().trim(); //trim() обрежет финальную строку?
    }

}
