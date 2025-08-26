package hexlet.code.formatters;

import hexlet.code.Status;
import java.util.LinkedList;

import static hexlet.code.Status.UNCHANGED; //ужас сколько малоосмысленного импорта
import static hexlet.code.Status.DELETED;
import static hexlet.code.Status.ADDED;
import static hexlet.code.Status.CHANGED;

public class Json {

    public static String format(LinkedList<Status> resultMap) { //смысл если не json?
        var answer = new StringBuilder();
        answer.append("{");
        for (Status status : resultMap) { //тут статус максимально осложнил всё
            int name = status.getStatusName();
            switch (name) {
                case UNCHANGED:
                    answer.append("\n  \"" + status.getMapKey() + "\": " + stringify(status.getOldValue()));
                    break;
                case DELETED:
                    break;
                case ADDED, CHANGED:
                    answer.append("\n  \"" + status.getMapKey() + "\": " + stringify(status.getNewValue()));
                    break;
                default:
                    throw new RuntimeException("Error in statusName: " + name); //не нужен
            }
        }
        answer.append("\n}");
        return answer.toString();
    } //Статус сломал прошлый лаконичный класс //и сам результат теперь развёрнутый //хотя это выявило скрытые баги

    private static String stringify(Object value) {
        if (value.equals("null")) {
            return "null";
        }
        if (value instanceof String) {
            return "\"" + value + "\"";
        }
        return value.toString();
    }

}
