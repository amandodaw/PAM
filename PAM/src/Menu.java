import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
	private static Jugador jugador;
	//private static ArrayList<Mercenario> pandilla = new ArrayList<Mercenario>();
	static Scanner sc = new Scanner(System.in);
	
	public static void inicio(){
		System.out.println("Bienvenido. Te esperan un monton de aventuras y ostias como panes\nIntroduce tu nombre");
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
}
