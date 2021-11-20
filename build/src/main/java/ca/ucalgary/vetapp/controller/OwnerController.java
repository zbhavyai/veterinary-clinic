package ca.ucalgary.vetapp.controller;

import ca.ucalgary.vetapp.model.Owner;
import ca.ucalgary.vetapp.model.Animal;
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
@RequestMapping(path = "api/v1/owners")
public class OwnerController {
    private final OwnerRepository ownerRepository;

    public OwnerController(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    /**
     * Get one owner
     *
     * @param id
     * @return
     */
    @GetMapping(path = "{ownerId}")
    public Owner getOwnerById(@PathVariable("ownerId") Long id) throws NotFoundException {
        Owner o = this.searchOwnerById(id).get(0);

        if (o == null) {
            throw new NotFoundException("owner", id);
        }

        else {
            return o;
        }
    }

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

    private List<Owner> searchOwnerByContact(String sT) {
        String searchTerm = sT.toLowerCase();

        List<Owner> allOwners = this.ownerRepository.findAll();
        List<Owner> searchResults = new ArrayList<>();

        for (Owner eachOwner : allOwners) {
            if(eachOwner.getContactNumber() != null && eachOwner.getContactNumber().contains(searchTerm)) {
                searchResults.add(eachOwner);
                continue;
            }
        }

        return searchResults;
    }

    private List<Owner> searchOwnerByEmail(String sT) {
        String searchTerm = sT.toLowerCase();

        List<Owner> allOwners = this.ownerRepository.findAll();
        List<Owner> searchResults = new ArrayList<>();

        for (Owner eachOwner : allOwners) {
            if (eachOwner.getEmailId() != null && eachOwner.getEmailId().toLowerCase().contains(searchTerm)) {
                searchResults.add(eachOwner);
                continue;
            }
        }

        return searchResults;
    }

    @GetMapping(path = "{ownerId}/animals")
    public List<Animal> getOwnedAnimals(@PathVariable("ownerId") Long id) {
        Optional<Owner> ownerOptional = this.ownerRepository.findById(id);

        if (ownerOptional.isPresent()) {
            Owner owner = ownerOptional.get();
            return owner.fetchOwnedAnimals();
        }

        else {
            throw new NotFoundException("owner", id);
        }
    }

    @GetMapping()
    public List<Owner> searchOwner(@RequestParam(name = "searchBy", required = false) String searchBy,
            @RequestParam(name = "searchTerm", required = false) String searchTerm) throws UnsupportedRequestException {

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
            return this.searchOwnerByContact(searchTerm);
        }

        else if (searchBy.equalsIgnoreCase("email")) {
            return this.searchOwnerByEmail(searchTerm);
        }

        else {
            throw new UnsupportedRequestException("Invalid search by");
        }
    }

    /**
     * Saves one owner
     *
     * @param o
     * @return
     */
    @PostMapping(path = "add")
    public Owner addOwner(@RequestBody Owner o) {
        o.setOwnerId(0);
        return this.ownerRepository.save(o);
    }

    /**
     *
     * @param o
     * @param id
     * @return
     */
    @PutMapping(path = "{ownerId}")
    public Owner updateOwner(@RequestBody Owner o, @PathVariable("ownerId") Long id) {
        Optional<Owner> ownerOptional = this.ownerRepository.findById(id);

        Owner updatedOwner;

        if (ownerOptional.isPresent()) {
            Owner owner = ownerOptional.get();

            owner.setFirstName(o.getFirstName());
            owner.setLastName(o.getLastName());
            owner.setMiddleName(o.getMiddleName());
            owner.setRole(o.getRole());
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
     * Deletes one owner
     *
     * @param id
     */
    @DeleteMapping(path = "{ownerId}")
    public void deleteOwner(@PathVariable("ownerId") Long id) throws UnsupportedRequestException {
        if (!this.ownerRepository.existsById(id)) {
            throw new NotFoundException("owner", id);
        }

        // before deleting owner, he should have no animals in the system
        if(this.searchOwnerById(id).get(0).fetchOwnedAnimals().size() != 0) {
            throw new UnsupportedRequestException("Please remove owned animals from the system first");
        }

        this.ownerRepository.deleteById(id);
    }
}
