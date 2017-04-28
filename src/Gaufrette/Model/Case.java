package Gaufrette.Model;

public class Case {
	
	public final static int MANGEABLE = 0;
	public final static int POISON = 1;
	public final static int MANGEE = 2;

	private int type;
	
	public Case(int type){
		this.type = type;
	}
	
	public boolean estMangeable(){
		return this.type == MANGEABLE;
	}
	
	public boolean estPoison(){
		return this.type == POISON;
	}
	
	public boolean estMangee(){
		return this.type == MANGEE;
	}

	public void manger() {
		this.type = MANGEE;
	}

	public String toString(){
		return ""+this.type;
	}
	
	public int getType(){
		return this.type;
	}
}
