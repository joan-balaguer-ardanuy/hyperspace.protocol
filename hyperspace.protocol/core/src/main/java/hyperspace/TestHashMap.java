package hyperspace;

import java.util.HashMap;

public class TestHashMap {

	public TestHashMap() {
	}

	public static void main(String[] args) {
		HashMap<Integer, String> map = new HashMap<>();
		
		for(int i = 1; i < Integer.MAX_VALUE; i++) {
			String value = Integer.toString(i);
			Integer key = Integer.valueOf(i);
			map.put(key, value);
			System.out.println(map.size());
		}
	}
}
