
import java.util.Scanner;

public class Main{
	public static void main(String[]args){
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce tu nombre, oh nuevo lider");
		//System.out.println((int)(Math.random() * 11 + 2));; ME ACUERDO PERFECTAMENTE
		/*Mercenario pepe = new Mercenario();
		pepe.generarStats();
		pepe.mostrarStats();*/
		Jugador jugador = new Jugador(sc.nextLine());
		jugador.menuPrincipal();
		sc.close();
	}
}