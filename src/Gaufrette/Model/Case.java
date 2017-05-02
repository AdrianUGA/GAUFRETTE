package Gaufrette.Model;

public enum Case {
	
	MANGEABLE(0),
	POISON(1),
	MANGEE(2);

	private int type;
	
	private Case(int type){
		this.type = type;
	}
	
	public boolean estMangeable(){
		return this.type == 0;
	}
	
	public boolean estPoison(){
		return this.type == 1;
	}
	
	public boolean estMangee(){
		return this.type == 2;
	}

	public String toString(){
		return ""+this.type;
	}

}
