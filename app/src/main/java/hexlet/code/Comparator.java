package hexlet.code;

import java.util.LinkedHashMap;
import java.util.TreeMap;

public class Comparator {

    public static void analyzeData(TreeMap<String, Object> sortedMap1, TreeMap<String, Object> sortedMap2,
                                   LinkedHashMap<String, Object> resultMap) { //обрабатывает без возвращения
        while (!sortedMap1.isEmpty() && !sortedMap2.isEmpty()) { //должен пересчитываться.
            var entry1 = sortedMap1.firstEntry();
            var entry2 = sortedMap2.firstEntry();
            var value1 = entry1.getValue() == null ? "null" : entry1.getValue(); //пустоты в строки
            var value2 = entry2.getValue() == null ? "null" : entry2.getValue(); //.toString()? equals()

            if ((entry1.getKey()).equals(entry2.getKey())) {  //имеется ли такой же ключ?
                if (value1.equals(value2)) {
                    resultMap.put("  " + entry1.getKey(), value1);
                } else {
                    resultMap.put("- " + entry1.getKey(), value1);
                    resultMap.put("+ " + entry2.getKey(), value2);
                }
                sortedMap1.remove(entry1.getKey()); //удаление первого в этом случае (оптимальный ли метод?)
                sortedMap2.remove(entry2.getKey());
            } else {
                if ((entry1.getKey()).compareTo(entry2.getKey()) < 0) { //1 до 2 (то есть ключ выше).
                    resultMap.put("- " + entry1.getKey(), value1);
                    sortedMap1.remove(entry1.getKey());
                } else {
                    resultMap.put("+ " + entry2.getKey(), value2);
                    sortedMap2.remove(entry2.getKey());
                }
            }
        }

        if (!sortedMap1.isEmpty() || !sortedMap2.isEmpty()) { //на случай разного количества элементов в файлах
            while (!sortedMap1.isEmpty()) {
                var entry1 = sortedMap1.pollFirstEntry();
                String value1 = entry1.getValue() == null ? "null" : entry1.getValue().toString(); //может выделить?
                resultMap.put("+ " + entry1.getKey(), value1);
            }
            while (!sortedMap2.isEmpty()) {
                var entry2 = sortedMap2.pollFirstEntry();
                String value2 = entry2.getValue() == null ? "null" : entry2.getValue().toString();
                resultMap.put("+ " + entry2.getKey(), value2);
            }
        }
    }

}
