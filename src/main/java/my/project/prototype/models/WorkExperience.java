package my.project.prototype.models;

import java.util.List;

public class WorkExperience {

	private String companyName;
	private String jobTitle;
	private String startDate;
	private String endDate;
	private String specialization;
	private List<SoftwareProject> projects;

	public WorkExperience() {
	}

	public WorkExperience(String companyName, String jobTitle, String startDate, String endDate, String specialization,
			List<SoftwareProject> projects) {
		this.companyName = companyName;
		this.jobTitle = jobTitle;
		this.startDate = startDate;
		this.endDate = endDate;
		this.specialization = specialization;
		this.projects = projects;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public List<SoftwareProject> getProjects() {
		return projects;
	}

	public void setProjects(List<SoftwareProject> projects) {
		this.projects = projects;
	}

	public Object getLocation() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getLocation'");
	}

	public String[] getResponsibilities() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getResponsibilities'");
	}
}