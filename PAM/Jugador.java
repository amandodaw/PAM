import java.util.Scanner;
import java.util.ArrayList;

public class Jugador{
	private String nombre;
	private int oro, numPociones,numEscudos,numMachetes;
	private ArrayList<Mercenario> pandilla = new ArrayList<Mercenario>();
	private Mercenario rival;
	private int recompensa, poderPocion, precioPocion;
	Scanner sc = new Scanner(System.in);
	private String[] requiem = new String[]{" queria vivir para servir a su amo... Pero ha muerto. Era un poco rarito si me preguntas",
	" ha mordido el polvo. Literalmente. Que ha muerto @#$%&!"," HA MUERTO! MUERTISIMO ESTA."};	
	
	public Jugador(String nombre){
		this.nombre=nombre;
		oro=500;
		recompensa=100;
		poderPocion=50;
		precioPocion=50;
		numPociones=0;
	}
	
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

	public int getRecompensa() {
		return recompensa;
	}

	public void setRecompensa(int recompensa) {
		this.recompensa = recompensa;
	}

	public int getPoderPocion() {
		return poderPocion;
	}

	public void setPoderPocion(int poderPocion) {
		this.poderPocion = poderPocion;
	}

	public int getPrecioPocion() {
		return precioPocion;
	}

	public void setPrecioPocion(int precioPocion) {
		this.precioPocion = precioPocion;
	}

	public Mercenario getRival() {
		return rival;
	}

	public void setRival(Mercenario rival) {
		this.rival = rival;
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

	public void usarPocion() {
		elegirMercenario().setHP(-poderPocion);
	}
	
	public void darEscudo(){
		elegirMercenario().setEscudo(true);
	}
	
	public void darMachete(){
		elegirMercenario().setMachete(true);
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

	public ArrayList<Mercenario> getPandilla() {
		return pandilla;
	}

	public void setPandilla(ArrayList<Mercenario> pandilla) {
		this.pandilla = pandilla;
	}
	
}
