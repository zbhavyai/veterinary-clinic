package ca.ucalgary.vetapp.controller;

import ca.ucalgary.vetapp.model.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@CrossOrigin(methods = { RequestMethod.PUT, RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE,
    RequestMethod.OPTIONS })
@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {
    private final UserRepository userRepository;

    /**
     * Constructor
     *
     * @param userRepository jpa repository for db operations
     */
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Search the users by id
     *
     * @param id user id
     * @return list of users with id; the list would have atmost one element
     */
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

    /**
     * Search the users by name
     *
     * @param allUsers list of all users
     * @param sT       search term
     * @return filtered list of users having name meeting search term
     */
    private List<User> searchUserByName(List<User> allUsers, String sT) {
        String searchTerm = sT.toLowerCase();
        List<User> searchResults = new ArrayList<>();

        for (User eachUser : allUsers) {
            if (eachUser.getFullName().toLowerCase().contains(searchTerm)) {
                searchResults.add(eachUser);
                continue;
            }
        }

        return searchResults;
    }

    /**
     * Search the users by email id
     *
     * @param allUsers list of all users
     * @param sT       search term
     * @return filtered list of users having email id meeting search term
     */
    private List<User> searchUserByEmail(List<User> allUsers, String sT) {
        String searchTerm = sT.toLowerCase();
        List<User> searchResults = new ArrayList<>();

        for (User eachUser : allUsers) {
            if (eachUser.getEmailId() != null && eachUser.getEmailId().toLowerCase().contains(searchTerm)) {
                searchResults.add(eachUser);
                continue;
            }
        }

        return searchResults;
    }

    /**
     * Search the users by their role
     *
     * @param allUsers list of all users
     * @param sT       search term
     * @return filtered list of users having role as search term
     */
    private List<User> searchUserByRole(List<User> allUsers, String sT) throws UnsupportedRequestException {
        try {
            UserRole searchTerm = UserRole.valueOf(sT.toUpperCase());
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

    /**
     * Search the users by their status
     *
     * @param allUsers list of all users
     * @param sT       search term
     * @return filtered list of users having status as search term
     */
    private List<User> searchUserByStatus(List<User> allUsers, String sT) throws UnsupportedRequestException {
        try {
            UserStatus searchTerm = UserStatus.valueOf(sT.toUpperCase());
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

    /**
     * Endpoint for GET - fetch user by id
     *
     * @param id user id
     * @return user
     * @throws NotFoundException
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

    /**
     * Endpoint for GET - search and fetch all users meeting search criteria
     *
     * @param searchBy   where to search
     * @param searchTerm what to search
     * @return list of users meeting search criteria
     * @throws UnsupportedRequestException
     */
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
            return this.searchUserByName(allUsers, searchTerm);
        }

        else if (searchBy.equalsIgnoreCase("email")) {
            return this.searchUserByEmail(allUsers, searchTerm);
        }

        else if (searchBy.equalsIgnoreCase("role")) {
            return this.searchUserByRole(allUsers, searchTerm);
        }

        else if (searchBy.equalsIgnoreCase("status")) {
            return this.searchUserByStatus(allUsers, searchTerm);
        }

        else {
            throw new UnsupportedRequestException("Invalid search by");
        }
    }

    /**
     * Endpoint for POST - add a user in db
     *
     * @param u user details in json
     * @return the added user details
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

        // set the joining date to now and activation date to null
        u.setJoiningDate(LocalDate.now());
        u.setActivationDate(null);

        // all new users should be inactive and must be activated by admin
        u.setStatus(UserStatus.INACTIVE);

        return this.userRepository.save(u);
    }

    /**
     * Endpoint for PUT - update the user in db
     *
     * @param u  updated user details in json
     * @param id the user to be updated
     * @return the updated user details
     * @throws UserExistsException
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

            // check if this is activation request
            if(user.getStatus() == UserStatus.INACTIVE && u.getStatus() == UserStatus.ACTIVE) {
                user.setActivationDate(LocalDate.now());
            }

            // check if this is a block request
            if(u.getStatus() == UserStatus.INACTIVE) {
                user.setActivationDate(null);
            }

            user.setTerminationDate(u.getTerminationDate());
            user.setFirstName(u.getFirstName());
            user.setMiddleName(u.getMiddleName());
            user.setLastName(u.getLastName());
            user.setRole(u.getRole());
            user.setEmailId(u.getEmailId());
            user.setPasswordHash(u.getPasswordHash());
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
     * Endpoint for DELETE - delete user from db
     *
     * @param id user id
     * @throws UnsupportedRequestException
     */
    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long id) throws UnsupportedRequestException {
        if (!this.userRepository.existsById(id)) {
            throw new NotFoundException("user", id);
        }

        if (this.userRepository.getById(id).getRole() == UserRole.ADMIN) {
            throw new UnsupportedRequestException("Cannot delete an admin");
        }

        // TODO: make sure user getting deleted has all its treatments, issues, and
        // TODO: comments deleted as well. To be done on sql side using CASCADE
        // DONE: handled via tables cascade
        this.userRepository.deleteById(id);
    }
}
