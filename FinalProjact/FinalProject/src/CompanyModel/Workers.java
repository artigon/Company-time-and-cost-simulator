package CompanyModel;

public class Workers {
	public enum WorkdayPreferences {
		Earlier, Later, Regular, Home;
	}

	public enum SalaryType {
		BaseSalary, Hourly, BaseSalaryPlusSales
	}

	private String name;
	private WorkdayPreferences workdayPreferences;
	private String roles;
	private WorkdayPreferences CurrentWorkdayPolicy;
	private SalaryType SalaryType;
	// private Departments Departmentnames;

	public Workers(String name, WorkdayPreferences workdayPreferences, String roles,
			WorkdayPreferences currentWorkdayPolicy, SalaryType TheSalaryType) {
		super();
		this.name = name;
		this.workdayPreferences = workdayPreferences;
		this.roles = roles;
		CurrentWorkdayPolicy = currentWorkdayPolicy;
		this.SalaryType = TheSalaryType;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getName() {
		return name;
	}

	public WorkdayPreferences getWorkdayPreferences() {
		return workdayPreferences;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setWorkdayPreferences(WorkdayPreferences workdayPreferences) {
		this.workdayPreferences = workdayPreferences;
	}

	public WorkdayPreferences getCurrentWorkdayPolicy() {
		return CurrentWorkdayPolicy;
	}

	public void setCurrentWorkdayPolicy(WorkdayPreferences currentWorkdayPolicy) {
		CurrentWorkdayPolicy = currentWorkdayPolicy;
	}

	public SalaryType getSalaryType() {
		return SalaryType;
	}

	public void setSalaryType(SalaryType salaryType) {
		SalaryType = salaryType;
	}

	@Override
	public String toString() {
		StringBuffer StringBuffer = new StringBuffer();
		StringBuffer.append("Worker: " + "\nWorker's  name: " + name + "\nWorkday Preferences: " + workdayPreferences
				+ "\nCurrent worker role: " + roles + "\nCurrentWorkdayPolicy: " + CurrentWorkdayPolicy
				+ "\nThe SalaryType: " + SalaryType + "\n\n");
		return StringBuffer.toString();
	}

}
