package hyperspace.recurrent;

public class Hyperstring2 extends Hypermap<Integer, String> {

	private static final long serialVersionUID = -4227286858884571201L;
	
	public Hyperstring2() {
		super();
	}
	public Hyperstring2(Class<Hyperstring2> type, String name, Integer key, String value) {
		super(type, name, key, value);
	}
	public Hyperstring2(Class<Hyperstring2> type, String name) {
		super(type, name);
	}
	public Hyperstring2(Hyperstring2 parent, Integer key, String value) {
		super(parent, key, value);
	}
	public Hyperstring2(Hyperstring2 root, String name, Integer key, String value) {
		super(root, name, key, value);
	}
	public Hyperstring2(Hyperstring2 root, String name) {
		super(root, name);
	}
	public Hyperstring2(Hyperstring2 parent) {
		super(parent);
	}
}
