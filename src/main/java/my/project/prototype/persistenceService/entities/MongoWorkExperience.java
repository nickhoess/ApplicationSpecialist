package my.project.prototype.persistenceService.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "work_experiences")
public class MongoWorkExperience {

	@Id
	private String id;
	private String company;
	private String position;
	private String startDate;
	private String endDate;
	private String description;

	public MongoWorkExperience() {
	}

	public MongoWorkExperience(String id, String company, String position, String startDate, String endDate,
			String description) {
		this.id = id;
		this.company = company;
		this.position = position;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
	}

	// Getters and Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "MongoWorkExperience{" + "id='" + id + '\'' + ", company='" + company + '\'' + ", position='" + position
				+ '\'' + ", startDate='" + startDate + '\'' + ", endDate='" + endDate + '\'' + ", description='"
				+ description + '\'' + '}';
	}
}