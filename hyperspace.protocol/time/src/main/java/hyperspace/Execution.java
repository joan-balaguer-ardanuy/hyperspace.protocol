package hyperspace;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import hyperspace.genesis.Aaron;
import hyperspace.genesis.AlphaCentauri;
import hyperspace.genesis.Andromeda;
import hyperspace.genesis.Antimatter;
import hyperspace.genesis.BigBang;
import hyperspace.genesis.BigBong;
import hyperspace.genesis.Chromosome;
import hyperspace.genesis.Diploid;
import hyperspace.genesis.Earth;
import hyperspace.genesis.Genomap;
import hyperspace.genesis.Gliese;
import hyperspace.genesis.Haploid;
import hyperspace.genesis.Hyperchain;
import hyperspace.genesis.Hypercube;
import hyperspace.genesis.Interstellar;
import hyperspace.genesis.Matter;
import hyperspace.genesis.MilkyWay;
import hyperspace.genesis.Operon;
import hyperspace.genesis.Polyploid;
import hyperspace.genesis.Ribosome;
import hyperspace.genesis.Sun;
import hyperspace.genesis.Supercluster;
import hyperspace.genesis.Tetraploid;
import hyperspace.genesis.TimeMaster;

public class Execution {

	public static void main(String[] args) {
		int dilatation = 1;
		Hypercube hypercube = new Hypercube(Hyperchain.class, Parity.XX);
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

		Genomap genomap = new Genomap(Haploid.class, Parity.YY);
		genomap.put(hypercube, (Hyperchain) hypercube.getChild());
		
		Chromosome chromosome = new Chromosome(Diploid.class, Parity.YY);
		chromosome.put(genomap, (Haploid) genomap.getChild());

		Ribosome ribosome = new Ribosome(Tetraploid.class, Parity.YY);
		ribosome.put(chromosome, (Diploid) chromosome.getChild());
		
		Operon operon = new Operon(Polyploid.class, Parity.YY);
		operon.put(ribosome, (Tetraploid) ribosome.getChild());
		
		Earth earth = new Earth(Gliese.class, Parity.YY);
		earth.put(operon, (Polyploid) operon.getChild());
		
		Sun sun = new Sun(AlphaCentauri.class, Parity.YY);
		sun.put(earth, (Gliese) earth.getChild());
		
		MilkyWay milkyWay = new MilkyWay(Andromeda.class, Parity.YY);
		milkyWay.put(sun, (AlphaCentauri) sun.getChild());
		
		Supercluster supercluster = new Supercluster(Interstellar.class, Parity.YY);
		supercluster.put(milkyWay, (Andromeda) milkyWay.getChild());
		
		Matter matter = new Matter(Antimatter.class, Parity.YY);
		matter.put(supercluster, (Interstellar) supercluster.getChild());
		
		BigBang bigBang = new BigBang(BigBong.class, Parity.YY);
		bigBang.put(matter, (Antimatter) matter.getChild());
		
		Aaron aaron = new Aaron(TimeMaster.class, Parity.YY);
		aaron.put(bigBang, (BigBong) bigBang.getChild());
		
		Listener timeListener = new Listener() {
			
			private static final long serialVersionUID = 9215158565934363875L;
			@Override
			public void execute(Runnable command) {
				newThread(command).start();
			}
			@Override
			public Thread newThread(Runnable r) {
				return new Thread(r);
			}
			@Override
			public void run() {
				
			}
			@Override
			public void setParity(Parity parity) {
				
			}
			@Override
			public void setCommand(String command) {
				
			}
			@Override
			public Parity getParity() {
				return Parity.YY;
			}
			
			@Override
			public String getName() {
				return null;
			}
			@Override
			public String getCommand() {
				return null;
			}
			@Override
			public void removeEventListener(Listener listener) {
				
			}
			@Override
			public void event(EventArgs e) {
				Listener listener = (Listener) e.getSource();
				switch (e.getCommand()) {
				case Command.INSTANCE:
					if(listener instanceof BigBang) {
						System.out.println("ENTRA");
						BigBang entry = (BigBang) listener;
						entry.addEventListener(this);
						entry.getChild().addEventListener(this);
						execute(entry);
					}
					break;
				case Command.LISTEN:
					if(listener instanceof Hypercube || listener instanceof Hyperchain) {
						
					} else {
						PrintStream output;
						try {
							output = new PrintStream(
								     new FileOutputStream("output.txt", true));
							output.println(listener.getClass().getName()) ;
							output.close();
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}
					}
					break;
				default:
					break;
				}
			}
			@Override
			public void addEventListener(Listener listener) {
				
			}
		};
		
		aaron.addEventListener(timeListener);
		aaron.getChild().addEventListener(timeListener);
//		System.setErr(new PrintStream(new OutputStream() {
//			
//			@Override
//			public void write(int b) throws IOException {
//				// TODO Auto-generated method stub
//				
//			}
//		}));
		aaron.execute(aaron);
	}
}
