package Gaufrette.Vue;

import Gaufrette.Model.Coordonnees;
import Gaufrette.Model.Jeu;
import Gaufrette.Moteur;

public interface App {

    public void run(Moteur m);

    public void menu();

    public Coordonnees choixJoueurHumain();

    public void rafraichir(Jeu jeu);

    public void fin(Jeu jeu);

    public void coupInvalide();
}
