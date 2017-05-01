package Gaufrette.state;


import Gaufrette.Controller.HumanController;
import Gaufrette.GameStateMachine;
import Gaufrette.Model.Jeu;
import Gaufrette.Vue.GridGaufrette;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameState implements State {

    private GameStateMachine gsm;
    private Stage primaryStage;
    private Jeu jeu;

    public GameState(GameStateMachine gsm, Jeu jeu, Stage primaryStage){
        this.gsm = gsm;
        this.jeu = jeu;
        this.primaryStage = primaryStage;
    }

    @Override
    public void update() {
        System.out.println(this.jeu.getJoueurCourant() + ", c'est a toi de jouer");
        if(this.jeu.partieTerminee()){
            this.gsm.change("menu");
            System.out.println(this.jeu.getJoueurCourant() + ", tu as perdu");
        } else if(this.jeu.getJoueurCourant().getType().equals("ia")){
            this.jeu.jouer(this.jeu.getJoueurCourant().jouer());
        }
    }

    @Override
    public void render() {
        GridGaufrette gg = new GridGaufrette(this.jeu.getGaufre());
        GridPane dd = gg.getGridGaufrette();
        Group root = new Group();
        root.getChildren().add(dd);
        Scene scene = new Scene(root, 500, 500, Color.WHITE);
        if(this.jeu.getJoueurCourant().getType().equals("humain")){
            scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new HumanController(this.jeu));
        }
        this.primaryStage.setScene(scene);
    }

    @Override
    public void onEnter(Object param) {

    }

    @Override
    public void onExit() {

    }
}
