package hyperspace.recurrent;

public class TestCollection {

	public TestCollection() {
	}

	public static void main(String[] args) {
		IntegerCollection intCol = new IntegerCollection(null);
		
		for(Integer i = 0; i < Integer.MAX_VALUE - 8; i++) {
			intCol.add(i);
			System.out.println(intCol.getParent().getElement());
		}
	}

}
