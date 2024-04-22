package hyperspace;

import hyperspace.genesis.*;

public class Execution {

	public static void main(String[] args) {
		int dilatation = 10;
		
		Hypercube hypercube = new Hypercube();
		hypercube.put('A', 1*dilatation);
		hypercube.put('B', 2*dilatation);
		hypercube.put('C', 3*dilatation);
		hypercube.put('D', 4*dilatation);
		hypercube.put('E', 5*dilatation);
		hypercube.put('F', 6*dilatation);
		hypercube.put('G', 7*dilatation);
		hypercube.put('H', 8*dilatation);
		hypercube.put('I', 9*dilatation);
		hypercube.put('J', 10*dilatation);
		hypercube.put('K', 11*dilatation);
		hypercube.put('L', 12*dilatation);
		hypercube.put('M', 13*dilatation);
		hypercube.put('N', 14*dilatation);
		hypercube.put('O', 15*dilatation);
		hypercube.put('P', 16*dilatation);
		hypercube.put('Q', 17*dilatation);
		hypercube.put('R', 18*dilatation);
		hypercube.put('S', 19*dilatation);
		hypercube.put('T', 20*dilatation);
		hypercube.put('U', 21*dilatation);
		hypercube.put('V', 22*dilatation);
		hypercube.put('W', 23*dilatation);
		hypercube.put('X', 24*dilatation);
		hypercube.put('Y', 25*dilatation);
		hypercube.put('Z', 26*dilatation);
		hypercube.put('Ç', 27*dilatation);
		
		Genomap genomap = new Genomap();
		genomap.put(hypercube, (Hyperchain) hypercube.getChild());
		
		Chromosome chromosome = new Chromosome();
		chromosome.put(genomap, (Haploid) genomap.getChild());

//		Ribosome ribosome = new Ribosome();
//		ribosome.put(chromosome, (Diploid) chromosome.getChild());
//		
//		Operon operon = new Operon();
//		operon.put(ribosome, (Tetraploid) ribosome.getChild());
//		
//		Earth earth = new Earth();
//		earth.put(operon, (Polyploid) operon.getChild());
//		
//		Sun sun = new Sun();
//		sun.put(earth, (Gliese) earth.getChild());
//		
//		MilkyWay milkyWay = new MilkyWay();
//		milkyWay.put(sun, (AlphaCentauri) sun.getChild());
//		
//		Supercluster supercluster = new Supercluster();
//		supercluster.put(milkyWay, (Andromeda) milkyWay.getChild());
//		
//		Matter matter = new Matter();
//		matter.put(supercluster, (Interstellar) supercluster.getChild());
//		
//		BigBang bigBang = new BigBang();
//		bigBang.put(matter, (Antimatter) matter.getChild());
//		
//		Aaron aaron = new Aaron();
//		aaron.put(bigBang, (BigBong) bigBang.getChild());
//		
//		aaron.execute(aaron);
		
		chromosome.execute(chromosome);
	}
}
