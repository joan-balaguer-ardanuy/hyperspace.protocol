package hyperspace.recurrent;

public class TestHypercube {

	public TestHypercube() {
		
	}

	public static void main(String[] args) {
		
		XMLTest xml = new XMLTest();
		
		Hyperstring cube = new Hyperstring(Hyperinteger.class, xml);
		for(int i = 0; i < Integer.MAX_VALUE; i++) {
			char value = (char) i;
			Integer key = i;
			cube.putValue(key, value);
			System.out.println(cube.getParent().getKey());
		}
	}
}
