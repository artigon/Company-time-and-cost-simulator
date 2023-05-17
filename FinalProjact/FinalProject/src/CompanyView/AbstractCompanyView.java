package CompanyView;

import java.util.Vector;

import CompanyListeners.CompanyViewEventListener;
import CompanyModel.Departments;
import CompanyModel.Departments.DepartmentWorkingPolicies;
import CompanyModel.Workers.SalaryType;
import CompanyModel.Workers.WorkdayPreferences;

public interface AbstractCompanyView {

	void register(CompanyViewEventListener newCompanyViewEventListener);

	void FireNewWorkerToModel(String name, WorkdayPreferences TheworkdayPreferences, String Therole,
			WorkdayPreferences ThecurrentWorkdayPolicy, String Departmentnames, SalaryType TheSalaryType);

	void FireNewDeparmenttoModel(String NewDepartmentname,  DepartmentWorkingPolicies WorkingPolicy);

	void FirePrintCompanyFromModel();

	void FireShowDepartmentWorkersListFromModel(String departmentName);

	void setShowDepartmentWorkersList(String temp);

	void setPrintCompany(String temp);

	void showMessegeFromModel(String temp);

	void setDepatmentsNamesListToCmb(String[] names);

	void setWorkerProfit(String temp);

	void setDepatmentProfits(String temp);
	
	void setCompanyProfit(String temp);

}
