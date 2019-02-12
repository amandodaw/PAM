import java.util.Scanner;

public class Menu {
	private static Jugador jugador;
	//private static ArrayList<Mercenario> pandilla = new ArrayList<Mercenario>();
	static Scanner sc = new Scanner(System.in);
	
	public static void inicio(){
		System.out.println("Bienvenido. Te esperan un monton de aventuras y ostias como panes\nIntroduce tu nombre, oh valiente lider de bellacos");
		jugador=new Jugador(sc.nextLine());
		menuPrincipal();
	}
	public static void menuPrincipal(){
		if(jugador.getPandilla().size()>0||jugador.getOro()>=300){
			System.out.println("Selecciona una opcion.\n\n1. Contratar mercenario\n2. Lista reclutados\n3. Pelear en el ring\n4. Tienda\n5. Inventario");
			System.out.println("---------------");
			int opcion=sc.nextInt();
			switch(opcion){
				case 1: menuReclutar(); break;
				case 2: mostrarMercenarios(); break;
				case 3: menuRing(); break;
				case 4: menuTienda(); break;
				case 5: inventario(); break;
			}
		}
	}
	
	public static void menuReclutar(){
		if(jugador.getOro()>=300){
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
				jugador.getPandilla().add(listaReclutas[opcion]);
				jugador.setOro(jugador.getOro()-300);
				System.out.println("Te queda "+jugador.getOro()+"g");
			}
		}else{
			System.out.println("Que quieres hacer con solo "+jugador.getOro()+"g? vuelve cuando tengas mas pasta, pobreton");
		}
		menuPrincipal();
	}
	public static void mostrarMercenarios(){
		System.out.println("\nPandilla de "+jugador.getNombre());
		for(Mercenario merc:jugador.getPandilla()){
			merc.mostrarStats();
		}
		System.out.println("---------------");
		menuPrincipal();
	}
	public static void menuRing(){
		Mercenario[] listaRing = new Mercenario[5];
		for(int i = 0; i<5;i++){
			listaRing[i]=new Mercenario();
			listaRing[i].generarStats();
		}
		System.out.println("Nombre |");
		for(int i = 0; i<6;i++){
			if(i==5){
				System.out.println("6. Cancelar pelea");
			}else System.out.println((i+1)+" "+listaRing[i].getNombre()+" "
			+listaRing[i].getAtaque()+"-ATQ "+listaRing[i].getDefensa()+"-DEF "+listaRing[i].getHP()+"-HP");			
		}
		System.out.println("Introduce el numero correspondiente al mercenario rival que quieres atacar");
		int opcion = sc.nextInt()-1;
		/*if(opcion!=5){
			jugador.setRival(new Mercenario());
			jugador.setRival(listaRing[opcion]);
		}*/		
		jugador.pelear(jugador.elegirMercenario(),listaRing[opcion]);
		menuPrincipal();
	}
	public static void menuTienda(){
		System.out.println("\n1. Comprar pocion(Cura 50 HP) - 50g\n2. Comprar escudo(Defensa+15)\n3. Comprar machete(Ataque+10)\n4. Volver");
		int opcion = sc.nextInt();
		switch(opcion){
		case 1: comprarPocion();break;
		case 2: comprarEscudo();break;
		case 3: comprarMachete();break;
		case 4: menuPrincipal();break;
		}
	}
	public static void comprarPocion(){
		System.out.println("Las pociones restauran "+jugador.getPoderPocion()+"\nCuantas pociones quieres comprar? \nPrecio: 50g");
		int num=sc.nextInt();
		jugador.setNumPociones(jugador.getNumPociones()+num);
		jugador.setOro(jugador.getOro()-num*jugador.getPrecioPocion());
		System.out.println("Te queda: "+jugador.getOro()+"g");
		menuPrincipal();
	}
	public static void comprarEscudo(){
		System.out.println("El escudo otorga 15 de defensa. Tiene un 25% de probabilidad de romperse. Cuesta 100g\nCuantos quieres comprar?");
		int num=sc.nextInt();
		jugador.setNumEscudos(jugador.getNumEscudos()+num);
		jugador.setOro(jugador.getOro()-100);
		System.out.println("Te queda: "+jugador.getOro()+"g");
		menuPrincipal();
	}
	public static void comprarMachete(){
		System.out.println("El machete otorga 10 de ataque. Tiene un 25% de probabilidad de romperse. Cuesta 100g\nCuantos quieres comprar?");
		int num=sc.nextInt();
		jugador.setNumMachetes(jugador.getNumMachetes()+num);
		jugador.setOro(jugador.getOro()-100);
		System.out.println("Te queda: "+jugador.getOro()+"g");
		menuPrincipal();
	}
	public static void inventario(){
		System.out.println("Dispones de los siguientes objetos:\n1.- "+jugador.getNumPociones()+" pociones.\n2.- "+jugador.getNumEscudos()+" escudos.\n3.- "+jugador.getNumMachetes()+" machetes.\n4.- Salir\nQuieres usar alguno?");
		int opcion = sc.nextInt();
		switch(opcion){
		case 1: jugador.usarPocion();break;
		case 2: jugador.darEscudo();break;
		case 3: jugador.darMachete();break;
		case 4: menuPrincipal();break;
		}
		inventario();
	}
}
