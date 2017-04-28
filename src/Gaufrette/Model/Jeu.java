package Gaufrette.Model;

import Gaufrette.Vue.Application;

public class Jeu {

	private Gaufre gaufre;
	private Joueur[] joueurs;
    private int joueurCourant;
    private Application application;
	
	public Jeu(Application application){
		this.joueurs = new Joueur[2];
		this.application = application;
	}
	
	public void nouveauJeu(Joueur j1, Joueur j2, int largeurGaufre, int hauteurGaufre){
        this.joueurCourant = 0;
		this.joueurs[0] = j1;
        this.joueurs[1] = j2;
		this.gaufre = new Gaufre(largeurGaufre, hauteurGaufre);
	}

	public void jouerPartie(){

        while(!this.partieTerminee()){
            this.nextJoueur();
            Joueur j = getJoueurCourant();
            this.application.rafraichir(this);
            Coordonnees c;
            boolean coupValide = false;
            do {
                c = j.jouer();
                coupValide = this.coupEstValide(c);
                if (!coupValide){
                    this.application.coupInvalide();
                }
            } while (!coupValide);
            this.jouerCoup(c);
        }
        this.application.fin(this);
    }

	private boolean coupEstValide(Coordonnees c){
        return this.gaufre.peutManger(c);
    }

	private void jouerCoup(Coordonnees c){
        this.gaufre.manger(c);
	}

	private void nextJoueur(){
        this.joueurCourant = (this.joueurCourant + 1) % 2;
    }

	private boolean partieTerminee(){
		return this.gaufre.toutMangees();
	}

    public Joueur getJoueurCourant(){
	    return this.joueurs[this.joueurCourant];
    }

    public Gaufre getGaufre(){
        return this.gaufre;
    }
}
