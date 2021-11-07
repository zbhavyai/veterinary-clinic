package VetAPI;
import java.util.HashMap;

public class DataStore {
	HashMap<String, User> userDataStore ;
	HashMap<Integer, Animal> animalDataStore;
	public DataStore() {
		this.userDataStore= new HashMap<String, User>();
		this.animalDataStore = new HashMap<Integer, Animal>();
		this.sampleLoad();

	}

	public boolean userExist(String key) {
		if(this.userDataStore.containsKey(key)) {
			return true;

		}
		else {
			return false;
		}
	}
	public void addUser(String fname, String mname, String lname, String email, String role, String status, String password) {
		User newUser = new User(fname, mname, lname, email, role, status, password);
		this.userDataStore.put(newUser.getEmail(), newUser);
		//add update to database here
	}

	public User getUser(String email) {

		return this.userDataStore.get(email);
	}

	public void updateUser(User user) {
		this.userDataStore.put(user.getEmail(), user);
	}

	public void removeUser(String email) {
		this.userDataStore.remove(email);
	}

	private void sampleLoad() {
		this.addUser("Mike", "Test", "Lee", "mike@test.com", "admin", "good", "test");
		this.addUser("Bhavyai", "Test", "Gupta", "bhavyai@test.com", "admin", "good", "test");
		this.addUser("Sarang", "Test", "Kumar", "sarang@test.com", "admin", "good", "test");
	}








}
