package my.project.prototype.models;

import java.util.List;

public class User {

    private String name;
    private String email;
    private String phone;
    private String address;
    private List<String> skills;
    private List<WorkExperience> workExperiences; // List of work experiences
    private List<String> education; // List of degrees or certifications
    private List<SoftwareProject> projects;

    public User() {
    }

    public User(String name, String email, String phone, String address, List<String> skills, List<WorkExperience> workExperiences, List<String> education, List<SoftwareProject> projects) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.skills = skills;
        this.workExperiences = workExperiences;
        this.education = education;
        this.projects = projects;
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

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public List<WorkExperience> getWorkExperiences() {
        return workExperiences;
    }

    public void setWorkExperiences(List<WorkExperience> workExperiences) {
        this.workExperiences = workExperiences;
    }

    public List<String> getEducation() {
        return education;
    }

    public void setEducation(List<String> education) {
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
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", skills=" + skills +
                ", workExperiences=" + workExperiences +
                ", education=" + education +
                ", projects=" + projects +
                '}';
    }
}