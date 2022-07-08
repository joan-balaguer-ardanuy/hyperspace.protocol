package hyperspace.recurrent;

public class TestHypermap {

	public TestHypermap() {
	}

	public static void main(String[] args) {
		Mapping<Integer, String> map = new Hyperstring2(Hyperstring2.class, "hola món", 0, "0");
		
		for(int i = 1; i < Integer.MAX_VALUE; i++) {
			String value = Integer.toString(i);
			Integer key = Integer.valueOf(i);
			map.put(key, value);
			System.out.println(map.getParent().getKey());
		}
	}
}
