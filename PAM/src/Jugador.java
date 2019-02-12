import java.util.Scanner;
import java.util.ArrayList;

public class Jugador{
	private String nombre;
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getOro() {
		return oro;
	}

	public void setOro(int oro) {
		this.oro = oro;
	}

	public int getNumPociones() {
		return numPociones;
	}

	public void setNumPociones(int numPociones) {
		this.numPociones = numPociones;
	}

	public int getNumEscudos() {
		return numEscudos;
	}

	public void setNumEscudos(int numEscudos) {
		this.numEscudos = numEscudos;
	}

	public int getNumMachetes() {
		return numMachetes;
	}

	public void setNumMachetes(int numMachetes) {
		this.numMachetes = numMachetes;
	}

	public static int getRecompensa() {
		return recompensa;
	}

	public static void setRecompensa(int recompensa) {
		Jugador.recompensa = recompensa;
	}

	public static int getPoderPocion() {
		return poderPocion;
	}

	public static void setPoderPocion(int poderPocion) {
		Jugador.poderPocion = poderPocion;
	}

	public static int getPrecioPocion() {
		return precioPocion;
	}

	public static void setPrecioPocion(int precioPocion) {
		Jugador.precioPocion = precioPocion;
	}

	private int oro, numPociones,numEscudos,numMachetes;
	private ArrayList<Mercenario> pandilla = new ArrayList<Mercenario>();
	private static Menu menu;
	private Mercenario rival;
	private static int recompensa, poderPocion, precioPocion;
	Scanner sc = new Scanner(System.in);
	private String[] requiem = new String[]{" queria vivir para servir a su amo... Pero ha muerto. Era un poco rarito si me preguntas",
	" ha mordido el polvo. Literalmente. Que ha muerto @#$%&!"," HA MUERTO! MUERTISIMO ESTA."};	
	public Jugador(String nombre){
		this.nombre=nombre;
		oro=500;
		recompensa=100;
		poderPocion=50;
		precioPocion=50;
		menu=new Menu();
	}

	public void mostrarMercenarios(){
		System.out.println("\nPandilla de "+nombre);
		for(Mercenario merc:getPandilla()){
			merc.mostrarStats();
		}
		System.out.println("---------------");
		menuPrincipal();
	}
	public void menuReclutar(){
		if(oro>=300){
			Mercenario[] listaReclutas = new Mercenario[5];
			for(int i = 0; i<5;i++){
				listaReclutas[i]=new Mercenario();
				listaReclutas[i].generarStats();
			}
			System.out.println("Nombre |");
			for(int i = 0; i<6;i++){
				if(i==5){
					System.out.println("6. Cancelar compra");
				}else System.out.println((i+1)+" "+listaReclutas[i].getNombre()+" "
				+listaReclutas[i].getAtaque()+"-ATQ "+listaReclutas[i].getDefensa()+"-DEF "+listaReclutas[i].getHP()+"-HP");			
			}
			System.out.println("---------------");
			System.out.println("Introduce el numero correspondiente al mercenario que quieres comprar. Todos cuestan 300 de oro");
			System.out.println("---------------");
			int opcion = sc.nextInt()-1;
			if(opcion!=5){
				getPandilla().add(listaReclutas[opcion]);
				oro=oro-300;
				System.out.println("Te queda "+oro+"g");
			}
		}else{
			System.out.println("Que quieres hacer con solo "+oro+"g? vuelve cuando tengas mas pasta, pobreton");
		}
		menuPrincipal();
	}
	public void menuRing(){
		Mercenario[] listaRing = new Mercenario[5];
		for(int i = 0; i<5;i++){
			listaRing[i]=new Mercenario();
			listaRing[i].generarStats();
		}
		System.out.println("Nombre | Ataque | Defensa | HP");
		for(int i = 0; i<6;i++){
			if(i==5){
				System.out.println("6. Cancelar pelea");
			}else System.out.println((i+1)+" "+listaRing[i].getNombre()+" "
			+listaRing[i].getAtaque()+" "+listaRing[i].getDefensa()+" "+listaRing[i].getHP());			
		}
		System.out.println("Introduce el numero correspondiente al mercenario rival que quieres atacar");
		int opcion = sc.nextInt()-1;
		if(opcion!=5){
			rival=new Mercenario();
			rival=listaRing[opcion];
		}		
		pelear(elegirMercenario(),rival);
		menuPrincipal();
	}
	public void menuTienda(){
		System.out.println("\n1. Comprar pocion(Cura 50 HP) - 50g\n2. Comprar escudo(Defensa+25)\n3. Comprar machete(Ataque+25)");
		int opcion = sc.nextInt();
		switch(opcion){
		case 1: comprarPocion();break;
		}
	}
	public Mercenario pelear(Mercenario a, Mercenario b){
		a.mostrarStats();
		System.out.println("-----VS-----");
		b.mostrarStats();
		System.out.println();
		int iniciativa=(int) Math.floor(Math.random() * 2);
		if(iniciativa==1){
			System.out.println(a.getNombre()+" tiene la iniciativa! Machacaos!");
		}else System.out.println(b.getNombre()+" tiene la iniciativa! Machacaos!");
		while(a.getHP()>0&&b.getHP()>0){
			if(iniciativa==1){
				b.setHP(a.atacar(b.defender()));
				iniciativa=0;
			}else{
				a.setHP(b.atacar(a.defender()));
				iniciativa=1;
			} 
			System.out.println(a.getNombre()+" HP:"+a.getHP()+" | "+b.getNombre()+" HP:"+b.getHP()+"\n---------------");
			try{
				Thread.sleep(1000);
			}catch(InterruptedException e){}
		}
		if(a.getHP()<=0){
			System.out.println(b.getNombre()+" ha reventado a "+a.getNombre());
			cementerio();
			return b;
		}else{
			System.out.println(a.getNombre()+" ha reventado a "+b.getNombre());
			oro=oro+recompensa;
			System.out.println("Has ganado "+recompensa+"! Total: "+oro);
			return a;
		}	
	}		
	public Mercenario elegirMercenario(){
		System.out.println("Elige a un campeon");
		for(int i=0;i< getPandilla().size();i++){
			System.out.println((i+1)+" "+getPandilla().get(i).getNombre());
			getPandilla().get(i).mostrarStats();
		}
		int opcion=sc.nextInt();
		return getPandilla().get(opcion-1);
	}
	public void inventario(){
		System.out.println("Dispones de los siguientes objetos:\n1.- "+numPociones+" pociones.\n2.- "+numEscudos+" escudos.\n3.- "+numMachetes+" machetes.\n4.- Salir\nQuieres usar alguno?");
		int opcion = sc.nextInt();
		switch(opcion){
		case 1: usarPocion();break;
		}
	}
	public void usarPocion() {
		elegirMercenario().setHP(-poderPocion);
	}
	public void cementerio(){
		for(int i=0;i<getPandilla().size();i++){
			if(getPandilla().get(i).getHP()<=0){
				System.out.println(getPandilla().get(i).getNombre()+requiem[(int)(Math.random() * 3 + 0)]);
				getPandilla().remove(i);
			}
		}
		if(getPandilla()==null||getPandilla().size()==0){
			gameOver();
		}
	}
	public void gameOver(){
		System.out.println("\nHa muerto todo tu equipo y no tienes pasta. Adios! :D\n");
		System.out.println("   _____                         ____                 ");
		System.out.println("  / ____|                       / __ \\                ");
		System.out.println(" | |  __  __ _ _ __ ___   ___  | |  | |_   _____ _ __ ");
		System.out.println(" | | |_ |/ _` | '_ ` _ \\ / _ \\ | |  | \\ \\ / / _ \\ '__|");
		System.out.println(" | |__| | (_| | | | | | |  __/ | |__| |\\ V /  __/ |   ");
		System.out.println("  \\_____|\\__,_|_| |_| |_|\\___|  \\____/  \\_/ \\___|_|   ");
		sc.close();
	}
	public void comprarPocion(){
		System.out.println("Las pociones restauran "+poderPocion+"\nCuantas pociones quieres comprar? \nPrecio: 50g");
		int num=sc.nextInt();
		numPociones=numPociones+num;
		oro=oro-num*precioPocion;
		System.out.println("Te queda: "+oro+"g");
		
	}

	public ArrayList<Mercenario> getPandilla() {
		return pandilla;
	}

	public void setPandilla(ArrayList<Mercenario> pandilla) {
		this.pandilla = pandilla;
	}
	
}
