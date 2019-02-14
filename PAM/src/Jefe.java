
public class Jefe extends Mercenario {
	private boolean transformado=false;
	public Jefe(int atq, int def, int hp){
		super();
		this.setAtaque(atq);
		this.setDefensa(def);
		this.setHPMax(hp);
		this.setHP(-this.getHPMax());
	}
	public void formaFinal(){
		transformado=true;
		System.out.println(this.getNombre()+" se esta transformando!\nTambien le ha pedido un cuchillo a su primo!\nY un escudo al Papa!");
		this.setEscudo(true);
		this.setMachete(true);
		this.setHPMax(this.getHPMax()*3);
		this.setHP(-150);
	}
	public boolean isTransformado() {
		return transformado;
	}
	public void setTransformado(boolean transformado) {
		this.transformado = transformado;
	}
}
