package Gaufrette.Vue.Graphique;

import Gaufrette.Model.Coordonnees;
import Gaufrette.Model.Jeu;
import Gaufrette.Moteur;
import Gaufrette.Vue.App;
import javafx.application.Application;
import javafx.stage.Stage;


public class AppGraphique extends Application implements App {

    private MainFrame mainFrame;
    private Moteur moteur;

    @Override
    public void run(Moteur m) {
        this.moteur = m;
        launch();
    }

    @Override
    public void menu() {
        this.mainFrame.loadSelectionFrame();
    }

    @Override
    public Coordonnees choixJoueurHumain() {
        return new Coordonnees(3,2);
    }

    @Override
    public void rafraichir(Jeu jeu) {

    }

    @Override
    public void fin(Jeu jeu) {

    }

    @Override
    public void coupInvalide() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.mainFrame = new MainFrame(primaryStage);
        this.menu();

    }
}
