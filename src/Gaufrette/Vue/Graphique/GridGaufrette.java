package Gaufrette.Vue.Graphique;

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

	public GridGaufrette(int x, int y){
		this.alreadyEatX = this.x = x;
		this.alreadyEatY = this.y = y;
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
				grid.add(c, i, j);
			}
		}

		grid.setGridLinesVisible(true);
		
		this.gp = grid;
	}
	
	public GridPane getGridGaufrette() {
		return this.gp;
	}
	
	public boolean calculPosition(double posiX, double posiY) {
		int canvasX = 0;
		int canvasY = 0;

		while((canvasX * canvasWidth) <= posiX) {
			canvasX++;
		}
		while((canvasY * canvasHeight) <= posiY) {
			canvasY++;
		}
		
		canvasX--;
		canvasY--;
		System.out.println("x: " + canvasX + " y: " + canvasY);
		
		
		if(alreadyEatX>canvasX || alreadyEatY>canvasY){
			Node node = getNode(canvasX,canvasY);
			if(node == null) {
				System.err.println("node = null");
				return false;
			}
			for(int col = canvasX; col <this.x; col++) {
				for (int row = canvasY; row < this.y; row++) {
					if(canvasX != 0 || canvasY != 0) {				
						StackPane holder = new StackPane();
						holder.getChildren().add(node);
						holder.setStyle("-fx-background-color: blue");
						this.gp.add(holder, col, row);
					}
					else {
						System.out.println("Perdu !");
						return true;
					}

				}
			}
			this.alreadyEatX = canvasX;
			this.alreadyEatY = canvasY;
		}
		else {
			System.out.println("Déjà mangé !");
		}
		
		return false;
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
	
	

