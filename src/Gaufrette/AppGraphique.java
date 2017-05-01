package Gaufrette;

import Gaufrette.Model.Jeu;
import Gaufrette.state.GameState;
import Gaufrette.state.MenuState;
import javafx.application.Application;
import javafx.stage.Stage;


public class AppGraphique extends Application {

    static Jeu jeu;

    public void run(Jeu jeu) {
        this.jeu = jeu;
        launch();
    }

    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("GAUFRETTE");
        primaryStage.show();
        GameStateMachine gsm = new GameStateMachine();
        gsm.add("menu", new MenuState(gsm, this.jeu, primaryStage));
        gsm.add("game", new GameState(gsm, this.jeu, primaryStage));

        gsm.change("menu");

        GameLoop gameLoop = new GameLoop(gsm);
        gameLoop.start();
    }
}
