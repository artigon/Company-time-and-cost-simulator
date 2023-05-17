package CompanyListeners;

import CompanyModel.Departments;
import CompanyModel.Departments.DepartmentWorkingPolicies;
import CompanyModel.Workers.SalaryType;
import CompanyModel.Workers.WorkdayPreferences;

public interface CompanyViewEventListener {
	void register(CompanyViewEventListener newCompanyViewEventListener);

	void FireShowDepartmentWorkersListFromModel(String departmentName);

	void FirePrintCompanyFromModel();

	void FireNewWorkerToModel(String name, WorkdayPreferences TheworkdayPreferences, String Therole,
			WorkdayPreferences ThecurrentWorkdayPolicy, String Departmentnames, SalaryType TheSalaryType);
	
	void FireNewDeparmenttoModel(String NewDepartmentname,
			DepartmentWorkingPolicies WorkingPolicy);
	
	void fireGetDepatmentsNamesFromModel();
	
	void FireRoleChangeCalcToModel(WorkdayPreferences ThePreference, String theDepartmentname, String Therole) ;
	
	void FireCompanyRoleChangeProfitToModel(WorkdayPreferences ThePreference, String Therole);
		
	void FireProfitCalculationToModel(WorkdayPreferences ThePreference, String TheDepartmentName);
	
	 void fireGetWorkersProfitFromModel(double MonthlySalary, double HourlySalary, double MonthlySales);
	 
	 void fireGetDepatmentProfitFromModel(String temp);
	 
	 void fireGetCompanyProfitFromModel();
}
