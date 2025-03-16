package my.project.prototype.dataService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

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

		// Add more keys and values as needed

		System.out.println("Map: " + map.toString());

		return map;
	}
}