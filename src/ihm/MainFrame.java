package ihm;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainFrame {

	private int x;
	private int y;
	Canvas[][] c;
	
    private Stage primaryStage;
	
	public MainFrame(int x, int y, Stage primaryStage) {
		this.x = x;
		this.y = y;
		
		c = new Canvas[x][y];
		
		
		
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("GAUFRETTE");

        loadFirstFrame();
        this.primaryStage.show();
	}
	
	private void loadGrid() {
		GridGaufrette gg = new GridGaufrette(x,y);
		GridPane dd = gg.getGridGaufrette();
		Group root = new Group();
        root.getChildren().add(dd);
        Scene scene = new Scene(root, 500, 500, Color.WHITE);
        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new javafx.event.EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				boolean lose = gg.calculPosition(event.getX(), event.getY());
				if(lose) {
					loadFirstFrame();
				}
			}
		});
        this.primaryStage.setScene(scene);
	}
	
	private void loadFirstFrame(){
		AnchorPane ap = null;
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFrame.class.getResource("Selection.fxml"));
            ap = (AnchorPane)loader.load();
            
        } catch (IOException e) {
            e.printStackTrace();
        }		
//	        loadGrid();
        Group root = new Group();
        root.getChildren().add(ap);
        Scene scene = new Scene(root, 500, 500, Color.BLACK);
        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new javafx.event.EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
                loadGrid();
			}
		});
        this.primaryStage.setScene(scene);
	}
	
}
