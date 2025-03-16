package my.project.prototype.models;

import java.util.List;

public class SoftwareProject {

    private String name;
    private String description;
    private String version;
    private List<String> languages;
    private List<String> technologies;
    private List<String> frameworks;
    private List<String> databases;
    private List<String> tools;
    private List<String> libraries;
    private String architecture;
    private String versionControl;
    private String projectType; // e.g., Backend, Frontend, Full Stack, Bot, etc.
    private List<String> informationItems; // New field for information items

    public SoftwareProject() {
    }

    public SoftwareProject(String name, String description, String version, List<String> languages, List<String> technologies, List<String> frameworks, List<String> databases, List<String> tools, List<String> libraries, String architecture, String versionControl, String projectType, List<String> informationItems) {
        this.name = name;
        this.description = description;
        this.version = version;
        this.languages = languages;
        this.technologies = technologies;
        this.frameworks = frameworks;
        this.databases = databases;
        this.tools = tools;
        this.libraries = libraries;
        this.architecture = architecture;
        this.versionControl = versionControl;
        this.projectType = projectType;
        this.informationItems = informationItems; // Initialize new field
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<String> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<String> technologies) {
        this.technologies = technologies;
    }

    public List<String> getFrameworks() {
        return frameworks;
    }

    public void setFrameworks(List<String> frameworks) {
        this.frameworks = frameworks;
    }

    public List<String> getDatabases() {
        return databases;
    }

    public void setDatabases(List<String> databases) {
        this.databases = databases;
    }

    public List<String> getTools() {
        return tools;
    }

    public void setTools(List<String> tools) {
        this.tools = tools;
    }

    public List<String> getLibraries() {
        return libraries;
    }

    public void setLibraries(List<String> libraries) {
        this.libraries = libraries;
    }

    public String getArchitecture() {
        return architecture;
    }

    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }

    public String getVersionControl() {
        return versionControl;
    }

    public void setVersionControl(String versionControl) {
        this.versionControl = versionControl;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public List<String> getInformationItems() {
        return informationItems;
    }

    public void setInformationItems(List<String> informationItems) {
        this.informationItems = informationItems;
    }

    @Override
    public String toString() {
        return "SoftwareProject{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", version='" + version + '\'' +
                ", languages=" + languages +
                ", technologies=" + technologies +
                ", frameworks=" + frameworks +
                ", databases=" + databases +
                ", tools=" + tools +
                ", libraries=" + libraries +
                ", architecture='" + architecture + '\'' +
                ", versionControl='" + versionControl + '\'' +
                ", projectType='" + projectType + '\'' +
                ", informationItems=" + informationItems + // Include new field in toString
                '}';
    }
}