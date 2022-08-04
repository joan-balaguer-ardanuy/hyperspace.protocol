package hyperspace.recurrent;

public class TestCollection {

	public TestCollection() {
	}

	public static void main(String[] args) {
		IntegerCollection intCol = new IntegerCollection(0);
		
		for(Integer i = 1; i < Integer.MAX_VALUE - 8; i++) {
			intCol.add(i);
			System.out.println(intCol.getParent().getElement());
		}
	}

}
