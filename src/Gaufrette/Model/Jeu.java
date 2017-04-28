package Gaufrette.Model;

import Gaufrette.Vue.App;

public class Jeu {

	private Gaufre gaufre;
	private Joueur[] joueurs;
    private int joueurCourant;

	public Jeu(){
		this.joueurs = new Joueur[2];
	}
	
	public void nouveauJeu(Joueur j1, Joueur j2, int largeurGaufre, int hauteurGaufre){
        this.joueurCourant = 0;
		this.joueurs[0] = j1;
        this.joueurs[1] = j2;
		this.gaufre = new Gaufre(largeurGaufre, hauteurGaufre);
	}

	public boolean coupEstValide(Coordonnees c){
        return this.gaufre.peutManger(c);
    }

    public void jouerCoup(Coordonnees c){
        this.gaufre.manger(c);
	}

    public void nextJoueur(){
        this.joueurCourant = (this.joueurCourant + 1) % 2;
    }

    public boolean partieTerminee(){
		return this.gaufre.toutMangees();
	}

    public Joueur getJoueurCourant(){
	    return this.joueurs[this.joueurCourant];
    }

    public Gaufre getGaufre(){
        return this.gaufre;
    }
}
