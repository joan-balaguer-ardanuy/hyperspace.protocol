package hyperspace;

public class TestHypercube {

	public TestHypercube() {
		
	}

	public static void main(String[] args) {
		Hyperstring cube = new Hyperstring(Hyperinteger.class, "hola món", 0, "0");
		
		for(int i = 1; i < Integer.MAX_VALUE; i++) {
			String value = Integer.toString(i);
			Integer key = Integer.valueOf(value);
			cube.putValue(key, value);
			System.out.println(cube.getParent().getValue());
		}
		System.out.println(cube.size());
	}
}
