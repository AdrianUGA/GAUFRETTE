package Gaufrette.Vue.Console;

import Gaufrette.Model.Coordonnees;
import Gaufrette.Model.Humain;
import Gaufrette.Model.Joueur;
import Gaufrette.Model.Jeu;
import Gaufrette.Vue.Application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ApplicationConsole implements Application {

    private Jeu jeu;

    public ApplicationConsole(){
        this.jeu = new Jeu(this);
    }

    public void run(){
        boolean quitter = false;
        while(!quitter){
            switch (this.menu()){
                case 1:
                    Joueur j1 = new Humain(this, "j1");
                    Joueur j2 = new Humain(this, "j2");
                    this.jeu.nouveauJeu(j1, j2, 20, 10);
                    this.jeu.jouerPartie();
                    break;
                case 2:
                    quitter = true;
                    break;
            }
        }
    }


    public int menu(){
        int i = 0;
        while (i <= 0){
            System.out.println("Menu");
            System.out.println("1. Nouvelle partie");
            System.out.println("2. Quitter");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Votre choix: ");
            try{
                i = Integer.parseInt(br.readLine());
            }catch(Exception e){
                i = -1;
            }
        }
        return i;
    }

    @Override
    public Coordonnees choixJoueurHumain() {
        System.out.print("Coordonnée x: ");
        Scanner sc = new Scanner(System.in);
        String x = sc.nextLine();
        System.out.print("Coordonnée y: ");
        String y = sc.nextLine();
        return new Coordonnees(Integer.parseInt(x), Integer.parseInt(y));
    }

    public void rafraichir(Jeu jeu){
        System.out.println(jeu.getJoueurCourant().getNom() + ", c'est à toi de jouer");
        System.out.println(jeu.getGaufre());
    }

    public void fin(Jeu jeu){
        System.out.println(jeu.getJoueurCourant().getNom() + " a gagné");
        System.out.println();
    }

    public void coupInvalide(){
        System.out.println("Coup invalide, essayez d'autre coordonnées");
    }
}
