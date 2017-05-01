package Gaufrette.Vue;

import Gaufrette.Model.Gaufre;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;

public class GridGaufrette{
	
	GridPane gp;
	double canvasWidth, canvasHeight;
	int x, y, alreadyEatX, alreadyEatY;
	Gaufre g;

	public GridGaufrette(Gaufre g){
	    this.g = g;
		this.alreadyEatX = this.x = g.getLargeur();
		this.alreadyEatY = this.y = g.getHauteur();
		canvasWidth = 500/x;
		canvasHeight = 500/y;
		final GridPane grid = new GridPane();
		
		
        for (int row = 0 ; row < y ; row++ ){
            RowConstraints rc = new RowConstraints();
            rc.setFillHeight(true);
            rc.setVgrow(Priority.ALWAYS);
            grid.getRowConstraints().add(rc);
        }
        for (int col = 0 ; col < x; col++ ) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setFillWidth(true);
            cc.setHgrow(Priority.ALWAYS);
            grid.getColumnConstraints().add(cc);
        }
		
		for (int i = 0; i<x; i++) {
			for (int j = 0; j<y; j++) {
				final Canvas c = new Canvas(canvasWidth, canvasHeight);
				if(i==0 && j==0) {
					StackPane holder = new StackPane();
			        holder.getChildren().add(c);
			        grid.add(holder, i, j);
			        holder.setStyle("-fx-background-color: red");
				}
				if (g.getCases()[j][i].estMangee()){
                    StackPane holder = new StackPane();
                    holder.getChildren().add(c);
                    grid.add(holder, i, j);
                    holder.setStyle("-fx-background-color: blue");
                }
				grid.add(c, i, j);
			}
		}

		grid.setGridLinesVisible(true);
		
		this.gp = grid;
	}
	
	public GridPane getGridGaufrette() {
		return this.gp;
	}

	private Node getNode(int col, int row) {
		   for (Node node : this.gp.getChildren()) {
		        if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
		            return node;
		        }
		    }
		    return null;
	}
}
	
	

