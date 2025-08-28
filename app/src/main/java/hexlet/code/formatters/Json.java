package hexlet.code.formatters;

import hexlet.code.Status;
import java.util.LinkedList;

import com.fasterxml.jackson.databind.ObjectMapper;
//import java.util.LinkedHashMap;

//import static hexlet.code.Status.DELETED;

public class Json {

    public static String format(LinkedList<Status> resultMap) throws Exception { //смысл если не json?
        /*
        LinkedHashMap<String, Object> resultMap0 = new LinkedHashMap<>();
        for (Status status : resultMap) {
            if (!status.getStatusName().equals(DELETED)) {
                resultMap0.put(status.getMapKey(), status.getNewValue());
            }
        }
        return new ObjectMapper().valueToTree(resultMap0).toString();
         */
        return new ObjectMapper().writeValueAsString(resultMap); //нерабочий метод с артефактами Статуса
    } //Статус подломал прошлый лаконичный класс //хотя это выявило скрытые баги

}
