package my.project.prototype.models;

import java.util.List;

public class WorkExperience {

	private String companyName;
	private String jobTitle;
	private String startDate;
	private String endDate;
	private String specialization;
	private String location;
	private List<SoftwareProject> projects;
	private List<String> experienceItems; // Add this field

	public WorkExperience() {
	}

	public WorkExperience(String companyName, String jobTitle, String startDate, String endDate, String specialization,
			String location, List<SoftwareProject> projects, List<String> experienceItems) {
		this.companyName = companyName;
		this.jobTitle = jobTitle;
		this.startDate = startDate;
		this.endDate = endDate;
		this.specialization = specialization;
		this.location = location;
		this.projects = projects;
		this.experienceItems = experienceItems; // Initialize this field
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<String> getExperienceItems() {
		return experienceItems;
	}

	public void setExperienceItems(List<String> experienceItems) {
		this.experienceItems = experienceItems;
	}
}