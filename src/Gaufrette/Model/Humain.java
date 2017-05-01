package Gaufrette.Model;

public class Humain implements Joueur{

	private String nom;
	
	public Humain(String nom){
		this.nom = nom;
	}

	@Override
	public Coordonnees jouer() {
        return null;
	}

	public String getNom(){
	    return this.nom;
    }

    public String getType(){
        return "humain";
    }

    public String toString(){
	    return "Joueur humain: " + this.nom;
    }
}
