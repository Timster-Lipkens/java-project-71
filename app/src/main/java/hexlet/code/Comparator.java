package hexlet.code;

import java.util.Map;
import java.util.TreeMap;
import java.util.LinkedList;

import static hexlet.code.Status.UNCHANGED; //ужас сколько малоосмысленного импорта
import static hexlet.code.Status.DELETED;
import static hexlet.code.Status.ADDED;
import static hexlet.code.Status.CHANGED;

public class Comparator {

    public static LinkedList<Status> analyzeData(Map<String, Object> parserMap1,
                                                 Map<String, Object> parserMap2) {
        TreeMap<String, Object> sortedMap1 = new TreeMap<>(parserMap1); //авто-сортировка
        TreeMap<String, Object> sortedMap2 = new TreeMap<>(parserMap2); //от дерева.
        var resultMap = new LinkedList<Status>(); //может переназвать потом..

        while (!sortedMap1.isEmpty() && !sortedMap2.isEmpty()) { //должен пересчитываться. //просят Status
            var entry1 = sortedMap1.firstEntry();
            var entry2 = sortedMap2.firstEntry();
            var key1 = entry1.getKey();
            var key2 = entry2.getKey();
            var value1 = entry1.getValue() == null ? "null" : entry1.getValue(); //вечная пустота, null (не toString())
            var value2 = entry2.getValue() == null ? "null" : entry2.getValue(); //иначе equals() не работает

            if (key1.equals(key2)) {  //имеется ли такой же ключ?
                if (value1.equals(value2)) {
                    resultMap.add(new Status(UNCHANGED, key1, value1, value1)); //решил дублировать значения
                } else {
                    resultMap.add(new Status(CHANGED, key1, value1, value2)); //ключи равны DELETED ADDED
                }
                sortedMap1.remove(key1); //удаление первого в этом случае (оптимальный ли метод?)
                sortedMap2.remove(key2);
            } else {
                if (key1.compareTo(key2) < 0) { //1 до 2 (то есть ключ выше).
                    resultMap.add(new Status(DELETED, key1, value1, null));
                    sortedMap1.remove(key1);
                } else {
                    resultMap.add(new Status(ADDED, key2, null, value2));
                    sortedMap2.remove(key2);
                }
            }
        }

        if (!sortedMap1.isEmpty() || !sortedMap2.isEmpty()) { //на случай разного количества элементов в файлах
            while (!sortedMap1.isEmpty()) {
                var entry1 = sortedMap1.pollFirstEntry();
                var value1 = entry1.getValue() == null ? "null" : entry1.getValue();
                resultMap.add(new Status(ADDED, entry1.getKey(), null, value1));
            }
            while (!sortedMap2.isEmpty()) {
                var entry2 = sortedMap2.pollFirstEntry();
                var value2 = entry2.getValue() == null ? "null" : entry2.getValue();
                resultMap.add(new Status(ADDED, entry2.getKey(), null, value2));
            }
        }

        return resultMap;
    }

}
