package hexlet.code.formatters;

import hexlet.code.Status;
import java.util.LinkedList;

import static hexlet.code.Status.UNCHANGED; //ужас сколько малоосмысленного импорта
import static hexlet.code.Status.DELETED;
import static hexlet.code.Status.ADDED;
import static hexlet.code.Status.CHANGED;

public class Stylish {

    public static String format(LinkedList<Status> statusList) {
        var answer = new StringBuilder();
        answer.append("{");
        for (Status status : statusList) { //тут статус максимально осложнил всё
            String name = status.getStatusName();
            switch (name) {
                case UNCHANGED:
                    answer.append("\n    " + status.getMapKey() + ": " + status.getOldValue());
                    break;
                case DELETED:
                    answer.append("\n  - " + status.getMapKey() + ": " + status.getOldValue());
                    break;
                case ADDED:
                    answer.append("\n  + " + status.getMapKey() + ": " + status.getNewValue());
                    break;
                case CHANGED:
                    answer.append("\n  - " + status.getMapKey() + ": " + status.getOldValue());
                    answer.append("\n  + " + status.getMapKey() + ": " + status.getNewValue());
                    break;
                default:
                    throw new RuntimeException("Error in statusName: " + name); //не нужен
            }
        }
        answer.append("\n}");
        return answer.toString();
    }

}
