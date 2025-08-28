package hexlet.code.formatters;

import hexlet.code.Status;
import java.util.LinkedList;

import com.fasterxml.jackson.databind.ObjectMapper;
//import java.util.LinkedHashMap;

//import static hexlet.code.Status.DELETED;

public class Json {

    public static String format(LinkedList<Status> statusList) throws Exception { //смысл если не json?
        /*
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        for (Status status : resultMap) {
            if (!status.getStatusName().equals(DELETED)) {
                resultMap.put(status.getMapKey(), status.getNewValue());
            }
        }
        return new ObjectMapper().valueToTree(resultMap).toString();
         */
        return new ObjectMapper().writeValueAsString(statusList); //рабочий метод для Хекслета, хотя с артефактами?
    } //Статус подломал прошлый лаконичный класс //хотя это выявило скрытые баги

}
