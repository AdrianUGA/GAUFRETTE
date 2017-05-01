package Gaufrette;

import Gaufrette.Model.Jeu;

public class Main {

	public static void main(String[] args) {
	    Jeu jeu = new Jeu();
        AppGraphique app = new AppGraphique();
        app.run(jeu);
	}

}