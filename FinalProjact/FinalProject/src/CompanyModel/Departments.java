package CompanyModel;

import java.util.Vector;

public class Departments {

	public enum DepartmentWorkingPolicies {
		NoChange, FreeChange, AllChange
	}
	private String Departmentname;
	private Vector<Workers> WorkersListforDepartment = new Vector<Workers>();
	
	private DepartmentWorkingPolicies departmentWorkingPolicies;
	private double DepartmentProfit;

	public Departments(String Departmentnames, Vector<Workers> workersListforDepartment, 
			DepartmentWorkingPolicies departmentWorkingPolicies,double DepartmentProfit) {
		super();
		this.Departmentname = Departmentnames;
		WorkersListforDepartment = workersListforDepartment;
		;
		this.departmentWorkingPolicies = departmentWorkingPolicies;
		this.DepartmentProfit=DepartmentProfit;
	}

	public Vector<Workers> getWorkersListforDepartment() {
		return WorkersListforDepartment;
	}

	

	public double getDepartmentProfit() {
		return DepartmentProfit;
	}

	public void setDepartmentProfit(double departmentProfit) {
		DepartmentProfit = departmentProfit;
	}

	public void setWorkersListforDepartment(Vector<Workers> workersListforDepartment) {
		WorkersListforDepartment = workersListforDepartment;
	}

	public DepartmentWorkingPolicies getDepartmentWorkingPolicies() {
		return departmentWorkingPolicies;
	}

	public void setDepartmentWorkingPolicies(DepartmentWorkingPolicies departmentWorkingPolicies) {
		this.departmentWorkingPolicies = departmentWorkingPolicies;
	}

	public String getDepartmentname() {
		return Departmentname;
	}

	public void setDepartmentnames(String departmentnames) {
		Departmentname = departmentnames;
	}

	@Override
	public String toString() {
		StringBuffer Temp = new StringBuffer();
		Temp.append("\nThe Deparment :" + "\nDeparment name :" + Departmentname +  "\nDepartment Working Policies :"
				+ departmentWorkingPolicies+"\nCurrent Profit :"+DepartmentProfit+"\n Workers List :");
		for (int i = 0; i < WorkersListforDepartment.size(); i++) {
			if (!(WorkersListforDepartment.elementAt(i) == null)) {
				Temp.append("\n" + (i + 1) + " - " + WorkersListforDepartment.elementAt(i).getName());
			} else {
				continue;
			}
		}
		return Temp.toString();
	}

}
