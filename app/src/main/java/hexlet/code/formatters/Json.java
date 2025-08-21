package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.LinkedHashMap;

public class Json {
    public static ObjectNode format(LinkedHashMap<String, Object> resultMap) {
        //ObjectNode jsonNode = new ObjectMapper().valueToTree(resultMap); //вне инструкции
        return new ObjectMapper().valueToTree(resultMap); //нормально ли что всё в одну строчку?
    }
}
