package Gaufrette.Model;

import Gaufrette.Vue.App;

public class Humain implements Joueur{

	private String nom;
	private App app;
	
	public Humain(App app, String nom){
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
