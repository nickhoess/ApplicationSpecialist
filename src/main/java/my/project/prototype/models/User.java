package my.project.prototype.models;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class User {

	private String name;
	private String email;
	private String phone;
	private String address;
	private List<TechSkill> techSkills;
	private List<WorkExperience> workExperiences; // Changed to List<WorkExperience>
	private List<Education> education; // List of degrees or certifications
	private List<SoftwareProject> projects;

	// New fields for JSON file paths
	private String skillsJsonFilePath;
	private String workExperiencesJsonFilePath;
	private String educationJsonFilePath;
	private String projectsJsonFilePath;

	public User() {
	}

	public User(String name, String email, String phone, String address, List<TechSkill> techSkills,
			List<WorkExperience> workExperiences, List<Education> education, List<SoftwareProject> projects,
			String workExperiencesJsonFilePath, String skillsJsonFilePath, String educationJsonFilePath,
			String projectsJsonFilePath) {
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.techSkills = techSkills;
		this.workExperiences = workExperiences;
		this.education = education;
		this.projects = projects;
		this.skillsJsonFilePath = skillsJsonFilePath;
		this.workExperiencesJsonFilePath = workExperiencesJsonFilePath;
		this.educationJsonFilePath = educationJsonFilePath;
		this.projectsJsonFilePath = projectsJsonFilePath;
	}

	public String getSkillsJsonFilePath() {
		return skillsJsonFilePath;
	}

	public void setSkillsJsonFilePath(String skillsJsonFilePath) {
		this.skillsJsonFilePath = skillsJsonFilePath;
	}

	public String getWorkExperiencesJsonFilePath() {
		return workExperiencesJsonFilePath;
	}

	public void setWorkExperiencesJsonFilePath(String workExperiencesJsonFilePath) {
		this.workExperiencesJsonFilePath = workExperiencesJsonFilePath;
	}

	public String getEducationJsonFilePath() {
		return educationJsonFilePath;
	}

	public void setEducationJsonFilePath(String educationJsonFilePath) {
		this.educationJsonFilePath = educationJsonFilePath;
	}

	public String getProjectsJsonFilePath() {
		return projectsJsonFilePath;
	}

	public void setProjectsJsonFilePath(String projectsJsonFilePath) {
		this.projectsJsonFilePath = projectsJsonFilePath;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<TechSkill> getSkills() {
		return techSkills;
	}

	public void setSkills(List<TechSkill> skills) {
		this.techSkills = skills;
	}

	public List<WorkExperience> getWorkExperiences() {
		return workExperiences;
	}

	public void setWorkExperiences(List<WorkExperience> workExperiences) {
		this.workExperiences = workExperiences;
	}

	public List<Education> getEducation() {
		return education;
	}

	public void setEducation(List<Education> education) {
		this.education = education;
	}

	public List<SoftwareProject> getProjects() {
		return projects;
	}

	public void setProjects(List<SoftwareProject> projects) {
		this.projects = projects;
	}

	@Override
	public String toString() {
		return "User{" + "name='" + name + '\'' + ", email='" + email + '\'' + ", phone='" + phone + '\''
				+ ", address='" + address + '\'' + ", skills=" + techSkills + ", workExperiences=" + workExperiences
				+ ", education=" + education + ", projects=" + projects + ", skillsJsonFilePath='" + skillsJsonFilePath
				+ '\'' + ", workExperiencesJsonFilePath='" + workExperiencesJsonFilePath + '\''
				+ ", educationJsonFilePath='" + educationJsonFilePath + '\'' + ", projectsJsonFilePath='"
				+ projectsJsonFilePath + '\'' + '}';
	}

	public static User fromJson(String json) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, User.class);
	}
}