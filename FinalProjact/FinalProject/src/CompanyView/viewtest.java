package CompanyView;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventDispatchChain;
import javafx.event.EventDispatcher;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class viewtest extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage theStage) throws Exception {
		theStage.setTitle("Company Policy Simulation Machine");
		GridPane gpRoot = new GridPane();
		gpRoot.setPadding(new Insets(10));
		gpRoot.setVgap(10);
		gpRoot.setHgap(10);
		
		///menu pane
		GridPane gpMenu = new GridPane();
		gpMenu.setPadding(new Insets(10));
		gpMenu.setVgap(10);
		gpMenu.setHgap(10);
		VBox vbMenuButtons = new VBox();
		vbMenuButtons.setSpacing(10);
		ToggleGroup menuButtonGroup = new ToggleGroup();
		RadioButton rb1 = new RadioButton("Add a new Workers to A Deparment");
		RadioButton rb2 = new RadioButton("Add a new Deparment");
		RadioButton rb3 = new RadioButton("Show Deparment Workers");
		RadioButton rb4 = new RadioButton("Show Entire Company");
		RadioButton rb5 = new RadioButton("Changing Work Policy Based on roles");
		RadioButton rb6 = new RadioButton("Changing Work Policy Based on Departments");
		RadioButton rb7 = new RadioButton("Current profit Values");
		RadioButton rb8 = new RadioButton("Exit");
		rb1.setToggleGroup(menuButtonGroup);
		rb2.setToggleGroup(menuButtonGroup);
		rb3.setToggleGroup(menuButtonGroup);
		rb4.setToggleGroup(menuButtonGroup);
		rb5.setToggleGroup(menuButtonGroup);
		rb6.setToggleGroup(menuButtonGroup);
		rb7.setToggleGroup(menuButtonGroup);
		rb8.setToggleGroup(menuButtonGroup);
		vbMenuButtons.getChildren().addAll(rb1, rb2, rb3, rb4, rb5, rb6, rb7, rb8);
		gpMenu.add(vbMenuButtons, 0, 0, 10, 10);
		
		//Add new Department Workers
		GridPane gpAddNewWorker = new GridPane();
		gpAddNewWorker.setVisible(false);
		gpAddNewWorker.setPadding(new Insets(10));
		gpAddNewWorker.setVgap(10);
		gpAddNewWorker.setHgap(10);
		Label lblAddWorkerName = new Label("Name:");
		TextField tfAddWorkerName = new TextField();
		Label lblAddWorkerPreference = new Label("Preference:");
		ComboBox<String> cmbWorkdayPrefrences = new ComboBox<String>();
		 cmbWorkdayPrefrences.getItems().addAll("Earlier","Later","Regular","Home");
		 cmbWorkdayPrefrences.setValue("Regular");
		 Label lblAddWorkerRoles = new Label("Role:");
		TextField tfAddWorkerRoles = new TextField();
		Label lblAddWorkerCurrentWorkdayPolicy = new Label("Current work Policy:");
		ComboBox<String> cmbCurrentWorkdayPolicy = new ComboBox<String>();
		 cmbCurrentWorkdayPolicy.getItems().addAll("Earlier","Later","Regular","Home");
		 cmbCurrentWorkdayPolicy.setValue("Regular");
		
			HBox hbAddWorkerButtons = new HBox();
			hbAddWorkerButtons.setSpacing(20);
			Button btnAddWorkerSave = new Button("Save");
			btnAddWorkerSave.setStyle("-fx-font-size: 1.5em;");
			hbAddWorkerButtons.getChildren().addAll( btnAddWorkerSave);
			
			gpAddNewWorker.add(lblAddWorkerName, 0, 0);
			gpAddNewWorker.add(tfAddWorkerName, 0, 1);
			gpAddNewWorker.add(lblAddWorkerPreference, 0, 2);
			gpAddNewWorker.add(cmbWorkdayPrefrences, 0, 3);
			gpAddNewWorker.add(lblAddWorkerRoles, 0, 4);
			gpAddNewWorker.add(tfAddWorkerRoles, 0, 5);
			gpAddNewWorker.add(lblAddWorkerCurrentWorkdayPolicy, 0, 6);
			gpAddNewWorker.add(cmbCurrentWorkdayPolicy, 0, 7);
			

		
			btnAddWorkerSave.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					gpMenu.setVisible(true);
					gpAddNewWorker.setVisible(false);
					tfAddWorkerName.clear();
					tfAddWorkerRoles.clear();
				

					// add function!

				}
			});

		// adding GridPanes to main gpRoot:
		gpRoot.add(gpMenu, 5, 0, 20, 20);
		gpRoot.add(gpAddNewWorker, 5, 0, 20, 20);
		theStage.setScene(new Scene(gpRoot, 600, 500));
		theStage.show();
		
	}
	

}
