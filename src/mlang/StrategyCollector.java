package mlang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.text.IAutoEditStrategy;

public class StrategyCollector {

	private final Map<String, List<IAutoEditStrategy>> startegies = new HashMap<String, List<IAutoEditStrategy>>();
	
	public void add(String contentType, IAutoEditStrategy strategy) {
		addToListInMap(startegies, contentType, strategy);
	}
	
	public Map<String, List<IAutoEditStrategy>> map() {
		return startegies;
	}
	
	public static <K, V> void addToListInMap(Map<K, List<V>> map, K key, V value) {
		List<V> list = map.get(key);
		if (list == null) {
			list = new ArrayList<V>();
			map.put(key, list);
		}
		list.add(value);
	}

	
}
