public class Mercenario{
	private String nombre;
	private int ataque, defensa, hp;
	
	public void generarStats(){
		ataque = (int) Math.floor(Math.random() * 51);
		defensa = (int) Math.floor(Math.random() * 51);
		hp = (int) Math.floor(Math.random() * 51);
		String consonantes = "bcdfghjklmnpqrstvwxyz";
		String vocales = "aeiou";
		int tamano = (int)(Math.random() * 11 + 2);
		char[] constructorNombre = new char[tamano];
		for(int i = 0;i<tamano;i++){
			if(i%2==0){
				constructorNombre[i]=consonantes.charAt((int) Math.floor(Math.random() * 21));
				if(i==0){
					constructorNombre[i]=Character.toUpperCase(constructorNombre[i]);
				}
			}
			else{
				constructorNombre[i]=vocales.charAt((int) Math.floor(Math.random() * 5));
			}
		}
		nombre= new String(constructorNombre);
	}
	public void mostrarStats(){
		System.out.println(getNombre()+" "+getAtaque()+" "+getDefensa()+" "+getHP());
	}
	public String getNombre(){
		return nombre;
	}
	public int getAtaque(){
		return ataque;
	}
	public int getDefensa(){
		return defensa;
	}
	public void setDefensa(int def){
		defensa=def;		
	}
	public int getHP(){
		return hp;
	}
	public void setHP(int ataque){
		hp=hp-ataque;
	}
	public int atacar(int valorDef){
		int ataque = ((int)(getAtaque()*Math.random()))-valorDef;
		System.out.println(getNombre()+" ataca con una fuerza de: "+(ataque+valorDef));		
		if(ataque<0){
			hp=hp+ataque/2;
			System.out.println(getNombre()+" ha esquivado el ataque! Devuelve "+(-ataque/2)+" de da\u00f1o");
			return 0;
		}else	return ataque;

	}
	public int defender(){
		int valorDefensa=(int)(getDefensa()*Math.random());
		System.out.println(getNombre()+" defiende con un valor de: "+valorDefensa);
		return valorDefensa;

	}
}