package hexlet.code;

import java.util.Map;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.Objects;

import static hexlet.code.Status.UNCHANGED; //ужас сколько малоосмысленного импорта
import static hexlet.code.Status.DELETED;
import static hexlet.code.Status.ADDED;
import static hexlet.code.Status.CHANGED;

public class Comparator {

    public static LinkedList<Status> analyzeData(Map<String, Object> parserMap1, Map<String, Object> parserMap2) {
        TreeSet<String> treeSet = new TreeSet<>(); //авто-сортировка дерева и оптимизация циклов
        treeSet.addAll(parserMap1.keySet());
        treeSet.addAll(parserMap2.keySet());
        var statusList = new LinkedList<Status>();

        for (String key : treeSet) {
            if (!parserMap2.containsKey(key)) { // нет во втором, значит точно имеется в первом:
                statusList.add(new Status(DELETED, key, parserMap1.get(key), null)); // и удалённое
            } else if (!parserMap1.containsKey(key)) { // обратная проверка, значит точно во втором
                statusList.add(new Status(ADDED, key, null, parserMap2.get(key))); // и добавленное
            } else if (Objects.equals(parserMap1.get(key), parserMap2.get(key))) { // иначе в обоих местах
                statusList.add(new Status(UNCHANGED, key, parserMap1.get(key), parserMap1.get(key))); // если равны
            } else {
                statusList.add(new Status(CHANGED, key, parserMap1.get(key), parserMap2.get(key))); //иначе
            }
        }

        return statusList; //sortedMap1.get(key).equals(sortedMap2.get(key)) - не устойчиво к null
    }

}
