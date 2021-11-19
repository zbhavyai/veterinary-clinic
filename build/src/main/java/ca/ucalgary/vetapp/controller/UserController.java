package ca.ucalgary.vetapp.controller;

import ca.ucalgary.vetapp.model.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Gets all users
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
    @GetMapping(path = "{userId}")
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
     * @param u
     * @return
     */
    @PostMapping
    public User addUser(@RequestBody User u) {
        return this.userRepository.save(u);
    }

    /**
     *
     * @param u
     * @param id
     * @return
     */
    @PutMapping(path = "{userId}")
    public User updateUser(@RequestBody User u, @PathVariable("userId") Long id) {
        Optional<User> userOptional = this.userRepository.findById(id);

        User updatedUser;

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            user.setFirstName(u.getFirstName());
            user.setLastName(u.getLastName());
            user.setMiddleName(u.getMiddleName());
            user.setRole(u.getRole());
            user.setEmailId(u.getEmailId());
            user.setPassword(u.getPassword());

            updatedUser = this.userRepository.save(user);
        }

        else {
            u.setUserId(id);
            updatedUser = this.userRepository.save(u);
        }

        return updatedUser;
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
}
