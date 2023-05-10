package hyperspace;

import java.util.Random;

public enum Parity {
	XY,
	XX,
	YY;
	
	public Parity oposed() {
		if(this == Parity.XX)
			return XY;
		else if(this == Parity.XY)
			return XX;
		else return YY;
	}
	/**
	 * L'aleatorietat.
	 */
	private static Random random = new Random();
	
	/**
	 * L'aleatorietat
	 * @return l'aleatorietat.
	 */
	public static Parity random() {
		switch (random.nextInt(3)) {
		case 0:
			return XY;
		case 1:
			return XX;
		default:
			return YY;
		}
	}
}
