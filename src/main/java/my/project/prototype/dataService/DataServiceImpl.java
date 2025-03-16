package my.project.prototype.dataService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import my.project.prototype.models.SoftwareProject;
import my.project.prototype.models.User;

@Service
public class DataServiceImpl {

	public Map<String, String> buildMapper(User user) throws IOException {
		System.out.println("User object: " + user.toString());
		Map<String, String> map = new HashMap<>();

		// Populate the map with values from the user object
		if (user.getName() != null) {
			System.out.println("Name: " + user.getName());
			map.put("name", user.getName());
		} else {
			System.out.println("Name is null");
		}

		if (user.getEmail() != null) {
			System.out.println("Email: " + user.getEmail());
			map.put("email", user.getEmail());
		} else {
			System.out.println("Email is null");
		}

		if (user.getPhone() != null) {
			System.out.println("Phone: " + user.getPhone());
			map.put("phone", user.getPhone());
		} else {
			System.out.println("Phone is null");
		}

		if (user.getAddress() != null) {
			System.out.println("Address: " + user.getAddress());
			map.put("address", user.getAddress());
		} else {
			System.out.println("Address is null");
		}

		// Populate the map with values from the user's projects
		List<SoftwareProject> projects = user.getProjects();
		if (projects != null) {
			for (int i = 0; i < projects.size(); i++) {
				SoftwareProject project = projects.get(i);
				int projectIndex = i + 1;
				map.put("projectName" + projectIndex, project.getName());
				map.put("institution" + projectIndex, project.getDescription());
				map.put("location" + projectIndex, project.getVersion());
				map.put("startDate" + projectIndex, project.getLanguages().toString());
				map.put("endDate" + projectIndex, project.getTechnologies().toString());
				map.put("projectItems" + projectIndex, String.join(", ", project.getInformationItems()));
			}
		} else {
			System.out.println("Projects list is null");
		}

		System.out.println("Map: " + map.toString());

		return map;
	}
}