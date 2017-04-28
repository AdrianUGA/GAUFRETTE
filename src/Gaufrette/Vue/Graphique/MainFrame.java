package Gaufrette.Vue.Graphique;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainFrame {

	private int x;
	private int y;
	private Button one, two, zero;
	
    private Stage primaryStage;
	
	public MainFrame(Stage primaryStage) {
		
		initSelectionFrame();
		
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("GAUFRETTE");

        this.primaryStage.show();
	}
	
	public void setSizeOfGrid(int x, int y) {
		this.x = x;
		this.y = y;
	}

	private void initSelectionFrame() {
		one = new Button();
		two = new Button();
		zero = new Button();
		
	    one.setText("1 Player");
	    one.setOnAction(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent event) {
	            System.out.println("1");

	            loadGridFrame();
	        }
	    });
	    one.setLayoutX(78);
	    one.setLayoutY(334);
	    
	    two.setText("2 Player");
	    two.setOnAction(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent event) {
	            System.out.println("2");
	            loadGridFrame();
	        }
	    });
	    two.setLayoutX(224);
	    two.setLayoutY(334);
	    
	    zero.setText("IA vs IA");
	    zero.setOnAction(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent event) {
	            System.out.println("0");
	            loadGridFrame();
	        }
	    });
	    zero.setLayoutX(383);
	    zero.setLayoutY(334);
	    

	}
	
	public void loadGridFrame() {
		GridGaufrette gg = new GridGaufrette(x,y);
		GridPane dd = gg.getGridGaufrette();
		Group root = new Group();
        root.getChildren().add(dd);
        Scene scene = new Scene(root, 500, 500, Color.WHITE);
        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				boolean lose = gg.calculPosition(event.getX(), event.getY());
				if(lose) {
					loadSelectionFrame();
				}
			}
		});
        this.primaryStage.setScene(scene);
	}
	
	public void loadSelectionFrame(){

		AnchorPane ap = new AnchorPane();
		ap.getChildren().add(this.one);
		ap.getChildren().add(this.two);
		ap.getChildren().add(this.zero);
		
        Scene scene = new Scene(ap, 500, 500);
        this.primaryStage.setScene(scene);
	}
	

	
}
