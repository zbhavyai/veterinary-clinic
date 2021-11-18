package ca.ucalgary.vetapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.ucalgary.vetapp.model.Animal;
import ca.ucalgary.vetapp.model.User;
@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {
	private final UserRepository userRepository;

	public UserController(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	/**
     * Gets all animals
     *
     * @return
     */
    @GetMapping
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }
    
    /**
     * Get one animal
     *
     * @param id
     * @return
     */
    @GetMapping("{userId}")
    public User getOneUser(@PathVariable("userId") Long id) {
        Optional<User> userOptional = this.userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user;
        }

        else {
            throw new NotFoundException(id);
        }
    }
    
    /**
     * Saves one user
     *
     * @param a
     * @return
     */
    @PostMapping
    public User addUser(@RequestBody User a) {
        return this.userRepository.save(a);
    }
    
    /**
     * Deletes one user
     *
     * @param id
     */
    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long id) {
        if (!this.userRepository.existsById(id)) {
            throw new NotFoundException(id);
        }

        this.userRepository.deleteById(id);
    }
    
    @PutMapping(path = "{userId}")
    public User updateUser(@RequestBody User a, @PathVariable("userId") Long id) {
        Optional<User> userOptional = this.userRepository.findById(id);

        if (userOptional.isPresent()) {
        	User user = userOptional.get();
        	user.setFirstName(a.getFirstName());
        	user.setLastName(user.getLastName());
        	user.setMiddleName(user.getMiddleName());
        	user.setRole(user.getRole());

            

            return this.userRepository.save(user);
        }

        else {
            a.setUserId(id);
            return this.userRepository.save(a);
        }
        
    }

}
