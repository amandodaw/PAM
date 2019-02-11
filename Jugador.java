import java.util.Scanner;
import java.util.ArrayList;

public class Jugador{
	private String nombre;
	private int oro;
	private ArrayList<Mercenario> pandilla = new ArrayList<Mercenario>();
	private Mercenario rival;
	private static int recompensa;
	Scanner sc = new Scanner(System.in);
	private String[] requiem = new String[]{" queria vivir para servir a su amo... Pero ha muerto. Era un poco rarito si me preguntas",
	" ha mordido el polvo. Literalmente. Que ha muerto @#$%&!"," HA MUERTO! MUERTISIMO ESTA."};	
	public Jugador(String nombre){
		this.nombre=nombre;
		oro=500;
		recompensa=100;
	}
	public void menuPrincipal(){
		if(pandilla.size()>0||oro>=300){
			System.out.println("Selecciona una opcion.\n\n1. Contratar mercenario\n2. Lista reclutados\n3. Pelear en el ring");
			System.out.println("---------------");
			int opcion=sc.nextInt();
			switch(opcion){
				case 1: menuReclutar(); break;
				case 2: mostrarMercenarios(); break;
				case 3: menuRing(); break;
			}
		}


	}
	public void mostrarMercenarios(){
		for(Mercenario merc:pandilla){
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
			System.out.println("Nombre | Ataque | Defensa | HP");
			for(int i = 0; i<6;i++){
				if(i==5){
					System.out.println("6. Cancelar compra");
				}else System.out.println((i+1)+" "+listaReclutas[i].getNombre()+" "
				+listaReclutas[i].getAtaque()+" "+listaReclutas[i].getDefensa()+" "+listaReclutas[i].getHP());			
			}
			System.out.println("---------------");
			System.out.println("Introduce el numero correspondiente al mercenario que quieres comprar. Todos cuestan 300 de oro");
			System.out.println("---------------");
			int opcion = sc.nextInt()-1;
			if(opcion!=5){
				pandilla.add(listaReclutas[opcion]);
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
				b.setHP(b.defender(a.atacar()));
				iniciativa=0;
			}else{
				a.setHP(a.defender(b.atacar()));
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
		System.out.println("Elige el campeón con el que quieres pelear");
		for(int i=0;i< pandilla.size();i++){
			System.out.println((i+1)+" "+pandilla.get(i).getNombre());
			pandilla.get(i).mostrarStats();
		}
		int opcion=sc.nextInt();
		return pandilla.get(opcion-1);
	}
	public void cementerio(){
		for(int i=0;i<pandilla.size();i++){
			if(pandilla.get(i).getHP()<=0){
				System.out.println(pandilla.get(i).getNombre()+requiem[(int)(Math.random() * 3 + 0)]);
				pandilla.remove(i);
			}
		}
		if(pandilla==null||pandilla.size()==0){
			gameOver();
		}
	}
	public void gameOver(){
		System.out.println("Ha muerto todo tu equipo y no tienes pasta. Adios\n");
		System.out.println("   _____                         ____                 ");
		System.out.println("  / ____|                       / __ \\                ");
		System.out.println(" | |  __  __ _ _ __ ___   ___  | |  | |_   _____ _ __ ");
		System.out.println(" | | |_ |/ _` | '_ ` _ \\ / _ \\ | |  | \\ \\ / / _ \\ '__|");
		System.out.println(" | |__| | (_| | | | | | |  __/ | |__| |\\ V /  __/ |   ");
		System.out.println("  \\_____|\\__,_|_| |_| |_|\\___|  \\____/  \\_/ \\___|_|   ");
		sc.close();
	}
	
}
