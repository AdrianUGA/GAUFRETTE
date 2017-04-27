package Gaufrette.Model;

import Gaufrette.Vue.Application;

public class Humain implements Joueur{

	private String nom;
	private Application app;
	
	public Humain(Application app, String nom){
		this.nom = nom;
		this.app = app;
	}

	@Override
	public Coordonnees jouer() {
        return this.app.choixJoueurHumain();
	}

	public String getNom(){
	    return this.nom;
    }
}
