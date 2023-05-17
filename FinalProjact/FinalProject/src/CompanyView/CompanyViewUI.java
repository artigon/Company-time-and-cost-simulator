package CompanyView;

import java.util.Vector;
import javax.swing.JOptionPane;

import com.sun.webkit.ThemeClient;

import CompanyController.CompanyControllers;
import CompanyListeners.CompanyViewEventListener;
import CompanyModel.Departments;
import CompanyModel.Departments.DepartmentWorkingPolicies;
import CompanyModel.Workers.SalaryType;
import CompanyModel.Workers.WorkdayPreferences;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CompanyViewUI implements AbstractCompanyView {
	private static final SalaryType TheSalaryType = null;
	Vector<CompanyViewEventListener> allListeners = new Vector<CompanyViewEventListener>();
	TextArea taShowComany = new TextArea();
	TextArea taShowDepartmentWorkers = new TextArea();
	// ComboBoxs:
	ComboBox<String> cmbAddWorkerDepartmentNames = new ComboBox<String>();
	ComboBox<String> cmbShowDepatmentDepartmentNames = new ComboBox<String>();
	ComboBox<String> cmbChangingWorkPolicyBasedOnRolesInDepartmentDepartmentNames = new ComboBox<String>();
	ComboBox<String> cmbChangeWorkPolicyBasedOnDepartmentNameDepartmentNames = new ComboBox<String>();
	ComboBox<String> cmbProfitDepartmentNames = new ComboBox<String>();

	// profits:
	Label lblProfitComany = new Label("company profit test");
	Label lblProfitDepatments = new Label("Depatments profit test");
	TextArea taWorkersProfit = new TextArea("Workers profit test");

	public CompanyViewUI(Stage theStage) {
		taShowComany.setEditable(false);
		taShowDepartmentWorkers.setEditable(false);
		taWorkersProfit.setEditable(false);

		theStage.setTitle("Company Policy Simulation Machine");
		GridPane gpRoot = new GridPane();
		gpRoot.setBackground(new Background(new BackgroundFill(Color.LIGHTSLATEGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
		gpRoot.setPadding(new Insets(10));
		gpRoot.setVgap(10);
		gpRoot.setHgap(10);

		/// menu pane
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
		RadioButton rb5 = new RadioButton("Change work policy based on roles in depatment");
		RadioButton rb6 = new RadioButton("Changing Work Policy Based on Departments");
		RadioButton rb7 = new RadioButton("Changing Work Policy Based on roles");
		RadioButton rb8 = new RadioButton("Current profit Values");
		RadioButton rb9 = new RadioButton("Exit");
		rb1.setToggleGroup(menuButtonGroup);
		rb2.setToggleGroup(menuButtonGroup);
		rb3.setToggleGroup(menuButtonGroup);
		rb4.setToggleGroup(menuButtonGroup);
		rb5.setToggleGroup(menuButtonGroup);
		rb6.setToggleGroup(menuButtonGroup);
		rb8.setToggleGroup(menuButtonGroup);
		rb9.setToggleGroup(menuButtonGroup);
		vbMenuButtons.getChildren().addAll(rb1, rb2, rb3, rb4, rb5, rb6, rb7, rb8, rb9);
		gpMenu.add(vbMenuButtons, 0, 0, 10, 10);

// Add new Worker to a Department grid pane:
		GridPane gpAddNewWorker = new GridPane();
		gpAddNewWorker.setVisible(false);
		gpAddNewWorker.setPadding(new Insets(10));
		gpAddNewWorker.setVgap(10);
		gpAddNewWorker.setHgap(10);
		Label lblAddWorkerName = new Label("Name:");
		TextField tfAddWorkerName = new TextField();
		Label lblAddWorkerPreference = new Label("Preference:");
		ComboBox<String> cmbWorkdayPrefrences = new ComboBox<String>();
		cmbWorkdayPrefrences.getItems().addAll("Earlier", "Later", "Regular", "Home");
		cmbWorkdayPrefrences.setValue("Regular");
		Label lblAddWorkerRoles = new Label("Role:");
		TextField tfAddWorkerRoles = new TextField();
		Label lblAddWorkerCurrentWorkdayPolicy = new Label("Current work Policy:");
		ComboBox<String> cmbCurrentWorkdayPolicy = new ComboBox<String>();
		cmbCurrentWorkdayPolicy.getItems().addAll("Earlier", "Later", "Regular", "Home");
		cmbCurrentWorkdayPolicy.setValue("Regular");
		Label lblWorkerSalaryType = new Label("Choose Salary type:");
		ComboBox<String> cmbSalaryTypes = new ComboBox<String>();
		cmbSalaryTypes.getItems().addAll("BaseSalary", "Hourly", "BaseSalaryPlusPercentage");
		cmbSalaryTypes.setValue("BaseSalary");

		HBox hbAddWorkerButtons = new HBox();
		hbAddWorkerButtons.setSpacing(20);
		Button btnAddWorkerSave = new Button("Save");
		Button btnAddWorkerBack = new Button("Back");
		btnAddWorkerSave.setStyle("-fx-font-size: 1.5em;");
		btnAddWorkerBack.setStyle("-fx-font-size: 1.5em;");
		hbAddWorkerButtons.getChildren().addAll(btnAddWorkerBack, btnAddWorkerSave);

		gpAddNewWorker.add(lblAddWorkerName, 0, 0);
		gpAddNewWorker.add(tfAddWorkerName, 0, 1);
		gpAddNewWorker.add(lblAddWorkerPreference, 0, 2);
		gpAddNewWorker.add(cmbWorkdayPrefrences, 0, 3);
		gpAddNewWorker.add(lblAddWorkerRoles, 0, 4);
		gpAddNewWorker.add(tfAddWorkerRoles, 0, 5);
		gpAddNewWorker.add(lblAddWorkerCurrentWorkdayPolicy, 0, 6);
		gpAddNewWorker.add(cmbCurrentWorkdayPolicy, 0, 7);
		gpAddNewWorker.add(cmbAddWorkerDepartmentNames, 0, 8);
		gpAddNewWorker.add(lblWorkerSalaryType, 0, 9);
		gpAddNewWorker.add(cmbSalaryTypes, 0, 10);
		gpAddNewWorker.add(hbAddWorkerButtons, 0, 12);

		btnAddWorkerSave.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					if (tfAddWorkerName.getText().isBlank() || tfAddWorkerRoles.getText().isBlank()) {
						throw new Exception("Cant leave text fields empty");
					} else if (cmbAddWorkerDepartmentNames.getValue().equalsIgnoreCase("Choose Department")) {
						throw new Exception("You need to choose a department");
					} else {

						WorkdayPreferences prefrenc = null;
						WorkdayPreferences current = null;
						SalaryType Thesalary = null;

						if (cmbWorkdayPrefrences.getValue().toString().equalsIgnoreCase("Earlier")) {
							prefrenc = WorkdayPreferences.Earlier;
						} else if (cmbWorkdayPrefrences.getValue().toString().equalsIgnoreCase("Later")) {
							prefrenc = WorkdayPreferences.Later;
						} else if (cmbWorkdayPrefrences.getValue().toString().equalsIgnoreCase("Regular")) {
							prefrenc = WorkdayPreferences.Regular;
						} else {
							prefrenc = WorkdayPreferences.Home;
						}
						if (cmbCurrentWorkdayPolicy.getValue().toString().equalsIgnoreCase("Earlier")) {
							current = WorkdayPreferences.Earlier;
						} else if (cmbCurrentWorkdayPolicy.getValue().toString().equalsIgnoreCase("Later")) {
							current = WorkdayPreferences.Later;
						} else if (cmbCurrentWorkdayPolicy.getValue().toString().equalsIgnoreCase("Regular")) {
							current = WorkdayPreferences.Regular;
						} else {
							current = WorkdayPreferences.Home;
						}
						if (cmbSalaryTypes.getValue().toString().equalsIgnoreCase("BaseSalary")) {
							Thesalary = TheSalaryType.BaseSalary;
						} else if (cmbSalaryTypes.getValue().toString().equalsIgnoreCase("Hourly")) {
							Thesalary = TheSalaryType.Hourly;
						} else if (cmbSalaryTypes.getValue().toString().equalsIgnoreCase("BaseSalaryPlusSales")) {
							Thesalary = TheSalaryType.Hourly;
						}

						FireNewWorkerToModel(tfAddWorkerName.getText(), prefrenc, tfAddWorkerRoles.getText(), current,
								cmbAddWorkerDepartmentNames.getValue().toString(), Thesalary);
						gpMenu.setVisible(true);
						gpAddNewWorker.setVisible(false);
						tfAddWorkerName.clear();
						tfAddWorkerRoles.clear();

					}
				} catch (Exception e) {
					Alert theAlert = new Alert(AlertType.ERROR);
					theAlert.setContentText("please check again");
					theAlert.setHeaderText(e.getMessage());
					theAlert.show();
				}
			}
		});

		btnAddWorkerBack.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				gpMenu.setVisible(true);
				gpAddNewWorker.setVisible(false);
				tfAddWorkerName.clear();
				tfAddWorkerRoles.clear();

			}
		});

		// add new Deparment grid pane:
		GridPane gpAddNewDepartment = new GridPane();
		gpAddNewDepartment.setVisible(false);
		gpAddNewDepartment.setPadding(new Insets(10));
		gpAddNewDepartment.setVgap(10);
		gpAddNewDepartment.setHgap(10);
		Label lblDepartmentName = new Label("Department name:");
//		lblDepartmentName.setStyle("-fx-text-fill: white; -fx-font-size: 1.5em;");
		TextField tfDepartmentName = new TextField();

//		lblRoles.setStyle("-fx-text-fill: white; -fx-font-size: 1.5em;");
		Label lblDepartmentWorkingPolicy = new Label("What Is The Department Working Policy?");
//		lblDepartmentWorkingPolicy.setStyle("-fx-text-fill: white; -fx-font-size: 1.5em;");
		VBox vbDepartmentWorkingPolicyButtons = new VBox();
		vbDepartmentWorkingPolicyButtons.setSpacing(10);
		ToggleGroup DepartmentWorkingPolicy = new ToggleGroup();
		RadioButton rbAllchangeDepartment = new RadioButton("All change department");
//		rbAllchangeDepartment.setStyle("-fx-text-fill: white; -fx-font-size: 1.5em;");
		RadioButton rbNoChangeDepartment = new RadioButton("No change department");
//		rbNoChangeDepartment.setStyle("-fx-text-fill: white; -fx-font-size: 1.5em;");
		RadioButton rbFreeChangeDepartment = new RadioButton("Free Change Department");
//		rbFreeChangeDepartment.setStyle("-fx-text-fill: white; -fx-font-size: 1.5em;");
		rbAllchangeDepartment.setToggleGroup(DepartmentWorkingPolicy);
		rbNoChangeDepartment.setToggleGroup(DepartmentWorkingPolicy);
		rbFreeChangeDepartment.setToggleGroup(DepartmentWorkingPolicy);
		vbDepartmentWorkingPolicyButtons.getChildren().addAll(rbAllchangeDepartment, rbFreeChangeDepartment,
				rbNoChangeDepartment);

		HBox hbAddNewDepartmentButtons = new HBox();
		hbAddNewDepartmentButtons.setSpacing(20);
		Button btnAddNewDepartmentSave = new Button("Save");
//		btnAddNewDepartmentSave.setStyle("-fx-font-size: 1.5em;");
		Button btnAddNewDepartmentBack = new Button("Back");
//		btnAddNewDepartmentBack.setStyle("-fx-font-size: 1.5em;");
		hbAddNewDepartmentButtons.getChildren().addAll(btnAddNewDepartmentBack, btnAddNewDepartmentSave);

		gpAddNewDepartment.add(lblDepartmentName, 0, 0);
		gpAddNewDepartment.add(tfDepartmentName, 0, 1);
		gpAddNewDepartment.add(vbDepartmentWorkingPolicyButtons, 0, 2);
		gpAddNewDepartment.add(hbAddNewDepartmentButtons, 0, 3);

		btnAddNewDepartmentBack.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				gpMenu.setVisible(true);
				gpAddNewDepartment.setVisible(false);
				tfDepartmentName.clear();
				rbAllchangeDepartment.setSelected(false);
				rbNoChangeDepartment.setSelected(false);
				rbFreeChangeDepartment.setSelected(false);

			}
		});

		btnAddNewDepartmentSave.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {

					if (tfDepartmentName.getText().isBlank()) {
						throw new Exception("Cant leae text field empty");
					} else if (!(rbAllchangeDepartment.isSelected()) && !(rbFreeChangeDepartment.isSelected())
							&& !(rbNoChangeDepartment.isSelected())) {
						throw new Exception("You need to chose a role");
					} else {
						DepartmentWorkingPolicies policie = null;

						if (rbAllchangeDepartment.isSelected()) {
							policie = DepartmentWorkingPolicies.AllChange;
						} else if (rbFreeChangeDepartment.isSelected()) {
							policie = DepartmentWorkingPolicies.FreeChange;
						} else if (rbNoChangeDepartment.isSelected()) {
							policie = DepartmentWorkingPolicies.NoChange;
						}
						FireNewDeparmenttoModel(tfDepartmentName.getText(), policie);
						gpMenu.setVisible(true);
						gpAddNewDepartment.setVisible(false);
						tfDepartmentName.clear();
						rbAllchangeDepartment.setSelected(false);
						rbNoChangeDepartment.setSelected(false);
						rbFreeChangeDepartment.setSelected(false);

					}
				} catch (Exception e) {
					Alert theAlert = new Alert(AlertType.ERROR);
					theAlert.setContentText("please check again");
					theAlert.setHeaderText(e.getMessage());
					theAlert.show();
				}
			}
		});

		// Show Department Workers
		GridPane gpShowDepartmentWorkers = new GridPane();
		gpShowDepartmentWorkers.setVisible(false);
		gpShowDepartmentWorkers.setPadding(new Insets(10));
		gpShowDepartmentWorkers.setVgap(10);
		gpShowDepartmentWorkers.setHgap(10);
		Label lblChooseDepartment = new Label("Choose which Department to show:");
//		lblChooseDepartment.setStyle("-fx-text-fill: white;-fx-font-size: 1.5em;");
		HBox hbShowDepatmentWorkerListButtons = new HBox();
		hbShowDepatmentWorkerListButtons.setSpacing(20);
		Button btnShowDepartmentWorkersBack = new Button("Back");
//		btnShowDepartmentWorkersBack.setStyle("-fx-font-size: 1.5em;");
		Button btnShowDepartmentWorkerListShow = new Button("Show");
//		btnShowDepartmentWorkerListShow.setStyle("-fx-font-size: 1.5em;");
		hbShowDepatmentWorkerListButtons.getChildren().addAll(btnShowDepartmentWorkersBack,
				btnShowDepartmentWorkerListShow);
		taShowDepartmentWorkers.setWrapText(true); // here\/
//		taShowDepartmentWorkers.setStyle("-fx-font-size: 1.5em;");
		gpShowDepartmentWorkers.add(lblChooseDepartment, 0, 0);
		gpShowDepartmentWorkers.add(cmbShowDepatmentDepartmentNames, 0, 1);
		gpShowDepartmentWorkers.add(taShowDepartmentWorkers, 0, 2);
		gpShowDepartmentWorkers.add(hbShowDepatmentWorkerListButtons, 0, 10);

		btnShowDepartmentWorkersBack.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				taShowDepartmentWorkers.clear();
				gpShowDepartmentWorkers.setVisible(false);
				gpMenu.setVisible(true);

			}
		});

		btnShowDepartmentWorkerListShow.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					if (cmbShowDepatmentDepartmentNames.getValue().equalsIgnoreCase("Choose Department")) {
						throw new Exception("You need to choose a depratment");
					} else {
						FireShowDepartmentWorkersListFromModel(cmbShowDepatmentDepartmentNames.getValue().toString());
					}
				} catch (Exception e) {
					Alert theAlert = new Alert(AlertType.ERROR);
					theAlert.setContentText("please check again");
					theAlert.setHeaderText(e.getMessage());
					theAlert.show();
				}
			}
		});

// Changing work policy based on roles gridpane in department:
		GridPane gpRolePolicyChangeDepartment = new GridPane();
		gpRolePolicyChangeDepartment.setVisible(false);
		gpRolePolicyChangeDepartment.setPadding(new Insets(10));
		gpRolePolicyChangeDepartment.setVgap(10);
		gpRolePolicyChangeDepartment.setHgap(10);
		Label lblChooseDepartmenttochange = new Label("Choose which Department to Change:");
//		lblChooseDepartmenttochange.setStyle("-fx-text-fill: white;-fx-font-size: 1.5em;");
		Label lblRole = new Label("Choose which role to change:");
//		lblRole.setStyle("-fx-text-fill: white; -fx-font-size: 1.5em;");
		TextField tfDepartmentRoleTochange = new TextField();
		Label lblAddPreference = new Label("Preference:");
		ComboBox<String> cmbPrefrences = new ComboBox<String>();
		cmbPrefrences.getItems().addAll("Earlier", "Later", "Regular", "Home");
		cmbPrefrences.setValue("Regular");
		HBox hbRolePolicyChangeDepartmentButtons = new HBox();
		hbRolePolicyChangeDepartmentButtons.setSpacing(20);
		Button btnRolePolicyChangeDepartmentSave = new Button("Save");
		Button btnRolePolicyChangeDepartmentBack = new Button("Back");
		hbRolePolicyChangeDepartmentButtons.getChildren().addAll(btnRolePolicyChangeDepartmentBack,
				btnRolePolicyChangeDepartmentSave);

		gpRolePolicyChangeDepartment.add(lblChooseDepartmenttochange, 0, 0);
		gpRolePolicyChangeDepartment.add(cmbChangingWorkPolicyBasedOnRolesInDepartmentDepartmentNames, 0, 1);
		gpRolePolicyChangeDepartment.add(lblRole, 0, 2);
		gpRolePolicyChangeDepartment.add(tfDepartmentRoleTochange, 0, 3);
		gpRolePolicyChangeDepartment.add(cmbPrefrences, 0, 4);
		gpRolePolicyChangeDepartment.add(hbRolePolicyChangeDepartmentButtons, 0, 5);

		btnRolePolicyChangeDepartmentSave.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					if (cmbChangingWorkPolicyBasedOnRolesInDepartmentDepartmentNames.getValue()
							.equalsIgnoreCase("Choose Department")) {
						throw new Exception("You need to choose a department");

					} else if (tfDepartmentRoleTochange.getText().isBlank()) {
						throw new Exception("Cant leave text field empty");
					} else {

						WorkdayPreferences Prefrence = null;

						if (cmbPrefrences.getValue().toString().equalsIgnoreCase("Earlier")) {
							Prefrence = WorkdayPreferences.Earlier;
						} else if (cmbPrefrences.getValue().toString().equalsIgnoreCase("Later")) {
							Prefrence = WorkdayPreferences.Later;
						} else if (cmbPrefrences.getValue().toString().equalsIgnoreCase("Regular")) {
							Prefrence = WorkdayPreferences.Regular;
						} else {
							Prefrence = WorkdayPreferences.Home;
						}
						FireRoleChangeCalcToModel(Prefrence,
								cmbChangingWorkPolicyBasedOnRolesInDepartmentDepartmentNames.getValue().toString(),
								tfDepartmentRoleTochange.getText());
						gpRolePolicyChangeDepartment.setVisible(false);
						gpMenu.setVisible(true);
						tfDepartmentRoleTochange.clear();
					}
				} catch (Exception e) {
					Alert theAlert = new Alert(AlertType.ERROR);
					theAlert.setContentText("please check again");
					theAlert.setHeaderText(e.getMessage());
					theAlert.show();
				}
			}
		});

		btnRolePolicyChangeDepartmentBack.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				gpRolePolicyChangeDepartment.setVisible(false);
				gpMenu.setVisible(true);

			}
		});

// change work policy based on department name
		GridPane gpWorkPolicyChangeDepartment = new GridPane();
		gpWorkPolicyChangeDepartment.setVisible(false);
		gpWorkPolicyChangeDepartment.setPadding(new Insets(10));
		gpWorkPolicyChangeDepartment.setVgap(10);
		gpWorkPolicyChangeDepartment.setHgap(10);
		Label lblChooseWhichDepartmenttochange = new Label("Choose which Department to Change:");
//		lblChooseWhichDepartmenttochange.setStyle("-fx-text-fill: white;-fx-font-size: 1.5em;");

		Label lblNewPolicy = new Label("Preference:");
		ComboBox<String> cmbNewPolicy = new ComboBox<String>();
		cmbNewPolicy.getItems().addAll("Earlier", "Later", "Regular", "Home");
		cmbNewPolicy.setValue("Regular");

		HBox hbWorkPolicyChangeDepartment = new HBox();
		hbWorkPolicyChangeDepartment.setSpacing(20);
		Button btnWorkPolicyChangeDepartmentSave = new Button("Save");
//		btnWorkPolicyChangeDepartmentSave.setStyle("-fx-font-size: 1.5em;");
		Button btnWorkPolicyChangeDepartmentBack = new Button("Back");

		hbWorkPolicyChangeDepartment.getChildren().addAll(btnWorkPolicyChangeDepartmentBack,
				btnWorkPolicyChangeDepartmentSave);

		gpWorkPolicyChangeDepartment.add(lblChooseWhichDepartmenttochange, 0, 0);
		gpWorkPolicyChangeDepartment.add(cmbChangeWorkPolicyBasedOnDepartmentNameDepartmentNames, 0, 1);
		gpWorkPolicyChangeDepartment.add(lblNewPolicy, 0, 2);
		gpWorkPolicyChangeDepartment.add(cmbNewPolicy, 0, 3);
		gpWorkPolicyChangeDepartment.add(hbWorkPolicyChangeDepartment, 0, 4);

		btnWorkPolicyChangeDepartmentSave.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {

					if (cmbChangeWorkPolicyBasedOnDepartmentNameDepartmentNames.getValue()
							.equalsIgnoreCase("Choose Department")) {
						throw new Exception("You need to choose a department");

					} else {
						WorkdayPreferences TheChosenPreference = null;
						if (cmbPrefrences.getValue().toString().equalsIgnoreCase("Earlier")) {
							TheChosenPreference = WorkdayPreferences.Earlier;
						} else if (cmbPrefrences.getValue().toString().equalsIgnoreCase("Later")) {
							TheChosenPreference = WorkdayPreferences.Later;
						} else if (cmbPrefrences.getValue().toString().equalsIgnoreCase("Regular")) {
							TheChosenPreference = WorkdayPreferences.Regular;
						} else {
							TheChosenPreference = WorkdayPreferences.Home;
						}
						FireProfitCalculationToModel(TheChosenPreference,
								cmbChangeWorkPolicyBasedOnDepartmentNameDepartmentNames.getValue().toString());
						gpWorkPolicyChangeDepartment.setVisible(false);
						gpMenu.setVisible(true);

					}
				} catch (Exception e) {
					Alert theAlert = new Alert(AlertType.ERROR);
					theAlert.setContentText("please check again");
					theAlert.setHeaderText(e.getMessage());
					theAlert.show();
				}
			}
		});

		btnWorkPolicyChangeDepartmentBack.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				gpWorkPolicyChangeDepartment.setVisible(false);
				gpMenu.setVisible(true);

			}
		});

// gridpane change workpolicy for entire comapany based on role
		GridPane gpRolePolicyChangeCompany = new GridPane();
		gpRolePolicyChangeCompany.setVisible(false);
		gpRolePolicyChangeCompany.setPadding(new Insets(10));
		gpRolePolicyChangeCompany.setVgap(10);
		gpRolePolicyChangeCompany.setHgap(10);
		Label lblRoleChange = new Label("Choose which role to change:");
		TextField tfCompanyRoleTochange = new TextField();
		Label lblNewCompanyPolicy = new Label("Preference:");
		ComboBox<String> cmbNewCompanyPolicy = new ComboBox<String>();
		cmbNewCompanyPolicy.getItems().addAll("Earlier", "Later", "Regular", "Home");
		cmbNewCompanyPolicy.setValue("Regular");

		HBox hbRolePolicyChangeCompany = new HBox();
		hbRolePolicyChangeCompany.setSpacing(20);
		Button btnRolePolicyChangeCompanySave = new Button("Save");
//		btnWorkPolicyChangeDepartmentSave.setStyle("-fx-font-size: 1.5em;");
		Button btnRolePolicyChangeCompanyBack = new Button("Back");
		hbRolePolicyChangeCompany.getChildren().addAll(btnRolePolicyChangeCompanyBack, btnRolePolicyChangeCompanySave);

		gpRolePolicyChangeCompany.add(lblRoleChange, 0, 0);
		gpRolePolicyChangeCompany.add(tfCompanyRoleTochange, 0, 1);
		gpRolePolicyChangeCompany.add(lblNewCompanyPolicy, 0, 2);
		gpRolePolicyChangeCompany.add(cmbNewCompanyPolicy, 0, 3);
		gpRolePolicyChangeCompany.add(hbRolePolicyChangeCompany, 0, 4);

		btnRolePolicyChangeCompanySave.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					if (tfCompanyRoleTochange.getText().isBlank()) {
						throw new Exception("Cant leave text field empty");
					} else {
						WorkdayPreferences ChosenPreference = null;
						if (cmbPrefrences.getValue().toString().equalsIgnoreCase("Earlier")) {
							ChosenPreference = WorkdayPreferences.Earlier;
						} else if (cmbPrefrences.getValue().toString().equalsIgnoreCase("Later")) {
							ChosenPreference = WorkdayPreferences.Later;
						} else if (cmbPrefrences.getValue().toString().equalsIgnoreCase("Regular")) {
							ChosenPreference = WorkdayPreferences.Regular;
						} else {
							ChosenPreference = WorkdayPreferences.Home;
						}
						CompanyRoleChangeProfitToModel(ChosenPreference, tfCompanyRoleTochange.getText());
						gpRolePolicyChangeCompany.setVisible(false);
						gpMenu.setVisible(true);
						tfCompanyRoleTochange.clear();

					}
				} catch (Exception e) {
					Alert theAlert = new Alert(AlertType.ERROR);
					theAlert.setContentText("please check again");
					theAlert.setHeaderText(e.getMessage());
					theAlert.show();
				}
			}
		});

		btnRolePolicyChangeCompanyBack.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				gpRolePolicyChangeCompany.setVisible(false);
				gpMenu.setVisible(true);

			}
		});

		// grid pane show company:
		GridPane gpShowCompany = new GridPane();
		gpShowCompany.setVisible(false);
		gpShowCompany.setPadding(new Insets(10));
		gpShowCompany.setVgap(10);
		gpShowCompany.setHgap(10);
		Button btnShowCompanyBack = new Button("Back");

		btnShowCompanyBack.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				gpShowCompany.setVisible(false);
				gpMenu.setVisible(true);

			}
		});

		gpShowCompany.add(taShowComany, 0, 0);
		gpShowCompany.add(btnShowCompanyBack, 0, 5);

		// GridPane profit:
		GridPane gpProfit = new GridPane();
		gpProfit.setVisible(false);
		gpProfit.setPadding(new Insets(10));
		gpProfit.setVgap(10);
		gpProfit.setHgap(10);

		// profite menu pane:
		GridPane gpProfitStart = new GridPane();
		gpProfitStart.setPadding(new Insets(10));
		gpProfitStart.setVgap(10);
		gpProfitStart.setHgap(10);

		Label lblProfitStartExplantion = new Label("enter payments:");
		Label lblProfiteStartMonthlyPay = new Label("Montly pay");
		TextField tfProfiteStartMonthlyPlay = new TextField();
		Label lblProfiteStartHourly = new Label("Hourly pay");
		TextField tfProfiteStartHourlyPay = new TextField();
		Label lblProfitStartSalesAmount = new Label("Sales amount");
		TextField tfProfitStartSaelsAmaount = new TextField();
		Label lblProfitStartSalesProsentage = new Label("Sales prosentage");
		TextField tfProfitStartSalesProsentage = new TextField();
		HBox hbProfitStartButtons = new HBox();
		hbProfitStartButtons.setSpacing(20);
		Button btnProfiteStartBack = new Button("Back");
		Button btnProfitStartCal = new Button("Calculate");
		hbProfitStartButtons.getChildren().addAll(btnProfiteStartBack, btnProfitStartCal);

		gpProfitStart.add(lblProfitStartExplantion, 0, 0);
		gpProfitStart.add(lblProfiteStartMonthlyPay, 0, 1);
		gpProfitStart.add(tfProfiteStartMonthlyPlay, 0, 2);
		gpProfitStart.add(lblProfiteStartHourly, 1, 1);
		gpProfitStart.add(tfProfiteStartHourlyPay, 1, 2);
		gpProfitStart.add(lblProfitStartSalesAmount, 2, 1);
		gpProfitStart.add(tfProfitStartSaelsAmaount, 2, 2);
		gpProfitStart.add(lblProfitStartSalesProsentage, 3, 1);
		gpProfitStart.add(tfProfitStartSalesProsentage, 3, 2);
		gpProfitStart.add(hbProfitStartButtons, 1, 4, 2, 1);

		btnProfiteStartBack.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				tfProfiteStartMonthlyPlay.clear();
				tfProfiteStartHourlyPay.clear();
				tfProfitStartSaelsAmaount.clear();
				tfProfitStartSalesProsentage.clear();
				gpProfit.setVisible(false);
				gpMenu.setVisible(true);

			}
		});

		// profit menu:
		GridPane gpProfitMenu = new GridPane();
		gpProfitMenu.setVisible(false);
		gpProfitMenu.setPadding(new Insets(10));
		gpProfitMenu.setVgap(10);
		gpProfitMenu.setHgap(10);

		Label lblProfitMenu = new Label("which profit do you want to see?");
		HBox hbProfitMenuButtons = new HBox();
		hbProfitMenuButtons.setSpacing(20);
		Button btnProfitMenuCompany = new Button("Company");
		Button btnProfitMenuDepartment = new Button("Department");
		Button btnProfitMenuWorker = new Button("Worker");
		hbProfitMenuButtons.getChildren().addAll(btnProfitMenuCompany, btnProfitMenuDepartment, btnProfitMenuWorker);
		Button btnProfitMenuBack = new Button("Back");

		btnProfitMenuBack.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				gpProfitMenu.setVisible(false);
				gpProfitStart.setVisible(true);

			}
		});

		btnProfitStartCal.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					if (tfProfiteStartMonthlyPlay.getText().isBlank() || tfProfiteStartHourlyPay.getText().isBlank()
							|| tfProfitStartSaelsAmaount.getText().isBlank()
							|| tfProfitStartSalesProsentage.getText().isBlank()) {
						throw new Exception("Cant leave text fields empty");
					} else {
						gpProfitStart.setVisible(false);
						gpProfitMenu.setVisible(true);
						fireGetWorkersProfitFromModel(Double.parseDouble(tfProfiteStartMonthlyPlay.getText()),
								Double.parseDouble(tfProfiteStartHourlyPay.getText()),
								Double.parseDouble(tfProfitStartSaelsAmaount.getText()));
					}
				} catch (Exception e) {
					Alert theAlert = new Alert(AlertType.ERROR);
					theAlert.setContentText("please check again");
					theAlert.setHeaderText(e.getMessage());
					theAlert.show();
				}
			}
		});

		gpProfitMenu.add(lblProfitMenu, 0, 0);
		gpProfitMenu.add(hbProfitMenuButtons, 0, 1, 3, 1);
		gpProfitMenu.add(btnProfitMenuBack, 0, 4);

		// profit company pane:
		GridPane gpProfitCompany = new GridPane();
		gpProfitCompany.setVisible(false);
		gpProfitCompany.setPadding(new Insets(10));
		gpProfitCompany.setVgap(10);
		gpProfitCompany.setHgap(10);

		Button btnProfitCompanyBack = new Button("Back");

		gpProfitCompany.add(lblProfitComany, 0, 0);
		gpProfitCompany.add(btnProfitCompanyBack, 0, 4);

		btnProfitCompanyBack.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				gpProfitCompany.setVisible(false);
				gpProfitMenu.setVisible(true);
				lblProfitComany.setText("");

			}
		});

		btnProfitMenuCompany.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				gpProfitMenu.setVisible(false);
				gpProfitCompany.setVisible(true);
				fireGetCompanyProfitFromModel();

			}
		});

		// profit depatments:
		GridPane gpProfitDepartments = new GridPane();
		gpProfitDepartments.setVisible(false);
		gpProfitDepartments.setPadding(new Insets(10));
		gpProfitDepartments.setVgap(10);
		gpProfitDepartments.setHgap(10);

		Label lblProfitDepartments = new Label("which departments would you like to see?");
		Button btnProfitDepartmentsBack = new Button("Back");
		Button btnProfitDepartmentsShow = new Button("Show");

		gpProfitDepartments.add(lblProfitDepartments, 0, 0);
		gpProfitDepartments.add(lblProfitDepatments, 1, 1);
		gpProfitDepartments.add(cmbProfitDepartmentNames, 0, 1);
		gpProfitDepartments.add(btnProfitDepartmentsShow, 0, 2);
		gpProfitDepartments.add(btnProfitDepartmentsBack, 0, 4);

		btnProfitDepartmentsBack.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				gpProfitDepartments.setVisible(false);
				gpProfitMenu.setVisible(true);

			}
		});

		btnProfitMenuDepartment.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				fireGetDepatmentsNamesFromModel();
				gpProfitDepartments.setVisible(true);
				gpProfitMenu.setVisible(false);

			}
		});

		btnProfitDepartmentsShow.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					if (cmbProfitDepartmentNames.getValue().equalsIgnoreCase("Choose Department")) {
						throw new Exception("You need to choose a department");
					} else {
						fireGetDepatmentProfitFromModel(cmbProfitDepartmentNames.getValue());
					}
				} catch (Exception e) {
					Alert theAlert = new Alert(AlertType.ERROR);
					theAlert.setContentText("please check again");
					theAlert.setHeaderText(e.getMessage());
					theAlert.show();
				}
			}
		});

		// profit worker pane:
		GridPane gpProfitWorkers = new GridPane();
		gpProfitWorkers.setVisible(false);
		gpProfitWorkers.setPadding(new Insets(10));
		gpProfitWorkers.setVgap(10);
		gpProfitWorkers.setHgap(10);

		Button btnProfitWorkersBack = new Button("Back");

		gpProfitWorkers.add(taWorkersProfit, 0, 0);
		gpProfitWorkers.add(btnProfitWorkersBack, 0, 1);

		btnProfitWorkersBack.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				gpProfitWorkers.setVisible(false);
				gpProfitMenu.setVisible(true);

			}
		});

		btnProfitMenuWorker.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				gpProfitWorkers.setVisible(true);
				gpProfitMenu.setVisible(false);

			}
		});

		// profite pane adds:
		gpProfit.add(gpProfitStart, 0, 0);
		gpProfit.add(gpProfitMenu, 0, 0);
		gpProfit.add(gpProfitCompany, 0, 0);
		gpProfit.add(gpProfitDepartments, 0, 0);
		gpProfit.add(gpProfitWorkers, 0, 0);

		// menu buttons:
		rb1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				fireGetDepatmentsNamesFromModel();
				gpMenu.setVisible(false);
				rb1.setSelected(false);
				gpAddNewWorker.setVisible(true);

			}
		});

		rb2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				gpMenu.setVisible(false);
				rb2.setSelected(false);
				gpAddNewDepartment.setVisible(true);

			}
		});

		rb3.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				fireGetDepatmentsNamesFromModel();
				gpMenu.setVisible(false);
				rb3.setSelected(false);
				gpShowDepartmentWorkers.setVisible(true);

			}
		});

		rb4.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				gpMenu.setVisible(false);
				rb4.setSelected(false);
				gpShowCompany.setVisible(true);
				FirePrintCompanyFromModel();

			}
		});

		rb5.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				fireGetDepatmentsNamesFromModel();
				gpMenu.setVisible(false);
				rb5.setSelected(false);
				gpRolePolicyChangeDepartment.setVisible(true);

			}
		});

		rb6.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				fireGetDepatmentsNamesFromModel();
				gpMenu.setVisible(false);
				rb6.setSelected(false);
				gpWorkPolicyChangeDepartment.setVisible(true);

			}
		});

		rb7.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				fireGetDepatmentsNamesFromModel();
				gpMenu.setVisible(false);
				rb7.setSelected(false);

				gpRolePolicyChangeCompany.setVisible(true);

			}
		});

		rb8.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				gpMenu.setVisible(false);
				rb8.setSelected(false);
				gpProfit.setVisible(true);

			}
		});

		rb9.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Platform.exit();
			}
		});

		// adding GridPanes to main gpRoot:
		gpRoot.add(gpMenu, 5, 0, 20, 20);
		gpRoot.add(gpAddNewWorker, 5, 0, 20, 20);
		gpRoot.add(gpAddNewDepartment, 5, 0, 20, 20);
		gpRoot.add(gpShowDepartmentWorkers, 5, 0, 20, 20);
		gpRoot.add(gpShowCompany, 5, 0, 20, 20);
		gpRoot.add(gpRolePolicyChangeDepartment, 5, 0, 20, 20);
		gpRoot.add(gpRolePolicyChangeCompany, 5, 0, 20, 20);
		gpRoot.add(gpWorkPolicyChangeDepartment, 5, 0, 20, 20);
		gpRoot.add(gpProfit, 5, 0, 20, 20);

		theStage.setScene(new Scene(gpRoot, 600, 500));
		theStage.show();

	}

	public void setShowDepartmentWorkersList(String temp) {
		taShowDepartmentWorkers.clear();
		taShowDepartmentWorkers.setText(temp);

	}

	public void setPrintCompany(String temp) {
		taShowComany.setText(temp);
	}

	public void showMessegeFromModel(String temp) {
		JOptionPane.showMessageDialog(null, temp);
	}

	public void setDepatmentsNamesListToCmb(String[] names) {
		cmbAddWorkerDepartmentNames.getItems().clear();
		cmbAddWorkerDepartmentNames.setValue("Choose Department");
		for (int i = 0; i < names.length; i++) {
			cmbAddWorkerDepartmentNames.getItems().add(names[i]);
		}

		cmbShowDepatmentDepartmentNames.getItems().clear();
		cmbShowDepatmentDepartmentNames.setValue("Choose Department");
		for (int i = 0; i < names.length; i++) {
			cmbShowDepatmentDepartmentNames.getItems().add(names[i]);
		}

		cmbChangingWorkPolicyBasedOnRolesInDepartmentDepartmentNames.getItems().clear();
		cmbChangingWorkPolicyBasedOnRolesInDepartmentDepartmentNames.setValue("Choose Department");
		for (int i = 0; i < names.length; i++) {
			cmbChangingWorkPolicyBasedOnRolesInDepartmentDepartmentNames.getItems().add(names[i]);
		}

		cmbChangeWorkPolicyBasedOnDepartmentNameDepartmentNames.getItems().clear();
		cmbChangeWorkPolicyBasedOnDepartmentNameDepartmentNames.setValue("Choose Department");
		for (int i = 0; i < names.length; i++) {
			cmbChangeWorkPolicyBasedOnDepartmentNameDepartmentNames.getItems().add(names[i]);
		}

		cmbProfitDepartmentNames.getItems().clear();
		cmbProfitDepartmentNames.setValue("Choose Department");
		for (int i = 0; i < names.length; i++) {
			cmbProfitDepartmentNames.getItems().add(names[i]);
		}
	}

	public void setWorkerProfit(String temp) {
		taWorkersProfit.setText(temp);
	}

	public void setDepatmentProfits(String temp) {
		lblProfitDepatments.setText("");
		lblProfitDepatments.setText(temp);
	}

	public void setCompanyProfit(String temp) {
		lblProfitComany.setText("");
		lblProfitComany.setText(temp);
	}

	// listeners:
	@Override
	public void register(CompanyViewEventListener newCompanyViewEventListener) {
		allListeners.add(newCompanyViewEventListener);

	}

	@Override
	public void FireNewWorkerToModel(String name, WorkdayPreferences TheworkdayPreferences, String Therole,
			WorkdayPreferences ThecurrentWorkdayPolicy, String Departmentnames, SalaryType TheSalaryType) {
		for (CompanyViewEventListener l : allListeners) {
			l.FireNewWorkerToModel(name, TheworkdayPreferences, Therole, ThecurrentWorkdayPolicy, Departmentnames,
					TheSalaryType);

		}
	}

	@Override
	public void FireNewDeparmenttoModel(String NewDepartmentname, DepartmentWorkingPolicies WorkingPolicy) {
		for (CompanyViewEventListener l : allListeners) {
			l.FireNewDeparmenttoModel(NewDepartmentname, WorkingPolicy);
		}

	}

	@Override
	public void FirePrintCompanyFromModel() {
		for (CompanyViewEventListener l : allListeners) {
			l.FirePrintCompanyFromModel();
		}

	}

	public void FireShowDepartmentWorkersListFromModel(String departmentName) {
		for (CompanyViewEventListener l : allListeners) {
			l.FireShowDepartmentWorkersListFromModel(departmentName);
		}

	}

	public void fireGetDepatmentsNamesFromModel() {
		for (CompanyViewEventListener l : allListeners) {
			l.fireGetDepatmentsNamesFromModel();
		}
	}

	public void FireRoleChangeCalcToModel(WorkdayPreferences ThePreference, String theDepartmentname, String Therole) {
		for (CompanyViewEventListener l : allListeners) {
			l.FireRoleChangeCalcToModel(ThePreference, theDepartmentname, Therole);
		}
	}

	public void CompanyRoleChangeProfitToModel(WorkdayPreferences ThePreference, String Therole) {
		for (CompanyViewEventListener l : allListeners) {
			l.FireCompanyRoleChangeProfitToModel(ThePreference, Therole);
		}
	}

	public void FireProfitCalculationToModel(WorkdayPreferences ThePreference, String TheDepartmentName) {
		for (CompanyViewEventListener l : allListeners) {
			l.FireProfitCalculationToModel(ThePreference, TheDepartmentName);
		}
	}

	public void fireGetWorkersProfitFromModel(double MonthlySalary, double HourlySalary, double MonthlySales) {
		for (CompanyViewEventListener l : allListeners) {
			l.fireGetWorkersProfitFromModel(MonthlySalary, HourlySalary, MonthlySales);
		}
	}

	public void fireGetDepatmentProfitFromModel(String temp) {
		for (CompanyViewEventListener l : allListeners) {
			l.fireGetDepatmentProfitFromModel(temp);
		}
	}

	public void fireGetCompanyProfitFromModel() {
		for (CompanyViewEventListener l : allListeners) {
			l.fireGetCompanyProfitFromModel();
		}
	}
}
