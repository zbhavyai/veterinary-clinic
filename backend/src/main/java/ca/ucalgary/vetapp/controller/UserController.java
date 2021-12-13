package ca.ucalgary.vetapp.controller;

import ca.ucalgary.vetapp.model.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@CrossOrigin
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
    private List<User> searchUserByRole(List<User> allUsers, String sT) {
        List<User> searchResults = new ArrayList<>();

        try {
            UserRole searchTerm = UserRole.valueOf(sT.toUpperCase());

            for (User eachUser : allUsers) {
                if (eachUser.getRole() == searchTerm) {
                    searchResults.add(eachUser);
                    continue;
                }
            }

            return searchResults;
        }

        catch (IllegalArgumentException e) {
            return searchResults;
        }
    }

    /**
     * Search the users by their status
     *
     * @param allUsers list of all users
     * @param sT       search term
     * @return filtered list of users having status as search term
     */
    private List<User> searchUserByStatus(List<User> allUsers, String sT) {
        List<User> searchResults = new ArrayList<>();

        try {
            UserStatus searchTerm = UserStatus.valueOf(sT.toUpperCase());

            for (User eachUser : allUsers) {
                if (eachUser.getStatus() == searchTerm) {
                    searchResults.add(eachUser);
                    continue;
                }
            }

            return searchResults;
        }

        catch (IllegalArgumentException e) {
            return searchResults;
        }
    }

    /**
     * Endpoint for GET - fetch user by id
     *
     * @param id user id
     * @return user
     */
    @GetMapping(path = "{userId}")
    public ResponseEntity<?> getUserById(@PathVariable("userId") Long id) {
        List<User> u = this.searchUserById(id);

        if (u == null) {
            String message = "Cannot find requested user";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomMessage(HttpStatus.NOT_FOUND, message));
        }

        else {
            return ResponseEntity.status(HttpStatus.OK).body(u.get(0));
        }
    }

    /**
     * Endpoint for GET - search and fetch all users meeting search criteria
     *
     * @param searchBy   where to search
     * @param searchTerm what to search
     * @return list of users meeting search criteria
     */
    @GetMapping()
    public ResponseEntity<?> searchUser(@RequestParam(name = "searchBy", required = false) String searchBy,
            @RequestParam(name = "searchTerm", required = false) String searchTerm) {

        List<User> allUsers = this.userRepository.findAll();

        if (searchBy == null || searchTerm == null) {
            return ResponseEntity.status(HttpStatus.OK).body(allUsers);
        }

        else if (searchBy.equals("") || searchTerm.equals("")) {
            return ResponseEntity.status(HttpStatus.OK).body(allUsers);
        }

        else if (searchBy.equalsIgnoreCase("id")) {
            return ResponseEntity.status(HttpStatus.OK).body(this.searchUserById(Long.valueOf(searchTerm)));
        }

        else if (searchBy.equalsIgnoreCase("name")) {
            return ResponseEntity.status(HttpStatus.OK).body(this.searchUserByName(allUsers, searchTerm));
        }

        else if (searchBy.equalsIgnoreCase("email")) {
            return ResponseEntity.status(HttpStatus.OK).body(this.searchUserByEmail(allUsers, searchTerm));
        }

        else if (searchBy.equalsIgnoreCase("role")) {
            return ResponseEntity.status(HttpStatus.OK).body(this.searchUserByRole(allUsers, searchTerm));
        }

        else if (searchBy.equalsIgnoreCase("status")) {
            return ResponseEntity.status(HttpStatus.OK).body(this.searchUserByStatus(allUsers, searchTerm));
        }

        else {
            String message = "Invalid user search operation";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new CustomMessage(HttpStatus.BAD_REQUEST, message));
        }
    }

    /**
     * Endpoint for POST - add a user in db
     *
     * @param u user details in json
     * @return the added user details
     */
    @PostMapping(path = "register")
    public ResponseEntity<?> registerUser(@RequestBody User u) {
        List<User> allUsers = this.userRepository.findAll();

        if (allUsers.size() != 0) {
            for (User eachUser : allUsers) {
                if (eachUser.getEmailId().equals(u.getEmailId())) {
                    String message = "Email is already taken";
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(new CustomMessage(HttpStatus.BAD_REQUEST, message));
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
        this.userRepository.save(u);

        String message = "User added successfully";
        return ResponseEntity.status(HttpStatus.OK).body(new CustomMessage(HttpStatus.OK, message));
    }

    /**
     * Endpoint for PUT - update the user in db
     *
     * @param u  updated user details in json
     * @param id the user to be updated
     * @return the updated user details
     */
    @PutMapping(path = "{userId}")
    public ResponseEntity<?> updateUser(@RequestBody User u, @PathVariable("userId") Long id) {
        // checks so that user cannot update to an already claimed it by other
        List<User> allUsers = this.userRepository.findAll();

        if (allUsers.size() != 0) {
            for (User eachUser : allUsers) {
                if (eachUser.getUserId() != id && eachUser.getEmailId().equals(u.getEmailId())) {
                    String message = "Email is already taken";
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(new CustomMessage(HttpStatus.BAD_REQUEST, message));
                }
            }
        }

        Optional<User> userOptional = this.userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // check if this is activation request
            if (user.getStatus() == UserStatus.INACTIVE && u.getStatus() == UserStatus.ACTIVE) {
                user.setActivationDate(LocalDate.now());
            }

            // check if this is a block request
            if (u.getStatus() == UserStatus.INACTIVE) {
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
            this.userRepository.save(user);
        }

        else {
            u.setUserId(id);
            this.userRepository.save(u);
        }

        String message = "User details updated successfully";
        return ResponseEntity.status(HttpStatus.OK).body(new CustomMessage(HttpStatus.OK, message));
    }

    /**
     * Endpoint for DELETE - delete user from db
     *
     * @param id user id
     */
    @DeleteMapping(path = "{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Long id) {
        if (!this.userRepository.existsById(id)) {
            String message = "Cannot find requested user";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomMessage(HttpStatus.NOT_FOUND, message));
        }

        if (this.userRepository.getById(id).getRole() == UserRole.ADMIN) {
            String message = "An admin cannot be deleted";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new CustomMessage(HttpStatus.BAD_REQUEST, message));
        }

        this.userRepository.deleteById(id);

        String message = "User deleted successfully";
        return ResponseEntity.status(HttpStatus.OK).body(new CustomMessage(HttpStatus.OK, message));
    }
}
