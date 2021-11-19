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
import org.springframework.web.bind.annotation.RestController;
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
     * Gets all owners
     *
     * @return
     */
    @GetMapping
    public List<Owner> getAllOwners() {
        return this.ownerRepository.findAll();
    }

    /**
     * Get one owner
     *
     * @param id
     * @return
     */
    @GetMapping(path = "{ownerId}")
    public Owner getOneOwner(@PathVariable("ownerId") Long id) {
        Optional<Owner> ownerOptional = this.ownerRepository.findById(id);

        if (ownerOptional.isPresent()) {
            Owner owner = ownerOptional.get();
            return owner;
        }

        else {
            throw new NotFoundException(id);
        }
    }

    @GetMapping(path = "{ownerId}/animals")
    public List<Animal> getOwnedAnimals(@PathVariable("ownerId") Long id) {
        Optional<Owner> ownerOptional = this.ownerRepository.findById(id);

        if (ownerOptional.isPresent()) {
            Owner owner = ownerOptional.get();
            return owner.fetchOwnedAnimals();
        }

        else {
            throw new NotFoundException(id);
        }
    }

    /**
     * Saves one owner
     *
     * @param u
     * @return
     */
    @PostMapping
    public Owner addOwner(@RequestBody Owner u) {
        return this.ownerRepository.save(u);
    }

    /**
     *
     * @param u
     * @param id
     * @return
     */
    @PutMapping(path = "{ownerId}")
    public Owner updateOwner(@RequestBody Owner u, @PathVariable("ownerId") Long id) {
        Optional<Owner> ownerOptional = this.ownerRepository.findById(id);

        Owner updatedOwner;

        if (ownerOptional.isPresent()) {
            Owner owner = ownerOptional.get();

            owner.setFirstName(u.getFirstName());
            owner.setLastName(u.getLastName());
            owner.setMiddleName(u.getMiddleName());
            owner.setRole(u.getRole());
            owner.setContactNumber(u.getContactNumber());
            owner.setEmailId(u.getEmailId());
            owner.setAddress(u.getAddress());

            updatedOwner = this.ownerRepository.save(owner);
        }

        else {
            u.setOwnerId(id);
            updatedOwner = this.ownerRepository.save(u);
        }

        return updatedOwner;
    }

    /**
     * Deletes one owner
     *
     * @param id
     */
    @DeleteMapping(path = "{ownerId}")
    public void deleteOwner(@PathVariable("ownerId") Long id) {
        if (!this.ownerRepository.existsById(id)) {
            throw new NotFoundException(id);
        }

        this.ownerRepository.deleteById(id);
    }
}
