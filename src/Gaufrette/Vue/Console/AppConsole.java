package Gaufrette.Vue.Console;

import Gaufrette.Model.Coordonnees;
import Gaufrette.Model.Jeu;
import Gaufrette.Moteur;
import Gaufrette.Vue.App;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class AppConsole implements App {

    private Moteur moteur;

    @Override
    public void run(Moteur m){
        this.moteur = m;
        this.menu();
    }

    public void menu(){
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

        switch (i){
            case 1:
                this.moteur.demarrerPartie();
                break;
            default:
                this.moteur.quitter();
                break;
        }
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
