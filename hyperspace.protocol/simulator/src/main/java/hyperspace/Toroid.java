package hyperspace;

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
import processing.core.*;

public class Toroid extends PApplet implements Listener {

	private static final long serialVersionUID = -5297826820482250738L;

	Aaron aaron;

	Coordinates coordinates;
	
	float constant = 400;
	float cameraZ = 5000;

	int dilatation = 10;
	float size = 200;

	public void setup() {
		background(0);
		colorMode(RGB, 255);
		noStroke();
		ellipseMode(RADIUS);
		frameRate(25);
		
		Hypercube hypercube = new Hypercube();
		hypercube.put('A', 65*dilatation);
		hypercube.put('B', 66*dilatation);
		hypercube.put('C', 67*dilatation);
		hypercube.put('D', 68*dilatation);
		hypercube.put('E', 69*dilatation);
		hypercube.put('F', 70*dilatation);
		hypercube.put('G', 71*dilatation);
		hypercube.put('H', 72*dilatation);
		hypercube.put('I', 73*dilatation);
		hypercube.put('J', 74*dilatation);
		hypercube.put('K', 75*dilatation);
		hypercube.put('L', 76*dilatation);
		hypercube.put('M', 77*dilatation);
		hypercube.put('N', 78*dilatation);
		hypercube.put('O', 79*dilatation);
		hypercube.put('P', 80*dilatation);
		hypercube.put('Q', 81*dilatation);
		hypercube.put('R', 82*dilatation);
		hypercube.put('S', 83*dilatation);
		hypercube.put('T', 84*dilatation);
		hypercube.put('U', 85*dilatation);
		hypercube.put('V', 86*dilatation);
		hypercube.put('W', 84*dilatation);
		hypercube.put('X', 88*dilatation);
		hypercube.put('Y', 89*dilatation);
		hypercube.put('Z', 90*dilatation);
		hypercube.put('Ç', 91*dilatation);

		Genomap genomap = new Genomap();
		genomap.put(hypercube, (Hyperchain) hypercube.getChild());
		
		Chromosome chromosome = new Chromosome();
		chromosome.put(genomap, (Haploid) genomap.getChild());

		Ribosome ribosome = new Ribosome();
		ribosome.put(chromosome, (Diploid) chromosome.getChild());
		
		Operon operon = new Operon();
		operon.put(ribosome, (Tetraploid) ribosome.getChild());
		
		Earth earth = new Earth();
		earth.put(operon, (Polyploid) operon.getChild());
		
		Sun sun = new Sun();
		sun.put(earth, (Gliese) earth.getChild());
		
		MilkyWay milkyWay = new MilkyWay();
		milkyWay.put(sun, (AlphaCentauri) sun.getChild());
		
		Supercluster supercluster = new Supercluster();
		supercluster.put(milkyWay, (Andromeda) milkyWay.getChild());
		
		Matter matter = new Matter();
		matter.put(supercluster, (Interstellar) supercluster.getChild());
		
		BigBang bigBang = new BigBang();
		bigBang.put(matter, (Antimatter) matter.getChild());
		
		Aaron aaron = new Aaron();
		aaron.put(bigBang, (BigBong) bigBang.getChild());
		
		aaron.addEventListener(this);
		aaron.getChild().addEventListener(this);
		
//		System.setErr(new PrintStream(new OutputStream() {
//			
//			@Override
//			public void write(int b) throws IOException {
//				// TODO Auto-generated method stub
//				
//			}
//		}));
		coordinates = new Coordinates();
		coordinates.add(new Coordinate(hypercube, dilatation));
		aaron.execute(aaron);
	}

	public void draw() {
		synchronized (this) {
			background(0);
			camera(0, 0, -cameraZ, 0, 0, 0, 0, 1, 0);
			lights();
			spotLight(255, 0, 0, width / 2, height / 2, 5000, 0, 0, -1, PI / 4, 2);
			noStroke();

			for (Coordinate coordinate : coordinates) {
				
				switch (coordinate.getParity()) {
				case XX:
					fill((coordinate.getX()) % 255, coordinate.getY() , (coordinate.getZ()));
					break;
				case XY:
					fill((coordinate.getX()), coordinate.getY() % 255, (coordinate.getZ()));
					break;
				default:
					fill((coordinate.getX()) % 255, coordinate.getY() % 255, coordinate.getZ() % 255);
					break;
				}
				rotateX(coordinate.getAngleX());
				coordinate.setAngleX(coordinate.getAngleX() + PI / coordinate.getX());
				rotateY(coordinate.getAngleY());
				coordinate.setAngleY(coordinate.getAngleY() + PI / coordinate.getY());
				rotateZ(coordinate.getAngleZ());
				coordinate.setAngleZ(coordinate.getAngleZ() + PI / coordinate.getZ());
				pushMatrix();
				if(coordinate.getListener() instanceof Hypercube || coordinate.getListener() instanceof Hyperchain) {
					translate(coordinate.getX(), coordinate.getY(), -coordinate.getZ());
					sphere(coordinate.getTotal() % 40 + 30);
				} else if(coordinate.getListener() instanceof Genomap || coordinate.getListener() instanceof Haploid) {
					translate(coordinate.getX() % 250 + 1000, coordinate.getY() % 250 + 1000, -coordinate.getZ() % 250 + 1000);
					sphere(coordinate.getTotal() % 80 + 60);
				} else if(coordinate.getListener() instanceof Chromosome || coordinate.getListener() instanceof Diploid) {
					translate(coordinate.getX() % 250 + 1250, coordinate.getY() % 250 + 1250, -coordinate.getZ() % 250 + 1250);
					box(coordinate.getTotal() % 120 + 70);
				}
				popMatrix();
			}
		}
	}
	
	public void settings() {
//		System.setProperty("jogl.disable.openglcore", "false");
		size(1920, 1080, "processing.opengl.PGraphics3D");
//		size(1366, 768, "processing.opengl.PGraphics3D");
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
	public void setCommand(String ordre) {
		
	}

	@Override
	public void run() {
		
	}

	@Override
	public void execute(Runnable command) {
		newThread(command).start();;
	}

	@Override
	public Thread newThread(Runnable r) {
		return new Thread(r);
	}

	@Override
	public void addEventListener(Listener listener) {
		
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
				BigBang genomapa = (BigBang) listener;
				genomapa.addEventListener(this);
				genomapa.getChild().addEventListener(this);
//				execute(genomapa);
			}
			break;
		case Command.LISTEN:
			synchronized (this) {
				if(listener instanceof Hypercube) {
					Coordinate coordinate = new Coordinate((Hypercube) listener, dilatation);
					coordinates.add(coordinate);
				} else if(listener instanceof Hyperchain) {
					Coordinate coordinate = new Coordinate((Hyperchain) listener, dilatation);;
					coordinates.add(coordinate);
				} else if(listener instanceof Genomap) {
					Coordinate coordinate = new Coordinate((Genomap) listener, dilatation);;
					coordinates.add(coordinate);
				} else if(listener instanceof Haploid) {
					Coordinate coordinate = new Coordinate((Haploid) listener, dilatation);;
					coordinates.add(coordinate);
				} else if(listener instanceof Chromosome) {
					Coordinate coordinate = new Coordinate((Chromosome) listener, dilatation);;
					coordinates.add(coordinate);
				} else if(listener instanceof Diploid) {
					Coordinate coordinate = new Coordinate((Diploid) listener, dilatation);;
					coordinates.add(coordinate);
				}
			}
			break;
		default:
			break;
		}
	}

	@Override
	public Parity getParity() {
		return null;
	}

	@Override
	public void setParity(Parity parity) {
		
	}
}