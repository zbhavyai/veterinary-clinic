package ca.ucalgary.vetapp.controller;

import ca.ucalgary.vetapp.model.Animal;
import ca.ucalgary.vetapp.model.Photos;
import ca.ucalgary.vetapp.model.Issues;
import ca.ucalgary.vetapp.model.Treatments;
import ca.ucalgary.vetapp.model.Comments;
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
@RequestMapping(path = "api/v1/animals")
public class AnimalController {
    private final AnimalRepository animalRepository;

    public AnimalController(AnimalRepository ar) {
        this.animalRepository = ar;
    }

    /**
     * Gets all animals
     *
     * @return
     */
    @GetMapping
    public List<Animal> getAllAnimals() {
        return this.animalRepository.findAll();
    }

    /**
     * Get one animal
     *
     * @param id
     * @return
     */
    @GetMapping(path = "{animalId}")
    public Animal getOneAnimal(@PathVariable("animalId") Long id) {
        Optional<Animal> animalOptional = this.animalRepository.findById(id);

        if (animalOptional.isPresent()) {
            Animal oneAnimal = animalOptional.get();
            return oneAnimal;
        }

        else {
            throw new NotFoundException(id);
        }
    }

    @GetMapping(path = "{animalId}/photos")
    public List<Photos> getPhotos(@PathVariable("animalId") Long id) {
        Optional<Animal> animalOptional = this.animalRepository.findById(id);

        if (animalOptional.isPresent()) {
            Animal oneAnimal = animalOptional.get();
            return oneAnimal.fetchAnimalPhotoList();
        }

        else {
            throw new NotFoundException(id);
        }
    }

    @GetMapping(path = "{animalId}/issues")
    public List<Issues> getIssues(@PathVariable("animalId") Long id) {
        Optional<Animal> animalOptional = this.animalRepository.findById(id);

        if (animalOptional.isPresent()) {
            Animal oneAnimal = animalOptional.get();
            return oneAnimal.fetchAnimalIssueList();
        }

        else {
            throw new NotFoundException(id);
        }
    }

    @GetMapping(path = "{animalId}/comments")
    public List<Comments> getComments(@PathVariable("animalId") Long id) {
        Optional<Animal> animalOptional = this.animalRepository.findById(id);

        if (animalOptional.isPresent()) {
            Animal oneAnimal = animalOptional.get();
            return oneAnimal.fetchAnimalCommentList();
        }

        else {
            throw new NotFoundException(id);
        }
    }

    @GetMapping(path = "{animalId}/treatments")
    public List<Treatments> getTreatments(@PathVariable("animalId") Long id) {
        Optional<Animal> animalOptional = this.animalRepository.findById(id);

        if (animalOptional.isPresent()) {
            Animal oneAnimal = animalOptional.get();
            return oneAnimal.fetchAnimalTreatmentList();
        }

        else {
            throw new NotFoundException(id);
        }
    }

    /**
     * Saves one animal
     *
     * @param a
     * @return
     */
    @PostMapping
    public Animal addAnimal(@RequestBody Animal a) {
        return this.animalRepository.save(a);
    }

    /**
     *
     * @param a
     * @param id
     * @return
     */
    @PutMapping(path = "{animalId}")
    public Animal updateAnimal(@RequestBody Animal a, @PathVariable("animalId") Long id) {
        Optional<Animal> animalOptional = this.animalRepository.findById(id);

        Animal updatedAnimal;

        if (animalOptional.isPresent()) {
            Animal animal = animalOptional.get();

            animal.setName(a.getName());
            animal.setType(a.getType());
            animal.setBreed(a.getBreed());
            animal.setBirthDate(a.getBirthDate());
            animal.setSex(a.getSex());
            animal.setStatus(a.getStatus());
            // animal.setTheOwner(a.getTheOwner());
            animal.setTattooNum(a.getTattooNum());
            animal.setRfidNumber(a.getRfidNumber());
            animal.setMicroChipNumber(a.getMicroChipNumber());
            animal.setWeight(a.getWeight());
            animal.setCoatColor(a.getCoatColor());
            animal.setContinuousMedication(a.getContinuousMedication());
            // animal.setAnimalPhotoList(a.getAnimalPhotoList());
            // animal.setAnimalTreatmentList(a.getAnimalTreatmentList());
            // animal.setAnimalIssueList(a.getAnimalIssueList());

            updatedAnimal = this.animalRepository.save(animal);
        }

        else {
            a.setAnimalId(id);
            updatedAnimal = this.animalRepository.save(a);
        }

        return updatedAnimal;
    }

    /**
     * Deletes one animal
     *
     * @param id
     */
    @DeleteMapping(path = "{animalId}")
    public void deleteAnimal(@PathVariable("animalId") Long id) {
        if (!this.animalRepository.existsById(id)) {
            throw new NotFoundException(id);
        }

        this.animalRepository.deleteById(id);
    }
}
