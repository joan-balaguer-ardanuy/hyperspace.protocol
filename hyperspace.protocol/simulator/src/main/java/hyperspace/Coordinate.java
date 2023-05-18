package hyperspace;

import hyperspace.genesis.Chromosome;
import hyperspace.genesis.Diploid;
import hyperspace.genesis.Genomap;
import hyperspace.genesis.Haploid;
import hyperspace.genesis.Hyperchain;
import hyperspace.genesis.Hypercube;
import hyperspace.recurrent.Enumerator;

public class Coordinate {

	Parity parity;
	Listener listener;

	float x = 0;
	float y = 0;
	float z = 0;

	static final float PI = (float) Math.PI;
	float angleX;
	float angleY;
	float angleZ;
	
	float total;

	public Parity getParity() {
		return parity;
	}

	public void setParity(Parity parity) {
		this.parity = parity;
	}

	public Listener getListener() {
		return listener;
	}

	public void setListener(Listener listener) {
		this.listener = listener;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}

	public float getAngleX() {
		return angleX;
	}

	public void setAngleX(float angleX) {
		this.angleX = angleX;
	}

	public float getAngleY() {
		return angleY;
	}

	public void setAngleY(float angleY) {
		this.angleY = angleY;
	}

	public float getAngleZ() {
		return angleZ;
	}

	public void setAngleZ(float angleZ) {
		this.angleZ = angleZ;
	}

	public float getTotal() {
		return total;
	}
	
	public void setTotal(float total) {
		this.total = total;
	}
	
	public Coordinate(Hypercube entry, int dilatation) {
		listener = entry;
		parity = entry.getParity();
		
		Enumerator<Entry<Character, Integer>> en = entry.enumerator();
		while(en.hasMoreElements()) {
			Entry<Character, Integer> hypercube = en.nextElement();
			switch (getDimension(hypercube.getKey())) {
			case XX:
				x += hypercube.getValue() / dilatation;
				break;
			case XY:
				y += hypercube.getValue() / dilatation;
				break;
			default:
				z += hypercube.getValue() / dilatation;
				break;
			}
		}
		total = x + y + z;
		angleX = PI / (x);
		angleY = PI / (y);
		angleZ = PI / (z);
	}
	public Coordinate(Hyperchain entry, int dilatation) {
		listener = entry;
		parity = entry.getParity();
		
		Enumerator<Entry<Integer, Character>> en = entry.enumerator();
		while(en.hasMoreElements()) {
			Entry<Integer, Character> hyperchain = en.nextElement();
			switch (getDimension(hyperchain.getValue())) {
			case XX:
				x += hyperchain.getKey() / dilatation;
				break;
			case XY:
				y += hyperchain.getKey() / dilatation;
				break;
			default:
				z += hyperchain.getKey() / dilatation;
				break;
			}
		}
		total = x + y + z;
		angleX = PI / (x);
		angleY = PI / (y);
		angleZ = PI / (z);
	}
	public Coordinate(Genomap entry, int dilatation) {
		listener = entry;
		parity = entry.getParity();
		
		Enumerator<Entry<Hypercube, Hyperchain>> en = entry.enumerator();
		while(en.hasMoreElements()) {
			Entry<Hypercube, Hyperchain> genomap = en.nextElement();
			Enumerator<Entry<Character,Integer>> en2 = genomap.getKey().enumerator();
			while (en2.hasMoreElements()) {
				Entry<Character, Integer> hypercube = en2.nextElement();
				switch (getDimension(hypercube.getKey())) {
				case XX:
					x += hypercube.getValue() / dilatation;
					break;
				case XY:
					y += hypercube.getValue() / dilatation;
					break;
				default:
					z += hypercube.getValue() / dilatation;
					break;
				}
			}
		}
		total = x + y + z;
		angleX = PI / (x);
		angleY = PI / (y);
		angleZ = PI / (z);
	}
	public Coordinate(Haploid entry, int dilatation) {
		listener = entry;
		parity = entry.getParity();
		
		Enumerator<Entry<Hyperchain, Hypercube>> en = entry.enumerator();
		while(en.hasMoreElements()) {
			Entry<Hyperchain, Hypercube> genomap = en.nextElement();
			Enumerator<Entry<Integer,Character>> en2 = genomap.getKey().enumerator();
			while (en2.hasMoreElements()) {
				Entry<Integer, Character> hypercube = en2.nextElement();
				switch (getDimension(hypercube.getValue())) {
				case XX:
					x += hypercube.getKey() / dilatation;
					break;
				case XY:
					y += hypercube.getKey() / dilatation;
					break;
				default:
					z += hypercube.getKey() / dilatation;
					break;
				}
			}
		}
		total = x + y + z;
		angleX = PI / (x);
		angleY = PI / (y);
		angleZ = PI / (z);
	}
	public Coordinate(Chromosome entry, int dilatation) {
		listener = entry;
		parity = entry.getParity();
		Enumerator<Entry<Genomap, Haploid>> en = entry.enumerator();
		while (en.hasMoreElements()) {
			Entry<Genomap, Haploid> chromosome = en.nextElement();
			Enumerator<Entry<Hypercube, Hyperchain>> en2 = chromosome.getKey().enumerator();
			while (en2.hasMoreElements()) {
				Entry<Hypercube, Hyperchain> genomap = en2.nextElement();
				Enumerator<Entry<Character, Integer>> en3 = genomap.getKey().enumerator();
				while (en3.hasMoreElements()) {
					Entry<Character, Integer> hypercube = en3.nextElement();
					switch (getDimension(hypercube.getKey())) {
					case XX:
						x += hypercube.getValue() / dilatation;
						break;
					case XY:
						y += hypercube.getValue() / dilatation;
						break;
					default:
						z += hypercube.getValue() / dilatation;
						break;
					}
				}
			}
		}
		total = x + y + z;
		angleX = PI / (x);
		angleY = PI / (y);
		angleZ = PI / (z);
	}
	public Coordinate(Diploid entry, int dilatation) {
		listener = entry;
		parity = entry.getParity();
		Enumerator<Entry<Haploid, Genomap>> en = entry.enumerator();
		while (en.hasMoreElements()) {
			Entry<Haploid, Genomap> chromosome = en.nextElement();
			Enumerator<Entry<Hyperchain, Hypercube>> en2 = chromosome.getKey().enumerator();
			while (en2.hasMoreElements()) {
				Entry<Hyperchain, Hypercube> genomap = en2.nextElement();
				Enumerator<Entry<Integer, Character>> en3 = genomap.getKey().enumerator();
				while (en3.hasMoreElements()) {
					Entry<Integer, Character> hypercube = en3.nextElement();
					switch (getDimension(hypercube.getValue())) {
					case XX:
						x += hypercube.getKey() / dilatation;
						break;
					case XY:
						y += hypercube.getKey() / dilatation;
						break;
					default:
						z += hypercube.getKey() / dilatation;
						break;
					}
				}
			}
		}
		total = x + y + z;
		angleX = PI / (x);
		angleY = PI / (y);
		angleZ = PI / (z);
	}
	public static Parity getDimension(char character) {
		switch (character) {
		case 'A':
		case 'B':
		case 'C':
		case 'D':
		case 'E':
		case 'F':
		case 'G':
		case 'H':
		case 'I':
			return Parity.XX;
		case 'J':
		case 'K':
		case 'L':
		case 'M':
		case 'N':
		case 'O':
		case 'P':
		case 'Q':
		case 'R':
			return Parity.YY;
		case 'S':
		case 'T':
		case 'U':
		case 'V':
		case 'W':
		case 'X':
		case 'Y':
		case 'Z':
		case 'Ç':
		default:
			return Parity.XY;
		}
	}
}
