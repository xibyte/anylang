package mlang.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Utils {

	public static <K, V> void addToListInMap(Map<K, List<V>> map, K key, V value) {
		List<V> list = map.get(key);
		if (list == null) {
			list = new ArrayList<V>(2);
			map.put(key, list);
		}
		list.add(value);
	}
	
	public static <K, V> void removeFromListInMap(Map<K, List<V>> map, K key, V value) {
		List<V> list = map.get(key);
		if (list != null) {
			list.remove(value);
			if (list.isEmpty()) {
				map.remove(key);
			}
		}
	}
}
