package CompanyMain;

import CompanyController.CompanyControllers;
import CompanyModel.CompanySimulation;
import CompanyView.AbstractCompanyView;
import CompanyView.CompanyViewUI;
import javafx.application.Application;
import javafx.stage.Stage;


public class CompanyMain extends Application{
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage theStage) throws Exception {
		CompanySimulation theModel = new CompanySimulation();
		AbstractCompanyView theView =  new CompanyViewUI(theStage) ;
		CompanyControllers theController = new CompanyControllers(theModel,theView);
	}
}