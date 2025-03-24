package my.project.prototype.models;

import java.util.List;

public class TechSkill {

	private String category;
	private List<String> skills;

	public TechSkill() {
	}

	public TechSkill(String category, List<String> skills) {
		this.category = category;
		this.skills = skills;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	@Override
	public String toString() {
		return "TechSkills{" + "category='" + category + '\'' + ", skills=" + skills + '}';
	}
}