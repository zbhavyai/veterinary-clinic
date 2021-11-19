package ca.ucalgary.vetapp.controller;

import ca.ucalgary.vetapp.model.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@RestController
@RequestMapping(path = "api/v1/animals")
public class AnimalController {
    private final AnimalRepository animalRepository;

    public AnimalController(AnimalRepository ar) {
        this.animalRepository = ar;
    }

    /**
     * Get one animal
     *
     * @param id
     * @return
     */
    @GetMapping(path = "{animalId}")
    public Animal getAnimalById(@PathVariable("animalId") Long id) throws NotFoundException {
        Animal a = this.searchAnimalById(id).get(0);

        if (a == null) {
            throw new NotFoundException("animal", id);
        }

        else {
            return a;
        }
    }

    private List<Animal> searchAnimalById(Long id) {
        Optional<Animal> animalOptional = this.animalRepository.findById(id);

        if (animalOptional.isPresent()) {
            List<Animal> searchResults = new ArrayList<>();
            searchResults.add(animalOptional.get());
            return searchResults;
        }

        else {
            return null;
        }
    }

    private List<Animal> searchAnimalByName(String sT) {
        String searchTerm = sT.toLowerCase();

        List<Animal> allAnimals = this.animalRepository.findAll();
        List<Animal> searchResults = new ArrayList<>();

        for (Animal eachAnimal : allAnimals) {
            if (eachAnimal.getName() != null && eachAnimal.getName().toLowerCase().contains(searchTerm)) {
                searchResults.add(eachAnimal);
                continue;
            }
        }

        return searchResults;
    }

    private List<Animal> searchAnimalByType(String sT) {
        String searchTerm = sT.toLowerCase();

        List<Animal> allAnimals = this.animalRepository.findAll();
        List<Animal> searchResults = new ArrayList<>();

        for (Animal eachAnimal : allAnimals) {
            if (eachAnimal.getType() != null && eachAnimal.getType().toLowerCase().contains(searchTerm)) {
                searchResults.add(eachAnimal);
                continue;
            }
        }

        return searchResults;
    }

    private List<Animal> searchAnimalByBreed(String sT) {
        String searchTerm = sT.toLowerCase();

        List<Animal> allAnimals = this.animalRepository.findAll();
        List<Animal> searchResults = new ArrayList<>();

        for (Animal eachAnimal : allAnimals) {
            if (eachAnimal.getBreed() != null && eachAnimal.getBreed().toLowerCase().contains(searchTerm)) {
                searchResults.add(eachAnimal);
                continue;
            }
        }

        return searchResults;
    }

    private List<Animal> searchAnimalByOwner(String sT) {
        String searchTerm = sT.toLowerCase();

        List<Animal> allAnimals = this.animalRepository.findAll();
        List<Animal> searchResults = new ArrayList<>();

        for (Animal eachAnimal : allAnimals) {
            if(eachAnimal.getOwnerName() != null && eachAnimal.getOwnerName().toLowerCase().contains(searchTerm)) {
                searchResults.add(eachAnimal);
            }
        }

        return searchResults;
    }

    private List<Animal> searchAnimalByStatus(String sT) throws UnsupportedRequestException {
        try {
            AnimalStatus searchTerm = AnimalStatus.valueOf(sT.toUpperCase());

            List<Animal> allAnimals = this.animalRepository.findAll();
            List<Animal> searchResults = new ArrayList<>();

            for (Animal eachAnimal : allAnimals) {
                if (eachAnimal.getStatus() == searchTerm) {
                    searchResults.add(eachAnimal);
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
    public List<Animal> searchAnimal(@RequestParam(name = "searchBy", required = false) String searchBy,
            @RequestParam(name = "searchTerm", required = false) String searchTerm) throws UnsupportedRequestException {

        List<Animal> allAnimals = this.animalRepository.findAll();

        if (searchBy == null || searchTerm == null) {
            return allAnimals;
        }

        else if (searchBy.equals("") || searchTerm.equals("")) {
            return allAnimals;
        }

        else if (searchBy.equalsIgnoreCase("id")) {
            return this.searchAnimalById(Long.valueOf(searchTerm));
        }

        else if (searchBy.equalsIgnoreCase("name")) {
            return this.searchAnimalByName(searchTerm);
        }

        else if (searchBy.equalsIgnoreCase("type")) {
            return this.searchAnimalByType(searchTerm);
        }

        else if (searchBy.equalsIgnoreCase("breed")) {
            return this.searchAnimalByBreed(searchTerm);
        }

        else if (searchBy.equalsIgnoreCase("owner")) {
            return this.searchAnimalByOwner(searchTerm);
        }

        else if (searchBy.equalsIgnoreCase("status")) {
            return this.searchAnimalByStatus(searchTerm);
        }

        else {
            throw new UnsupportedRequestException("Invalid search by");
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
            throw new NotFoundException("animal", id);
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
            throw new NotFoundException("animal", id);
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
            throw new NotFoundException("animal", id);
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
            throw new NotFoundException("animal", id);
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
            throw new NotFoundException("animal", id);
        }

        this.animalRepository.deleteById(id);
    }
}
