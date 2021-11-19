package ca.ucalgary.vetapp.controller;

import ca.ucalgary.vetapp.model.User;
import ca.ucalgary.vetapp.model.UserRole;
import ca.ucalgary.vetapp.model.UserStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
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
     * Get one animal
     *
     * @param id
     * @return
     */
    @GetMapping(path = "{userId}")
    public User getUserById(@PathVariable("userId") Long id) throws NotFoundException {
        User u = this.searchUserById(id).get(0);

        if (u == null) {
            throw new NotFoundException("user", id);
        }

        else {
            return u;
        }
    }

    private List<User> searchUserById(Long id) {
        Optional<User> userOptional = this.userRepository.findById(id);

        if (userOptional.isPresent()) {
            List<User> searchResults = new ArrayList<>();
            searchResults.add(userOptional.get());
            return searchResults;
        }

        else {
            return null;
        }
    }

    private List<User> searchUserByName(String sT) {
        String searchTerm = sT.toLowerCase();

        List<User> allUsers = this.userRepository.findAll();
        List<User> searchResults = new ArrayList<>();

        for (User eachUser : allUsers) {
            if (eachUser.getFirstName().contains(searchTerm)) {
                searchResults.add(eachUser);
                continue;
            }

            else if (eachUser.getMiddleName().contains(searchTerm)) {
                searchResults.add(eachUser);
                continue;
            }

            else if (eachUser.getLastName().contains(searchTerm)) {
                searchResults.add(eachUser);
                continue;
            }
        }

        return searchResults;
    }

    private List<User> searchUserByEmail(String sT) {
        String searchTerm = sT.toLowerCase();

        List<User> allUsers = this.userRepository.findAll();
        List<User> searchResults = new ArrayList<>();

        for (User eachUser : allUsers) {
            if (eachUser.getEmailId().contains(searchTerm)) {
                searchResults.add(eachUser);
                continue;
            }
        }

        return searchResults;
    }

    private List<User> searchUserByRole(String sT) throws UnsupportedRequestException {
        try {
            UserRole searchTerm = UserRole.valueOf(sT.toUpperCase());

            List<User> allUsers = this.userRepository.findAll();
            List<User> searchResults = new ArrayList<>();

            for (User eachUser : allUsers) {
                if (eachUser.getRole() == searchTerm) {
                    searchResults.add(eachUser);
                    continue;
                }
            }

            return searchResults;
        }

        catch (IllegalArgumentException e) {
            throw new UnsupportedRequestException(e.getMessage());
        }
    }

    private List<User> searchUserByStatus(String sT) throws UnsupportedRequestException {
        try {
            UserStatus searchTerm = UserStatus.valueOf(sT.toUpperCase());

            List<User> allUsers = this.userRepository.findAll();
            List<User> searchResults = new ArrayList<>();

            for (User eachUser : allUsers) {
                if (eachUser.getStatus() == searchTerm) {
                    searchResults.add(eachUser);
                    continue;
                }
            }

            return searchResults;
        }

        catch (IllegalArgumentException e) {
            throw new UnsupportedRequestException(e.getMessage());
        }
    }

    @GetMapping()
    public List<User> searchUser(@RequestParam(name = "searchBy", required = false) String searchBy,
            @RequestParam(name = "searchTerm", required = false) String searchTerm) throws UnsupportedRequestException {

        List<User> allUsers = this.userRepository.findAll();

        if (searchBy == null || searchTerm == null) {
            return allUsers;
        }

        else if (searchBy.equals("") || searchTerm.equals("")) {
            return allUsers;
        }

        else if (searchBy.equalsIgnoreCase("id")) {
            return this.searchUserById(Long.valueOf(searchTerm));
        }

        else if (searchBy.equalsIgnoreCase("name")) {
            return this.searchUserByName(searchTerm);
        }

        else if (searchBy.equalsIgnoreCase("email")) {
            return this.searchUserByEmail(searchTerm);
        }

        else if (searchBy.equalsIgnoreCase("role")) {
            return this.searchUserByRole(searchTerm);
        }

        else if (searchBy.equalsIgnoreCase("status")) {
            return this.searchUserByStatus(searchTerm);
        }

        else {
            throw new UnsupportedRequestException("Invalid search by");
        }
    }

    /**
     * Saves one user
     *
     * @param u
     * @return
     */
    @PostMapping(path = "register")
    public User registerUser(@RequestBody User u) throws UserExistsException {
        List<User> allUsers = this.userRepository.findAll();

        if (allUsers.size() != 0) {
            for (User eachUser : allUsers) {
                if (eachUser.getEmailId().equals(u.getEmailId())) {
                    throw new UserExistsException(u.getEmailId());
                }
            }
        }

        // reset the id to 0 to prevent overwrite
        u.setUserId(0);

        // all new users should be inactive and must be activated by admin
        u.setStatus(UserStatus.INACTIVE);
        return this.userRepository.save(u);
    }

    /**
     * Updates a user
     *
     * @param u
     * @param id
     * @return
     */
    @PutMapping(path = "{userId}")
    public User updateUser(@RequestBody User u, @PathVariable("userId") Long id) throws UserExistsException {
        // checks so that user cannot update to an already claimed it by other
        List<User> allUsers = this.userRepository.findAll();

        if (allUsers.size() != 0) {
            for (User eachUser : allUsers) {
                if (eachUser.getUserId() != id && eachUser.getEmailId().equals(u.getEmailId())) {
                    throw new UserExistsException(u.getEmailId());
                }
            }
        }

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
            user.setStatus(u.getStatus());

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
            throw new NotFoundException("user", id);
        }

        // TODO: make sure user getting deleted has all its treatments, issues, and
        // TODO: comments deleted as well. To be done on sql side using CASCADE
        this.userRepository.deleteById(id);
    }
}
