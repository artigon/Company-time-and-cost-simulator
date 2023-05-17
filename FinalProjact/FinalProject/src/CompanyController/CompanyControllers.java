package CompanyController;

import java.io.Serializable;
import java.util.Vector;

import CompanyListeners.CompanyModelEventListener;
import CompanyListeners.CompanyViewEventListener;
import CompanyModel.CompanySimulation;
import CompanyModel.Departments;
import CompanyModel.Departments.DepartmentWorkingPolicies;
import CompanyModel.Workers;
import CompanyModel.Workers.SalaryType;
import CompanyModel.Workers.WorkdayPreferences;
import CompanyView.AbstractCompanyView;

public class CompanyControllers implements Serializable, CompanyModelEventListener, CompanyViewEventListener {
	private CompanySimulation theModel;
	private AbstractCompanyView theView;

	public CompanyControllers(CompanySimulation theModel, AbstractCompanyView theView) {
		super();
		this.theModel = theModel;
		this.theView = theView;

		this.theModel.register(this);
		this.theView.register(this);

	}

	@Override
	public void register(CompanyViewEventListener newCompanyViewEventListener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addWorkertoDeparment(String name, WorkdayPreferences TheworkdayPreferences, String Therole,
			WorkdayPreferences ThecurrentWorkdayPolicy, String Departmentnames,SalaryType TheSalaryType) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addDepartment(String NewDepartmentname,  DepartmentWorkingPolicies WorkingPolicy) {
		// TODO Auto-generated method stub

	}

	@Override
	public Vector<Departments> getDepartmentsList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Workers> getWorkersListforDepartment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void hardCodeInput() {
		// TODO Auto-generated method stub

	}

	@Override
	public void CompanyDataSaving(CompanyModelEventListener theElectionSquence) {
		// TODO Auto-generated method stub

	}

	@Override
	public void fireShowDepartmentWorkersListToUI(String temp) {
		theView.setShowDepartmentWorkersList(temp);

	}

	@Override
	public void firePrintCompanyToUI(String temp) {
		theView.setPrintCompany(temp);

	}

	@Override
	public void fireNewCompanySimulationForController(CompanySimulation theSimulation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void FireShowDepartmentWorkersListFromModel(String departmentName) {
		theModel.ShowDepartmentWorkersList(departmentName);

	}

	@Override
	public void FirePrintCompanyFromModel() {
		theModel.PrintCompany();

	}


	@Override
	public void FireNewDeparmenttoModel(String NewDepartmentname, 
			DepartmentWorkingPolicies WorkingPolicy) {
		theModel.addDepartment(NewDepartmentname,  WorkingPolicy);
		
	}

	@Override
	public void sendMessegeToUI(String temp) {
		theView.showMessegeFromModel(temp);
		
	}

	@Override
	public void FireNewWorkerToModel(String name, WorkdayPreferences TheworkdayPreferences, String Therole,
			WorkdayPreferences ThecurrentWorkdayPolicy, String Departmentnames, SalaryType TheSalaryType) {
		theModel.addWorkertoDeparment(name, TheworkdayPreferences, Therole, ThecurrentWorkdayPolicy, Departmentnames, TheSalaryType);
		
	}

	@Override
	public void fireGetDepatmentsNamesFromModel() {
		theModel.getDepartmentNamesList();
		
	}

	@Override
	public void fireDepartmentsNamesListToUI(String[] names) {
		theView.setDepatmentsNamesListToCmb(names);
		
	}

	@Override
	public void FireRoleChangeCalcToModel(WorkdayPreferences ThePreference, String theDepartmentName, String Therole) {
		theModel.RoleChangeCalc(ThePreference, theDepartmentName, Therole);
	}

	@Override
	public void FireCompanyRoleChangeProfitToModel(WorkdayPreferences ThePreference, String Therole) {
		theModel.CompanyRoleChangeProfit(ThePreference, Therole);
		
	}

	@Override
	public void FireProfitCalculationToModel(WorkdayPreferences ThePreference, String TheDepartmentName) {
		theModel.profit( ThePreference,TheDepartmentName);		
	}

	@Override
	public void fireDepatmentProfirToUI(String profit) {
		theView.setDepatmentProfits(profit);
		
	}

	@Override
	public void fireGetWorkersProfitFromModel(double MonthlySalary, double HourlySalary, double MonthlySales) {
		theModel.GetWorkersProfit(MonthlySalary, HourlySalary, MonthlySales);
		
	}

	@Override
	public void fireWorkerProfitToUI(String temp) {
		theView.setWorkerProfit(temp);
		
	}

	@Override
	public void fireGetDepatmentProfitFromModel(String temp) {
		theModel.getDepatmentProfit(temp);
		
	}

	@Override
	public void fireGetCompanyProfitFromModel() {
		theModel.CompanyProfitCalculator();
		
	}

	@Override
	public void fireCompanyProfitToUI(String temp) {
		theView.setCompanyProfit(temp);
		
	}
	
	

}