package ca.ucalgary.vetapp.controller;

import ca.ucalgary.vetapp.model.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/owners")
public class OwnerController {
    private final OwnerRepository ownerRepository;

    /**
     * Constructor
     *
     * @param ownerRepository jpa repository for db operations
     */
    public OwnerController(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    /**
     * Search the owners by id
     *
     * @param id owner id
     * @return list of owners with id; the list would have atmost one element
     */
    private List<Owner> searchOwnerById(Long id) {
        Optional<Owner> ownerOptional = this.ownerRepository.findById(id);

        if (ownerOptional.isPresent()) {
            List<Owner> searchResults = new ArrayList<>();
            searchResults.add(ownerOptional.get());
            return searchResults;
        }

        else {
            return null;
        }
    }

    /**
     * Search the owners by contact number
     *
     * @param allOwners list of all owners
     * @param sT        search term
     * @return filtered list of owners having contact number meeting search term
     */
    private List<Owner> searchOwnerByContact(List<Owner> allOwners, String sT) {
        String searchTerm = sT.toLowerCase();
        List<Owner> searchResults = new ArrayList<>();

        for (Owner eachOwner : allOwners) {
            if (eachOwner.getContactNumber() != null && eachOwner.getContactNumber().contains(searchTerm)) {
                searchResults.add(eachOwner);
                continue;
            }
        }

        return searchResults;
    }

    /**
     * Search the owners by email
     *
     * @param allOwners list of all owners
     * @param sT        search term
     * @return filtered list of owners having email meeting search term
     */
    private List<Owner> searchOwnerByEmail(List<Owner> allOwners, String sT) {
        String searchTerm = sT.toLowerCase();
        List<Owner> searchResults = new ArrayList<>();

        for (Owner eachOwner : allOwners) {
            if (eachOwner.getEmailId() != null && eachOwner.getEmailId().toLowerCase().contains(searchTerm)) {
                searchResults.add(eachOwner);
                continue;
            }
        }

        return searchResults;
    }

    /**
     * Endpoint for GET - fetch owner by id
     *
     * @param id owner id
     * @return owner
     */
    @GetMapping(path = "{ownerId}")
    public Owner getOwnerById(@PathVariable("ownerId") Long id) {
        List<Owner> o = this.searchOwnerById(id);

        if (o.size() != 0) {
            return o.get(0);
        }

        else {
            // throw new NotFoundException("owner", id);
            return null;
        }
    }

    /**
     * Endpoint for GET - fetch all owned animals by owner id
     *
     * @param id owner id
     * @return list of animals owned by the owner
     */
    @GetMapping(path = "{ownerId}/animals")
    public List<Animal> getOwnedAnimals(@PathVariable("ownerId") Long id) {
        Optional<Owner> ownerOptional = this.ownerRepository.findById(id);

        if (ownerOptional.isPresent()) {
            Owner owner = ownerOptional.get();
            return owner.fetchOwnedAnimals();
        }

        else {
            // throw new NotFoundException("owner", id);
            return null;
        }
    }

    /**
     * Endpoint for GET - search and fetch all owners meeting search criteria
     *
     * @param searchBy   where to search
     * @param searchTerm what to search
     * @return list of owners meeting search criteria
     */
    @GetMapping()
    public List<Owner> searchOwner(@RequestParam(name = "searchBy", required = false) String searchBy,
            @RequestParam(name = "searchTerm", required = false) String searchTerm) {

        List<Owner> allOwners = this.ownerRepository.findAll();

        if (searchBy == null || searchTerm == null) {
            return allOwners;
        }

        else if (searchBy.equals("") || searchTerm.equals("")) {
            return allOwners;
        }

        else if (searchBy.equalsIgnoreCase("id")) {
            return this.searchOwnerById(Long.valueOf(searchTerm));
        }

        else if (searchBy.equalsIgnoreCase("contact")) {
            return this.searchOwnerByContact(allOwners, searchTerm);
        }

        else if (searchBy.equalsIgnoreCase("email")) {
            return this.searchOwnerByEmail(allOwners, searchTerm);
        }

        else {
            // throw new UnsupportedRequestException("Invalid search by");
            return null;
        }
    }

    /**
     * Endpoint for POST - save the owner in db
     *
     * @param o owner details in json
     * @return the added owner details
     */
    @PostMapping(path = "add")
    public Owner addOwner(@RequestBody Owner o) {
        o.setOwnerId(0);
        return this.ownerRepository.save(o);
    }

    /**
     * Endpoint for PUT - update the owner in db
     *
     * @param o  updated owner details in json
     * @param id the owner to be updated
     * @return the updated owner details
     */
    @PutMapping(path = "{ownerId}")
    public Owner updateOwner(@RequestBody Owner o, @PathVariable("ownerId") Long id) {
        Optional<Owner> ownerOptional = this.ownerRepository.findById(id);
        Owner updatedOwner;

        if (ownerOptional.isPresent()) {
            Owner owner = ownerOptional.get();

            owner.setFirstName(o.getFirstName());
            owner.setMiddleName(o.getMiddleName());
            owner.setLastName(o.getLastName());
            owner.setContactNumber(o.getContactNumber());
            owner.setEmailId(o.getEmailId());
            owner.setAddress(o.getAddress());

            updatedOwner = this.ownerRepository.save(owner);
        }

        else {
            o.setOwnerId(id);
            updatedOwner = this.ownerRepository.save(o);
        }

        return updatedOwner;
    }

    /**
     * Endpoint for DELETE - delete owner from db
     *
     * @param id owner id
     */
    @DeleteMapping(path = "{ownerId}")
    public void deleteOwner(@PathVariable("ownerId") Long id) {
        if (!this.ownerRepository.existsById(id)) {
            // throw new NotFoundException("owner", id);
        }

        // before deleting owner, he should have no animals in the system
        if (this.searchOwnerById(id).get(0).fetchOwnedAnimals().size() != 0) {
            // throw new UnsupportedRequestException("Please remove owned animals from the system first");
        }

        this.ownerRepository.deleteById(id);
    }
}
