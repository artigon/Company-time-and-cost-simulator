package CompanyModel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;

import CompanyController.CompanyControllers;
import CompanyListeners.CompanyModelEventListener;
import CompanyModel.Departments.DepartmentWorkingPolicies;
import CompanyModel.Workers.SalaryType;
import CompanyModel.Workers.WorkdayPreferences;

public class CompanySimulation implements Serializable {
	transient Vector<CompanyModelEventListener> allListeners = new Vector<CompanyModelEventListener>();

	private Vector<Departments> DepartmentsList = new Vector<Departments>();
	private Vector<Workers> WorkersListforDepartment = new Vector<Workers>();

	public CompanySimulation() {
		HardCodeInput();
	//	RoleChangeCalc	(WorkdayPreferences.Earlier, "PlaceHolder1",  "role1");
	//	profit(WorkdayPreferences.Later,"PlaceHolder1" );
	}
	
// Role Change profit calc
	public void RoleChangeCalc(WorkdayPreferences ThePreference, String theDepartmentname, String Therole) {
		int Unchangedworkers = 0, WorkersWithoutPrefrence = 0, WorkersWithprefrence = 0, HomeWorkers = 0;
		double profit;
		for (int j = 0; j < DepartmentsList.size(); j++) {
			if (DepartmentsList.elementAt(j).getDepartmentname().equalsIgnoreCase(theDepartmentname)) {
				if (DepartmentsList.elementAt(j).getDepartmentWorkingPolicies() != DepartmentWorkingPolicies.NoChange) {//problem here cant enter if need better way of comparing enums
					for (int i = 0; i < DepartmentsList.elementAt(j).getWorkersListforDepartment().size(); i++) {
						if (DepartmentsList.elementAt(j).getWorkersListforDepartment().elementAt(i).getRoles().equalsIgnoreCase(Therole)) {
							if (ThePreference.equals(DepartmentsList.elementAt(j).getWorkersListforDepartment().elementAt(i).getCurrentWorkdayPolicy())) {
								Unchangedworkers++;

							} else {
								if (ThePreference.equals(DepartmentsList.elementAt(j).getWorkersListforDepartment().elementAt(i).getWorkdayPreferences())
										&& ThePreference != WorkdayPreferences.Home) {
									WorkersWithprefrence++;

								}
								if (ThePreference.equals(DepartmentsList.elementAt(j).getWorkersListforDepartment().elementAt(i).getWorkdayPreferences())
										&& ThePreference == WorkdayPreferences.Home) {
									HomeWorkers++;

								} else {
									WorkersWithoutPrefrence++;

								}
							}
						} else {
							continue;
						}
					}
				}

				profit = (WorkersWithprefrence * 8 * 1.2) + (WorkersWithoutPrefrence * 8 * 0.8) + (Unchangedworkers * 8)
						+ (HomeWorkers * 8 * 1.1) - (DepartmentsList.elementAt(j).getWorkersListforDepartment().size() * 8);
				DepartmentsList.elementAt(j)
						.setDepartmentProfit(DepartmentsList.elementAt(j).getDepartmentProfit() + profit);
				break;
			}

		}

	}

// Company Role Change profitCalc
	public void CompanyRoleChangeProfit(WorkdayPreferences ThePreference, String Therole) {
		for (int i = 0; i < DepartmentsList.size(); i++) {
			RoleChangeCalc(ThePreference, DepartmentsList.elementAt(i).getDepartmentname(), Therole);
		}
	}

// profit change calculation functions
	public void profit(WorkdayPreferences ThePreference, String theDepartmentName) {
		double profit;
		int WorkersWithprefrence = 0, WorkersWithoutPrefrence = 0, HomeWorkers = 0, Unchangedworkers = 0;
		for (int j = 0; j < DepartmentsList.size(); j++) {
			if (DepartmentsList.elementAt(j).getDepartmentname().equalsIgnoreCase(theDepartmentName)) {

				// All workers must change
				if (DepartmentsList.elementAt(j).getDepartmentWorkingPolicies() == DepartmentWorkingPolicies.AllChange) {
					for (int i = 0; i <DepartmentsList.elementAt(j).getWorkersListforDepartment().size(); i++) {
						if (ThePreference.equals(DepartmentsList.elementAt(j).getWorkersListforDepartment().elementAt(i).getCurrentWorkdayPolicy())) {
							Unchangedworkers++;

						} else {
							if (ThePreference.equals(DepartmentsList.elementAt(j).getWorkersListforDepartment().elementAt(i).getWorkdayPreferences())
									&& ThePreference != WorkdayPreferences.Home) {
								WorkersWithprefrence++;

							}
							if (ThePreference.equals(DepartmentsList.elementAt(j).getWorkersListforDepartment().elementAt(i).getWorkdayPreferences())
									&& ThePreference == WorkdayPreferences.Home) {
								HomeWorkers++;

							} else {
								WorkersWithoutPrefrence++;

							}
						}
					}
					profit = (WorkersWithprefrence * 8 * 1.2) + (WorkersWithoutPrefrence * 8 * 0.8)
							+ (Unchangedworkers * 8) + (HomeWorkers * 8 * 1.1) - (DepartmentsList.elementAt(j).getWorkersListforDepartment().size() * 8);
					DepartmentsList.elementAt(j).setDepartmentProfit(DepartmentsList.elementAt(j).getDepartmentProfit() + profit);

				}
				// Workers can change into new prefrence freely
				if (DepartmentsList.elementAt(j)
						.getDepartmentWorkingPolicies() == DepartmentWorkingPolicies.FreeChange) {
					for (int i = 0; i < DepartmentsList.elementAt(j).getWorkersListforDepartment().size(); i++) {
						if (ThePreference.equals(DepartmentsList.elementAt(j).getWorkersListforDepartment().elementAt(i).getCurrentWorkdayPolicy())) {
							Unchangedworkers++;
						}
						if (ThePreference.equals(DepartmentsList.elementAt(j).getWorkersListforDepartment().elementAt(i).getWorkdayPreferences())
								&& ThePreference != WorkdayPreferences.Home) {
							WorkersWithprefrence++;
						}
						if (ThePreference.equals(DepartmentsList.elementAt(j).getWorkersListforDepartment().elementAt(i).getWorkdayPreferences())
								&& ThePreference == WorkdayPreferences.Home) {
							HomeWorkers++;
						}

					}
					profit = (WorkersWithprefrence * 8 * 1.2) + (Unchangedworkers * 8) + (HomeWorkers * 8 * 1.1)
							- (DepartmentsList.elementAt(j).getWorkersListforDepartment().size() * 8);
					DepartmentsList.elementAt(j)
							.setDepartmentProfit(DepartmentsList.elementAt(j).getDepartmentProfit() + profit);

				}
				// Workers current work schedule cannot change
				if (DepartmentsList.elementAt(j).getDepartmentWorkingPolicies() == DepartmentWorkingPolicies.NoChange) {
					for (int i = 0; i < DepartmentsList.elementAt(j).getWorkersListforDepartment().size(); i++) {
						if (DepartmentsList.elementAt(j).getWorkersListforDepartment().elementAt(i).getWorkdayPreferences()
								.equals(DepartmentsList.elementAt(j).getWorkersListforDepartment().elementAt(i).getCurrentWorkdayPolicy())) {
							Unchangedworkers++;
						} else {
							WorkersWithoutPrefrence++;
						}
					}
					profit = (Unchangedworkers * 8) + (WorkersWithoutPrefrence * 8 * 0.8)
							- (DepartmentsList.elementAt(j).getWorkersListforDepartment().size() * 8);
					DepartmentsList.elementAt(j)
							.setDepartmentProfit(DepartmentsList.elementAt(j).getDepartmentProfit() + profit);
				}
				// TODO
				break;
			}
		}

	}

//Changing workers workday scheduelle based on roles in a deparment
	public void rolechangeForDepartment(String TheRole, WorkdayPreferences ThePreference, String theDepartment) {
		for (int j = 0; j < DepartmentsList.size(); j++) {
			if (DepartmentsList.elementAt(j).getDepartmentname().equalsIgnoreCase(theDepartment)) {

				if (DepartmentsList.elementAt(j).getDepartmentWorkingPolicies() != DepartmentWorkingPolicies.NoChange) {
					for (int i = 0; i < DepartmentsList.elementAt(j).getWorkersListforDepartment().capacity(); i++) {
						if (DepartmentsList.elementAt(j).getWorkersListforDepartment().elementAt(i).equals(TheRole)) {
							DepartmentsList.elementAt(j).getWorkersListforDepartment().elementAt(i)
									.setCurrentWorkdayPolicy(ThePreference);
						} else {
							continue;
						}
					}
				}

				else {
					System.out.println("Cannot change according to departments policy");
				}
			}
			break;
		}

	}

// Changing The work schedule for the entire company by role
	public void ChangingWorkSchedueleByRoleForAllCompany(WorkdayPreferences ThePreference, String TheRole) {
		for (int i = 0; i < DepartmentsList.size(); i++) {
			for (int j = 0; j < DepartmentsList.elementAt(i).getWorkersListforDepartment().capacity(); j++) {
				if (DepartmentsList.elementAt(i).getDepartmentWorkingPolicies() != DepartmentWorkingPolicies.NoChange) {
					DepartmentsList.elementAt(i).getWorkersListforDepartment().elementAt(j).getCurrentWorkdayPolicy()
							.equals(ThePreference);
				} else {
					continue;
				}
			}
		}
		sendMessegeToUI("work scdual changed sucsesfully");
	}

	public void changeWorkScheduleForDepatment(WorkdayPreferences thePreferences, String thedepartment) {
		for (int i = 0; i < DepartmentsList.size(); i++) {
			if (DepartmentsList.elementAt(i).getDepartmentname().equalsIgnoreCase(thedepartment)) {
				if (DepartmentsList.elementAt(i).getDepartmentWorkingPolicies()
						.equals(DepartmentWorkingPolicies.AllChange)) {
					for (int j = 0; j < DepartmentsList.elementAt(i).getWorkersListforDepartment().size(); j++) {
						DepartmentsList.elementAt(i).getWorkersListforDepartment().elementAt(j)
								.setCurrentWorkdayPolicy(thePreferences);
					}
					sendMessegeToUI("work scdual changed sucsesfully");

				} else if (DepartmentsList.elementAt(i).getDepartmentWorkingPolicies()
						.equals(DepartmentWorkingPolicies.FreeChange)) {
					for (int j = 0; j < DepartmentsList.elementAt(i).getWorkersListforDepartment().size(); j++) {
						if (DepartmentsList.elementAt(i).getWorkersListforDepartment().elementAt(j)
								.getWorkdayPreferences().equals(thePreferences)) {
							DepartmentsList.elementAt(i).getWorkersListforDepartment().elementAt(j)
									.setCurrentWorkdayPolicy(thePreferences);
						} else {
							continue;
						}
					}
					sendMessegeToUI("work scdual changed sucsesfully");
				} else {
					sendMessegeToUI("canot change prefrenc for this depatment");
				}

			}
			break;
		}
	}

// Adding a new worker to an existing Department via department name
	public void addWorkertoDeparment(String name, WorkdayPreferences TheworkdayPreferences, String Therole,
			WorkdayPreferences ThecurrentWorkdayPolicy, String Departmentnames, SalaryType TheSalaryType) {
		Workers newWorker = new Workers(name, ThecurrentWorkdayPolicy, Therole, ThecurrentWorkdayPolicy, TheSalaryType);
		for (int i = 0; i < DepartmentsList.size(); i++) {
			if (Departmentnames.equalsIgnoreCase(DepartmentsList.elementAt(i).getDepartmentname())) {
				DepartmentsList.elementAt(i).getWorkersListforDepartment().add(newWorker);
				System.out.println("worker add!");
				sendMessegeToUI("worker add!");
				break;
			} else {
				continue;
			}
		}
	}

// Adding a new Department
	public void addDepartment(String NewDepartmentname,  DepartmentWorkingPolicies WorkingPolicy) {
		Vector<Workers> newWorkersListforDepartment = new Vector<Workers>();
		DepartmentsList.add(new Departments(NewDepartmentname, newWorkersListforDepartment, WorkingPolicy, 0.0));
		sendMessegeToUI("Department added!");
	}

	public Vector<Departments> getDepartmentsList() {
		return DepartmentsList;
	}

	public void getDepartmentNamesList() {
		String[] name = new String[DepartmentsList.size()];
		for (int i = 0; i < DepartmentsList.size(); i++) {
			name[i] = DepartmentsList.elementAt(i).getDepartmentname();
		}
		fireDepartmentsNamesListToUI(name);
	}

	public Vector<Workers> getWorkersListforDepartment() {
		return WorkersListforDepartment;
	}

	public void setDepartmentsList(Vector<Departments> departmentsList) {
		DepartmentsList = departmentsList;
	}

	public void setWorkersListforDepartment(Vector<Workers> workersListforDepartment) {
		WorkersListforDepartment = workersListforDepartment;
	}

//Worker salary calculator;
	public String WorkerSalary(SalaryType TheSalaryType, double MonthlySalary, double HourlySalary,
			double MonthlySales) {
		Double FinalSalary = 0.0;
		if (TheSalaryType == SalaryType.BaseSalary) {
			FinalSalary = MonthlySalary;
		}
		if (TheSalaryType == SalaryType.Hourly) {
			FinalSalary = HourlySalary * 160;
		}

		else if (TheSalaryType == SalaryType.BaseSalaryPlusSales) {
			FinalSalary = MonthlySalary + MonthlySales * 0.1;
		}
		return "The Worker salary for this month is " + FinalSalary;
	}

	public void GetWorkersProfit(double MonthlySalary, double HourlySalary, double MonthlySales) {
		StringBuffer temp = new StringBuffer();
		temp.append(WorkerSalary(SalaryType.BaseSalary, MonthlySalary, HourlySalary, MonthlySales));
		temp.append("\n" + WorkerSalary(SalaryType.Hourly, MonthlySalary, HourlySalary, MonthlySales));
		temp.append("\n" + WorkerSalary(SalaryType.BaseSalaryPlusSales, MonthlySalary, HourlySalary, MonthlySales));
		fireWorkerProfitToUI(temp.toString());
	}

	public void CompanyProfitCalculator() {
		double companyProfit = 0;
		for (int i = 0; i < DepartmentsList.size(); i++) {
			companyProfit = companyProfit + DepartmentsList.elementAt(i).getDepartmentProfit();
			fireCompanyProfitToUI(String.valueOf(companyProfit));
		}
	}

	public void getDepatmentProfit(String DepatmentName) {
		for (int i = 0; i < DepartmentsList.size(); i++) {
			if (DepartmentsList.elementAt(i).getDepartmentname().equalsIgnoreCase(DepatmentName)) {
				fireDepatmentProfirToUI("Department " + DepartmentsList.elementAt(i).getDepartmentname()
						+ " profit is: " + String.valueOf(DepartmentsList.elementAt(i).getDepartmentProfit()));
				break;
			}

		}

	}

//hardcode.
	public void HardCodeInput() {
		Vector<Workers> WorkersListforDepartment1 = new Vector<Workers>();
		WorkersListforDepartment1.add(new Workers("Bob", WorkdayPreferences.Earlier, "role1", WorkdayPreferences.Later,
				SalaryType.BaseSalary));
		WorkersListforDepartment1.add(new Workers("Marie", WorkdayPreferences.Later, "role2",
				WorkdayPreferences.Earlier, SalaryType.BaseSalaryPlusSales));
		WorkersListforDepartment1.add(
				new Workers("POPO", WorkdayPreferences.Home, "role1", WorkdayPreferences.Earlier, SalaryType.Hourly));
		WorkersListforDepartment1.add(
				new Workers("Guy", WorkdayPreferences.Regular, "role2", WorkdayPreferences.Home, SalaryType.Hourly));
		WorkersListforDepartment1.add(new Workers("George", WorkdayPreferences.Home, "role1",
				WorkdayPreferences.Later, SalaryType.BaseSalary));
		WorkersListforDepartment1.add(new Workers("Fred", WorkdayPreferences.Regular, "role3",
				WorkdayPreferences.Earlier, SalaryType.BaseSalary));
		WorkersListforDepartment1.add(new Workers("Simon", WorkdayPreferences.Home, "role3", WorkdayPreferences.Earlier,
				SalaryType.BaseSalaryPlusSales));
		WorkersListforDepartment1.add(
				new Workers("Shani", WorkdayPreferences.Regular, "role2", WorkdayPreferences.Home, SalaryType.Hourly));
		DepartmentsList.add(new Departments("Placeholder1", WorkersListforDepartment1, DepartmentWorkingPolicies.AllChange, 0.0));
		Vector<Workers> WorkersListforDepartment2 = new Vector<Workers>();
		WorkersListforDepartment2.add(new Workers("George", WorkdayPreferences.Earlier, "role1",
				WorkdayPreferences.Later, SalaryType.BaseSalary));
		WorkersListforDepartment2.add(new Workers("Shimon", WorkdayPreferences.Earlier, "role3",
				WorkdayPreferences.Earlier, SalaryType.BaseSalary));
		WorkersListforDepartment2.add(new Workers("bObO", WorkdayPreferences.Home, "role3", WorkdayPreferences.Earlier,
				SalaryType.BaseSalaryPlusSales));
		WorkersListforDepartment2.add(
				new Workers("Girl", WorkdayPreferences.Regular, "role2", WorkdayPreferences.Home, SalaryType.Hourly));

		DepartmentsList.add(new Departments("Placeholder2", WorkersListforDepartment2,
				DepartmentWorkingPolicies.NoChange, 0.0));

		Vector<Workers> WorkersListforDepartment3 = new Vector<Workers>();
		WorkersListforDepartment3.add(new Workers("Fred", WorkdayPreferences.Earlier, "role2", WorkdayPreferences.Later,
				SalaryType.BaseSalary));
		WorkersListforDepartment3.add(new Workers("Lisa", WorkdayPreferences.Later, "role1",
				WorkdayPreferences.Earlier, SalaryType.BaseSalaryPlusSales));
		WorkersListforDepartment3.add(
				new Workers("Bobi", WorkdayPreferences.Home, "role2", WorkdayPreferences.Earlier, SalaryType.Hourly));
		WorkersListforDepartment3.add(new Workers("Dude", WorkdayPreferences.Regular, "role3", WorkdayPreferences.Home,
				SalaryType.BaseSalaryPlusSales));

		DepartmentsList.add(new Departments("Placeholder3", WorkersListforDepartment3, 
				DepartmentWorkingPolicies.FreeChange, 0.0));
		
		
	}

// showing information about a department workers
	public void ShowDepartmentWorkersList(String departmentName) {
		StringBuffer temp = new StringBuffer();
		for (int j = 0; j < DepartmentsList.size(); j++) {
			if (DepartmentsList.elementAt(j).getDepartmentname().equalsIgnoreCase(departmentName)) {
				for (int i = 0; i < DepartmentsList.elementAt(j).getWorkersListforDepartment().size(); i++) {
					temp.append(DepartmentsList.elementAt(j).getWorkersListforDepartment().elementAt(i).toString());
				}
				break;
			}

		}
		System.out.println(temp.toString());
		fireShowDepartmentWorkersListToUI(temp.toString());
	}

// Show Entire Company
	public void PrintCompany() {
		StringBuffer temp = new StringBuffer();
		for (int j = 0; j < DepartmentsList.size(); j++) {
			temp.append(DepartmentsList.elementAt(j).toString() + "\n");
		}
		System.out.println(temp.toString());
		firePrintCompanyToUI(temp.toString());
	}

	public void CompanyDataSaving(CompanyModelEventListener SavedCompany) {

		try {
			ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream("Company data.txt"));
			objectOut.writeObject(SavedCompany);
			objectOut.close();
			// fireMessegeToUI("The Company was succesfully written to a file");
			System.out.println("The Company  was succesfully written to a file");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void CompanyDataLoad(Boolean answer) {
		try {
			CompanySimulation theSimulation = new CompanySimulation();
			if (!(answer)) {
				theSimulation.HardCodeInput();
				fireNewCompanySimulationForController(theSimulation);
			} else {
				ObjectInputStream inFile = new ObjectInputStream(new FileInputStream("Company data.txt"));
				theSimulation = (CompanySimulation) inFile.readObject();
				fireNewCompanySimulationForController(theSimulation);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

//to UI functions:

	public void register(CompanyModelEventListener newCompanyModelEventListener) {
		allListeners.add(newCompanyModelEventListener);

	}

	public void fireDepartmentsNamesListToUI(String[] names) {
		for (CompanyModelEventListener l : allListeners) {
			l.fireDepartmentsNamesListToUI(names);
		}
	}

	public void fireShowDepartmentWorkersListToUI(String temp) {
		for (CompanyModelEventListener l : allListeners) {
			l.fireShowDepartmentWorkersListToUI(temp);
		}
	}

	public void firePrintCompanyToUI(String temp) {
		for (CompanyModelEventListener l : allListeners) {
			l.firePrintCompanyToUI(temp);
		}
	}

	public void fireNewCompanySimulationForController(CompanySimulation theSimulation) {
		for (CompanyModelEventListener l : allListeners) {
			l.fireNewCompanySimulationForController(theSimulation);
		}
	}

	public void sendMessegeToUI(String temp) {
		for (CompanyModelEventListener l : allListeners) {
			l.sendMessegeToUI(temp);
		}
	}

	public void fireDepatmentProfirToUI(String profit) {
		for (CompanyModelEventListener l : allListeners) {
			l.fireDepatmentProfirToUI(profit);
		}
	}

	public void fireWorkerProfitToUI(String temp) {
		for (CompanyModelEventListener l : allListeners) {
			l.fireWorkerProfitToUI(temp);
		}
	}
	
	public void fireCompanyProfitToUI(String temp) {
		for (CompanyModelEventListener l : allListeners) {
			l.fireCompanyProfitToUI(temp);
		}
	}

}
