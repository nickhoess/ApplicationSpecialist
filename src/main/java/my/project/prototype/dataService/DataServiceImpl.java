package my.project.prototype.dataService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import my.project.prototype.models.User;

@Service
public class DataServiceImpl {

	public Map<String, String> buildMapper(User user) throws IOException {
		Map<String, String> map = new HashMap<>();

		// Populate the map with values from the user object
		for (Map.Entry<String, String> entry : map.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();

			switch (key) {
				case "name" -> map.put(value, user.getName());
				case "email" -> map.put(value, user.getEmail());
				case "phone" -> map.put(value, user.getPhone());
				case "address" -> map.put(value, user.getAddress());
				// Add more cases as needed
			}
		}

		return map;
	}
}