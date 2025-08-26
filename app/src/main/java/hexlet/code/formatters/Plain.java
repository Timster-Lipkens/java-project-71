package hexlet.code.formatters;

import hexlet.code.Status;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static hexlet.code.Status.UNCHANGED; //ужас сколько малоосмысленного импорта
import static hexlet.code.Status.DELETED;
import static hexlet.code.Status.ADDED;
import static hexlet.code.Status.CHANGED;

public class Plain {

    public static String format(LinkedList<Status> resultMap) {
        var answer = new StringBuilder();
        for (Status status : resultMap) { //тут Статус должен помочь
            int name = status.getStatusName();
            switch (status.getStatusName()) {
                case UNCHANGED:
                    break;
                case DELETED:
                    answer.append("Property '" + status.getMapKey() + "' was removed\n");
                    break;
                case ADDED:
                    answer.append("Property '" + status.getMapKey() + "' was added with value: "
                            + stringify(status.getNewValue()) + "\n");
                    break;
                case CHANGED:
                    answer.append("Property '" + status.getMapKey() + "' was updated. From "
                            + stringify(status.getOldValue()) + " to " + stringify(status.getNewValue()) + "\n");
                    break;
                default:
                    throw new RuntimeException("Error in statusName: " + name); //не нужен
            }
        }
        return answer.toString().trim(); // обрезка лишнего переноса
    }

    private static String stringify(Object value) {
        if (value.equals("null")) {
            return "null";
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }
        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        }
        return value.toString();
    }

}
