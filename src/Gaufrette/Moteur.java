package Gaufrette;


import Gaufrette.Model.Coordonnees;
import Gaufrette.Model.Humain;
import Gaufrette.Model.Jeu;
import Gaufrette.Model.Joueur;
import Gaufrette.Vue.App;
import Gaufrette.Vue.Console.AppConsole;
import Gaufrette.Vue.Graphique.AppGraphique;
import ia.Difficulté;
import ia.IA;

public class Moteur {

    private App app;
    private Jeu jeu;

    public Moteur(){
        this.app = new AppGraphique();
        this.jeu = new Jeu();
    }

    public void run(){
        this.app.run(this);
    }

    public void demarrerPartie(){
    	Joueur ia = new IA(this.jeu).setNom("o1").setDifficulté(Difficulté.facile);
        Joueur j1 = new Humain(this.app, "j1");
        Joueur j2 = new Humain(this.app, "j2");
        this.jeu.nouveauJeu(j1, j2, 20, 10);

        while(!this.jeu.partieTerminee()){
            this.jeu.nextJoueur();
            Joueur j = this.jeu.getJoueurCourant();
            this.app.rafraichir(this.jeu);
            Coordonnees c;
            boolean coupValide;
            do {
                c = j.jouer();
                coupValide = this.jeu.coupEstValide(c);
                if (!coupValide){
                    this.app.coupInvalide();
                }
            } while (!coupValide);
            this.jeu.jouerCoup(c);
        }

        this.app.fin(this.jeu);
        this.app.menu();
    }

    public void quitter(){
        //Quitter le programme
    }
}
