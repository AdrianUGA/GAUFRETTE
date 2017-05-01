package Gaufrette.Model;

public class Humain implements Joueur{

	private String nom;
	
	public Humain(String nom){
		this.nom = nom;
	}

	@Override
	public Coordonnees jouer() {
        //return this.app.choixJoueurHumain();
		return new Coordonnees(1,0);
	}

	public String getNom(){
	    return this.nom;
    }

    public String toString(){
	    return "Joueur humain: " + this.nom;
    }
}
