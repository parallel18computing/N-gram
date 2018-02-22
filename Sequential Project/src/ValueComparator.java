import java.util.Comparator;
import java.util.concurrent.ConcurrentHashMap;

public class ValueComparator implements Comparator {
    ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<String, Integer>();

    public ValueComparator(ConcurrentHashMap<String, Integer> map){
        this.map.putAll(map);
    }

    @Override
    public int compare(Object o1, Object o2) {
        String s1 = (String) o1;
        String s2 = (String) o2;

        if (map.get(s1) >= map.get(s2)) {
            return -1;
        } else {
            return 1;
        }


    }
}
