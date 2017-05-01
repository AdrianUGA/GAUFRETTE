package Gaufrette.Controller;

import Gaufrette.Model.Coordonnees;
import Gaufrette.Model.Jeu;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class HumanController implements EventHandler<MouseEvent> {

    private Jeu jeu;

    public HumanController(Jeu jeu){
        this.jeu = jeu;
    }

    public void jouer(Coordonnees coordonnees){
        this.jeu.jouer(coordonnees);
    }

    @Override
    public void handle(MouseEvent event) {
        int canvasX = 0;
        int canvasY = 0;

        while((canvasX * 500/20) <= event.getX()) {
            canvasX++;
        }
        while((canvasY * 500/10) <= event.getY()) {
            canvasY++;
        }

        canvasX--;
        canvasY--;
        System.out.println("x: " + canvasX + " y: " + canvasY);
        this.jouer(new Coordonnees(canvasX, canvasY));
    }
}
