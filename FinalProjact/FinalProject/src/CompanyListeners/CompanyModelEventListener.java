package CompanyListeners;

import java.util.Vector;

import CompanyModel.CompanySimulation;
import CompanyModel.Departments;
import CompanyModel.Workers;
import CompanyModel.Departments.DepartmentWorkingPolicies;
import CompanyModel.Workers.SalaryType;
import CompanyModel.Workers.WorkdayPreferences;

public interface CompanyModelEventListener {

	void addWorkertoDeparment(String name, WorkdayPreferences TheworkdayPreferences, String Therole,
			WorkdayPreferences ThecurrentWorkdayPolicy, String Departmentnames, SalaryType TheSalaryType);

	void addDepartment(String NewDepartmentname,  DepartmentWorkingPolicies WorkingPolicy);

	Vector<Departments> getDepartmentsList();

	Vector<Workers> getWorkersListforDepartment();

	void hardCodeInput();

	void CompanyDataSaving(CompanyModelEventListener theElectionSquence);

	void fireShowDepartmentWorkersListToUI(String temp);

	void firePrintCompanyToUI(String temp);

	void fireNewCompanySimulationForController(CompanySimulation theSimulation);

	void sendMessegeToUI(String temp);

	void fireDepartmentsNamesListToUI(String[] names);

	void fireDepatmentProfirToUI(String profit);
	
	void fireWorkerProfitToUI(String temp);
	
	void fireCompanyProfitToUI(String temp);

}
