package CompanyModel;

import java.util.Vector;

public class Company {

	private Vector<Departments>CompanyDepartmentsList=new Vector<Departments>();
	private String Companyname;
	
	
	public Company(Vector<Departments> companyDepartmentsList, String companyname) {
		super();
		CompanyDepartmentsList = companyDepartmentsList;
		Companyname = companyname;
	}


	public Vector<Departments> getCompanyDepartmentsList() {
		return CompanyDepartmentsList;
	}


	public String getCompanyname() {
		return Companyname;
	}


	public void setCompanyDepartmentsList(Vector<Departments> companyDepartmentsList) {
		CompanyDepartmentsList = companyDepartmentsList;
	}


	public void setCompanyname(String companyname) {
		Companyname = companyname;
	}
	
	
	
}
