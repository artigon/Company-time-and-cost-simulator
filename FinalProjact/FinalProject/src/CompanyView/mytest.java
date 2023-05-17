package CompanyView;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class mytest extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	ComboBox<String> test = new ComboBox<String>();

	@Override
	public void start(Stage theStage) throws Exception {
		theStage.setTitle("Company Policy Simulation Machine");
		GridPane gpRoot = new GridPane();
		gpRoot.setBackground(new Background(new BackgroundFill(Color.LIGHTSLATEGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
		gpRoot.setPadding(new Insets(10));
		gpRoot.setVgap(10);
		gpRoot.setHgap(10);
		Button btntest = new Button("test");
		btntest.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				set();
			}
		});

		
		gpRoot.add(test, 0, 0);
		gpRoot.add(btntest, 1, 0);

		theStage.setScene(new Scene(gpRoot, 600, 500));
		theStage.show();
	}
	
	
	public void set() {
		test.getItems().addAll("test1", "test2", "test3");
	}

}
