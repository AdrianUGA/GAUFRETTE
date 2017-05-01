package Gaufrette.state;


import Gaufrette.GameStateMachine;
import Gaufrette.Model.Jeu;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MenuState implements State {

    private GameStateMachine gsm;
    private Jeu jeu;
    private Stage primaryStage;
    private Button one, two, zero, quit;

    public MenuState(GameStateMachine gsm, Jeu jeu, Stage primaryStage){
        this.gsm = gsm;
        this.jeu = jeu;
        this.primaryStage = primaryStage;
        this.init();
    }

    public void init(){
        one = new Button();
        two = new Button();
        zero = new Button();
        quit = new Button();

        one.setText("1 Player");
        one.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("gamemode: 1 player");
                jeu.nouveauJeu(20, 10);
                jeu.setJoueur1("humain", "j1");
                jeu.setJoueur2("ia", "ia");

                gsm.change("game");
            }
        });
        one.setLayoutX(78);
        one.setLayoutY(334);

        two.setText("2 Players");
        two.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("gamemode: 2 players");
                jeu.nouveauJeu(20, 10);
                jeu.setJoueur1("humain", "j1");
                jeu.setJoueur2("humain", "j2");

                gsm.change("game");
            }
        });
        two.setLayoutX(224);
        two.setLayoutY(334);

        zero.setText("IA vs IA");
        zero.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("gamemode: 2 ia");
                jeu.nouveauJeu(20, 10);
                jeu.setJoueur1("ia", "ia1");
                jeu.setJoueur2("ia", "ia2");

                gsm.change("game");
            }
        });
        zero.setLayoutX(383);
        zero.setLayoutY(334);

        quit.setText("Quitter");
        quit.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });
        quit.setLayoutX(383);
        quit.setLayoutY(400);
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {

    }

    @Override
    public void onEnter(Object param) {
        AnchorPane ap = new AnchorPane();
        ap.getChildren().add(this.one);
        ap.getChildren().add(this.two);
        ap.getChildren().add(this.zero);
        ap.getChildren().add(this.quit);

        Scene scene = new Scene(ap, 500, 500);
        this.primaryStage.setScene(scene);
    }

    @Override
    public void onExit() {

    }
}
