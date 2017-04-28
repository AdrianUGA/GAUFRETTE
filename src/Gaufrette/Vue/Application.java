package Gaufrette.Vue;

import Gaufrette.Model.Coordonnees;
import Gaufrette.Model.Jeu;

public interface Application {

    public void run();

    public int menu();

    public Coordonnees choixJoueurHumain();

    public void rafraichir(Jeu jeu);

    public void fin(Jeu jeu);

    public void coupInvalide();
}
